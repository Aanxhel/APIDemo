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

import com.ikon.process.dao.IkCardDao;
import com.ikon.process.dao.IkTopicDao;
import com.ikon.process.dto.IkCardByTopicDto;
import com.ikon.process.dto.IkGetCard;
import com.ikon.process.json.error.DatosErroresJson;
import com.ikon.process.json.error.ErroresJson;
import com.ikon.process.json.request.IkRequestCard;
import com.ikon.process.json.response.IkResponseGeneric;
import com.ikon.process.service.IkCardNeg;
import com.ikon.process.util.Constantes;
import com.ikon.process.util.IkConvierteJsonObjeto;
import com.ikon.process.util.IkUtileria;
import com.ikon.process.util.IkValidaRequest;
import com.ikon.process.util.IkValidacionCard;

/**
 * @author icb_ipsra
 *
 */
@Service("ikCardNegImpl")
public class IkCardNegImpl implements IkCardNeg{

	private static final Logger LOGG = LoggerFactory.getLogger(IkCardNegImpl.class);
	
	@Autowired
	private IkCardDao ikCardDao;
	
	@Autowired
	private IkTopicDao ikTopicDao;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.service.CompModuleNeg@compModule()
	 */
	public ResponseEntity<Object> insertCardTopic (String requestCardTopic ) {
		ResponseEntity<Object> entity = null;
		LOGG.info("[:::::::::  {}  :::::::::]", "Insert / Update Card ");
		IkRequestCard request = new IkRequestCard();
		DatosErroresJson errroJson = new DatosErroresJson();
		IkValidacionCard validaError = new IkValidacionCard();
		
		if ( !IkValidaRequest.validaRequestCardTopic(requestCardTopic) ) {
			errroJson = IkUtileria.generaSegmentoError(Constantes.CODIGO_400_5, Constantes.MENSAJE_400_5, "");
			entity = ResponseEntity.badRequest().body(errroJson);	
			LOGG.error("[:::::::::::: { } :::::::::::]", "JSON structure error");
		} else {
			request = IkConvierteJsonObjeto.convierteCardTopic(requestCardTopic);
			errroJson = validaError.validaErroresCatalago(request);
			
			if (errroJson.getErrores() != null && !errroJson.getErrores().isEmpty()) {
				entity = ResponseEntity.badRequest().body(errroJson);
				LOGG.error("[::::::::: { }  :::::::::]", "It has errors in the request");
			} else {
				entity = this.identifyProcess(request);
			}
		}
		
		return entity; 
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.service.IkCardNeg@obtineCardbyTopic()
	 */
	public  ResponseEntity<Object> obtineCardbyTopic( Integer cveTopic) {
		ResponseEntity<Object> entity = null;
		ArrayList<IkCardByTopicDto> cardTopic = new ArrayList<>();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		DatosErroresJson errroJson = new DatosErroresJson();
		
		try {
			
			cardTopic = this.ikCardDao.obtineCardbyTopic(cveTopic);
			
			if ( !cardTopic.isEmpty() ) {
				entity = ResponseEntity.status(HttpStatus.OK).body(cardTopic);
			} else {
				cardTopic.add(new IkCardByTopicDto());
				entity = ResponseEntity.status(HttpStatus.NO_CONTENT).body(cardTopic);
			}
			
		} catch (Exception e) {
			errores.add(new ErroresJson(Constantes.ERROR_COD_500, Constantes.ERROR_COD_500.concat(e.getMessage())));
			errroJson.setErrores(errores);
			entity = ResponseEntity.badRequest().body(errroJson);
		}
		
		return entity;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.service.IkCardNeg@eliminaCard(java.lang.Integer)
	 */
	public ResponseEntity<Object> eliminaCard (Integer cveCard) {
		ResponseEntity<Object> entity = null;
		DatosErroresJson errroJson = new DatosErroresJson();
		
		if ( cveCard == null || cveCard <= 0  ) {
			errroJson = IkUtileria.generaSegmentoError(Constantes.CODIGO_400_1, Constantes.MENSAJE_400_1_6, "cveCard");
			entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errroJson);
		} else {
			entity = this.procedeEliminarCard(cveCard);
		}
		return entity;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.service.IkCardNeg@obtineCardbyID(java.lang.Integer)
	 */
	public ResponseEntity<Object> obtineCardbyID (Integer cveCard) {
		ResponseEntity<Object> entity = null;
		DatosErroresJson errroJson = new DatosErroresJson();
		
		if ( cveCard == null ) {
			errroJson = IkUtileria.generaSegmentoError(Constantes.CODIGO_400_1, Constantes.MENSAJE_400_1_6, "cveCard");
			entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errroJson);
		} else {
			entity = this.getCardInformation(cveCard);
		}
		
		return entity;
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.service.IkCardNeg@obtineCardbyID(java.lang.Integer)
	 */
	public ResponseEntity<Object> obtineCardbyTopicName (String  topicName ) {
		ResponseEntity<Object> entity = null;
		DatosErroresJson errroJson = new DatosErroresJson();
		
		if ( topicName == null || topicName.equals("") ) {
			errroJson = IkUtileria.generaSegmentoError(Constantes.CODIGO_400_1, Constantes.MENSAJE_400_1_6, "");
			entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errroJson);
		} else {
			entity = this.getCardByTopicname(topicName.trim().toUpperCase());
		}
		
		return entity;
	}
	
	
	/**
	 *  Metodo encargado de indentificar el proceso a ejecutar
	 * @param request
	 * @return
	 */
	private ResponseEntity<Object> identifyProcess ( IkRequestCard request ){
		ResponseEntity<Object> entity = null;
		
		if ( request.getCveTopic() == Constantes.VALOR_INT_CERO ) {
			LOGG.info("[:::::::::::: { } :::::::::::]", "Inserting and assigning card to the new topic");
			entity = this.insertRecordCardTopic(request);
			request.setCveTopic(Constantes.VALOR_INT_CERO);
		}
		if (request.getCveTopic() > Constantes.VALOR_INT_CERO  && request.getCveCard() == Constantes.VALOR_INT_CERO ) {
			LOGG.info("[:::::::::::: { } :::::::::::]", "Assigning card to the topic");
			entity = this.asignandoCardTopic(request);
			request.setCveTopic(Constantes.VALOR_INT_CERO);
		}
		
		if (request.getCveTopic() > Constantes.VALOR_INT_CERO  && request.getCveCard() > Constantes.VALOR_INT_CERO ) {
			LOGG.info("[:::::::::::: { } :::::::::::]", "Update card to the topic");
			entity = this.modificaCardTopic(request);
			request.setCveTopic(Constantes.VALOR_INT_CERO);
		}
		
		return entity;
	}
	
	/**
	 * Registrando Topic, la tarjeta será asignada a este nuevo topic
	 * @param request
	 * @return
	 */
	private ResponseEntity<Object> insertRecordCardTopic ( IkRequestCard request ) {
		ResponseEntity<Object> entity = null;
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		DatosErroresJson errroJson = new DatosErroresJson();
		Integer sequenceTopic = new Integer(0);
		IkResponseGeneric responseCard = new IkResponseGeneric();
		
		try {
			
			sequenceTopic = ikTopicDao.insertTopic(request.getTopicName().toUpperCase());
			
			if ( sequenceTopic == Constantes.VALOR_INT_MENOS_UNO ) {
				responseCard = IkUtileria.generaResponse(Constantes.I_RESPONSE_OK_EXIST, Constantes.S_RESPONSE_EXIST, request.getTopicName());
				entity = ResponseEntity.badRequest().body(responseCard);
			} else if (sequenceTopic == Constantes.VALOR_INT_MENOS_DOS ) {
				responseCard = IkUtileria.generaResponse(Constantes.I_RESPONSE_OK_EXIST, Constantes.S_RESPONSE_NO_EXIST, request.getTopicName());
				entity = ResponseEntity.badRequest().body(responseCard);
			} else {
				request.setCveTopic(sequenceTopic);
				entity = this.insertRecord(request);
				request.setCveCard(sequenceTopic);
			} 
		} catch (Exception e) {
			errores.add(new ErroresJson(Constantes.ERROR_COD_500, Constantes.ERROR_COD_500.concat(e.getMessage())));
			errroJson.setErrores(errores);
			entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errroJson);
		}
		
		return entity;
	}
	
	
	/***
	 * Asigna una tarjeta a un topic
	 * @param request
	 * @return
	 */
	private ResponseEntity<Object> asignandoCardTopic ( IkRequestCard request ) {
		ResponseEntity<Object> entity = null;
		entity = this.insertRecord(request);
		return entity;
	}
	
