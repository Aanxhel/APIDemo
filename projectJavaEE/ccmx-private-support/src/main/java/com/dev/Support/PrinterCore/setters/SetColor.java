package com.dev.Support.PrinterCore.setters;

import static com.dev.Support.PrinterCore.getters.GetColor.*;

public class SetColor {

    public static void  black(String text) { System.out.println(black_letters(text)); }

    public static void  red(String text) {
        System.out.println(red_letters(text));
    }

    public static void  green(String text) {
        System.out.println(green_letters(text));
    }

    public static void  yellow(String text) {
        System.out.println(yellow_letters(text));
    }

    public static void  blue(String text) {
        System.out.println(blue_letters(text));
    }

    public static void  purple(String text) {
        System.out.println(purple_letters(text));
    }

    public static void  cyan(String text) {
        System.out.println(cyan_letters(text));
    }

    public static void  white(String text) {
        System.out.println(white_letters(text));
    }

    public static void  darkGray(String text) {
        System.out.println(darkGray_letters(text));
    }

    public static void  orange(String text) { System.out.println(orange_letters(text)); }

    public static void  custom(String text) { System.out.println(custom_letters(text)); }

}
