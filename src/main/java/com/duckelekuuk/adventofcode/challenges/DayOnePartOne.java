package com.duckelekuuk.adventofcode.challenges;

import com.duckelekuuk.adventofcode.framework.AbstractDayChallenge;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DayOnePartOne extends AbstractDayChallenge {

    public static void main(String[] args) {
        Set<Integer> allNumbers = getInput("day-1-input.txt");

        Logger logger = Logger.getLogger(DayOnePartOne.class.getSimpleName());

        String result = "not found";
        for (int baseNumber : allNumbers) {
            int missingNumber = 2020 - baseNumber;
            if (allNumbers.contains(missingNumber)) {
                result = String.valueOf(missingNumber * baseNumber);
            }
        }

        logger.log(Level.INFO, "Result: {0}", result);
    }

    public static Set<Integer> getInput(String resourceName) {
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(resourceName);
        if (inputStream == null) {
            throw new IllegalArgumentException("Resource not found");
        }

        BufferedReader reader = new BufferedReader(new BufferedReader(new InputStreamReader(inputStream)));

        return reader.lines().map(Integer::parseInt).collect(Collectors.toSet());
    }
}
