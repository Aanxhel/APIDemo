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
public class IkRequestCard {

	private Integer cveTopic;
	private String topicName;
	private Integer cveCard;
	private String cardText;
	private String pathImage;
	private String pathSound;
	private String pathVideo;
	private String pathMeaning;
	
	
}
