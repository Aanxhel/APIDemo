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
public class IkSentenceCardDto {

	private Integer cveSentence; 
	private String sentenceOrder; 
	private String sentenceName; 
	private String pathSound; 
	private String pathVideo; 
	private String pathInfo;
	/**
	 * @param cveSentence
	 * @param sentenceOrder
	 * @param sentenceName
	 * @param pathSound
	 * @param pathVideo
	 * @param pathInfo
	 */
	public IkSentenceCardDto(Integer cveSentence, String sentenceOrder, String sentenceName, String pathSound,
			String pathVideo, String pathInfo) {
		super();
		this.cveSentence = cveSentence;
		this.sentenceOrder = sentenceOrder;
		this.sentenceName = sentenceName;
		this.pathSound = pathSound;
		this.pathVideo = pathVideo;
		this.pathInfo = pathInfo;
	}
	/**
	 * 
	 */
	public IkSentenceCardDto() {
		super();
	}
	
	
	
	
	
}
