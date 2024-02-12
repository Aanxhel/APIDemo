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
public class IkModSessionCardsDto {

	private Integer cveCard;
	private String textCard;
	private String imagenCard;
	private String soundCard;
	private String meaningCard;
	private Integer cvetopic;
	private String topicName;
	
	/**
	 * @param cveCard
	 * @param textCard
	 * @param imagenCard
	 * @param soundCard
	 * @param meaningCard
	 * @param topicName
	 */
	public IkModSessionCardsDto(Integer cveCard, String textCard, String imagenCard, String soundCard,
			String meaningCard,  Integer cvetopic, String topicName) {
		super();
		this.cveCard = cveCard;
		this.textCard = textCard;
		this.imagenCard = imagenCard;
		this.soundCard = soundCard;
		this.meaningCard = meaningCard;
		this.cvetopic = cvetopic;
		this.topicName = topicName;
	}
	
	
	
	
}
