package com.cdc.fis2022.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonInclude(value = Include.NON_EMPTY, content = Include.NON_NULL)
public class ResponseScoreExtern {
/*	
	@JsonProperty("Des_Exclusion_code")
	private String des_Exclusion_code;
	
	@JsonProperty("Exclusion_code")
	private String exclusion_code;
	
	
	@JsonProperty("score")
	private Integer score;
*/
	@JsonProperty("scoreNoHit")
	private ScoreNoHit scoreNoHit;
	
	
	

}
