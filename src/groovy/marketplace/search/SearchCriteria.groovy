package marketplace.search

import org.apache.log4j.Logger
import org.elasticsearch.index.query.QueryBuilder
import org.elasticsearch.index.query.QueryBuilders
import org.elasticsearch.search.builder.SearchSourceBuilder
import org.elasticsearch.search.facet.query.QueryFacetBuilder
import org.elasticsearch.search.facet.terms.TermsFacetBuilder
import org.elasticsearch.search.sort.FieldSortBuilder
import org.elasticsearch.search.sort.ScoreSortBuilder
import org.elasticsearch.search.sort.SortOrder
import ozone.utils.ApplicationContextHolder

class SearchCriteria implements Cloneable, Serializable {
    private static final log = Logger.getLogger(SearchCriteria.class)

    static final String SECONDARY_SORT = 'avgRate'
    static final SortOrder SECONDARY_ORDER = SortOrder.DESC
    static final String TERTIARY_SORT = 'sortTitle'
    static final SortOrder TERTIARY_ORDER = SortOrder.ASC

    static final Integer DEFAULT_FACET_SIZE = 100

    static final Collection<String> TYPES_TO_SEARCH = ['marketplace.Listing']

    static final String[] TERM_FACETS = ['types', 'categories', 'agency']

    String sort
    String order = "asc"
    boolean facets = false
    def max
    def offset

    PredicateFactory predicateFactory

    /**
     * Collection of predicates form which search clause is generated.
     */
    Map<String, Predicate> predicateMap

    public SearchCriteria(params) {
        predicateFactory = PredicateFactory.instance

        predicateMap = predicateFactory.buildFiltersForRequestParams(params)
        predicateMap = predicateFactory.addQueryStringPredicateIfMissing(predicateMap)

        sort = params.sort
        order = params.order
        max = params.max
        offset = params.offset
        facets = params.facets
    }

    public updateBean(params) {
        if (params.sort) {
            sort = params.sort
        }
        if (params.order) {
            order = params.order
        }
        if (params.max) {
            max = params.max
        }
        if (params.offset) {
            offset = params.offset
        }
        if(params.facets) {
            facets = params.facets
        }

        // Add predicates from parameter map
        predicateMap.putAll(predicateFactory.buildFiltersForRequestParams(params))
    }

    public updateParams(params) {
        if (sort) {
            params.sort = sort
        }
        if (order) {
            params.order = order
        }
        if (max) {
            params.max = max
        }
        if (offset) {
            params.offset = offset
        }
        if(facets) {
            params.facets = facets
        }
        params
    }

    /**
     * Add a new search criterion for the given attribute. If this attribute already has values,
     * append to the list
     * @param field
     * @param val
     * @return
     */
    def addSearch(String field, String val) {
        log.info "OP-3759: addSearch $field, $val"

        if (this.predicateMap[(field)] && this.predicateMap[(field)] instanceof MultiValuePredicate) {
            MultiValuePredicate multiValueFilter = this.predicateMap[(field)]
            multiValueFilter.addValue(val)
        } else {
            replaceSearch(field, val)
        }
    }

    /**
     * Replace a search criterion values for the given attribute, if one exists
     * @param field
     * @param val
     * @return
     */
    def replaceSearch(String field, String val) {
        log.info "OP-3759: replaceSearch $field, $val"
        log.info "OP-3759: predicateMap = ${this.predicateMap}"
        this.predicateMap[(field)] = this.predicateFactory.getPredicateForParam(field, val)
    }

    /**
     * Remove a search criterion value for the given attribute. Remove the entire criterion if the last value is removed.
     * @param field
     * @param val
     * @return
     */
    def clearSearch(String field, String val) {
        if (val && this.predicateMap[(field)] && this.predicateMap[(field)] instanceof MultiValuePredicate) {
            MultiValuePredicate multiValuePredicate = this.predicateMap[(field)]
            multiValuePredicate.removeValue(val)
            if (!multiValuePredicate.hasValues()) this.predicateMap.remove(field)
        } else {
            this.predicateMap.remove(field)
        }
        this.predicateMap = this.predicateFactory.addQueryStringPredicateIfMissing(this.predicateMap)
    }

    /**
     * Builds search clause for ElasticSearch query based on the collection of predicates.
     * @return
     */
    def getSearchClause() {
        List<Predicate> allPredicates = predicateMap.values().toList()
        List<Predicate> filters = allPredicates.findAll {it.isFilter()}
        List<Predicate> queries = allPredicates - filters

        def result
        if (allPredicates) {
            result = {
                filtered {
                    if (queries) {
                        query {
                            bool {
                                queries.each { Predicate query ->
                                    Closure searchClause = (Closure) query.getSearchClause()
                                    searchClause.delegate = delegate.delegate
                                    searchClause()
                                }
                            }
                        }
                    }
                    if (filters) {
                        filter {
                            query {
                                bool {
                                    filters.each { Predicate filter ->
                                        Closure searchClause = (Closure) filter.getSearchClause()
                                        searchClause.delegate = delegate.delegate
                                        searchClause()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            result = {
                bool {
                    must {
                        query_string(default_field: "_all", query: "*")
                    }
                }
            }
        }
        result
    }

    /**
     * Return the named filter value as a property of this class
     * @param name
     * @return
     */
    def propertyMissing(String name) {
        if (predicateMap[(name)])
            return predicateMap[(name)].value
        else
            null
    }

    /**
     * Replace search predicate
     * @param name
     * @param value
     * @return
     */
    def propertyMissing(String name, value) {
        replaceSearch(name, value)
    }

    SearchSourceBuilder getExtraSearchSource() {
        SearchSourceBuilder source = new SearchSourceBuilder()

        addSort(source)
        addFacets(source)

        return source
    }

    def addSort(SearchSourceBuilder source) {
        //title is tokenized per word, but we want to sort on the whole title,
        //which is stored in the sortTitle field of the index
        if (sort == 'title') {
            sort = 'sortTitle'
        }

        if(sort == 'score') {
            source.sort(new ScoreSortBuilder().order(SortOrder.DESC))
        } else if(sort) {
            source.sort(new FieldSortBuilder(sort).order(SortOrder.valueOf(order.toUpperCase()) ?: SortOrder.ASC))
        }

        if(sort != SECONDARY_SORT) {
            source.sort(new FieldSortBuilder(SECONDARY_SORT).order(SECONDARY_ORDER))
        }

        if(sort != TERTIARY_SORT) {
            source.sort(new FieldSortBuilder(TERTIARY_SORT).order(TERTIARY_ORDER))
        }
    }

    def addFacets(SearchSourceBuilder source) {
        if(facets) {
            TERM_FACETS.each { String term ->
                source.facet(new TermsFacetBuilder(term).field("${term}.id").size(DEFAULT_FACET_SIZE))
            }
        }
    }
}
