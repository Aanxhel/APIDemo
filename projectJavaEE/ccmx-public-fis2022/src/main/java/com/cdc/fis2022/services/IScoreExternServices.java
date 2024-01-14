package com.cdc.fis2022.services;

import org.springframework.http.ResponseEntity;

import com.cdc.fis2022.beans.BeanAccesN;
import com.cdc.fis2022.beans.Chain;
import com.cdc.fis2022.beans.ResponseScoreExtern;

public interface IScoreExternServices {
	
	ResponseEntity<ResponseScoreExtern> sendCadenagetScore(Chain chain, BeanAccesN keyA);

}
