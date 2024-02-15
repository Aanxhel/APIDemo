/**
 * 
 */
package com.ikon.process.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ikon.process.service.IkActivityLogNeg;

/**
 * @author icb_ipsra
 *
 */
@RestController
@RequestMapping(value="/v1/")
public class IkActivityLogController {

	private static final Logger LOGG = LoggerFactory.getLogger(IkActivityLogController.class);
	
	@Autowired
	private IkActivityLogNeg activityLogNeg;
	
	@CrossOrigin(origins = "*", maxAge = 3600 , methods = {RequestMethod.POST})
	@RequestMapping(value = "log/activity/", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> insertActivity ( @RequestBody String requestActivityMonitoryJson ) {
		ResponseEntity<Object>  entity = null;
		LOGG.info("[::::::::::. { }  .::::::::]", "insertActivity");
		entity = this.activityLogNeg.insertActivityLog(requestActivityMonitoryJson);
		
		return entity;
	} 
	
	/***
	 * Permite obtener todas las tarjetas pertenecientes a un tema
	 * @param cveTopic
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600 , methods = {RequestMethod.GET} )
	@RequestMapping(value = "log/all", params = {"record"},  method = RequestMethod.GET, produces = "application/json")

	public ResponseEntity<Object> getActivityLog( @RequestParam(value="record", required = true) int record ) {
		ResponseEntity<Object>  entity = null;
		LOGG.info("[::::::::::. {},{},{} .::::::::]", "Getting allgetActivityLogc wiht records"," ", record);
		entity = this.activityLogNeg.getAllActivityLog(record);
		
		return entity;
	}
	
	
	
}
