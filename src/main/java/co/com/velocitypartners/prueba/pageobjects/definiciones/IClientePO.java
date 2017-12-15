package co.com.velocitypartners.prueba.pageobjects.definiciones;

import co.com.velocitypartners.prueba.modelo.ClienteDTO;

public interface IClientePO {

	public String crearCliente(String cedula, String nombre, String direccion, String tel);

	public ClienteDTO seleccionarCliente(String cedula);
	
	public boolean eliminarCliente(String cedula);

}
