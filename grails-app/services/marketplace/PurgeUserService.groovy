package marketplace
import ozone.marketplace.service.AuthorizationException

class PurgeUserService {

    boolean transactional = true

    def accountService
    def itemCommentRestService
    def grailsApplication

    void purgeInactiveAccounts() {
        def thresholdInDays = 90

        List<Profile> inactiveAccounts = getInactiveAccounts(thresholdInDays)
        inactiveAccounts.each { account ->
            Profile profile = Profile.findByUsername(account.username)
            if (profile) {
                purgeUser(profile)
            }
        }
    }

    List<Profile> getInactiveAccounts(def thresholdInDays) {
        Date cutOffDate = new Date().minus(thresholdInDays.toInteger())
        List<Profile> inactiveAccounts = Profile.findAllByLastLoginLessThan(cutOffDate)
        return inactiveAccounts
    }

    void purgeUser(Profile user) throws AuthorizationException {
        Profile systemProfile = Profile.getSystemUser()

        // This has to be run as the system user because when we try to change the editedBy field for
        // gorm.AuditStamp domains, the beforeValidate() hook will automatically set editedBy to the current user.
        if (!currentUserIsSystem()) {
            throw new AuthorizationException(message: "Only the System user can purge users.")
        }

        def serviceItems = Listing.findAllByAuthor(user)
        serviceItems.each {
            it.removeFromOwners(user)
            it.addToOwners(systemProfile)
            it.save(flush: true)
        }

        def serviceItemActivities = ListingActivity.findAllByAuthor(user)
        serviceItemActivities.each {
            it.author = systemProfile
            it.save(flush: true)
        }

        def itemComments = ItemComment.findAllByAuthor(user)
        itemComments.each {
            def si = it.listing
            si.refresh()
            itemCommentRestService.deleteById(it.id)
            si.save(flush: true)
        }

        def rejectionListings = RejectionListing.findAllByAuthor(user)
        rejectionListings.each {
            it.author = systemProfile
            it.save(flush: true)
        }

        // The gorm.AuditStamp annotation automatically adds createdBy and editedBy fields to the domain,
        // so we need to make sure we update those wherever they exist.
        grailsApplication.getDomainClasses().each { domainClass ->
            if (domainClass.clazz.isAnnotationPresent(gorm.AuditStamp)) {
                // Change the createdBy
                domainClass.clazz.findAllByCreatedBy(user).each {
                    it.createdBy = systemProfile
                }
                // Change the editedBy (note that it doesn't matter what we assign to it.editedBy because
                // the gorm.AuditStamp AuditInterceptor will automatically overwrite it with the current user
                domainClass.clazz.findAllByEditedBy(user).each {
                    it.editedBy = systemProfile
                }
            }
        }

        // Delete the profile last, after all references to it have been changed/removed.
        user.delete()
    }

    private boolean currentUserIsSystem() {
        def currentUser = accountService.getLoggedInUsername()
        return (currentUser == null || currentUser == Profile.getSystemUser().username)
    }

}
