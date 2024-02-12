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
public class IkCardDto {
	private Integer cveCard;
	private String cardText;
	private String topicName;
	

	
}
