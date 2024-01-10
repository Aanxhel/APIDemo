package com.dev.Support.PrinterCore.getters;

import com.dev.Support.beans.ErrorDTO;
import com.dev.Support.exceptions.GenericException;
import com.google.gson.Gson;
import org.apache.commons.lang3.exception.ExceptionUtils;
import java.time.LocalDateTime;
import java.util.Map;

import static com.dev.Support.PrinterCore.getters.Filler.*;
import static com.dev.Support.PrinterCore.getters.GetColor.*;
import static com.dev.Support.PrinterCore.validations.Validator.*;

public class GetFormat {

    public static <T> String controllerWithBody(String RequestBodyName,T bodyObject){
        String responseLog = "\n";
        responseLog += (getArrowFormat(getStringTimeNL(4) + purple_letters("Entering Controller ") , cyan_letters(javaInClassName(dotInMethodName(Thread.currentThread().getStackTrace()[2].getClassName()))),60));
        responseLog += ("\n" + getArrowFormat(getStringTimeNL(4) + green_letters("On Method ") , cyan_letters(dotInMethodName(Thread.currentThread().getStackTrace()[2].getMethodName())),60));
        responseLog += ("\n" + paramFormat(RequestBodyName,bodyObject));
        return responseLog;
    }

    public static <T> String controller(){
        String responseLog = "\n";
        responseLog += (getArrowFormat(getStringTimeNL(4) + purple_letters("Entering Controller ") , cyan_letters(javaInClassName(dotInMethodName(Thread.currentThread().getStackTrace()[2].getClassName()))),60));
        responseLog += ("\n" + getArrowFormat(getStringTimeNL(4) + green_letters("On Method ") , cyan_letters(dotInMethodName(Thread.currentThread().getStackTrace()[2].getMethodName())),60));
        return responseLog;
    }

    public static String executeLog() {
        return newLine() + purple_letters("Executing:") + tab(2) + purple_letters(methodNameFormat(dotInMethodName(Thread.currentThread().getStackTrace()[2].getMethodName()),30) + tab(2) +  "on" + tab(2)) + cyan_letters(classNameFormat(Thread.currentThread().getStackTrace()[2].getClassName()));
    }

    public static String executeLogWithFlag(String flagName,boolean value) {
        return newLine() + purple_letters("Executing:") + tab(2) + purple_letters(methodNameFormat(dotInMethodName(Thread.currentThread().getStackTrace()[2].getMethodName()),30) + tab(2) +  "on" + tab(2)) + cyan_letters(classNameFormat(Thread.currentThread().getStackTrace()[2].getClassName())) + "   " + flagFormat(flagName,value);
    }

    public static <T> String executeLogWithParam(String paramName,T value) {
        return newLine() + purple_letters("Executing:") + tab(2) + purple_letters(methodNameFormat(dotInMethodName(Thread.currentThread().getStackTrace()[2].getMethodName()),30) + tab(2) +  "on" + tab(2)) + cyan_letters(classNameFormat(Thread.currentThread().getStackTrace()[2].getClassName())) + "   " + paramFormat(paramName,value);
    }

    public static <T> String executeLogWithParams(Map<String, T> params) {
        String response = newLine() + (purple_letters("Executing:") + tab(2) + purple_letters(methodNameFormat(dotInMethodName(Thread.currentThread().getStackTrace()[2].getMethodName()),30) + tab(2) +  "on" + tab(2)) + cyan_letters(classNameFormat(Thread.currentThread().getStackTrace()[2].getClassName())));
        try {
            for (Map.Entry<String,T> entry : params.entrySet())
                response += yellow_letters (newLine() + "[ " + entry.getKey() + " ]: "  + entry.getValue());
        } catch (Exception e) {
            response += red_letters("Error on printing the params");
        }
        return response;
    }

