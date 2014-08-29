package marketplace

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.text.ParseException
import org.codehaus.groovy.grails.web.json.JSONArray
import org.codehaus.groovy.grails.web.json.JSONObject
import org.apache.commons.lang.builder.HashCodeBuilder
import org.apache.commons.lang.builder.EqualsBuilder
import static marketplace.ValidationUtil.validateUrl
import ozone.utils.Utils
import gorm.AuditStamp

@AuditStamp
class ServiceItem implements Serializable {

    public static final String RELEASE_DATE_FORMAT_STRING= 'MM/dd/yyyy'
    private final DateFormat RELEASE_DATE_FORMAT =
        new SimpleDateFormat(RELEASE_DATE_FORMAT_STRING)

    //these two fields are used by the RestService to determine
    //how to handle marshalling of this domain
    final static bindableProperties = [
        'types', 'owners',
        'categories', 'state',
        'owfProperties', 'customFields',
        'approvalStatus', 'releaseDate',
        'agency', 'title', 'whatIsNew',
        'description', 'requirements',
        'dependencies', 'contacts',
        'versionName', 'imageLargeUrl',
        'imageSmallUrl', 'imageMediumUrl', 'installUrl',
        'launchUrl', 'docUrls', 'descriptionShort',
        'isOutside', 'screenshots',
        'isEnabled', 'techPocs', 'tags',
        'organization', 'relationships',
        'isHidden', 'recommendedLayouts',
        'opensInNewBrowserTab', 'satisfiedScoreCardItems'
    ]

    final static modifiableReferenceProperties = [
        'owfProperties', 'docUrls',
        'customFields', 'screenshots',
        'relationships', 'contacts'
    ]

    static searchable = {
        types component: true
        owners component: true
        categories component: true
        state component: true
        owfProperties component: true
        itemComments component: true
        customFields component: true
        lastActivityDate index: 'not_analyzed', excludeFromAll: true
        approvedDate index: 'not_analyzed', excludeFromAll: true
        // Yes we need this much precision unless you want to see rounding errors between the short and detailed view
        avgRate index: 'not_analyzed', excludeFromAll: true
        totalRate5 index: 'not_analyzed', excludeFromAll: true
        totalRate4 index: 'not_analyzed', excludeFromAll: true
        totalRate3 index: 'not_analyzed', excludeFromAll: true
        totalRate2 index: 'not_analyzed', excludeFromAll: true
        totalRate1 index: 'not_analyzed', excludeFromAll: true
        totalVotes index: 'not_analyzed', excludeFromAll: true
        approvalStatus index: 'not_analyzed', excludeFromAll: false
        releaseDate index: 'not_analyzed', excludeFromAll: true
        agency component: true
        title boost: 2.0
        sortTitle index: 'not_analyzed'
        description boost: 1.9
        requirements boost: 1.8
        dependencies boost: 1.7
        versionName index: 'not_analyzed', excludeFromAll: true
        totalComments index: 'not_analyzed', excludeFromAll: true
        imageSmallUrl index: 'not_analyzed', excludeFromAll: true
        imageMediumUrl index: 'not_analyzed', excludeFromAll: true
        imageLargeUrl index: 'not_analyzed', excludeFromAll: true
        installUrl index: 'not_analyzed', excludeFromAll: true
        launchUrl index: 'not_analyzed', excludeFromAll: true
        docUrls component: true, excludeFromAll: true
        uuid index: 'not_analyzed', excludeFromAll: false
        screenshots component: true, excludeFromAll: true
        contacts component: true, excludeFromAll: true
        isHidden index: 'not_analyzed', excludeFromAll: false
        isOutside index: 'not_analyzed', excludeFromAll: false
        only = [
            'state', 'categories', 'owners', 'types', 'id', 'owfProperties',
            'screenshots', 'releaseDate', 'approvedDate', 'lastActivityDate',
            'customFields', 'itemComments', 'contacts', 'totalRate1', 'totalRate2',
            'totalRate3', 'totalRate4', 'totalRate5', 'totalVotes', 'avgRate',
            'description', 'requirements', 'dependencies', 'versionName', 'sortTitle',
            'title', 'agency', 'docUrls', 'uuid', 'launchUrl', 'installUrl',
            'imageLargeUrl', 'imageMediumUrl', 'imageSmallUrl', 'approvalStatus',
            'editedDate', 'isHidden', 'isOutside', 'tags', 'descriptionShort', 'whatIsNew'
        ]
    }

