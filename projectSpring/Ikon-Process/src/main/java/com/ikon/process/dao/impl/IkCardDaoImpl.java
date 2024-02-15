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

import com.ikon.process.dao.IkCardDao;
import com.ikon.process.dto.IkCardByTopicDto;
import com.ikon.process.dto.IkGetCard;
import com.ikon.process.json.request.IkRequestCard;
import com.ikon.process.util.Constantes;

/**
 * Implementa la funcionalidad para el manejo de tarjetas
 * @author icb_ipsra
 * @since Octuber, 2021
 * @version 0.2.1
 *
 */
@Repository("ikCardDao")
public class IkCardDaoImpl extends GenericDao implements IkCardDao {
	
	private static final Logger LOGG = LoggerFactory.getLogger(IkCardDaoImpl.class);

	@Value("${base.schema}")
	private String baseSchema;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.dao.IkCardDao@addUpdatecardTopic(com.ikon.process.json.request.IkRequestCard)
	 */
	public int insertCard(IkRequestCard request ) throws Exception {
		int sequenceCard = 0;
		LOGG.info("[:::::. Inserting Card - schema {} .:::::]", baseSchema);
		
		try {
			
			SimpleJdbcCall simpleJdbcCall	=	new SimpleJdbcCall(getJdbcTemplate())
					.withCatalogName(baseSchema)
					.withProcedureName("sp_card_insert");
				
			SqlParameterSource params = new MapSqlParameterSource()
								.addValue("in_des_text", request.getCardText())
								.addValue("in_des_imagen", request.getPathImage())
								.addValue("in_des_sonido", request.getPathSound())
								.addValue("in_des_video", request.getPathVideo())
								.addValue("in_desc_meaning", request.getPathMeaning())
								.addValue("in_topic_id",request.getCveTopic());
			
			
			Map<String, Object> mapResults = simpleJdbcCall.execute(params);
			sequenceCard = Integer.parseInt(mapResults.get("out_respuesta").toString());
			
			if ( sequenceCard < Constantes.VALOR_INT_UNO ) {
				LOGG.warn("[:::::. {}{}{} .:::::]",  mapResults.get("out_msg_error").toString()," WITH:> ", request.getCardText());
			} else {
				LOGG.info("[:::::. {}{}{}{} .:::::]", "INSERT Card name:> ",request.getCardText(), " cveCard:> ", sequenceCard, " cveTopic:> ", request.getCveTopic());
			} 
			
		} catch (Exception e) {
			LOGG.error("[:::::::::::: insertCard {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		return sequenceCard;
		
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.dao.IkCardDao@updateCardTopic(com.ikon.process.json.request.IkRequestCard)
	 */
	public int updateCardTopic( IkRequestCard request ) throws Exception {
		int actualizaRegistro = 0;
		LOGG.info("[:::::. updateCardTopic Card - schema {} .:::::]", baseSchema);
		
		try {
			
			SimpleJdbcCall simpleJdbcCall	=	new SimpleJdbcCall(getJdbcTemplate())
					.withCatalogName(baseSchema)
					.withProcedureName("sp_card_update");
				
			SqlParameterSource params = new MapSqlParameterSource()
								.addValue("in_id_card", request.getCveCard())
								.addValue("in_des_text", request.getCardText())
								.addValue("in_des_imagen", request.getPathImage())
								.addValue("in_des_sonido", request.getPathSound())
								.addValue("in_des_video", request.getPathVideo())
								.addValue("in_desc_meaning", request.getPathMeaning())
								.addValue("in_topic_id",request.getCveTopic())
								.addValue("in_topic_name",request.getTopicName());
			
			
			Map<String, Object> mapResults = simpleJdbcCall.execute(params);
			actualizaRegistro = Integer.parseInt(mapResults.get("out_respuesta").toString());
			
			if ( actualizaRegistro < Constantes.VALOR_INT_UNO ) {
				LOGG.warn("[:::::. {}{}{} .:::::]",  mapResults.get("out_msg_error").toString()," WITH:> ", request.getCardText());
			} else {
				LOGG.info("[:::::. {}{}{}{} .:::::]", "UPDATE Card name:> ",request.getCardText(), " cveCard:> ", request.getCveCard(), " cveTopic:> ", request.getCveTopic());
			} 
			
		} catch (Exception e) {
			LOGG.error("[:::::::::::: updateCardTopic {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		return actualizaRegistro;
		
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.dao.IkCardDao@updateCardTopic(com.ikon.process.json.request.IkRequestCard)
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<IkCardByTopicDto> obtineCardbyTopic( Integer cveTopic) throws Exception {
		ArrayList<IkCardByTopicDto> cboCardsTopic = new ArrayList<>();
		
		try {

			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(getJdbcTemplate())
					.withCatalogName(baseSchema)
					.withProcedureName("sp_card_topic")
					.returningResultSet("cboCardByTopic", new RowMapper<IkCardByTopicDto>() {

						@Override
						public IkCardByTopicDto mapRow(ResultSet rs, int rowNum) throws SQLException {
							return new IkCardByTopicDto(
											rs.getInt("cveCard"), 
											rs.getString("cardText"), 
											rs.getString("pathImage"),
											rs.getString("pathSound"), 
											rs.getString("pathVideo"), 
											rs.getString("pathMeaning"));
						}

					});
					
			
			SqlParameterSource params = new MapSqlParameterSource()
					                    .addValue("in_topic_id", cveTopic);
					
			Map<String, Object> map = simpleJdbcCall.execute(params);
			cboCardsTopic = (ArrayList<IkCardByTopicDto>) map.get("cboCardByTopic");
			
		} catch (Exception e) {
			 e.printStackTrace() ;
			LOGG.error("[:::::::::::: obtineCardbyTopic {}  :::::::::]" );
			throw new Exception();
		}
		
		return cboCardsTopic;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ikon.process.dao.IkCardDao@eliminaCard(java.lang.Integer)
	 */
	public int eliminaCard (Integer cveCard )  throws Exception {
		int respuestaDelete = -1;
		
		try {
			
			SimpleJdbcCall simpleJdbcCall	=	new SimpleJdbcCall(getJdbcTemplate())
					.withCatalogName(baseSchema)
					.withProcedureName("sp_delete_card");
				
			SqlParameterSource params = new MapSqlParameterSource()
								.addValue("in_cveCard", cveCard);
								
			Map<String, Object> mapResults = simpleJdbcCall.execute(params);
			respuestaDelete = Integer.parseInt(mapResults.get("out_respuesta").toString());
			
			LOGG.info("[:::::. {}{} .:::::]", "Stats DELETE Card number:> ", respuestaDelete , " with de cardNumber " +  cveCard );
			
			if ( respuestaDelete != Constantes.VALOR_INT_CERO ) {
				LOGG.warn("[:::::. {}{}{} .:::::]",  mapResults.get("out_msg_error").toString()," WITH:> ", cveCard);
			} else {
				respuestaDelete = 0;
				LOGG.info("[:::::. {}{} .:::::]", "DELETE Card number:> ",cveCard );
			} 
			
		} catch (Exception e) {
			LOGG.error("[:::::::::::: eliminando Card {}  :::::::::]", e.getMessage());
			throw new Exception();
		}

		return respuestaDelete;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.dao.IkCardDao@obtineCardbyID(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<IkGetCard> obtineCardbyID( Integer cveCard) throws Exception {
		ArrayList<IkGetCard> cboCardsTopic = new ArrayList<>();
		
		try {

			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(getJdbcTemplate())
					.withCatalogName(baseSchema)
					.withProcedureName("sp_getCarbyId")
					.returningResultSet("cardByID", new RowMapper<IkGetCard>() {

						@Override
						public IkGetCard mapRow(ResultSet rs, int rowNum) throws SQLException {
							return new IkGetCard(rs.getInt("cveTopic"), 
												 rs.getString("topicName"), 
												 rs.getInt("cveCard"),
												 rs.getString("cardText"), 
												 rs.getString("pathImage"), 
												 rs.getString("pathSound"),
												 rs.getString("pathVideo"), 
												 rs.getString("pathMeaning"));
						}
					});
					
			
			SqlParameterSource params = new MapSqlParameterSource()
					                    .addValue("in_cveCard", cveCard);
					
			Map<String, Object> map = simpleJdbcCall.execute(params);
			cboCardsTopic = (ArrayList<IkGetCard>) map.get("cardByID");
			
		} catch (Exception e) {
			 e.printStackTrace() ;
			LOGG.error("[:::::::::::: obtineCardbyTopic {}  :::::::::]" );
			throw new Exception();
		}
		
		return cboCardsTopic;
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.dao.IkCardDao@obtineCardbyID(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<IkGetCard> obtineAllCards( ) throws Exception {
		ArrayList<IkGetCard> cboCardsTopic = new ArrayList<>();
		
		try {

			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(getJdbcTemplate())
					.withCatalogName(baseSchema)
					.withProcedureName("sp_getAllCard")
					.returningResultSet("getAllCards", new RowMapper<IkGetCard>() {

						@Override
						public IkGetCard mapRow(ResultSet rs, int rowNum) throws SQLException {
							return new IkGetCard(rs.getInt("cveTopic"), 
												 rs.getString("topicName"), 
												 rs.getInt("cveCard"),
												 rs.getString("cardText"), 
												 rs.getString("pathImage"), 
												 rs.getString("pathSound"),
												 rs.getString("pathVideo"), 
												 rs.getString("pathMeaning"));
						}
					});
					
					
			Map<String, Object> map = simpleJdbcCall.execute();
			cboCardsTopic = (ArrayList<IkGetCard>) map.get("getAllCards");
			
		} catch (Exception e) {
			 e.printStackTrace() ;
			LOGG.error("[:::::::::::: obtineAllCards {}  :::::::::]" );
			throw new Exception();
		}
		
		return cboCardsTopic;
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.dao.IkModuleDao@existModuleSession()
	 */
	public int existeCards ( Integer cveCard ) throws Exception {
		int existeSession = 0;
		LOGG.info("[:::::.  existeCards - schema {} .:::::]", baseSchema);
		
		try {
			
			SimpleJdbcCall simpleJdbcCall	=	new SimpleJdbcCall(getJdbcTemplate())
					.withCatalogName(baseSchema)
					.withProcedureName("sp_existCard");
				
			SqlParameterSource params = new MapSqlParameterSource()
								.addValue("in_cveCard", cveCard );
			
			Map<String, Object> mapResults = simpleJdbcCall.execute(params);
			existeSession = Integer.parseInt(mapResults.get("out_respuesta").toString());
			
			if (existeSession != Constantes.VALOR_INT_CERO ) {
				LOGG.warn("[:::::. {}{}{} .:::::]",  mapResults.get("out_msg_error").toString()," WITH in_cveCard :> ", cveCard );
			}
			
		} catch (Exception e) {
			LOGG.error("[:::::::::::: existeCards {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		return existeSession;
	}
	
	
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.dao.IkCardDao@getCardByTopicName(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<IkCardByTopicDto> getCardByTopicName(  String topicName ) throws Exception {
		ArrayList<IkCardByTopicDto> cboCardsTopic = new ArrayList<>();
		
		try {

			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(getJdbcTemplate())
					.withCatalogName(baseSchema)
					.withProcedureName("sp_card_topic_name")
					.returningResultSet("getAllCardsByTopicName", new RowMapper<IkCardByTopicDto>() {

						@Override
						public IkCardByTopicDto mapRow(ResultSet rs, int rowNum) throws SQLException {
							return new IkCardByTopicDto(
											rs.getInt("cveCard"), 
											rs.getString("cardTex"), 
											rs.getString("pathImage"),
											rs.getString("pathSound"), 
											rs.getString("pathVideo"), 
											rs.getString("pathMeaning"));
						}

					});
					
			
			SqlParameterSource params = new MapSqlParameterSource()
					                    .addValue("in_topic_name", topicName);
					
			Map<String, Object> map = simpleJdbcCall.execute(params);
			cboCardsTopic = (ArrayList<IkCardByTopicDto>) map.get("getAllCardsByTopicName");
			
		} catch (Exception e) {
			 e.printStackTrace() ;
			LOGG.error("[:::::::::::: obtineCardbyTopic {}  :::::::::]" );
			throw new Exception();
		}
		
		return cboCardsTopic;
	}
	

}
