package com.dev.Support.Facturacion.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FacturacionRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String cveProducto;
	private String cvePersona;
	private String cveRetorno;
	private String tiempoBusqIlytics;
	private String tiempoBusqueda;
	private String indicadorFirma;
	private String tipoMedioConsulta;
	private String sNombreServer;
	private String claveUsuario;
	private String numeroFirma;
	private String claveOtorganteXML;
	private String folio;
	private String folioOtorgante;

	private String messageid;
	private String tipo;
	private String payload;
	private String headers;
	private String statusCode;
	private String verb;
	private String apiproxyName;
	private String apiproxyRevision;
	private String developerId;
	private String apiproductName;
	private String appId;

	@Override
	public String toString() {
		return "FacturacionRequest [cveProducto=" + cveProducto + ", cvePersona=" + cvePersona + ", cveRetorno="
				+ cveRetorno + ", tiempoBusqIlytics=" + tiempoBusqIlytics + ", tiempoBusqueda=" + tiempoBusqueda
				+ ", indicadorFirma=" + indicadorFirma + ", tipoMedioConsulta=" + tipoMedioConsulta + ", sNombreServer="
				+ sNombreServer + ", claveUsuario=" + claveUsuario + ", numeroFirma=" + numeroFirma
				+ ", claveOtorganteXML=" + claveOtorganteXML + ", folio=" + folio + ", folioOtorgante=" + folioOtorgante
				+ ", messageid=" + messageid + ", tipo=" + tipo + ", payload=" + payload + ", headers=" + headers
				+ ", statusCode=" + statusCode + ", verb=" + verb + ", apiproxyName=" + apiproxyName
				+ ", apiproxyRevision=" + apiproxyRevision + ", developerId=" + developerId + ", apiproductName="
				+ apiproductName + ", appId=" + appId + "]";
	}
}