    // Specifies that changes to serviceItems will be written to the database as ChangeDetail
    // records and which fields to ignore.
    static auditable = [ignore:[
        'version',
        'lastUpdated',
        'editedBy',
        'editedDate',
        'totalVotes',
        'avgRate',
        'totalRate5',
        'totalRate4',
        'totalRate3',
        'totalRate2',
        'totalRate1',
        'itemComments',
        'totalComments',
        'lastActivity',
        'isHidden',
        'rejectionListings',
        'serviceItemActivities',

        //these fields are technically auditable, but are associated with a separate activity
        'relationships',
        'isEnabled',
        'approvalStatus',
        'isOutside',

        //the following fields are audited as a special case in ServiceItemRestService
        'customFields',
        'owfProperties'
    ]]

    Date releaseDate
    Date approvedDate
    String title
    String description
    String launchUrl
    List<Profile> owners
    String installUrl
    String versionName
    // AML-1128 isOutside null initially, rather than false
    Boolean isOutside

    /** Hidden: administrator can unhide, no one else can see **/
    //TODO why is this an Integer?
    Integer isHidden = 0

    String requirements
    String dependencies
    String organization

    Agency agency

    Float avgRate = 0F
    Integer totalVotes = 0
    Integer totalRate5 = 0
    Integer totalRate4 = 0
    Integer totalRate3 = 0
    Integer totalRate2 = 0
    Integer totalRate1 = 0
    String uuid = Utils.generateUUID()
    String imageSmallUrl
    String imageMediumUrl
    String imageLargeUrl
    String whatIsNew
    String descriptionShort
    Boolean opensInNewBrowserTab = false
    String approvalStatus = Constants.APPROVAL_STATUSES['APPROVED']

    String toString() {
        return "${id}:${title}:${uuid}:${releaseDate}:${approvalStatus}"
    }

    String prettyPrint() {
        return "${id}:${title}:${uuid}:${releaseDate}:${approvalStatus}:${types}:${categories}:${customFields}"
    }

    State state
    Types types
    OwfProperties owfProperties
    List customFields
    Set itemComments
    Integer totalComments = 0
    List categories
    List screenshots = []
    SortedSet rejectionListings
    List serviceItemActivities
    ServiceItemActivity lastActivity


    static transients = ['sortTitle', 'lastActivityDate', 'isEnabled']

    static hasMany = [
        categories: Category,
        owners: Profile,
        recommendedLayouts: RecommendedLayout,
        itemComments: ItemComment,
        customFields: CustomField,
        rejectionListings: RejectionListing,
        serviceItemActivities: ServiceItemActivity,
        docUrls: ServiceItemDocumentationUrl,
        screenshots: Screenshot,
        techPocs: String,
        relationships: Relationship,
        contacts: Contact,
        satisfiedScoreCardItems: ScoreCardItem,
        tags: String
    ]

    //so that GORM knows which property of the relationship is the backref
    static mappedBy = [relationships: 'owningEntity']

