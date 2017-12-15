package co.com.velocitypartners.test.dataprovider;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import co.com.velocitypartners.test.util.dataprovider.CSVDataProvider;

public class ClienteDataProvider  {

	public static final String NOMBRE="clientedata";
	
	@DataProvider(name=NOMBRE)
	public static Object[][] data(ITestContext ctx) {
		// TODO Auto-generated method stub
		System.err.println("DATAPROVDIDER DE TEST "+ctx.getName());
		try {
			return new CSVDataProvider().obtenerDatos(null, NOMBRE);
		} catch (Exception e) {
			return null;
		}
	}
	

}
