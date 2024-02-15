/**
 * 
 */
package com.ikon.process.dao;

import java.util.ArrayList;

import com.ikon.process.dto.IkCatTopicDto;

/**
 * @author icb_ipsra
 *
 */
public interface IkTopicDao {

	
	/***
	 * This method, allow us insert the record  and get a sequence of the topic 
	 * @param topicName
	 * @return
	 * @throws Exception
	 */
	Integer insertTopic( String topicName ) throws Exception;
	
	/***
	 * Obtiene un listado de registros para generar el Combo
	 * @return
	 * @throws Exception
	 */
	ArrayList<IkCatTopicDto> obtieneTopic() throws Exception;
}

