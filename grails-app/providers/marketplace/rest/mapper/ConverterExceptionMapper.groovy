package marketplace.rest.mapper

import javax.ws.rs.core.Response
import javax.ws.rs.ext.Provider

import org.codehaus.groovy.grails.web.converters.exceptions.ConverterException

@Provider
class ConverterExceptionMapper extends RestExceptionMapper<ConverterException> {
    ConverterExceptionMapper() {
        super(Response.Status.BAD_REQUEST)
    }
}
