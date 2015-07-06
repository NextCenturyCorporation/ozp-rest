package marketplace.rest.service

import java.io.IOException
import java.

import marketplace.Constants
import marketplace.IwcDataObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.annotation.Isolation
import org.springframework.security.access.AccessDeniedException

import org.apache.log4j.Logger

import net.sf.ehcache.CacheManager
import net.sf.ehcache.Cache
import net.sf.ehcache.Element

import org.codehaus.groovy.grails.commons.GrailsApplication

import marketplace.Notification
import marketplace.Profile
import marketplace.Agency
import marketplace.Role
import marketplace.Sorter

import marketplace.authentication.AccountService
import marketplace.rest.service.NotificationRestService

import javax.ws.rs.core.MediaType

import marketplace.rest.representation.in.AgencyIdRef
import marketplace.rest.DomainObjectNotFoundException

@Service
class ProfileRestService extends RestService<Profile> {
    @Autowired AccountService accountService
    @Autowired AgencyRestService agencyRestService
    @Autowired NotificationRestService notificationRestService

    private final Cache recentLoginsCache
    private static final Logger log = Logger.getLogger(ProfileRestService.class)

    @Autowired
    public ProfileRestService(GrailsApplication grailsApplication, CacheManager cacheManager) {
        super(grailsApplication, Profile.class, null,
            new Sorter<Profile>(Constants.SortDirection.ASC, 'displayName'))

        this.recentLoginsCache = cacheManager.getCache('recentLogins')
    }

    //Keep CGLIB happy
    ProfileRestService() {}

    protected void authorizeUpdate(Profile existing) {
        Profile currentUserProfile = this.currentUserProfile
        if (!(isAdmin() || accountService.loggedInUsername == existing.username)) {
            throw new AccessDeniedException("Unauthorized attempt to modify profile " +
                "${existing.username} by user ${accountService.loggedInUsername}")
        }
    }

    protected void authorizeCreate(Profile dto) {
        throw new AccessDeniedException("Profiles cannot be created via the REST interface")
    }

    @Transactional(readOnly=true)
    public Collection<Profile> getAll(Integer offset, Integer max, Role role,
            String usernameStartsWith) {
        Profile.createCriteria().list(offset: offset, max: max) {
            switch (role) {
                case Role.USER:
                    or {
                        eq('highestRole', Role.USER)
                        eq('highestRole', Role.ORG_STEWARD)
                        eq('highestRole', Role.APPSMALL_STEWARD)
                        eq('highestRole', Role.ADMIN)
                    }
                    break;
                case Role.ORG_STEWARD:
                    or {
                        eq('highestRole', Role.ORG_STEWARD)
                        eq('highestRole', Role.APPSMALL_STEWARD)
                        eq('highestRole', Role.ADMIN)
                    }
                    break;
                case Role.APPSMALL_STEWARD:
                    or {
                        eq('highestRole', Role.APPSMALL_STEWARD)
                        eq('highestRole', Role.ADMIN)
                    }
            }

            if (usernameStartsWith) {
                ilike('displayName', "${usernameStartsWith}%")
            }

            if (this.sorter) {
                order(sorter.sortField, sorter.direction.name().toLowerCase())
            }
        }
    }


    @Transactional(readOnly = true)
    public Collection<IwcDataObject> getUserData(Long userId) {
        Profile profile = getById(userId)
        authorizeView(profile)

        IwcDataObject.findAllByProfile(profile)
    }

    @Transactional(readOnly = true)
    public Collection<IwcDataObject> findDataItems(Long userId, String key) {
        Profile profile = getById(userId)
        authorizeView(profile)
        IwcDataObject.createCriteria().list(){
            eq('profile', profile)
            ilike('key', key+'%')
        }
    }

    @Transactional(readOnly=true)
    public IwcDataObject getDataItem(Long userId, String key) {
        Profile profile = getById(userId)
        authorizeView(profile)

        def object = IwcDataObject.findByProfileAndKey(profile, key)

        if(!object) {
            throw new DomainObjectNotFoundException(IwcDataObject.class, key)
        }

        object
    }

    @Transactional
    public IwcDataObject updateDataItem(Long userId, String key, String entity, String contentType) {
        Profile profile = getById(userId)
        authorizeUpdate(profile)

        IwcDataObject putValue = IwcDataObject.findByProfileAndKey(profile, key)

        if(!putValue) {
            profile.addToIwcData(key: key, entity: entity, contentType: contentType)
            profile.save(failOnError: true)
            putValue = IwcDataObject.findByProfileAndKey(profile, key)
        } else {
            putValue.entity = entity
            putValue.contentType = contentType
            putValue.save(failOnError: true)
        }
        putValue
    }

    @Transactional
    public void deleteDataItem(Long userId, String key) {
        Profile profile = getById(userId)
        authorizeUpdate(profile)

        getDataItem(profile.id, key).delete()
    }

    /**
     * get the current user profile with an optional pessimistic lock
     * @param lock If true, the profile is locked for update at the database level
     */
    @Transactional(readOnly=true)
    public Profile getCurrentUserProfile(boolean lock=false) {
        Profile.findByUsername(accountService.loggedInUsername, [lock: lock])
    }

