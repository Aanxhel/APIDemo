/**
 * 
 */
package com.ikon.process.json.response;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ikon.process.dto.IkSessionModDto;

import lombok.Data;

/**
 * @author icb_ipsra
 *
 */
@Data
@JsonIgnoreProperties
public class IkResponseModSessionJson {
	
	private Integer cveModule;
	private String moduleName;
	private String moduleDesc;
	private Integer totalSessiones;
	
	private ArrayList<IkSessionModDto> sesiones;
	
}
