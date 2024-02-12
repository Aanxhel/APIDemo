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

import com.ikon.process.dao.IkModuleDao;
import com.ikon.process.dao.IkSessionDao;
import com.ikon.process.dto.CompModuleDto;
import com.ikon.process.dto.IkModSessionCardsDto;
import com.ikon.process.dto.IkModSessionDto;
import com.ikon.process.dto.IkModSessionHeaderDto;
import com.ikon.process.dto.IkSessionDto;
import com.ikon.process.dto.IkSessionModDto;
import com.ikon.process.json.error.DatosErroresJson;
import com.ikon.process.json.error.ErroresJson;
import com.ikon.process.json.request.IkRequestModuleJson;
import com.ikon.process.json.response.IkResponseGeneric;
import com.ikon.process.json.response.IkResponseModSessionJson;
import com.ikon.process.json.response.IkResponseModuleSessionJson;
import com.ikon.process.service.IkModuleNeg;
import com.ikon.process.util.Constantes;
import com.ikon.process.util.IkConvierteJsonObjeto;
import com.ikon.process.util.IkUtileria;
import com.ikon.process.util.IkValidaRequest;
import com.ikon.process.util.IkValidacionModule;

/**
 * @author icb_ipsra
 * @since Octuber, 2021
 *
 */
@Service("IkModuleNeg")
public class IkModuleNegImpl implements IkModuleNeg {

	private static final Logger LOGG = LoggerFactory.getLogger(IkModuleNegImpl.class);
	
	@Autowired
	private IkModuleDao ikModuleDao;
	
	@Autowired
	private IkSessionDao ikSessionDao;
	
