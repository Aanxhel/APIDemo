/**
 * 
 */
package com.ikon.process.dao;

import java.util.ArrayList;

import com.ikon.process.dto.IkActivityDto;

/**
 * @author icb_ipsra
 *
 */
public interface IkActivityLogDao {

	/**
	 * allow us to insert all params to the  activity 
	 * @param activityDto
	 * @return
	 * @throws Exception
	 */
	Integer insertActivitiLog (IkActivityDto activityDto) throws Exception;
	
	/***
	 * We obtain all the records for the activity according to the number of records 
	 * @param totalRegistros
	 * @return
	 * @throws Exception
	 */
	ArrayList<IkActivityDto> getAllActivityLog (int totalRegistros )  throws Exception;
	
}
