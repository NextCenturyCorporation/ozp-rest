package marketplace.rest

import marketplace.Profile
import marketplace.IwcDataObject

class LegacyPreference {
    String namespace, name, value
    Profile user
    int id 

    LegacyPreference(String namespace, String name, String value, Profile user, int id) {
        this.namespace = namespace
        this.name = name
        this.value = value
        this.user = user
        this.id = id
    }   

    LegacyPreference(IwcDataObject object) {
        String keyBreaker = "" + (char) 0x1E
        int breakIndex = object.key.indexOf(keyBreaker)
        this.namespace = object.key.substring(0,breakIndex)
        this.name = object.key.substring(breakIndex)

        this.value = object.entity
        this.user = object.profile
        this.id = object.id
    }
}
