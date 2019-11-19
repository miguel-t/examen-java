package com.alMundo.helper;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Configuration {

	private static final Logger LOGGER =  LoggerFactory.getLogger(Configuration.class);

	private static final String FILE = "application.properties";
	
	private Integer director ;
	private Integer operador;
	private Integer supervisor;
	private Integer thread;
	
	private Properties properties = null;
	
	public Configuration() {
		Properties props = this.getAllPropertie();
	
		this.thread = Integer.parseInt(props.getProperty(BackendConstant.APP_COUNT_THREAD));
		this.operador = Integer.parseInt(props.getProperty(BackendConstant.APP_OPERATOR));
		this.supervisor = Integer.parseInt(props.getProperty(BackendConstant.APP_SUPERVISOR));
		this.director = Integer.parseInt(props.getProperty(BackendConstant.APP_DIRECTOR));
	}

	public Configuration(int operador, int director, int supervisor,int thread) {
		this.thread = thread;
		this.operador = operador;
		this.supervisor = supervisor;
		this.director = director;
	}
		
	public Integer getDirector() {
		return director;
	}

	public void setDirector(Integer director) {
		this.director = director;
	}

	public Integer getOperador() {
		return operador;
	}

	public void setOperador(Integer operador) {
		this.operador = operador;
	}

	public Integer getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Integer supervisor) {
		this.supervisor = supervisor;
	}

	public Integer getThread() {
		return thread;
	}

	public void setThread(Integer thread) {
		this.thread = thread;
	}

	private Properties getAllPropertie(){
		
		if(this.properties != null) {
			return  properties;
		}
		
		InputStream resourceStream = null;
		this.properties = new Properties();
		ClassLoader loader =  getClass().getClassLoader();
		try {
			resourceStream = loader.getResourceAsStream(FILE);
			properties.load(resourceStream);
		} catch (Exception e) {
			LOGGER.error("No se pudo cargar archivo properties {}", e);
		} finally {
			if (resourceStream != null) {
				try {
					resourceStream.close();
				} catch (Exception e) {
					LOGGER.error("Error al cerrar archivo de configuracion {}", e.getMessage());
				}
			}
		}
		return this.properties;
	}
}
