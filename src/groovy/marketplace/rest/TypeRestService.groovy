package marketplace.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import org.codehaus.groovy.grails.commons.GrailsApplication

import marketplace.Types

@Service
class TypeRestService extends AdminRestService<Types> {
    @Autowired
    public TypeRestService(GrailsApplication grailsApplication) {
        super(grailsApplication, Types.class, null, null)
    }

    TypeRestService() {}
}
