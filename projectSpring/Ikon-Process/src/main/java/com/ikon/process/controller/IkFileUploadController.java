/**
 * 
 */
package com.ikon.process.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ikon.process.service.IkFileUploadNeg;

/**
 * @author icb_ipsra
 *
 */
@RestController
@RequestMapping(value="/v1/")
public class IkFileUploadController {

	private static final Logger LOGG = LoggerFactory.getLogger(IkFileUploadController.class);
	
	@Autowired
	private IkFileUploadNeg ikFileUploadNeg;
	
	
	@RequestMapping(value = "hi", method = RequestMethod.GET)
	public String hello () {
		LOGG.info("[::::::::: {} :::::::::]", "Iniciando Hi");
		String valorBD = "isra";
		return "Inforacion sensible ".concat(valorBD);
	}
	
	@CrossOrigin(origins = "*", maxAge = 3600 , methods = {RequestMethod.POST})
	@RequestMapping(value = "upload", method = RequestMethod.POST, headers = "Content-Type=multipart/form-data")
	public ResponseEntity<Object> uploadFile (@RequestParam("file") MultipartFile multipartFile) {
		ResponseEntity<Object> entity = null;
		
		LOGG.info("[::::::::: File {} :::::::::]", multipartFile.getOriginalFilename());
		entity = this.ikFileUploadNeg.uploadImagenServer(multipartFile);
		
		return entity;
	}
	
	
	
	
	
	
}
