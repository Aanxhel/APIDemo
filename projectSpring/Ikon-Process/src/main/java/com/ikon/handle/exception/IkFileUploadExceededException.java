/**
 * 
 */
package com.ikon.handle.exception;

import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * @author icb_ipsra
 *
 */
public class IkFileUploadExceededException extends MaxUploadSizeExceededException {

	private static final long serialVersionUID = 1L;

	public IkFileUploadExceededException(long maxUploadSize, Throwable ex) {
		super(maxUploadSize, ex);
		// TODO Auto-generated constructor stub
	}

}
