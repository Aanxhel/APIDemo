package com.dev.Support.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class BeanError {
	
    @JsonProperty("success")
    private Boolean success;
	@JsonProperty("code")
    private Long code;
	@JsonProperty("message")
    private String message;

}