    public static String executeKafkaLog(String consumer,String topic,int partition){
        String responseLog = "\n";
        responseLog += orange_letters("[") + cyan_letters("[  Consumer ==> { ") + white_letters(consumer ) + cyan_letters(" } Topic ==> { ") + white_letters(topic) + cyan_letters(" } Partition ==> { ") + white_letters(String.valueOf(partition)) + cyan_letters(" }  ]") + orange_letters("]") ;
        return responseLog;
    }


    public static <T> String  restRequestWithBody(String restServiceUrl,String bodyName,T value) {

        return (orange_letters(newLine() + "Calling rest Service on:  ") + yellow_letters(restServiceUrl + newLine())
                + getBody(bodyName, value));

    }

    public static <T> String  restRequestWithJsonFormat(String restServiceUrl,T value) {

        return (orange_letters(newLine() + "Calling rest Service on:  ") + yellow_letters(restServiceUrl) + newLine()
                + jsonFormat(value.toString(), 2));

    }

    public static <T> String  restResponseWithBody(String restServiceUrl,String bodyName,T value) {

        return orange_letters(newLine() +"Response of rest Service on:") + yellow_letters(restServiceUrl + newLine()) + getResponse(bodyName, value);

    }

    public static <T> String  restResponseWithJsonFormat(String restServiceUrl,T value) {

        return orange_letters(newLine() +"Response of rest Service on:") + yellow_letters(restServiceUrl) + newLine() + jsonFormat(value.toString(), 2);

    }

    public static String  errorHandledException(Exception exception) {

        return red_letters("ERROR: ") + white_letters("Handled Exception [ ") + yellow_letters(classNameFormat(exception.getClass().getName())) + white_letters(" ] with message:") + newLine() + exception.getMessage();
    }

    public static String  errorHandledExceptionWithStrack(Exception exception) {

        String response = "";

        response +=  red_letters("ERROR: ") + white_letters("Handled Exception [ ") + yellow_letters(classNameFormat(exception.getClass().getName())) + white_letters(" ] with message:") + newLine() + exception.getMessage();
        response += "\n" + ExceptionUtils.getStackTrace(exception);

        return response;
    }

    public static String  errorHandledExceptionWithErrors(GenericException exception) {
        String response = red_letters("ERROR: ") + white_letters("Handled Exception [ ") + yellow_letters(classNameFormat(exception.getClass().getName())) + white_letters(" ] with message:") + newLine() + exception.getMessage();
        for(ErrorDTO error : exception.getErrores())
            response += "\n" + red_letters("Error: ") + error;
        return response;
    }

    public static String  errorHandledExceptionWithErrorsAndStrack(GenericException exception) {
        String response = red_letters("ERROR: ") + white_letters("Handled Exception [ ") + yellow_letters(classNameFormat(exception.getClass().getName())) + white_letters(" ] with message:") + newLine() + exception.getMessage();
        for(ErrorDTO error : exception.getErrores())
            response += "\n" + red_letters("Error: ") + error;
        response += "\n" + ExceptionUtils.getStackTrace(exception);
        return response;
    }

    public static String getterLog(String methodName , String className) {
        return newLine() + green_letters("Getter:") + tab(3) + purple_letters(methodNameFormat(methodName,30) + stringRepeater(" ",7) +  "on" + stringRepeater(" ",7)) + cyan_letters(className);
    }

    public static <T> String getterWithReturnLog(String methodName , String className , String parameterValueAndType , T value) {
        className = classNameFormat(className);
        return newLine() + green_letters("Getter:") + tab(3) + purple_letters(methodNameFormat(methodName,30) + stringRepeater(" ",7) +  "on" + stringRepeater(" ",7)) + cyan_letters(className) + "   " + returningValue(parameterValueAndType,value);
    }

    public static <T> String setterLog(String methodName,String className,String paramName,T value) {
        className = classNameFormat(className);
        return newLine() + green_letters("Setter:") + tab(3) + purple_letters(methodNameFormat(methodName,30) + stringRepeater(" ",7) +  "on" + stringRepeater(" ",7)) + cyan_letters(className)+ "   " + paramFormat(paramName,value);
    }


