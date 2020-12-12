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

public class DayNinePartOne {

    public static void main(String[] args) {
        //Credits to ItsLars
        List<Long> inputList = getInput("input.txt").map(Long::parseLong).collect(Collectors.toList());

        Queue<Long> last25Numbers = new LinkedList<>(inputList.subList(0, 25));

        for (int i = 25; i < inputList.size(); i++) {
            long currentNumber = inputList.get(i);
            if (!hasMultiple(last25Numbers, currentNumber)) {
                System.out.println(currentNumber);
                break;
            }

            last25Numbers.remove();
            last25Numbers.add(currentNumber);
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
