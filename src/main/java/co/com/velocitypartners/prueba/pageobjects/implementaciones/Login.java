package co.com.velocitypartners.prueba.pageobjects.implementaciones;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import co.com.velocitypartners.prueba.pageobjects.definiciones.ILoginPO;
import co.com.velocitypartners.prueba.util.PageObject;

import static com.codeborne.selenide.Selenide.*;

public class Login extends PageObject implements ILoginPO{

	
	
	private WebElement tfUser;
	private WebElement tfPass;
	private WebElement facesMessages;
	
	
	public Login() {
	}
	
	public String  login(String user,String pass){
		tfUser=$(By.id("tfuser"));
		tfPass=$(By.id("tfpass"));
		
		tfUser.sendKeys(user);
		tfPass.sendKeys(pass);
		
		$(By.xpath(".//*[text()='Iniciar sesion']")).click();;
		
		facesMessages=$(By.xpath(".//*[@id='facesMessage']/div/ul/li/span"));
			
		
		return facesMessages.getText();
	}
	
	public void logOut(){
		WebElement menu = $(By.xpath(".//a[text()='Cerrar sesion']"));
		menu.click();
	}

	@Override
	public void ir() {
		navegar("/banco-web/index.jsf");
		
	}
	
	
}
