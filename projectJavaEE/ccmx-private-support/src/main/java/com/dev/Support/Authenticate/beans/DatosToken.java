package com.dev.Support.Authenticate.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DatosToken {
	private Long id;
	private Long exp;
	private Long iat;
	private String sub;
	private Permissions permissions;
	private String lender;
	
}
