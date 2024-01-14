package com.cdc.fis2022.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chain {
	
	
	@JsonProperty("cadenaVariables")
	private String chain;


}
