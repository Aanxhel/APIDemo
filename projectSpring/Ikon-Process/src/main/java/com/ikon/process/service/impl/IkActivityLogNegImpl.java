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

import com.ikon.process.dao.IkActivityLogDao;
import com.ikon.process.dto.IkActivityDto;
import com.ikon.process.json.error.DatosErroresJson;
import com.ikon.process.json.error.ErroresJson;
import com.ikon.process.json.request.IKRequestActivityLogJson;
import com.ikon.process.json.response.IkResponseGeneric;
import com.ikon.process.service.IkActivityLogNeg;
import com.ikon.process.util.Constantes;
import com.ikon.process.util.IkConvierteJsonObjeto;
import com.ikon.process.util.IkUtileria;
import com.ikon.process.util.IkValidaRequest;
import com.ikon.process.util.IkValidacionActivityLog;

/**
 * @author icb_ipsra
 *
 */
@Service("IkActivityLogNeg")
public class IkActivityLogNegImpl  implements IkActivityLogNeg {

	private static final Logger LOGG = LoggerFactory.getLogger(IkActivityLogNegImpl.class);
	
	@Autowired
	private IkActivityLogDao activityLogDao;
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.service.IkActivityLogNeg@insertActivityLog(java.lang.String)
	 */
	public ResponseEntity<Object> insertActivityLog (String requestActivityMonitoryJson ){
		ResponseEntity<Object> entity = null;
		LOGG.info("[:::::::::  {}  :::::::::]", "Insert requestActivityMonitory ");
		IKRequestActivityLogJson request = new IKRequestActivityLogJson();
		DatosErroresJson errroJson = new DatosErroresJson();
		IkValidacionActivityLog validaError = new IkValidacionActivityLog();
		
		if ( !IkValidaRequest.validaRequestActivityLog(requestActivityMonitoryJson) ) {
			errroJson = IkUtileria.generaSegmentoError(Constantes.CODIGO_400_5, Constantes.MENSAJE_400_5, "");
			entity = ResponseEntity.badRequest().body(errroJson);	
			LOGG.error("[:::::::::::: { } :::::::::::]", "JSON structure error");
		} else {
			request = IkConvierteJsonObjeto.convierteActivityLog(requestActivityMonitoryJson);
			errroJson = validaError.validaErroresSentenceCard(request);
			
			if (errroJson.getErrores() != null && !errroJson.getErrores().isEmpty()) {
				entity = ResponseEntity.badRequest().body(errroJson);
				LOGG.error("[::::::::: { }  :::::::::]", "It has errors in the request");
			} else {
				entity = this.insertaActivityLog(request);
			}
		}
		
		return entity;
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.service.IkSentenceNeg@getCatAllSentencesCards()
	 */
	public ResponseEntity<Object> getAllActivityLog (Integer totalRecords ) {
		ResponseEntity<Object> entity = null;
		LOGG.info("[:::::::::  {}  :::::::::]", "Get all the records Activity Log");
		DatosErroresJson errroJson = new DatosErroresJson();
		
		if ( totalRecords == null || totalRecords <= 0 ) {
			errroJson = IkUtileria.generaSegmentoError(Constantes.CODIGO_400_1, Constantes.MENSAJE_400_1_6, "totalRecords");
			entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errroJson);
		} else {
			entity = this.getActivityLog(totalRecords);
		}
		
		return entity;
	}
	
	
	/***
	 * Obtiene todos los registros de acuerdo al total de registros
	 * @param totalRecords
	 * @return
	 */
	private ResponseEntity<Object>  getActivityLog (Integer totalRecords)  {
		ResponseEntity<Object> entity = null;
		ArrayList<IkActivityDto> arrayListActivity = new ArrayList<>(); 
		DatosErroresJson errroJson = new DatosErroresJson();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		
		try {
			arrayListActivity = this.activityLogDao.getAllActivityLog(totalRecords);
			
			if ( !arrayListActivity.isEmpty() && arrayListActivity.size() > Constantes.VALOR_INT_CERO ) {
				entity = ResponseEntity.status(HttpStatus.OK).body(arrayListActivity);
			} else {
				entity = ResponseEntity.status(HttpStatus.NO_CONTENT).body(arrayListActivity); 
			}
			
		} catch (Exception e) {
			errores.add(new ErroresJson(Constantes.ERROR_COD_500, Constantes.ERROR_COD_500.concat(e.getMessage())));
			errroJson.setErrores(errores);
			entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errroJson);
		}
		
		return entity;
	}
	
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.service.IkActivityLogNeg@insertActivityLog(java.lang.String)
	 */
	private ResponseEntity<Object> insertaActivityLog  ( IKRequestActivityLogJson request ) {
		ResponseEntity<Object> entity = null;
		int respActivity = -1;
		DatosErroresJson errroJson = new DatosErroresJson();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		IkResponseGeneric responseGeneric = new IkResponseGeneric();
		
		IkActivityDto activityDto = new IkActivityDto();
		activityDto.setNomUsername(request.getNomUsername().toUpperCase());
		activityDto.setTimStart(request.getTimStart().toUpperCase());
		activityDto.setTimEnd(request.getTimEnd().toUpperCase());
		activityDto.setDesModule(request.getDesModule().toUpperCase());
		activityDto.setDesSession(request.getDesSession().toUpperCase());
		activityDto.setNumSentences(request.getNumSentences().toUpperCase());
		
		try {
			respActivity = this.activityLogDao.insertActivitiLog(activityDto );
			
			if ( respActivity == Constantes.VALOR_INT_CERO) {
				responseGeneric = IkUtileria.generaResponse(Constantes.I_RESPONSE_OK, Constantes.S_RESPONSE_INSERT_OK, request.getNomUsername());
				entity = ResponseEntity.status(HttpStatus.OK).body(responseGeneric);
			}
			
		} catch (Exception e) {
			errores.add(new ErroresJson(Constantes.ERROR_COD_500, Constantes.ERROR_COD_500.concat(e.getMessage())));
			errroJson.setErrores(errores);
			entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errroJson);
		}
		
		return entity;
	}
	
	
	
	
	
}
