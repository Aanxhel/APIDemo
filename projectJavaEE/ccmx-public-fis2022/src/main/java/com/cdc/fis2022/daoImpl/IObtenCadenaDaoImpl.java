package com.cdc.fis2022.daoImpl;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.cdc.fis2022.constantes.Constantes;
import com.cdc.fis2022.dao.GenericDao;
import com.cdc.fis2022.dao.IObtenCadenaDao;


@Repository
public class IObtenCadenaDaoImpl extends GenericDao implements IObtenCadenaDao{
	
	@SuppressWarnings("unused")
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final Logger logger = LogManager.getLogger("FIS-2022");
	
	@Override
	public String getCadenaDao(Long folioConsulta, Long npersCve) { 
		// en este punto ya se envian seteados, el valor que si viene y el 0 en el otro que no viene
		String cadena = "";	
		try {
			SimpleJdbcCall llamaSP = new SimpleJdbcCall(getJdbcTemplate())	
					.withSchemaName(Constantes.SCORE_FIS_SCHEMA.value.toString())
					.withCatalogName(Constantes.SCORE_FIS_PKG.value.toString())
//					.withProcedureName(dbSPCadena)
					.withFunctionName(Constantes.SCORE_FIS_FNCN.value.toString())
					.declareParameters(new SqlParameter(Constantes.SCORE_PARAM_DAO_1.value.toString(), Types.NUMERIC),
					new SqlParameter(Constantes.SCORE_PARAM_DAO_2.value.toString(), Types.NUMERIC),
					new SqlOutParameter(Constantes.SCORE_PARAM_DAO_3.value.toString(), Types.VARCHAR));
			
			Map<String, Number> parametros = new HashMap<String, Number>();
			parametros.put(Constantes.SCORE_PARAM_DAO_1.value.toString(), folioConsulta);
			parametros.put(Constantes.SCORE_PARAM_DAO_2.value.toString(), npersCve);	
			
			Map map = llamaSP.execute(parametros);
			String variablesCalculadas = (String) map.get(Constantes.SCORE_PARAM_DAO_3.value.toString());	
			if(!variablesCalculadas.equals(Constantes.SCORE_BAD_RESPONSE_CHAIN.value.toString())){ //36 ||	
				cadena = variablesCalculadas;
			}else {
				cadena = Constantes.SCORE_NOT_CADENA.value.toString();
				logger.info("NOTCADENA");
			}	
		} catch (Exception e) {
			logger.info("Error en el SP de devolver Cadena");
			e.printStackTrace();
			logger.info(e.getMessage()+" === "+e.getCause());
		}
		
		return cadena;
	} 
	
}
