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

import com.ikon.process.dao.IkSentenceDao;
import com.ikon.process.dao.IkSessionDao;
import com.ikon.process.dto.IkCatSessionDto;
import com.ikon.process.dto.IkCatSessionSentenceDto;
import com.ikon.process.dto.IkSentenceDto;
import com.ikon.process.dto.IkSentencesDto;
import com.ikon.process.json.error.DatosErroresJson;
import com.ikon.process.json.error.ErroresJson;
import com.ikon.process.json.request.IkRequestSessionJson;
import com.ikon.process.json.response.IkResponseGeneric;
import com.ikon.process.service.IkSessionNeg;
import com.ikon.process.util.Constantes;
import com.ikon.process.util.IkConvierteJsonObjeto;
import com.ikon.process.util.IkUtileria;
import com.ikon.process.util.IkValidaRequest;
import com.ikon.process.util.IkValidacionSession;

/**
 * @author icb_ipsra
 *
 */
@Service("ikSessionNeg")
public class IkSessionNegImpl implements IkSessionNeg {

	private static final Logger LOGG = LoggerFactory.getLogger(IkSessionNegImpl.class);
	
	@Autowired
	private IkSessionDao ikSessionDao;
	@Autowired
	private IkSentenceDao ikSentenceDao;
	
	/*
	 * (non-Javadoc)
	 * @see com.ikon.process.service.IkSessionNeg@getCatAllSentencesCards()
	 */
	public ResponseEntity<Object> getCatAllSentencesCards ( ) {
		ResponseEntity<Object> entity = null;
		LOGG.info("[:::::::::  {}  :::::::::]", "Get all the sentences and their cards, it d");
		entity = this.getAllSession(Constantes.VALOR_INT_MENOS_UNO);
		return entity;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ikon.process.service.IkSessionNeg@movimientoSentence(java.lang.String)
	 */
	public ResponseEntity<Object> movimientoSentence (String requestSessionJson ) {
		ResponseEntity<Object> entity = null;
		DatosErroresJson errroJson = new DatosErroresJson();
		LOGG.info("[:::::::::  {}  :::::::::]", "Insert/Update movimientoSentence ");
		IkRequestSessionJson request = new IkRequestSessionJson();
		IkValidacionSession validaError = new IkValidacionSession();
		
		if ( !IkValidaRequest.validaRequestSession(requestSessionJson)) {
			errroJson = IkUtileria.generaSegmentoError(Constantes.CODIGO_400_5, Constantes.MENSAJE_400_5, "");
			entity = ResponseEntity.badRequest().body(errroJson);	
			LOGG.error("[:::::::::::: { } :::::::::::]", "JSON structure error");
		} else {
			request = IkConvierteJsonObjeto.convierteSession(requestSessionJson);
			errroJson = validaError.validaErroresSessiones(request);
			
			if (errroJson.getErrores() != null && !errroJson.getErrores().isEmpty()) {
				entity = ResponseEntity.badRequest().body(errroJson);
				LOGG.error("[::::::::: { }  :::::::::]", "It has errors in the request");
			} else {
				// TODO .- Procesando Insert
				entity = this.identifyProcess(request);
			}
		}
		
		return entity;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.service.IkSessionNeg@eliminaSession(java.lang.Integer)
	 */
	public ResponseEntity<Object> eliminaSession (Integer cveSession) {
		ResponseEntity<Object> entity = null;
		DatosErroresJson errroJson = new DatosErroresJson();
		
		if ( cveSession == null || cveSession <= 0  ) {
			errroJson = IkUtileria.generaSegmentoError(Constantes.CODIGO_400_1, Constantes.MENSAJE_400_1_6, "cveCard");
			entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errroJson);
		} else {
			entity = this.procedeEliminarSesion(cveSession);
		}
		return entity;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ikon.process.service.IkSessionNeg@getCatAllSentencesCards()
	 */
	public ResponseEntity<Object> getSessionesById(Integer cveSession) {
		ResponseEntity<Object> entity = null;
		LOGG.info("[:::::::::  {}  :::::::::]", "Get all the sentences and their cards, it d");
		entity = this.getAllSession(cveSession);
		return entity;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.ikon.process.service.IkSessionNeg@getCatAllSentencesCards()
	 */
	public ResponseEntity<Object> getSessionesNotAssociated( ) {
		ResponseEntity<Object> entity = null;
		LOGG.info("[:::::::::  {}  :::::::::]", "Get all the sentences and their cards, it d");
		entity = this.getAllSession(Constantes.VALOR_INT_MENOS_DOS);
		return entity;
	}
	
	
	/**
	 *  Metodo encargado de indentificar el proceso a ejecutar
	 * @param request
	 * @return
	 */
	private ResponseEntity<Object> identifyProcess ( IkRequestSessionJson request ){
		ResponseEntity<Object> entity = null;
		IkResponseGeneric response = new IkResponseGeneric();
		
		Boolean existeSentences = false;
		
		
		existeSentences = this.vaidaExisteSentences(request);
		
		if ( existeSentences ) {
			if ( request.getCveSession() == Constantes.VALOR_INT_CERO ) {
				LOGG.info("[:::::::::::: { } :::::::::::]", "Inserting Session - Sentences ");
				entity = this.insertSessionSentences(request);
			}
			
			if ( request.getCveSession() > Constantes.VALOR_INT_CERO ) {
				LOGG.info("[:::::::::::: { } :::::::::::]", "Update session - Sentences ");
				entity = this.updateSessionSentences(request);
			}
		} else {
			//The sentences does not exist
			response = IkUtileria.generaResponse(Constantes.I_RESPONSE_NO_EXIST, Constantes.ERROR_MESSAGE_SESSION_MENOS_6, "");
			entity = ResponseEntity.badRequest().body(response);
		}	
		
		return entity;
	}
	
	
	
	/***
	 * Valida la existencia de Sentencias, para asignarlas a una session
	 * @param request
	 * @return
	 */
	private boolean vaidaExisteSentences (IkRequestSessionJson request) {
	
		boolean exitSentences = true;
		
		try {
			for (IkSentenceDto sentence : request.getSentences()) {
				int existe = this.ikSentenceDao.existeSentence(sentence.getCveSentence());
				if ( existe != Constantes.VALOR_INT_CERO) {
					exitSentences = false;
					LOGG.info("[:::::::::: Ther is some sentence doesn't exist cveCard {} ::::::::]", sentence.getCveSentence());
					break;
				}
			}
			
		} catch (Exception e) {
			exitSentences = false;
		}
		
		return exitSentences;
	}
	
	
	
	
	/***
	 * Asigna tarjetas a una sentencia
	 * @param request
	 * @return
	 */
	private ResponseEntity<Object> insertSessionSentences  ( IkRequestSessionJson request ) {
		ResponseEntity<Object> entity = null;
		Integer sequenceSession = new Integer (Constantes.VALOR_INT_CERO);
		DatosErroresJson errroJson = new DatosErroresJson();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		IkResponseGeneric response = new IkResponseGeneric();
		
		String sessionName = request.getSessionName().trim().toUpperCase();
		String sessionDesc = request.getSessionDesc() != null && !request.getSessionDesc().equals("")
													? request.getSessionDesc().trim().toUpperCase()
													: "";
		try {
			
			sequenceSession = this.ikSessionDao.insertSession(sessionName, sessionDesc);
			
			if ( sequenceSession == Constantes.VALOR_INT_MENOS_UNO ) {
				response = IkUtileria.generaResponse(Constantes.I_RESPONSE_OK_EXIST, Constantes.S_RESPONSE_EXIST, sessionName);
				entity = ResponseEntity.badRequest().body(response);
			} else {
				
				for (IkSentenceDto sentence : request.getSentences()) {
					this.ikSessionDao.insertSessionSentence(sequenceSession, sentence.getCveSentence());
					LOGG.info("[:::::::::::: {}{}{} :::::::::::]", "Inserting session-sentence ", "cveSession:> "+ sequenceSession, " cveSentence:> " +sentence.getCveSentence());
				}
		
				response = IkUtileria.generaResponse(Constantes.I_RESPONSE_OK, Constantes.S_RESPONSE_INSERT_OK, sessionName);
				entity = ResponseEntity.status(HttpStatus.OK).body(response);
				
			}
			
		} catch (Exception e) {
			errores.add(new ErroresJson(Constantes.ERROR_COD_500, Constantes.ERROR_COD_500.concat(e.getMessage())));
			errroJson.setErrores(errores);
			entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errroJson);
		}
		
		return entity;
	}
	
	/***
	 * Asigna tarjetas a una sentencia
	 * @param request
	 * @return
	 */
	private ResponseEntity<Object> updateSessionSentences  ( IkRequestSessionJson request ) {
		ResponseEntity<Object> entity = null;
		DatosErroresJson errroJson = new DatosErroresJson();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		IkResponseGeneric response = new IkResponseGeneric();
		
		int sentenceSession = 0;

		try {
			
			String sessionName = request.getSessionName().trim().toUpperCase();
			sentenceSession = this.ikSessionDao.existSessionSentences(request.getCveSession(), sessionName);
			
			switch (sentenceSession) {
			case Constantes.VALOR_INT_CERO:
				///Apto para proceder con la modificación
				entity = this.processUpdate(request);
				break;
			case Constantes.VALOR_INT_MENOS_UNO:
				//The session does not exist
				response = IkUtileria.generaResponse(Constantes.I_RESPONSE_NO_EXIST, Constantes.ERROR_MESSAGE_SESSION_MENOS_1, request.getCveSession() +" " +request.getSessionName());
				entity = ResponseEntity.badRequest().body(response);
				break;
			case Constantes.VALOR_INT_MENOS_TRES:
				//El nombre de la session existe con otra sentencia
				response = IkUtileria.generaResponse(Constantes.I_RESPONSE_OK_EXIST, Constantes.ERROR_MESSAGE_SESSION_MENOS_3, request.getSessionName());
				entity = ResponseEntity.badRequest().body(response);
				break;	
			default:
				break;
			}
		
		} catch (Exception e) {
			errores.add(new ErroresJson(Constantes.ERROR_COD_500, Constantes.ERROR_COD_500.concat(e.getMessage())));
			errroJson.setErrores(errores);
			entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errroJson);
		}
		
		return entity;
		
	}
	
	/***
	 * Encardado de modificar la sentencia actual
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private  ResponseEntity<Object> processUpdate ( IkRequestSessionJson request ) throws Exception{
		ResponseEntity<Object> entity = null;
		IkResponseGeneric response = new IkResponseGeneric();
		
		//-- Insertando la session con el mismo numero de sentence
		this.ikSessionDao.updateSession(request.getCveSession(), request.getSessionName(), request.getSessionDesc());
		
			
		Integer sequenceSession = request.getCveSession();

		// eliminando las sentencias asignadas a la sessión y volver a ingresar las nuevas
		
		this.ikSessionDao.eliminaSessionSentences(sequenceSession);
		
		
		if (request.getSentences() != null ) {
			if ( !request.getSentences().isEmpty() && request.getSentences().size() > Constantes.VALOR_INT_CERO ) {
				for (IkSentenceDto sentence : request.getSentences()) {
					this.ikSessionDao.insertSessionSentence(sequenceSession, sentence.getCveSentence());
				}
			}
		}
	
		response = IkUtileria.generaResponse(Constantes.I_RESPONSE_OK, Constantes.S_RESPONSE_INSERT_OK,  request.getSessionName());
		entity = ResponseEntity.status(HttpStatus.OK).body(response);
		
		
		return entity;
	} 
	
	/***
	 * Obtiene todos los registros de sessiones 
	 * @return
	 */
	private ResponseEntity<Object>  getAllSession (Integer cveSession)  {
		ResponseEntity<Object> entity = null;
		DatosErroresJson errroJson = new DatosErroresJson();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		ArrayList<IkCatSessionDto> callAllSession = new ArrayList<>();
//		IkResponseGeneric response = new IkResponseGeneric();
		ArrayList<IkCatSessionSentenceDto> arraySession = new ArrayList<IkCatSessionSentenceDto>();
		
		
		try {
			
			if ( cveSession == Constantes.VALOR_INT_MENOS_UNO ) {
				callAllSession = this.ikSessionDao.obtieneSessiones();
			}
			
			if ( cveSession != null && cveSession > Constantes.VALOR_INT_CERO ) {
				callAllSession = this.ikSessionDao.obtieneSessionesById(cveSession);
			}
			
			if ( cveSession != null && cveSession == Constantes.VALOR_INT_MENOS_DOS ) {
				callAllSession = this.ikSessionDao.obtieneSessionesNotAssociated();
			}
			 
			
			if (callAllSession.isEmpty() || callAllSession.size()== Constantes.VALOR_INT_CERO ) {
				//response = IkUtileria.generaResponse (Constantes.I_RESPONSE_NO_EXIST, Constantes.S_RESPONSE_NO_EXIST,"");
//				entity = ResponseEntity.badRequest().body(response);
				entity = ResponseEntity.status(HttpStatus.NO_CONTENT).body(arraySession);
				
			} else {
				arraySession  = this.getSessionSentences(callAllSession);
				entity = ResponseEntity.status(HttpStatus.OK).body(arraySession);
			}
			
		} catch (Exception e) {
			errores.add(new ErroresJson(Constantes.ERROR_COD_500, Constantes.ERROR_COD_500.concat(e.getMessage())));
			errroJson.setErrores(errores);
			entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errroJson);
		}
		
		return entity;
	}
	
	
	/***
	 * Inserta un registro en Base de datos.
	 * @param request
	 * @param cveTopic
	 * @return
	 */
	private ResponseEntity<Object> procedeEliminarSesion( Integer cveSession) {
		
		ResponseEntity<Object> entity = null;
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		DatosErroresJson errroJson = new DatosErroresJson();
		int respEliminar = -1;
		IkResponseGeneric responseCard = new IkResponseGeneric();
		
		try {
			
			respEliminar = this.ikSessionDao.eliminaSession(cveSession);
			
			LOGG.info("[:::::::::  {}  :::::::::]", "Status delete Session " + respEliminar);
			
			
			if ( respEliminar == Constantes.VALOR_INT_CERO ) {
				responseCard = IkUtileria.generaResponse(Constantes.I_RESPONSE_OK, Constantes.S_RESPONSE_DELETE_OK, "");
				entity = ResponseEntity.status(HttpStatus.OK).body(responseCard);
				
			} else if ( respEliminar == Constantes.VALOR_INT_UNO ) {
				responseCard = IkUtileria.generaResponse(Constantes.I_RESPONSE_NO_EXIST, Constantes.ERROR_MESSAGE_SESSION_MENOS_1, "");
				entity = ResponseEntity.badRequest().body(responseCard);
			} else {
				responseCard = IkUtileria.generaResponse(Constantes.I_RESPONSE_ERROR_ASSOCIATE, Constantes.ERROR_MESSAGE_ERROR_ASSOCIATE, "Module");
				entity = ResponseEntity.badRequest().body(responseCard);
			}
			
		} catch (Exception e) {
			errores.add(new ErroresJson(Constantes.ERROR_COD_500, Constantes.ERROR_COD_500.concat(e.getMessage())));
			errroJson.setErrores(errores);
			entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errroJson);
		}
		
