package marketplace.rest.service

import marketplace.Listing
import marketplace.search.SearchCriteria
import marketplace.search.SearchResult
import org.elasticsearch.index.query.FilterBuilder
import org.elasticsearch.index.query.FilterBuilders
import org.elasticsearch.index.query.QueryBuilders
import org.elasticsearch.index.query.QueryBuilder
import org.elasticsearch.search.sort.FieldSortBuilder
import org.elasticsearch.search.sort.ScoreSortBuilder
import org.elasticsearch.search.sort.SortBuilder
import org.elasticsearch.search.sort.SortOrder
import org.grails.plugins.elasticsearch.ElasticSearchService
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired

@Service
class ListingSearchService {

    @Autowired ElasticSearchService elasticSearchService

    public SearchResult<Listing> searchListings(SearchCriteria sc) {
        def searchOptions = [
                size: sc.max,
                from: sc.offset,
                types: ['marketplace.Listing'],
                sort: getSortBuilder(sc)
        ]

        def searchData = elasticSearchService.search(searchOptions, getQueryBuilder(sc))
        def result = new SearchResult<Listing>()

        result.with {
            searchCriteria = sc
            total = searchData.total
            items = searchData.searchResults
        }

        result
    }

    /**
     * Create the query builder that will ultimately be passed to the elasticsearch client from the criteria.
     * Conditionally creates either a filter query (if there are filters) or a simple query string query.
     *
     * @param searchCriteria
     * @return
     */
    private static QueryBuilder getQueryBuilder(SearchCriteria searchCriteria) {
        if(searchCriteria.filters) {
            QueryBuilders.filteredQuery(
                getQueryStringQueryBuilder(searchCriteria),
                getFilterBuilder(searchCriteria)
            )
        } else {
            getQueryStringQueryBuilder(searchCriteria)
        }
    }

    /**
     * Create a simple query string query builder
     *
     * @param searchCriteria
     * @return
     */
    private static QueryBuilder getQueryStringQueryBuilder(SearchCriteria searchCriteria) {
        def queryStringQueryBuilder = QueryBuilders.queryString(searchCriteria.queryString)
        searchCriteria.fields.each { queryStringQueryBuilder.field(it) }

        queryStringQueryBuilder
    }

    /**
     * Create the filter builder from the criteria. In general, the filter clause is a bool filter
     * with a must clause - containing a single query filter - for each filtered field.
     * In the cases where the field is in a nested object, the query filter is a nested filter which
     * handles the multipart path.
     *
     * @param searchCriteria
     * @return
     */
    private static FilterBuilder getFilterBuilder(SearchCriteria searchCriteria) {
        def boolFilterBuilder = FilterBuilders.boolFilter()

        searchCriteria.filters.each { String field, List values ->
            Collection<FilterBuilder> fieldFilters
            def filterCombinationMethod = searchCriteria.UNION_FILTERS.contains(field) ?
                    FilterBuilders.&orFilter : FilterBuilders.&andFilter

            if(field.contains(".")) {
                String nestedPath = field.split("\\.")[0]

                fieldFilters = values.collect { String value ->
                    FilterBuilders.nestedFilter(
                        nestedPath,
                        QueryBuilders
                            .queryString("\"$value\"")
                            .defaultField(field)
                    )
                }
            } else {
                fieldFilters = values.collect { String value ->
                    FilterBuilders.queryFilter(
                        QueryBuilders
                            .queryString("\"$value\"")
                            .defaultField(field)
                    )
                }
            }

            boolFilterBuilder.must(filterCombinationMethod(fieldFilters as FilterBuilder[]))
        }

        boolFilterBuilder
    }

    /**
     * Build a list of Sort Builders. The primary sort order is always what is specified
     * by the sort query parameter. The secondary sort is average rating, DESC and the tertiary
     * sort is listing title, ASC. In the case where primary order is average rating, the
     * secondary order is listing title and there is no tertiary order.
     *
     * @param searchCriteria
     * @return
     */
    private static List<SortBuilder> getSortBuilder(SearchCriteria searchCriteria) {
        SortOrder order = SortOrder.valueOf(searchCriteria.order)
        String sort = searchCriteria.sort
        def sorters = []

        sorters << (sort == 'score' ? new ScoreSortBuilder().order(order) : new FieldSortBuilder(sort).order(order))
        if(sort != 'avgRate') sorters << new FieldSortBuilder('avgRate').order(SortOrder.DESC)
        sorters << new FieldSortBuilder('sortTitle').order(SortOrder.ASC)

        sorters
    }
}
