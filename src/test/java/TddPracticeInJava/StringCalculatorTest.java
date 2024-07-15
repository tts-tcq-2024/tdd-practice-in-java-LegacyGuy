package TddPracticeInJava;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class StringCalculatorTest {

    @Test
    public void ExpectZeroForEmptyInput() {
        int expectedResult = 0;
        String input = "";
        StringCalculator objUnderTest = new StringCalculator();
        int result = objUnderTest.add(input);
        assertEquals(expectedResult, result);
    }

    @Test
    public void ExpectZeroForSingleZero() {
        int expectedResult = 0;
        String input = "0";
        StringCalculator objUnderTest = new StringCalculator();
        int result = objUnderTest.add(input);
        assertEquals(expectedResult, result);
    }

    @Test
    public void ExpectSumForTwoNumbers() {
        int expectedResult = 3;
        String input = "1,2";
        StringCalculator objUnderTest = new StringCalculator();
        int result = objUnderTest.add(input);
        assertEquals(expectedResult, result);
    }

    @Test
    public void ExpectSumWithNewlineDelimiter() {
        int expectedResult = 6;
        String input = "1\n2,3";
        StringCalculator objUnderTest = new StringCalculator();
        int result = objUnderTest.add(input);
        assertEquals(expectedResult, result);
    }

    @Test
    public void IgnoreNumbersGreaterThan1000() {
        int expectedResult = 1;
        String input = "1,1001";
        StringCalculator objUnderTest = new StringCalculator();
        int result = objUnderTest.add(input);
        assertEquals(expectedResult, result);
    }

    @Test
    public void ExpectSumWithCustomDelimiter() {
        int expectedResult = 3;
        String input = "//;\n1;2";
        StringCalculator objUnderTest = new StringCalculator();
        int result = objUnderTest.add(input);
        assertEquals(expectedResult, result);
    }

    @Test
    public void ExpectSumWithMultipleCustomDelimiters() {
        int expectedResult = 6;
        String input = "//[***][%%%]\n1***2%%%3";
        StringCalculator objUnderTest = new StringCalculator();
        int result = objUnderTest.add(input);
        assertEquals(expectedResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ThrowExceptionForNegativeNumbers() {
        String input = "1,-2,3";
        StringCalculator objUnderTest = new StringCalculator();
        objUnderTest.add(input);
    }

    @Test
    public void ThrowExceptionForMultipleNegativeNumbers() {
        String input = "-1,-2,3";
        StringCalculator objUnderTest = new StringCalculator();
        try {
            objUnderTest.add(input);
        } catch (IllegalArgumentException e) {
            assertEquals("Negatives not allowed: [-1, -2]", e.getMessage());
        }
    }
}
