package com.cdc.fis2022.servicesImpl;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cdc.Support.Authenticate.beans.AuthenticateData;
import com.cdc.Support.Facturacion.beans.FacturacionRequest;
import com.cdc.Support.Facturacion.beans.FacturacionResponse;
import com.cdc.Support.Match.beans.MatchRequest;
import com.cdc.Support.Networking.beans.GenericResponse;
import com.cdc.Support.beans.ErrorDTO;
import com.cdc.Support.exceptions.MatchServiceException;
import com.cdc.fis2022.Constantes.Constantes;
import com.cdc.fis2022.beans.BeanAccesN;
import com.cdc.fis2022.beans.Chain;
import com.cdc.fis2022.beans.Persona;
import com.cdc.fis2022.beans.RequestFolioConsulta;
import com.cdc.fis2022.beans.ResponseScoreExtern;
import com.cdc.fis2022.exceptions.BadRequestException;
import com.cdc.fis2022.services.ICadenaServices;
import com.cdc.fis2022.services.IMatchServices;
import com.cdc.fis2022.services.IScoreExternServices;
import com.cdc.fis2022.services.IScoreFisService;
import com.cdc.fis2022.services.ValidarRequestService;
import com.cdc.fis2022.util.UtilJsonGeneric;
import com.cdc.validacionescore.response.MatchResponse;
import com.google.gson.Gson;



@Service
public class ScoreFisServiceImpl implements IScoreFisService{ // clase que sera llamada desde el controlador
	
	
	@Autowired
	private IMatchServices matchService;
	
	@Autowired 
	private ICadenaServices cadenaService;
	
	@Autowired
//	private IScoreExternServices scoreExternService;
	private ScoreExternServicesImplSSL scoreExternService;
	
	@Autowired
	private ValidarRequestService validarRequestService;
	
	@Value("${ruta.facturacion}")
	private String urlFacturacion; 
	

	private static final Logger logger = LogManager.getLogger("ScoreFisServiceImpl");
	
	
	