    static mapping = {
        cache true
        tablePerHierarchy false
        recommendedLayouts joinTable: 'si_recommended_layouts'
        recommendedLayouts batchSize: 50
        categories batchSize: 50
        customFields batchSize: 50
        serviceItemActivities batchSize: 50
        itemComments batchSize: 50
        itemComments cascade: "all-delete-orphan"
        rejectionListings batchSize: 50
        categories index: 'svc_item_cat_id_idx'
        customFields index:'svc_item_cst_fld_id_idx', cascade: 'all-delete-orphan'
        techPocs joinTable: [
            table: 'service_item_tech_pocs',
            column: 'tech_poc'
        ]
        screenshots indexColumn: [name: "ordinal", type: Integer], cascade: 'all-delete-orphan'
        contacts cascade: 'all-delete-orphan'
        relationships cascade: 'all-delete-orphan'
        docUrls cascade: 'all-delete-orphan'
        satisfiedScoreCardItems joinTable: [name: 'service_item_score_card_item',
                                            column: 'score_card_item_id',
                                            key: 'service_item_id']
    }

    static constraints = {
        whatIsNew nullable: true, maxSize: 250
        descriptionShort nullable: true, maxSize: 150
        isOutside(nullable: true)
        title(blank: false, maxSize: 256)
        description(maxSize: 4000, nullable: true)
        versionName(maxSize: 256, nullable: true)
        types(blank: false)
        requirements(nullable: true, maxSize: 1000)
        dependencies(nullable: true, maxSize: 1000)
        organization(nullable: true, maxSize: 256)
        agency(nullable: true)
        totalRate5(nullable: true)
        totalRate4(nullable: true)
        totalRate3(nullable: true)
        totalRate2(nullable: true)
        totalRate1(nullable: true)
        launchUrl(nullable: true, maxSize: Constants.MAX_URL_SIZE, validator: { val, obj ->
            if (obj.types?.hasLaunchUrl && (!val || 0 == val.trim().size())) {
                return [
                    'serviceItem.launchUrl.required'
                ]
            }
            if (obj.types?.hasLaunchUrl && !val && !validateUrl(val)) {
                return [
                    'serviceItem.launchUrl.url.invalid'
                ]
            }
            if (val != null && val.trim().size() > 0 && !validateUrl(val)) {
                return [
                    'serviceItem.launchUrl.url.invalid'
                ]
            }
        }
        )
        installUrl(nullable: true, maxSize: Constants.MAX_URL_SIZE, validator: { val, obj ->
            if (val != null && val.trim().size() > 0 && !validateUrl(val)) {
                return [
                    'serviceItem.installUrl.url.invalid'
                ]
            }
        }
        )
        categories(nullable: true)
        state(nullable: true)
        releaseDate(nullable: true)
        owfProperties(nullable:true)
        uuid(nullable:false, matches: /^[A-Fa-f\d]{8}-[A-Fa-f\d]{4}-[A-Fa-f\d]{4}-[A-Fa-f\d]{4}-[A-Fa-f\d]{12}$/)
        imageSmallUrl(nullable:true, maxSize:Constants.MAX_URL_SIZE, validator:{ val, obj ->
            if(val?.trim()?.size() > 0 && !validateUrl(val)) {
                return [
                    'serviceItem.imageSmallUrl.url.invalid'
                ]
            }
        })
        imageMediumUrl(nullable:true, maxSize:Constants.MAX_URL_SIZE, validator:{ val, obj ->
            if(val?.trim()?.size() > 0 && !validateUrl(val)) {
                return [
                    'serviceItem.imageLargeUrl.url.invalid'
                ]
            }
        })
        imageLargeUrl(nullable:true, maxSize:Constants.MAX_URL_SIZE, validator:{ val, obj ->
            if(val?.trim()?.size() > 0 && !validateUrl(val)) {
                return [
                    'serviceItem.imageLargeUrl.url.invalid'
                ]
            }
        })
        approvalStatus(inList:Constants.APPROVAL_STATUSES.values().toList())
        lastActivity(nullable:true)
        approvedDate(nullable:true)
        recommendedLayouts(nullable:true)
        // Need to validate each custom field and propagate any errors up to serviceItem
        customFields(validator: { cf, si, errs ->
            def valid = true
            cf?.every {
                if (!it?.validate()){
                    valid = false
                    it?.errors?.allErrors.each{error->
                        si.errors.rejectValue('customFields',error.code + ".CustomField",error.arguments,error.defaultMessage)
                    }
                }
            }
            return valid
        }
        )
        owners( validator: { val ->
            if (val == null || val.isEmpty()) {
                return 'empty'
            }
        })
    }

