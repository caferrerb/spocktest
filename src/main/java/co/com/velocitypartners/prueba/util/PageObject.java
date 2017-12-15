package co.com.velocitypartners.prueba.util;
import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.WebDriver;

public abstract class PageObject {
	
	private static String URL_BASE="";
	protected WebDriver driver;
	protected  String url;
	
	
	
	public PageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public PageObject() {

	}
	
	public PageObject( String url) {
		navegar(url);
	}
	
	public void navegar(String url){

		this.url=url;
		ir();
	}
	
	public  void ir(){
		driver.get(url);
	}
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
