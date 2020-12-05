package com.duckelekuuk.adventofcode;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayThreePartTwo {


    public static void main(String[] args) {
        List<String> input = getInput("input.txt").collect(Collectors.toList());

        Logger logger = Logger.getLogger(DayThreePartTwo.class.getSimpleName());

        String result = "not found";

        result = String.valueOf(
                        getTreeEncounters(1, 1, input) *
                        getTreeEncounters(3, 1, input) *
                        getTreeEncounters(5, 1, input) *
                        getTreeEncounters(7, 1, input) *
                        getTreeEncounters(1, 2, input)
        );

        logger.log(Level.INFO, "Result: {0}", result);
    }

    public static int getTreeEncounters(int right, int down, List<String> input) {
        int trees = 0;
        int x = 0;

        for (int y = 0; y < input.size(); y += down) {
            String line = input.get(y);

            if (line.charAt(x) == '#') {
                trees++;
            }

            x = (x + right) % line.length();
        }

        return trees;
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
