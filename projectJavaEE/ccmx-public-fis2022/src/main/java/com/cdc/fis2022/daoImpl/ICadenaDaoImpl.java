package com.cdc.fis2022.daoImpl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.cdc.fis2022.beans.DataA;
import com.cdc.fis2022.constantes.Constantes;
import com.cdc.fis2022.dao.GenericDao;
import com.cdc.fis2022.dao.ICadenasDao;
import com.cdc.fis2022.mapper.FisMapperD;

import oracle.jdbc.internal.OracleTypes;

@Repository
public class ICadenaDaoImpl extends GenericDao implements ICadenasDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Value("${id.app.data}")
	public Integer id_app_data;
	

	private static final Logger logger = LogManager.getLogger("FIS-GET-DATA");
	
	
	@Override
	public List<DataA> getData() {
		
//		DataA getDatos = new DataA();
		List<DataA> getDatos = new ArrayList<DataA>();
		try {
			
			SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate())
					.withSchemaName(Constantes.SCORE_FIS_SCHEMA.value.toString())
					.withProcedureName(Constantes.SCORE_FIS_SP.value.toString())
					.declareParameters(new SqlParameter("IN_ID_KEY", Types.INTEGER),
							new SqlOutParameter("MENSAJE_ERROR", OracleTypes.VARCHAR),
							new SqlOutParameter("OUT_DATOS_FIP", OracleTypes.CURSOR, new FisMapperD()) );
			
			logger.info("id data => "+id_app_data);
			
			Map<String, Integer> dates = new HashMap<String, Integer>();
			dates.put("IN_ID_KEY", id_app_data);
				
			logger.info("dates a enviar a ejecutar => "+dates);
			Map map = call.execute(dates);
			
			logger.info("map despues de la ejecuccion => "+map);
			
			String error = (String) map.get("MENSJAE_ERROR");
			
			if(error == null){
				getDatos = (List<DataA>) map.get("OUT_DATOS_FIP");
				
			}else {
				getDatos = (List<DataA>) map.get("MENSJAE_ERROR");
			}
		
			
		}catch(Exception e) {
				e.printStackTrace();
				logger.info("error al invocar el SP de getData");
		}
		
		return getDatos;
	}
	

}
