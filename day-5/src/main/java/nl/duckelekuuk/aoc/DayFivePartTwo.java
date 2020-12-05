package nl.duckelekuuk.aoc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayFivePartTwo {

    public static void main(String[] args) {
        int highestSeat = 0;

        List<String> input = getInput("input.txt").collect(Collectors.toList());

        for (String line : input) {
            int row = computeBinarySpacing(line.substring(0, 7), 127, "F");
            int seat = computeBinarySpacing(line.substring(7, 10), 7, "L");

            int seatId = (row * 8) + seat;

            if (seatId > highestSeat) {
                highestSeat = seatId;
            }
        }

        System.out.println("Seat: " + highestSeat);
    }


    public static int computeBinarySpacing(String line, int max, String lowerChar) {
        int min = 0;

        for (String character : line.split("")) {
            int result = (max - min + 1) / 2;

            if (character.equalsIgnoreCase(lowerChar)) {
                max -= result;
            } else {
                min += result;
            }
        }

        return min;
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
