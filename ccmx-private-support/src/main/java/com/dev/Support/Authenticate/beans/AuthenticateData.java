package com.dev.Support.Authenticate.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthenticateData {
	
	@JsonProperty("numUserId")
	private String numUserId;
	@JsonProperty("firstname")
	private String firstname;
	@JsonProperty("surnames")
	private String surnames;
	@JsonProperty("email")
	private String email;
	@JsonProperty("numLenderFather")
	private String numLenderFather;
	@JsonProperty("numLenderChannel")
	private String  numLenderChannel;
	@JsonProperty("token")
	private String  token;
	@JsonProperty("permissions")
	private Permissions permissions;
	@JsonProperty("reino")
	private String reino;
	@JsonProperty("username")
	private String username;
	
	@Override
	public String toString() {
		return "LdapDatos{" +
				"numUserId='" + numUserId + '\'' +
				", firstname='" + firstname + '\'' +
				", surnames='" + surnames + '\'' +
				", email='" + email + '\'' +
				", numLenderFather='" + numLenderFather + '\'' +
				", numLenderChannel='" + numLenderChannel + '\'' +
				", token='" + token + '\'' +
				", permissions=" + permissions +
				", reino='" + reino + '\'' +
				", username='" + username + '\'' +
				'}';
	}

}
