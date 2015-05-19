package marketplace.rest.representation.out

import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Autowired

import com.fasterxml.jackson.annotation.JsonValue

import marketplace.rest.LegacyWidget

import marketplace.hal.ApplicationRootUriBuilderHolder
import marketplace.hal.AbstractHalRepresentation
import marketplace.hal.RepresentationFactory

import marketplace.rest.ChildObjectCollection


class LegacyWidgetCollectionRepresentation extends
        AbstractHalRepresentation<Collection<LegacyWidget>> {


    private List<LegacyWidget> list
    private RepresentationFactory<LegacyWidget> widgetFactory
    private ApplicationRootUriBuilderHolder uriBuilderHolder

    private LegacyWidgetCollectionRepresentation(
            Collection<LegacyWidget> list,
            RepresentationFactory<LegacyWidget> widgetFactory,
            ApplicationRootUriBuilderHolder uriBuilderHolder) {
        this.list = list as List
        this.widgetFactory = widgetFactory
        this.uriBuilderHolder = uriBuilderHolder
    }

    public Number getStatus() { 200 }
    public ListJson getData() { new ListJson(this.list, this.widgetFactory, this.uriBuilderHolder) }

    @Component
    public static class Factory implements
            RepresentationFactory<Collection<LegacyWidget>> {
        @Autowired LegacyWidgetRepresentation.Factory widgetFactory

        @Override
        LegacyWidgetCollectionRepresentation toRepresentation(
                Collection<LegacyWidget>  list,
                ApplicationRootUriBuilderHolder uriBuilderHolder) {

            new LegacyWidgetCollectionRepresentation(list, widgetFactory, uriBuilderHolder)
        }
    }

    public static class ListJson {

        private List<LegacyWidget> list
        private RepresentationFactory<LegacyWidget> widgetFactory
        private ApplicationRootUriBuilderHolder uriBuilderHolder

        ListJson(List<LegacyWidget> list, RepresentationFactory<LegacyWidget> widgetFactory, 
            ApplicationRootUriBuilderHolder uriBuilderHolder) {
            this.list = list
            this.widgetFactory = widgetFactory
            this.uriBuilderHolder = uriBuilderHolder
        }
            
        @JsonValue
        public List<LegacyWidgetCollectionRepresentation> asJsonList() {
            list.collect {
                widgetFactory.toRepresentation(it, uriBuilderHolder)
            }
        }

    }
}

