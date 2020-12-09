package com.duckelekuuk.aoc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DaySixPartTwo {

    public static void main(String[] args) {
        int count = 0;
        Set<String> characterGroup = new HashSet<>();
        boolean newGroup = true;

        for (String line : getInput("input.txt").collect(Collectors.toList())) {
            if (line.isEmpty()) {
                count += characterGroup.size();
                characterGroup.clear();
                newGroup = true;
                continue;
            }

            if (newGroup) {
                newGroup = false;
                characterGroup.addAll(Arrays.asList(line.split("")));
                continue;
            }

            characterGroup.removeIf(character -> !line.contains(character));
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
