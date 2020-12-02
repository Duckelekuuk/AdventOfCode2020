package com.duckelekuuk.adventofcode;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class DayTwoPartOne {

    public static void main(String[] args) {
        Stream<String> input = getInput("input.txt");

        Logger logger = Logger.getLogger(DayTwoPartOne.class.getSimpleName());

        String result = "not found";

        result = String.valueOf(input.filter(DayTwoPartOne::validatePassword).count());

        logger.log(Level.INFO, "Result: {0}", result);
    }

    private static boolean validatePassword(String input) {
        String[] split = input.split(":");
        String pattern = split[0];
        String password = split[1];

        String[] patternSplit = pattern.split(" ");

        int min = Integer.parseInt(patternSplit[0].split("-")[0]);
        int max = Integer.parseInt(patternSplit[0].split("-")[1]);
        String character = patternSplit[1];
        int count = StringUtils.countMatches(password.trim(), character);

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
