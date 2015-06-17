package marketplace.rest.service

import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.Files
import java.nio.file.FileSystems
import java.nio.file.SimpleFileVisitor
import java.nio.file.FileVisitResult
import java.nio.file.DirectoryStream
import java.nio.file.PathMatcher
import java.nio.file.attribute.BasicFileAttributes


import javax.ws.rs.core.MediaType

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.annotation.Propagation
import org.springframework.security.access.AccessDeniedException

import org.apache.log4j.Logger

import net.sf.ehcache.CacheManager
import net.sf.ehcache.Cache
import net.sf.ehcache.Element

import org.codehaus.groovy.grails.commons.GrailsApplication

import marketplace.ImageReference
import marketplace.Listing
import marketplace.Agency
import marketplace.Screenshot
import marketplace.Intent
import marketplace.ClientAuditData

import marketplace.rest.DomainObjectNotFoundException
import marketplace.rest.InvalidContentTypeException
import marketplace.rest.RequestTooLargeException

import marketplace.rest.representation.in.InputRepresentation

import org.ozoneplatform.auditing.format.cef.Extension
import org.ozoneplatform.auditing.format.cef.CEF
import org.ozoneplatform.auditing.format.cef.factory.ExtensionFactory
import org.ozoneplatform.auditing.enums.EventTypes
import org.ozoneplatform.auditing.enums.PayloadType
import org.ozoneplatform.auditing.enums.RequestMethodTypes
import org.ozoneplatform.auditing.hibernate.AbstractAuditLogListener

@Service
@Transactional(propagation=Propagation.SUPPORTS)
class ImageRestService {

    private static final Logger log = Logger.getLogger(ImageRestService.class)
    private static final Logger cefLog = Logger.getLogger(AbstractAuditLogListener.class)

    //do not delete images that are younger than a day
    private static final long GARBAGE_COLLECTION_MIN_AGE = (1000 * 60 * 60 * 24)

    //reject images larger than 1MiB
    private static final int IMAGE_MAX_SIZE = (1 << 20)

    @Autowired ProfileRestService profileRestService

    private final Map<MediaType, String> mediaTypeToExtension
    private final Map<String, MediaType> extensionToMediaType

    private final GrailsApplication grailsApplication
    private final Cache imageReferenceCache

    @Autowired
    public ImageRestService(GrailsApplication grailsApplication,
            CacheManager cacheManager) {
        this.grailsApplication = grailsApplication
        this.imageReferenceCache = cacheManager.getCache('imageReference')

        mediaTypeToExtension = Collections.unmodifiableMap(
            grailsApplication.config.marketplace.acceptableImageTypes.collectEntries { k, v ->
                [MediaType.valueOf(k), v]
            })

        extensionToMediaType = Collections.unmodifiableMap(
            mediaTypeToExtension.collectEntries { k, v -> [v, k] })
    }

    ImageRestService() {}

    public Path createFromRepresentation(ImageReference imageRef, byte[] data,
            ClientAuditData auditData) {
        Path imageFile = getPath(imageRef)

        //ensure directory exists
        Files.createDirectories(imageFile.parent)

        if (Files.exists(imageFile)) {
            throw new IllegalStateException("Trying to create file with path that already exists")
        }

        if (data.length > IMAGE_MAX_SIZE) {
            throw new RequestTooLargeException("Images cannot be larger than 1 MiB")
        }
        else if (data.length == 0) {
            throw new IllegalArgumentException("Images cannot be empty")
        }

        try {
            Files.write(imageFile, data)
        }
        catch (IOException e) {
            try {
                if (Files.exists(imageFile)) {
                    Files.delete(imageFile)
                }
            }
            catch (IOException e2) {
                log.warn("Could not clean up image folder after image creation failure", e2)
            }

            throw e
        }

        imageReferenceCache.put(new Element(imageRef.id, imageRef))

        logImageCreate(imageRef, auditData)

        return imageFile
    }

    public Path get(ImageReference imageRef, ClientAuditData auditData) {
        Path path = getPath(imageRef)

        if (Files.notExists(path)) {
            throw new DomainObjectNotFoundException(ImageReference.class, imageRef.id)
        }

        logImageGet(imageRef, auditData)

        return path
    }

