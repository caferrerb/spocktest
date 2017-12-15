package co.com.velocitypartners.test.casos;

import co.com.velocitypartners.prueba.pageobjects.definiciones.eharmony.IRegisterPO;
import co.com.velocitypartners.prueba.pageobjects.implementaciones.eharmony.Register;
import co.com.velocitypartners.test.util.TestBase;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class TestRegisterEHarmony  extends TestBase {


    private IRegisterPO registerPage;

    @Override
    public void init() {
        registerPage= PageFactory.initElements(driver, Register.class);
    }

    @Test
    public void testRegister(){

        registerPage.register("Camilo Andres Ferrer",1,2,"00000","Colombia"
        ,"caferrerb@gmail.com","Algo1234@","30");

    }
}
