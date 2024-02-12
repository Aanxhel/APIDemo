/**
 * 
 */
package com.ikon.process.service.impl;

import java.util.ArrayList;
import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ikon.process.dao.IkCardDao;
import com.ikon.process.dao.IkSentenceDao;
import com.ikon.process.dto.IkCardDto;
import com.ikon.process.dto.IkCardsSentencesDto;
import com.ikon.process.dto.IkSentenceCardDto;
import com.ikon.process.json.error.DatosErroresJson;
import com.ikon.process.json.error.ErroresJson;
import com.ikon.process.json.request.IkRequestSentenceCard;
import com.ikon.process.json.response.IkResponseGeneric;
import com.ikon.process.json.response.IkResponseSentencesJson;
import com.ikon.process.service.IkSentenceNeg;
import com.ikon.process.util.Constantes;
import com.ikon.process.util.IkConvierteJsonObjeto;
import com.ikon.process.util.IkUtileria;
import com.ikon.process.util.IkUtileriaSentences;
import com.ikon.process.util.IkValidaRequest;
import com.ikon.process.util.IkValidacionSenteceCard;

/**
 * @author icb_ipsra
 *
 */
@Service("IkSentenceNeg")
public class IkSentenceNegImpl implements IkSentenceNeg{

	private static final Logger LOGG = LoggerFactory.getLogger(IkSentenceNegImpl.class);
	
	@Autowired
	private IkSentenceDao ikSentenceDao;
	
