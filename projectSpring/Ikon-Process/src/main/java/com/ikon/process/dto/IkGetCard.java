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
public class IkGetCard {

	private Integer cveTopic;
	private String  topicName;
	private Integer cveCard;
	private String cardText;
	private String pathImage;
	private String pathSound;
	private String pathVideo;
	private String pathMeaning;
	/**
	 * @param cveTopic
	 * @param topicName
	 * @param cveCard
	 * @param cardText
	 * @param pathImage
	 * @param pathSound
	 * @param pathVideo
	 * @param pathMeaning
	 */
	public IkGetCard(Integer cveTopic, String topicName, Integer cveCard, String cardText, String pathImage,
			String pathSound, String pathVideo, String pathMeaning) {
		super();
		this.cveTopic = cveTopic;
		this.topicName = topicName;
		this.cveCard = cveCard;
		this.cardText = cardText;
		this.pathImage = pathImage;
		this.pathSound = pathSound;
		this.pathVideo = pathVideo;
		this.pathMeaning = pathMeaning;
	}
	
	
	
	
	
}
