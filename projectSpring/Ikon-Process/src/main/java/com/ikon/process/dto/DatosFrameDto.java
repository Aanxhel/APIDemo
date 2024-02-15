package com.ikon.process.dto;

import lombok.Data;

/**
 * 
 * Genera el objeto del resultado
 * para la creación de la respuesta en formato
 * Json
 * @author icb_ipsra
 *
 */
@Data
public class DatosFrameDto {

	private Integer cveframe;
	private String descripcion;
	
	
	/**
	 * @param cveframe
	 * @param descripcion
	 */
	public DatosFrameDto(Integer cveframe, String descripcion) {
		super();
		this.cveframe = cveframe;
		this.descripcion = descripcion;
	}
	
	
	
}
