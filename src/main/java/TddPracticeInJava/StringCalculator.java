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
            if (isNegative(token)) {
                negatives.add(Integer.parseInt(token));
            }
        }
        return negatives;
    }

    private boolean isNegative(String token) {
        return !token.isEmpty() && Integer.parseInt(token) < 0;
    }

    private int sumTokens(String[] tokens) {
        return sumFilteredNumbers(filterValidNumbers(tokens));
    }

    private List<Integer> filterValidNumbers(String[] tokens) {
        List<Integer> validNumbers = new ArrayList<>();
        for (String token : tokens) {
            if (isValidNumber(token)) {
                validNumbers.add(Integer.parseInt(token));
            }
        }
        return validNumbers;
    }

    private boolean isValidNumber(String token) {
        return !token.isEmpty() && Integer.parseInt(token) <= 1000;
    }

    private int sumFilteredNumbers(List<Integer> numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }
}
