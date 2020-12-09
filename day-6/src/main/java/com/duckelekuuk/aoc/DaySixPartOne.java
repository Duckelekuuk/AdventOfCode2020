package com.duckelekuuk.aoc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DaySixPartOne {

    public static void main(String[] args) {
        int count = 0;
        Set<String> characterGroup = new HashSet<>();

        for (String line : getInput("input.txt").collect(Collectors.toList())) {
            if (line.isEmpty()) {
                count += characterGroup.size();
                characterGroup.clear();
                continue;
            }

            characterGroup.addAll(Arrays.asList(line.split("")));
        }


        count += characterGroup.size();

        System.out.println("Count: " + count);
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
