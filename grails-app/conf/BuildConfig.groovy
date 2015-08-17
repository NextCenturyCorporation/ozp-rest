grails.project.plugins.dir = "${basedir}/plugins"
grails.project.dependency.resolver = 'maven'
grails.project.groupId = 'org.ozoneplatform'
grails.project.war.file = 'target/marketplace.war'

coverage {
    exclusions = [
        "**/org/apache/log4j/**",
        "**/marketplace/testing/**",
        "changelog*/**"
    ]
    xml = true
    enabledByDefault = false
}

codenarc.reports = {
    AmlXmlReport('xml') {
        outputFile = 'target/CodeNarcReport.xml'
        title = 'App Mall CodeNarc Report'
    }

    AmlHtmlReport('html') {
        outputFile = 'target/CodeNarcReport.html'
        title = 'App Mall CodeNarc Report'
    }
}

codenarc.ruleSetFiles = "file:grails-app/conf/CodeNarcRules.groovy"
codenarc.propertiesFile = "grails-app/conf/codenarc.properties"

grails.war.resources = { stagingDir ->

    def classesDir = "$stagingDir/WEB-INF/classes"

    //Delete sample security configuration
    [
        'SecurityContext.xml',
        'OzoneConfig.properties',
        'users.properties'
    ].each { delete file: "$classesDir/$it" }

    delete dir: "$classesDir/ozone-security-beans"

    //don't add test ehcache config to war
    delete dir: "$classesDir/ehcache-test.xml"
}

grails.project.dependency.resolution = {

    // inherit Grails' default dependencies
    inherits("global") {
        excludes 'slf4j'
    }

    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'

    repositories {
        grailsPlugins()
        grailsHome()
        mavenLocal()
        mavenRepo 'http://maven.restlet.org'
        grailsCentral()
        mavenCentral()
    }

    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        runtime('net.sf.ehcache:ehcache-jgroupsreplication:1.4', 'log4j:apache-log4j-extras:1.0')
        /*
        //Code-Coverage-1.2 (PLUGIN)
        runtime 'asm:asm-parent:3.0', 'asm:asm:3.0',
            'asm:asm-tree:3.0', 'net.sourceforge.cobertura:cobertura:1.9.4.1'
        */
        runtime('dom4j:dom4j:1.6.1') { excludes 'xml-apis' }
        runtime('org.hibernate:hibernate-ehcache:3.3.1.GA') { excludes 'ehcache', 'hibernate-core' }

        runtime 'mysql:mysql-connector-java:5.1.32'

        //Rome-modules
        compile 'rome:modules:0.3.2'

        compile 'org.springframework.security:spring-security-core:3.2.5.RELEASE'

        //upgrade commons-validator to get correct validation of localhost URLs (OP-420)
        compile('commons-validator:commons-validator:1.4.0')

        //Fix for ClassNotFoundException: javax.ws.rs.ApplicationPath
        runtime('javax.ws.rs:jsr311-api:1.1.1')

        compile('org.ozoneplatform:ozone-security:4.0.3') {
            excludes([group: 'org.springframework'], [name: 'servlet-api'])
        }

        compile 'com.google.guava:guava:18.0'
        compile 'com.fasterxml.jackson.core:jackson-databind:2.3.3'
        compile 'com.damnhandy:handy-uri-templates:2.0.2'
        compile 'com.sun.jersey.contribs:jersey-multipart:1.17'

    }

    plugins {
        compile 'org.ozoneplatform:ozone-auditing:1.2.1'
        compile ':database-migration:1.4.0'
        compile ':quartz:1.0.1'
        compile ':cache:1.1.5'
        compile ':elasticsearch:0.0.4.3'
        compile ':audit-trail:2.0.2'
        compile ':hibernate:3.6.10.12'

        build ':tomcat:7.0.54'
        build ':release:3.0.1'

        test ':codenarc:0.22'
        test ':code-coverage:2.0.3-2'

        runtime ':cors:1.1.6' // OP-3932
        runtime ':jaxrs:0.10'
    }
}
