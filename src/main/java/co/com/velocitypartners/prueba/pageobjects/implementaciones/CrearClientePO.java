package co.com.velocitypartners.prueba.pageobjects.implementaciones;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import co.com.velocitypartners.prueba.modelo.ClienteDTO;
import co.com.velocitypartners.prueba.pageobjects.definiciones.IClientePO;
import co.com.velocitypartners.prueba.util.PageObject;

public class CrearClientePO extends PageObject implements IClientePO {

	public CrearClientePO() {
		super();
	}

	@Override
	public void ir() {
		//navegar("banco-web/paginas/banca/crearcliente.jsf");
		WebElement menu = $(By.xpath(".//a[text()='Crear cliente']"));
		menu.click();
	}

	public String crearCliente(String cedula, String nombre, String direccion, String tel) {

		WebElement tfced = $(By.id("tfced"));
		WebElement tfnom = $(By.id("tfnom"));
		WebElement tfdir = $(By.id("tfdir"));
		WebElement tftel = $(By.id("tftel"));

		tfced.sendKeys(cedula);
		tfnom.sendKeys(nombre);
		tfdir.sendKeys(direccion);
		tftel.sendKeys(tel);

		WebElement btnCrear = $(By.id("btnCrear"));
		btnCrear.click();

		WebElement facesMessage = $(By.xpath(".//*[@id='facesMessage']/div/ul/li/span"));
		return facesMessage.getText();

	}

	public ClienteDTO seleccionarCliente(String cedula) {

		int r = buscarFilaCLiente(cedula);
		if (r > 0) {

			WebElement boton = $(By.id("tabla:" + r + ":btnSeleccione"));
			boton.click();
			WebElement tfced = $(By.id("tfced"));
			WebElement tfnom = $(By.id("tfnom"));
			WebElement tfdir = $(By.id("tfdir"));
			WebElement tftel = $(By.id("tftel"));

			ClienteDTO cliente = new ClienteDTO();
			cliente.setCedula(tfced.getText());
			cliente.setNombre(tfnom.getText());
			cliente.setTelefono(tftel.getText());
			cliente.setDireccion(tfdir.getText());

			return cliente;
		} else {
			return null;
		}

	}

	public boolean eliminarCliente(String cedula) {

		int r = buscarFilaCLiente(cedula);
		if (r >= 0) {

			WebElement boton = $(By.id("tabla:" + r + ":btnSeleccionemod"));
			boton.click();
			$(By.id("j_idt47")).click();
			WebElement facesMessage = $(By.xpath(".//*[@id='facesMessage']/div/ul/li/span"));
			String msj= facesMessage.getText();
			return "Cliente borrado".equals(msj);
			//return true;
		}else{
			return false;
		}
	}

	private int buscarFilaCLiente(String cedula) {
		ElementsCollection clienteFilas = $$(By.xpath(".//*[@id='tabla']/div/table/tbody/tr"));
		int i = 0;
		int r = -1;
		for (SelenideElement selenideElement : clienteFilas) {

			WebElement filaCed = selenideElement.findElement(By.xpath("td[1]"));
			if (filaCed.getText().equals(cedula)) {
				r = i;
				break;
			}
			i++;
		}
		return r;
	}

}
