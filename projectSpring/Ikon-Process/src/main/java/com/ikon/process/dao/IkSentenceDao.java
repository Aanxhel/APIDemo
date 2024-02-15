/**
 * 
 */
package com.ikon.process.dao;

import java.util.ArrayList;

import com.ikon.process.dto.IkCardsSentencesDto;
import com.ikon.process.dto.IkSentenceCardDto;

/**
 * Implementa la firma de cada uno de los metodos de la
 * clase IkSentenceDaoImpl
 * @author icb_ipsra
 * @since Octuber, 2021
 * @version 0.2.1
 *
 */
public interface IkSentenceDao {

	/***
	 * Inserta la sentencia completa
	 * @param ikSentenceCardDto Objeto transportador de datos
	 * @return
	 * @throws Exception
	 */
	Integer insertSentence (IkSentenceCardDto ikSentenceCardDto ) throws Exception;
	
	/**
	 * Inserta la relacón de una sentencia con n... cantidad de tarjetas 
	 * @param sentenceId  Id Sentencia principal
	 * @param cardId ---> Id Tarjeta asignada a la sentencia
	 * @return
	 * @throws Exception
	 */
	int insertSentenceCard (Integer sentenceId, Integer cardId ) throws Exception;
	
	/***
	 * Modifica los recursos de una sentencia.
	 * @param ikSentenceCardDto
	 * @return
	 * @throws Exception
	 */
	int updateSentence (IkSentenceCardDto ikSentenceCardDto ) throws Exception;
	
	/***
	 * Se obtiene todos las sentencias y tarjetas asignadas.
	 * @return
	 * @throws Exception
	 */
	ArrayList<IkCardsSentencesDto> getAllSentencesCards () throws Exception;
	
	/**
	 * Eliminando sentencias 
	 * @param cveSentence
	 * @return
	 *    -1  No existe la sentencia
	 *     0  Sentencia eliminada "No esta asociada a una Session"
	 *   -10  Sentencia asociada a una session
	 *    
	 * @throws Exception
	 */
	int eliminaSentence (Integer cveSentence )  throws Exception;
	
	/***
	 * Obtiene los datos de una sentencias por ID
	 * @param cveSentence
	 * @return
	 * @throws Exception
	 */
	ArrayList<IkCardsSentencesDto> getSentencesCardsByID (Integer cveSentence) throws Exception;
	
	/***
	 * Todas las sentencias no asociadas a una session
	 * @return
	 * @throws Exception
	 */
	ArrayList<IkCardsSentencesDto> notassociatedSession () throws Exception;
	
	/**
	 * 
	 * Valida la existencia de una sentencia
	 * @param cveSentence
	 * @return
	 * @throws Exception
	 */
	int existeSentence ( Integer cveSentence ) throws Exception;
	
	/***
	 * Elimina todas las tarjetas asignadas a una sentencia
	 * @param cveSentence
	 * @return
	 * @throws Exception
	 */
	int eliminaCardSentence (Integer cveSentence )  throws Exception;
	
}
