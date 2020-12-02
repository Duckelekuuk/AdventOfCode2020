package com.duckelekuuk.adventofcode;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class DayTwoPartTwo {

    private static final Pattern INPUT_PATTERN = Pattern.compile("([^>]+)-([^>]+) ([^>]+): ([^>]+)");

    public static void main(String[] args) {
        Stream<String> input = getInput("input.txt");

        Logger logger = Logger.getLogger(DayTwoPartTwo.class.getSimpleName());

        String result = "not found";

        result = String.valueOf(input.filter(DayTwoPartTwo::validatePassword).count());

        logger.log(Level.INFO, "Result: {0}", result);
    }

    private static boolean validatePassword(String input) {
        Matcher matcher = INPUT_PATTERN.matcher(input);

        if (!matcher.find()) return false;

        final int firstIndex = Integer.parseInt(matcher.group(1)) - 1;
        final int secondIndex = Integer.parseInt(matcher.group(2)) - 1;
        final char character = matcher.group(3).charAt(0);
        final String password = matcher.group(4);

        return password.charAt(firstIndex) == character ^ password.charAt(secondIndex) == character;
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
