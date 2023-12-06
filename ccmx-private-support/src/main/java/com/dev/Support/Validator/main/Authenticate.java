package com.dev.Support.Validator.main;

import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import com.dev.Support.Authenticate.beans.AuthenticateData;
import com.dev.Support.Authenticate.beans.DatosToken;
import com.dev.Support.Authenticate.beans.KeycloakData;
import com.dev.Support.Authenticate.beans.KeycloakResponse;
import com.google.gson.Gson;

@Component
public class Authenticate {
	
	public String obtieneToken(List<String> authotization) {
		String token = null;
		if (authotization != null && !authotization.isEmpty() && authotization.get(0).startsWith("Bearer ")) {
			token = authotization.get(0).substring(7);
		}
		return token;
	}
	
	public AuthenticateData getDatosToken(String jwtToken) {
		AuthenticateData ldapDatos = new AuthenticateData();
		
		String[] split_string = jwtToken.split("\\.");
        String base64EncodedBody = split_string[1];
        Base64 base64Url = new Base64(true);
        String body = new String(base64Url.decode(base64EncodedBody));
        DatosToken dt = new Gson().fromJson(body, DatosToken.class);
        ldapDatos.setNumUserId(String.valueOf(dt.getId()));
		ldapDatos.setSurnames(String.valueOf(dt.getSub()));
		ldapDatos.setPermissions(dt.getPermissions());
		ldapDatos.setNumLenderFather(dt.getLender());
		
        return ldapDatos;
	}
	
	public KeycloakData getDatosTokenK(String jwtToken) {
		
		String[] split_string = jwtToken.split("\\.");
        String base64EncodedBody = split_string[1];
        Base64 base64Url = new Base64(true);
        String body = new String(base64Url.decode(base64EncodedBody));
        KeycloakData dt = new Gson().fromJson(body, KeycloakData.class);       
        return dt;
	}
	
	public KeycloakResponse getDatosJWTKeycloak(String jwtToken) {
		String[] split_string = jwtToken.split("\\.");
        String base64EncodedBody = split_string[1];
        Base64 base64Url = new Base64(true);
        String body = new String(base64Url.decode(base64EncodedBody));
        KeycloakResponse dt = new Gson().fromJson(body, KeycloakResponse.class);
        return dt;
	}
	
	public String getReino(String ruta) {
		String reino = null;
		if(ruta != null && !"".equals(ruta)) {
			String[] split = ruta.split("/");
			if(split != null && split.length > 0) {
				reino = split[split.length - 1];
			}
		}
		
		return reino;
	}
	
	/**
	 * 
	 * @param url String
	 * @param reino String
	 * @return url
	 */
	public String getUrlKeycloak(String url, String reino) {
		url = url.replace("{{reino}}", reino);
		return url;
	}

}
