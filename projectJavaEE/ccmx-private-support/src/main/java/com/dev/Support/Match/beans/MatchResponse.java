package com.dev.Support.Match.beans;

import com.dev.Support.beans.BeanError;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchResponse extends BeanError {

	@JsonProperty("nperscve")
	private Long nperscve;
	@JsonProperty("npersdomfol")
	private Long npersdomfol;
	@JsonProperty("houseHoldId")
	private Long houseHoldId;
	@JsonProperty("success")
	private Boolean success = false;
	@JsonProperty("mensaje")
	private String mensaje;
	@JsonProperty("matchIlytics")
	private String matchIlytics;
	@JsonProperty("folioConsulta")
	private String folioConsulta;
	@Override
	
	public String toString() {
		return "MatchResponse [nperscve=" + nperscve + ", npersdomfol=" + npersdomfol + ", houseHoldId=" + houseHoldId
				+ ", success=" + success + ", mensaje=" + mensaje + ", matchIlytics=" + matchIlytics
				+ ", folioConsulta=" + folioConsulta + "]";
	}
	
	
}
