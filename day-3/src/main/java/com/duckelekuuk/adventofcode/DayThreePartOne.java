package com.duckelekuuk.adventofcode;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayThreePartOne {


    public static void main(String[] args) {
        Stream<String> input = getInput("input.txt");

        Logger logger = Logger.getLogger(DayThreePartOne.class.getSimpleName());

        String result = "not found";

        int x = 0;
        int trees = 0;

        for (String line: input.collect(Collectors.toList())) {
            if (line.charAt(x) == '#') {
                trees++;
            }

            x = (x + 3) % line.length();
        }

        result = String.valueOf(trees);

        logger.log(Level.INFO, "Result: {0}", result);
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