	@Override
	public ResponseEntity<Object> scoreRequestService(String request, AuthenticateData keyclock) throws BadRequestException, MatchServiceException {

		GenericResponse<ResponseScoreExtern> responseScoreExtern = null;
		// request folioConsulta
		if(request != null && !request.equals("") && request.contains(Constantes.SCORE_VALUE_COMPARE.value.toString())) {
			
				// va ha validar que la request sea correcta
				if(UtilJsonGeneric.validaRequestObject(request, RequestFolioConsulta.class)) {
					
					// convierte la request a Objeto
					RequestFolioConsulta requestFolio = new RequestFolioConsulta();
					requestFolio = (RequestFolioConsulta) UtilJsonGeneric.convierteJsonObject(request, RequestFolioConsulta.class);
					
					
					// ya se valido la request y se asegura que sea la request de Folio
					logger.info("requestFolio => "+requestFolio);		
					
					// se va ha validar la request de folio que contenga datos
					if(validarRequestService.validaRequestFolios(requestFolio) != null) {
						return new ResponseEntity<Object>(validarRequestService.validaRequestFolios(requestFolio), HttpStatus.BAD_REQUEST);
					}		
					
					// 2.- ENVIAR FOLIOCONSULTA AL SERVICIO QUE IMPLEMENTA EL SP para devolver la cadena
					
				//	String getCadenaHere;
					Chain getCadenaHere = new Chain();		
			
				//	getCadenaHere = this.cadenaService.getCadenaReadyScore(requestFolio.getFolioConsulta(), 0L); // 143071929L
					getCadenaHere.setChain(this.cadenaService.getCadenaReadyScore(requestFolio.getFolioConsulta(), 0L)); 
								
					if(getCadenaHere.getChain().startsWith(Constantes.SCORE_WORD_NO.value.toString())) {
						
						return new ResponseEntity<Object>(
							   new GenericResponse<Object>(
							   404,false,Constantes.SCORE_FOLIO_NO_EXISTS.value.toString(),null,Constantes.SCORE_NOT_GET_CHAIN_VALUES.value.toString()), HttpStatus.NOT_FOUND);
					}else {		
							//4.-  ENVIAR CADENA A SERVICIO SCORE EXTERN, agregando los accesos NGINX
							logger.info("cadena a enviar al servicio de score, en controller => "+getCadenaHere);

							responseScoreExtern = this.scoreExternService.sendCadenagetScore(getCadenaHere);


							this.getFolioConsultaFacturacion(responseScoreExtern.getData(), request, keyclock, requestFolio.getFolioOtorgante().toString(), null);
						
							if(responseScoreExtern.getData() == null) {	
								
								return new ResponseEntity<>(new ErrorDTO("404","Error al invocar servicio de Score External"), HttpStatus.NOT_FOUND); //responseScoreExtern
							}else {
							
								return new ResponseEntity<>(responseScoreExtern, HttpStatus.OK);		
							}
					}
				}else {
					// Error en el proceso, normlamente de bad request. string por ejemplo en los folios que son Long	
					return new ResponseEntity<Object>(new GenericResponse(400,false,"Error",Arrays.asList(new ErrorDTO("400", "Request malformada")),null), HttpStatus.BAD_REQUEST);	
				}
// ************************************** DIFERNECIA ENTRE REQUESTSES **************************************
			// requestPersona
		 }else if(request != null && !request.equals("") && !request.contains(Constantes.SCORE_VALUE_COMPARE.value.toString())) {
			
				if(UtilJsonGeneric.validaRequestObject(request, Persona.class)) {
					
					Persona datosGenerales = new Persona();
					datosGenerales = (Persona) UtilJsonGeneric.convierteJsonObject(request, Persona.class);

					// validar Request
					if(validarRequestService.validarRequesDG(datosGenerales) != null) {
						return new ResponseEntity<Object>(validarRequestService.validarRequesDG(datosGenerales), HttpStatus.BAD_REQUEST);
					}	
					// 2.- HACER MATCH
					MatchResponse matchResponse;
					matchResponse = matchService.hacerMatchPorDatosGenerales(datosGenerales);// datosGrales es obj de tipo Persona		
					if(matchResponse == null) {  //matchResponse.getNperscve()
						logger.info("matchResponse => "+matchResponse);
						return new ResponseEntity<>(new GenericResponse(400,false,Constantes.SCORE_PERSON_NOT_FOUND.value.toString(),null,null) ,HttpStatus.BAD_REQUEST);
						
	//					return new ResponseEntity<>(new GenericResponse(400,false,"Bad Request",null,"Datos Incorrectos") ,HttpStatus.BAD_REQUEST);
					}else{
						
						// 3.- ENVIAR NPERSCVE AL SERVICIO QUE IMPLEMENTA EL SP para devolver la cadena
						Chain getCadenaHere = new Chain();                    // 143071929L
						getCadenaHere.setChain(this.cadenaService.getCadenaReadyScore(0L, matchResponse.getNperscve()));
	
						if(getCadenaHere.getChain().startsWith(Constantes.SCORE_WORD_NO.value.toString())) {
						
							return new ResponseEntity<Object>(
								   new GenericResponse<Object>(
									   404,false,Constantes.SCORE_NOT_GET_CHAIN_VALUES.value.toString(),null,null), HttpStatus.NOT_FOUND);
						}else {
								//4.-  ENVIAR CADENA A SERVICIO SCORE EXTERN								
//			
								responseScoreExtern = this.scoreExternService.sendCadenagetScore(getCadenaHere);

								// INVOCAR FACTURACION PARA AGREGAR FOLIO ORIGINAL
								this.getFolioConsultaFacturacion(responseScoreExtern.getData(), request, keyclock, null, matchResponse.getNperscve().toString());
								
								if(responseScoreExtern.getData() == null) {							
									return new ResponseEntity<>(new ErrorDTO("404","Error al invocar servicio de Score External"), HttpStatus.NOT_FOUND); //responseScoreExtern
								}else {
								
									return new ResponseEntity<>(responseScoreExtern, HttpStatus.OK);
								}	
						}
					}
				}else {  // else de la validacion de la request Persona		
					return new ResponseEntity<Object>(new GenericResponse(400,false,"Error",Arrays.asList(new ErrorDTO("400", "Request malformada DG")),null), HttpStatus.BAD_REQUEST);
				}
		}
		return new ResponseEntity<>(responseScoreExtern, HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	
	
	
	// METODOS PARA FACTURACION  ResponseSEON response, RequestSEON request, AuthenticateData keycloak, String folioOtorgante
	public FacturacionRequest ArmarRequestFacturacion(ResponseScoreExtern response, Object request, AuthenticateData keycloak, String folioOtorgante, String cvePersona) {
		FacturacionRequest req = new FacturacionRequest();
		
		req.setCveProducto("144");  // preguntar cveProducto de FIS2022  143
		req.setSNombreServer("CORE");
		
		req.setFolioOtorgante(folioOtorgante != null && !"0".equals(folioOtorgante) ? folioOtorgante : "0");
		req.setCvePersona(cvePersona != null && !"0".equals(cvePersona) ? cvePersona : "0");
		req.setCveRetorno("0");
		req.setTiempoBusqIlytics("0");
		req.setTiempoBusqueda("0");
		req.setIndicadorFirma("S");
		req.setTipoMedioConsulta("AP");
		req.setClaveUsuario(keycloak.getNumUserId());
		
		req.setNumeroFirma("CORE"+"-"+folioOtorgante != null ? folioOtorgante : cvePersona);
		req.setClaveOtorganteXML(keycloak.getNumLenderFather());
		req.setPayload("REQUEST=> " + new Gson().toJson(request) + " RESPONSE=> " + new Gson().toJson(response));
//		req.setStatusCode(response != null && response.getError().getCode() != null && response.getError().getCode() > 0 ? String.valueOf(response.getError().getCode()) : "200");

		req.setStatusCode(response != null && response.getScoreNoHit().getExclusion_code() != null && !"".equals(response.getScoreNoHit().getExclusion_code())  ? String.valueOf(response.getScoreNoHit().getExclusion_code()) : "200");
//		req.setStatusCode(null);
		req.setFolio(null);
		
		logger.info("1.- estoy armando la request de facturacion, metodo ArmarRequestFacturacion =>  "+req);
		
		return req;
	}
	
	
	public FacturacionResponse invocaFacturacion(FacturacionRequest req) {
		RestTemplate restTemplate = new RestTemplate();
		FacturacionResponse response = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<FacturacionRequest> entity = new HttpEntity<FacturacionRequest>(req, headers);
		
		try {
			logger.info("2.- voy a entrar al metodo de facturacion, estos son los datos a enviar a facturacion => "+entity+"\n");
			response = restTemplate.postForObject(urlFacturacion, entity, FacturacionResponse.class);
			logger.info("\n datos devueltos del emtodo facturacion => "+response);
			logger.info("");
		} catch (Exception e) {
			logger.info("2.- Error al llamar al servicio de facturacion => "+urlFacturacion+"\n "+e.getMessage()+", \n"
					+ " "+e.getCause());
			e.printStackTrace();
		}
		
		return response;
	}
	
	// 										
	public Long getFolioConsultaFacturacion(ResponseScoreExtern response, Object request, AuthenticateData keycloak, String folioOtorgante, String cvePersona) {
		Long folioConsulta = null;
		
		logger.info("3.- entre a getFolioConsultaFacturacion, invocare a ambos servicios, armar request e invocar facturacion => ");
		FacturacionRequest resFac = this.ArmarRequestFacturacion(response,request, keycloak, folioOtorgante, cvePersona);
		FacturacionResponse invFac = this.invocaFacturacion(resFac);
		
		logger.info("FacturacionRequest resFac => "+resFac);
		logger.info("FacturacionResponse invFac => "+invFac);
		
		if (invFac != null) {
			if (invFac.getFolioRespuesta() != null) {
				folioConsulta = invFac.getFolioRespuesta();
				logger.info("si facturo");
			}else {
				System.out.println("No facturo");
				logger.info("NO facturo ...");
			}
		}
		// este folio es el bueno que devuelve facturacion .......
		return folioConsulta;
	}
	
	

}
