package com.duckelekuuk.aoc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day10PartOne {

    public static void main(String[] args) {
        List<Integer> input = getInput("input.txt").map(Integer::parseInt).sorted().collect(Collectors.toList());

        Map<Integer, Integer> differenceMap = new HashMap<>();

        int lastJolt = 0;
        for (int jolt : input) {
            int difference = jolt - lastJolt;
            differenceMap.put(difference, differenceMap.getOrDefault(difference, 0) + 1);
            lastJolt = jolt;
        }

        differenceMap.put(3, differenceMap.getOrDefault(3, 0) + 1);

        System.out.println(differenceMap.get(1) * differenceMap.get(3));
        System.out.println("1 Difference: " + differenceMap.get(1));
        System.out.println("3 Difference: " + differenceMap.get(3));
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
