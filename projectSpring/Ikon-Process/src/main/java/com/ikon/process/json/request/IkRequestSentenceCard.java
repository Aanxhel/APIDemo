/**
 * 
 */
package com.ikon.process.json.request;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ikon.process.dto.IkCardDto;

import lombok.Data;

/**
 * @author icb_ipsra
 *
 */
@Data
@JsonIgnoreProperties
public class IkRequestSentenceCard {

	private Integer cveSentence;
	private String sentenceName;
	private String pathSound;
	private String pathVideo;
	private String pathInfo;
	private ArrayList<IkCardDto> cards;
	
}
