package com.dev.Support.Authenticate.templates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import com.dev.Support.Authenticate.beans.AuthenticateData;
import com.dev.Support.Authenticate.beans.KeycloakData;
import com.dev.Support.Authenticate.beans.KeycloakResponse;
import com.dev.Support.Networking.beans.GenericResponse;
import com.dev.Support.Networking.templates.RestTemplateGenericSSL;
import com.dev.Support.Validator.main.Authenticate;

@Component
public class AuthenticateKeycloak extends RestTemplateGenericSSL {

	@Autowired
	private Authenticate authenticate;

	public AuthenticateData validarPorKeycloak(HttpHeaders headers, String url) {
		KeycloakData keycData = null;
		AuthenticateData authData = null;

		String token = authenticate.obtieneToken(headers.get("Authorization"));
		if (token != null && !token.isEmpty()) {
			keycData = this.autenticarToken(token, url);
		}

		if (keycData != null) {
			authData = new AuthenticateData();
			authData.setNumUserId(keycData.getNumeroEmpleado());
			authData.setNumLenderFather(keycData.getOtorgantePadre());
			authData.setToken(token);
			authData.setUsername(keycData.getUsuario());
		}

		return authData;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public KeycloakData autenticarToken(String token, String urlKeycloak) {
		KeycloakData keycloakDatos = null;

		KeycloakResponse datosJWT = authenticate.getDatosJWTKeycloak(token);

		if (datosJWT.getRuta() != null) {
			String url = authenticate.getUrlKeycloak(urlKeycloak, authenticate.getReino(datosJWT.getRuta()));
			try {

				HttpHeaders headers = new HttpHeaders();
				headers.add("Authorization", "Bearer " + token);
				HttpEntity entity = new HttpEntity(headers);

				GenericResponse<KeycloakResponse> response = requestSSL(url, entity, HttpMethod.POST, KeycloakResponse.class,
						AuthenticateCore.class.getName());

				if (response != null) {
					keycloakDatos = response.getData().getKeycloakdata();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return keycloakDatos;
	}
}
