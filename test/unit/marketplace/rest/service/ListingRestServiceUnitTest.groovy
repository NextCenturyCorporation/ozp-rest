package marketplace.rest.service

import grails.test.mixin.Mock
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin
import marketplace.Contact
import marketplace.ContactType
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.codehaus.groovy.grails.commons.DefaultGrailsApplication

import org.springframework.security.access.AccessDeniedException

import marketplace.Category
import marketplace.Agency
import marketplace.ContactType
import marketplace.Listing
import marketplace.Type
import marketplace.DocUrl
import marketplace.Screenshot
import marketplace.Profile
import marketplace.ListingActivity
import marketplace.RejectionActivity
import marketplace.RejectionListing
import marketplace.Intent
import marketplace.Relationship
import marketplace.Constants
import marketplace.ApprovalStatus
import marketplace.ChangeDetail
import marketplace.validator.ListingValidator
import ozone.marketplace.enums.RelationshipType

import marketplace.rest.representation.in.ListingInputRepresentation
import marketplace.rest.representation.in.ProfilePropertyInputRepresentation
import marketplace.rest.representation.in.ResourceInputRepresentation
import marketplace.rest.representation.in.ScreenshotInputRepresentation
import marketplace.rest.representation.in.ContactInputRepresentation

import marketplace.testutil.FakeAuditTrailHelper
import marketplace.testutil.ProfileMappedByFix

@TestMixin(DomainClassUnitTestMixin)
@Mock([ContactType, Screenshot, Contact, DocUrl])
class ListingRestServiceUnitTest {

    GrailsApplication grailsApplication

    ListingRestService service

    Profile currentUser, owner, nonOwner, admin
    Type type1
    Agency agency1
    ContactType contactType1
    Category category1

    private static final exampleServiceItemProps = [
        height: 10,
        width: 10,
        title: "test service item",
        type: "Test Type",
        description: "a test service item",
        launchUrl: "https://localhost/asf",
        owners: [[ username: 'owner' ]],
        versionName: '1',
        isEnabled: true,
        approvalStatus: ApprovalStatus.IN_PROGRESS,
        docUrls: [[
            name: "doc 1",
            url: "https://localhost"
        ]],
        screenshots: [[
            smallImageUrl: "https://localhost",
            largeImageUrl: "https://localhost"
        ]],
        imageSmallUrl: "https://localhost/asdf",
        imageMediumUrl: "https://localhost/asdf",
        imageLargeUrl: "https://localhost/asdf",
        imageXlargeUrl: "https://localhost/asdf",
        whatIsNew: "nothin'",
        descriptionShort: "asdf",
        categories: ["Test Category"],
        agency: "Test Agency",
        requirements: "stuff",
        contacts: [[
            unsecurePhone: "555-555-5555",
            email: "me@you.com",
            name: "jim bob",
            type: "Test Contact Type"
        ]],
        tags: ["test tag"]
    ]

    private createGrailsApplication() {
        grailsApplication = new DefaultGrailsApplication()
        grailsApplication.refresh()

        //necessary to get reflection-based marshalling to work
        grailsApplication.addArtefact(DocUrl.class)
        grailsApplication.addArtefact(Screenshot.class)
        grailsApplication.addArtefact(Relationship.class)
        grailsApplication.addArtefact(Intent.class)
        grailsApplication.addArtefact(Listing.class)
        grailsApplication.addArtefact(Contact.class)
        grailsApplication.addArtefact(Profile.class)

        FakeAuditTrailHelper.install()
        ProfileMappedByFix.fixProfileMappedBy()
    }

    private makeServiceItem() {
        def exampleServiceItem = new Listing(exampleServiceItemProps + [
            owners: [owner],
            type: type1,
            agency: agency1,
            categories: [category1],
            docUrls: exampleServiceItemProps.docUrls.collect {
                new DocUrl(it)
            },
            screenshots: exampleServiceItemProps.screenshots.collect {
                new Screenshot(it)
            },
            contacts: exampleServiceItemProps.contacts.collect {
                new Contact(it + [
                    type: contactType1
                ])
            }
        ])
    }

    private makeServiceItemInputRepresentation() {
        def exampleServiceItem = new ListingInputRepresentation(exampleServiceItemProps + [
            owners: exampleServiceItemProps.owners.collect {
                new ProfilePropertyInputRepresentation(it)
            },
            docUrls: exampleServiceItemProps.docUrls.collect {
                new ResourceInputRepresentation(it)
            },
            screenshots: exampleServiceItemProps.screenshots.collect {
                new ScreenshotInputRepresentation(it)
            },
            contacts: exampleServiceItemProps.contacts.collect {
                new ContactInputRepresentation(it)
            }
        ])

        return exampleServiceItem
    }

