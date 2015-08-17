package marketplace.rest.writer

import org.codehaus.groovy.grails.commons.GrailsApplication

import org.springframework.beans.factory.annotation.Autowired

import marketplace.FilteredListings
import marketplace.hal.AbstractRepresentationWriter

import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.ext.Provider

import marketplace.rest.representation.out.FilteredListingsCountsRepresentation

@Provider
@Produces([
    MediaType.APPLICATION_JSON
])
class FilteredListingsCountsRepresentationWriter extends AbstractRepresentationWriter<FilteredListings.Counts> {

    @Autowired
    FilteredListingsCountsRepresentationWriter(GrailsApplication grailsApplication,
            FilteredListingsCountsRepresentation.Factory factory) {
        super(grailsApplication, factory)
    }
}
