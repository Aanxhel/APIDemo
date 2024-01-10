package com.dev.Support.PrinterCore.validations;

public class Validator {

    public static String methodLength(String methodName) { return methodLength(methodName,30); }

    public static String dotInClassName(String className){ return stringAftersubstring(".",className); }

    public static String dotInMethodName(String className){ return stringAftersubstring(".",className); }

    public static String javaInClassName(String className){ return substrinInString(".java",className); }



    public static String methodLength(String methodName,int length) {
        if(methodName.length() > length)
            return methodName.substring(0,length);
        return methodName;
    }

    public static String substrinInString(String substring,String string){

        String result = string;

        if(!string.contains(substring)){
            result = string + substring;
        }

        return result;
    }

    public static String stringAftersubstring(String substring,String string){

        String result = string;

        while(result.contains(substring)){
            result = result.substring(result.indexOf(substring) + 1);
        }

        return result;
    }


}
