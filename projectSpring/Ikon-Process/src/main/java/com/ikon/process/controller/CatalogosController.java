/**
 * 
 */
package com.ikon.process.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ikon.process.json.error.DatosErroresJson;
import com.ikon.process.json.error.ErroresJson;
import com.ikon.process.service.CatHeaderNeg;
import com.ikon.process.service.impl.validaTokenRestImpl;
import com.ikon.process.util.Constantes;

/**
 * 
 * Controller para obtener cada uno de los catalogos.
 * @author icb_ipsra
 *   Eliminar
 */
@RestController
@RequestMapping (value="/cat/v1")
public class CatalogosController {
	private static final Logger LOGG = LoggerFactory.getLogger(CatalogosController.class);
	
	
	@Autowired
	private CatHeaderNeg catHeaderNeg;
	
	@Autowired
	private validaTokenRestImpl validaToken;
	
	/*
	 * 
	 * Obtiene los registros de la tabla, para generar el listado de headers
	 */
	@CrossOrigin(origins = "*", maxAge = 3600 , methods = {RequestMethod.POST})
	@RequestMapping(value = "/header", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> catalagosHeader ( @RequestBody String requestJson ) {
		ResponseEntity<Object>  entity = null;
		LOGG.info("[::::::::::. { }  .::::::::]", "Obtiene header's");
		
		entity = this.catHeaderNeg.catHeader(requestJson);
		
		return entity;
	}
	
	/*
	 * 
	 * Obtiene los registros de la tabla, para generar el listado de headers
	 */
	@CrossOrigin(origins = "*", maxAge = 3600 , methods = {RequestMethod.POST})
	@RequestMapping(value = "/headerTest", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> catalagosHeaderTest (@RequestHeader(value = "Authorization", required = true) String authorization, @RequestBody String requestJson) {
		ResponseEntity<Object>  entity = null;
		LOGG.info("[::::::::::. { }  .::::::::]", "Obtiene header's");
		
		DatosErroresJson errroJson = new DatosErroresJson();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		
		LOGG.info("[::::::::. {} .::::::::]", "Validando TOKEN");
		
		if ( validaToken.obtieneTokenValida(authorization, "{}")) {
			entity = this.catHeaderNeg.catHeader(requestJson);
		} else {
			errores.add(new ErroresJson(Constantes.CODIGO_400_10, Constantes.MENSAJE_400_10));
			errroJson.setErrores(errores);
			entity = ResponseEntity.badRequest().body(errroJson);
		}
		
		return entity;
	}
	
	
}
