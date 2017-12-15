package co.com.velocitypartners.spock.framework.test

import co.com.velocitypartners.spock.extensions.driver.Driver
import org.openqa.selenium.WebDriver
import spock.lang.Specification

class TestBase extends Specification {



    @Driver
    protected WebDriver driver



    def setup() {println "super.setup"}          // run before every feature method

    def cleanup() {
        println "super.cleanup"
        driver.close()

    }        // run after every feature method

    def setupSpec() {println "super.setupSpec"}     // run before the first feature method
    def cleanupSpec() {println "super.cleanupSpec"}   // run after the last feature method
}
