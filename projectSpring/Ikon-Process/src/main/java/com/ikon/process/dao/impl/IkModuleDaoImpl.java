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

import com.ikon.process.dao.IkModuleDao;
import com.ikon.process.dto.CompModuleDto;
import com.ikon.process.dto.IkModSessionCardsDto;
import com.ikon.process.dto.IkModSessionHeaderDto;
import com.ikon.process.util.Constantes;

/**
 * @author icb_ipsra
 * @since Octuber, 2021
 *
 */
@Repository("ikModuleDao")
public class IkModuleDaoImpl extends GenericDao implements IkModuleDao {

	private static final Logger LOGG = LoggerFactory.getLogger(IkModuleDaoImpl.class);

	@Value("${base.schema}")
	private String baseSchema;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.dao.IkModuleDao@obtieneModule()
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<CompModuleDto> obtieneModule() throws Exception {
		ArrayList<CompModuleDto> catModule = new ArrayList<>();

		try {

			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(getJdbcTemplate())
					.withCatalogName(this.baseSchema)
					.withProcedureName("sp_obtieneModulo")
					.returningResultSet("cboModule", new RowMapper<CompModuleDto>() {

						@Override
						public CompModuleDto mapRow(ResultSet rs, int rowNum) throws SQLException {
							CompModuleDto valor = new CompModuleDto();
							valor.setCveModule(rs.getInt("cveModule"));
							valor.setModuleName(rs.getString("moduleName"));
							valor.setModuleDesc(rs.getString("moduleDesc"));
							valor.setTotalSessiones(rs.getInt("totalSessiones"));
							valor.setCveSession(rs.getInt("cveSession"));
							valor.setSessionName(rs.getString("sessionName"));
							return valor;
						}

					});
			
			Map<String, Object> map = simpleJdbcCall.execute();
			catModule = (ArrayList<CompModuleDto>) map.get("cboModule");
			

		} catch (Exception e) {
			LOGG.error("[:::::::::::: obtieneModule {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		return catModule;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.dao.IkModuleDao@insertModule()
	 */
	public Integer insertModule (String moduleName, String moduleDesc) throws Exception {
		Integer sequenceModule = new Integer(0);
		LOGG.info("[:::::. insertModule topic - schema {} .:::::]", baseSchema);
		
		try {
			
			SimpleJdbcCall simpleJdbcCall	=	new SimpleJdbcCall(getJdbcTemplate())
					.withCatalogName(baseSchema)
					.withProcedureName("sp_insert_module");
				
			SqlParameterSource params = new MapSqlParameterSource()
												.addValue("in_module_name", moduleName )
												.addValue("in_module_desc", moduleDesc);
			
			Map<String, Object> mapResults = simpleJdbcCall.execute(params);
			sequenceModule = Integer.parseInt(mapResults.get("out_sequence").toString());
			
			if ( sequenceModule < Constantes.VALOR_INT_UNO ) {
				LOGG.warn("[:::::. {}{}{} .:::::]",  mapResults.get("out_msg_error").toString()," WITH:> ", moduleName);
			} else {
				LOGG.info("[:::::. {}{}{}{} .:::::]", "Module name:> ",moduleName, " cveModule:> ", sequenceModule);
			}
			
		} catch (Exception e) {
			LOGG.error("[:::::::::::: insertModule {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		return sequenceModule;
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.dao.IkModuleDao@insertModule()
	 */
	public int insertModuleSession (Integer moduleId, Integer sessionId ) throws Exception {
		int statusInsert = 0;
		
		LOGG.info("[:::::. Inserting insertModuleSession - schema {} .:::::]", baseSchema);
		try {
			
			SimpleJdbcCall simpleJdbcCall	=	new SimpleJdbcCall(getJdbcTemplate())
					.withCatalogName(baseSchema)
					.withProcedureName("sp_i_module_session");
				
			SqlParameterSource params = new MapSqlParameterSource()
								.addValue("in_module_id", moduleId )
								.addValue("in_session_id", sessionId);
			
			
			Map<String, Object> mapResults = simpleJdbcCall.execute(params);
			statusInsert = Integer.parseInt(mapResults.get("out_respuesta").toString());
			
			if ( statusInsert == Constantes.VALOR_INT_UNO ) {
				LOGG.warn("[:::::. {}{}{} .:::::]",  mapResults.get("out_msg_error").toString()," WITH moduleId:> ", moduleId );
			} else {
				LOGG.info("[:::::. {}{}{}{} .:::::]", "INSERT  insertModuleSession moduleId:> ", moduleId, " sessionId:> ", sessionId);
			} 
			
		} catch (Exception e) {
			LOGG.error("[:::::::::::: insertModuleSession {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		return statusInsert;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.dao.IkModuleDao@existModuleSession()
	 */
	public int existModuleSession ( Integer cveModule, String moduleName ) throws Exception {
		int recordFine = 0;
		LOGG.info("[:::::.  existModuleSession - schema {} .:::::]", baseSchema);
		
		
		try {
			
			SimpleJdbcCall simpleJdbcCall	=	new SimpleJdbcCall(getJdbcTemplate())
					.withCatalogName(baseSchema)
					.withProcedureName("sp_existModuleSession");
				
			SqlParameterSource params = new MapSqlParameterSource()
								.addValue("in_cveModule", cveModule )
								.addValue("in_moduleName", moduleName );
			
			
			Map<String, Object> mapResults = simpleJdbcCall.execute(params);
			recordFine = Integer.parseInt(mapResults.get("out_respuesta").toString());
			
			LOGG.warn("[:::::. {}{}{} .:::::]",  mapResults.get("out_msg_error").toString()," WITH cveModule :> ", cveModule );
			
		} catch (Exception e) {
			LOGG.error("[:::::::::::: existModuleSession {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		return recordFine;
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.dao.IkModuleDao@existModuleSession()
	 */
	
	public int updateModule (Integer cveModule, String moduleName, String moduleDesc ) throws Exception  {
		int recordUpdated = -1;
		
		LOGG.info("[:::::. updateSession - schema {} .:::::]", baseSchema);
		try {
			
			SimpleJdbcCall simpleJdbcCall	=	new SimpleJdbcCall(getJdbcTemplate())
					.withCatalogName(baseSchema)
					.withProcedureName("sp_update_module");
				
			SqlParameterSource params = new MapSqlParameterSource()
								.addValue("in_cveModule", cveModule )
								.addValue("in_module_name", moduleName )
								.addValue("in_module_desc", moduleDesc );
			
			
			Map<String, Object> mapResults = simpleJdbcCall.execute(params);
			recordUpdated = Integer.parseInt(mapResults.get("out_sequence").toString());
			
			LOGG.info("[:::::. {}{}{}{} .:::::]", "UPDATE  updateModule cveModule:> ",cveModule );
			
		} catch (Exception e) {
			e.printStackTrace();
			LOGG.error("[:::::::::::: updateModule {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		return recordUpdated;
	} 
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.dao.IkModuleDao@getModSessionHeader(java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<IkModSessionHeaderDto> getModSessionHeader (Integer moduleId, Integer sessionId  )  throws Exception  { 
		ArrayList<IkModSessionHeaderDto> arrayModSessionHeader = new ArrayList<>();
		
		try {

			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(getJdbcTemplate()).withCatalogName(baseSchema)
					.withProcedureName("sp_mod_sess_header")
					.returningResultSet("responseModSession", new RowMapper<IkModSessionHeaderDto>() {

						@Override
						public IkModSessionHeaderDto mapRow(ResultSet rs, int rowNum) throws SQLException {
							return new IkModSessionHeaderDto(
																rs.getString("moduleName"), 
																rs.getString("sessionName"), 
																Integer.valueOf(rs.getInt("cveSentence")),
																rs.getString("sentenceName"), 
																rs.getString("sentenceOrder"),
																rs.getString("audioSent"),
																rs.getString("videoSent"),
																rs.getString("infoSent"));
						}

					});
			
			SqlParameterSource params = new MapSqlParameterSource()
													.addValue("in_module_id", moduleId)
													.addValue("in_session_id", sessionId);
			
			Map<String, Object> map = simpleJdbcCall.execute(params);
			arrayModSessionHeader = (ArrayList<IkModSessionHeaderDto>) map.get("responseModSession");
			

		} catch (Exception e) {
			LOGG.error("[:::::::::::: getModSessionHeader {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		return arrayModSessionHeader;
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.dao.IkModuleDao@getModSessionCard(java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings({ "unchecked" })
	public ArrayList<IkModSessionCardsDto> getModSessionCard (Integer moduleId, Integer sessionId  )  throws Exception  { 
		ArrayList<IkModSessionCardsDto> arrayModSessionCard = new ArrayList<>();
		
		try {

			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(getJdbcTemplate()).withCatalogName(baseSchema)
					.withProcedureName("sp_mod_sess_cards")
					.returningResultSet("responseModSessionCard", new RowMapper<IkModSessionCardsDto>() {

						@Override
						public IkModSessionCardsDto mapRow(ResultSet rs, int rowNum) throws SQLException {
							return new IkModSessionCardsDto(Integer.valueOf(rs.getInt("cveCard")), 
																rs.getString("textCard"),
																rs.getString("imagenCard"),
																rs.getString("soundCard"), 
																rs.getString("meaningCard"), 
																Integer.valueOf(rs.getInt("cvetopic")),
																rs.getString("topicName"));
						}

					});
			
			
			SqlParameterSource params = new MapSqlParameterSource()
											.addValue("in_module_id", moduleId)
											.addValue("in_session_id", sessionId);
			
			Map<String, Object> map = simpleJdbcCall.execute(params);
			arrayModSessionCard = (ArrayList<IkModSessionCardsDto>) map.get("responseModSessionCard");
			

		} catch (Exception e) {
			LOGG.error("[:::::::::::: getModSessionCard {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		return arrayModSessionCard;
	}
	
	
	//------ Proceso para eliminar modulo - Sessiones - Sentencias -----// 
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.dao.IkModuleDao@moduleExists(java.lang.Integer)
	 */
	public int moduleExists (Integer cveModule )  throws Exception {
		int modExists = 0;

		LOGG.info("[:::::.  {} .:::::]", "Validando existencia del modulo");	

		try {
			
			SimpleJdbcCall simpleJdbcCall	=	new SimpleJdbcCall(getJdbcTemplate())
					.withCatalogName(baseSchema)
					.withProcedureName("sp_module_exists");
				
			SqlParameterSource params = new MapSqlParameterSource()
								.addValue("in_cveModule", cveModule );
			
			
			Map<String, Object> mapResults = simpleJdbcCall.execute(params);
			modExists = Integer.parseInt(mapResults.get("out_respuesta").toString());
			
			LOGG.info("[:::::. status: {} message: {}{}{} .:::::]", modExists, mapResults.get("out_msg_error").toString()," WITH cveModule : ", cveModule );
			
		} catch (Exception e) {
			LOGG.error("[:::::::::::: moduleExists {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		return modExists;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.dao.IkModuleDao@getAllSessionesByModule( java.lang.Integer )
	 */
	@SuppressWarnings({ "unchecked" })
	public ArrayList<Integer> getAllSessionesByModule (Integer cveModule )  throws Exception  { 
		ArrayList<Integer> arraySessiones = new ArrayList<>();
		
		try {

			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(getJdbcTemplate()).withCatalogName(baseSchema)
					.withProcedureName("sp_getModDelSessiones")
					.returningResultSet("responseSessiones", new RowMapper<Integer>() {

						@Override
						public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
							return new Integer(rs.getInt("cveSession"));
						}
					});

			SqlParameterSource params = new MapSqlParameterSource()
											.addValue("in_cveModule", cveModule);
			
			Map<String, Object> map = simpleJdbcCall.execute(params);
			arraySessiones = (ArrayList<Integer>) map.get("responseSessiones");

		} catch (Exception e) {
			LOGG.error("[:::::::::::: getAllSessionesByModule {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		return arraySessiones;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.dao.IkModuleDao@getAllSentencesBySessionAndModule( java.lang.Integer, java.lang.Integer )
	 */
	@SuppressWarnings({ "unchecked" })
	public ArrayList<Integer> getAllSentencesBySessionAndModule (Integer cveModule, Integer cveSession )  throws Exception { 
		ArrayList<Integer> arraySentences = new ArrayList<>();
		
		try {

			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(getJdbcTemplate()).withCatalogName(baseSchema)
					.withProcedureName("sp_getModDelSessSentence")
					.returningResultSet("responseSentences", new RowMapper<Integer>() {

						@Override
						public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
							return new Integer(rs.getInt("cveSentence"));
						}
					});

			SqlParameterSource params = new MapSqlParameterSource()
											.addValue("in_cveModule", cveModule)
											.addValue("in_cveSession", cveSession);
			
			Map<String, Object> map = simpleJdbcCall.execute(params);
			arraySentences = (ArrayList<Integer>) map.get("responseSentences");

		} catch (Exception e) {
			LOGG.error("[:::::::::::: getAllSentencesBySessionAndModule {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		return arraySentences;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ikon.process.dao.IkModuleDao@eliminaModule(java.lang.Integer)
	 */
	public int eliminaModule (Integer cveModule )  throws Exception {
		int respuestaDelete = -1;
		
		try {
			
			SimpleJdbcCall simpleJdbcCall	=	new SimpleJdbcCall(getJdbcTemplate())
					.withCatalogName(baseSchema)
					.withProcedureName("sp_delete_module");
				
			SqlParameterSource params = new MapSqlParameterSource()
								.addValue("in_cveModule", cveModule);
								
			Map<String, Object> mapResults = simpleJdbcCall.execute(params);
			respuestaDelete = Integer.parseInt(mapResults.get("out_respuesta").toString());
			
			LOGG.info("[:::::. {}{} .:::::]", "Stats DELETE ModuleNumber:> ", respuestaDelete , " with de ModuleNumber " +  cveModule );
			
		} catch (Exception e) {
			LOGG.error("[:::::::::::: eliminando Modules {}  :::::::::]", e.getMessage());
			throw new Exception();
		}

		return respuestaDelete;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ikon.process.dao.IkModuleDao@deleteSenteceModuleSession(java.lang.Integer, java.lang.Integer)
	 */
	public int deleteSenteceModuleSession (Integer cveSession, Integer cveSentence )  throws Exception {
		int respuestaDelete = -1;
		
		try {
			
			SimpleJdbcCall simpleJdbcCall	=	new SimpleJdbcCall(getJdbcTemplate())
					.withCatalogName(baseSchema)
					.withProcedureName("sp_getDeleteSenModule");
				
			SqlParameterSource params = new MapSqlParameterSource()
								.addValue("in_cveSession", cveSession)
								.addValue("in_cveSentence", cveSentence);
								
			Map<String, Object> mapResults = simpleJdbcCall.execute(params);
			respuestaDelete = Integer.parseInt(mapResults.get("out_respuesta").toString());
			
			LOGG.info("[:::::. {} CveSession {} cveSentence{} .:::::]", "Stats DELETE Sentence:> ", respuestaDelete, cveSession, cveSentence);
			
		} catch (Exception e) {
			LOGG.error("[:::::::::::: eliminando deleteSenteceModuleSession {}  :::::::::]", e.getMessage());
			throw new Exception();
		}

		return respuestaDelete;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.dao.IkModuleDao@obtieneModule()
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<CompModuleDto> obtieneModuleById(Integer cveModule) throws Exception {
		ArrayList<CompModuleDto> catModule = new ArrayList<>();

		try {

			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(getJdbcTemplate())
					.withCatalogName(this.baseSchema)
					.withProcedureName("sp_getModuleById")
					.returningResultSet("cboModulebyId", new RowMapper<CompModuleDto>() {

						@Override
						public CompModuleDto mapRow(ResultSet rs, int rowNum) throws SQLException {
							CompModuleDto valor = new CompModuleDto();
							valor.setCveModule(rs.getInt("cveModule"));
							valor.setModuleName(rs.getString("moduleName"));
							valor.setModuleDesc(rs.getString("moduleDesc"));
							valor.setTotalSessiones(1);
							valor.setCveSession(rs.getInt("cveSession"));
							valor.setSessionName(rs.getString("sessionName"));
							return valor;
						}

					});
			
			SqlParameterSource params = new MapSqlParameterSource()
					.addValue("in_module_id", cveModule);
			
			Map<String, Object> map = simpleJdbcCall.execute(params);
			
			catModule = (ArrayList<CompModuleDto>) map.get("cboModulebyId");
			
			LOGG.info("[:::::. Consulting module by ID {}  .:::::]", cveModule);

		} catch (Exception e) {
			LOGG.error("[:::::::::::: obtieneModule by ID {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		return catModule;
	}
	

	
	
}
