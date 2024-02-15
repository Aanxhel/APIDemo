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
public class IkCardsSentencesDto {

	private Integer cveSentence;
	private String sentenceName;
	private Integer cveCard;
	private String cardText;
	private String topicName;
	private String pathSound;
	private String pathVideo;
	private String pathInfo;
	
	/**
	 * @param cveSentence
	 * @param sentenceName
	 * @param cveCard
	 * @param cardText
	 */
	public IkCardsSentencesDto(Integer cveSentence, String sentenceName, Integer cveCard, String cardText) {
		super();
		this.cveSentence = cveSentence;
		this.sentenceName = sentenceName;
		this.cveCard = cveCard;
		this.cardText = cardText;
	}

	/**
	 * @param cveSentence
	 * @param sentenceName
	 * @param cveCard
	 * @param cardText
	 * @param desSound
	 * @param desVideo
	 * @param desInfor
	 */
	public IkCardsSentencesDto(Integer cveSentence, String sentenceName, Integer cveCard, String cardText,
			String topicName, String pathSound, String pathVideo, String pathInfo) {
		super();
		this.cveSentence = cveSentence;
		this.sentenceName = sentenceName;
		this.cveCard = cveCard;
		this.cardText = cardText;
		this.topicName = topicName;
		this.pathSound = pathSound;
		this.pathVideo = pathVideo;
		this.pathInfo = pathInfo;
	}
	
	/**
	 * @param cveSentence
	 * @param sentenceName
	 * @param cveCard
	 * @param cardText
	 * @param desSound
	 * @param desVideo
	 * @param desInfor
	 */
	public IkCardsSentencesDto(Integer cveSentence, String sentenceName, Integer cveCard, String cardText,
			String pathSound, String pathVideo, String pathInfo) {
		super();
		this.cveSentence = cveSentence;
		this.sentenceName = sentenceName;
		this.cveCard = cveCard;
		this.cardText = cardText;
		this.pathSound = pathSound;
		this.pathVideo = pathVideo;
		this.pathInfo = pathInfo;
	}
	
	
	
	
	
	
}
