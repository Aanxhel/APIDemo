package com.cdc.testing.beans;

import java.io.Serializable;

import com.cdc.Support.beans.ErrorResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Response extends ErrorResponse implements Serializable {
	
	@JsonProperty("rfc")
	private String rfc; 
}