	/*
	 * (non-Javadoc)
	 * @see com.ikon.process.service.CompModuleNeg@obtieneCompModule()
	 */
	public ResponseEntity<Object> obtieneModuleSessiones () {
		ResponseEntity<Object> entity = null;
		DatosErroresJson errroJson = new DatosErroresJson();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		
		try {
			entity = this.getModuleSession(new Integer(Constantes.VALOR_INT_CERO));

		} catch (Exception e) {
			errores.add(new ErroresJson(Constantes.ERROR_COD_500, Constantes.ERROR_COD_500.concat(e.getMessage())));
			errroJson.setErrores(errores);
			entity = ResponseEntity.badRequest().body(errroJson);
		}

		return entity;
	}
	/*
	 * (non-Javadoc)
	 * @see com.ikon.process.service.CompModuleNeg@obtieneCompModule()
	 */
	public ResponseEntity<Object> obtieneModuleById(Integer cveModule) {
		ResponseEntity<Object> entity = null;
		DatosErroresJson errroJson = new DatosErroresJson();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		
		try {
			entity = this.getModuleSession(cveModule);

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
	 * @see com.ikon.process.service.CompModuleNeg@compModule()
	 */
	public ResponseEntity<Object> movimientoModule(String requestModuleJson ) {
		ResponseEntity<Object> entity = null;
		IkRequestModuleJson request = new IkRequestModuleJson();
		DatosErroresJson errroJson = new DatosErroresJson();
		IkValidacionModule validaError = new IkValidacionModule();
		
		LOGG.info("[:::::::::  {}  :::::::::]", "Getting data from the movimientoModule");
		
		if (!IkValidaRequest.validaRequestModule(requestModuleJson)) {
			
			errroJson = IkUtileria.generaSegmentoError(Constantes.CODIGO_400_5, Constantes.MENSAJE_400_5, "");
			entity = ResponseEntity.badRequest().body(errroJson);
			LOGG.error("[:::::::::::: { } :::::::::::]", "JSON structure error");
		} else {
			request = IkConvierteJsonObjeto.convierteModule(requestModuleJson);
			errroJson = validaError.validaErroresModule(request);
			
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
	 * @see com.ikon.process.service.CompModuleNeg@compModule()
	 */
	public ResponseEntity<Object> obtieneSentenCards ( Integer cveModule, Integer cveSession) {
		ResponseEntity<Object> entity = null;
		entity = this.getSentencesCards(cveModule, cveSession);
		return entity;
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.service.CompModuleNeg@eliminaModule(java.lang.Integer)
	 */
	public ResponseEntity<Object> eliminaModule (Integer cveModule) {
		ResponseEntity<Object> entity = null;
		DatosErroresJson errroJson = new DatosErroresJson();
		
		if ( cveModule == null || cveModule <= 0  ) {
			errroJson = IkUtileria.generaSegmentoError(Constantes.CODIGO_400_1, Constantes.MENSAJE_400_1_6, "cveCard");
			entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errroJson);
		} else {
			entity = this.procedeEliminarModule(cveModule);
		}
		return entity;
	}
	
	/***
	 * Arma el arrreglo de cada una de
	 * @param cveModule
	 * @param cveSession
	 * @return
	 */
	private ResponseEntity<Object>  getSentencesCards ( Integer cveModule, Integer cveSession ){
		ResponseEntity<Object> entity = null;
		ArrayList<IkModSessionHeaderDto> arraySentences = new ArrayList<>();
		DatosErroresJson errroJson = new DatosErroresJson();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		IkResponseGeneric responseCard = new IkResponseGeneric();
		IkResponseModuleSessionJson response = new IkResponseModuleSessionJson();
		
		try {
			
			
			//---> Obteniendolas sentencias.
			arraySentences = this.ikModuleDao.getModSessionHeader(cveModule, cveSession);
			
			if ( arraySentences == null || arraySentences.isEmpty() ||  arraySentences.size() == 0) {
				//responseCard = IkUtileria.generaResponse (Constantes.I_RESPONSE_NO_EXIST, Constantes.ERROR_MESSAGE_MODULE_SESSION_NO_,"");
				//entity = ResponseEntity.badRequest().body(responseCard);
				entity = ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseCard);
			} else {
				
				ArrayList<IkModSessionHeaderDto> sentencesArray = new ArrayList<>();
				ArrayList<IkModSessionDto> lstSentences = new ArrayList<>();
				
				int headerRowCero = Constantes.VALOR_INT_CERO;;
				
				for (IkModSessionHeaderDto sent : arraySentences) {
					 
					if ( headerRowCero == Constantes.VALOR_INT_CERO ) {
						response.setModuleName(sent.getModuleName());
						response.setSessionName(sent.getSessionName());
						headerRowCero += 1;
					}  
					
					sentencesArray.add(new IkModSessionHeaderDto(
										sent.getCveSentence(), sent.getSentenceName(), 
										sent.getSentenceOrder(), sent.getAudioSent(), 
										sent.getVideoSent(), sent.getInfoSent()));
				}
				
				for (IkModSessionHeaderDto sentdata : sentencesArray) {
					lstSentences.add(new IkModSessionDto(
														sentdata.getCveSentence(), 
														sentdata.getSentenceName(), 
														sentdata.getSentenceOrder(), 
														sentdata.getAudioSent(), 
														sentdata.getVideoSent(), 
														sentdata.getInfoSent()));
				} 
				
				response.setSentences(lstSentences);
				
				
				ArrayList<IkModSessionCardsDto> arrayCards = new ArrayList<>();
				
				//---> Obteniendolas las tarjetas
				arrayCards = this.ikModuleDao.getModSessionCard(cveModule, cveSession);
				
				if ( arrayCards == null || arrayCards.isEmpty() ||  arrayCards.size() == 0) {
					responseCard = IkUtileria.generaResponse (Constantes.I_RESPONSE_NO_EXIST, Constantes.ERROR_MESSAGE_MODULE_SESSION_SENTENCES_NO_,"");
					entity = ResponseEntity.badRequest().body(responseCard);
				} else {
					response.setCards(arrayCards);
				}
				
				entity = ResponseEntity.status(HttpStatus.OK).body(response);
				
			}
			
		} catch (Exception e) {
			errores.add(new ErroresJson(Constantes.ERROR_COD_500, Constantes.ERROR_COD_500.concat(e.getMessage())));
			errroJson.setErrores(errores);
			entity = ResponseEntity.badRequest().body(errroJson);
		}
		
		return entity;
	}
	
	
	
	
	
	/**
	 *  Metodo encargado de indentificar el proceso a ejecutar
	 * @param request
	 * @return
	 */
	private ResponseEntity<Object> identifyProcess ( IkRequestModuleJson request ){
		ResponseEntity<Object> entity = null;
		Boolean existSession  = false;
		IkResponseGeneric response = new IkResponseGeneric();
		
		existSession = this.validaSesionesExistentes(request);
		
		if ( existSession ) {
		
			if ( request.getCveModule() == Constantes.VALOR_INT_CERO ) {
				LOGG.info("[:::::::::::: { } :::::::::::]", "Inserting module - Sessiones  ");
				
				entity = this.insertModuleSession(request);
			}
			
			if ( request.getCveModule() > Constantes.VALOR_INT_CERO ) {
				LOGG.info("[:::::::::::: { } :::::::::::]", "Update module - Sessiones ");
				entity = this.updateModuleSession(request);
			}
		} else {
			//The session does not exist
			response = IkUtileria.generaResponse(Constantes.I_RESPONSE_NO_EXIST, Constantes.ERROR_MESSAGE_SESSION_MENOS_4, "");
			entity = ResponseEntity.badRequest().body(response);
		}
		
		return entity;
	}
	
	/***
	 * Asigna tarjetas a una sentencia
	 * @param request
	 * @return
	 */
	private ResponseEntity<Object> insertModuleSession  ( IkRequestModuleJson request ) {
		ResponseEntity<Object> entity = null;
		DatosErroresJson errroJson = new DatosErroresJson();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		IkResponseGeneric response = new IkResponseGeneric();
		Integer sequenceModule = new Integer (Constantes.VALOR_INT_CERO);
		
		
		String moduleName = request.getModuleName().trim().toUpperCase();
		String moduleDesc = request.getModuleDesc() != null && !request.getModuleDesc().equals("")
										?request.getModuleDesc().trim().toUpperCase():"";
		
		try {								
				
			sequenceModule = this.ikModuleDao.insertModule(moduleName, moduleDesc);
			
			
			if ( sequenceModule == Constantes.VALOR_INT_MENOS_UNO ) {
				response = IkUtileria.generaResponse(Constantes.I_RESPONSE_OK_EXIST, Constantes.S_RESPONSE_EXIST, moduleName);
				entity = ResponseEntity.badRequest().body(response);
			} else {
				
				for (IkSessionDto session : request.getSesiones()) {
					this.ikModuleDao.insertModuleSession(sequenceModule, session.getCveSesion());
				}
				
				response = IkUtileria.generaResponse(Constantes.I_RESPONSE_OK, Constantes.S_RESPONSE_INSERT_OK, moduleName);
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
	 * Valida la existencia de Sessiones
	 * @param request
	 * @return
	 */
	private boolean validaSesionesExistentes (IkRequestModuleJson request ) {
		boolean exiteSessiones = true;
		
		try {
			for (IkSessionDto session : request.getSesiones()) {
				int existe = this.ikSessionDao.existeSession(session.getCveSesion());
				if ( existe != Constantes.VALOR_INT_CERO) {
					exiteSessiones = false;
					LOGG.info("[:::::::::: Ther is a session doesn't exist cveSession {} ::::::::]", session.getCveSesion());
					break;
				}
			}
			
		} catch (Exception e) {
			exiteSessiones = false;
		}
		
		return exiteSessiones;
		
	}
	
	
	/***
	 * Asigna tarjetas a una sentencia
	 * @param request
	 * @return
	 */
	private ResponseEntity<Object> updateModuleSession  ( IkRequestModuleJson request ) {
		ResponseEntity<Object> entity = null;
		DatosErroresJson errroJson = new DatosErroresJson();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		IkResponseGeneric response = new IkResponseGeneric();
		
		int moduleSession = 0; 
		
		String moduleName = request.getModuleName().trim().toUpperCase();
		
		try {
			moduleSession = this.ikModuleDao.existModuleSession(request.getCveModule(), moduleName);
		
			switch (moduleSession) {
			case Constantes.VALOR_INT_CERO:
				///Apto para proceder con la modificación
				entity = this.processUpdate(request);
				break;
			case Constantes.VALOR_INT_MENOS_UNO:
				//The session does not exist
				response = IkUtileria.generaResponse(Constantes.I_RESPONSE_NO_EXIST, Constantes.ERROR_MESSAGE_SESSION_MENOS_1, request.getCveModule() +" " +request.getModuleName());
				entity = ResponseEntity.badRequest().body(response);
				break;
			case Constantes.VALOR_INT_MENOS_TRES:
				//El nombre de la session existe con otra sentencia
				response = IkUtileria.generaResponse(Constantes.I_RESPONSE_OK_EXIST, Constantes.ERROR_MESSAGE_SESSION_MENOS_3, request.getModuleName());
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
	private  ResponseEntity<Object> processUpdate ( IkRequestModuleJson request ) throws Exception{
		ResponseEntity<Object> entity = null;
		IkResponseGeneric response = new IkResponseGeneric();
		
		//-- Insertando la module con el mismo numero de sequencia actual
		this.ikModuleDao.updateModule(request.getCveModule(), request.getModuleName(), request.getModuleDesc());
		
		
		if (request.getSesiones() != null ) {
			if ( !request.getSesiones().isEmpty() && request.getSesiones().size() > Constantes.VALOR_INT_CERO ) {
				for (IkSessionDto session : request.getSesiones()) {
					this.ikModuleDao.insertModuleSession(request.getCveModule(), session.getCveSesion());
				}
			}
		}
	
		response = IkUtileria.generaResponse(Constantes.I_RESPONSE_OK, Constantes.S_RESPONSE_INSERT_OK,  request.getModuleName());
		entity = ResponseEntity.status(HttpStatus.OK).body(response);
		
		
		return entity;
	} 
	
	
	
	/***
	 * Inserta un registro en Base de datos.
	 * @param request
	 * @param cveTopic
	 * @return
	 */
	private ResponseEntity<Object> procedeEliminarModule( Integer cveModule) {
		
		ResponseEntity<Object> entity = null;
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		DatosErroresJson errroJson = new DatosErroresJson();
		IkResponseGeneric responseCard = new IkResponseGeneric();
		ArrayList<Integer> arraySessiones = new ArrayList<>();
		ArrayList<Integer> arraySentences = new ArrayList<>();
		
		try {
			
			int moduleExists = this.moduleExists(cveModule);
			
			if ( moduleExists == Constantes.VALOR_INT_MENOS_UNO ) {
				LOGG.info("[:::::. {} .:::::]", "Module doesn't exists");
				responseCard = IkUtileria.generaResponse(Constantes.I_RESPONSE_NO_EXIST, Constantes.ERROR_MESSAGE_SESSION_MENOS_1, "");
				entity = ResponseEntity.badRequest().body(responseCard);
			} 
			if ( moduleExists == Constantes.VALOR_INT_UNO ) {
				LOGG.info("[:::::. {} .:::::]", "Module exists");
				arraySessiones = this.getAllSessionesByModule(cveModule);
				
				if ( arraySessiones != null && !arraySessiones.isEmpty()  ) {
					LOGG.info("[:::::. {} .:::::]", "The module contains sessions.");
					
					for (Integer cveSession : arraySessiones) {
						LOGG.info("[:::::. cveSession {} .:::::]", cveSession);
						arraySentences = this.getAllSentencesBySessionAndModule(cveModule, cveSession);
						
						for (Integer cveSentence : arraySentences) {
							LOGG.info("[:::::. cveSession {} cveSentence {}.:::::]", cveSession, cveSentence);
							this.deleteSentences(cveSession, cveSentence);
						}
					}
					responseCard = IkUtileria.generaResponse(Constantes.I_RESPONSE_OK, Constantes.S_RESPONSE_DELETE_OK, "");
					entity = ResponseEntity.status(HttpStatus.OK).body(responseCard);
					
				} else {
					LOGG.info("[:::::. {} .:::::]", "The module does not contains sessions.");
					this.ikModuleDao.eliminaModule(cveModule);
					responseCard = IkUtileria.generaResponse(Constantes.I_RESPONSE_OK, Constantes.S_RESPONSE_DELETE_OK, "");
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
	 * Arma la respuesta de las sessiones correspondientes 
	 * a una session
	 * @param cveSession
	 * @return
	 */
	private ResponseEntity<Object>  getModuleSession (Integer cveModule ){
		
		ResponseEntity<Object> entity = null;
		DatosErroresJson errroJson = new DatosErroresJson();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		ArrayList<CompModuleDto> callModuleSessiones = new ArrayList<CompModuleDto>();
		ArrayList<IkResponseModSessionJson>  arrayModuleSession = new ArrayList<IkResponseModSessionJson>();
		
		try {
				if ( cveModule != null && cveModule == Constantes.VALOR_INT_CERO) {
					callModuleSessiones = this.ikModuleDao.obtieneModule();
				} else {
					callModuleSessiones = this.ikModuleDao.obtieneModuleById(cveModule);
				}
				
				if (callModuleSessiones.isEmpty() || callModuleSessiones.size() == Constantes.VALOR_INT_CERO ) {
					entity = ResponseEntity.status(HttpStatus.NO_CONTENT).body(arrayModuleSession);
				} else {
					arrayModuleSession = this.getModuleSentences(callModuleSessiones);
					entity = ResponseEntity.status(HttpStatus.OK).body(arrayModuleSession);
				}
				
		} catch (Exception e) {
			errores.add(new ErroresJson(Constantes.ERROR_COD_500, Constantes.ERROR_COD_500.concat(e.getMessage())));
			errroJson.setErrores(errores);
			entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errroJson);
		}
		
		
		return entity;
	}
	
	/**
	 * genera el objeto final de Modulos con sus sentencias 
	 * @param callModuleSessiones
	 * @return
	 */
	private ArrayList<IkResponseModSessionJson>  getModuleSentences ( ArrayList<CompModuleDto> callModuleSessiones ) {
		ArrayList<IkResponseModSessionJson>  arrayModuleSession = new ArrayList<IkResponseModSessionJson>();
		ArrayList<IkSessionModDto> arraySessiones = new ArrayList<>();
		IkSessionModDto modSession = null;
		IkResponseModSessionJson moduleSession = null;
		
		int registroCero = 0;
		
		for (CompModuleDto datosModuleSession : callModuleSessiones) {
			modSession = new IkSessionModDto();
			
			if ( registroCero == 0 ) {
				moduleSession = new IkResponseModSessionJson();
				
				moduleSession.setCveModule(datosModuleSession.getCveModule());
				moduleSession.setModuleName(datosModuleSession.getModuleName());
				moduleSession.setModuleDesc(datosModuleSession.getModuleDesc());
				moduleSession.setTotalSessiones(datosModuleSession.getTotalSessiones());
			}
			
			registroCero +=1;
			
			modSession.setCveSession(datosModuleSession.getCveSession());
			modSession.setSessionName(datosModuleSession.getSessionName());
			
			if ( registroCero <  datosModuleSession.getTotalSessiones() ) {
				arraySessiones.add(modSession);
			}
			
			if ( registroCero == datosModuleSession.getTotalSessiones() ) {
				arraySessiones.add(modSession);
				moduleSession.setSesiones(arraySessiones);
				arrayModuleSession.add(moduleSession);
				arraySessiones = new ArrayList<IkSessionModDto>();
				registroCero = 0;
			}
		}
		
		return arrayModuleSession;
	}
	
	
	//---- Procedimiento para eliminar todo el modulo;
	
	/***
	 * Valida si existe el modulo
	 * @param cveModule
	 * @return
	 * @throws Exception
	 */
	private int moduleExists ( Integer cveModule ) throws Exception {
		return this.ikModuleDao.moduleExists(cveModule);
	}
	
	/***
	 * Obtiene una lista de registros "Sessiones" de acuerdo a la clave del modulo
	 * @param cveModule
	 * @return
	 * @throws Exception 
	 */
	private ArrayList<Integer> getAllSessionesByModule (Integer cveModule ) throws Exception{
		ArrayList<Integer> arraySessione = new ArrayList<>();
		arraySessione = this.ikModuleDao.getAllSessionesByModule(cveModule);
		return arraySessione;
	}
	
	/***
	 * Obtiene las sentencias asociadas a las sessiones:
	 * A la vez, elimina ModuleSession, Session, module
	 * @param cveModule
	 * @param cveSession
	 * @return
	 * @throws Exception
	 */
	private ArrayList<Integer> getAllSentencesBySessionAndModule (Integer cveModule, Integer cveSession )  throws Exception {
		ArrayList<Integer> arraySentences = new ArrayList<>();
		arraySentences = this.ikModuleDao.getAllSentencesBySessionAndModule(cveModule, cveSession);
		return arraySentences;
	}
	
	/***
	 * Elimina las Session_Sentences y sentences.
	 * @param cveSession
	 * @param cveSentence
	 * @throws Exception
	 */
	private void deleteSentences (Integer cveSession, Integer cveSentence )  throws Exception {
		this.ikModuleDao.deleteSenteceModuleSession(cveSession, cveSentence);
	}
	
	
}
