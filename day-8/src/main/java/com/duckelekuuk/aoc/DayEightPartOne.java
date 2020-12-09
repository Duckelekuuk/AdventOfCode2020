package com.duckelekuuk.aoc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayEightPartOne {

    public static void main(String[] args) {
        List<String> instructions = getInput("input.txt").collect(Collectors.toList());

        int accumulator = 0;
        int currentInstruction = 0;

        Set<Integer> instructionsExecuted = new HashSet<>();

        while(!instructionsExecuted.contains(currentInstruction)) {
            instructionsExecuted.add(currentInstruction);

            String instructionSet = instructions.get(currentInstruction);
            String instruction = instructionSet.substring(0, 3);


            int value = Integer.parseInt(instructionSet.substring(4));

            switch (instruction) {
                case "jmp":
                    currentInstruction += value;
                    break;
                case "acc":
                    accumulator += value;
                case "nop":
                default:
                    currentInstruction++;
            }
        }

        System.out.println(accumulator);
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
