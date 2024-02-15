/**
 * 
 */
package com.ikon.process.json.request;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ikon.process.dto.CompModuleDto;

import lombok.Data;

/**
 * @author icb_ipsra
 *
 */
@Data
@JsonIgnoreProperties
public class RequestCompModuleJson {

	private String movement;
	private ArrayList<CompModuleDto> data;
	
}
