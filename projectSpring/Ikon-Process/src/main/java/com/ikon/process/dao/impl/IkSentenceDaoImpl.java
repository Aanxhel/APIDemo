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

import com.ikon.process.dao.IkSentenceDao;
import com.ikon.process.dto.IkCardsSentencesDto;
import com.ikon.process.dto.IkSentenceCardDto;
import com.ikon.process.util.Constantes;

/**
 * Implementa la funcionalidad para el manejo de tarjetas
 * @author icb_ipsra
 * @since Octuber, 2021
 * @version 0.2.1
 */
@Repository("IkSentenceDao")
public class IkSentenceDaoImpl extends GenericDao implements IkSentenceDao {
	
	private static final Logger LOGG = LoggerFactory.getLogger(IkSentenceDaoImpl.class);

	@Value("${base.schema}")
	private String baseSchema;
	
	/*
	 * (non-Javadoc)
	 * @see com.ikon.process.dao.IkSentenceDao@insertSentence(com.ikon.process.dto.IkSentenceCardDto)
	 */
	public Integer insertSentence (IkSentenceCardDto ikSentenceCardDto ) throws Exception {
		Integer sequenceSentence = 0;
		LOGG.info("[:::::. Inserting Sentence - schema {} .:::::]", baseSchema);
		
		try {
			
			SimpleJdbcCall simpleJdbcCall	=	new SimpleJdbcCall(getJdbcTemplate())
					.withCatalogName(baseSchema)
					.withProcedureName("sp_insert_sentence");
				
			SqlParameterSource params = new MapSqlParameterSource()
								.addValue("in_sentence_order", ikSentenceCardDto.getSentenceOrder())
								.addValue("in_sentenceName", ikSentenceCardDto.getSentenceName())
								.addValue("in_des_sound", ikSentenceCardDto.getPathSound())
								.addValue("in_des_video", ikSentenceCardDto.getPathVideo())
								.addValue("in_des_info", ikSentenceCardDto.getPathInfo());
			
			
			Map<String, Object> mapResults = simpleJdbcCall.execute(params);
			sequenceSentence = Integer.parseInt(mapResults.get("out_seq_sentence").toString());
			
			if ( sequenceSentence == Constantes.VALOR_INT_UNO ) {
				LOGG.warn("[:::::. {}{}{} .:::::]",  mapResults.get("out_msg_error").toString()," WITH:> ", ikSentenceCardDto.getSentenceName());
			} else {
				LOGG.info("[:::::. {}{}{}{} .:::::]", "INSERT insertSentence cveSentence:> ",sequenceSentence, " SentenceOrder:> ", ikSentenceCardDto.getSentenceOrder(), " SentenceName:> ", ikSentenceCardDto.getSentenceName());
			} 
			
		} catch (Exception e) {
			LOGG.error("[:::::::::::: insertSentence {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		return sequenceSentence;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.ikon.process.dao.IkSentenceDao@insertSentenceCard(java.lang.Integer,java.lang.Integer)
	 */
	public int insertSentenceCard (Integer sentenceId, Integer cardId ) throws Exception{
		int statusInsert = -1;
		LOGG.info("[:::::. Inserting insertSentenceCard - schema {} .:::::]", baseSchema);
		

		try {
			
			SimpleJdbcCall simpleJdbcCall	=	new SimpleJdbcCall(getJdbcTemplate())
					.withCatalogName(baseSchema)
					.withProcedureName("sp_i_sentece_card");
				
			SqlParameterSource params = new MapSqlParameterSource()
								.addValue("in_sentence_id", sentenceId )
								.addValue("in_card_id", cardId);
			
			
			Map<String, Object> mapResults = simpleJdbcCall.execute(params);
			statusInsert = Integer.parseInt(mapResults.get("out_respuesta").toString());
			
			if ( statusInsert == Constantes.VALOR_INT_UNO ) {
				LOGG.warn("[:::::. {}{}{} .:::::]",  mapResults.get("out_msg_error").toString()," WITH:> ", sentenceId );
			} else {
				LOGG.info("[:::::. {}{}{}{} .:::::]", "INSERT  insertSentenceCard sentenceId:> ",sentenceId, " cardId:> ", cardId);
			} 
			
		} catch (Exception e) {
			LOGG.error("[:::::::::::: insertSentenceCard {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		return statusInsert;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.ikon.process.dao.IkSentenceDao@updateSentence(com.ikon.process.dto.IkSentenceCardDto)
	 */
	public int updateSentence (IkSentenceCardDto ikSentenceCardDto ) throws Exception {
		int statusUpdate = 0;
		LOGG.info("[:::::. updateSentence  - schema {} .:::::]", baseSchema);
		
		try {
			
			SimpleJdbcCall simpleJdbcCall	=	new SimpleJdbcCall(getJdbcTemplate())
					.withCatalogName(baseSchema)
					.withProcedureName("sp_update_sentence");
				
			SqlParameterSource params = new MapSqlParameterSource()
								.addValue("in_cveSentence",ikSentenceCardDto.getCveSentence())
								.addValue("in_sentence_order", ikSentenceCardDto.getSentenceOrder())
								.addValue("in_sentenceName", ikSentenceCardDto.getSentenceName())
								.addValue("in_des_sound", ikSentenceCardDto.getPathSound())
								.addValue("in_des_video", ikSentenceCardDto.getPathVideo())
								.addValue("in_des_info", ikSentenceCardDto.getPathInfo());
			
			
			Map<String, Object> mapResults = simpleJdbcCall.execute(params);
			statusUpdate = Integer.parseInt(mapResults.get("out_respuesta").toString());
			
			if ( statusUpdate == Constantes.VALOR_INT_UNO ) {
				LOGG.warn("[:::::. {}{}{} .:::::]",  mapResults.get("out_msg_error").toString()," WITH:> ", ikSentenceCardDto.getSentenceName());
			} else {
				LOGG.info("[:::::. {}{}{}{} .:::::]", "UPDATE updateSentence cveSentence:> ",ikSentenceCardDto.getCveSentence(), " SentenceOrder:> ", ikSentenceCardDto.getSentenceOrder());
			} 
			
		} catch (Exception e) {
			LOGG.error("[:::::::::::: updateSentence {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		return statusUpdate;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.ikon.process.dao.IkSentenceDao@updateSentence( )
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<IkCardsSentencesDto> getAllSentencesCards () throws Exception {
		ArrayList<IkCardsSentencesDto> catAllSentences = new ArrayList<>();
		
		try {

			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(getJdbcTemplate()).withCatalogName(baseSchema)
					.withProcedureName("sp_cards_sentence")
					.returningResultSet("catalogoAllSentences", new RowMapper<IkCardsSentencesDto>() {

						@Override
						public IkCardsSentencesDto mapRow(ResultSet rs, int rowNum) throws SQLException {
							return new IkCardsSentencesDto(
									Integer.valueOf(rs.getInt("cveSentence")),
									rs.getString("sentenceName"),
									Integer.valueOf(rs.getInt("cveCard")), 
									rs.getString("cardTex"),
									rs.getString("desSound"),
									rs.getString("desVideo"),
									rs.getString("desInfor"));
						}

					});
			
			Map<String, Object> map = simpleJdbcCall.execute();
			catAllSentences = (ArrayList<IkCardsSentencesDto>) map.get("catalogoAllSentences");
			

		} catch (Exception e) {
			LOGG.error("[:::::::::::: obtieneTopic {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		
		
		return catAllSentences;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.ikon.process.dao.IkSentenceDao@eliminaSentence(java.lang.Integer)
	 */
	public int eliminaSentence (Integer cveSentence )  throws Exception {
		int respuestaDelete = -1;
		
		try {
			
			SimpleJdbcCall simpleJdbcCall	=	new SimpleJdbcCall(getJdbcTemplate())
					.withCatalogName(baseSchema)
					.withProcedureName("sp_delete_sentence");
				
			SqlParameterSource params = new MapSqlParameterSource()
								.addValue("in_cveSentence", cveSentence);
								
			Map<String, Object> mapResults = simpleJdbcCall.execute(params);
			respuestaDelete = Integer.parseInt(mapResults.get("out_respuesta").toString());
			
			LOGG.info("[:::::. {}{} .:::::]", "Stats DELETE Sentence number:> ", respuestaDelete , " with the cveSentence " +  cveSentence );
			
			if ( respuestaDelete != Constantes.VALOR_INT_CERO ) {
				LOGG.warn("[:::::. {}{}{} .:::::]",  mapResults.get("out_msg_error").toString()," WITH:> ", cveSentence);
			} else {
				respuestaDelete = 0;
				LOGG.info("[:::::. {}{} .:::::]", "DELETE Sentence number:> ",cveSentence );
			} 
			
		} catch (Exception e) {
			LOGG.error("[:::::::::::: eliminando Sentencia {}  :::::::::]", e.getMessage());
			throw new Exception();
		}

		return respuestaDelete;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.ikon.process.dao.IkSentenceDao@updateSentence( )
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<IkCardsSentencesDto> getSentencesCardsByID (Integer cveSentence) throws Exception {
		ArrayList<IkCardsSentencesDto> catAllSentences = new ArrayList<>();
		
		try {

			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(getJdbcTemplate()).withCatalogName(baseSchema)
					.withProcedureName("sp_sentence_card_byId")
					.returningResultSet("getSentencesCardByID", new RowMapper<IkCardsSentencesDto>() {

						@Override
						public IkCardsSentencesDto mapRow(ResultSet rs, int rowNum) throws SQLException {
							return new IkCardsSentencesDto(
									Integer.valueOf(rs.getInt("cveSentence")),
									rs.getString("sentenceName"),
									Integer.valueOf(rs.getInt("cveCard")), 
									rs.getString("cardTex"),
									rs.getString("topicName"),
									rs.getString("desSound"),
									rs.getString("desVideo"),
									rs.getString("desInfor"));
						}

					});
			SqlParameterSource params = new MapSqlParameterSource()
                    								.addValue("in_sentence_id", cveSentence);
			
			Map<String, Object> map = simpleJdbcCall.execute(params);
			catAllSentences = (ArrayList<IkCardsSentencesDto>) map.get("getSentencesCardByID");
			

		} catch (Exception e) {
			LOGG.error("[:::::::::::: obtieneTopic {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		
		
		return catAllSentences;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.ikon.process.dao.IkSentenceDao@updateSentence( )
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<IkCardsSentencesDto> notassociatedSession () throws Exception {
		ArrayList<IkCardsSentencesDto> catAllSentences = new ArrayList<>();
		
		try {

			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(getJdbcTemplate()).withCatalogName(baseSchema)
					.withProcedureName("sp_sent_noassociate_ses")
					.returningResultSet("catalogoAllSentences", new RowMapper<IkCardsSentencesDto>() {

						@Override
						public IkCardsSentencesDto mapRow(ResultSet rs, int rowNum) throws SQLException {
							return new IkCardsSentencesDto(
									Integer.valueOf(rs.getInt("cveSentence")),
									rs.getString("sentenceName"),
									Integer.valueOf(rs.getInt("cveCard")), 
									rs.getString("cardTex"),
									rs.getString("desSound"),
									rs.getString("desVideo"),
									rs.getString("desInfor"));
						}

					});
			
			Map<String, Object> map = simpleJdbcCall.execute();
			catAllSentences = (ArrayList<IkCardsSentencesDto>) map.get("catalogoAllSentences");
			

		} catch (Exception e) {
			LOGG.error("[:::::::::::: obtieneTopic {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		return catAllSentences;
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.dao.IkModuleDao@existModuleSession()
	 */
	public int existeSentence ( Integer cveSentence ) throws Exception {
		int existeSession = 0;
		LOGG.info("[:::::.  existeSentence - schema {} .:::::]", baseSchema);
		
		try {
			
			SimpleJdbcCall simpleJdbcCall	=	new SimpleJdbcCall(getJdbcTemplate())
					.withCatalogName(baseSchema)
					.withProcedureName("sp_existSentence");
				
			SqlParameterSource params = new MapSqlParameterSource()
								.addValue("in_cveSentence", cveSentence );
			
			Map<String, Object> mapResults = simpleJdbcCall.execute(params);
			existeSession = Integer.parseInt(mapResults.get("out_respuesta").toString());
			
			if (existeSession != Constantes.VALOR_INT_CERO ) {
				LOGG.warn("[:::::. {}{}{} .:::::]",  mapResults.get("out_msg_error").toString()," WITH in_cveSentence :> ", cveSentence );
			}
			
		} catch (Exception e) {
			LOGG.error("[:::::::::::: existeSession {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		return existeSession;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.ikon.process.dao.IkSentenceDao@eliminaSentence(java.lang.Integer)
	 */
	public int eliminaCardSentence (Integer cveSentence )  throws Exception {
		int respuestaDelete = -1;
		
		try {
			
			SimpleJdbcCall simpleJdbcCall	=	new SimpleJdbcCall(getJdbcTemplate())
					.withCatalogName(baseSchema)
					.withProcedureName("sp_d_cardSentence");
				
			SqlParameterSource params = new MapSqlParameterSource()
								.addValue("in_sentence_id", cveSentence);
								
			Map<String, Object> mapResults = simpleJdbcCall.execute(params);
			respuestaDelete = Integer.parseInt(mapResults.get("out_respuesta").toString());
			
			LOGG.info("[:::::. {}{} .:::::]", "Stats DELETE Card Sentence number:> ", respuestaDelete , " with the in_sentence_id " +  cveSentence );
			
		} catch (Exception e) {
			LOGG.error("[:::::::::::: eliminando Sentencia {}  :::::::::]", e.getMessage());
			throw new Exception();
		}

		return respuestaDelete;
	}
	
	
}
