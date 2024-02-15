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
public class CatHeaderDto {

	private Integer cveHeader;
	private String desHeader;

	/**
	 * @param cveHeader
	 * @param desHeader
	 */
	public CatHeaderDto(Integer cveHeader, String desHeader) {
		super();
		this.cveHeader = cveHeader;
		this.desHeader = desHeader;
	}
	
}
