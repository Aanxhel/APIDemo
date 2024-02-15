/**
 * 
 */
package com.ikon.process.dao;

import java.util.ArrayList;

import com.ikon.process.dto.IkCardByTopicDto;
import com.ikon.process.dto.IkGetCard;
import com.ikon.process.json.request.IkRequestCard;

/**
 * @author icb_ipsra
 *
 */
public interface IkCardDao {

	/***
	 * Insert un registro
	 * @param request
	 * @return
	 * @throws Exception
	 */
	int insertCard(IkRequestCard request ) throws Exception;
	
	/**
	 * Modifica un registro
	 * @param request
	 * @return
	 * @throws Exception
	 */
	int updateCardTopic( IkRequestCard request ) throws Exception;
	
	/**
	 * Obtiene una lista de registros
	 * @param cveTopic
	 * @return
	 * @throws Exception
	 */
	 ArrayList<IkCardByTopicDto> obtineCardbyTopic( Integer cveTopic) throws Exception ;
	 
	 /**
	  * Elimina una card, si la tarjeta esta sociada a una sentencia
	  * Desde BD mandará un -10 y mensaje de error, mencionando  no es posible
	  * @param cveCard
	  * @return
	  * 	-10 ==> Eror tarjeta sociada
	  * 	1  ==> Tarjeta no existe
	  *     0  ==> Eliminado correctamente
	  * @throws Exception
	  */
	 int eliminaCard (Integer cveCard )  throws Exception;
	 
	 /***
	  * Obtienen  todas las tarjeras de acuerdo
	  * @param cveCard
	  * @return
	  * @throws Exception
	  */
	 ArrayList<IkGetCard> obtineCardbyID( Integer cveCard) throws Exception;
	 
	 /***
	  * Obtiene todas las tarjetas 
	  * @return
	  * @throws Exception
	  */
	 ArrayList<IkGetCard> obtineAllCards( ) throws Exception;
	 /**
	  * Valida la existencia de las tarjetas
	  * @param cveCard
	  * @return
	  * @throws Exception
	  */
	 int existeCards ( Integer cveCard ) throws Exception;
	 
	 /***
	  * Obtiene todas las tarjetas relacionadas a un topic Name
	  * @param topicName
	  * @return
	  * @throws Exception
	  */
	 ArrayList<IkCardByTopicDto> getCardByTopicName(  String topicName ) throws Exception; 
	 
	 
}
