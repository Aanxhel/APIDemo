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

import com.ikon.process.dao.IkSessionDao;
import com.ikon.process.dto.IkCatSessionDto;
import com.ikon.process.util.Constantes;

/**
 * @author icb_ipsra
 *
 */
@Repository("ikSessionDao")
public class IkSessionDaoImpl  extends GenericDao implements IkSessionDao{

	private static final Logger LOGG = LoggerFactory.getLogger(IkSessionDaoImpl.class);

	@Value("${base.schema}")
	private String baseSchema;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.dao.IkSessionDao@obtieneSessiones( )
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<IkCatSessionDto> obtieneSessiones() throws Exception {
		ArrayList<IkCatSessionDto> catSession = new ArrayList<>();
		LOGG.info("[:::::. Get all session - schema {} .:::::]", baseSchema);
		
		try {

			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(getJdbcTemplate()).withCatalogName(baseSchema)
					.withProcedureName("sp_getallSession")
					.returningResultSet("catalogoSession", new RowMapper<IkCatSessionDto>() {

						@Override
						public IkCatSessionDto mapRow(ResultSet rs, int rowNum) throws SQLException {
							return new IkCatSessionDto(
											rs.getInt("totalSentences"),
											rs.getInt("cveSession"),
											rs.getString("sessionName"),
											rs.getString("sesionDesc"),
											rs.getInt("cveSentence"),
											rs.getString("sentenceName"));
						}

					});
			
			Map<String, Object> map = simpleJdbcCall.execute();
			catSession = (ArrayList<IkCatSessionDto>) map.get("catalogoSession");
			

		} catch (Exception e) {
			LOGG.error("[:::::::::::: obtieneSessiones {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		return catSession;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.dao.IkSessionDao@insertSession( java.lang.String, java.lang.String)
	 */
	public Integer insertSession (String sessionName, String sesionDesc ) throws Exception {
		Integer sequenceSession = new Integer(0);
		LOGG.info("[:::::. insertSession topic - schema {} .:::::]", baseSchema);
		
		try {
			
			SimpleJdbcCall simpleJdbcCall	=	new SimpleJdbcCall(getJdbcTemplate())
					.withCatalogName(baseSchema)
					.withProcedureName("sp_insert_sesion");
				
			SqlParameterSource params = new MapSqlParameterSource()
												.addValue("in_session_name", sessionName )
												.addValue("in_sesionDesc", sesionDesc);
			
			Map<String, Object> mapResults = simpleJdbcCall.execute(params);
			sequenceSession = Integer.parseInt(mapResults.get("out_sequence").toString());
			
			if ( sequenceSession < Constantes.VALOR_INT_UNO ) {
				LOGG.warn("[:::::. {}{}{} .:::::]",  mapResults.get("out_msg_error").toString()," WITH:> ", sessionName);
			} else {
				LOGG.info("[:::::. {}{}{}{} .:::::]", "Session name:> ",sessionName, " cveSession:> ", sequenceSession);
			}
			
		} catch (Exception e) {
			LOGG.error("[:::::::::::: insertTopic {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		return sequenceSession;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ikon.process.dao.IkSessionDao@insertSessionSentence(java.lang.Integer,java.lang.Integer)
	 */
	public int insertSessionSentence (Integer sessionId, Integer sentenceId ) throws Exception {
		int statusInsert = 0;
		
		LOGG.info("[:::::. Inserting insertSessionSentence - schema {} .:::::]", baseSchema);
		try {
			
			SimpleJdbcCall simpleJdbcCall	=	new SimpleJdbcCall(getJdbcTemplate())
					.withCatalogName(baseSchema)
					.withProcedureName("sp_i_sesion_sentence");
				
			SqlParameterSource params = new MapSqlParameterSource()
								.addValue("in_session_id", sessionId )
								.addValue("in_sentence_id", sentenceId);
			
			
			Map<String, Object> mapResults = simpleJdbcCall.execute(params);
			statusInsert = Integer.parseInt(mapResults.get("out_respuesta").toString());
			
			if ( statusInsert == Constantes.VALOR_INT_UNO ) {
				LOGG.warn("[:::::. {}{}{} .:::::]",  mapResults.get("out_msg_error").toString()," WITH:> ", sentenceId );
			} else {
				LOGG.info("[:::::. {}{}{}{} .:::::]", "INSERT  insertSessionSentence sessionId:> ",sessionId, " sentenceId:> ", sentenceId);
			} 
			
		} catch (Exception e) {
			LOGG.error("[:::::::::::: insertSentenceCard {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		return statusInsert;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.ikon.process.dao.IkSessionDao@existSessionSentences(java.lang.Integer, int )
	 */
	public int existSessionSentences ( Integer cveSession,  String sessionName ) throws Exception {
		int recordFine = 0;
		LOGG.info("[:::::.  existSessionCards - schema {} .:::::]", baseSchema);
		
		
		try {
			
			SimpleJdbcCall simpleJdbcCall	=	new SimpleJdbcCall(getJdbcTemplate())
					.withCatalogName(baseSchema)
					.withProcedureName("sp_existSessionSentence");
				
			SqlParameterSource params = new MapSqlParameterSource()
								.addValue("in_cveSession", cveSession )
								.addValue("in_sentenceName", sessionName );
			
			
			Map<String, Object> mapResults = simpleJdbcCall.execute(params);
			recordFine = Integer.parseInt(mapResults.get("out_respuesta").toString());
			
			LOGG.warn("[:::::. {}{}{} .:::::]",  mapResults.get("out_msg_error").toString()," WITH cveSession :> ", cveSession );
			
		} catch (Exception e) {
			LOGG.error("[:::::::::::: existSessionCards {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		return recordFine;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.ikon.process.dao.IkSessionDao@eliminaSessionSentences( java.lang.Integer )
	 */
	public int eliminaSessionSentences  ( Integer cveSession ) throws Exception {
		int recordEliminated = -1;
		
		LOGG.info("[:::::. Inserting eliminaSessionSentences - schema {} .:::::]", baseSchema);
		try {
			
			SimpleJdbcCall simpleJdbcCall	=	new SimpleJdbcCall(getJdbcTemplate())
					.withCatalogName(baseSchema)
					.withProcedureName("sp_deleteSessionSent");
				
			SqlParameterSource params = new MapSqlParameterSource()
								.addValue("in_cveSession", cveSession );
			
			
			Map<String, Object> mapResults = simpleJdbcCall.execute(params);
			recordEliminated = Integer.parseInt(mapResults.get("out_respuesta").toString());
			
			LOGG.info("[:::::. {}{} .:::::]", "DELETE  eliminaSessionSentences cveSession:> ",cveSession );
			
		} catch (Exception e) {
			LOGG.error("[:::::::::::: eliminaSessionSentences {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		return recordEliminated;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.ikon.process.dao.IkSessionDao@updateSession( java.lang.Integer,java.lang.Strin, java.lang.String )
	 */
	public int updateSession (Integer cveSession, String sessionName, String sessionDesc ) throws Exception  {
		int recordUpdated = -1;
		
		LOGG.info("[:::::. updateSession - schema {} .:::::]", baseSchema);
		try {
			
			SimpleJdbcCall simpleJdbcCall	=	new SimpleJdbcCall(getJdbcTemplate())
					.withCatalogName(baseSchema)
					.withProcedureName("sp_update_session");
				
			SqlParameterSource params = new MapSqlParameterSource()
								.addValue("in_cveSession", cveSession )
								.addValue("in_session_name", sessionName )
								.addValue("in_sesionDesc", sessionDesc );
			
			
			Map<String, Object> mapResults = simpleJdbcCall.execute(params);
			recordUpdated = Integer.parseInt(mapResults.get("out_sequence").toString());
			
			LOGG.info("[:::::. {}{}{}{} .:::::]", "UPDATE  updateSession cveSession:> ",cveSession );
			
		} catch (Exception e) {
			e.printStackTrace();
			LOGG.error("[:::::::::::: updateSession {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		return recordUpdated;
	} 
	
	/*
	 * (non-Javadoc)
	 * @see com.ikon.process.dao.IkSessionDao@eliminaSession(java.lang.Integer)
	 */
	public int eliminaSession (Integer cveSession )  throws Exception {
		int respuestaDelete = -1;
		
		try {
			
			SimpleJdbcCall simpleJdbcCall	=	new SimpleJdbcCall(getJdbcTemplate())
					.withCatalogName(baseSchema)
					.withProcedureName("sp_delete_session");
				
			SqlParameterSource params = new MapSqlParameterSource()
								.addValue("in_cveSession", cveSession);
								
			Map<String, Object> mapResults = simpleJdbcCall.execute(params);
			respuestaDelete = Integer.parseInt(mapResults.get("out_respuesta").toString());
			
			LOGG.info("[:::::. {}{} .:::::]", "Stats DELETE Session number:> ", respuestaDelete , " with de sessionNumber " +  cveSession );
			
			if ( respuestaDelete != Constantes.VALOR_INT_CERO ) {
				LOGG.warn("[:::::. {}{}{} .:::::]",  mapResults.get("out_msg_error").toString()," WITH:> ", cveSession);
			} else {
				respuestaDelete = 0;
				LOGG.info("[:::::. {}{} .:::::]", "DELETE Session number:> ",cveSession );
			} 
			
		} catch (Exception e) {
			LOGG.error("[:::::::::::: eliminando Session {}  :::::::::]", e.getMessage());
			throw new Exception();
		}

		return respuestaDelete;
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.dao.IkSessionDao@obtieneSessionesById(java.lang.Intenger )
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<IkCatSessionDto> obtieneSessionesById(Integer cveSession ) throws Exception {
		ArrayList<IkCatSessionDto> catSession = new ArrayList<>();
		LOGG.info("[:::::. Get by Id session - schema {} .:::::]", baseSchema);
		
		try {

			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(getJdbcTemplate()).withCatalogName(baseSchema)
					.withProcedureName("sp_getSessionbyId")
					.returningResultSet("catalogoSession", new RowMapper<IkCatSessionDto>() {

						@Override
						public IkCatSessionDto mapRow(ResultSet rs, int rowNum) throws SQLException {
							return new IkCatSessionDto(
											rs.getInt("totalSentences"),
											rs.getInt("cveSession"),
											rs.getString("sessionName"),
											rs.getString("sesionDesc"),
											rs.getInt("cveSentence"),
											rs.getString("sentenceName"));
						}

					});
			
			SqlParameterSource params = new MapSqlParameterSource()
					                          .addValue("in_session_id", cveSession);
			
			Map<String, Object> map = simpleJdbcCall.execute(params);
			catSession = (ArrayList<IkCatSessionDto>) map.get("catalogoSession");
			

		} catch (Exception e) {
			LOGG.error("[:::::::::::: obtieneSessiones {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		return catSession;
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.dao.IkSessionDao@obtieneSessiones( )
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<IkCatSessionDto> obtieneSessionesNotAssociated() throws Exception {
		ArrayList<IkCatSessionDto> catSession = new ArrayList<>();
		LOGG.info("[:::::. Get all session sp_getallSessionNotAssociated  - schema {} .:::::]", baseSchema);
		
		try {

			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(getJdbcTemplate()).withCatalogName(baseSchema)
					.withProcedureName("sp_getallSessionNotAssociated")
					.returningResultSet("catalogoSession", new RowMapper<IkCatSessionDto>() {

						@Override
						public IkCatSessionDto mapRow(ResultSet rs, int rowNum) throws SQLException {
							return new IkCatSessionDto(
											rs.getInt("totalSentences"),
											rs.getInt("cveSession"),
											rs.getString("sessionName"),
											rs.getString("sesionDesc"),
											rs.getInt("cveSentence"),
											rs.getString("sentenceName"));
						}

					});
			
			Map<String, Object> map = simpleJdbcCall.execute();
			catSession = (ArrayList<IkCatSessionDto>) map.get("catalogoSession");
			

		} catch (Exception e) {
			LOGG.error("[:::::::::::: obtieneSessiones {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		return catSession;
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.dao.IkModuleDao@existModuleSession()
	 */
	public int existeSession ( Integer cveSession ) throws Exception {
		int existeSession = 0;
		LOGG.info("[:::::.  sp_existSession - schema {} .:::::]", baseSchema);
		
		
		try {
			
			SimpleJdbcCall simpleJdbcCall	=	new SimpleJdbcCall(getJdbcTemplate())
					.withCatalogName(baseSchema)
					.withProcedureName("sp_existSession");
				
			SqlParameterSource params = new MapSqlParameterSource()
								.addValue("in_cveSession", cveSession );
			
			Map<String, Object> mapResults = simpleJdbcCall.execute(params);
			existeSession = Integer.parseInt(mapResults.get("out_respuesta").toString());
			
			if (existeSession != Constantes.VALOR_INT_CERO ) {
				LOGG.warn("[:::::. {}{}{} .:::::]",  mapResults.get("out_msg_error").toString()," WITH cveSession :> ", cveSession );
			}
			
		} catch (Exception e) {
			LOGG.error("[:::::::::::: existeSession {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		return existeSession;
	}
	
}
