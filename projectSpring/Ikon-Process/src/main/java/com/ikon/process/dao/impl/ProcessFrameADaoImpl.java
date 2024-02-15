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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.ikon.process.dao.ProcessFrameADao;
import com.ikon.process.dto.DatosFrameDto;
import com.ikon.process.json.request.RequestFrameAJson;


/**
 * Esta clase genera la funcionalidad para el proceso del 
 * frame 1
 * @author icb_ipsra
 * @since Septiembre, 2021
 *
 */
@Repository("processFrameADao")
public class ProcessFrameADaoImpl extends GenericDao implements ProcessFrameADao {

	private static final Logger LOGG = LoggerFactory.getLogger(ProcessFrameADaoImpl.class);
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.dao.ProcessFrameADao@obtieneModulosFrame(int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<DatosFrameDto> obtieneModulosFrame(int cveModulo, int cveTopic) throws Exception {
		ArrayList<DatosFrameDto> gridFrame = new ArrayList<>();
		
		try {
		

			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(getJdbcTemplate()).withCatalogName("dbikon")
					.withProcedureName("sp_busqueda_frame")
					.returningResultSet("datogridFrame", new RowMapper<DatosFrameDto>() {

						@Override
						public DatosFrameDto mapRow(ResultSet rs, int rowNum) throws SQLException {
							return new DatosFrameDto(rs.getInt("clave"),rs.getString("descripcion"));
						}

					});
					
			
			SqlParameterSource params = new MapSqlParameterSource()
					.addValue("in_cve_module", cveModulo)
					.addValue("in_cve_topic", cveTopic);
					
			
			Map<String, Object> map = simpleJdbcCall.execute(params);
			gridFrame = (ArrayList<DatosFrameDto>) map.get("datogridFrame");
			
		} catch (Exception e) {
			 e.printStackTrace() ;
			LOGG.error("[:::::::::::: obtieneModulosFrame {}  :::::::::]" );
			throw new Exception();
		}
		
		return gridFrame;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.dao.ProcessFrameADao@agregaDatosFrameA(com.ikon.process.json.request.RequestFrameAJson)
	 */
	public int agregaDatosFrameA ( RequestFrameAJson request ) throws Exception {
		int insertaRegistro = -1;
		
		try {
			
			SimpleJdbcCall simpleJdbcCall	=	new SimpleJdbcCall(getJdbcTemplate())
					.withCatalogName("dbikon")
					.withProcedureName("sp_insertFramea");
				
			SqlParameterSource params = new MapSqlParameterSource()
												.addValue("in_cve_module", request.getCveModule())
												.addValue("in_cve_topic", request.getCveTopic())
												.addValue("in_des_txt_sent", request.getDesTxtSent().trim().toUpperCase())
												.addValue("in_des_img_sent", request.getPathDesImgSent())
												.addValue("in_des_sound_sent",request.getPathDSoundSent())
												.addValue("in_des_video_sent", request.getPathVideoSent())
												.addValue("in_des_mean_sent", request.getPathMeanSent())
												.addValue("in_des_info_sent", request.getPathDesInfoSent())
												.addValue("in_cve_status",request.getCveStatus());
			
			Map<String, Object> mapResults = simpleJdbcCall.execute(params);
			insertaRegistro = Integer.parseInt(mapResults.get("out_respuesta").toString());
			
			if ( insertaRegistro != 0 ) {
				LOGG.info("[:::::::::: " + mapResults.get("out_msg_error") != null 
						&& !mapResults.get("out_msg_error").equals("") ? mapResults.get("out_msg_error").toString()
						: "" + " ::::::::]");
			} 
			
		} catch (Exception e) {
			LOGG.error("[:::::::::::: agregaDatosFrameA {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		return insertaRegistro;
		
	} 
	
	
	
	
}
