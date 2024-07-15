package TddPracticeInJava;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public int add(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String delimiter = ",|\n";
        if (input.startsWith("//")) {
            Matcher matcher = Pattern.compile("//(\\[.*?\\])+\n").matcher(input);
            if (matcher.find()) {
                delimiter = parseDelimiters(matcher.group());
                input = input.substring(matcher.end());
            } else {
                delimiter = Character.toString(input.charAt(2));
                input = input.substring(4);
            }
        }

        String[] numbers = input.split(delimiter);
        return calculateSum(numbers);
    }

    private String parseDelimiters(String delimiterPart) {
        StringBuilder delimiters = new StringBuilder();
        Matcher matcher = Pattern.compile("\\[(.*?)\\]").matcher(delimiterPart);
        while (matcher.find()) {
            delimiters.append(Pattern.quote(matcher.group(1))).append("|");
        }
        return delimiters.substring(0, delimiters.length() - 1);
    }

    private int calculateSum(String[] numbers) {
        List<Integer> negatives = new ArrayList<>();
        int sum = 0;
        for (String number : numbers) {
            if (!number.isEmpty()) {
                int num = Integer.parseInt(number);
                if (num < 0) {
                    negatives.add(num);
                } else if (num <= 1000) {
                    sum += num;
                }
            }
        }
        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("Negatives not allowed: " + negatives);
        }
        return sum;
    }
}

