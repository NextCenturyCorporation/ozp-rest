package marketplace.rest.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import org.codehaus.groovy.grails.commons.GrailsApplication
import marketplace.Category
import marketplace.Constants
import marketplace.Sorter


@Service
class CategoryRestService extends AdminRestService<Category> {
    @Autowired
    public CategoryRestService(GrailsApplication grailsApplication) {
        super(grailsApplication, Category.class,
              null, new Sorter(Constants.SortDirection.ASC, 'title'))
    }

    CategoryRestService() {}
}
