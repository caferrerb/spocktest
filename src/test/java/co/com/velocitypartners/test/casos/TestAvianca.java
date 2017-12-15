package co.com.velocitypartners.test.casos;

import org.testng.annotations.Test;

import co.com.velocitypartners.prueba.pageobjects.PageObjectFactory;
import co.com.velocitypartners.prueba.pageobjects.definiciones.IAviancaBuscarVuelo;
import co.com.velocitypartners.prueba.pageobjects.implementaciones.AviancaBooking;

public class TestAvianca {
	
	@Test
    public void testAviancaBooking() {
        IAviancaBuscarVuelo avianca=PageObjectFactory.crearPaginaBook();
        
        avianca.buscarVuelo("Bogot", "Armenia", "24112017", "27112017");
        
        
        
    }

}
