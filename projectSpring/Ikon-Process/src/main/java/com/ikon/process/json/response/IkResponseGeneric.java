/**
 * 
 */
package com.ikon.process.json.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author icb_ipsra
 *
 */
@Data
@JsonIgnoreProperties
public class IkResponseGeneric {

	private int code;
	private String message;
	
	
}
