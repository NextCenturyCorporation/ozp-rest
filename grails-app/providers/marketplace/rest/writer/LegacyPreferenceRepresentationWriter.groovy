package marketplace.rest.writer

import java.io.OutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.annotation.Annotation;

import org.codehaus.groovy.grails.commons.GrailsApplication

import org.springframework.beans.factory.annotation.Autowired

import javax.ws.rs.ext.Provider
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.MultivaluedMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import marketplace.rest.LegacyPreference

import marketplace.hal.AbstractRepresentationWriter

import marketplace.rest.representation.out.LegacyPreferenceRepresentation

@Provider
@Produces(MediaType.APPLICATION_JSON)
class LegacyPreferenceRepresentationWriter extends
        AbstractRepresentationWriter<LegacyPreference> {

    @Override
    public void writeTo(LegacyPreference t, Class<?> type, Type genericType,
            Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String,Object> httpHeaders, OutputStream entityStream)
            throws IOException {
        if (t.value != null) {
            super.writeTo(t, type, genericType, annotations, mediaType,
                httpHeaders, entityStream)
        } else {
            def representation = [success: true, preference: null]
            def objectMapper = new ObjectMapper()
            entityStream.write(objectMapper.writeValueAsBytes(representation));

        }
    }

    @Autowired
    LegacyPreferenceRepresentationWriter(GrailsApplication grailsApplication,
            LegacyPreferenceRepresentation.Factory factory) {
        super(grailsApplication, factory)
    }
}
