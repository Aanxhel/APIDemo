package com.cdc.testing.services.imp;

import java.text.Normalizer;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import com.cdc.Support.PrinterCore.getters.GetFormat;
import com.cdc.testing.beans.*;
import com.cdc.testing.services.TestingService;
import com.cdc.testing.util.Constantes;

@Service
public class TestingServiceImpl implements TestingService {

	private static final Logger logger = LogManager.getLogger(TestingServiceImpl.class);

	public Response obtenerRFC(Request request) throws Exception {
		logger.info(GetFormat.controllerWithBody("TestingServiceImpl", null));
		Response response = new Response();
		String fecha, nomApe = null;
		String dia, mes, anio;
		String ap1, ap2 = null, ap = null, am = null, n;
		Boolean esVocal = false;

		// Formato de Fecha para RFC
		dia = request.getFechaNacimiento().substring(8);
		mes = request.getFechaNacimiento().substring(5, 7);
		anio = request.getFechaNacimiento().substring(2, 4);
		fecha = anio + mes + dia;

		// Limpiar Nombre
		if (request.getPrimerNombre() != null && !request.getPrimerNombre().equals("")) {
			request.setPrimerNombre(request.getPrimerNombre().replace("Ñ", "X"));
			request.setPrimerNombre(Normalizer.normalize(request.getPrimerNombre(), Normalizer.Form.NFD)
					.replaceAll("\\p{InCombiningDiacriticalMarks}+", ""));

			for (int i = 0; i < Constantes.COMPUESTO.size(); i++) {
				request.setPrimerNombre(request.getPrimerNombre().replace(Constantes.COMPUESTO.get(i) + " ", ""));
			}

			for (int i = 0; i < Constantes.NOMBRES_COMUNES.size(); i++) {
				if (Constantes.NOMBRES_COMUNES.get(i).contains(request.getPrimerNombre())) {
					if (request.getSegundoNombre() != null) {
						for (int j = 0; j < Constantes.NOMBRES_COMUNES.size(); j++) {
							if (Constantes.NOMBRES_COMUNES.get(j).contains(request.getSegundoNombre())) {
								request.setSegundoNombre(
										request.getSegundoNombre().replace(request.getSegundoNombre(), ""));
								break;
							}
						}
						if (!request.getSegundoNombre().equals("")) {
							request.setPrimerNombre(request.getPrimerNombre().replace(request.getPrimerNombre(), ""));
						}
						break;
					}
				}
			}
		} else if (request.getSegundoNombre() != null && !request.getSegundoNombre().equals("")) {
			request.setSegundoNombre(request.getSegundoNombre().replace("Ñ", "X"));
			request.setSegundoNombre(Normalizer.normalize(request.getSegundoNombre(), Normalizer.Form.NFD)
					.replaceAll("\\p{InCombiningDiacriticalMarks}+", ""));

			for (int i = 0; i < Constantes.COMPUESTO.size(); i++) {
				request.setSegundoNombre(request.getSegundoNombre().replace(Constantes.COMPUESTO.get(i) + " ", ""));
			}

			request.setPrimerNombre(request.getSegundoNombre());
			request.setSegundoNombre(null);
		}

		if (request.getSegundoNombre() != null && !request.getSegundoNombre().equals("")) {
			request.setSegundoNombre(request.getSegundoNombre().replace("Ñ", "X"));
			request.setSegundoNombre(Normalizer.normalize(request.getSegundoNombre(), Normalizer.Form.NFD)
					.replaceAll("\\p{InCombiningDiacriticalMarks}+", ""));

			for (int i = 0; i < Constantes.COMPUESTO.size(); i++) {
				request.setSegundoNombre(request.getSegundoNombre().replace(Constantes.COMPUESTO.get(i) + " ", ""));
			}

			if (request.getPrimerNombre().equals(""))
				request.setPrimerNombre(request.getSegundoNombre());
		}

		// Limpiar Apellido
		if (request.getApellidoPaterno() != null && !request.getApellidoPaterno().equals("")) {
			request.setApellidoPaterno(request.getApellidoPaterno().replace("Ñ", "X"));
			request.setApellidoPaterno(Normalizer.normalize(request.getApellidoPaterno(), Normalizer.Form.NFD)
					.replaceAll("\\p{InCombiningDiacriticalMarks}+", ""));

			for (int i = 0; i < Constantes.COMPUESTO.size(); i++) {
				request.setApellidoPaterno(request.getApellidoPaterno().replace(Constantes.COMPUESTO.get(i) + " ", ""));
			}
			request.setApellidoPaterno(request.getApellidoPaterno().trim());
		} else if (request.getApellidoMaterno() != null && !request.getApellidoMaterno().equals("")) {
			request.setApellidoMaterno(request.getApellidoMaterno().replace("Ñ", "X"));
			request.setApellidoMaterno(Normalizer.normalize(request.getApellidoMaterno(), Normalizer.Form.NFD)
					.replaceAll("\\p{InCombiningDiacriticalMarks}+", ""));

			for (int i = 0; i < Constantes.COMPUESTO.size(); i++) {
				request.setApellidoMaterno(request.getApellidoMaterno().replace(Constantes.COMPUESTO.get(i) + " ", ""));
			}
			request.setApellidoPaterno(request.getApellidoMaterno().trim());
			request.setApellidoMaterno(null);
		}

		if (request.getApellidoMaterno() != null && !request.getApellidoMaterno().equals("")) {
			request.setApellidoMaterno(request.getApellidoMaterno().replace("Ñ", "X"));
			request.setApellidoMaterno(Normalizer.normalize(request.getApellidoMaterno(), Normalizer.Form.NFD)
					.replaceAll("\\p{InCombiningDiacriticalMarks}+", ""));

			for (int i = 0; i < Constantes.COMPUESTO.size(); i++) {
				request.setApellidoMaterno(request.getApellidoMaterno().replace(Constantes.COMPUESTO.get(i) + " ", ""));
			}
			request.setApellidoMaterno(request.getApellidoMaterno().trim());
		}

		// Inician reglas de RFC
		String pattern = "[aeiouAEIOU]";

		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(request.getApellidoPaterno().substring(1));
		while (m.find()) {
			esVocal = true;
		}

		// Apellido Paterno
		// Con Vocales
		if (esVocal) {
			// Menos de 2 digitos
			if (request.getApellidoPaterno().length() <= 2) {
				ap1 = request.getApellidoPaterno().substring(0, 1);
				ap = ap1.toUpperCase();
				// Mas de 2 digitos
			} else {
				ap1 = request.getApellidoPaterno().substring(0, 1);

				List<String> vocales = Constantes.VOCALES;

				for (int i = 1; i < request.getApellidoPaterno().length(); i++) {
					if (ap2 == null) {
						for (int j = 0; j < vocales.size(); j++) {
							if (vocales.get(j).contains(request.getApellidoPaterno().substring(i, i + 1))) {
								ap2 = request.getApellidoPaterno().substring(i, i + 1);
								break;
							}
						}
					} else {
						break;
					}
				}
				ap = ap1 + ap2;
				ap = ap.toUpperCase();
			}
			// Sin Vocales
		} else {
			// Menos de 2 digitos
			if (request.getApellidoPaterno().length() <= 2) {
				ap1 = request.getApellidoPaterno().substring(0, 1);
				ap = ap1.toUpperCase();
			}
			// Mas de 2 digitos
			else {
				ap1 = request.getApellidoPaterno().substring(0, 2);
				ap = ap1.toUpperCase();
			}
		}
		logger.info(GetFormat.controllerWithBody("AP::::", ap));

		// Apellido Materno
		if (request.getApellidoMaterno() != null && !request.getApellidoMaterno().equals("")) {
			am = request.getApellidoMaterno().substring(0, 1).toUpperCase();
		}
		logger.info(GetFormat.controllerWithBody("AM::::", am));

		// Nombre
		// Si AP solo cuenta con 1 digito se toman dos del nombre
		if (ap.length() > 1 && am != null) {
			n = request.getPrimerNombre().substring(0, 1).toUpperCase();
		} else {
			n = request.getPrimerNombre().substring(0, 2).toUpperCase();
		}
		logger.info(GetFormat.controllerWithBody("N::::", n));

		nomApe = ap + ((am != null) ? am : "") + n;

		// Listado de PALABRAS NO PERMITIDAS EN RFC
		List<String> palabrasIncovenientes = Constantes.PALABRAS_INCOVENIENTES;

		// REEMPLAZA PALABRAS NO PERMITIDAS
		for (int i = 0; i < palabrasIncovenientes.size(); i++) {
			if (palabrasIncovenientes.get(i).contains(nomApe)) {
				nomApe = nomApe.substring(0, 3) + "X";
				break;
			}
		}

		// Respuesta final
		response.setRfc(nomApe + fecha);
		logger.info(GetFormat.controllerWithBody("RFC::: ", response.getRfc()));

		return response;
	}
}
