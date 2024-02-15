/**
 * 
 */
package com.ikon.process.dto;

import lombok.Data;

/**
 * @author icb_ipsra
 *
 */
@Data
public class IkCatSessionDto {

	private Integer totalSentences;
	private Integer cveSession;
	private String sessionName;
	private String sesionDesc;
	private Integer cveSentence;
	private String sentenceName;
	
	
	/**
	 * @param cveSession
	 * @param sessionName
	 * @param sesionDesc
	 */
	public IkCatSessionDto(Integer totalSentences, Integer cveSession, String sessionName, String sesionDesc, Integer cveSentence, String sentenceName) {
		super();
		this.totalSentences = totalSentences;
		this.cveSession = cveSession;
		this.sessionName = sessionName;
		this.sesionDesc = sesionDesc;
		this.cveSentence = cveSentence;
		this.sentenceName = sentenceName;
	}
	
	
	
	
}
