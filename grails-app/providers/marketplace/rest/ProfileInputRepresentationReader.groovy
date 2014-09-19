package marketplace.rest

import javax.ws.rs.Consumes
import javax.ws.rs.ext.Provider
import javax.ws.rs.core.MediaType

@Consumes([
    ProfileInputRepresentation.MEDIA_TYPE,
    MediaType.APPLICATION_JSON
])
@Provider
class ProfileInputRepresentationReader
        extends AbstractRepresentationReader<ProfileInputRepresentation> {}
