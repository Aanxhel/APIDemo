/**
 * 
 */
package com.ikon.process.util;

import org.springframework.stereotype.Component;

/**
 * @author IsraelC
 *
 */
@Component
public class Constantes {
	
	public static final String CODIGO_500 = "500";
	public static final String MENSAJE_500 =  "An internal problem has been detected when consulting the service. Please reach Administrator.";
	public static final String REGEX_NUMEROS = "[0-9]*";
	public static final String REGEX_NUM_VALIDOS = "[1-2]*";
	public static final String REGEX_NUM_DECIMAL ="[0-9]{1,4}[.]?[0-9]{1,2}";
	public static final String REGEX_EMAIL = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	public static final int VALOR_INT_CERO = 0;
	public static final int VALOR_INT_UNO = 1;
	public static final int VALOR_INT_MENOS_UNO = -1;
	public static final int VALOR_INT_MENOS_DOS = -2;
	public static final int VALOR_INT_MENOS_TRES = -3;
	public static final int VALOR_INT_MENOS_DIEZ = -10;
	public static final int VALOR_INT_DOS = 2;
	public static final int VALOR_INT_TRES = 3;
	
	
	public static final String VERSION_CATALOGO = "0.0.1";
	
	
	
	public static final String CODIGO_200 = "200";
	public static final String MENSAJE_200 = "Autorizar Reporte";
	
	
	public static final String SUCCESS_CODE = "000";
	public static final String SUCCES_RECORD = "Successfully registered";
	public static final String SUCCES_RECORD_UPDATE = "Successfully modified";
	
	
	public static final String CODE_EXISTS = "001";
	public static final String RECORD_EXISTS = "Record exists already.";
	
	
	
	
	public static final String CODIGO_400_1 = "400.1";
	public static final String MENSAJE_400_1 = "The attribute is required or does not exist, Please enter information required: ";
	public static final String MENSAJE_400_1_1 = "The attribute is required: ";
	public static final String MENSAJE_400_1_5 = "Description already exists: ";
	public static final String MENSAJE_400_1_6 = "Parameters are required and / or different from ZERO: ";
	public static final String MENSAJE_400_1_7 = "This attribute does not comply with base64 format";
	
	
	
	public static final String CODIGO_400_10 = "400.10";
	public static final String MENSAJE_400_10 = "NO AUTENTICADO";
	
	public static final String CODIGO_400_4 = "400.4";
	public static final String MENSAJE_400_4 = "Does not meet the length according to the attribute, Please review the right spelling : ";
	
	public static final String CODIGO_400_5 = "400.5";
	public static final String MENSAJE_400_5 = "Invalid request";
//	
	public static final String CODIGO_400_11 = "400.11";
	public static final String MENSAJE_400_11 = "Unrecognized movement type,  Please enter information required ";
	
 	public static final String MENSAJE_400_11_1 = "Required: ";
	public static final String MENSAJE_400_11_2 = "Description is required: ";
	
	public static final String CODIGO_400_12 = "400.12";
	public static final String MENSAJE_400_12 = "No content, Plase select a file to upload";
	public static final String MENSAJE_400A_12 = "Type of file incorrect";
	
	
	public static final String TOKEN_BEARER = "Bearer ";	
	public static final String TOKEN_AUTHORIZATION = "Authorization";
	

	public static final String ERROR_COD_500 = "500";
	
	
	public static final int LONGITUD_DESCRIPCION = 250;
	public static final int LONGITUD_DATOS_CLIENTE = 50;
	
	
	public static final int MAX_LENGTH_INT_8 = 8;
	public static final int MAX_LENGTH_INT_50 = 50;
	public static final int MAX_LENGTH_INT_100 = 100;
	public static final int MAX_LENGTH_INT_150 = 150;
	public static final int MAX_LENGTH_INT_200 = 200;
	public static final int MAX_LENGTH_INT_1000 = 1000;
	public static final int MAX_LENGTH_INT_2000 = 2000;
	
	
	
	
	public static final String MAX_LENGTH_STR_100 = " to 100 Characters";
	public static final String MAX_LENGTH_STR_150 = " to 150 Characters";
	public static final String MAX_LENGTH_STR_200 = " to 200 Characters";
	public static final String MAX_LENGTH_STR_1000 = " to 1000 Characters";
	public static final String MAX_LENGTH_STR_2000 = " to 2000 Characters";
	
	public static final String TIPO_MOV_ALTA = "ADD";
	public static final String TIPO_MOV_MODIFICA = "UPDATE";
	public static final String TIPO_MOV_ELIMINA = "DELETE";
	public static final String TIPO_MOV_LISTA = "LIST";
	
	
	public static final String S_RESPONSE_INSERT_OK = "Success! Registered Successfully ";
	public static final String S_RESPONSE_UPDATE_OK = "Success! Updated Successfully ";
	public static final String S_RESPONSE_DELETE_OK = "Success! Deleted Successfully ";
	
	public static final int I_RESPONSE_OK = 0;
	
	public static final String S_RESPONSE_EXIST = "Warning! The record already exists;  ";
	public static final int I_RESPONSE_OK_EXIST = 1;
	
	public static final String S_RESPONSE_NO_EXIST = "Warning! The record doesn't exists. Please create.  ";
	public static final int I_RESPONSE_NO_EXIST = 1;
	public static final int I_RESPONSE_ERROR_ASSOCIATE = 1;
	
	public static final String ERROR_MESSAGE_ERROR_ASSOCIATE = "Warning! It is not possible to delete it, is associated with a ";
	
	public static final String ERROR_MESSAGE_SESSION_MENOS_1 = "Warning! The record doesn't exists. Please create ";
	public static final String ERROR_MESSAGE_SESSION_MENOS_2 = "Warning! The number of sentences is different from the current ";
	public static final String ERROR_MESSAGE_SESSION_MENOS_3 = "Warning! Name of the record already exists; please use a different one ";
	public static final String ERROR_MESSAGE_SESSION_MENOS_4 = "Warning! There are sessions that don't exist ";
	public static final String ERROR_MESSAGE_SESSION_MENOS_5 = "Warning! There are cards that don't exist ";
	public static final String ERROR_MESSAGE_SESSION_MENOS_6 = "Warning! There are sentences that don't exist ";
	
	public static final String ERROR_MESSAGE_MODULE_SESSION_NO_ = "Warning! This session doesn't exist. Please create. ";
	public static final String ERROR_MESSAGE_MODULE_SESSION_SENTENCES_NO_ = "Warning! No sentences or cards have been registered yet, ; please to create accordingly ";
	
	
	
	
}
