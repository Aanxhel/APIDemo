package com.dev.Support.PrinterCore.getters;

public class Filler {

    public static String newLine(){
        return "\n";
    }

    public static String newLine(int repeatNewLine){
        String newLines = "";
        for(int i  = 1 ; i <= repeatNewLine ; i++)
            newLines += newLine();
        return newLines;
    }

    public static String stringRepeater(String stringToRepeat,int numberOfTimesToRepeat) {
        return new String(new char[numberOfTimesToRepeat]).replace("\0", stringToRepeat);
    }

    public static String tab(){
        return stringRepeater(" ", 4);
    }

    public static String tab(int repeatTab){
        String space = "";
        for(int i  = 1 ; i <= repeatTab ; i++)
            space += tab();
        return space;
    }
}
