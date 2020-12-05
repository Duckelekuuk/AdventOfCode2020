package com.duckelekuuk.adventofcode;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class DayTwoPartOne {

    private static final Pattern INPUT_PATTERN = Pattern.compile("([^>]+)-([^>]+) ([^>]+): ([^>]+)");

    public static void main(String[] args) {
        Stream<String> input = getInput("input.txt");

        Logger logger = Logger.getLogger(DayTwoPartOne.class.getSimpleName());

        String result = "not found";

        result = String.valueOf(input.filter(DayTwoPartOne::validatePassword).count());

        logger.log(Level.INFO, "Result: {0}", result);
    }

    private static boolean validatePassword(String input) {
        Matcher matcher = INPUT_PATTERN.matcher(input);

        if (!matcher.find()) return false;

        int min = Integer.parseInt(matcher.group(1));
        int max = Integer.parseInt(matcher.group(2)) ;
        String character = matcher.group(3) ;
        int count = StringUtils.countMatches(matcher.group(4), character);

        return count >= min && count <= max;
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
