package com.duckelekuuk.adventofcode;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class DayTwoPartTwo {

    public static void main(String[] args) {
        Stream<String> input = getInput("input.txt");

        Logger logger = Logger.getLogger(DayTwoPartTwo.class.getSimpleName());

        String result = "not found";

        result = String.valueOf(input.filter(DayTwoPartTwo::validatePassword).count());

        logger.log(Level.INFO, "Result: {0}", result);
    }

    private static boolean validatePassword(String input) {
        String[] split = input.split(": ");
        String pattern = split[0];
        String password = split[1];

        String[] patternSplit = pattern.split(" ");

        int firstIndex = Integer.parseInt(patternSplit[0].split("-")[0]) - 1;
        int secondIndex = Integer.parseInt(patternSplit[0].split("-")[1]) - 1;
        char character = patternSplit[1].charAt(0);

        boolean result = password.charAt(firstIndex) == character ^ password.charAt(secondIndex) == character;

        System.out.println(character + " - " + password.charAt(firstIndex) + ":" + password.charAt(secondIndex) + " = " + result);
        return result;
    }

    public static Stream<String> getInput(String resourceName) {
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(resourceName);
        if (inputStream == null) {
            throw new IllegalArgumentException("Resource not found");
        }

        BufferedReader reader = new BufferedReader(new BufferedReader(new InputStreamReader(inputStream)));

        return reader.lines();
    }
}