    void cleanBeforeSave() {
        if (log.isDebugEnabled()) {
            log.debug 'cleanBeforeSave: this.types = ' + this.types
            customFields?.each { log.debug "${it} - ${it.customFieldDefinition?.types}" }
        }

        if (this.customFields?.retainAll({ it.customFieldDefinition.belongsToType(this.types) })) {
            log.debug 'something was removed by service item type test!'
            customFields?.each { log.debug it }
        }

        this.scrubCR()
    }

    String cats2String() {
        def ret = ""
        this.categories?.each { c ->
            ret += c.title + ", "
        }

        if (ret.length() > 0) {
            ret = ret.substring(0, ret.length() - 2)
        }
        return ret
    }

    String lastActivityString() {
        def ret = ""
        if (lastActivity?.getAction()?.description()) {
            ret = lastActivity?.getAction()?.description()
            if (lastActivity?.getActivityDate()?.getDateTimeString()) {
                def dateStr = AdminObjectFormatter.standardShortDateDisplay(lastActivity.activityDate)
                ret += " on " + dateStr
            }
        }
        return ret
    }

    /**
     * Service Item agencies are now stored internally as separate objects.
     * However, for compatibility, the import/export format still needs to have
     * agency and agencyIcon as separate strings
     */
    private static void transformAgencyToLegacy(JSONObject json) {
        if (json.agency instanceof JSONObject) {
            json.agencyIcon = json.agency.iconUrl
            json.agency = json.agency.title
        }
    }

    /**
     * Service Item screenshots are now stored internally as separate objects.
     * However, for compatibility, the import/export format still needs to have
     * the old format
     */
    private static void transformScreenshotsToLegacy(JSONObject json) {
        if (json.screenshots?.size() > 0) {
            json.screenshot1Url = json.screenshots[0]?.smallImageUrl
        }
        if (json.screenshots?.size() > 1) {
            json.screenshot2Url = json.screenshots[1]?.smallImageUrl
        }
    }

    /**
     * @return a JSONObject representing this service item in the format
     * required for backwards compatibility for export and extserviceitems
     */
    JSONObject asLegacyJSON() {
        JSONObject json = this.asJSON()

        transformAgencyToLegacy(json)
        transformScreenshotsToLegacy(json)

        json
    }

    // TODO: it would be nice if we just used the JSON marshaller registered in BootStrap.groovy
    def asJSON() {
        return asJSON(null)
    }

