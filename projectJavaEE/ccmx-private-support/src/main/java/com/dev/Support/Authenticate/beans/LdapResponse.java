package com.dev.Support.Authenticate.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data 
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LdapResponse {
	
	@JsonProperty("errorCode")
	private Long errorCode;
	@JsonProperty("isSuccess")
    private Boolean isSuccess;
    @JsonProperty("message")
    private String message;
    @JsonProperty("errors")
    private String errors;
    @JsonProperty("data")
    private AuthenticateData data;

}
