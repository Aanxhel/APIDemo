package com.cdc.fis2022.servicesImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.cdc.Support.Networking.beans.GenericResponse;
import com.cdc.Support.Networking.templates.RestTemplateGenericSSL;
import com.cdc.fis2022.beans.Chain;
import com.cdc.fis2022.beans.DataA;
import com.cdc.fis2022.beans.ResponseScoreExtern;
import com.cdc.fis2022.constantes.Constantes;
import com.cdc.fis2022.dao.ICadenasDao;

@Service
public class ScoreExternServicesImplSSL extends RestTemplateGenericSSL{
	
	@Value("${url.score.extern}")
	private String urlScoreExtern;
	
	@Autowired
	private ICadenasDao cadenasDao;
	
	
	private static final Logger logger = LogManager.getLogger("ScoreExternServiceImplSSL");
	
	
	public GenericResponse<ResponseScoreExtern> sendCadenagetScore(Chain chain) {
		
		DataA data = new DataA();
		
		data.setAppName(cadenasDao.getData().get(0).getAppName());
		data.setAppAut(cadenasDao.getData().get(0).getAppAut());
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.set(Constantes.SCORE_EXT_HD_NAME.value.toString(), data.getAppName());
		httpHeaders.set(Constantes.SCORE_EXT_HD_AN.value.toString(), data.getAppAut()); 
		
		chain.setChain("{\"cadenaVariables\": \""+chain.getChain()+"\"}");
		

		
		HttpEntity<Object> entity = new HttpEntity<Object>(chain.getChain(),httpHeaders);
		
		
		
		return requestSSL(urlScoreExtern, entity, HttpMethod.POST, ResponseScoreExtern.class, ResponseScoreExtern.class.getName()); //ScoreExternServicesImplSSL
		
	}

}