    //BASIC

    public static <T> String returningValue(String parameterValueAndType , T value) {
        return newLine() + yellow_letters("Returning: ( " + parameterValueAndType + " ): " + (value == null ? red_letters("Null Object") : value.toString()));
    }

    public static <T> String paramFormat(String paramName , T value) {
        return newLine() + yellow_letters("Param: ( " + paramName + " ):" + newLine() + tab() + green_letters((value != null ? value.toString() : red_letters("Null Object")) + newLine()));
    }

    public static String flagFormat(String flagName , boolean value) {
        return newLine() + yellow_letters("Flag: { " + flagName + " }: " + trueBooleanFormat(value));
    }

    public static <T> String getBody(String paramName , T value) {
        return newLine() + yellow_letters(tab() + "Body: ( " + paramName + " ):" +newLine() + tab(2) + nullObjectFormat(value) + newLine());
    }

    public static <T> String getResponse(String paramName , T value) {
        return newLine() + yellow_letters("Response: ( " + paramName + " ):" + newLine() + tab() + nullObjectFormat(value) + newLine());
    }

    public static <T> String getResponseAsJson(T response) {
        Gson gson = new Gson();
        return yellow_letters("Response: ") + green_letters(gson.toJson(response));
    }

    public static String trueBooleanFormat(Boolean value) {
        return value == true ? green_letters(String.valueOf(value)) : red_letters(String.valueOf(value));
    }

    public static <T> String nullObjectFormat(T value) {
        return value != null ? green_letters(value.toString()) : red_letters("Null Object");
    }

    private static String methodNameFormat(String methodName , int maxSize) {
        String validatedMethodString = methodLength(methodName);
        int size = (maxSize - validatedMethodString.length())/2;
        return "[" + stringRepeater(" ",size) + validatedMethodString + stringRepeater(" ",maxSize - (size + validatedMethodString.length())) + "]";
    }

    public static String classNameFormat(String className){

        String classNameFormated = className;

        classNameFormated = dotInClassName(classNameFormated);

        classNameFormated = javaInClassName(classNameFormated);

        return classNameFormated;
    }

    public  static String jsonFormat(String body,int numberOfTabs){

        if(body.contains("(") && body.indexOf("(") < body.indexOf(",")){
            if((body.contains("=") ) && (body.indexOf("=") < body.indexOf("(")))
                return (tab(numberOfTabs) + body.substring(0,body.indexOf("=")) + " {" + ("\n" + jsonFormat(body.substring(body.indexOf("(") + 1),numberOfTabs +1)));
            return (tab(numberOfTabs) + body.substring(0,body.indexOf("(")) + "{" + ("\n" + jsonFormat(body.substring(body.indexOf("(") + 1),numberOfTabs +1)));
        }


        if(body.contains(","))
            return (tab(numberOfTabs) + body.substring(0,body.indexOf(",")) + ("\n" + jsonFormat(body.substring(body.indexOf(",") + 1),numberOfTabs )));

        return body;
    }


    private static String getArrowFormat(String prefix,String object , int maxSize) {
        return getPrefixValidatedFormat(prefix,maxSize) + "==>" + stringRepeater(" ",5) + object;
    }

    private static String getPrefixValidatedFormat(String prefix,int maxSize){
        String validatedPrefix = validateSpacedString(prefix,maxSize);
        if(validatedPrefix.length() != maxSize){
            return validatedPrefix + stringRepeater(" ",maxSize - validatedPrefix.length());
        }
        return validatedPrefix;

    }

    private static String validateSpacedString(String methodName,int maxSize) {
        if(methodName.length() > maxSize) {
            return methodName.substring(0,maxSize);
        }
        return methodName;

    }

    public static String getStringTimeNL(int spaceBetween) {
        return "\n" + LocalDateTime.now() + stringRepeater(" ",spaceBetween);
    }


}

