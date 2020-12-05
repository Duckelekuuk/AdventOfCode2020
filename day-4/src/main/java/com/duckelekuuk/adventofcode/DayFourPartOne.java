package com.duckelekuuk.adventofcode;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayFourPartOne {

    private static final Set<String> PRESET_FIELDS = Set.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");

    public static void main(String[] args) {
        Stream<String> input = getInput("input.txt");

        int valid = 0;
        Set<String> fields = new HashSet<>();

        for (String line : input.collect(Collectors.toList())) {
            if (line.isEmpty()) {
                if (fields.containsAll(PRESET_FIELDS)) {
                    valid++;
                }

                fields.clear();
                continue;
            }

            for (String attribute : line.split(" ")) {
                fields.add(attribute.split(":")[0]);
            }
        }

        //Check last field. File ends with empty line so isn't counted
        if (fields.containsAll(PRESET_FIELDS)) {
            valid++;
        }

        System.out.println("Valid: " + valid);
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
