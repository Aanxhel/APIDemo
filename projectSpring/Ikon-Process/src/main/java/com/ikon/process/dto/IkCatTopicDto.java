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
public class IkCatTopicDto {

	private int cveTopic;
	private String topicName;
	/**
	 * @param cveTopic
	 * @param topicName
	 */
	public IkCatTopicDto(int cveTopic, String topicName) {
		super();
		this.cveTopic = cveTopic;
		this.topicName = topicName;
	}
	
	
}
