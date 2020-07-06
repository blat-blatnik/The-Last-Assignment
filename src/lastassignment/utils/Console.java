package lastassignment.utils;

import java.util.Scanner;

/**
 * @author Boris
 * @author Jana
 * @version 5.0
 *
 * Helper class that simplifies the scanner use throughout this programm.
 * It can read inputs as strings and integers and print output in different ways.
 */
public class Console {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Reads a string from the input scanner.
     *
     * @return Returns player input if available, otherwise empty string.
     */
    public static String readString() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine().trim();
        }
        return "";
    }

    /**
     * Reads an integer from the scanner.
     *
     * @return Integer or -1 if input was not an integer.
     */
    public static int readInt() {
        String input = readString();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Reads a string from the input scanner as all uppercase.
     * So that we can treat player inputs "x" and "X" as the same input.
     *
     * @return String in uppercase.
     */
    public static String readInput() {
        return readString().toUpperCase();
    }

    /**
     * Synonym/wrapper for System.out.printf
     * Since this is shorter and more consistent looking with other output.
     */
    public static void print(String format, Object... args) {
        System.out.printf(format, args);
    }

    /**
     * Prints line like System.out.writeln but with possible string formatting.
     *
     * @param format String that will be printed.
     * @param args Optional formatting parameters.
     */
    public static void printLine(String format, Object... args) {
        String output = String.format(format, args);
        System.out.println(output);
    }

    /**
     * Prints a possibly formatted line, followed by "...".
     * The player must then input something before this function returns.
     *
     * @param format String that is printed.
     * @param args Optional formatting parameters.
     */
    public static void printWithPause(String format, Object... args) {
        String output = String.format(format, args) + " ...";
        System.out.print(output);
        readString();
    }

    /**
     * Prints out a list of conversation lines, each followed by "...",
     * and the player must input anything after each of the lines.
     *
     * @param lines Optional variable number of lines to print.
     */
    public static void printConversationWithPauses(String ...lines) {
        for (String line: lines)
            printWithPause(line);
    }
}