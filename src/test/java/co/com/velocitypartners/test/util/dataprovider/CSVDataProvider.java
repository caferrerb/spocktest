package co.com.velocitypartners.test.util.dataprovider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

/**
 * 
 * Clase responsable de cargarr el proovedor de datos de un archivo csv <br>
 * 
 * @author Camilo Andres Ferrer Bustos<br>
 * 
 * @date 24/10/2017
 * @version 1.0
 */
public class CSVDataProvider implements IDataProvider {

	public static String RUTA = "dataprovider";
	
	public Object[][] obtenerDatos(String nombreProveedor, String test) throws Exception {
		List<String[]> lista = new ArrayList<String[]>();
		File file = new File(RUTA + File.separatorChar + test+".csv");
		try (BufferedReader entrada = new BufferedReader(new FileReader(file));) {
			String linea = "";
			while ((linea = entrada.readLine()) != null) {
				String data[] = linea.split(";");
				lista.add(data);
			}

			Object[][] res = new Object[lista.size()][];
			int i = 0;
			for (String[] data : lista) {
				res[i] = new Object[data.length];
				for (int j = 0; j < data.length; j++) {
					res[i][j] = data[j];
				}

				i++;
			}

			return res;
		}
	}

}
