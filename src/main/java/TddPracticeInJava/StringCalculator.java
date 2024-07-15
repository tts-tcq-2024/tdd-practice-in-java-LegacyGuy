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
        validateNoNegatives(tokens);

        return sumTokens(tokens);
    }

    private String parseDelimiters(String numbers) {
        int delimiterIndex = numbers.indexOf('\n');
        return numbers.substring(2, delimiterIndex);
    }

    private String[] splitNumbers(String numbers, String delimiter) {
        return numbers.split(delimiter + "|\n");
    }

    private void validateNoNegatives(String[] tokens) {
        List<Integer> negatives = findNegatives(tokens);
        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("Negatives not allowed: " + negatives);
        }
    }

    private List<Integer> findNegatives(String[] tokens) {
        List<Integer> negatives = new ArrayList<>();
        for (String token : tokens) {
            if (!token.isEmpty()) {
                int number = Integer.parseInt(token);
                if (number < 0) {
                    negatives.add(number);
                }
            }
        }
        return negatives;
    }

    private int sumTokens(String[] tokens) {
        return sumFilteredNumbers(filterValidNumbers(tokens));
    }

    private List<Integer> filterValidNumbers(String[] tokens) {
        List<Integer> validNumbers = new ArrayList<>();
        for (String token : tokens) {
            if (!token.isEmpty()) {
                int number = Integer.parseInt(token);
                if (number <= 1000) {
                    validNumbers.add(number);
                }
            }
        }
        return validNumbers;
    }

    private int sumFilteredNumbers(List<Integer> numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }
}
