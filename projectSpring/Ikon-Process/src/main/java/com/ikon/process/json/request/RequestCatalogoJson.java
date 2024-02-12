/**
 * 
 */
package com.ikon.process.json.request;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ikon.process.dto.CatDato;

import lombok.Data;

/**
 * @author icb_ipsra
 *
 */
@Data
@JsonIgnoreProperties
public class RequestCatalogoJson {
	
	private String movimiento;
	private ArrayList<CatDato> dato;
	
}
