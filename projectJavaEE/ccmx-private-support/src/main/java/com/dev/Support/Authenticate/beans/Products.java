package com.dev.Support.Authenticate.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data 
@JsonIgnoreProperties
public class Products {
	
	@JsonProperty  ("productKey")
	private String productKey;
	@JsonProperty ("descriptionProduct") 
	private String descriptionProduct;

}
