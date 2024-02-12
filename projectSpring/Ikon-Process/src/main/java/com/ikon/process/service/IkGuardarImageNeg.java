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
public interface IkGuardarImageNeg {

	ResponseEntity<Object> uploadImagenServer ( MultipartFile multipartFile);
	
	ResponseEntity<Object> getImagenServer (String tipoArchivo, String datoImagen);
}
