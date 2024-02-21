package com.cdc.fis2022.servicesImpl;




import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdc.fis2022.constantes.Constantes;
import com.cdc.fis2022.dao.IObtenCadenaDao;
import com.cdc.fis2022.services.ICadenaServices;

@Service
public class CadenaServicesImpl implements ICadenaServices{
	
	
	@Autowired
	private IObtenCadenaDao getCadenaDao;
	
	
	private static final Logger logger = LogManager.getLogger("CadenaServiceImpl");
	

	@Override
	public String getCadenaReadyScore(Long folioConsulta, Long npersCve) {
		
		String obtenCadenaDao = "";

		logger.info("recibo folioConsulta => "+folioConsulta+" npersCve => "+npersCve);
			
		try {
			if(folioConsulta == 0 && npersCve > 0) { // tiene npersCVe 
				
				obtenCadenaDao = getCadenaDao.getCadenaDao(0L, npersCve); // devuelve la cadena o un NOTCADENA desde el dao
				logger.info("obtenCadenaDao con NpersCVe => "+obtenCadenaDao);
				if(obtenCadenaDao.equals(Constantes.SCORE_NOT_CADENA.value.toString())) {
					
					obtenCadenaDao = Constantes.SCORE_NOT_GET_CHAIN_VALUES_DAO.value.toString();	
					logger.info("NO SE PUDO OBTENER LA CADENA A ENVIAR A SCORE EXTERNAL ");
				}
			}
		}catch(Exception e) {
			logger.info("posiblemente el cp no es correcto");
			obtenCadenaDao = "NO posiblemente el cp no es correcto";
		}	
				
		
		try {
			if(npersCve == 0 && folioConsulta > 0) { // tiene folioConsulta
				
				obtenCadenaDao = getCadenaDao.getCadenaDao(folioConsulta, 0L); // devuelve la cadena o un NOTCADENA desde el dao
				logger.info("obtenCadenaDao con folioConsulta => "+obtenCadenaDao);
				if(obtenCadenaDao.equals(Constantes.SCORE_NOT_CADENA.value.toString())) {
					
					obtenCadenaDao = Constantes.SCORE_NOT_GET_CHAIN_VALUES_DAO.value.toString();
					logger.info("NO SE PUDO OBTENER LA CADENA A ENVIAR A SCORE EXTERNAL ");
				}
			}
		}catch(Exception e) {
			logger.info("posiblemente el cp no es correcto");
			obtenCadenaDao = "NO posiblemente el cp no es correcto";
		}
		
		return obtenCadenaDao;
	}


}
