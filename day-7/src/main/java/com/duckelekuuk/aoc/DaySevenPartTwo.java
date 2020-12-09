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

public class DaySevenPartTwo {

    private static final String GOLDEN_KEY = "shiny gold";
    private static Map<String, Map<String, Integer>> bags = new HashMap<>();

    public static void main(String[] args) {

        for (String line : getInput("input.txt").collect(Collectors.toList())) {
            String[] bagFormat = line.substring(0, line.length() - 1).split(" bags contain ");

            Map<String, Integer> bagContents = new HashMap<>();

            for(String content : bagFormat[1].split(", ")) {
                try {
                    int amount = Integer.parseInt(content.charAt(0) + "");

                    String bagName = content.substring(2, content.length() - 4).strip();
                    bagContents.put(bagName, amount);
                } catch (NumberFormatException exception) {
                    //silent
                }

            }

            bags.put(bagFormat[0], bagContents);
        }



        System.out.println(countBags(GOLDEN_KEY));
    }

    public static int countBags(String bagKey) {
        if (bags.get(bagKey) == null) return 0;

        int count = 0;

        Map<String, Integer> content = bags.get(bagKey);

        for (Map.Entry<String, Integer> subBag : content.entrySet()) {
            count += subBag.getValue() + (subBag.getValue() * countBags(subBag.getKey()));
        }

        return count;
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
