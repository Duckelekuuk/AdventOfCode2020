package com.duckelekuuk.adventofcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DayOnePartTwo {

    public static void main(String[] args) {
        List<Integer> sortedNumbers = getInput("input.txt").stream().sorted().collect(Collectors.toList());

        Logger logger = Logger.getLogger(DayOnePartOne.class.getSimpleName());

        String result = "not found";


        outerLoop:
        for (int baseIndex = 0; baseIndex < sortedNumbers.size() - 2; baseIndex++) {
            int baseValue = sortedNumbers.get(baseIndex);
            int bottomIndex = baseIndex + 1;
            int topIndex = sortedNumbers.size() - 1;

            while(bottomIndex < topIndex) {
                int bottomValue = sortedNumbers.get(bottomIndex);
                int topValue = sortedNumbers.get(topIndex);

                int sum = baseValue + bottomValue + topValue;

                if (sum == 2020) {
                    result = String.valueOf(baseValue * bottomValue * topValue);
                    break outerLoop;
                } else if (sum > 2020) {
                    topIndex--;
                } else {
                    bottomIndex++;
                }
            }
        }

        logger.log(Level.INFO, "Result: {0}", result);
    }

    public static List<Integer> getInput(String resourceName) {
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(resourceName);
        if (inputStream == null) {
            throw new IllegalArgumentException("Resource not found");
        }

        BufferedReader reader = new BufferedReader(new BufferedReader(new InputStreamReader(inputStream)));

        return reader.lines().map(Integer::parseInt).collect(Collectors.toList());
    }
}
