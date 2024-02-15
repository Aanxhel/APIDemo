/**
 * 
 */
package com.ikon.process.json.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author IsraelC
 * v2
 */
@Data
@JsonIgnoreProperties
public class ErroresJson {

	private String codigo;
	private String 	mensaje;
	
	
	/**
	 * @param codigo
	 * @param mensaje
	 */
	public ErroresJson(String codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}





	/**
	 * 
	 */
	public ErroresJson() {
		super();
	}
	
	
}
