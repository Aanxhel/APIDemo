package com.cdc.fis2022.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RequestChainScoreExtern {
	
	
	@JsonProperty("cadenaVariables")
	private String chainSend;

}
