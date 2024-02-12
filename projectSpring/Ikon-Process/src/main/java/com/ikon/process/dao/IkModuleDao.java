/**
 * 
 */
package com.ikon.process.dao;

import java.util.ArrayList;

import com.ikon.process.dto.CompModuleDto;
import com.ikon.process.dto.IkModSessionCardsDto;
import com.ikon.process.dto.IkModSessionHeaderDto;

/**
 * @author icb_ipsra
 * @since Septiembre, 2021
 *
 */
public interface IkModuleDao {

	/**
	 * Obtiene toda la lissta de Modulos
	 * @return Objeto de tipo ArrayList<CompModuleDto>
	 * @throws Exception
	 */
	ArrayList<CompModuleDto> obtieneModule() throws Exception;
	
	/**
	 * Inserta  un registro
	 * @param sessionName
	 * @param sesionDesc
	 * @return
	 * @throws Exception
	 */
	Integer insertModule (String moduleName, String moduleDesc) throws Exception;
	/**
	 * Inserta la relacion de module con sessiones
	 * @param moduleId
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	int insertModuleSession (Integer moduleId, Integer sessionId ) throws Exception;
	/***
	 * Valida la existencia de la session y sentences
	 * @param cveModule
	 * @param moduleName
	 * @return
	 * 	 0 ==> Apto para continual
	 *  -1 ==> No existe la module
	 *  -3 ==> El nombre de la Module existe con otra module
	 * @throws Exception
	 */
	int existModuleSession ( Integer cveModule, String moduleName ) throws Exception;
	/***
	 * Inserta un registro con el mismo numeor de module
	 * @param cveModule --> Clase de module que ya eiste
	 * @param moduleName ---> Nombre de la module
	 * @param moduleDesc   ---> Descripción de las module;
	 * @return
	 * @throws Exception
	 */
	int updateModule (Integer cveModule, String moduleName, String moduleDesc ) throws Exception;
	
	/***
	 * Obtiene la lista de sentencias correspondientes al modulo y session que se este 
	 * solicitando 
	 * @param moduleId
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	ArrayList<IkModSessionHeaderDto> getModSessionHeader (Integer moduleId, Integer sessionId  )  throws Exception ;
	
	/***
	 * Obtiene la lista de tarjetas correspondientes a las sentencias al modulo, session  
	 * @param moduleId
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	ArrayList<IkModSessionCardsDto> getModSessionCard (Integer moduleId, Integer sessionId  )  throws Exception;
	
	/***
	 * Valida la existencia del modulo
	 * @param cveModule --> Clave del modulo a validar
	 * @return 
	 * 	  1 Existe
	 *   -1 No existe
	 * @throws Exception
	 */
	int moduleExists (Integer cveModule )  throws Exception;
	
	
	/***
	 * Obtiene la lista de sessiones de acuerdo a la clave del modulo
	 * @param cveModule
	 * @return
	 * @throws Exception
	 */
	ArrayList<Integer> getAllSessionesByModule (Integer cveModule )  throws Exception;
	
	/**
	  * Elimina todo un modulo completo (Sessiones-Sentences), dejando libres las tarjetas 
	  * @param cveModule
	  * @return
	  * 	1  ==> Tarjeta no existe
	  *     0  ==> Eliminado correctamente
	  * @throws Exception
	  */
	int eliminaModule (Integer cveModule )  throws Exception;
	
	/***
	 * Obtiene las sentencias asociadas a las sessiones:
	 * A la vez, elimina ModuleSession, Session, module 
	 * @param cveModule --> Clave del modulo
	 * @param cveSession ---> Clave de la session
	 * @return
	 * 		Objeto de tipo ArrayList<Integer> con todas las sentencias.
	 * @throws Exception
	 */
	ArrayList<Integer> getAllSentencesBySessionAndModule (Integer cveModule, Integer cveSession )  throws Exception;
	
	/***
	 * Elimina SessionSentencia y la sentencia
	 * @param cveSession
	 * @param cveSentence
	 * @return
	 * @throws Exception
	 */
	int deleteSenteceModuleSession (Integer cveSession, Integer cveSentence )  throws Exception;
	
	/***
	 *  Getting module information by ID
	 * @param cveModule
	 * @return
	 * @throws Exception
	 */
	ArrayList<CompModuleDto> obtieneModuleById(Integer cveModule) throws Exception;
	
}
