package com.dev.Support.Authenticate.beans;

import com.dev.Support.Authenticate.beans.KeycloakData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

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
public class KeycloakResponse {

    @JsonProperty("data")
    @SerializedName("data")
    private KeycloakData keycloakdata;
    @JsonProperty("iss")
    @SerializedName("iss")
	private String ruta;
	
}
