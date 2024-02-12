/**
 * 
 */
package com.ikon.process.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 
 * @author icb_ipsra
 * @since Septiembre, 2021
 *
 */
@Data
@JsonIgnoreProperties
public class CompModuleDto {

	private Integer cveModule;
	private String moduleName;
	private String moduleDesc;
	private Integer totalSessiones;
	private Integer cveSession;
	private String sessionName;
	
	
	
	
}
