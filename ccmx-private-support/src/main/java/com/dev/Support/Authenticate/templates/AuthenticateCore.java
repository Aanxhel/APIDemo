package com.dev.Support.Authenticate.templates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.dev.Support.Authenticate.beans.AuthenticateData;
import com.dev.Support.Authenticate.beans.LdapRequest;
import com.dev.Support.Authenticate.beans.LdapResponse;
import com.dev.Support.Networking.beans.GenericResponse;
import com.dev.Support.Networking.templates.RestTemplateGeneric;
import com.dev.Support.Validator.main.Authenticate;

@Component
public class AuthenticateCore extends RestTemplateGeneric {


	@Autowired
	private Authenticate authenticate;

	/**
	 * Autenticacion por usuario y contraseña o token
	 * @param headers HttpHeaders
	 * @param user String
	 * @param pass String
	 * @param url String (Enviar url de autenticar por usuario y pass, o por token)
	 * @return LdapDatos
	 */
	public AuthenticateData validarUsuarioPassword(HttpHeaders headers, String user, String pass, String url) {
		AuthenticateData ldapDatos = null;

		String token = authenticate.obtieneToken(headers.get("Authorization"));
		if (token != null && !token.isEmpty()) {
			if (this.autenticarToken(token, url)) {
				ldapDatos = authenticate.getDatosToken(token);
			}
		} else if (user != null && !user.isEmpty() && pass != null && !user.isEmpty()) {
			ldapDatos = this.autenticar(user, pass, url);
		}

		return ldapDatos;
	}
	
	/**
	 * Autenticacion por usuario y contraseña
	 * @param user String
	 * @param password String
	 * @param url String
	 * @return LdapDatos
	 */
	public AuthenticateData autenticar(String user, String password, String url) {
		AuthenticateData ldapDatos = new AuthenticateData();

		LdapRequest ldapRequest = createRequestAuth(user, password);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<LdapRequest> entity = new HttpEntity<LdapRequest>(ldapRequest, headers);

		GenericResponse<LdapResponse> response = request(url, entity, HttpMethod.POST, LdapResponse.class,
				AuthenticateCore.class.getName());

		if (response != null && response.getHttpStatus().intValue() == 200) {
			ldapDatos = response.getData().getData();
		}

		return ldapDatos;
	}

	/**
	 * Creación de objeto de autenticacion.
	 * @param user String
	 * @param pass String
	 * @return LdapRequest
	 */
	private LdapRequest createRequestAuth(String user, String pass) {
		LdapRequest ldapRequest = new LdapRequest();
		ldapRequest.setUser(user);
		ldapRequest.setPassword(pass);
		ldapRequest.setNewLdap(1l);
		return ldapRequest;
	}

	/**
	 * Autenticacion por token
	 * @param token String
	 * @param String url
	 * @return Boolean
	 */
	public Boolean autenticarToken(String token, String url) {
		Boolean isValidToken = false;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Bearer " + token);
		HttpEntity<LdapRequest> entity = new HttpEntity<LdapRequest>(headers);

		GenericResponse<LdapResponse> response = request(url, entity, HttpMethod.POST, LdapResponse.class,
				AuthenticateCore.class.getName());

		if (response != null && response.getHttpStatus().intValue() == 200) {
			isValidToken = true;
		}

		return isValidToken;
	}
}
