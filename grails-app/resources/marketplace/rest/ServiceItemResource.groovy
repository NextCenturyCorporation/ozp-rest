package marketplace.rest

import javax.ws.rs.Consumes
import javax.ws.rs.Path
import javax.ws.rs.POST
import javax.ws.rs.PUT
import javax.ws.rs.GET
import javax.ws.rs.DELETE
import javax.ws.rs.Produces
import javax.ws.rs.PathParam
import javax.ws.rs.QueryParam
import javax.ws.rs.HeaderParam
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

import javax.servlet.http.HttpServletRequest

import org.springframework.beans.factory.annotation.Autowired

import org.grails.plugins.elasticsearch.ElasticSearchAdminService

import marketplace.ServiceItem
import marketplace.RejectionListing
import marketplace.ItemComment
import marketplace.ServiceItemActivity

import marketplace.WebUtil

import static org.grails.jaxrs.response.Responses.created

@Path('/api/serviceItem')
class ServiceItemResource extends DomainResource<ServiceItem> {
    ServiceItemRestService serviceItemRestService
    ServiceItemActivityRestService serviceItemActivityRestService
    RejectionListingRestService rejectionListingRestService
    ItemCommentRestService itemCommentRestService
    ElasticSearchAdminService elasticSearchAdminService

    @Autowired
    ServiceItemResource(ServiceItemRestService service) {
        super(ServiceItem.class, service)
    }

    ServiceItemResource() {}

    @POST
    @Consumes([
        ListingInputRepresentation.MEDIA_TYPE,
        MediaType.APPLICATION_JSON
    ])
    Response create(ListingInputRepresentation rep) {
        created service.createFromRepresentation(rep)
    }

    @PUT
    @Path('/{id}')
    @Consumes([
        ListingInputRepresentation.MEDIA_TYPE,
        MediaType.APPLICATION_JSON
    ])
    ServiceItem update(@PathParam('id') long id, ListingInputRepresentation rep) {
        service.updateById(id, rep)
    }

    @Path('/activity')
    @GET
    public Collection<ServiceItemActivity> getActivitiesForServiceItems(
            @QueryParam('offset') Integer offset,
            @QueryParam('max') Integer max) {
        serviceItemActivityRestService.getAll(offset, max)
    }

    @Path('/{serviceItemId}/activity')
    @GET
    public Collection<ServiceItemActivity> getServiceItemActivitiesForServiceItem(
            @PathParam('serviceItemId') long serviceItemId,
            @QueryParam('offset') Integer offset,
            @QueryParam('max') Integer max) {
        serviceItemActivityRestService.getByParentId(serviceItemId, offset, max)
    }

    @Path('/{serviceItemId}/rejectionListing')
    @POST
    public RejectionListing createRejectionListing(@PathParam('serviceItemId') long serviceItemId,
            RejectionListing dto) {
        rejectionListingRestService.createFromParentIdAndDto(serviceItemId, dto)
    }

    @Path('/{serviceItemId}/rejectionListing')
    @GET
    public RejectionListing getMostRecentRejectionListing(
            @PathParam('serviceItemId') long serviceItemId) {
        rejectionListingRestService.getMostRecentRejectionListing(serviceItemId)
    }

    @Path('/{serviceItemId}/requiredServiceItems')
    //add JSONP support.  javascript has to be first in the list because browsers
    //send */* Accept headers for script tags, which is quite unhelpful
    @Produces(['application/javascript', 'text/javascript', 'application/json'])
    @GET
    public Collection<ServiceItem> getRequiredServiceItems(
            @PathParam('serviceItemId') long serviceItemId) {

        serviceItemRestService.getAllRequiredServiceItemsByParentId(serviceItemId)
    }

    @Path('/{serviceItemId}/requiringServiceItems')
    @Produces(['application/javascript', 'text/javascript', 'application/json'])
    @GET
    public Collection<ServiceItem> getRequiringServiceItems(
            @PathParam('serviceItemId') long serviceItemId) {

        serviceItemRestService.getRequiringServiceItemsByChildId(serviceItemId)
    }

    @Path('/{serviceItemId}/itemComment')
    @GET
    public Collection<ItemComment> getItemCommentsByServiceItemId(
            @PathParam('serviceItemId') long serviceItemId) {
        itemCommentRestService.getByParentId(serviceItemId)
    }

    @Path('/{serviceItemId}/itemComment')
    @POST
    public ItemComment createItemComment(@PathParam('serviceItemId') long serviceItemId,
            ItemComment dto) {
        itemCommentRestService.createFromParentIdAndDto(serviceItemId, dto)
    }

    @Path('/{serviceItemId}/itemComment/{itemCommentId}')
    @PUT
    public ItemComment updateItemComment(@PathParam('serviceItemId') long serviceItemId,
            @PathParam('itemCommentId') long id, ItemComment dto) {
        itemCommentRestService.updateByParentId(serviceItemId, id, dto)
    }

    @Path('/{serviceItemId}/itemComment/{itemCommentId}')
    @DELETE
    public void deleteItemComment(@PathParam('itemCommentId') long itemCommentId) {
        itemCommentRestService.deleteById(itemCommentId)
    }

    @Override
    public void delete(long id) {
        super.delete(id)
        refreshElasticSearch()
    }

    @Override
    public Response create(ServiceItem dto) {
        Response retval = super.create(dto)
        refreshElasticSearch()
        return retval
    }

    @Override
    public ServiceItem update(long id, ServiceItem dto) {
        ServiceItem retval = super.update(id, dto)
        refreshElasticSearch()
        return retval
    }

    private void refreshElasticSearch() {
        //ensure elastic search is finished updating before returning response
        elasticSearchAdminService.refresh(ServiceItem)
    }
}
