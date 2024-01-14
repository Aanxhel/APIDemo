package com.cdc.fis2022.servicesImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cdc.fis2022.beans.Persona;
import com.cdc.fis2022.services.IMatchServices;
import com.cdc.validacionescore.request.MatchRequest;
import com.cdc.validacionescore.response.MatchResponse;


@Service
public class MatchServicesImpl implements IMatchServices{
	
	@Value("${url.match}")
	private String urlMatch;
	
	@Value("${match.endpoint.consultaPorNpersCve}")
	private String matchXnperscve;
	
	@Value("${match.endpoint.cosultaNOEnriquece}")
	private String matchNoEnriquece;
	
	
	private RestTemplate restTemplate = new RestTemplate();
	
	private static final Logger logger = LogManager.getLogger("MatchServicesImpl en FIS");

	
	@Override
	public MatchResponse hacerMatchPorDatosGenerales(Persona request) {

		MatchRequest matchRequest = new MatchRequest();
		MatchResponse matchResponse = new MatchResponse();
		String urlMatchNO = urlMatch+matchNoEnriquece;
		logger.info("urlMatchNO => "+urlMatchNO);
		
		
		String nombreS = (request.getSegundoNombre() != null && !request.getSegundoNombre().isEmpty())
				? request.getPrimerNombre()+" "+request.getSegundoNombre()
				: request.getPrimerNombre();
		
		String apellidoPaterno = request.getApellidoPaterno();
		String apellidoMaterno = request.getApellidoMaterno();
		
		matchRequest.setNombre(nombreS);
		matchRequest.setApellidoPaterno(apellidoPaterno);
		matchRequest.setApellidoMaterno(apellidoMaterno);
		matchRequest.setFechaNacimiento(request.getFechaNacimiento());
		
		matchRequest.setRfc(request.getRfc());
		matchRequest.setCurp(request.getCurp());
		
		matchRequest.setDireccion(request.getCalle());
		matchRequest.setColonia(request.getColonia());
		matchRequest.setCiudad(request.getCiudad());
		matchRequest.setEstado(request.getEstado());
		matchRequest.setDelegacionMunicipio(request.getMunicipio());
		matchRequest.setCp(request.getCp());
		
		try {
			
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			
			HttpEntity<MatchRequest> entity = new HttpEntity<MatchRequest>(matchRequest, httpHeaders);
			
			ResponseEntity<MatchResponse> responseEntity = restTemplate.postForEntity(urlMatchNO, entity, MatchResponse.class);
			
			if(responseEntity.getStatusCode() == HttpStatus.OK) {
				matchResponse = responseEntity.getBody();
				logger.info("Se logro hacer match correctamente ... "+matchResponse);
				
			}else {
				logger.info("NO se logro hacer match correctamente, no existe: codigo de estado -> "+responseEntity.getStatusCode());
				matchResponse = responseEntity.getBody();
			}
	
		}catch(Exception e) {
			logger.info("Error al invocar el servicio de Match => "+e.getMessage()+"\n"+
					    " cause => "+e.getCause());
			logger.info("matchRequest => "+matchRequest+"\n");
			e.printStackTrace();
		}
		
		logger.info("matchResponse a devovler en MtchServicesImpl => "+matchResponse);
		return matchResponse;
		
	}


}
	
	
	
	
	
	
	
	


