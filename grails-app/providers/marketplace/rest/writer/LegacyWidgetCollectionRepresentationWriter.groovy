package marketplace.rest.writer

import org.codehaus.groovy.grails.commons.GrailsApplication

import org.springframework.beans.factory.annotation.Autowired

import javax.ws.rs.ext.Provider
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

import com.google.common.reflect.TypeToken

import marketplace.rest.LegacyWidget

import marketplace.hal.AbstractRepresentationWriter

import marketplace.rest.representation.out.LegacyWidgetCollectionRepresentation
import marketplace.rest.ChildObjectCollection

@Provider
@Produces(MediaType.APPLICATION_JSON)
class LegacyWidgetCollectionRepresentationWriter extends
        AbstractRepresentationWriter<Collection<LegacyWidget>> {
    @Autowired
    LegacyWidgetCollectionRepresentationWriter(GrailsApplication grailsApplication,
            LegacyWidgetCollectionRepresentation.Factory factory) {
        super(grailsApplication, factory)
    }
}
