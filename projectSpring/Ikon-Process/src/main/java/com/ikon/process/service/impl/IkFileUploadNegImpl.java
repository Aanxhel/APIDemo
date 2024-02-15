/**
 * 
 */
package com.ikon.process.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ikon.process.json.error.DatosErroresJson;
import com.ikon.process.json.error.ErroresJson;
import com.ikon.process.json.response.IKResponsePathImagen;
import com.ikon.process.service.IkFileUploadNeg;
import com.ikon.process.util.Constantes;
import com.ikon.process.util.IkUtileria;

/**
 * @author icb_ipsra
 *
 */
@Service("ikFileUploadNeg")
public class IkFileUploadNegImpl implements IkFileUploadNeg {

	private static final Logger LOGG = LoggerFactory.getLogger(IkModuleNegImpl.class);
	
	@Value("${path.image}")
	private String pathServer;

	
	@Override
	public ResponseEntity<Object> uploadImagenServer(MultipartFile multipartFile) {
		ResponseEntity<Object> entity = null;
		DatosErroresJson errroJson = new DatosErroresJson();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		IKResponsePathImagen path_file = new IKResponsePathImagen();
		
		try {
			LOGG.info("[:::::. FileName {}  .:::::]", multipartFile.getOriginalFilename());
			LOGG.info("[:::::. path FileUpload {}  .:::::]", pathServer );
			
			
			if ( multipartFile.isEmpty() ) {
				errroJson = IkUtileria.generaSegmentoError(Constantes.CODIGO_400_12, Constantes.MENSAJE_400_12, "");
				entity = ResponseEntity.badRequest().body(errroJson);
			} else {
				
				if ( this.validaTipoArchivo(multipartFile) ) {
					path_file.setPathFilename(this.fileName(multipartFile));
					entity = ResponseEntity.ok().body(path_file);
				} else {
					errroJson = IkUtileria.generaSegmentoError(Constantes.CODIGO_400_12, Constantes.MENSAJE_400A_12, "");
					entity = ResponseEntity.badRequest().body(errroJson);
				}
				
			}
			
		} catch (IOException e) {
			errores.add(new ErroresJson(Constantes.CODIGO_400_12, "Error during upload ".concat(multipartFile.getOriginalFilename())));
			errroJson.setErrores(errores);
			entity = ResponseEntity.badRequest().body(errroJson);
		}
		
		return entity;
	}
	
	
	/***
	 * Guarda el archivo en el path
	 * @param multipartFile
	 * @return
	 * @throws IOException
	 */
	private String fileName (MultipartFile multipartFile ) throws IOException {
		StringBuilder fileName = new StringBuilder();
		long timeStampMillis = Instant.now().toEpochMilli();
		String pathComplet = null;
		
		String extension = multipartFile.getContentType().split("/")[1];
		 
		fileName.append("ik_");
		fileName.append(extension);
		fileName.append("_");
		fileName.append(timeStampMillis);
		fileName.append(".");
		fileName.append(extension);
		fileName.append("");
				
		String pathFolder = pathServer.concat(extension);
		File directorio = new File(pathFolder);
		if ( !directorio.exists() ) {
			directorio.mkdir();
			LOGG.info("[:::::::::::: {} ::::::::::::::]", "Folder created");
		} else {
			LOGG.info("[:::::::::::: {} ::::::::::::::]", "Folder exists");
		}
		
		
		byte[] byteFile = multipartFile.getBytes();
		
		pathComplet = pathFolder.concat("/").concat(fileName.toString()); 
		// pathServer.concat(fileName.toString());
		LOGG.info("[:::::. {}  .:::::]", pathComplet);
		Path path = Paths.get(pathComplet);
		Files.write(path, byteFile);
		
		pathComplet = pathComplet.replace("src/main/resources/static", "");
		
		return pathComplet;
	}
	
	/***
	 * Valida la extensión del tipo de archivos que vamos a aceptar
	 * @param extension
	 * @return
	 */
	private Boolean validaTipoArchivo (MultipartFile multipartFile) {
		Boolean extensionCorrecta = false;
		String extension = multipartFile.getContentType().split("/")[1];
		
		LOGG.info("[:::::. extension {}  .:::::]", extension );
		
		switch (extension) {
		case "avi":
		case "png":
		case "mp3":
		case "mp4":
		case "jpg":
		case "jpeg":
		case "pdf":
		case "gif":
			extensionCorrecta = true;
			break;
		default:
			extensionCorrecta = false;
			break;
		}
		return extensionCorrecta;
		
	} 

}
