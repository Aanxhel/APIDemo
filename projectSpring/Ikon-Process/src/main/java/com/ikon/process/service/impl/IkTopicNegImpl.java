/**
 * 
 */
package com.ikon.process.service.impl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ikon.process.dao.IkTopicDao;
import com.ikon.process.dto.IkCatTopicDto;
import com.ikon.process.json.error.DatosErroresJson;
import com.ikon.process.json.error.ErroresJson;
import com.ikon.process.service.IkTopicNeg;
import com.ikon.process.util.Constantes;

/**
 * @author icb_ipsra
 *
 */
@Service("IkTopicNeg")
public class IkTopicNegImpl implements IkTopicNeg{

	private static final Logger LOGG = LoggerFactory.getLogger(IkTopicNegImpl.class);
	

	@Autowired
	private IkTopicDao ikTopicDao;
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.service.CompModuleNeg@compModule()
	 */
	public ResponseEntity<Object> getallRecords ( ) {
		ResponseEntity<Object> entity = null;
		LOGG.info("[:::::::::  {}  :::::::::]", "getallRecords TOPICS ");
		entity = this.getRecordsTopic();
		return entity; 
	}
	
	/***
	 * Registra información.
	 * @return
	 */
	private ResponseEntity<Object> getRecordsTopic () {
	
		ResponseEntity<Object> entity = null;
		ArrayList<IkCatTopicDto> cboTopic = new ArrayList<>();
		DatosErroresJson errroJson = new DatosErroresJson();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		
		try {
			cboTopic = this.ikTopicDao.obtieneTopic();
			
			if ( cboTopic.isEmpty() && cboTopic.size()== 0) {
				entity = ResponseEntity.status(HttpStatus.NO_CONTENT).body(cboTopic);
			} else {
				entity = ResponseEntity.status(HttpStatus.OK).body(cboTopic);
			}
			
			
		} catch (Exception e) {
			errores.add(new ErroresJson(Constantes.ERROR_COD_500, Constantes.ERROR_COD_500.concat(e.getMessage())));
			errroJson.setErrores(errores);
			entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errroJson);
		}
		
		return entity;
	}
	
	
}