    /**
     * Get an image reference that correctly represents the image at the given id.  This
     * requires searching the file system in order to inspect the file extension of the image
     * file.
     */
    public ImageReference getImageReference(UUID id) {
        Element fromCache = imageReferenceCache.get(id)

        if (fromCache) {
            return fromCache.objectValue
        }
        else {
            String folderName = getFolder(id)
            Path folderPath = imageDir.resolve(Paths.get(folderName))
            Path matchedFile

            if (Files.exists(folderPath)) {
                String fileName = getFileBaseName(id)
                String matcherSpec = "glob:**/${fileName}.*"

                PathMatcher matcher = FileSystems.default.getPathMatcher(matcherSpec)

                DirectoryStream<Path> dirIter = Files.newDirectoryStream(folderPath)
                try {
                    matchedFile = dirIter.find {
                        matcher.matches(it)
                    }
                }
                finally {
                    dirIter.close()
                }
            }

            if (matchedFile) {
                //dot is guaranteed to be present based on specified glob pattern
                String extension = matchedFile.fileName.toString().split('\\.')[1]
                MediaType mediaType = extensionToMediaType[extension]
                ImageReference ref = new ImageReference(id, mediaType)

                imageReferenceCache.put(new Element(id, ref))
                logImageGet(ref, null)
                return ref
            }
            else {
                throw new DomainObjectNotFoundException(ImageReference, id)
            }
        }
    }

    /**
     * @return the name of the file for the image reference, without the extension or dot
     */
    private String getFileBaseName(UUID id) {
        id.toString()
    }

    /**
     * @return the name of the file for the image reference, without the extension or dot
     */
    private String getFolder(UUID id) {
        id.toString()[0..1]
    }

    public Path getRelativePath(ImageReference imageRef) {
        String fileName = getFileBaseName(imageRef.id), folderName = getFolder(imageRef.id),
            extension = getFileExtension(imageRef)

        Paths.get(folderName, "$fileName.$extension")
    }

    private Path getPath(ImageReference imageRef) {
        imageDir.resolve(getRelativePath(imageRef))
    }

    public Path getImageDir() {
        Paths.get(grailsApplication.config.marketplace.imageStoragePath)
    }

    public String getFileExtension(ImageReference imageRef) {
        String extension = mediaTypeToExtension[imageRef.mediaType]

        if (!extension) {
            throw new InvalidContentTypeException(
                "Unacceptable media type ${imageRef.mediaType}")
        }

        return extension
    }

    public MediaType getMediaType(String fileExtension) {
        MediaType mediaType = extensionToMediaType[fileExtension]

        if (!mediaType) {
            throw new IllegalArgumentException(
                "Could not find mediaType for extension $fileExtension")
        }

        return mediaType
    }