    void setUp() {
        def owner = new Profile(username: 'owner')
        owner.id = 1
        def nonOwner = new Profile(username: 'nonOwner')
        nonOwner.id = 2
        def admin = new Profile(username: 'admin')
        admin.id = 3


        type1 = new Type(title: 'Test Type')
        type1.id = 1
        agency1 = new Agency(title: 'Test Agency', shortName: "TA")
        agency1.id = 1
        contactType1 = new ContactType(title: 'Test Contact Type')
        contactType1.id = 1
        category1 = new Category(title: 'Test Category')
        category1.id = 1

        def intent = new Intent(
            action: 'run',
            dataType: 'text/plain'
        )
        intent.id = 1

        createGrailsApplication()

        mockDomain(ChangeDetail.class)
        mockDomain(ListingActivity.class)
        mockDomain(Relationship.class)
        mockDomain(RejectionListing.class)

        mockDomain(Type.class, [type1])
        mockDomain(ContactType.class, [contactType1])
        mockDomain(Category.class, [category1])
        mockDomain(Agency.class, [agency1])
        mockDomain(Intent.class, [intent])
        mockDomain(Profile.class, [owner, nonOwner, admin])

        currentUser = admin
        this.admin = admin
        this.owner = owner
        this.nonOwner = nonOwner

        mockDomain(Listing.class)
        def exampleServiceItem = makeServiceItem()
        exampleServiceItem.save(failOnError:true)

        def serviceItemValidator = [
            validateNew: {},
            validateChanges: { a, b -> }
        ] as ListingValidator

        service = new ListingRestService(grailsApplication, serviceItemValidator)

        service.profileRestService = [
            getCurrentUserProfile: { currentUser },
            getById: { id -> Profile.get(id) },
            isAdmin: { currentUser.username.toLowerCase().contains('admin') },
            checkAdmin: {
                if (!currentUser.username.toLowerCase().contains('admin')) {
                    throw new AccessDeniedException('access denied')
                }
            }
        ] as ProfileRestService

        service.listingActivityInternalService = [
            addListingActivity: {si, action -> }
        ] as ListingActivityInternalService

        //dirty checking isn't mocked in unit tests, so we need to mock
        //the method that relies on it
        Listing.metaClass.modifiedForChangeLog = { false }
    }

    void testAuthorizeUpdate() {
        Listing si

        //this should work because the listing is in progress
        currentUser = Profile.findByUsername('owner')
        service.updateById(1, makeServiceItemInputRepresentation())

        //populate initial ServiceItem
        Listing original = makeServiceItem()
        service.populateDefaults(original)
        original.approvalStatus = ApprovalStatus.APPROVED
        def id = original.save(failOnError: true).id

        //this should succeed because owners can always edit their listings
        ListingInputRepresentation updates = makeServiceItemInputRepresentation()
        updates.approvalStatus = ApprovalStatus.APPROVED
        service.updateById(id, updates)

        //this should fail because an non-admin, non-owner user is trying to update
        shouldFail(AccessDeniedException) {
            si = Listing.get(id)
            si.approvalStatus = ApprovalStatus.IN_PROGRESS
            si.save(failOnError: true)
            currentUser = nonOwner
            service.updateById(id, makeServiceItemInputRepresentation())
        }

        //this should succeed because admins can always edit
        currentUser = admin
        service.updateById(id, makeServiceItemInputRepresentation())
    }

    void testAuthorizeCreate() {

        //dirty checking isn't mocked in unit tests, so we need to mock
        //the method that relies on it
        Listing.metaClass.modifiedForChangeLog = { false }

        //ensure that normal users can create
        currentUser = Profile.findByUsername('nonOwner')
        service.createFromRepresentation(makeServiceItemInputRepresentation())

        //ensure that admins can create
        currentUser = Profile.findByUsername('admin')
        Listing dto = service.createFromRepresentation(makeServiceItemInputRepresentation())

        assertNotNull dto
    }

    void testApprove() {
        ListingActivity activity
        ListingInputRepresentation dto
        Listing listing
        def id

        service.listingActivityInternalService = [
            addListingActivity: { si, action ->
                //creation of the changelog uses the other signature
                if(action instanceof ListingActivity)
                    return
                activity = new ListingActivity(action: action, listing: si)
            }
        ] as ListingActivityInternalService

        def approve = {
            dto = makeServiceItemInputRepresentation()
            dto.approvalStatus = ApprovalStatus.APPROVED
            listing = service.updateById(id, dto)
        }

        dto = makeServiceItemInputRepresentation()

        id = service.createFromRepresentation(dto).id
        dto = makeServiceItemInputRepresentation()
        dto.approvalStatus = ApprovalStatus.PENDING
        listing = service.updateById(id, dto)

        //users cannot approve
        shouldFail(AccessDeniedException) {
            currentUser = this.owner
            approve()
        }

        currentUser = admin

        //need to reset the approval status because the unit tests aren't transactional and
        //the preceding failed change did not get rolled back
        Listing.get(id).approvalStatus = ApprovalStatus.PENDING
        approve()

        assert activity.action == Constants.Action.APPROVED

        //make sure it was approved within the last second
        assert listing.approvedDate.time > (new Date()).time - 1000
    }

