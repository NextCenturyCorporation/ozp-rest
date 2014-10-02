package marketplace.testutil

import marketplace.Profile
import marketplace.Relationship
import marketplace.Listing
import ozone.utils.ApplicationContextHolder

class IntegrationDomainHelper {
    public static void deleteGrailsDomainClasses() {

        Relationship.getAll()*.delete(flush: true)
        Listing.getAll()*.delete(flush: true)

        def domainClasses = ApplicationContextHolder.grailsApplication.domainClasses*.clazz

        domainClasses.each { domainClass ->
            if(domainClass.name in ['org.ozoneplatform.appconfig.server.domain.model.ApplicationConfiguration', 'marketplace.Profile'])
                return

            try {
                domainClass.getAll()*.delete(flush: true, failOnError: true)
            } catch(Exception e) {
                println e.message
            }

        }

        Profile.getAll()*.delete()
    }
}
