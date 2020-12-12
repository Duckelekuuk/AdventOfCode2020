package com.duckelekuuk.aoc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayNinePartTwo {

    public static void main(String[] args) {
        //Credits to ItsLars
        List<Long> inputList = getInput("input.txt").map(Long::parseLong).collect(Collectors.toList());

        long sumResultToFind = 88311122;

        int minIndex = 0;
        int maxIndex = 1;

        while (true) {
            long sum = inputList.subList(minIndex, maxIndex).stream().mapToLong(l -> l).sum();

            if (sum == sumResultToFind) {
                System.out.println(inputList.subList(minIndex, maxIndex).stream().max(Long::compareTo).get() + inputList.subList(minIndex, maxIndex).stream().min(Long::compareTo).get());
                break;
            }

            if (sum > sumResultToFind) {
                minIndex++;
            } else {
                maxIndex++;
            }
        }
    }

    public static boolean hasMultiple(Collection<Long> collection, long result) {
        for (long i : collection) {
            if (collection.contains(result - i)) {
                return true;
            }
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
