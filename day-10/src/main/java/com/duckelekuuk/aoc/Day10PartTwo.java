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

public class Day10PartTwo {

    public static void main(String[] args) {
        List<Integer> input = getInput("input.txt").map(Integer::parseInt).sorted().collect(Collectors.toList());

        final long[] sums = new long[input.get(input.size() - 1) + 1];
        sums[0] = 1;

        for (int i : input) {
            final long j1 = i >= 1 ? sums[i - 1] : 0;
            final long j2 = i >= 2 ? sums[i - 2] : 0;
            final long j3 = i >= 3 ? sums[i - 3] : 0;
            sums[i] = j1 + j2 + j3;
        }

        System.out.println(sums[sums.length - 1]);
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
