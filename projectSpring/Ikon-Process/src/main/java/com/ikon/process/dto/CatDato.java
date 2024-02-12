/**
 * 
 */
package com.ikon.process.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author icb_ipsra
 *
 */
@Data
@JsonIgnoreProperties
public class CatDato {

	private int clave;
	private String descripcion;
	
}
