package com.cdc.fis2022.services;

import org.springframework.http.ResponseEntity;

import com.cdc.Support.Authenticate.beans.AuthenticateData;
import com.cdc.Support.exceptions.BadRequestException;
import com.cdc.Support.exceptions.MatchServiceException;


public interface IScoreFisService { // clase que sera llamada desde el controlador
	
	public ResponseEntity<Object> scoreRequestService(String dgRequest, AuthenticateData keyclock) throws BadRequestException, MatchServiceException, com.cdc.fis2022.exceptions.BadRequestException;
}
