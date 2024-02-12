/**
 * 
 */
package com.ikon.process.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author icb_ipsra
 *
 */
@Data
@JsonIgnoreProperties
public class IkModSessionHeaderDto {

	private String moduleName;
	private String sessionName;
	private Integer cveSentence;
	private String sentenceName;
	private String sentenceOrder;
	private String audioSent;
	private String videoSent;
	private String infoSent;
	
	
	/**
	 * @param moduleName
	 * @param sessionName
	 * @param cveSentence
	 * @param sentenceName
	 * @param sentenceOrder
	 * @param audioSent
	 * @param videoSent
	 * @param infoSent
	 */
	public IkModSessionHeaderDto(String moduleName, String sessionName, Integer cveSentence, String sentenceName,
			String sentenceOrder, String audioSent, String videoSent, String infoSent) {
		super();
		this.moduleName = moduleName;
		this.sessionName = sessionName;
		this.cveSentence = cveSentence;
		this.sentenceName = sentenceName;
		this.sentenceOrder = sentenceOrder;
		this.audioSent = audioSent;
		this.videoSent = videoSent;
		this.infoSent = infoSent;
	}


	/**
	 * Este objeto tiene la finalidad de separar las sentencias con el encabezado
	 * @param cveSentence
	 * @param sentenceName
	 * @param sentenceOrder
	 * @param audioSent
	 * @param videoSent
	 * @param infoSent
	 */
	public IkModSessionHeaderDto(Integer cveSentence, String sentenceName, String sentenceOrder, String audioSent,
			String videoSent, String infoSent) {
		super();
		this.cveSentence = cveSentence;
		this.sentenceName = sentenceName;
		this.sentenceOrder = sentenceOrder;
		this.audioSent = audioSent;
		this.videoSent = videoSent;
		this.infoSent = infoSent;
	}
	
	
	
	
	
}
