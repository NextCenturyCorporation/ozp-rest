package marketplace.rest.representation.in

import com.sun.jersey.multipart.FormDataBodyPart

import marketplace.Contact
import marketplace.Screenshot
import marketplace.Listing
import marketplace.DocUrl
import marketplace.ApprovalStatus
import marketplace.ImageReference

class ListingInputRepresentation extends AbstractInputRepresentation<Listing> {
    public static final String MEDIA_TYPE = 'application/vnd.ozp-listing-v1+json'

    ListingInputRepresentation() {
        super(Listing.class)
    }

    String title
    String launchUrl
    String requirements
    String descriptionShort
    String description
    UUID featuredBannerIconId
    UUID bannerIconId
    UUID largeIconId
    UUID smallIconId
    String versionName
    String whatIsNew
    Integer width
    Integer height
    Boolean singleton = false
    Boolean isFeatured = false
    Boolean isEnabled = true
    Set<String> tags = new HashSet()
    ApprovalStatus approvalStatus = ApprovalStatus.IN_PROGRESS
    TypeTitleInputRepresentation type
    Set<IntentPropertyRefInputRepresentation> intents = new HashSet()
    Set<ContactInputRepresentation> contacts = new HashSet()
    Set<ProfilePropertyInputRepresentation> owners = new HashSet()
    Set<CategoryTitleInputRepresentation> categories = new HashSet()
    AgencyTitleInputRepresentation agency
    Set<ResourceInputRepresentation> docUrls = new HashSet()
    Set<ListingIdRef> required = new HashSet()
    List<ScreenshotInputRepresentation> screenshots = []

    public void setType(String typeTitle) {
        this.type = new TypeTitleInputRepresentation(typeTitle)
    }

    public void setAgency(String agencyTitle) {
        this.agency = agencyTitle ? new AgencyTitleInputRepresentation(agencyTitle) : null;
    }

    public void setCategories(Collection<String> categoryTitles) {
        this.categories = categoryTitles.collect { new CategoryTitleInputRepresentation(it) }
    }

    public void setIntents(Collection<String> intents) {
        this.intents = intents.collect { new IntentPropertyRefInputRepresentation(it) }
    }
}

class ResourceInputRepresentation extends AbstractInputRepresentation<DocUrl> {
    ResourceInputRepresentation() {
        super(DocUrl.class)
    }

    String name
    String url
}

class ScreenshotInputRepresentation extends AbstractInputRepresentation<Screenshot> {
    ScreenshotInputRepresentation() {
        super(Screenshot.class)
    }

    UUID smallImageId
    UUID largeImageId
}

class ContactInputRepresentation extends AbstractInputRepresentation<Contact> {
    ContactInputRepresentation() {
        super(Contact.class)
    }

    String email
    String securePhone
    String unsecurePhone
    String organization
    String name
    ContactTypeTitleInputRepresentation type

    public void setType(String typeTitle) {
        this.type = new ContactTypeTitleInputRepresentation(typeTitle)
    }
}
