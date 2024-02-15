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

import com.ikon.process.dao.IkTopicDao;
import com.ikon.process.dto.IkCatTopicDto;
import com.ikon.process.util.Constantes;

/**
 * Implementa la funcionalidad para el manejo de tarjetas
 * @author icb_ipsra
 * @since Octuber, 2021
 * @version 0.2.1
 *
 */
@Repository("okTopicDao")
public class IkTopicDaoImpl extends GenericDao implements IkTopicDao {
	
	private static final Logger LOGG = LoggerFactory.getLogger(IkTopicDaoImpl.class);

	@Value("${base.schema}")
	private String baseSchema;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.dao.IkCardDao@addUpdatecardTopic(com.ikon.process.json.request.IkRequestCard)
	 */
	public Integer insertTopic( String topicName ) throws Exception {
		Integer sequenceTopic = new Integer(-1);
		LOGG.info("[:::::. Inserting topic - schema {} .:::::]", baseSchema);
		
		try {
			
			SimpleJdbcCall simpleJdbcCall	=	new SimpleJdbcCall(getJdbcTemplate())
					.withCatalogName(baseSchema)
					.withProcedureName("sp_topic_insert");
				
			SqlParameterSource params = new MapSqlParameterSource()
												.addValue("in_topic_name", topicName );
			
			Map<String, Object> mapResults = simpleJdbcCall.execute(params);
			sequenceTopic = Integer.parseInt(mapResults.get("out_sequence").toString());
			
			if ( sequenceTopic < Constantes.VALOR_INT_UNO ) {
				LOGG.warn("[:::::. {}{}{} .:::::]",  mapResults.get("out_msg_error").toString()," WITH:> ", topicName);
			} else {
				LOGG.info("[:::::. {}{}{}{} .:::::]", "Topic name:> ",topicName, " cveTopic:> ", sequenceTopic);
			} 
			
		} catch (Exception e) {
			LOGG.error("[:::::::::::: insertTopic {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		return sequenceTopic;
		
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.dao.IkCardDao@addUpdatecardTopic( )
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<IkCatTopicDto> obtieneTopic() throws Exception {
		ArrayList<IkCatTopicDto> catTopic = new ArrayList<>();

		try {

			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(getJdbcTemplate()).withCatalogName(baseSchema)
					.withProcedureName("sp_obtieneTopic")
					.returningResultSet("catalogoTopic", new RowMapper<IkCatTopicDto>() {

						@Override
						public IkCatTopicDto mapRow(ResultSet rs, int rowNum) throws SQLException {
							return new IkCatTopicDto(rs.getInt("cveTopic"),rs.getString("topicName"));
						}

					});
			
			Map<String, Object> map = simpleJdbcCall.execute();
			catTopic = (ArrayList<IkCatTopicDto>) map.get("catalogoTopic");
			

		} catch (Exception e) {
			LOGG.error("[:::::::::::: obtieneTopic {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		return catTopic;
	}
	
	
	

}
