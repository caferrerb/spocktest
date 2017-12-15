package co.com.velocitypartners.test.casos;

import org.junit.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import co.com.velocitypartners.prueba.pageobjects.PageObjectFactory;
import co.com.velocitypartners.prueba.pageobjects.definiciones.ILoginPO;
import co.com.velocitypartners.prueba.util.PageObject;
import co.com.velocitypartners.prueba.util.ReporterUtil;
import co.com.velocitypartners.test.dataprovider.LoginDataProvider;
import co.com.velocitypartners.test.util.TestBase;

public class TestLogin extends TestBase {

	private static ILoginPO loginPage;

	public void init() {
		loginPage = PageObjectFactory.crearPaginaLogin();
	}

	@org.testng.annotations.Test(dataProvider = "logindata", dataProviderClass = LoginDataProvider.class)
	public void loginTest(String user, String pass, String msj) {
		String resp = loginPage.login(user, pass);
		Assert.assertEquals(resp, msj);
		if (resp.equals("Inicio de sesion exitoso!!!!")) {
			loginPage.logOut();
		}
		ReporterUtil.getInstance().agregarScreen();

	}
}
