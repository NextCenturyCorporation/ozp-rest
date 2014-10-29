package marketplace.rest.resource.uribuilder

import org.springframework.stereotype.Component

import marketplace.hal.ApplicationRootUriBuilderHolder

import marketplace.rest.resource.IwcResource

import marketplace.rest.IwcApi

//TODO is this class redundant with IwcUriBuilder
class IwcResourceUriBuilder implements CollectionUriBuilder<IwcApi> {
    private ApplicationRootUriBuilderHolder uriBuilderHolder

    private IwcResourceUriBuilder(ApplicationRootUriBuilderHolder uriBuilderHolder) {
        this.uriBuilderHolder = uriBuilderHolder
    }

    URI getCollectionUri() {
        uriBuilderHolder.builder
            .path(IwcResource.class)
            .build()
    }

    @Component
    public static class Factory implements CollectionUriBuilder.Factory {
        IwcResourceUriBuilder getBuilder(ApplicationRootUriBuilderHolder uriBuilderHolder) {
            new IwcResourceUriBuilder(uriBuilderHolder)
        }
    }
}
