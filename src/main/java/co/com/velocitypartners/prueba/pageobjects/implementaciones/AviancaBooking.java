package co.com.velocitypartners.prueba.pageobjects.implementaciones;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import co.com.velocitypartners.prueba.pageobjects.definiciones.IAviancaBuscarVuelo;
import co.com.velocitypartners.prueba.util.PageObject;

public class AviancaBooking extends PageObject  implements IAviancaBuscarVuelo{

	@Override
	public boolean buscarVuelo(String ciudadOrigen, String ciudaDestino, String fechaInicio, String fechaFinal) {

		WebElement bkOrigen = driver.findElement(By.id("bkOrigen"));
		WebElement bkDestino = driver.findElement(By.id("bkDestino"));

		bkOrigen.click();
		bkOrigen.sendKeys(ciudadOrigen);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		////*[contains(text(),'%s')][contains(@id,'id')]
		WebElement clickCiudadOrigen = driver
				.findElement(By.xpath(String.format("//*[contains(text(),'%s')][contains(@id,'id')]", ciudadOrigen)));
		clickCiudadOrigen.click();
		

		bkDestino.click();
		bkDestino.sendKeys(ciudaDestino);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement clickCiudaDest = driver
				.findElement(By.xpath(String.format("//*[contains(text(),'%s')][contains(@id,'id')]", ciudaDestino)));
		clickCiudaDest.click();

		
		WebElement bkFechaIda = driver.findElement(By.id("bkFechaIda"));
		WebElement bkFechaRegreso = driver.findElement(By.id("bkFechaRegreso"));
		
		bkFechaIda.clear();
		bkFechaIda.sendKeys(fechaInicio);
		
		bkFechaRegreso.clear();
		bkFechaRegreso.sendKeys(fechaFinal);

		WebElement boton = driver.findElement(By.cssSelector("input[type='submit'][title='consultar' i]"));
		boton.click();
		
		
		driver.switchTo().frame("FrameAmadeus");
		WebElement elem=$(By.id("button-tripsummary-farereview"));
		System.out.println("TEXTO="+elem.getText());
		return false;
	}
	
	public void ir() {
		navegar("https://www.avianca.com/");

	}

}
