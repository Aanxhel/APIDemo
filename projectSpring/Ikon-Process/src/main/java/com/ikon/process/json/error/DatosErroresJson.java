/**
 * 
 */
package com.ikon.process.json.error;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author IsraelC
 * 
 */
@Data
@JsonIgnoreProperties
public class DatosErroresJson {
	
	private ArrayList<ErroresJson>	errores;

}
