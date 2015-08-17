package marketplace.rest.representation.out

import org.springframework.stereotype.Component

import marketplace.hal.ApplicationRootUriBuilderHolder
import marketplace.hal.AbstractHalRepresentation
import marketplace.hal.RepresentationFactory

import marketplace.FilteredListings

class FilteredListingsCountsRepresentation extends AbstractHalRepresentation<FilteredListings.Counts> {
    private FilteredListings.Counts counts

    private FilteredListingsCountsRepresentation(FilteredListings.Counts counts) {
        this.counts = counts
    }

    public int getApproved() { counts.approved }
    public int getEnabled() { counts.enabled }
    public Map<String, Integer> getAgencyCounts() {
            return counts.agencyCounts.inject([:]) { acc, k, v ->
                k ? acc + [new AbstractMap.SimpleEntry(k.toString(), v)] : acc
            }
        }

    @Component
    public static class Factory implements RepresentationFactory<FilteredListings.Counts> {
        AbstractHalRepresentation<FilteredListings.Counts> toRepresentation(
                FilteredListings.Counts counts,
                ApplicationRootUriBuilderHolder uriBuilderHolder) {
            new FilteredListingsCountsRepresentation(counts)
        }
    }
}
