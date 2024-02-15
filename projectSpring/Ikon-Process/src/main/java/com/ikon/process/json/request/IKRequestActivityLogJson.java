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
public class IKRequestActivityLogJson {

	private String nomUsername;
	private String timStart;
	private String timEnd; 
	private String desModule;
	private String desSession;
	private String numSentences;
	
	
}