	@Autowired
	private IkCardDao ikCardDao;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.service.IkSentenceNeg@asignaCardSentences(java.lang.String)
	 */
	public ResponseEntity<Object> asignaCardSentences (String requestSentenceCard ) {
		ResponseEntity<Object> entity = null;
		LOGG.info("[:::::::::  {}  :::::::::]", "Insert requestSentenceCard ");
		
		IkRequestSentenceCard request = new IkRequestSentenceCard();
		DatosErroresJson errroJson = new DatosErroresJson();
		IkValidacionSenteceCard validaError = new IkValidacionSenteceCard();
		
		if ( !IkValidaRequest.validaRequestCardSentences(requestSentenceCard) ) {
			errroJson = IkUtileria.generaSegmentoError(Constantes.CODIGO_400_5, Constantes.MENSAJE_400_5, "");
			entity = ResponseEntity.badRequest().body(errroJson);	
			LOGG.error("[:::::::::::: { } :::::::::::]", "JSON structure error");
		} else {
			request = IkConvierteJsonObjeto.convierteSenteceCard(requestSentenceCard);
			errroJson = validaError.validaErroresSentenceCard(request);
			
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
	 * @see com.ikon.process.service.IkSentenceNeg@getCatAllSentencesCards()
	 */
	public ResponseEntity<Object> getCatAllSentencesCards ( ) {
		ResponseEntity<Object> entity = null;
		LOGG.info("[:::::::::  {}  :::::::::]", "Get all the sentences and their cards");
		entity = this.getAllSentencesCards(Constantes.VALOR_INT_CERO);
		return entity;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.service.IkSentenceNeg@getCatAllSentencesCards()
	 */
	public ResponseEntity<Object> notassociatedSession ( ) {
		ResponseEntity<Object> entity = null;
		LOGG.info("[:::::::::  {}  :::::::::]", "Get all the sentences and their cards NOT associated to session");
		entity = this.getAllSentencesCards(Constantes.VALOR_INT_UNO);
		return entity;
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.service.IkSentenceNeg@eliminaSentencia(java.lang.Integer)
	 */
	public ResponseEntity<Object> eliminaSentencia (Integer cveSentence) {
		ResponseEntity<Object> entity = null;
		DatosErroresJson errroJson = new DatosErroresJson();
		
		if ( cveSentence == null || cveSentence <= 0  ) {
			errroJson = IkUtileria.generaSegmentoError(Constantes.CODIGO_400_1, Constantes.MENSAJE_400_1_6, "cveSentence");
			entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errroJson);
		} else {
			entity = this.procedeEliminarSentencia(cveSentence);
		}
		return entity;
	}
	
	
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.service.IkSentenceNeg@eliminaSentencia(java.lang.Integer)
	 */
	public ResponseEntity<Object> getSentencesById (Integer cveSentence) {
		ResponseEntity<Object> entity = null;
		DatosErroresJson errroJson = new DatosErroresJson();
		
		if ( cveSentence == null || cveSentence <= 0  ) {
			errroJson = IkUtileria.generaSegmentoError(Constantes.CODIGO_400_1, Constantes.MENSAJE_400_1_6, "cveSentence");
			entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errroJson);
		} else {
			entity = this.getSentencesCardsByID(cveSentence);
		}
		return entity;
	}
	
	
	
	/**
	 *  Metodo encargado de indentificar el proceso a ejecutar
	 * @param request
	 * @return
	 */
	private ResponseEntity<Object> identifyProcess ( IkRequestSentenceCard request ){
		ResponseEntity<Object> entity = null;
		IkResponseGeneric response = new IkResponseGeneric();
		Boolean existeCards = false;
		
		existeCards = this.validaExisteCards(request);
		
		if (existeCards ) {
			
			if ( request.getCveSentence() == Constantes.VALOR_INT_CERO ) {
				LOGG.info("[:::::::::::: { } :::::::::::]", "Inserting card  to sentence ");
				entity = this.insertaCardSentence(request);
				request.setCveSentence(Constantes.VALOR_INT_CERO);
			}
			
			if ( request.getCveSentence() > Constantes.VALOR_INT_CERO ) {
				LOGG.info("[:::::::::::: { } :::::::::::]", "Update card  to sentence ");
				entity = this.updateSentence(request);
			}
		} else {
			//The session does not exist
			response = IkUtileria.generaResponse(Constantes.I_RESPONSE_NO_EXIST, Constantes.ERROR_MESSAGE_SESSION_MENOS_5, "");
			entity = ResponseEntity.badRequest().body(response);
		}
		
		return entity;
	}
	
	
	/***
	 * Valida la existencia de Sessiones
	 * @param request
	 * @return
	 */
	private boolean validaExisteCards (IkRequestSentenceCard request) {
		boolean exitCards = true;
		
		try {
			for (IkCardDto card : request.getCards()) {
				int existe = this.ikCardDao.existeCards(card.getCveCard());
				if ( existe != Constantes.VALOR_INT_CERO) {
					exitCards = false;
					LOGG.info("[:::::::::: Ther is some card doesn't exist cveCard {} ::::::::]", card.getCveCard());
					break;
				}
			}
			
		} catch (Exception e) {
			exitCards = false;
		}
		
		return exitCards;
		
	}
	
	
	
	
	/***
	 * Asigna tarjetas a una sentencia
	 * @param request
	 * @return
	 */
	private ResponseEntity<Object> insertaCardSentence  ( IkRequestSentenceCard request ) {
		ResponseEntity<Object> entity = null;
		String senteceOrder = this.armandoSentenceOrder(request);
		Integer sequenceSentence = new Integer (Constantes.VALOR_INT_CERO);
		IkResponseGeneric responseCard = new IkResponseGeneric();
		int statusInsertcard = -1;
		boolean statusInsertC = true;
		DatosErroresJson errroJson = new DatosErroresJson();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		
		String sentenceName = request.getSentenceName().trim().toUpperCase();
		
		IkSentenceCardDto ikSentenceCardDto = new IkSentenceCardDto();
		ikSentenceCardDto.setSentenceOrder(senteceOrder);
		ikSentenceCardDto.setSentenceName(sentenceName);
		ikSentenceCardDto.setPathSound(request.getPathSound());
		ikSentenceCardDto.setPathVideo(request.getPathVideo());
		ikSentenceCardDto.setPathInfo(request.getPathInfo());
		
		try {
			
			sequenceSentence = this.ikSentenceDao.insertSentence(ikSentenceCardDto );
			
			if ( sequenceSentence == Constantes.VALOR_INT_MENOS_UNO ) {
				responseCard = IkUtileria.generaResponse(Constantes.I_RESPONSE_OK_EXIST, Constantes.S_RESPONSE_EXIST, "The Sentence Order exists in another sentence: "+ senteceOrder);
				entity = ResponseEntity.badRequest().body(responseCard);
			} else if (sequenceSentence == Constantes.VALOR_INT_MENOS_DOS) {
				responseCard = IkUtileria.generaResponse(Constantes.I_RESPONSE_OK_EXIST, Constantes.S_RESPONSE_EXIST, "The Sentence name already exists in another record: " + sentenceName);
				entity = ResponseEntity.badRequest().body(responseCard);
			}  else {
				
				for (IkCardDto card : request.getCards()) {
					statusInsertcard = this.ikSentenceDao.insertSentenceCard(sequenceSentence, card.getCveCard());
					if ( statusInsertcard != Constantes.VALOR_INT_CERO ) {
						statusInsertC = false;
						break;
					}
				}
				if ( !statusInsertC ) {
					responseCard = IkUtileria.generaResponse(Constantes.I_RESPONSE_OK_EXIST, Constantes.S_RESPONSE_EXIST, senteceOrder);
					entity = ResponseEntity.badRequest().body(responseCard);
				} else {
					responseCard = IkUtileria.generaResponse(Constantes.I_RESPONSE_OK, Constantes.S_RESPONSE_INSERT_OK, senteceOrder);
					entity = ResponseEntity.status(HttpStatus.OK).body(responseCard);
				}
			}
		} catch (Exception e) {
			
			errores.add(new ErroresJson(Constantes.ERROR_COD_500, Constantes.ERROR_COD_500.concat(e.getMessage())));
			errroJson.setErrores(errores);
			entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errroJson);
		}
		return entity;
	}
	
	
	/**
	 * Modifica los componentes de una sentencia 
	 * @param request
	 * @return
	 */
	private ResponseEntity<Object> updateSentence  ( IkRequestSentenceCard request ) {
		ResponseEntity<Object> entity = null;
		int statusUpdate = 0;
		DatosErroresJson errroJson = new DatosErroresJson();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		IkResponseGeneric responseCard = new IkResponseGeneric();
		int statusInsertcard = -1;
		boolean statusInsertC = true;
		String senteceOrder = this.armandoSentenceOrder(request);
		
		IkSentenceCardDto ikSentenceCardDto = new IkSentenceCardDto();
		
		ikSentenceCardDto.setCveSentence(request.getCveSentence());
		ikSentenceCardDto.setSentenceName(request.getSentenceName());
		ikSentenceCardDto.setSentenceOrder(senteceOrder);
		ikSentenceCardDto.setPathSound(request.getPathSound());
		ikSentenceCardDto.setPathVideo(request.getPathVideo());
		ikSentenceCardDto.setPathInfo(request.getPathInfo());
		
		try {
			
			statusUpdate = this.ikSentenceDao.updateSentence(ikSentenceCardDto);
			
			if ( statusUpdate == Constantes.VALOR_INT_MENOS_UNO ) {
				responseCard = IkUtileria.generaResponse(Constantes.I_RESPONSE_NO_EXIST, Constantes.S_RESPONSE_NO_EXIST, request.getCveSentence().toString());
				entity = ResponseEntity.badRequest().body(responseCard);
			} else if ( statusUpdate == Constantes.VALOR_INT_MENOS_DOS ) {
				responseCard = IkUtileria.generaResponse(Constantes.I_RESPONSE_NO_EXIST, Constantes.S_RESPONSE_EXIST, "The Sentence name already exists in another record " +request.getSentenceName());
				entity = ResponseEntity.badRequest().body(responseCard);
			} else if ( statusUpdate == Constantes.VALOR_INT_MENOS_TRES ) {
				responseCard = IkUtileria.generaResponse(Constantes.I_RESPONSE_NO_EXIST, Constantes.S_RESPONSE_EXIST, "The Sentence Order exists in another sentence: "+ senteceOrder );
				entity = ResponseEntity.badRequest().body(responseCard);
			} else {
				
				//Eliminando tarjetas asignadas para volver a insertar de acuerdo:
				this.ikSentenceDao.eliminaCardSentence(request.getCveSentence());
				
				for (IkCardDto card : request.getCards()) {
					statusInsertcard = this.ikSentenceDao.insertSentenceCard(request.getCveSentence(), card.getCveCard());
					if ( statusInsertcard != Constantes.VALOR_INT_CERO ) {
						statusInsertC = false;
						break;
					}
				}
				if ( !statusInsertC ) {
					responseCard = IkUtileria.generaResponse(Constantes.I_RESPONSE_OK_EXIST, Constantes.S_RESPONSE_EXIST,  senteceOrder);
					entity = ResponseEntity.badRequest().body(responseCard);
				} else {
					responseCard = IkUtileria.generaResponse(Constantes.I_RESPONSE_OK, Constantes.S_RESPONSE_UPDATE_OK, request.getCveSentence().toString() );
					entity = ResponseEntity.status(HttpStatus.OK).body(responseCard);
				}
				
			}
			
		} catch (Exception e) {
			errores.add(new ErroresJson(Constantes.ERROR_COD_500, Constantes.ERROR_COD_500.concat(e.getMessage())));
			errroJson.setErrores(errores);
			entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errroJson);
		}
		return entity;
	}
	
	
	/***
	 * Obtiene todos los registros de Base de Datos, de todas las 
	 * sentencias y sus tarjetas asignadas
	 * @return
	 */
	private ResponseEntity<Object>  getAllSentencesCards (int typeConsulta)  {
		ResponseEntity<Object> entity = null;
		DatosErroresJson errroJson = new DatosErroresJson();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		ArrayList<IkCardsSentencesDto> catAllSentences = new ArrayList<IkCardsSentencesDto>();
		ArrayList<IkResponseSentencesJson> arraySentences = new ArrayList<IkResponseSentencesJson>();
		
		try {
			if ( typeConsulta  == Constantes.VALOR_INT_CERO) {
				//TODO Obtiene todas las Sentencias asociadas y no asociadas a una session
				catAllSentences = this.ikSentenceDao.getAllSentencesCards();
			} else if (typeConsulta  == Constantes.VALOR_INT_UNO) {
				//TODO Obtiene todas las Sentencias no asociadas a una sessión
				catAllSentences = this.ikSentenceDao.notassociatedSession();
			}
			
			LOGG.info("[:::::::::::: Total de sentencias  {}  :::::::::]", catAllSentences.size());
			
			if ( !catAllSentences.isEmpty()) {
				catAllSentences.sort(Comparator.comparing(IkCardsSentencesDto::getCveSentence));
			
				arraySentences = IkUtileriaSentences.armaReponseSentencesCards(catAllSentences);
			
				if ( !arraySentences.isEmpty() && arraySentences.size() > Constantes.VALOR_INT_CERO ) {
					entity = ResponseEntity.status(HttpStatus.OK).body(arraySentences);
				} else {
					entity = ResponseEntity.status(HttpStatus.NO_CONTENT).body(arraySentences); //badRequest().body(responseCard);
				}
			} else {
				entity = ResponseEntity.status(HttpStatus.NO_CONTENT).body(arraySentences); 
			}
			
				
		} catch (Exception e) {
			errores.add(new ErroresJson(Constantes.ERROR_COD_500, Constantes.ERROR_COD_500.concat(e.getMessage())));
			errroJson.setErrores(errores);
			entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errroJson);
		}
		
		
		return entity;		
	}
	
	
	/***
	 * Obtiene todos los registros de Base de Datos, de todas las 
	 * sentencias y sus tarjetas asignadas
	 * @return
	 */
	private ResponseEntity<Object>  getSentencesCardsByID (Integer cveSentence)  {
		ResponseEntity<Object> entity = null;
		DatosErroresJson errroJson = new DatosErroresJson();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		ArrayList<IkCardsSentencesDto> catAllSentences = new ArrayList<IkCardsSentencesDto>();
		ArrayList<IkResponseSentencesJson> arraySentences = new ArrayList<IkResponseSentencesJson>();
		
		try {
			catAllSentences = this.ikSentenceDao.getSentencesCardsByID(cveSentence);
			
			LOGG.info("[:::::::::::: Total de sentencias By id  {}  :::::::::]", catAllSentences.size());
			
			if ( !catAllSentences.isEmpty()) {
				catAllSentences.sort(Comparator.comparing(IkCardsSentencesDto::getCveSentence));
			
				arraySentences = IkUtileriaSentences.armaReponseSentencesCards(catAllSentences);
			
				if ( !arraySentences.isEmpty() && arraySentences.size() > Constantes.VALOR_INT_CERO ) {
					entity = ResponseEntity.status(HttpStatus.OK).body(arraySentences);
				} else {
					entity = ResponseEntity.status(HttpStatus.NO_CONTENT).body(arraySentences); //badRequest().body(responseCard);
				}
			} else {
				entity = ResponseEntity.status(HttpStatus.NO_CONTENT).body(arraySentences); 
			}
			
				
		} catch (Exception e) {
			errores.add(new ErroresJson(Constantes.ERROR_COD_500, Constantes.ERROR_COD_500.concat(e.getMessage())));
			errroJson.setErrores(errores);
			entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errroJson);
		}
		
		
		return entity;		
	}
	
	/**
	 * Permite generar la estructura orden de acuerdo a las tarjetas incluidas.
	 * @param request
	 * @return
	 */
	private String armandoSentenceOrder (IkRequestSentenceCard request ) {
		String sentenceOrder = null;
		
		for (IkCardDto card : request.getCards() ) {

			if (sentenceOrder == null ) {
				sentenceOrder = card.getCveCard().toString().concat("-");
			} else {
				
				sentenceOrder = sentenceOrder.concat(card.getCveCard().toString().concat("-"));
			}
		}
		if ( sentenceOrder.endsWith("-") ) {
			sentenceOrder = sentenceOrder.substring(0, sentenceOrder.length()-1);
		}
		return sentenceOrder;
	}
	
	
	/***
	 * Inserta un registro en Base de datos.
	 * @param request
	 * @param cveTopic
	 * @return
	 */
	private ResponseEntity<Object> procedeEliminarSentencia( Integer cveSentence) {
		
		ResponseEntity<Object> entity = null;
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		DatosErroresJson errroJson = new DatosErroresJson();
		int respEliminar = -1;
		IkResponseGeneric responseSentence = new IkResponseGeneric();
		
		try {
			
			respEliminar = this.ikSentenceDao.eliminaSentence(cveSentence);
			
			LOGG.info("[:::::::::  {}  :::::::::]", "Status delete Sentence " + respEliminar);
			
			
			if ( respEliminar == Constantes.VALOR_INT_CERO ) {
				responseSentence = IkUtileria.generaResponse(Constantes.I_RESPONSE_OK, Constantes.S_RESPONSE_DELETE_OK, "");
				entity = ResponseEntity.status(HttpStatus.OK).body(responseSentence);
				
			} else if ( respEliminar == Constantes.VALOR_INT_UNO ) {
				responseSentence = IkUtileria.generaResponse(Constantes.I_RESPONSE_NO_EXIST, Constantes.ERROR_MESSAGE_SESSION_MENOS_1, "");
				entity = ResponseEntity.badRequest().body(responseSentence);
			} else {
				responseSentence = IkUtileria.generaResponse(Constantes.I_RESPONSE_ERROR_ASSOCIATE, Constantes.ERROR_MESSAGE_ERROR_ASSOCIATE, "Session");
				entity = ResponseEntity.badRequest().body(responseSentence);
			}
			
		} catch (Exception e) {
			errores.add(new ErroresJson(Constantes.ERROR_COD_500, Constantes.ERROR_COD_500.concat(e.getMessage())));
			errroJson.setErrores(errores);
			entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errroJson);
		}
		
		return entity;
	}
	
	
	
	
	

	
}
