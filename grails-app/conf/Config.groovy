import grails.util.*

import org.springframework.web.context.request.RequestContextHolder as RCH
import org.codehaus.groovy.grails.commons.ConfigurationHolder

// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts
def userConfig = System.properties.userConfig ?: "${userHome}/.ozone/MarketplaceConfig.groovy"
grails.config.locations = ["file:resources/MarketplaceConfig.groovy",
    "file:resources/OzoneConfig.properties",
    OverlayConfig,
    MetadataConfig,
    "file:${userConfig}"]

environments {
    production {
        grails.config.locations = ["classpath:MarketplaceConfig.groovy",
            "classpath:OzoneConfig.properties",
            OverlayConfig,
            MetadataConfig]
        log4j = {
            appenders {
                rollingFile name: 'stacktrace',
                    maxFileSize: "10000KB",
                    maxBackupIndex: 10,
                    file: "logs/stacktrace.log"
                layout: pattern(conversionPattern: '%d{dd MMM yyyy HH:mm:ss,SSS z} %m%n')
            }
            error stacktrace: "StackTrace"
        }
    }
}

println "grails.config.locations = ${grails.config.locations}"


marketplace.layout = "marketplace"

grails.databinding.useSpringBinder = true

grails.mime.file.extensions = false // enables the parsing of file extensions from URLs into the request format
grails.mime.types = [html: ['text/html', 'application/xhtml+xml'],
    xml: ['text/xml', 'application/xml'],
    text: 'text-plain',
    js: 'text/javascript',
    rss: 'application/rss+xml',
    atom: 'application/atom+xml',
    css: 'text/css',
    csv: 'text/csv',
    all: '*/*',
    json: ['application/json', 'text/json'],
    form: 'application/x-www-form-urlencoded',
    multipartForm: 'multipart/form-data'
]
// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
grails.war.resources = { stagingDir ->
    delete(file: "${stagingDir}/WEB-INF/lib/jetty-6.1.21-sources.jar")
}
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true


discoveryMaxPerRow = 5;
discoveryWidgetMaxPerRow = 6;
grails {
    plugin {
        audittrail {
            //the created and edited fields should be present or they won't get added during AST
            createdBy.field = "createdBy" //id who created
            createdBy.mapping = "column: 'created_by_id'"
            createdBy.type = 'marketplace.Profile'
            createdBy.constraints = 'nullable: true'
            createdDate.field = "createdDate" // if you want a date stamp that is not the grails default dateCreated
            editedBy.field = "editedBy" //id who updated/edited
            editedBy.type = 'marketplace.Profile'
            editedBy.mapping = "column: 'edited_by_id'"
            editedBy.constraints = 'nullable: true'
            editedDate.field = "editedDate"//use this field instead of the grails default lastUpdated
            currentUserClosure = { ctx ->
                Long returnValue
                if(RCH?.getRequestAttributes()?.getSession()?.profileID != null)
                {
                    returnValue = RCH?.getRequestAttributes()?.getSession()?.profileID
                }
                if (returnValue)
                    return marketplace.Profile.get(returnValue)
                else
                    return marketplace.Profile.get(
                        ConfigurationHolder.config.system_user_id)
            }
        }
    }
}

/**
 * ElasticSearch Defaults - override these in an external config as needed
 */
 elasticSearch {
     datastoreImpl = 'hibernateDatastore'
     client.mode = 'local'
     index.store.type = 'memory'
 }

