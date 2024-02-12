/**
 * 
 */
package com.ikon.process.dto;

import lombok.Data;

/**
 * @author icb_ipsra
 *
 */
@Data
public class ResponseMessage {

	private String code;
	private String message;
	
	/**
	 * @param code
	 * @param message
	 */
	public ResponseMessage(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	
	
}
