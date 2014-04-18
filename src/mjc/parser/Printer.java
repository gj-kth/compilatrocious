package mjc.parser;

import java.io.*;
import java.util.*;

public class Printer {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void printRed(String str) {
        System.out.println(ANSI_RED + str + ANSI_RESET);
    }
    public static void printGreen(String str) {
        System.out.println(ANSI_GREEN + str + ANSI_RESET);
    }

    public static void conditionalPrintln(String str, boolean condition){
        if(condition){
            System.out.println(str);
        }
    }

    public static void printThrowable(Throwable e){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        System.out.println("--------------------------------------------------------");
        System.out.println("error message: " + e.getMessage());
        System.out.println(sw.toString());
        System.out.println("--------------------------------------------------------");
    }
}
