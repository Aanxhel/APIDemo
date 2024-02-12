/**
 * 
 */
package com.ikon.process.json.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author icb_ipsra
 *
 */
@Data
@JsonIgnoreProperties
public class IkRequestFileJson {

	String datoImagen;
}
