/**
 * 
 */
package com.ikon.process.json.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author icb_ipsra
 *
 */
@Data 
@JsonIgnoreProperties
public class RequestDatosFameJson {
	
	private int cveModulo;
	private int cveTopic;
	
}


