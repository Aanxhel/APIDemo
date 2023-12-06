package com.dev.Support.Match.beans;

import com.dev.Support.Match.beans.Persona;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchRequest {
	
	@JsonProperty("nombre")
    private String nombre;
    @JsonProperty("apellidoPaterno")
    private String apellidoPaterno;
    @JsonProperty("apellidoMaterno")
    private String apellidoMaterno;
    @JsonProperty("apellidoAdicional")
    private String apellidoAdicional;
    @JsonProperty("fechaNacimiento")
    private String fechaNacimiento;
    @JsonProperty("direccion")
    private String direccion;
    @JsonProperty("colonia")
    private String colonia;
    @JsonProperty("ciudad")
    private String ciudad;
    @JsonProperty("estado")
    private String estado;
    @JsonProperty("pais")
    private String pais;
    @JsonProperty("delegacionMunicipio")
    private String delegacionMunicipio;
    @JsonProperty("cp")
    private String cp;
    @JsonProperty("telefono")
    private String telefono;
    @JsonProperty("rfc")
    private String rfc;
    @JsonProperty("curp")
    private String curp;

	public MatchRequest(Persona persona){
		this.nombre = persona.getPrimerNombre() + persona.getSegundoNombre();
		this.apellidoPaterno = persona.getApellidoPaterno();
		this.apellidoMaterno = persona.getApellidoMaterno();
		this.apellidoAdicional = persona.getApellidoAdicional();
		this.fechaNacimiento = persona.getFechaNacimiento();
		this.rfc = persona.getRfc();
		this.curp = persona.getCurp();
		if(persona.getDomicilio() != null){
			this.direccion = persona.getDomicilio().getDireccion();
			this.estado = persona.getDomicilio().getEstado();
			this.pais = persona.getDomicilio().getPais();
			this.delegacionMunicipio = persona.getDomicilio().getMunicipio();
			this.cp = persona.getDomicilio().getCp();
			this.telefono = persona.getDomicilio().getNumeroTelefono();
		}
	}

}
