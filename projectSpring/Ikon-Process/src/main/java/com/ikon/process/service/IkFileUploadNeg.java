/**
 * 
 */
package com.ikon.process.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author icb_ipsra
 *
 */
public interface IkFileUploadNeg {

	/***
	 * Sube un archivo
	 * @param multipartFile
	 * @return
	 */
	ResponseEntity<Object> uploadImagenServer ( MultipartFile multipartFile);
}
