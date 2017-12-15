package co.com.velocitypartners.prueba.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import com.codeborne.selenide.Selenide;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.NetworkMode;
import com.relevantcodes.extentreports.ReporterType;

public class ReporterUtil {

	private ExtentReports report;
	private ExtentTest test;
	private static ReporterUtil instance;
	private String path;

	private ReporterUtil() {
	}

	public void crearREporte(String ruta) {
		String folder = ruta + File.separatorChar + new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date());
		new File(folder).mkdirs();
		// ExtentReports extent = new ExtentReports("file-path",
		// NetworkMode.OFFLINE);
		report = new ExtentReports(folder + File.separatorChar + "reporte.html", NetworkMode.OFFLINE);
		path = folder;
	}

	public void agregarImagen(File capture) {
		if (test != null) {
			File imagen = new File(path + File.separatorChar + capture.getName());
			try {
				FileUtils.copyFile(capture, imagen);
				String scree = test.addScreenCapture(imagen.getAbsolutePath());
				test.log(LogStatus.INFO, scree);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void agregarScreen() {
		agregarImagen(new File(Selenide.screenshot("screeshot" + System.currentTimeMillis())));
	}

	public void iniciarTest(String titulo) {
		if (report != null) {
			test = report.startTest(titulo, "Sample description");
		}
	}

	public void log(String log) {
		if (test != null) {
			test.log(LogStatus.INFO, log);
		}
	}

	public void log(LogStatus tipo, String log, Throwable t) {
		if (test != null) {
			if (tipo == LogStatus.ERROR) {
				test.log(tipo, log, t);
			} else {
				test.log(tipo, log);
			}
		}
	}

	public void finalizarTest() {
		if (test != null) {
			report.endTest(test);
			report.flush();
		}
	}

	public static ReporterUtil getInstance() {
		if (instance == null) {
			instance = new ReporterUtil();
		}
		return instance;
	}
}