	/***
	 * Asigna una tarjeta a un topic
	 * @param request
	 * @return
	 */
	private ResponseEntity<Object> modificaCardTopic ( IkRequestCard request ) {
		int estatusActualiza = -1;
		ResponseEntity<Object> entity = null;
		DatosErroresJson errroJson = new DatosErroresJson();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		IkResponseGeneric responseCard = new IkResponseGeneric();
		
		try {
			
			request.setCardText(request.getCardText().toUpperCase());	
			estatusActualiza = this.ikCardDao.updateCardTopic(request);
			
			if ( estatusActualiza == Constantes.VALOR_INT_MENOS_TRES ) {
				responseCard = IkUtileria.generaResponse(Constantes.I_RESPONSE_OK_EXIST, Constantes.S_RESPONSE_NO_EXIST, "cveTopic - cveCard" );
				entity = ResponseEntity.badRequest().body(responseCard);
			}

			if ( estatusActualiza == Constantes.VALOR_INT_MENOS_DOS ) {
				responseCard = IkUtileria.generaResponse(Constantes.I_RESPONSE_NO_EXIST, Constantes.S_RESPONSE_NO_EXIST, "cveTopic - cveCard");
				entity = ResponseEntity.badRequest().body(responseCard);
			}
			
			if ( estatusActualiza == Constantes.VALOR_INT_CERO ) {
				responseCard = IkUtileria.generaResponse(Constantes.I_RESPONSE_OK, Constantes.S_RESPONSE_UPDATE_OK, request.getCardText());
				entity = ResponseEntity.status(HttpStatus.OK).body(responseCard);
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
	private ResponseEntity<Object> insertRecord( IkRequestCard request) {
		ResponseEntity<Object> entity = null;
		Integer sequenceCard = new Integer(0); 
		IkResponseGeneric responseCard = new IkResponseGeneric();
		DatosErroresJson errroJson = new DatosErroresJson();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		
		try {
			request.setCardText(request.getCardText().toUpperCase());
			
			//TODO .- Insertando tarjeta
			sequenceCard = this.ikCardDao.insertCard(request);
			
			if ( sequenceCard == Constantes.VALOR_INT_MENOS_UNO ) {
				// TODO .- La tarjeta ya existe con el topic
				 responseCard = IkUtileria.generaResponse(Constantes.I_RESPONSE_OK_EXIST, Constantes.S_RESPONSE_EXIST, request.getCardText());
//				 entity = ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseCard);
				 entity = ResponseEntity.badRequest().body(responseCard);
				 
			} else if ( sequenceCard == Constantes.VALOR_INT_MENOS_DOS) {
				
				responseCard = IkUtileria.generaResponse(Constantes.I_RESPONSE_NO_EXIST, Constantes.S_RESPONSE_NO_EXIST, "cveTopic " + request.getCveTopic().toString());
				entity = ResponseEntity.badRequest().body(responseCard);
				
			} else if (sequenceCard > Constantes.VALOR_INT_CERO ) {
				
				responseCard = IkUtileria.generaResponse(Constantes.I_RESPONSE_OK, Constantes.S_RESPONSE_INSERT_OK, request.getCardText());
				entity = ResponseEntity.status(HttpStatus.OK).body(responseCard);
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
	private ResponseEntity<Object> procedeEliminarCard( Integer cveCard) {
		
		ResponseEntity<Object> entity = null;
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		DatosErroresJson errroJson = new DatosErroresJson();
		int respEliminar = -1;
		IkResponseGeneric responseCard = new IkResponseGeneric();
		
		try {
			
			respEliminar = this.ikCardDao.eliminaCard(cveCard);
			
			LOGG.info("[:::::::::  {}  :::::::::]", "Status delete card " + respEliminar);
			
			
			if ( respEliminar == Constantes.VALOR_INT_CERO ) {
				responseCard = IkUtileria.generaResponse(Constantes.I_RESPONSE_OK, Constantes.S_RESPONSE_DELETE_OK, "");
				entity = ResponseEntity.status(HttpStatus.OK).body(responseCard);
				
			} else if ( respEliminar == Constantes.VALOR_INT_UNO ) {
				responseCard = IkUtileria.generaResponse(Constantes.I_RESPONSE_NO_EXIST, Constantes.ERROR_MESSAGE_SESSION_MENOS_1, "");
				entity = ResponseEntity.badRequest().body(responseCard);
			} else {
				responseCard = IkUtileria.generaResponse(Constantes.I_RESPONSE_ERROR_ASSOCIATE, Constantes.ERROR_MESSAGE_ERROR_ASSOCIATE, "Sentence");
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
	 * Obtiene información relacionada a una tarjeta
	 * @param cveCard
	 * 		cveCard == 0  getting all cards information
	 *      cveCard > 0   getting card information by ID  		
	 * @return
	 */
	private ResponseEntity<Object> getCardInformation ( Integer cveCard) {
		ArrayList<IkGetCard> arrayCard = new ArrayList<>();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		DatosErroresJson errroJson = new DatosErroresJson();
		ResponseEntity<Object> entity = null;
		IkResponseGeneric response = new IkResponseGeneric();
		
		LOGG.info("[:::::::::  {}  :::::::::]", "Getting card information ");
		try {
			
			
			if ( cveCard != null && cveCard == Constantes.VALOR_INT_CERO ) {
				arrayCard = this.ikCardDao.obtineAllCards();
				LOGG.info("[:::::::::  {}  :::::::::]", "Getting all cards information ");
			} else {
				LOGG.info("[:::::::::  {}  :::::::::]", "Getting card information by ID ");
				arrayCard = this.ikCardDao.obtineCardbyID(cveCard);
			}
			
			if (arrayCard.isEmpty() || arrayCard.size() == Constantes.VALOR_INT_CERO ) {
				response = IkUtileria.generaResponse (Constantes.I_RESPONSE_NO_EXIST, Constantes.S_RESPONSE_NO_EXIST,"");
				entity = ResponseEntity.badRequest().body(response);
			} else {
				entity = ResponseEntity.status(HttpStatus.OK).body(arrayCard);
			}
			
		} catch (Exception e) {
			errores.add(new ErroresJson(Constantes.ERROR_COD_500, Constantes.ERROR_COD_500.concat(e.getMessage())));
			errroJson.setErrores(errores);
			entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errroJson);
		}
		
		return entity;
	}
	
	
	/****
	 * Obtiene información relacionada a una tarjeta
	 * @param cveCard
	 * 		cveCard == 0  getting all cards information
	 *      cveCard > 0   getting card information by ID  		
	 * @return
	 */
	private ResponseEntity<Object> getCardByTopicname ( String topicName) {
		ArrayList<IkCardByTopicDto> arrayCard = new ArrayList<>();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		DatosErroresJson errroJson = new DatosErroresJson();
		ResponseEntity<Object> entity = null;
		
		LOGG.info("[:::::::::  {}  :::::::::]", "Getting card information by topic name ");
		try {
			
			LOGG.info("[:::::::::  {}  :::::::::]", "Getting card information by ID ");
			arrayCard = this.ikCardDao.getCardByTopicName(topicName);
			
			
			if (arrayCard.isEmpty() || arrayCard.size() == Constantes.VALOR_INT_CERO ) {
				entity = ResponseEntity.status(HttpStatus.NO_CONTENT).body(arrayCard);
			} else {
				entity = ResponseEntity.status(HttpStatus.OK).body(arrayCard);
			}
			
		} catch (Exception e) {
			errores.add(new ErroresJson(Constantes.ERROR_COD_500, Constantes.ERROR_COD_500.concat(e.getMessage())));
			errroJson.setErrores(errores);
			entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errroJson);
		}
		
		return entity;
	}
	
}
