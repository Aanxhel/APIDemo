/**
 * 
 */
package com.ikon.process.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ikon.process.service.ProcessFrameANeg;

/**
 * Encargado de establecer comunicación con la capa de negocio
 * @author icb_ipsra
 *
 */
@RestController
@RequestMapping (value= "/frame/v1")
public class FrameAController {

	private static final Logger LOGG = LoggerFactory.getLogger(FrameAController.class);
	
	@Autowired
	private ProcessFrameANeg processFrameANeg;

	@RequestMapping(value = "/datosFrame", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> busquedaDatosFrame ( @RequestBody String requestJson ) {
		ResponseEntity<Object>  entity = null;
		LOGG.info("[::::::::::. { }  .::::::::]", " busquedaDatosFrame ");
		entity = this.processFrameANeg.frameBusqueda(requestJson);
		return entity;
	}
	
	@RequestMapping(value = "/a/insert", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> insertaRegistro ( @RequestBody String requestFrameAJson ) {
		ResponseEntity<Object>  entity = null;
		LOGG.info("[::::::::::. { }  .::::::::]", " busquedaDatosFrame ");
		entity = this.processFrameANeg.agregaDatosFrameA(requestFrameAJson);
		return entity;
	}

	
	
	
	
	
	
	
	
}
