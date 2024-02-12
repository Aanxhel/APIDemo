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
public class RequestFrameAJson {

	private Integer cveModule;
    private Integer cveTopic;
    private String desTxtSent;
    private String pathDesImgSent;
    private String pathDSoundSent;
    private String pathVideoSent;
    private String pathMeanSent;
    private String pathDesInfoSent;
    private Integer cveStatus;
	
}