    void testReject() {
        Listing created
        ListingActivity activity

        service.listingActivityInternalService = [
            addRejectionActivity: { si, rejectionListing ->
                activity = new RejectionActivity(
                    serviceItem: si,
                    rejectionListing: rejectionListing
                )
            },
            addListingActivity: { si, action -> }
        ] as ListingActivityInternalService

        def reject = {
            service.reject(created, new RejectionListing(
                description: 'bad listing'
            ))
        }

        def id = service.createFromRepresentation(makeServiceItemInputRepresentation()).id

        //make a fresh dto
        created = makeServiceItem()
        service.populateDefaults(created)
        created.id = id
        created.approvalStatus = ApprovalStatus.PENDING
        created.save(failOnError:true)

        shouldFail(AccessDeniedException) {
            currentUser = this.owner
            reject()
        }

        currentUser = admin
        reject()

        assert created.approvalStatus == ApprovalStatus.REJECTED
        assert activity.rejectionListing.description == 'bad listing'
        assert activity instanceof RejectionActivity
        assert created.rejectionListings == [activity.rejectionListing] as SortedSet
    }

    void testPopulateDefaults() {
        service.profileRestService = [
            isAdmin: { currentUser.username.toLowerCase().contains('admin') },
            checkAdmin: {
                if (!currentUser.username.toLowerCase().contains('admin')) {
                    throw new AccessDeniedException('access denied')
                }
            },
            getCurrentUserProfile: {
                currentUser
            }
        ] as ProfileRestService

        //create a dto with no defaults filled in
        ListingInputRepresentation dto = makeServiceItemInputRepresentation()
        dto.owners = null


        Listing created = service.createFromRepresentation(dto)

        assert created.owners == [currentUser] as Set

        //create a dto with defaults filled in and ensure that ey are preserved
        dto = makeServiceItemInputRepresentation()
        dto.owners = [Profile.findByUsername('nonOwner')]

        created = service.createFromRepresentation(dto)

        assert created.owners == [Profile.findByUsername('nonOwner')] as Set
    }

    void testUpdateHiddenServiceItemActivity() {
        //isEnabled = true is the default
        def id = service.createFromRepresentation(makeServiceItemInputRepresentation()).id
        def activity

        service.listingActivityInternalService = [
            addListingActivity: { si, action ->
                activity = new ListingActivity(action: action, listing: si)
            }
        ] as ListingActivityInternalService

        ListingInputRepresentation dto = makeServiceItemInputRepresentation()
        Listing listing
        dto.isEnabled = false

        listing = service.updateById(id, dto)
        assertNotNull activity
        assert activity.action == Constants.Action.DISABLED


        dto = makeServiceItemInputRepresentation()
        dto.isEnabled = true

        listing = service.updateById(id, dto)
        assertNotNull activity
        assert activity.action == Constants.Action.ENABLED
    }

    //re-activate this test once the ListingInputRepresentation
    //supports required listings
    @Ignore
    void testUpdateRelationshipServiceItemActivity() {
        def makeRelationship = { related=null ->
            new Relationship(
                relationshipType: RelationshipType.REQUIRE,
                relatedItems: related ?: []
            )
        }

        currentUser = Profile.findByUsername('admin')

        def id = service.createFromRepresentation(makeServiceItemInputRepresentation()).id
        def parent, added, removed
        def relationship = makeRelationship()
        def relatedItem1 = service.createFromRepresentation(makeServiceItemInputRepresentation())
        def relatedItem2 = service.createFromRepresentation(makeServiceItemInputRepresentation())

        service.listingActivityInternalService = [
            addListingActivity: { si, action -> },
            addRelationshipActivities: { p, a, r ->
                parent = p
                added = a
                removed = r
            }
        ] as ListingActivityInternalService

        Listing dto = makeServiceItemInputRepresentation()
        dto.id = id
        dto.relationships = [makeRelationship([relatedItem1, relatedItem2])]
        service.updateById(id, dto)

        assert parent.id == dto.id
        assert added.collect { it.id } as Set == [relatedItem1.id, relatedItem2.id] as Set
        assert removed as Set == [] as Set

        dto.relationships = [makeRelationship([relatedItem1])]
        service.updateById(id, dto)

        assert added as Set == [] as Set
        assert removed.collect {it.id} as Set == [relatedItem2.id] as Set

        dto.relationships = [makeRelationship([relatedItem2])]
        service.updateById(id, dto)

        assert added.collect {it.id} as Set == [relatedItem2.id] as Set
        assert removed.collect {it.id} as Set == [relatedItem1.id] as Set

        dto.relationships = [makeRelationship([relatedItem2])]
        service.updateById(id, dto)

        assert added as Set == [] as Set
        assert removed as Set == [] as Set
    }

