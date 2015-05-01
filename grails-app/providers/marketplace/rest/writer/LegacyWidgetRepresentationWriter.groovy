package marketplace.rest.writer

import org.codehaus.groovy.grails.commons.GrailsApplication

import org.springframework.beans.factory.annotation.Autowired

import javax.ws.rs.ext.Provider
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

import marketplace.rest.LegacyWidget

import marketplace.hal.AbstractRepresentationWriter

import marketplace.rest.representation.out.LegacyWidgetRepresentation

@Provider
@Produces(MediaType.APPLICATION_JSON)
class LegacyWidgetRepresentationWriter extends
        AbstractRepresentationWriter<LegacyWidget> {

    @Autowired
    LegacyWidgetRepresentationWriter(GrailsApplication grailsApplication,
            LegacyWidgetRepresentation.Factory factory) {
        super(grailsApplication, factory)
    }
}
