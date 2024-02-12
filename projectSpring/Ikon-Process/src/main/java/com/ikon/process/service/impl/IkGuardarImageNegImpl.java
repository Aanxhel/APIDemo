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

//import javax.xml.bind.DatatypeConverter;
//import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ikon.process.json.error.DatosErroresJson;
import com.ikon.process.json.error.ErroresJson;
import com.ikon.process.json.request.IkRequestFileJson;
import com.ikon.process.json.response.IKResponsePathImagen;
import com.ikon.process.json.response.IkResponseGeneric;
import com.ikon.process.service.IkGuardarImageNeg;
import com.ikon.process.util.Constantes;
import com.ikon.process.util.IkConvierteJsonObjeto;
import com.ikon.process.util.IkUtileria;
import com.ikon.process.util.IkValidaRequest;

/**
 * @author icb_ipsra
 *
 */
@Service("ikGuardarImageNeg")
public class IkGuardarImageNegImpl  implements IkGuardarImageNeg {
	private static final Logger LOGG = LoggerFactory.getLogger(IkModuleNegImpl.class);
	
	@Value("${path.image}")
	private String pathServer;
	
	public ResponseEntity<Object> uploadImagenServer ( MultipartFile multipartFile) {
		ResponseEntity<Object> entity = null;
		DatosErroresJson errroJson = new DatosErroresJson();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		IKResponsePathImagen path_file = new IKResponsePathImagen();
		
		//String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath();
		
		try {
			LOGG.info("[:::::. {}  .:::::]", multipartFile.getOriginalFilename());
			if ( multipartFile.isEmpty() ) {
				errroJson = IkUtileria.generaSegmentoError(Constantes.CODIGO_400_12, Constantes.MENSAJE_400_12, "");
				entity = ResponseEntity.badRequest().body(errroJson);
			} else {
				path_file.setPathFilename(this.fileName(multipartFile));
				entity = ResponseEntity.ok().body(path_file);
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
		 
		fileName.append("ik");
		fileName.append(timeStampMillis);
		fileName.append(".");
		fileName.append(extension);
		fileName.append("");
				
		byte[] byteFile = multipartFile.getBytes();
		
		pathComplet = pathServer.concat(fileName.toString());
		LOGG.info("[:::::. {}  .:::::]", pathComplet);
		Path path = Paths.get(pathComplet);
		Files.write(path, byteFile);
		
		return pathComplet;
	}
	
	
	
	
	public ResponseEntity<Object> getImagenServer (String tipoArchivo, String requestDatoImagenJson) {
	
		ResponseEntity<Object> entity = null;
		DatosErroresJson errroJson = new DatosErroresJson();
		IkRequestFileJson request = new IkRequestFileJson();
		
		if (!IkValidaRequest.validaRequestDatoFile(requestDatoImagenJson)) {
			errroJson = IkUtileria.generaSegmentoError(Constantes.CODIGO_400_5, Constantes.MENSAJE_400_5, "");
			entity = ResponseEntity.badRequest().body(errroJson);
		} else {
			request = IkConvierteJsonObjeto.convierteDatoImagen(requestDatoImagenJson);
			errroJson = validaErrores(request);
			
			if (errroJson.getErrores() != null && !errroJson.getErrores().isEmpty()) {
				entity = ResponseEntity.badRequest().body(errroJson);
				LOGG.error("[::::::::: { }  :::::::::]", "It has errors in the request");
			} else {
				entity = this.getSentencesCards(requestDatoImagenJson, tipoArchivo);
			}
		}
		
		return entity;
	}
	
	
	/****
	 * Valida posibles errores
	 * @param request
	 * @return
	 */
	public DatosErroresJson validaErrores (IkRequestFileJson request) {
		DatosErroresJson datosErroresJson = new DatosErroresJson();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		
		
		if (request.getDatoImagen() == null ) {
			errores.add(new ErroresJson(Constantes.CODIGO_400_1, Constantes.MENSAJE_400_1_1.concat("datoImagen")));
		} else {
			
//			if (!isValidaBase64(request.getDatoImagen())) {
//				errores.add(new ErroresJson(Constantes.CODIGO_400_1, Constantes.MENSAJE_400_1_7));
//			}
		}
		
		return datosErroresJson;
	}
	
	/***
	 * 
	 * @param datoImagen
	 * @param tipoArchivo
	 * @return
	 */
	private ResponseEntity<Object>  getSentencesCards ( String datoImagen, String tipoArchivo ){
		ResponseEntity<Object> entity = null;
		String tipoExtenion = "";
		Boolean extensionCorrecta = false;
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		DatosErroresJson errroJson = new DatosErroresJson();
		IkResponseGeneric response = new IkResponseGeneric();
		IKResponsePathImagen imagen = new IKResponsePathImagen();
		
		tipoExtenion = tipoArchivo.toLowerCase();
		
		switch (tipoExtenion) {
		case "avi": case "pgn" : case "mp3" :  case "mp4" : case "jpg": case "jpeg":
			extensionCorrecta = true;
			break;
		default:
			extensionCorrecta = false;
			break;
		}
		
		try {
			if ( extensionCorrecta ) {
				imagen = this.generaNombreArchivo(datoImagen, tipoArchivo);
				entity = ResponseEntity.status(HttpStatus.OK).body(imagen);
				
			} else {
				response = IkUtileria.generaResponse(Constantes.I_RESPONSE_OK_EXIST, Constantes.ERROR_MESSAGE_SESSION_MENOS_3, "" );
				entity = ResponseEntity.badRequest().body(response);
			}
		} catch (IOException e) {
			errores.add(new ErroresJson(Constantes.ERROR_COD_500, Constantes.ERROR_COD_500.concat(e.getMessage())));
			errroJson.setErrores(errores);
			entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errroJson);
		}
		
		return entity;
	}
	
	/***
	 * Crea la carpeta y guarda el registro
	 * @param datoImagen
	 * @param tipoArchivo
	 * @return
	 */
	private IKResponsePathImagen generaNombreArchivo (String datoImagen, String tipoArchivo) throws IOException {
		String completePath = null;
		String extension = "";
		long timeStampMillis = Instant.now().toEpochMilli();
		IKResponsePathImagen imagen = new IKResponsePathImagen();
		StringBuilder fileName = new StringBuilder();
		File directorio = new File(this.pathServer.concat(tipoArchivo));
		
		if ( !directorio.exists() ) {
			directorio.mkdir();
			LOGG.info("[:::::::::::: {} ::::::::::::::]", "Folder created");
		} else {
			LOGG.info("[:::::::::::: {} ::::::::::::::]", "Folder exists");
		}
		
		String[] strings = datoImagen.split(",");
		
		switch (strings[0]) {// check image's extension
		
		case "data:image/jpeg;base64":
			extension = "jpeg";
			break;
		case "data:image/png;base64":
			extension = "png";
			break;
		case "data:image/avi;base64":
			extension = "avi";
			break;
		case "data:image/mp3;base64":
			extension = "mp3";
			break;	
		default:// should write cases for more images types
			extension = "jpg";
			break;
		}
//		byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
//		byte[] data = Base64.decodeBase64(strings[1]);
		
		fileName.append(pathServer);
		fileName.append(tipoArchivo);
		fileName.append("/");
		fileName.append(timeStampMillis);
		fileName.append(".");
		fileName.append(extension);
		 
		completePath = fileName.toString();
		LOGG.info("[:::::::::::: Complete path {} ::::::::::::::]", completePath);
		
//		File file = new File(completePath);
		
//		try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
//			outputStream.write(data);
//		} 
        
		imagen.setPathFilename(completePath);
		
		return imagen;
	}
	
	
//	/***
//	 * Valida si la cadena cumple con las caracteristicas de base64
//	 * @param datoImagen
//	 * @return
//	 */
//	private boolean isValidaBase64 ( String datoImagen ) {
//		String[] arrayBase64 = datoImagen.split(",");
//		String strBase64 = arrayBase64[1];
//		boolean isBase64 = Base64.isBase64(strBase64.getBytes());
//		LOGG.info("[:::::::::::: String Base64 {} ::::::::::::::]", isBase64);
//		return isBase64;
//		
//	} 
	
	
}
