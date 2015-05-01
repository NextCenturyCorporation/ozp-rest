package marketplace.rest.writer

import org.springframework.beans.factory.annotation.Autowired

import javax.ws.rs.ext.Provider
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

import marketplace.rest.LegacyWidget

import marketplace.hal.AbstractWindownameWriter

@Provider
@Produces([MediaType.TEXT_HTML])
class LegacyWidgetCollectionWindownameWriter extends
        AbstractWindownameWriter<Collection<LegacyWidget>> {
    @Autowired
    LegacyWidgetCollectionWindownameWriter(LegacyWidgetCollectionRepresentationWriter writer) {
        super(writer)
    }
}