    /**
     * Delete 'orphan' images.  An image is an orphan if it is at least a day
     * old and has no Listings, Screenshots, Intents, or Agencies referring to it
     * @return the number of image files deleted
     */
    @Transactional(propagation=Propagation.REQUIRED)
    public int garbageCollectImages() {
        profileRestService.checkAdmin()

        long maxDateToDelete = new Date().time - GARBAGE_COLLECTION_MIN_AGE
        int deletedFiles = 0
        Path imageDir = this.imageDir
        Set<UUID> idsToKeep =
            (Listing.createCriteria().list {
                projections {
                    property('smallIconId')
                    property('largeIconId')
                    property('bannerIconId')
                    property('featuredBannerIconId')
                }
            }.flatten() as Set) +
            (Screenshot.createCriteria().list {
                projections {
                    property('smallImageId')
                    property('largeImageId')
                }
            }.flatten() as Set) +
            (Agency.createCriteria().list {
                projections {
                    property('iconId')
                }
            }.flatten() as Set) +
            (Intent.createCriteria().list {
                projections {
                    property('iconId')
                }
            }.flatten() as Set) - null


        //ensure image dir exists
        if (Files.notExists(imageDir)) {
            Files.createDirectories(imageDir)
        }

        //traverse the image directory and delete images that are at least a day old
        //and are not referenced by any domain object
        Files.walkFileTree(imageDir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                    throws IOException {
                String fileName = file.fileName.toString()
                log.debug "Visiting file ${fileName}"
                String[] fileNameParts = fileName.split('\\.')

                if (fileNameParts.length != 2) {
                    log.warn "Found file with unexpected name: $fileName"
                    return FileVisitResult.CONTINUE
                }

                UUID uuid
                try {
                    uuid = UUID.fromString(fileNameParts[0])
                }
                catch (IllegalArgumentException e) {
                    log.warn "Invalid UUID in filename $fileName"
                    return FileVisitResult.CONTINUE
                }

                if (attrs.lastModifiedTime().toMillis() < maxDateToDelete &&
                        !(uuid in idsToKeep)) {

                    log.debug "Deleting image file $file"
                    logImageDelete(file)

                    Files.delete(file)
                    imageReferenceCache.remove(uuid)
                    deletedFiles++
                }

                return FileVisitResult.CONTINUE
            }
            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException e)
                    throws IOException {
                if (e) {
                    // directory iteration failed
                    throw e
                } else {
                    if (dir == imageDir) {
                        return FileVisitResult.CONTINUE
                    }
                    else {
                        //if the directory is empty delete it
                        DirectoryStream<Path> dirStream = Files.newDirectoryStream(dir)
                        try {
                            if (!dirStream.iterator().hasNext()) {
                                log.debug "Deleting empty image directory $dir"
                                Files.delete(dir)
                            }
                        }
                        finally {
                            dirStream.close()
                        }

                        return FileVisitResult.CONTINUE
                    }
                }
            }
        })

        return deletedFiles
    }

    private void logImageCreate(ImageReference imageRef, ClientAuditData auditData) {
        if (grailsApplication.config.cef.enabled) {
            logImageCef(EventTypes.OBJ_CREATE, imageRef, auditData)
        }
    }

    private void logImageGet(ImageReference imageRef, ClientAuditData auditData) {
        if (grailsApplication.config.cef.verbose && grailsApplication.config.cef.enabled) {
            logImageCef(EventTypes.OBJ_ACCESS, imageRef, auditData)
        }
    }

    private ImageReference getImageRefFromPath(Path path) {
        String fileName = path.fileName.toString()
        String[] parts = fileName.split('\\.')
        if (parts.length != 2) {
            throw new IllegalArgumentException("Cannot construct ImageReference from path $path")
        }

        UUID id = UUID.fromString(parts[0])
        MediaType mediaType = getMediaType(parts[1])

        return new ImageReference(id, mediaType)
    }

    private void logImageDelete(Path path) {
        if (grailsApplication.config.cef.enabled) {
            ImageReference imageRef = null
            try {
                imageRef = getImageRefFromPath(path)
            }
            catch (IllegalArgumentException e) {
                log.warn "Could not create CEF log for deletion of $path"
            }

            if (imageRef) {
                logImageCef(EventTypes.OBJ_DELETE, imageRef, null)
            }
        }
    }

    private void logImageCef(EventTypes event, ImageReference imageRef,
            ClientAuditData auditData) {
        InetAddress localAddress = InetAddress.localHost
        def cefConfigs = grailsApplication.config.cef

        String empty = '',
            appVersion = grailsApplication.metadata['app.version'],
            classification = cefConfigs.securityLevel ?: Extension.UNKOWN_VALUE,
            date = ExtensionFactory.eventDateFormatter.clone().format(new Date()),
            trigger = RequestMethodTypes.USER_INITIATED.getDescription(),
            source = auditData?.remoteAddr ?: localAddress.hostAddress,
            dest = auditData?.localAddr ?: localAddress.hostAddress,
            username = profileRestService.currentUserProfile?.username ?: 'SYSTEM',
            eventType = event.description,
            filepath = getPath(imageRef).toString(),
            payloadType = PayloadType.FILE.getDescription(),
            deviceVendor = cefConfigs.device.vendor,
            deviceProduct = cefConfigs.device.product,
            deviceVersion = cefConfigs.device.version,
            description

        //not sure of the meaning here, but matches the severity in AbstractAuditLogListener
        int severity = 7,
            cefVersion = cefConfigs.version

        switch(event) {
            case EventTypes.OBJ_CREATE:
                description = 'Object was created'
                break
            case EventTypes.OBJ_ACCESS:
                description = 'Object was accessed'
                break
            case EventTypes.OBJ_DELETE:
                description = 'Object was deleted'
                break
        }

        Extension extension = new Extension(
            "${Extension.EVENT_TYPE}": empty,
            "${Extension.STATUS}": 'SUCCESS',
            "${Extension.REASON}": empty,
            "${Extension.SYSTEM_NOTIFICATIONS}": empty,
            "${Extension.SYSTEM_VERSION}": appVersion,
            "${Extension.TRANSACTION_ID}": empty,
            "${Extension.DESTINATION_CLS}": classification,
            "${Extension.EVENT_DATE_TIME}": date,
            "${Extension.EVENT_CLS}": classification,
            "${Extension.SOURCE}": source,
            "${Extension.DESTINATION}": dest,
            "${Extension.TRIGGER}": trigger,
            "${Extension.USER_ID}": username,
            "${Extension.EVENT_TYPE}": eventType,
            "${Extension.PAYLOAD_CLS}": classification,
            "${Extension.PAYLOAD}": filepath,
            "${Extension.PAYLOAD_ID}": imageRef.id,
            "${Extension.PAYLOAD_TYPE}": payloadType
        )

        CEF cef = new CEF(cefVersion, deviceVendor, deviceProduct, deviceVersion,
                eventType, description, severity, extension)

        cefLog.info cef.toString()
    }
}
