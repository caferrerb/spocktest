package co.com.velocitypartners.test.casos;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import co.com.velocitypartners.prueba.pageobjects.PageObjectFactory;
import co.com.velocitypartners.prueba.pageobjects.definiciones.IClientePO;
import co.com.velocitypartners.prueba.pageobjects.definiciones.ILoginPO;
import co.com.velocitypartners.prueba.util.ReporterUtil;
import co.com.velocitypartners.test.dataprovider.ClienteDataProvider;
import co.com.velocitypartners.test.util.TestBase;

public class TestCliente extends TestBase {

	private IClientePO paginaCli;
	private ILoginPO login;

	@Override
	public void init() {
		
		login = PageObjectFactory.crearPaginaLogin();
	}

	@BeforeTest
	public void login() {
		login.login("asesor", "1234");
		ReporterUtil.getInstance().agregarScreen();
	}

	@Test(dataProvider = "clientedata", dataProviderClass = ClienteDataProvider.class)
	public void testCrear(String ced, String nom, String tel, String dir, String msj) {
		paginaCli = PageObjectFactory.crearPaginaCliente();
		String msjReps = paginaCli.crearCliente(ced, nom, dir, tel);
		assertEquals(msjReps, msj);

		if (msjReps.equals("Cliente registrado con exito")) {
			assertTrue(paginaCli.eliminarCliente(ced));
			ReporterUtil.getInstance().agregarScreen();
		}

	}

	@AfterTest
	public void logout() {
		login.logOut();
		ReporterUtil.getInstance().agregarScreen();

	}

}
