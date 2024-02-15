/**
 * 
 */
package com.ikon.process.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.ikon.process.dao.CatHeaderDao;
import com.ikon.process.dto.CatHeaderDto;
import com.ikon.process.util.Constantes;

/**
 * Esta clase proporcionará el control de todos los catalogos Insert, Update,
 * Select
 * 
 * @author icb_ipsra
 * @Since Agosto 8, 2021
 *
 */
@Repository("catHeaderDao")
public class CatHeaderDaoImpl extends GenericDao implements CatHeaderDao {

	private static final Logger LOGG = LoggerFactory.getLogger(CatHeaderDaoImpl.class);

	@Value("${base.schema}")
	private String baseSchema;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.dao.CatalogosDao@obtieneHeader()
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<CatHeaderDto> obtieneHeader() throws Exception {
		ArrayList<CatHeaderDto> catheader = new ArrayList<>();

		try {

			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(getJdbcTemplate()).withCatalogName("dbikon")
					.withProcedureName("sp_header")
					.returningResultSet("catalogoHeaders", new RowMapper<CatHeaderDto>() {

						@Override
						public CatHeaderDto mapRow(ResultSet rs, int rowNum) throws SQLException {
							return new CatHeaderDto(rs.getInt("cve_header"),rs.getString("des_header"));
						}

					});
			
			Map<String, Object> map = simpleJdbcCall.execute();
			catheader = (ArrayList<CatHeaderDto>) map.get("catalogoHeaders");
			

		} catch (Exception e) {
			LOGG.error("[:::::::::::: obtieneHeader {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		return catheader;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.dao.CatalogosDao@movimientoHeader(java.lang.Integer, java.lang.String, int)
	 */
	public Boolean movimientoHeader (Integer cveHeader, String desHeader, int movimiento  ) throws Exception {
		Boolean mov = false;
		
		try {
			
			SimpleJdbcCall simpleJdbcCall	=	new SimpleJdbcCall(getJdbcTemplate())
							.withCatalogName("dbikon")
							.withProcedureName("sp_mov_header");
			
			SqlParameterSource params = new MapSqlParameterSource()
	                						.addValue("in_mov", movimiento)
	                						.addValue("in_cve_header", cveHeader)
	                						.addValue("in_des_header", desHeader); 
			
			Map<String, Object> mapResults = simpleJdbcCall.execute(params);
			
			if (Integer.parseInt(mapResults.get("out_respuesta").toString()) == Constantes.VALOR_INT_CERO ) {
				mov = true;
			} else {
				LOGG.info("[:::::::::: " + mapResults.get("out_msg_error") != null
						&& !mapResults.get("out_msg_error").equals("") ? mapResults.get("out_msg_error").toString()
								: "" + " ::::::::]"); 
			}
			
		} catch (Exception e) {
			LOGG.error("[::::::::::::movimientoHeader {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		return mov;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.dao.CatalogosDao@existeDesHeader(java.lang.String)
	 */
	public Boolean existeDesHeader ( String desHeader  ) throws Exception {
		Boolean existeHeader = false;

		try {
			
			SimpleJdbcCall simpleJdbcCall	=	new SimpleJdbcCall(getJdbcTemplate())
							.withCatalogName("dbikon")
							.withProcedureName("sp_existe_header");
			
			SqlParameterSource params = new MapSqlParameterSource()
	                							.addValue("in_des_header", desHeader); 
			
			Map<String, Object> mapResults = simpleJdbcCall.execute(params);
			
			existeHeader = Integer.parseInt(mapResults.get("out_existe").toString()) >= Constantes.VALOR_INT_UNO ? true:false;
			
			if (Integer.parseInt(mapResults.get("out_existe").toString()) >= Constantes.VALOR_INT_UNO ) {
				existeHeader = true;
			} else {
				LOGG.info("[:::::::::: " + mapResults.get("out_msg_error").toString() != null
										&& !mapResults.get("out_msg_error").equals("") ? mapResults.get("out_msg_error").toString():" " + " ::::::::]");
			}
			
		} catch (Exception e) {
			LOGG.error("[::::::::::::existeDesHeader {}{}{}  :::::::::]", e.getMessage(), " ", desHeader);
			throw new Exception();
		}
		
		
		return existeHeader;
	}
	
	

}