		return entity;
	}
	
	
	/****
	 * Genera objeto completo de todas las sessiones existentes. 
	 * @param callAllSession
	 * @return
	 */
	private ArrayList<IkCatSessionSentenceDto> getSessionSentences ( ArrayList<IkCatSessionDto> callAllSession ) {
		
		ArrayList<IkCatSessionSentenceDto> arraySession = new ArrayList<IkCatSessionSentenceDto>();
		IkCatSessionSentenceDto  catSessionSentence = null;
		ArrayList<IkSentencesDto> arraysentences  = new ArrayList<>();
		IkSentencesDto sentence = null;
		
		int registroCero = 0;
		
		
		
		for ( IkCatSessionDto ikCatSessionDto : callAllSession) {
			sentence = new IkSentencesDto();
			
			if ( registroCero == 0 ) {
				catSessionSentence = new IkCatSessionSentenceDto();
				
				catSessionSentence.setCveSession(ikCatSessionDto.getCveSession());
				catSessionSentence.setSessionName(ikCatSessionDto.getSessionName());
				catSessionSentence.setSesionDesc(ikCatSessionDto.getSesionDesc());
				catSessionSentence.setTotalSentences(ikCatSessionDto.getTotalSentences());
				
			} 
			registroCero +=1;
			
			sentence.setCveSentence(ikCatSessionDto.getCveSentence());
			sentence.setSentenceName(ikCatSessionDto.getSentenceName());
			
			if ( registroCero <  ikCatSessionDto.getTotalSentences()) {
				arraysentences.add(sentence);
			}
			if ( registroCero ==  ikCatSessionDto.getTotalSentences()) {
				arraysentences.add(sentence);
				catSessionSentence.setSentences(arraysentences);
				arraySession.add(catSessionSentence);
				arraysentences  = new ArrayList<>();
				registroCero = 0;
			}
		}
		return arraySession;
	}
	
	
	
}