    // The parameter requires allows the caller to pass in a list of required listings which will be
    // add to the JSON structure being returned.
    def asJSON(def requires) {
        def currJSON = new JSONObject(
            id: id,
            title: title,
            versionName: versionName,
            releaseDate: releaseDate ? RELEASE_DATE_FORMAT.format(releaseDate) : null,
            approvedDate: approvedDate,
            lastActivity: new JSONObject(activityDate: lastActivity?.activityDate),
            approvalStatus: approvalStatus,
            isOutside: isOutside,
            avgRate: avgRate,
            agency: agency?.asJSON(),
            totalVotes: totalVotes,
            totalRate5: totalRate5,
            totalRate4: totalRate4,
            totalRate3: totalRate3,
            totalRate2: totalRate2,
            totalRate1: totalRate1,
            totalComments: totalComments,
            categories: (categories?.collect { it.asJSONRef()} ?: []) as JSONArray,
            customFields: (customFields?.collect { it.asJSON() } ?: []) as JSONArray,
            owners: (owners?.collect { it.username } ?: []) as JSONArray,
            dependencies: dependencies,
            description: description,
            docUrls: (docUrls?.collect {it.asJSON()} ?: []) as JSONArray,
            imageSmallUrl: imageSmallUrl,
            imageMediumUrl: imageMediumUrl,
            imageLargeUrl: imageLargeUrl,
            installUrl: installUrl,
            isPublished: state?.isIsPublished(),
            launchUrl: launchUrl,
            validLaunchUrl: validateUrl(launchUrl),
            organization: organization,
            satisfiedScoreCardItems: satisfiedScoreCardItems?.collect { it.asJSON() } as JSONArray,
            recommendedLayouts: (recommendedLayouts?.collect { it.name()} ?: []) as JSONArray,
            requirements: requirements,
            screenshots: (this.screenshots.collect { it?.asJSON() }.findAll { it != null }) as JSONArray,
            state: state?.asJSONRef(),
            techPocs: (techPocs ?: []) as JSONArray,
            types: types?.asJSON(),
            class: getClass(),
            uuid: uuid,
            ozoneAware: types?.ozoneAware,
            isEnabled: isEnabled,
            intents: new JSONArray(),
            contacts: contacts.collect { it.asJSON() } as JSONArray,
            opensInNewBrowserTab: opensInNewBrowserTab,
            relationships: relationships.collect{ it.asJSON() } as JSONArray,
            tags: tags as JSONArray,
            descriptionShort: descriptionShort,
            whatIsNew: whatIsNew
        )

        JSONUtil.addCreatedAndEditedInfo(currJSON, this)
        if (owners != null) {
            currJSON.put('owners', new JSONArray(owners?.collect {
                new JSONObject(id: it.id, name: it.displayName, username: it.username)
            }))
        }

        if (owfProperties != null) {
            currJSON.put("owfProperties", owfProperties.asJSON())
            //TODO remove this once the non-REST API is gone
            currJSON.put("intents", ((owfProperties.intents == null) ? new JSONArray() :
                new JSONArray(owfProperties.intents?.collect { it.asJSON()})))
        }
        if (requires != null) {
            currJSON.put("requires", new JSONArray(requires?.collect {
                new JSONObject(id: it.id, title: it.title, uuid: it.uuid)
            }))
        }

        return currJSON
    }

    def asJSONMinimum () {
        return new JSONObject(
            id: id,
            title: title,
            imageSmallUrl: imageSmallUrl
        )
    }

    //Used for affliated searches or to minimum data needed for the grid / list and hover badges
    def asJSONRef() {

        def json = new JSONObject(
            id: id,
            uuid: uuid,
            title: title,
            description: description,
            imageSmallUrl: imageSmallUrl,
            imageMediumUrl: imageMediumUrl,
            imageLargeUrl: imageLargeUrl,
            totalVotes: totalVotes,
            avgRate: avgRate,
            categoriesString: categories?.collect { it.title?.padLeft(it.title.size() + 1, " ") },
            typesString: types?.collect { it.title?.padLeft(it.title.size() + 1, " ") },
            agency:agency?.asJSON(),

            //These are not used in 7.0 and greater releases but are left here for backwards compatibility
            owners: owners ? new JSONArray(owners?.collect { new JSONObject(id: it.id, name: it.displayName, username: it.username) }) : null,
            versionName: versionName,
            releaseDate: releaseDate,
            lastActivity: lastActivity,
            state: state,
            types: types
        )

        if (owfProperties != null) {
            json.put("owfProperties", owfProperties.asJSON())
        }

        return json
    }

    void scrubCR() {
        if (this.description) {
            this.description = this.description.replaceAll("\r", "")
        }
        if (this.requirements) {
            this.requirements = this.requirements.replaceAll("\r", "")
        }
        if (this.dependencies) {
            this.dependencies = this.dependencies.replaceAll("\r", "")
        }
    }

