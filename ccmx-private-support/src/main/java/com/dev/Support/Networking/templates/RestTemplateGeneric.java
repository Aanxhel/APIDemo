package com.dev.Support.Networking.templates;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.dev.Support.Networking.beans.GenericResponse;
import com.dev.Support.Networking.beans.ResponseRestErrorHandler;
import com.dev.Support.beans.ErrorDTO;

public abstract class RestTemplateGeneric {	

	protected RestTemplate restTemplate = new RestTemplate();
	
	/**
	 * Método para realizar una petición en cualquier servico REST con respuesta tipada.
	 * @param endpoint String, url del servicio a invocar por http o https
	 * @param entity HttpEntity<K>, Entidad de la petición http, aqui pueden ir los Headers (HttpHeaders)
	 * @param method HttpMethod, Vebo de la petición
	 * @param responseType Class<T>, Tipo de dato para la respuesta.
	 * @param serviceName String, Nombre del servicio
	 * @return GenericResponse<T> 
	 */
	protected <T, K> GenericResponse<T> request(String endpoint, HttpEntity<K> requestEntity, HttpMethod httpMethod, Class<T> responseType, String serviceName) {
		List<ErrorDTO> errors = new ArrayList<ErrorDTO>();
		ResponseRestErrorHandler errorHandler = new ResponseRestErrorHandler();
		
		try {
			restTemplate.setErrorHandler(errorHandler);
			ResponseEntity<T> response = restTemplate.exchange(endpoint, httpMethod, requestEntity, responseType);

			HttpStatus status = response.getStatusCode();
			if (status == HttpStatus.OK || status == HttpStatus.NO_CONTENT) {
				String message = "Operación Exitosa";
				return new GenericResponse<T>(status.value(), true, message , null, response.getBody());
			} else{
				errors.add(new ErrorDTO(errorHandler.getError(),errorHandler.getCodigo()));
				return new GenericResponse<T>(status.value(), false, "No se logró invocar el servicio de " + serviceName, errors, null);
			}
		} catch (RestClientException e){
			errors.add(new ErrorDTO(errorHandler.getError(), errorHandler.getCodigo()));
			return new GenericResponse<T>(Integer.valueOf(errorHandler.getCodigo()), false, "No se logró invocar el servicio de " + serviceName, errors, null);
		} catch (Exception e){
			errors.add(new ErrorDTO(errorHandler.getError(), errorHandler.getCodigo()));
			return new GenericResponse<T>(Integer.valueOf(errorHandler.getCodigo()), false, "No se logró invocar el servicio de " + serviceName, errors, null);
		}
	}

	/**
	 * Método para realizar una petición en cualquier servico REST con respuesta tipada y parametrizada T<K>.
	 * @param endpoint String, url del servicio a invocar por http o https
	 * @param entity HttpEntity<K>, Entidad de la petición http, aqui pueden ir los Headers (HttpHeaders)
	 * @param method HttpMethod, Vebo de la petición
	 * @param type ParameterizedTypeReference<GenericResponse<T>>, Tipo de dato parametrizado para la respuesta.
	 * @param serviceName String, Nombre del servicio
	 * @return GenericResponse<T> 
	 */
	protected <T, K> GenericResponse<T> requestParameterized(String endpoint, HttpEntity<K> requestEntity, HttpMethod httpMethod, String serviceName) {
		List<ErrorDTO> errors = new ArrayList<ErrorDTO>();
		ResponseRestErrorHandler errorHandler = new ResponseRestErrorHandler();
		ParameterizedTypeReference<GenericResponse<T>> typeRef = new ParameterizedTypeReference<GenericResponse<T>>() {};
		
		try {
			restTemplate.setErrorHandler(errorHandler);
			ResponseEntity<GenericResponse<T>> response = restTemplate.exchange(endpoint, httpMethod, requestEntity, typeRef);

			if (response.getStatusCode() == HttpStatus.OK || response.getStatusCode() == HttpStatus.NO_CONTENT) {
				return response.getBody();
			} else{
				errors.add(new ErrorDTO(errorHandler.getError(),errorHandler.getCodigo()));
				return new GenericResponse<T>(response.getStatusCode().value(), false, "No se logró invocar el servicio de " + serviceName, errors, null);
			}
		} catch (RestClientException e){
			errors.add(new ErrorDTO(errorHandler.getError(), errorHandler.getCodigo()));
			return new GenericResponse<T>(Integer.valueOf(errorHandler.getCodigo()), false, "No se logró invocar el servicio de " + serviceName, errors, null);
		} catch (Exception e){
			errors.add(new ErrorDTO(errorHandler.getError(), errorHandler.getCodigo()));
			return new GenericResponse<T>(Integer.valueOf(errorHandler.getCodigo()), false, "No se logró invocar el servicio de " + serviceName, errors, null);
		}
	}

}
