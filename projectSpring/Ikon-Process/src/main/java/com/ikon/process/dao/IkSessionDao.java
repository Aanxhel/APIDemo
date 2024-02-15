/**
 * 
 */
package com.ikon.process.dao;

import java.util.ArrayList;

import com.ikon.process.dto.IkCatSessionDto;

/**
 * @author icb_ipsra
 *
 */
public interface IkSessionDao {

	/**
	 * Obtiene un arreglo de sessiones
	 * @return
	 * @throws Exception
	 */
	ArrayList<IkCatSessionDto> obtieneSessiones() throws Exception;
	
	/**
	 * Inserta  un registro
	 * @param sessionName
	 * @param sesionDesc
	 * @return
	 * @throws Exception
	 */
	Integer insertSession (String sessionName, String sesionDesc ) throws Exception;
	
	/**
	 * Inserta la relacion de sessiones con sentencias
	 * @param sessionId
	 * @param sentenceId
	 * @return
	 * @throws Exception
	 */
	int insertSessionSentence (Integer sessionId, Integer sentenceId ) throws Exception;
	
	
	/***
	 * Valida la existencia de la session y sentences
	 * @param cveSession
	 * @param totalCards
	 * @return
	 * 	 0 ==> Apto para continual
	 *  -1 ==> No existe la session
	 *  -2 ==> El número de sentences no coincide con el actual 
	 *  -3 ==> El nombre de la sessión existe con otra session
	 * @throws Exception
	 */
	int existSessionSentences ( Integer cveSession, String sessionName ) throws Exception;
	
	
	/****
	 * Elimina todas las sentences correspondientes a la session
	 * @param cveSession
	 * @return
	 * @throws Exception
	 */
	int eliminaSessionSentences  ( Integer cveSession ) throws Exception;
	
	/***
	 * Inserta un registro con el mismo numeor de session
	 * @param cveSession --> Clase de session que ya eiste
	 * @param sessionName ---> Nombre de la session
	 * @param sessionDesc   ---> Descripción de las session;
	 * @return
	 * @throws Exception
	 */
	int updateSession (Integer cveSession, String sessionName, String sessionDesc ) throws Exception;
	
	/**
	 * Elimina una session
	 * @param cveSession
	 * @return
	 * 	  -1 No existe la session
	 *     0 Session eliminada
	 *   -10 Session Asociada a un Modulo 
	 * @throws Exception
	 */
	int eliminaSession (Integer cveSession )  throws Exception;
	
	/***
	 * Obtiene un arreglo de sessione por id
	 * @param cveSession
	 * @return
	 * @throws Exception
	 */
	ArrayList<IkCatSessionDto> obtieneSessionesById(Integer cveSession ) throws Exception;
	
	/***
	 * Devuelve un arreglo de todas las sessiones no asociadas a una sentencia
	 * @return
	 * @throws Exception
	 */
	ArrayList<IkCatSessionDto> obtieneSessionesNotAssociated() throws Exception;
	
	/***
	 * Valida si existe la session 
	 * @param cveSession
	 * @return
	 * 	-1 No existe la session
	 *   0 Existe la sesion 
	 * @throws Exception
	 */
	int existeSession ( Integer cveSession ) throws Exception;
	
}
