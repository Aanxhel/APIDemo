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
public class IkCardByTopicDto {

	private Integer cveCard;
	private String cardText;
	private String pathImage;
	private String pathSound;
	private String pathVideo;
	private String pathMeaning;
	/**
	 * @param cveCard
	 * @param cardText
	 * @param pathImage
	 * @param pathSound
	 * @param pathVideo
	 * @param pathMeaning
	 */
	public IkCardByTopicDto(Integer cveCard, String cardText, String pathImage, String pathSound, String pathVideo,
			String pathMeaning) {
		super();
		this.cveCard = cveCard;
		this.cardText = cardText;
		this.pathImage = pathImage;
		this.pathSound = pathSound;
		this.pathVideo = pathVideo;
		this.pathMeaning = pathMeaning;
	}
	/**
	 * 
	 */
	public IkCardByTopicDto() {
		super();
	}
	
	
	
}
