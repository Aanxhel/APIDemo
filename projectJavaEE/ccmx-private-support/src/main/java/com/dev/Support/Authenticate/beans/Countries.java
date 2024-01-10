package com.dev.Support.Authenticate.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties
public class Countries {
	
	@JsonProperty ("country")
	private String country;
	@JsonProperty ("contexts")
	private List<Contexts> contexts;

}
