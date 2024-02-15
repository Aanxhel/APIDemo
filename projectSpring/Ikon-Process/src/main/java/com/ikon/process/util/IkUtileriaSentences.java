/**
 * 
 */
package com.ikon.process.util;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ikon.process.dto.IkCardDto;
import com.ikon.process.dto.IkCardsSentencesDto;
import com.ikon.process.json.response.IkResponseSentencesJson;


/**
 * @author IsraelC
 * @Since Julio 24, 2021
 *
 */
public class IkUtileriaSentences {

	private static final Logger LOGG = LoggerFactory.getLogger(IkUtileriaSentences.class);
	
	/*
	 * Arma el arreglo con todas las sentencias y sus tarjetas.
	 */
	public static ArrayList<IkResponseSentencesJson> armaReponseSentencesCards (ArrayList<IkCardsSentencesDto> catAllSentences  ){
		
		LOGG.info("[:::::.  Incia el armado del response Senteces Cards   .:::::]");
		
		ArrayList<IkResponseSentencesJson> arraySentences = new ArrayList<IkResponseSentencesJson>();
		ArrayList<IkCardDto> card = new ArrayList<>();
		Integer valorSentence = new Integer(0);
		IkCardDto cardSentence = new IkCardDto();
		int registroCero = 0;
		int posicion = 0;
		IkResponseSentencesJson respo = new IkResponseSentencesJson();
		
		valorSentence = catAllSentences.get(0).getCveSentence();
		
		for (IkCardsSentencesDto sent : catAllSentences) {
			respo = new IkResponseSentencesJson();
			cardSentence = new IkCardDto();
			
			if ( sent.getCveSentence() > valorSentence) {
				respo = new IkResponseSentencesJson();
				cardSentence = new IkCardDto();
				card = new ArrayList<>();
				posicion = 0; 
			} else {
				posicion += 1;
			}
			respo.setCveSentence(sent.getCveSentence());
			respo.setSentenceName(sent.getSentenceName());
			respo.setPathVideo(sent.getPathVideo());
			respo.setPathSound(sent.getPathSound());
			respo.setPathInfo(sent.getPathInfo());
			
			cardSentence.setCveCard(sent.getCveCard());
			cardSentence.setCardText(sent.getCardText());
			cardSentence.setTopicName(sent.getTopicName());
			card.add(cardSentence);
			
			
			if ( registroCero == 0 ) {
				respo.setCard(card);
				arraySentences.add(respo);
				registroCero += 1;
			}
			if ( registroCero > 0) {
				respo.setCard(card);
			}
			if ( sent.getCveSentence() > valorSentence && posicion == 0) {
				arraySentences.add(respo);
			}
			valorSentence = sent.getCveSentence();
		}
		
		respo = new IkResponseSentencesJson();
		cardSentence = new IkCardDto();
		card = new ArrayList<>();
		posicion = 0; 
		
		
		return arraySentences;
	}
	
}
