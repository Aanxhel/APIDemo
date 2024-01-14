package com.cdc.fis2022.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cdc.Support.Authenticate.beans.AuthenticateData;
import com.cdc.Support.exceptions.MatchServiceException;
import com.cdc.fis2022.beans.BeanAccesN;
import com.cdc.fis2022.exceptions.BadRequestException;
import com.cdc.fis2022.services.IScoreFisService;


@RestController
public class FisController {
	

	@Autowired
	private IScoreFisService sFisService;
	
	
	
	private static final Logger logger = LogManager.getLogger("FIS2022Controller");
		
	
	@RequestMapping(value = "/hola", method = RequestMethod.GET)
	public ResponseEntity<String> metodo(){
		
		logger.info("entre al metodo HOLA ...");
		return ResponseEntity.ok("Eres el mejor Sayayin .... :) OGN");
	}
	
	
	@RequestMapping(value = "/obtenerScore", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getScore(@RequestHeader HttpHeaders headers, @Valid  @RequestBody String requestFis) throws BadRequestException, MatchServiceException, com.cdc.Support.exceptions.BadRequestException{
		
		logger.info("request en el controlador: "+requestFis);
		
		AuthenticateData keycloak = new AuthenticateData();
//		BeanAccesN keysNgnx = new BeanAccesN();
		
	    keycloak.setNumLenderFather(headers.get("Otorgante").get(0).toString());
		keycloak.setNumUserId(headers.get("Usuario").get(0).toString());
		
//		keysNgnx.setDEVOPS_APP_NAME(headers.get("X-DEVOPS-APPNAME").get(0).toString());
//		keysNgnx.setDEVOPS_AUTH(headers.get("X-DEVOPS-AUTH").get(0).toString());

		
		ResponseEntity<Object> entity = null;
		
		entity = this.sFisService.scoreRequestService(requestFis, keycloak);
		return entity;
		
	}

	
	
	
	
	
	
	
	
	
/*	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<Object> userExceptionNotFound(MethodArgumentNotValidException ex, WebRequest request){
		
		final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		
		final List<ErrorDTO> erroresDTO = new ArrayList<>();
		
		for(FieldError fieldError : fieldErrors) {
			
			final String field = fieldError.getField();
			
			final String message = fieldError.getDefaultMessage();
			
			final ErrorDTO errorDTO = ErrorDTO.builder().codigo("400").mensaje("El atributo: "+field+", "+message).build();
			
			erroresDTO.add(errorDTO);
		}

		
		GenericResponse<List<CustomFieldError>> badRequestGenericResponse = new GenericResponse<List<CustomFieldError>>(400, false, Constantes.SCORE_BAD_REQUEST.value.toString(),erroresDTO,null);
		
		return ResponseEntity.badRequest().body(badRequestGenericResponse);
	}
	
	*/
	
/*
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {

    	       logger.error("Entre a una excepcion Contorlada del controlador, algo salio mal .... ");

        GenericResponse<List<CustomFieldError>> badRequestGenericResponse = new GenericResponse(400,false,Constantes.SCORE_ERROR_CALL_SERVER.value.toString(),null,ex.getMessage());  //ex.getMessage()   "persona NO existe"
    
        return ResponseEntity.badRequest().body(badRequestGenericResponse);
    }
	*/
	
	
	
	
	
	
	
	
	
	
	

}
