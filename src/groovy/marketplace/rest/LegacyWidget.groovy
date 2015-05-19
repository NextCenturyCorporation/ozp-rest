package marketplace.rest

import marketplace.Listing
import marketplace.Type

class LegacyWidget {

	Boolean visible = true
	Boolean minimized = false
	Boolean maximized = false
	Boolean	definitionVisible = true
	Boolean singleton
	Boolean background = false
	String userId
	String namespace
	String url
	String uuid
	String id
	UUID headerIcon
	UUID image
	UUID smallIcon
	UUID largeIcon
	String widgetVersion
	Number width
	Number height
	Number x = 0
	Number y = 0
	Collection<String> tags
	List<LegacyWidget> allRequired = new ArrayList<LegacyWidget>()
	List<LegacyWidget> directRequired = new ArrayList<LegacyWidget>()
	List<String> widgetTypes = new ArrayList<String>()

	String universalName = null
	String description = null
	Number totalUsers = 0
	Number totalGroups = 0
	String descriptorUrl = null
	List<String> intents = new ArrayList<String>()


	LegacyWidget(Listing listing) {
		this.id = listing.uuid
		this.singleton = listing.singleton
		this.userId = listing.owners[0].id
		this.namespace = listing.title
		this.url = listing.launchUrl
		this.uuid = listing.uuid
		this.headerIcon = listing.bannerIconId
		this.smallIcon = listing.smallIconId
		this.largeIcon = listing.largeIconId
		this.image = listing.largeIconId
		this.widgetVersion = listing.versionName
		this.width = listing.width
		this.height = listing.height

		// Take a Set<Tag> and create a Set<String>
		this.tags = listing.tags

		//this.widgetTypes.add(listing.type.toString())

		listing.required.each { item ->
			LegacyWidget req = new LegacyWidget(item)
			allRequired.add(req)
			directRequired.add(req)
		}

	}
}
