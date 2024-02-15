/**
 * 
 */
package com.ikon.process.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ikon.handle.exception.IkFileUploadExceededException;

/**
 * @author icb_ipsra
 *
 */
@RestControllerAdvice
public class IKFileUploadExceptionController {

	@ExceptionHandler(value = IkFileUploadExceededException.class)
	public ResponseEntity<Object> exception(IkFileUploadExceededException exception) {
	      return new ResponseEntity<>("Maximum upload size of: ", HttpStatus.BANDWIDTH_LIMIT_EXCEEDED );
	   }
}
