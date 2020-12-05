package com.duckelekuuk.adventofcode;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayFourPartTwo {

    private static final Set<String> PRESET_FIELDS = Set.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");

    public static void main(String[] args) {
        Stream<String> input = getInput("input.txt");

        int valid = 0;
        Map<String, String> passport = new HashMap<>();

        for (String line : input.collect(Collectors.toList())) {
            if (line.isEmpty()) {

                if (validPassport(passport)) {
                    valid++;
                }

                passport.clear();
                continue;
            }

            for (String attribute : line.split(" ")) {
                String[] value = attribute.split(":");
                if (!PRESET_FIELDS.contains(value[0])) continue;

                passport.put(value[0], value[1]);
            }
        }

        //Check last field. File ends with empty line so isn't counted
        if (validPassport(passport)) {
            valid++;
        }

        System.out.println("Valid: " + valid);
    }

    public static boolean validPassport(Map<String, String> passport) {
        if (!passport.keySet().containsAll(PRESET_FIELDS)) return false;

        for (Map.Entry<String, String> entry : passport.entrySet()) {
            if (!PassportChecker.valueOf(entry.getKey().toUpperCase()).getCondition().test(entry.getValue())) {

                return false;
            }
        }

        return true;
    }

    public enum PassportChecker {

        BYR(input -> {
            int year = Integer.parseInt(input);
            return year >= 1920 && year <= 2002;
        }),
        IYR(input -> {
            int year = Integer.parseInt(input);
            return year >= 2010 && year <= 2020;
        }),
        EYR(input -> {
            int year = Integer.parseInt(input);
            return year >= 2020 && year <= 2030;
        }),
        HGT(input -> {
            if (!input.endsWith("cm") && !input.endsWith("in")) return false;

            int num = Integer.parseInt(input.substring(0, input.length() - 2));

            if (input.endsWith("cm")) {
                return num >= 150 && num <= 193;
            }
            if (input.endsWith("in")) {
                return num >= 59 && num <= 76;
            }

            return false;
        }),
        HCL(input -> {
            return input.matches("#[0-9a-f]{6}");
        }),
        ECL(input -> {
            return Set.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(input);
        }),
        PID(input -> {
            return input.matches("[0-9]{9}");
        });


        private final Predicate<String> condition;

        PassportChecker(Predicate<String> condition) {
            this.condition = condition;
        }

        public Predicate<String> getCondition() {
            return condition;
        }
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

//                System.out.println("\n------------------------------------");
//                        for (Map.Entry<String, String> debugEntry : passport.entrySet()) {
//        System.out.println(debugEntry.getKey() + ": " + debugEntry.getValue());
//        }
//        System.out.println("------------------------------------\n");