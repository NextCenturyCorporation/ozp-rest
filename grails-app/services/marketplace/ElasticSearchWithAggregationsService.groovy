/*
 * Copyright 2002-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package marketplace

import org.codehaus.groovy.grails.commons.GrailsApplication
import org.codehaus.groovy.grails.plugins.support.aware.GrailsApplicationAware
import org.elasticsearch.action.count.CountRequest
import org.elasticsearch.action.search.SearchRequest
import org.elasticsearch.action.search.SearchType
import org.elasticsearch.action.support.QuerySourceBuilder
import org.elasticsearch.client.Client
import org.elasticsearch.index.query.QueryBuilder
import org.elasticsearch.index.query.QueryStringQueryBuilder
import org.elasticsearch.index.query.FilterBuilder
import org.elasticsearch.search.SearchHit
import org.elasticsearch.search.builder.SearchSourceBuilder
import org.elasticsearch.search.highlight.HighlightBuilder
import org.elasticsearch.search.sort.SortBuilder
import org.elasticsearch.search.sort.SortOrder
import org.grails.plugins.elasticsearch.mapping.SearchableClassMapping
import org.grails.plugins.elasticsearch.util.GXContentBuilder
import org.grails.plugins.elasticsearch.ElasticSearchService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.elasticsearch.search.aggregations.AggregationBuilder

import static org.elasticsearch.index.query.QueryBuilders.queryString
import static org.elasticsearch.index.query.QueryStringQueryBuilder.Operator

class ElasticSearchWithAggregationsService extends ElasticSearchService {
    static final Logger LOG = LoggerFactory.getLogger(this)

    /**
     * Builds a search request
     *
     * @param params The query parameters
     * @param query The search query, whether a String, a Closure or a QueryBuilder
     * @param filter The search filter, whether a Closure or a FilterBuilder
     * @return The SearchRequest instance
     */
    public SearchRequest buildSearchRequest(query, filter, Map params, List<AggregationBuilder> aggBuilders) {
        SearchSourceBuilder source = new SearchSourceBuilder()

        source.from(params.from ? params.from as int : 0)
                .size(params.size ? params.size as int : 60)
                .explain(params.explain ?: true).minScore(params.min_score ?: 0)

        if (params.sort) {
            def sorters = (params.sort instanceof Collection) ? params.sort : [params.sort]

            sorters.each {
                if (it instanceof SortBuilder) {
                    source.sort(it as SortBuilder)
                } else {
                    source.sort(it, SortOrder.valueOf(params.order?.toUpperCase() ?: "ASC"))
                }
            }
        }

        // Handle the query, can either be a closure or a string
        if (query) {
            setQueryInSource(source, query, params)
        }

        if (filter) {
            setFilterInSource(source, filter, params)
        }

        // Handle highlighting
        if (params.highlight) {
            def highlighter = new HighlightBuilder()
            // params.highlight is expected to provide a Closure.
            def highlightBuilder = params.highlight
            highlightBuilder.delegate = highlighter
            highlightBuilder.resolveStrategy = Closure.DELEGATE_FIRST
            highlightBuilder.call()
            source.highlight highlighter
        }

        source.explain(false)

        for (AggregationBuilder ab : aggBuilders) {
            source.aggregation(ab)
        }

        SearchRequest request = new SearchRequest()
        request.searchType SearchType.DFS_QUERY_THEN_FETCH
        request.source source

        return request
    }

    /**
     * Computes a search request and builds the results
     *
     * @param request The SearchRequest to compute
     * @param params Search parameters
     * @return A Map containing the search results
     */
    def search(SearchRequest request, Map params) {
        resolveIndicesAndTypes(request, params)
        elasticSearchHelper.withElasticSearch { Client client ->
            LOG.debug 'Executing search request.'
            def response = client.search(request).actionGet()
            LOG.debug 'Completed search request.'

            def searchHits = response.getHits()
            def result = [:]
            result.total = searchHits.totalHits()

            LOG.debug "Search returned ${result.total ?: 0} result(s)."

            // Convert the hits back to their initial type
            result.searchResults = domainInstancesRebuilder.buildResults(searchHits)

            // Extract highlight information.
            // Right now simply give away raw results...
            if (params.highlight) {
                def highlightResults = []
                for (SearchHit hit : searchHits) {
                    highlightResults << hit.highlightFields
                }
                result.highlight = highlightResults
            }

            LOG.debug 'Adding score information to results.'

            //Extract score information
            //Records a map from hits of (hit.id, hit.score) returned in 'scores'
            if (params.score) {
                def scoreResults = [:]
                for (SearchHit hit : searchHits) {
                    scoreResults[(hit.id)] = hit.score
                }
                result.scores = scoreResults
            }

            if (params.sort) {
                def sortValues = [:]
                searchHits.each { SearchHit hit ->
                    sortValues[hit.id] = hit.sortValues
                }
                result.sort = sortValues
            }

            def aggregations = response.getAggregations()
            if (aggregations) {
                def aggregationsAsMap = aggregations.asMap()
                if (aggregationsAsMap) {
                    result.aggregations = aggregationsAsMap
                }
            }

            result
        }
    }
}
