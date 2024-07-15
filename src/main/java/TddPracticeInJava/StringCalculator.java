package TddPracticeInJava;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

    public int add(String numbers) {
        if (numbers.isEmpty()) return 0;

        String delimiter = ",";
        String numbersWithoutDelimiter = numbers;
        
        if (numbers.startsWith("//")) {
            delimiter = parseDelimiters(numbers);
            numbersWithoutDelimiter = numbers.substring(numbers.indexOf('\n') + 1);
        }

        String[] tokens = splitNumbers(numbersWithoutDelimiter, delimiter);
        return calculateSum(tokens);
    }

    private String parseDelimiters(String numbers) {
        int delimiterIndex = numbers.indexOf('\n');
        return numbers.substring(2, delimiterIndex);
    }

    private String[] splitNumbers(String numbers, String delimiter) {
        return numbers.split(delimiter + "|\n");
    }

    private int calculateSum(String[] tokens) {
        int sum = 0;
        List<Integer> negatives = new ArrayList<>();

        for (String token : tokens) {
            if (!token.isEmpty()) {
                int number = Integer.parseInt(token);
                if (number < 0) {
                    negatives.add(number);
                } else if (number <= 1000) {
                    sum += number;
                }
            }
        }

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("Negatives not allowed: " + negatives);
        }

        return sum;
    }
}
