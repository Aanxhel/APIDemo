package com.project.testing.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseEuler {

	@JsonProperty("resultado")
	private Integer resultado;

	@JsonProperty("errorCode")
	private Long errorCode;

	@JsonProperty("errorDescription")
	private List<String> errorDescription;

}
