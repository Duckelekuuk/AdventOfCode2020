package com.duckelekuuk.aoc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayEightPartTwo {

    public static void main(String[] args) {


        List<String> instructions = getInput("input.txt").collect(Collectors.toList());

        for(int i = 0; i < instructions.size(); i++) {
            int result = terminatesSuccessFully(instructions, i);
            if (result != -1) {
                System.out.println(result);
                return;
            }

        }
    }


    public static int terminatesSuccessFully(List<String> instructions, int swapIndex) {

        int accumulator = 0;
        int currentInstruction = 0;

        Set<Integer> instructionsExecuted = new HashSet<>();

        while(!instructionsExecuted.contains(currentInstruction)) {
            if (currentInstruction >= instructions.size() -1) return accumulator;

            instructionsExecuted.add(currentInstruction);

            String instructionSet = instructions.get(currentInstruction);

            String instruction = instructionSet.substring(0, 3);

            if (currentInstruction == swapIndex) {
                 if (instruction.equalsIgnoreCase("jmp")) {
                     instruction = "nop";
                 } else if (instruction.equalsIgnoreCase("nop")) {
                     instruction = "jmp";
                 }
            }


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

        return -1;
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