environments {
    test {
        log4j = {
            appenders {
                rollingFile name: "marketplace", maxFileSize: "10000KB", maxBackupIndex: 10, file: "logs/unittest/marketplace.log", layout: pattern(conversionPattern: '%d{yyyy-MM-dd HH:mm:ss,SSS z} [%t] %-5p[%c]: %m%n')
                rollingFile name: "elasticsearch", maxFileSize: "10000KB", maxBackupIndex: 10, file: "logs/elasticsearch.log", layout: pattern(conversionPattern: '%d{dd MMM yyyy HH:mm:ss,SSS z} %m%n')
            }
            debug marketplace: 'grails.app', additivity: false
            debug elasticsearch: 'org.grails.plugins.elasticsearch', additivity: false
            root {
                error 'marketplace'
                additivity = true
            }
        }

        uiperformance.enabled = false  // MS: Had to disable the plugin for the Selenium tests to work
    }

    stage {
    }

    schema_export_oracle {
    }
    development {
        uiperformance.enabled = false

        // ----------------------------------------------------------------------------
        // Import stub locations; if populated with a value, this will stub out the import interface!
        //    Any URL on any importTask will be overridden by this stub file content.
        //    Note that if multiple files are present, which file gets used is currently non-deterministic.
        //    May use classpath-relative path or URL.
        // ----------------------------------------------------------------------------
        //marketplace.importStub.locations = ["classpath:marketplaceImportStub.json"]
        //marketplace.importStub.locations = ["file:///c:/marketplace/test/marketplaceImportStub.json"]

        //log4j configuration
        log4j = {
            // Example of changing the log pattern for the default console
            // appender:
            //
            //appenders {
            //    console name:'stdout', layout:pattern(conversionPattern: '%d{yyyy-MM-dd HH:mm:ss,SSS z} [%t] %-5p[%c]: %m%n')
            //}

            appenders {
                console name: 'stdout', layout: pattern(conversionPattern: '%d{yyyy-MM-dd HH:mm:ss,SSS z} [%t] %-5p[%c]: %m%n')
                rollingFile name: "stacktrace", maxFileSize: "10000KB", maxBackupIndex: 10, file: "logs/stacktrace.log", layout: pattern(conversionPattern: '%d{dd MMM yyyy HH:mm:ss,SSS z} %m%n')
                rollingFile name: "marketplace", maxFileSize: "10000KB", maxBackupIndex: 10, file: "logs/marketplace.log", layout: pattern(conversionPattern: '%d{yyyy-MM-dd HH:mm:ss,SSS z} [%t] %-5p[%c]: %m%n')
                rollingFile name: "marketplaceCefAudit", maxFileSize: "10000KB", maxBackupIndex: 10, file: "logs/marketplace-cef-audit.log", layout: pattern(conversionPattern: '%d{yyyy-MM-dd HH:mm:ss,SSS z} [%t] %-5p[%c]: %m%n')
                rollingFile name: "elasticsearch", maxFileSize: "10000KB", maxBackupIndex: 10, file: "logs/elasticsearch.log", layout: pattern(conversionPattern: '%d{dd MMM yyyy HH:mm:ss,SSS z} %m%n')
            }
            info marketplaceCefAudit: 'ozone.marketplace.util.AuditLogListener', 'org.ozoneplatform'
            info marketplace: 'grails.app.bootstrap'
            info marketplace: 'grails.app'
            info elasticsearch: 'org.grails.plugins.elasticsearch', additivity: false
            //debug marketplace: 'grails.app.bootstrap', additivity: false
            //debug marketplace: 'grails.app', additivity: false

            //debug 'ozone.utils'
            //debug 'util'
            error marketplace: ['org.springframework', 'org.hibernate']
            //debug  'org.springframework', 'org.hibernate.transaction'

            error stacktrace: 'StackTrace'

            root {
                error 'stdout'
                additivity = false
            }

            //debug marketplace: 'liquibase'
            //debug marketplace: 'grails.plugin.databasemigration'
            //trace 'org.hibernate.cache'
            //trace 'org.compass.core.lucene.engine.LuceneSearchEngine'
            //trace 'org.springframework.transaction'
            //trace 'org.codehaus.groovy.grails.orm.hibernate.GrailsHibernateTransactionManager'

            //uncomment the filter in src/templates/war/web.xml to use this
            debug 'org.apache.catalina.filters.RequestDumperFilter'
        }
    }
}
grails.cache.config = {
    defaults {
        // set default cache properties that will apply to all caches that do not override them
        eternal = false
        diskPersistent = false
        overflowToDisk = false
        // 40 minutes
        timeToLive = 2400
    }
//    caches {
//        pirateCache {
//            // set any properties unique to this cache
//            memoryStoreEvictionPolicy = "LRU"
//        }
//    }
}


system_user_id = 1

httpsession.timeout = 120

marketplace.defaultSearchPageSize = 24
marketplace.defaultLandingPageSize = 18

grails.plugin.databasemigration.changelogFileName = "changelog_master.groovy"
grails.plugin.databasemigration.updateOnStart = false
grails.plugin.databasemigration.updateOnStartFileNames = ["changelog_master.groovy"]

marketplace.defaultAffiliatedMarketplaceTimeout = 30000
// A value of null means export all
//marketplace.maxListingsToExport=25
//marketplace.maxProfilesToExport=20

//Custom quartz configuration goes here.
quartz {
    autoStartup = false
    props {
        scheduler.skipUpdateCheck = true
    }
}
environments {
    production {
        quartz {
            autoStartup = true
            props {
                scheduler.skipUpdateCheck = true
            }
        }
    }
}

cef {
    device {
        vendor = "OZONE"
        product = "Store"
        version = "500-27_L2::1.3"
    }
    version = 0
    enabled = true
    verbose = false
}



// Disabled SpringCache plugin since it does not support clustering by storing
// non-serializable objects in cache
grails.cache.enabled = false

org.grails.jaxrs.doreader.disable=true
org.grails.jaxrs.dowriter.disable=true

println "Config loaded"

notifications.enabled = false
