package com.dev.Support.Networking.templates;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.dev.Support.Networking.beans.GenericResponse;
import com.dev.Support.Networking.beans.ResponseRestErrorHandler;
import com.dev.Support.beans.ErrorDTO;

public abstract class RestTemplateGenericSSL {
	
	protected RestTemplate restTemplateSSL = new RestTemplate(SSLClientFactory.getClientHttpRequestFactory());
	
	/**
	 * Método para realizar una petición en cualquier verbo
	 * 
	 * @param endpoint    String, url del servicio a invocar por http o https
	 * @param entity      HttpEntity<K>, Entidad de la petición http, aqui pueden ir
	 *                    los Headers (HttpHeaders)
	 * @param method      HttpMethod, Vebo de la petición
	 * @param type        Class<T>, Variables de la url
	 * @param serviceName String, Nombre del servicio
	 */
	protected <T, K> GenericResponse<T> requestSSL(String endpoint, HttpEntity<K> entity, HttpMethod method,
			Class<T> type, String serviceName) {
		List<ErrorDTO> errors = new ArrayList<ErrorDTO>();
		ResponseRestErrorHandler errorHandler = new ResponseRestErrorHandler();
		try {
			restTemplateSSL.setErrorHandler(errorHandler);
			ResponseEntity<T> response = restTemplateSSL.exchange(endpoint, method, entity, type);

			HttpStatus status = response.getStatusCode();
			if (status == HttpStatus.OK || status == HttpStatus.NO_CONTENT) {
				String message = "Operación Exitosa";
				return new GenericResponse<T>(status.value(), true, message, null, response.getBody());
			} else {
				errors.add(new ErrorDTO(errorHandler.getError(), errorHandler.getCodigo()));
				return new GenericResponse<T>(status.value(), false,
						"No se puedo invocar el servicio de " + serviceName, errors, null);
			}
		} catch (RestClientException e) {
			errors.add(new ErrorDTO(errorHandler.getError(), errorHandler.getCodigo()));
			return new GenericResponse<T>(Integer.valueOf(errorHandler.getCodigo()), false,
					"No se puedo invocar el servicio de " + serviceName, errors, null);
		} catch (Exception e) {
			errors.add(new ErrorDTO(errorHandler.getError(), errorHandler.getCodigo()));
			return new GenericResponse<T>(Integer.valueOf(errorHandler.getCodigo()), false,
					"No se puedo invocar el servicio de " + serviceName, errors, null);
		}
	}

}
