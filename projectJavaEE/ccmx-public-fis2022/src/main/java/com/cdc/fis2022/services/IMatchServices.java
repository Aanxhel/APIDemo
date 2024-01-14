package com.cdc.fis2022.services;



import com.cdc.fis2022.beans.Persona;
import com.cdc.validacionescore.response.MatchResponse;

public interface IMatchServices {
	
//	public MatchResponse consultarPorNpersCve(String npersCve);
	// Persona de validacionesCore
	public MatchResponse hacerMatchPorDatosGenerales(Persona request);
	
	
}
