package com.cdc.fis2022.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = Include.NON_EMPTY, content = Include.NON_NULL)
public class ScoreNoHit {
	
	
	@JsonProperty("codigo_descripcion")
	private String des_Exclusion_code;
	
	@JsonProperty("codigo")
	private String exclusion_code;
	
	
	@JsonProperty("valor")
	private Integer score;

}
