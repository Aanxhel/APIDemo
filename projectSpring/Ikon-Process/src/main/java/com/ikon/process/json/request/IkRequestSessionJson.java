/**
 * 
 */
package com.ikon.process.json.request;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ikon.process.dto.IkSentenceDto;

import lombok.Data;

/**
 * @author icb_ipsra
 *
 */
@Data
@JsonIgnoreProperties
public class IkRequestSessionJson {

	private Integer cveSession;
	private String sessionName;
	private String sessionDesc;
	private ArrayList<IkSentenceDto> sentences;
}