    /**
     * Ensure that a Profile object exists for the current user and update it from the security
     * plugin information
     */
    @Transactional(isolation=Isolation.READ_COMMITTED)
    public void login() {
        String username = accountService.loggedInUsername

        //this cache is set to expire items after 1 minute, so any
        //entry in the cache is a recent login
        if (recentLoginsCache.get(username)) {
            //log.error('Found ' + DEBUG_USERNAME + ' in cache')
            return
        }

        log.error('ProfileResService.login (not found in cache), username: ' + username + ', loggedInOrg: ' + accountService.loggedInOrganization)


        //pessimistically lock for update
        Profile profile = getCurrentUserProfile(true) ?: new Profile(
            username: accountService.loggedInUsername
        )

        //TODO This might need to be more robust
        Agency organization = Agency.findByTitle(accountService.loggedInOrganization)
        if (organization) {
            log.error('Found organization for user ' + accountService.loggedInUsername + ': ' + organization)
            profile.addToOrganizations(organization)
        } else {
            log.error('Failed to find organization for user ' + accountService.loggedInUsername + ', looking for org title: ' + accountService.loggedInOrganization)
        }

        profile.with {
            displayName = accountService.loggedInDisplayName
            email = accountService.loggedInEmail
            lastLogin = new Date()
            highestRole = accountService.loggedInUserRoles.collect {
                Role.fromGrantedAuthority(it)
            }.max()
        }

        log.error('Saving profile for user ' + accountService.loggedInUsername + 'displayName: ' + profile.displayName + ', email: ' + profile.email + ', highestRole: ' + profile.highestRole)

        profile.save(failOnError:true)
        log.error('Finished saving profile for user ' + accountService.loggedInUsername)
        recentLoginsCache.put(new Element(username, null))
    }

    @Override
    protected void postprocess(Profile updated, Map original = null) {
        super.postprocess(updated, original)

        if (updated.organizations != original.organizations) {
            checkAdmin("Organization affiliation can only be updated by admins")
        }

        if (updated.stewardedOrganizations != original.stewardedOrganizations) {
            checkAdmin("Only admins can alter stewardship assignments")
        }
    }

    @Transactional(readOnly=true)
    public boolean isAdmin() {
        currentUserProfile.hasRole(Role.ADMIN)
    }

    @Transactional(readOnly=true)
    public boolean isUser() {
        currentUserProfile.hasRole(Role.USER)
    }

    /**
     * @return true if the current user is actually an org steward.  Note that this will
     * return false for users who are admins, even though being an admin implies org steward
     * capabilities.  The point of this method is to distinguish between org stewards and admins
     */
    @Transactional(readOnly=true)
    public boolean isOrgSteward() {
        currentUserProfile.hasRole(Role.ORG_STEWARD) && !isAdmin()
    }

    /**
     * @return whether or not the current user has org steward privileges over the specified
     * org.  This will be the case either if they are an admin, or if they are an org steward
     * whose stewardedOrganizations contains the specified org
     */
    @Transactional(readOnly=true)
    public boolean isOrgSteward(Agency org) {
        Profile profile = currentUserProfile

        isAdmin() ||
            (profile.hasRole(Role.ORG_STEWARD) && profile.stewardedOrganizations.contains(org))
    }

    /**
     * @throws AccessDeniedException if the current user in not an Admin
     * @param msg the Message for the exception
     */
    @Transactional(readOnly=true)
    public void checkAdmin(String msg = "Attempt to access Admin-only functionality") {
        if (!isAdmin()) {
            throw new AccessDeniedException(msg)
        }
    }

    @Transactional(readOnly=true)
    public void checkOrgSteward(Agency organization,
            String msg = "Attempt to access Organization-Steward " +
            "functionality for $organization") {
        if (!isOrgSteward(organization)) {
            throw new AccessDeniedException(msg)
        }
    }

    /**
     * Mark this Notification as dismissed for the given user
     */
    @Transactional
    public void dismissNotification(long profileId, long notificationId) {
        def profile = getById(profileId)
        authorizeUpdate(profile)
        def notification = notificationRestService.getById(notificationId)
        // If it already exists, the insert will fail silently
        profile.dismissedNotifications.add(notification)
        this.save(profile)
    }

    /**
     * Get all unexpired Notifications that this user hasn't dismissed yet
     */
    @Transactional(readOnly=true)
    public List<Notification> getUnreadNotifications(long profileId) {
        def now = new Date()
        def profile = getById(profileId)
        Notification.findAll("from Notification as notification where notification.expiresDate > :now and :profile not member of notification.dismissedBy order by expiresDate desc", [now: now, profile: profile])
    }

    /**
     * Remove this notification from all Profile.dismissedNotifications in preparation for deletion
     */
    @Transactional
    public void clearNotificationFromDismissed(long notificationId) {
        def notification = notificationRestService.getById(notificationId)
        def profiles = Profile.findAll("from Profile as p where :notification member of p.dismissedNotifications", [notification: notification])
        for (p in profiles) {
            p.dismissedNotifications.removeAll{it.id == notification.id}
            this.save(p)
        }
    }

}
