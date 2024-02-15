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

import com.ikon.process.dao.IkActivityLogDao;
import com.ikon.process.dto.IkActivityDto;
import com.ikon.process.util.Constantes;

/**
 * @author icb_ipsra
 * @since Octuber, 2021
 *
 */
@Repository("IkActivityLogDao")
public class IkActivityLogDaoImpl extends GenericDao implements IkActivityLogDao {

	private static final Logger LOGG = LoggerFactory.getLogger(IkActivityLogDaoImpl.class);

	@Value("${base.schema}")
	private String baseSchema;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.dao.IkActivityLogDao@insertActivitiLog(IkActivityDto)
	 */
	public Integer insertActivitiLog (IkActivityDto activityDto) throws Exception {
		int  insertResponse  =  -1;
		LOGG.info("[:::::. insertActivitiLog topic - schema {} .:::::]", baseSchema);
		
		try {
			
			SimpleJdbcCall simpleJdbcCall	=	new SimpleJdbcCall(getJdbcTemplate())
					.withCatalogName(baseSchema)
					.withProcedureName("sp_activity_log");
				
			SqlParameterSource params = new MapSqlParameterSource()
												.addValue("in_nom_username", activityDto.getNomUsername() )
												.addValue("in_tim_start", activityDto.getTimStart())
												.addValue("in_tim_end", activityDto.getTimEnd())
												.addValue("in_des_module", activityDto.getDesModule())
												.addValue("in_des_session", activityDto.getDesSession())
												.addValue("in_num_sentences", activityDto.getNumSentences());
			
			Map<String, Object> mapResults = simpleJdbcCall.execute(params);
			insertResponse = Integer.parseInt(mapResults.get("out_respuesta").toString());
			
			if ( insertResponse != Constantes.VALOR_INT_CERO ) {
				LOGG.warn("[:::::. {}{}{} .:::::]",  mapResults.get("out_msg_error").toString()," WITH:> ", activityDto.getNomUsername());
			} else {
				LOGG.info("[:::::. {}{} .:::::]", "insertActivitiLog:> ",activityDto.toString());
			}
			
		} catch (Exception e) {
			LOGG.error("[:::::::::::: insertActivitiLog {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		return insertResponse;
	}
	

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.dao.IkActivityLogDao@getAllSessionesByModule()
	 */
	@SuppressWarnings({ "unchecked" })
	public ArrayList<IkActivityDto> getAllActivityLog (int totalRegistros )  throws Exception  { 
		ArrayList<IkActivityDto> arraySessiones = new ArrayList<>();
		
		try {

			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(getJdbcTemplate()).withCatalogName(baseSchema)
					.withProcedureName("sp_get_activity_log")
					.returningResultSet("respActivityLog", new RowMapper<IkActivityDto>() {

						@Override
						public IkActivityDto mapRow(ResultSet rs, int rowNum) throws SQLException {
							return new IkActivityDto(rs.getInt("id"),
													 rs.getString("nom_username"),
													 rs.getString("fec_activity"),
													 rs.getString("tim_start"),
													 rs.getString("tim_end"),
													 rs.getString("des_module"),
													 rs.getString("des_session"),
													 rs.getString("num_sentences"));
						}
					});

			SqlParameterSource params = new MapSqlParameterSource()
											.addValue("in_records", totalRegistros);
			
			Map<String, Object> map = simpleJdbcCall.execute(params);
			arraySessiones = (ArrayList<IkActivityDto>) map.get("respActivityLog");

		} catch (Exception e) {
			LOGG.error("[:::::::::::: getAllActivityLog {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		return arraySessiones;
	}
	

	
	
}
