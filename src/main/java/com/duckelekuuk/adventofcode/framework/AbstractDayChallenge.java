package com.duckelekuuk.adventofcode.framework;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class AbstractDayChallenge {

    /**
     * Reads resource from resources folder.
     * File should be named: day-[day]-input.txt
     *
     * @return Stream of Strings from input file
     */
    public Stream<String> getInput() {
        ChallengeInfo declaredAnnotation = getClass().getDeclaredAnnotation(ChallengeInfo.class);
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("day-" + declaredAnnotation.day() + "-input.txt");

        if (inputStream == null) {
            throw new IllegalArgumentException("Resource not found");
        }

        BufferedReader reader = new BufferedReader(new BufferedReader(new InputStreamReader(inputStream)));

        return reader.lines();
    }
}