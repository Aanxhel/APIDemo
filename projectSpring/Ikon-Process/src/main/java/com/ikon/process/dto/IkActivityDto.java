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
public class IkActivityDto {

	private Integer id;
	private String nomUsername;
	private String fecActivity;
	private String timStart;
	private String timEnd; 
	private String desModule;
	private String desSession;
	private String numSentences;
	/**
	 * @param nomUsername
	 * @param fecActivity
	 * @param timStart
	 * @param timEnd
	 * @param desModule
	 * @param desSession
	 * @param numSentences
	 */
	public IkActivityDto(String nomUsername, String fecActivity, String timStart, String timEnd, String desModule,
			String desSession, String numSentences) {
		super();
		this.nomUsername = nomUsername;
		this.fecActivity = fecActivity;
		this.timStart = timStart;
		this.timEnd = timEnd;
		this.desModule = desModule;
		this.desSession = desSession;
		this.numSentences = numSentences;
	}
	
	/**
	 * @param id
	 * @param nomUsername
	 * @param fecActivity
	 * @param timStart
	 * @param timEnd
	 * @param desModule
	 * @param desSession
	 * @param numSentences
	 */
	public IkActivityDto(Integer id, String nomUsername, String fecActivity, String timStart, String timEnd,
			String desModule, String desSession, String numSentences) {
		super();
		this.id = id;
		this.nomUsername = nomUsername;
		this.fecActivity = fecActivity;
		this.timStart = timStart;
		this.timEnd = timEnd;
		this.desModule = desModule;
		this.desSession = desSession;
		this.numSentences = numSentences;
	}

	/**
	 * 
	 */
	public IkActivityDto() {
		super();
	} 
	
	
	
	
	
}
