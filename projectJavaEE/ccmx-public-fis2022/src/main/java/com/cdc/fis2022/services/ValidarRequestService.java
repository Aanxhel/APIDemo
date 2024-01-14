package com.cdc.fis2022.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import com.cdc.Support.beans.ErrorDTO;
import com.cdc.Support.exceptions.MatchServiceException;
import com.cdc.fis2022.beans.Persona;
import com.cdc.fis2022.beans.RequestFolioConsulta;
import com.cdc.validacionescore.response.MatchResponse;


//import com.cdc.validacionescore.request.MatchRequest;
//import com.cdc.validacionescore.response.MatchResponse;


@Service
public class ValidarRequestService {
	
	private static final Logger logger = LogManager.getLogger("ValidarRequestServide");
	
	
	public String validarCampoNperscve(String request){
		
		
		System.out.println("[::::::request { "+ request +" } :::::]" );
		
		String mensaje = "";
		
		if(request == null || request.equals("")){
			mensaje += "Nperscve";
		}

		if (!mensaje.equals("")) {
			mensaje = "Datos incorrectos, Campo(s) Obligatorio(s): " + mensaje;
		} else {
			mensaje = null;
		}
		return mensaje;
	}
	
	
	public List<ErrorDTO> validarRequesDG(Persona persona){

		 //       logger.info(GetFormatV2.executeLogWithParam("Persona persona",persona));
		logger.info("estoy en el metodo de validarRequestDG, recibo esta Persona => "+persona);

		        List<ErrorDTO> errores = new ArrayList<ErrorDTO>();
		        
		        
		        if(persona == null)
		            errores.add(new ErrorDTO("Persona nula","400"));
		        	logger.info("estoy en persona == "+persona);

		        if(persona.getPrimerNombre() == null || "".equals(persona.getPrimerNombre())) {
		            errores.add(new ErrorDTO("El atributo: primerNombre, no puede venir nulo o vacio","400"));
		    
		        }
		        if(persona.getApellidoPaterno() == null || "".equals(persona.getApellidoPaterno())){
		            errores.add(new ErrorDTO("El atributo: apellidoPaterno, no puede venir nulo o vacio","400"));
		      
		        }
		        if(persona.getApellidoMaterno() == null || "".equals(persona.getApellidoMaterno())) {
		            errores.add(new ErrorDTO("El atributo: apellidoMaterno, no puede venir nulo o vacio","400"));
		      
		        }
		        if(persona.getFechaNacimiento() == null || "".equals(persona.getFechaNacimiento())) {
		            errores.add(new ErrorDTO("El atributo: fechaNacimiento, no puede venir nulo o vacio","400"));
		     
		        }
		        if(persona.getRfc() == null || "".equals(persona.getRfc())) {
		        	errores.add(new ErrorDTO("El atributo rfc, no puede venir nulo o vacio","400"));       }
		           
		        // DOMICILIO
		        if(persona.getCalle() == null || "".equals(persona.getCalle())) 
		            errores.add(new ErrorDTO("El atributo: calle, no puede venir nulo o vacio","400"));
		        if(persona.getColonia() == null || "".equals(persona.getColonia()))
	        		errores.add(new ErrorDTO("El atributo: coloniaPoblacion, no puede venir nulo o vacio","400"));
		        if(persona.getPais() == null || "".equals(persona.getPais()))
		            errores.add(new ErrorDTO("El atributo: pais, no puede venir nulo o vacio","400"));
		        if(persona.getEstado() == null || "".equals(persona.getEstado()))
		            errores.add(new ErrorDTO("El atributo: estado, no puede venir nulo o vacio","400"));
		        if(persona.getMunicipio() == null || "".equals(persona.getMunicipio()))
		            errores.add(new ErrorDTO("El atributo: delegacionMunicipio, no puede venir nulo o vacio","400"));
		        if(persona.getCiudad() == null || "".equals(persona.getCiudad()))
		            errores.add(new ErrorDTO("El atributo: ciudad, no puede venir nulo o vacio","400"));
		        if(persona.getCp() == null || "".equals(persona.getCp())) {
		        	errores.add(new ErrorDTO("El atributo: codigo postal, no puede venir nulo o vacio","400"));
		        }else if(persona.getCp().length() != 5) {
		        	errores.add(new ErrorDTO("El atributo: codigo postal, solo debe tener 5 digitos","400"));
		        }
		        
		        
		        
		        if(!errores.isEmpty()) {
		        	logger.info("estoy en errores !errores.isEmpty()");
//		        	throw new BadRequestException("Error: Bad Request",errores);
		        	return errores;
		        	
		        }
		        
		        return null;
		    }
	
	
	
	public List<ErrorDTO> validaRequestFolios(RequestFolioConsulta request) {
		
		List<ErrorDTO> errores = new ArrayList<ErrorDTO>();
		
		if(request == null) { // Objects.isNull(request);
			errores.add(new ErrorDTO("No viene ningun folio, favor agregarlos en la request","400"));
		}
		
		if(request.getFolioConsulta() == null) {
			errores.add(new ErrorDTO("El atributo: folioConsulta, no puede venir nulo o vacio","400"));
		}else { // si contiene algo, enmtonces validar que sea nuemrico y no String 
			
			String fConsulta = request.getFolioConsulta().toString();
			if(fConsulta.length() < 5) {
				errores.add(new ErrorDTO("El atributo: folioConsulta, no puede tener menos de 5 caracteres", "400"));
			}
			
		}
		
		
		if(request.getFolioOtorgante() == null) {
			errores.add(new ErrorDTO("El atributo: folioOtorgante, no puede venir nulo o vacio","400"));
			
		}else {
			String fOtorgante = request.getFolioOtorgante().toString();
			if(fOtorgante.length() < 5) {
				errores.add(new ErrorDTO("El atributo: folioOtorgante, no puede tener menos de 5 caracteres", "400"));
			}
			
		}
		

/*
		if(request.getFolioConsulta().MIN_VALUE > 5) {
			errores.add(new ErrorDTO("El atributo: folioConsulta, no puede tener menos de 5 digitos ","400"));
		}
		
		if(request.getFolioOtorgante().MIN_VALUE > 5) {
			errores.add(new ErrorDTO("El atributo: folioOtorgante, no puede tener menos de 5 digitos ","400"));
		}
*/		
		if(!errores.isEmpty()) {
			return errores;
		}
		
		return null;
	}
	
	
	
	
	
	
	
	
	public void validarMatchResponse(MatchResponse matchResponse) throws MatchServiceException {

		 //       logger.info(GetFormatV2.executeLogWithParam("MatchResponse matchResponse",matchResponse));

		        List<ErrorDTO> errores = new ArrayList<>();

		        if(matchResponse == null)
		            errores.add(new ErrorDTO("Respuesta del sevicio nulo","500"));

		        if(matchResponse.getSuccess() == null )
		            errores.add(new ErrorDTO("La persona no fue encontrada","404"));

		        if(!errores.isEmpty())
		            throw new MatchServiceException("Error: Bad Request",errores);

		    }
	
	
	
	
	

}
