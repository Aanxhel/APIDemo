/**
 * 
 */
package com.ikon.process.json.request;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ikon.process.dto.IkSessionDto;

import lombok.Data;

/**
 * @author icb_ipsra
 *
 */
@Data
@JsonIgnoreProperties
public class IkRequestModuleJson {

	private Integer cveModule;
	private String moduleName;
	private String moduleDesc;
	private ArrayList<IkSessionDto> sesiones;
}
