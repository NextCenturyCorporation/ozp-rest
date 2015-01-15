package marketplace.rest.writer

import org.codehaus.groovy.grails.commons.GrailsApplication

import marketplace.Listing

import marketplace.rest.RequiredListingCollection

import marketplace.hal.AbstractRepresentationWriter
import marketplace.rest.representation.out.ListingRepresentation
import marketplace.rest.resource.uribuilder.RequiredListingCollectionUriBuilder
import marketplace.rest.representation.out.EmbeddedChildCollectionRepresentation
import marketplace.rest.resource.uribuilder.ListingUriBuilder
import org.springframework.beans.factory.annotation.Autowired

import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.ext.Provider

@Provider
@Produces([
      MediaType.APPLICATION_JSON,
      ListingRepresentation.COLLECTION_MEDIA_TYPE
])
class RequiredListingCollectionRepresentationWriter extends AbstractRepresentationWriter<RequiredListingCollection> {

    @Autowired
    RequiredListingCollectionRepresentationWriter(GrailsApplication grailsApplication,

          ListingRepresentation.Factory factory,
          RequiredListingCollectionUriBuilder.Factory collectionUriBuilderFactory,
          ListingUriBuilder.Factory parentUriBuilderFactory) {
            super(grailsApplication, EmbeddedChildCollectionRepresentation.createFactory(factory,
              collectionUriBuilderFactory, parentUriBuilderFactory))
      }
}
