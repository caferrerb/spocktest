package co.com.velocitypartners.prueba.pageobjects;

import org.openqa.selenium.WebDriver;

import co.com.velocitypartners.prueba.pageobjects.definiciones.IAviancaBuscarVuelo;
import co.com.velocitypartners.prueba.pageobjects.definiciones.IClientePO;
import co.com.velocitypartners.prueba.pageobjects.definiciones.ILoginPO;
import co.com.velocitypartners.prueba.pageobjects.implementaciones.AviancaBooking;
import co.com.velocitypartners.prueba.pageobjects.implementaciones.CrearClientePO;
import co.com.velocitypartners.prueba.pageobjects.implementaciones.Login;
import co.com.velocitypartners.prueba.util.DriverUtilities;

public class PageObjectFactory {
	private static WebDriver driver;
	static{
		driver=DriverUtilities.getDriver();
	}
	public static IClientePO crearPaginaCliente(){
		CrearClientePO po= new CrearClientePO();
		po.ir();
		
		return po;
	}
	
	
	public static ILoginPO crearPaginaLogin(){
		Login log=new Login();
		log.ir();
		return log;
	}
	
	public static IAviancaBuscarVuelo crearPaginaBook(){
		AviancaBooking log=new AviancaBooking();
		log.setDriver(driver);
		log.ir();
		
		return log;
	}
}
