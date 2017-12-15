package co.com.velocitypartners.spock.test

import co.com.velocitypartners.prueba.pageobjects.implementaciones.eharmony.Register
import co.com.velocitypartners.spock.extensions.driver.Driver
import co.com.velocitypartners.spock.extensions.pageobject.PageObject
import co.com.velocitypartners.spock.extensions.pageurl.PageUrl
import co.com.velocitypartners.spock.extensions.parameter.Parameter
import co.com.velocitypartners.spock.extensions.tempdirectory.TempDirectory
import co.com.velocitypartners.spock.framework.test.TestBase
import org.openqa.selenium.WebDriver
import spock.lang.Specification

class TestAvianca extends  TestBase {


    @Parameter
    @PageUrl(url="urlbook")
    @PageObject(preLoad = false)
    Register page


    def "probando la extension2" (){

        setup:
            page.setDriver(driver)

        when:

            page.ir()
            def res=page.register(name,gender,seek,zipCode,country,user,pass,hear)

        then:
            res==true
            println 'then esto dio'




        where:
            name <<["camilo ferrer","claudia fernandes"]
            gender << [1,2,1,2]
            seek << [1,1,2,2]
            zipCode << ["0000"]
            user << ["caferreb"]
            pass << ["1234"]
            hear << "30"
            country <<["Colombia", "Venezuela"]


    }
}
