package com.dev.Support.PrinterCore.enums;

public enum Colors {

    RESET_LETTER("\u001B[0m"),
    BLACK_LETTER("\u001B[30m"),
    RED_LETTER("\u001B[31m"),
    GREEN_LETTER("\u001B[32m"),
    YELLOW_LETTER("\u001B[33m"),
    BLUE_LETTER("\u001B[34m"),
    PURPLE_LETTER("\u001B[35m"),
    CYAN_LETTER("\u001B[36m"),
    WHITE_LETTER("\u001B[37m"),
    ORANGE_LETTER("\u001B[38;5;208m"),
    DARK_GRAY_LETTER("\u001B[90m"),
    CUSTOM_LETTER("\u001B[38;5;250m"),

    RESET_BACK("\u001B[40m"),
    BLACK_BACK("\u001B[40m"),
    RED_BACK("\u001B[41m"),
    GREEN_BACK("\u001B[42m"),
    YELLOW_BACK("\u001B[43m"),
    BLUE_BACK("\u001B[44m"),
    PURPLE_BACK("\u001B[45m"),
    CYAN_BACK("\u001B[46m"),
    WHITE_BACK("\u001B[47m"),
    ORANGE_BACK("\u001B[48;5;208m"),
    DARK_GRAY_BACK("\u001B[100m"),
    CUSTOM_BACK("\u001B[48;5;250m");


    public final String label;

    private Colors(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
