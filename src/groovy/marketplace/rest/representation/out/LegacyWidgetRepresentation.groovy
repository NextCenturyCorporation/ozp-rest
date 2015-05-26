package marketplace.rest.representation.out

import org.springframework.stereotype.Component

import marketplace.hal.ApplicationRootUriBuilderHolder
import marketplace.hal.AbstractHalRepresentation
import marketplace.hal.RepresentationFactory
import org.springframework.beans.factory.annotation.Autowired
import marketplace.rest.resource.uribuilder.ImageReferenceUriBuilder

import marketplace.rest.LegacyWidget
import marketplace.Type

class LegacyWidgetRepresentation extends AbstractHalRepresentation<LegacyWidget> {
    private LegacyWidget legacyWidget
    private ImageReferenceUriBuilder imageUriBuilder


    private LegacyWidgetRepresentation(LegacyWidget legacyWidget, ImageReferenceUriBuilder imageUriBuilder) {
        this.legacyWidget = legacyWidget
        this.imageUriBuilder = imageUriBuilder
    }

    public WidgetJson getValue() { new WidgetJson(legacyWidget, imageUriBuilder) }
    public String getId() { legacyWidget.id }
    public String getNamespace() { "widget" }
    public String getPath() {legacyWidget.uuid }


    @Component
    public static class Factory implements RepresentationFactory<LegacyWidget> {
        @Autowired ImageReferenceUriBuilder.Factory imageUriBuilderFactory

        AbstractHalRepresentation<LegacyWidget> toRepresentation(
                LegacyWidget legacyWidget,
                ApplicationRootUriBuilderHolder uriBuilderHolder) {
            new LegacyWidgetRepresentation(legacyWidget,
                imageUriBuilderFactory.getBuilder(uriBuilderHolder))
        }
    }

    public static class WidgetJson {
        private LegacyWidget legacyWidget
        private ImageReferenceUriBuilder imageUriBuilder

        WidgetJson(LegacyWidget legacyWidget, ImageReferenceUriBuilder imageUriBuilder) {
            this.legacyWidget = legacyWidget
            this.imageUriBuilder = imageUriBuilder
        }

        public String getNamespace() { legacyWidget.namespace }
        public String getUserid() { legacyWidget.userId }
        public Boolean getVisible() { legacyWidget.visible }
        public Boolean getMinimized() { legacyWidget.minimized }
        public Boolean getMaximized() { legacyWidget.maximized }
        public Boolean getDefinitionVisible() { legacyWidget.definitionVisible }
        public Boolean getSingleton() { legacyWidget.singleton }
        public Boolean getBackground() { legacyWidget.background }
        public String getUrl() { legacyWidget.url }
        public String getWidgetVersion() { legacyWidget.widgetVersion }
        public Number getWidth() { legacyWidget.width }
        public Number getHeight() { legacyWidget.height }
        public Number getX() { legacyWidget.x }
        public Number getY() { legacyWidget.y }
        
        public String getUniversalName() {legacyWidget.universalName}
        public String getDescription() {legacyWidget.description}
        public Number getTotalUsers() {legacyWidget.totalUsers}
        public Number getTotalGroups() {legacyWidget.totalGroups}
        public String getDescriptorUrl() {legacyWidget.descriptorUrl}
        public List<String> getIntents() {legacyWidget.intents}

        public URI getHeaderIcon() {
            legacyWidget.headerIcon ? imageUriBuilder.getImageUri(legacyWidget.headerIcon) : null
        }
        public URI getImage() {
            legacyWidget.image ? imageUriBuilder.getImageUri(legacyWidget.image) : null
        }
        public URI getSmallIconUrl() {
            legacyWidget.smallIcon ? imageUriBuilder.getImageUri(legacyWidget.smallIcon) : null
        }
        public URI getLargeIconUrl() {
            legacyWidget.largeIcon ? imageUriBuilder.getImageUri(legacyWidget.largeIcon) : null
        }

        public List<String> getWidgetTypes() { legacyWidget.widgetTypes }
        public Collection<LegacyWidget> getAllRequired() { legacyWidget.allRequired }
        public Collection<LegacyWidget> getDirectRequired() { legacyWidget.directRequired }
        public Collection<String> getTags() { legacyWidget.tags }
    }
}
