package com.dev.Support.Authenticate.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data 
@JsonIgnoreProperties
public class LdapRequest {
	@JsonProperty("user")
	private String user;
	@JsonProperty("password")
	private String password;
	@JsonProperty("numUserId")
	private String numUserId;
	@JsonProperty("countryId")
	private String countryId;
	@JsonProperty("applicationName")
	private String applicationName;
	@JsonProperty("newLdap")
	private Long newLdap;
	
}
