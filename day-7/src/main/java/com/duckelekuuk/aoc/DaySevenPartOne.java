package com.duckelekuuk.aoc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DaySevenPartOne {

    private static final String GOLDEN_KEY = "shiny gold";
    private static Map<String, List<String>> bags = new HashMap<>();

    public static void main(String[] args) {

        for (String line : getInput("input.txt").collect(Collectors.toList())) {
            String[] bagFormat = line.substring(0, line.length() - 1).split(" bags contain ");

            List<String> bagContents = new ArrayList<>();

            for(String content : bagFormat[1].split(", ")) {
                String bagName = content.substring(2, content.length() - 4).strip();
                bagContents.add(bagName);
            }

            bags.put(bagFormat[0], bagContents);
        }

        int count = 0;

        for (String bagKey : bags.keySet()) {
            if (hasGoldenBag(bagKey)) {
                count++;
            }
        }

        System.out.println(count);
    }

    public static boolean hasGoldenBag(String bagKey) {
        if (bags.get(bagKey) == null) return false;

        for (String subBag : bags.get(bagKey)) {
            if (subBag.equals(GOLDEN_KEY)) return true;

            if (hasGoldenBag(subBag)) return true;
        }

        return false;
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
