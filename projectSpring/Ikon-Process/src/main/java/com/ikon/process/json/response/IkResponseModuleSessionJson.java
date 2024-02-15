/**
 * 
 */
package com.ikon.process.json.response;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ikon.process.dto.IkModSessionCardsDto;
import com.ikon.process.dto.IkModSessionDto;

import lombok.Data;

/**
 * @author icb_ipsra
 *
 */
@Data
@JsonIgnoreProperties
public class IkResponseModuleSessionJson {

	private String moduleName;
	private String sessionName;
	private ArrayList<IkModSessionDto> sentences;
	private ArrayList<IkModSessionCardsDto> cards;
	
}
