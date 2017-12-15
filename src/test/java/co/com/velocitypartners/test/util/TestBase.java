package co.com.velocitypartners.test.util;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.LogStatus;

import co.com.velocitypartners.prueba.util.DriverUtilities;
import co.com.velocitypartners.prueba.util.ReporterUtil;

public abstract class TestBase {

	protected WebDriver driver;
	private static ReporterUtil reporter;

	@BeforeSuite
	public void beforeSuite() {
		reporter =  ReporterUtil.getInstance();
		reporter.crearREporte("reportes");
	}

	@BeforeTest(alwaysRun = true)
	public void beforeClass() {
		driver = DriverUtilities.getDriver();
		init();
	}

	public abstract void init() ;
	

	
	
	
	@BeforeMethod
	public void nameBefore(Method method,ITestContext ctx) {
		System.out.println("Test name: " + method.getName());
		reporter.iniciarTest(method.getName());
		
		
		
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		Object[] params=result.getParameters();
		StringBuilder str=new StringBuilder("PARAMETROS DE ENTRADA<br/>");
		for (Object parameter : params) {
			str.append("param").append("=").append(parameter).append("<br/>");
			
		}
		reporter.log(str.toString());
		reporter.agregarScreen();
		System.out.println("method name:" + result.getMethod().getMethodName());
		if (result.isSuccess()) {
			reporter.log(LogStatus.PASS, "OK", null);
		} else {
			if (result.getThrowable() != null) {
				reporter.log(LogStatus.ERROR, "Excepcion", result.getThrowable());
			}else{
				reporter.log(LogStatus.FAIL, "Fallo", null);
			}
		}
		reporter.finalizarTest();
	}
}
