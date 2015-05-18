package marketplace.rest.resource

import javax.ws.rs.PathParam
import javax.ws.rs.FormParam
import javax.ws.rs.QueryParam
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.Consumes
import javax.ws.rs.PUT
import javax.ws.rs.GET
import javax.ws.rs.DELETE
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import marketplace.rest.LegacyPreference
import marketplace.rest.LegacyUser
import marketplace.rest.LegacyWidget

import marketplace.IwcDataObject
import marketplace.Profile
import marketplace.Listing

import marketplace.rest.service.ProfileRestService
import marketplace.rest.service.ListingRestService
import marketplace.rest.representation.out.LegacyPreferenceRepresentation

import org.springframework.beans.factory.annotation.Autowired

/**
 * This class contains endpoints that are a carry-over (interface-wise) from OWF 7.x
 * and earlier.  These endpoints still need to exist to support OWF 7 widget compatibility.
 */
@Path('/api/prefs')
@Produces([
    MediaType.APPLICATION_JSON,
    MediaType.TEXT_HTML
])
@Consumes(
    MediaType.APPLICATION_FORM_URLENCODED
)
class LegacyResource {

    @Autowired ProfileRestService profileRestService
    @Autowired ListingRestService listingRestService

    @Path('/preference/{namespace}/{name}')
    @PUT
    public LegacyPreference setPreference(
            @PathParam('namespace') String namespace,
            @PathParam('name') String name,
            @FormParam('value') String value) {
        Profile currentUser = profileRestService.getCurrentUserProfile()
        Long userId = currentUser.id
        String key = namespace + (char) 0x1E + name

        profileRestService.updateDataItem(userId, key, value, 'application/json')

        new LegacyPreference(namespace, name, value, currentUser)
    }

    @Path('/preference/{namespace}/')
    @GET
    @Produces([
        MediaType.APPLICATION_JSON,
        MediaType.TEXT_HTML
    ])

    public Collection<LegacyPreference> getPreference(
        @PathParam('namespace') String namespace) {

        Collection<LegacyPreference> list = new ArrayList<LegacyPreference>()
        Profile currentUser = profileRestService.getCurrentUserProfile()
        Long userId = currentUser.id

        Collection<IwcDataObject> iwcList = profileRestService.findDataItems(userId,namespace)
        String breaker = "" + (char) 0x1E
        
        iwcList.each{
            data -> list.add(new LegacyPreference(namespace,data.key.substring(data.key.indexOf(breaker)+1),data.entity,currentUser))
        }

        list
    }

    @Path('/preference/{namespace}/{name}')
    @GET
    @Produces([
        MediaType.APPLICATION_JSON,
        MediaType.TEXT_HTML
    ])

    public Collection<LegacyPreference> getPreference(
        @PathParam('namespace') String namespace,
        @PathParam('name') String name) {

        Collection<LegacyPreference> list = new ArrayList<LegacyPreference>()
        Profile currentUser = profileRestService.getCurrentUserProfile()
        Long userId = currentUser.id
        
        if (name!="undefined") {
            String key = namespace + (char) 0x1E + name
            IwcDataObject data = profileRestService.getDataItem(userId,key)
            list.add(new LegacyPreference(namespace, name, data.entity, currentUser))
        }
        list
    }

    @Path('/preference/{namespace}/{name}')
    @DELETE
    @Produces([
        MediaType.APPLICATION_JSON,
        MediaType.TEXT_HTML
    ])
    public LegacyPreference deletePreference(
        @PathParam('namespace') String namespace,
        @PathParam('name') String name) {

        Profile currentUser = profileRestService.getCurrentUserProfile()
        Long userId = currentUser.id
        String key = namespace + (char) 0x1E + name

        IwcDataObject data = profileRestService.getDataItem(userId, key)
        profileRestService.deleteDataItem(userId, key)

        new LegacyPreference(namespace, name, data.entity, currentUser)
    }

    @Path('/hasPreference/{namespace}/{name}')
    @GET
    @Produces([
        MediaType.APPLICATION_JSON,
        MediaType.TEXT_HTML
    ])
    public Map<String, Object> hasPreference(
        @PathParam('namespace') String namespace,
        @PathParam('name') String name
    ) {
        Number statusCode = 200
        Boolean preferenceExist

        LegacyPreference pref = getPreference(namespace, name)
        if (pref) {
            preferenceExist = true
        } else {
            preferenceExist = false
        }

        [
            preferenceExist: preferenceExist,
            statusCode: statusCode
        ]
    }

    @Path('/person/whoami')
    @GET
    @Produces([
        MediaType.APPLICATION_JSON,
        MediaType.TEXT_HTML
    ])
    public LegacyUser getCurrentUser() {
        Profile currentUser = profileRestService.getCurrentUserProfile()

        new LegacyUser(currentUser)
    }


    @Path('widget/{widgetGuid}')
    @GET
    @Produces([
        MediaType.APPLICATION_JSON,
        MediaType.TEXT_HTML
    ])
    public LegacyWidget getWidget(
        @PathParam('widgetGuid') String widgetGuid
    ) {
        Profile currentUser = profileRestService.getCurrentUserProfile()
        Long userId = currentUser.id

        Listing listing = Listing.createCriteria().get() {
            eq('uuid', widgetGuid)
            owners {
                eq('id',userId)
            }
        }

        new LegacyWidget(listing)
    }

    @Path('widget')
    @GET
    @Produces([
        MediaType.APPLICATION_JSON,
        MediaType.TEXT_HTML
    ])
    public Collection<LegacyWidget> findWidgets(
        @QueryParam('widgetName') String widgetName,
        @QueryParam('universalName') String universalName,
        @QueryParam('widgetVersion') String widgetVersion,
        @QueryParam('widgetGuid') String widgetGuid
    ) {
        Collection<LegacyWidget> list = new ArrayList<LegacyWidget>()
        Profile currentUser = profileRestService.getCurrentUserProfile()
        Long userId = currentUser.id

        if (widgetName || universalName || widgetVersion || widgetGuid) {
            Collection<Listing> listings = Listing.createCriteria().list() {
                or {
                    if (widgetName) {
                        ilike('title', widgetName)
                    }
                    if (universalName) {
                        eq('title', universalName)
                    }
                }
                if (widgetVersion) {
                    like('versionName', widgetVersion)
                }
                if (widgetGuid) {
                    like('uuid', widgetGuid)
                }
                owners {
                    eq('id', userId)
                }
            }

            listings.each { listing -> 
                list.add(new LegacyWidget(listing))
            }
        } else {
            listingRestService.getAllByAuthorId(userId).each { listing ->
                list.add(new LegacyWidget(listing))
            }
        }
        return list
    }

    @Path('widget/listUserAndGroupWidgets')
    @GET
    @Produces([
        MediaType.APPLICATION_JSON,
        MediaType.TEXT_HTML
    ])
    public Collection<LegacyWidget> getAllWidgets(
        @QueryParam('widgetName') String widgetName,
        @QueryParam('widgetVersion') String widgetVersion,
        @QueryParam('widgetGuid') String widgetGuid,
        @QueryParam('universalName') String universalName)
    ) {
        findWidgets(widgetName, universalName, widgetVersion, widgetGuid)
    }

    @Path('server/resources')
    @GET
    @Produces([
        MediaType.APPLICATION_JSON,
        MediaType.TEXT_HTML
    ])
    public Map<String, String> getServerVersion() {
        [
            serverVersion: '1.0'
        ]
    }

}
