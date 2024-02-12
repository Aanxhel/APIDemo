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
public class IkModSessionDto {

	private Integer cveSentence;
	private String sentenceName;
	private String sentenceOrder;
	private String audioSent;
	private String videoSent;
	private String infoSent;
	
	

	/**
	 * Este objeto tiene la finalidad de separar las sentencias con el encabezado
	 * @param cveSentence
	 * @param sentenceName
	 * @param sentenceOrder
	 * @param audioSent
	 * @param videoSent
	 * @param infoSent
	 */
	public IkModSessionDto(Integer cveSentence, String sentenceName, String sentenceOrder, String audioSent,
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
