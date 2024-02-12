/**
 * 
 */
package com.ikon.process.dto;

import java.util.ArrayList;

import lombok.Data;

/**
 * @author icb_ipsra
 *
 */
@Data
public class IkCatSessionSentenceDto {

	private Integer cveSession;
	private String sessionName;
	private String sesionDesc;
	private Integer totalSentences;
	private ArrayList<IkSentencesDto> sentences;
	
}
