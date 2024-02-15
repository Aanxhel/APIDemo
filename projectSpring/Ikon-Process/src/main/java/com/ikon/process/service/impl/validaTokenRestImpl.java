/**
 * 
 */
package com.ikon.process.service.impl;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ikon.process.util.Constantes;

/**
 * @author IsraelC
 *
 */
@Component
public class validaTokenRestImpl {
	private static final Logger LOGG = LoggerFactory.getLogger(validaTokenRestImpl.class);
	
	@Value("${path.url.token}")
	private String urlToken;
	
	
	
	/***
	 * Valida la existencia del token. 
	 * @param headers  --> HttpHeaders headers,
	 * @return
	 */
	public boolean obtieneTokenValida (String authorization, String requestJson) {
		boolean respuestaToken  = false;
			
		if (authorization != null && !authorization.isEmpty() && authorization.startsWith(Constantes.TOKEN_BEARER) ) {
			respuestaToken = this.autenticarToken(authorization);
		}
			
		return respuestaToken;
	}
	
	
	/***
	 * Verifica que el token sea valido
	 * 
	 * @param token
	 * @return true => TOKEN SI valido false => TOKEN NO valido
	 */
	private Boolean autenticarToken(String token) {
		Boolean isValidToken = false;
		Long inicio = System.currentTimeMillis();

		RestTemplate  restTemplate = new RestTemplate();
		try {

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add(Constantes.TOKEN_AUTHORIZATION,token);
			
			//HttpEntity<String> entity = new HttpEntity<String>(headers);
			
			Map<String, Object> map = new HashMap<>();
			map.put("version", "1");
			
			HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
			
			ResponseEntity<String> response = restTemplate.postForEntity(urlToken, entity, String.class);

			if (response != null && response.getStatusCode().value() == 200l) {
				isValidToken = true;
			}
			
		} catch (Exception e) {
			LOGG.info("[:::::::: {}{}{} ::::::::::]", "Error consultando TOKEN"," ", e.getMessage());
		}
		LOGG.info("[:::::::: {}{}{} ::::::::]", "Tiempo TOKEN "," ", (System.currentTimeMillis() - inicio) );
		
		return isValidToken;
	}
	
}
