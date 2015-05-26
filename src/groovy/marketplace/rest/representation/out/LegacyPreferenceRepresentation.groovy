package marketplace.rest.representation.out

import org.springframework.stereotype.Component

import marketplace.hal.ApplicationRootUriBuilderHolder
import marketplace.hal.AbstractHalRepresentation
import marketplace.hal.RepresentationFactory

import marketplace.rest.LegacyPreference
import marketplace.Profile

class LegacyPreferenceRepresentation extends AbstractHalRepresentation<LegacyPreference> {
    private LegacyPreference legacyPreference

    private LegacyPreferenceRepresentation(LegacyPreference legacyPreference) {
        this.legacyPreference = legacyPreference
    }

    public String getNamespace() { legacyPreference.namespace }
    public String getPath() { legacyPreference.name }
    public String getValue() { legacyPreference.value }
    public UserJson getUser() { new UserJson(legacyPreference.user) }
    public int getId() { legacyPreference.id}
    
    @Component
    public static class Factory implements RepresentationFactory<LegacyPreference> {
        AbstractHalRepresentation<LegacyPreference> toRepresentation(
                LegacyPreference legacyPreference,
                ApplicationRootUriBuilderHolder uriBuilderHolder) {
            new LegacyPreferenceRepresentation(legacyPreference)
        }
    }

    public static class UserJson {
        private Profile user

        UserJson(Profile user) {
            this.user = user
        }

        public String getUserId() { user.username }
    }
}
