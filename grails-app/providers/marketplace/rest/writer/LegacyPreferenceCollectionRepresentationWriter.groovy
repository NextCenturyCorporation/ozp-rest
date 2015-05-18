package marketplace.rest.writer

import org.codehaus.groovy.grails.commons.GrailsApplication

import org.springframework.beans.factory.annotation.Autowired

import javax.ws.rs.ext.Provider
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

import com.google.common.reflect.TypeToken

import marketplace.rest.LegacyPreference

import marketplace.hal.AbstractRepresentationWriter

import marketplace.rest.representation.out.LegacyPreferenceCollectionRepresentation
import marketplace.rest.ChildObjectCollection

@Provider
@Produces([MediaType.APPLICATION_JSON, MediaType.TEXT_HTML])
class LegacyPreferenceCollectionRepresentationWriter extends
        AbstractRepresentationWriter<Collection<LegacyPreference>> {
    @Autowired
    LegacyPreferenceCollectionRepresentationWriter(GrailsApplication grailsApplication,
            LegacyPreferenceCollectionRepresentation.Factory factory) {
        super(grailsApplication, factory)
    }
}