    boolean hasAccess(String username) {
        if (this.owners?.find { it.username == username }) {
            return true
        } else {
            return !this.isHidden && this.statApproved()
        }
    }

    boolean statApproved() {
        return this.approvalStatus == Constants.APPROVAL_STATUSES["APPROVED"]
    }

    boolean statPending() {
        return this.approvalStatus == Constants.APPROVAL_STATUSES["PENDING"]
    }

    boolean statInProgress() {
        return this.approvalStatus == Constants.APPROVAL_STATUSES["IN_PROGRESS"]
    }

    boolean statRejected() {
        return this.approvalStatus == Constants.APPROVAL_STATUSES["REJECTED"]
    }

    boolean submittable() {
        return !(this.statApproved() || this.statPending())
    }

    Boolean isOWFCompatible() {
        return (statApproved() && types.ozoneAware && state?.isPublished)
    }

    Boolean isOWFAddable() {
        return (isOWFCompatible() && (owfProperties?.isStack() || (types.hasLaunchUrl && validateUrl(launchUrl))))
    }

    Boolean isHidden() {
        return this.isHidden
    }

    /**
     * Determines whether a service item can be launched based upon it's approval, published state
     * and launch URL.
     * @returns Boolean True, if the item can be launched; false, otherwise
     */
    Boolean isLaunchable() {
        return (this.statApproved() && state?.isPublished &&
            (types.hasLaunchUrl && validateUrl(launchUrl)))
    }

