package marketplace.rest.representation.out

import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Autowired

import com.fasterxml.jackson.annotation.JsonValue

import marketplace.rest.LegacyPreference

import marketplace.hal.ApplicationRootUriBuilderHolder
import marketplace.hal.AbstractHalRepresentation
import marketplace.hal.RepresentationFactory

import marketplace.rest.ChildObjectCollection


class LegacyPreferenceCollectionRepresentation extends
        AbstractHalRepresentation<Collection<LegacyPreference>> {

    // public static final String MEDIA_TYPE = 'application/vnd.ozp-library-simple-v1+json'

    private List<LegacyPreference> list
    private RepresentationFactory<LegacyPreference> preferenceFactory
    private ApplicationRootUriBuilderHolder uriBuilderHolder

    private LegacyPreferenceCollectionRepresentation(
            Collection<LegacyPreference> list,
            RepresentationFactory<LegacyPreference> preferenceFactory,
            ApplicationRootUriBuilderHolder uriBuilderHolder) {
        this.list = list as List
        this.preferenceFactory = preferenceFactory
        this.uriBuilderHolder = uriBuilderHolder
    }

    @JsonValue
    public List<LegacyPreferenceCollectionRepresentation> asJsonList() {
        list.collect {
            preferenceFactory.toRepresentation(it, uriBuilderHolder)
        }
    }


    @Component
    public static class Factory implements
            RepresentationFactory<Collection<LegacyPreference>> {
        @Autowired LegacyPreferenceRepresentation.Factory preferenceFactory

        @Override
        LegacyPreferenceCollectionRepresentation toRepresentation(
                Collection<LegacyPreference>  list,
                ApplicationRootUriBuilderHolder uriBuilderHolder) {

            new LegacyPreferenceCollectionRepresentation(list, preferenceFactory, uriBuilderHolder)
        }
    }
}