    //re-activate this test once the ListingInputRepresentation
    //supports required listings
    @Ignore
    void testGetAllRequiredServiceItemsByParentId() {
        def makeRelationship = { related=null ->
            new Relationship(
                relationshipType: RelationshipType.REQUIRE,
                relatedItems: related ?: []
            )
        }

        def id = service.createFromRepresentation(makeServiceItemInputRepresentation()).id

        def getRequired = {
            service.getAllRequiredListingsByParentId(id)
                .collect {it.id} as Set
        }

        def relationship = makeRelationship()
        def relatedItem1 = service.createFromRepresentation(makeServiceItemInputRepresentation())
        def relatedItem2 = makeServiceItemInputRepresentation()
        relatedItem2 = service.createFromRepresentation(relatedItem2)

        service.listingActivityInternalService = [
            addListingActivity: { si, action -> },
            addRelationshipActivities: { p, a, r -> }
        ] as ListingActivityInternalService

        Listing dto = makeServiceItemInputRepresentation()
        dto.id = id
        dto.relationships = [makeRelationship([relatedItem1, relatedItem2])]
        service.updateById(id, dto)

        assert getRequired() == [relatedItem1.id, relatedItem2.id] as Set

        dto.relationships = [makeRelationship([relatedItem1])]
        service.updateById(id, dto)

        assert getRequired() == [relatedItem1.id] as Set

        dto.relationships = [makeRelationship([relatedItem2])]
        service.updateById(id, dto)

        assert getRequired() == [relatedItem2.id] as Set

        dto.relationships = [makeRelationship()]
        service.updateById(id, dto)

        assert getRequired() == [] as Set
    }

    //re-activate this test once the ListingInputRepresentation
    //supports required listings
    @Ignore
    void testGetAllRequiredServiceItemsByParentIdCyclicDependency() {
        def makeRelationship = { ids=null ->
            new Relationship(
                relationshipType: RelationshipType.REQUIRE,
                relatedItems: ids.collect {
                    def si = new Listing()
                    si.id = it
                    si
                } ?: []
            )
        }

        def id

        def getRequired = {
            service.getAllRequiredListingsByParentId(id)
                .collect {it.id} as Set
        }

        service.listingActivityInternalService = [
            addListingActivity: { si, action -> },
            addRelationshipActivities: { p, a, r -> }
        ] as ListingActivityInternalService

        def relatedItem1Id = service.createFromRepresentation(makeServiceItemInputRepresentation()).id
        def relatedItem2Id = service.createFromRepresentation(makeServiceItemInputRepresentation()).id

        def relatedItem1Dto = makeServiceItemInputRepresentation()
        relatedItem1Dto.id = relatedItem1Id
        relatedItem1Dto.relationships = [makeRelationship([relatedItem2Id])]

        def relatedItem2Dto = makeServiceItemInputRepresentation()
        relatedItem2Dto.id = relatedItem2Id
        relatedItem2Dto.relationships = [makeRelationship([relatedItem1Id])]

        def relatedItem1 = service.updateById(relatedItem1Id, relatedItem1Dto)
        def relatedItem2 = service.updateById(relatedItem2Id, relatedItem2Dto)

        def dto = makeServiceItemInputRepresentation()
        dto.relationships = [makeRelationship([relatedItem1Id])]
        id = service.createFromRepresentation(dto).id

        assert getRequired() == [relatedItem1.id, relatedItem2.id] as Set
    }

    void testGetAllByAuthorId() {
        Listing.metaClass.static.findAllByAuthor = { Profile a ->
            Listing.list().grep { it.owners.contains(a) }
        }

        Listing existing = Listing.get(1)
        //create a serviceitem that is approved. The default one is not
        Listing approved = makeServiceItem()
        approved.approvalStatus = ApprovalStatus.APPROVED
        approved.save(failOnError: true)

        def author = Profile.findByUsername('owner')
        def authorId = author.id

        currentUser = Profile.findByUsername('nonOwner')

        def serviceItems = service.getAllByAuthorId(authorId)

        assert serviceItems.size() == 1
        assert serviceItems == [approved] as Set
    }
}