    @Override
    int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder()
        builder.append(id)
            .append(version)
            .append(uuid)
        def code = builder.toHashCode()
        return code;
    }

    @Override
    boolean equals(Object obj) {
        if (obj instanceof ServiceItem) {
            ServiceItem other = (ServiceItem) obj
            EqualsBuilder builder = new EqualsBuilder()
            builder.append(id, other.id)
                .append(uuid, other.uuid)
                .append(version, other.version)
            return builder.isEquals();
        }
        return false;
    }

    String getSortTitle() {
        title?.toLowerCase()
    }

    Date getLastActivityDate() {
        lastActivity?.activityDate
    }

    /**
     * Set the value of a custom field
     * @param name
     * @param value
     * @return
     */
    def propertyMissing(String name, value) {
        setCustomFieldValue(name, value)
    }

    public void setCustomFieldValue(String name, value) {
        // Verify the existence of a custom field with the given name
        CustomFieldDefinition customFieldDefinition = CustomFieldDefinition.findByName(name)
        if (customFieldDefinition) {
            CustomField customField = customFieldDefinition.styleType.newField([:])
            customField.customFieldDefinition = customFieldDefinition
            customField.setValue(value)

            if (!this.customFields) {
                this.customFields = new ArrayList<CustomField>()
            } else {
                // Search for an existing value and remove it
                CustomField existingField = this.customFields.find { it.customFieldName == name }
                if (existingField) this.customFields.remove(existingField)
            }
            this.customFields.add customField
        } else {
            throw new MissingPropertyException("Could not find custom field named " + name)
        }
    }

    /**
     * the flag internally known as 'hidden' is externally known as 'enabled'
     */
    public void setIsEnabled(boolean enabled) {
        this.isHidden = enabled ? 0 : 1
    }
    public boolean getIsEnabled() {
        !this.isHidden
    }

    public void setReleaseDate(String dateString) throws ParseException {
        releaseDate = RELEASE_DATE_FORMAT.parse(dateString)
    }

    public void setReleaseDate(Date date) {
        releaseDate = date
    }

    /**
     * Get the value of a custom field
     * @param name
     * @return
     */
    String propertyMissing(String name) {
        getCustomFieldValue(name)
    }

    public boolean hasCustomField(String name) {
        return (this.customFields.find { it?.customFieldDefinition?.name == name }) as boolean
    }

    private String getCustomFieldValue(String name) {
        CustomField customField = this.customFields.find { it?.customFieldDefinition?.name == name }
        if (customField) {
            customField.fieldValueText
        } else {
            throw new MissingPropertyException("Could not find custom field named " + name)
        }
    }

    static boolean findDuplicates(def obj) {
        !!(findByUuid(obj?.uuid) ?:
            OwfProperties.findByUniversalName(obj?.owfProperties?.universalName))
    }

    boolean isAuthor(Profile user) {
        isAuthor(user.username)
    }

    boolean isAuthor(String username) {
        this.owners?.find { it.username == username }
    }

    void addScreenshot(Screenshot screenshot) {
        this.addToScreenshots(screenshot)
    }

    static List<ServiceItem> findAllByAuthor(Profile user) {
        ServiceItem.findAll("from ServiceItem as serviceItem where :user member of serviceItem.owners", [user: user])
    }

    def beforeValidate() {
        //ensure that carriage returns are always removed
        this.scrubCR()

        //make audit trail plugin work on child items
        (modifiableReferenceProperties + ['serviceItemActivities', 'lastActivity']).each { prop ->
            Utils.singleOrCollectionDo(this[prop]) {
                //call beforeValidate if it exists
                if (it?.metaClass?.respondsTo(it, 'beforeValidate')) {
                    it.beforeValidate()
                }
            }
        }
    }

    /**
     * Validates that the custom field values specified in the JSON
     * are for custom fields that this service item should actually
     * have. Any custom fields that shouldn't be there are silently deleted, since its possible
     * that they used to be valid for this type and were removed
     *
     * Also, make sure that all custom fields have been fully marshalled
     *
     * @param dto The ServiceItem being validated
     */
    public void processCustomFields() {
        Types type = this.types

        this.customFields?.retainAll { it.customFieldDefinition?.belongsToType(type) }

        //make sure that all FieldValues are transformed from DTOs into
        //proper FieldValues
        this.customFields?.grep {it instanceof DropDownCustomField}?.each {
            it.marshallAllFieldValues()
        }
    }

    public void checkOwfProperties() {
        def owfProperties = this.owfProperties
        boolean ozoneAware = !!this.types?.ozoneAware

        //if ozoneAware we should always have an OwfProperties
        if (ozoneAware && !owfProperties) {
            owfProperties = this.owfProperties = new OwfProperties()
        }
        //of not ozoneAware we should never have an OwfProperties
        else if (!ozoneAware && owfProperties) {
            //since this method is called on the dto, the owfProperties is still
            //transient and all we have to do is null it out
            owfProperties = this.owfProperties = null
        }
    }

    /**
     * Potentially override the inside/outside setting of the listing based on the
     */
    public void updateInsideOutsideFlag(String globalInsideOutside) {
        switch (globalInsideOutside) {
            case Constants.INSIDE_OUTSIDE_ALL_OUTSIDE:
                this.isOutside = true
                break
            case Constants.INSIDE_OUTSIDE_ALL_INSIDE:
                this.isOutside = false
                break
            default:
                //leave as-is
                break
        }
    }

    /**
     * Update the rating statistics fields to be up to date with the
     * current list of ItemComments.  The following fields are updated:
     * totalComments, totalRate*, totalVotes, and avgRate
     */
    public void updateRatingStats() {
        if (this.itemComments == null) this.itemComments = new HashSet()

        this.totalComments = this.itemComments.size()

        //all of the non-null rating values
        Collection<Float> ratings = this.itemComments.grep { it.rate != null }.collect { it.rate }

        //the rating values grouped
        Map<Integer, Collection<Float>> groupedRatings = ratings.groupBy { Math.round(it) }

        //update each of the totalRating1 ... totalRating5 counts
        (1..5).each { rating ->
            this."totalRate$rating" = groupedRatings[rating]?.size() ?: 0
        }

        this.totalVotes = ratings.size()
        this.avgRate = this.totalVotes ? (ratings.sum() ?: 0) / this.totalVotes : 0F
    }
}
