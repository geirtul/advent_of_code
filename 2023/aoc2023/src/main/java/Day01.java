import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day01 implements Day<ArrayList<String>> {

    // Helper method to combine digits in a string
    private int combineDigits(String line) {
        StringBuilder digits = new StringBuilder();

        // Regular expression to match digits in the line
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(line);

        // Find and append digits to the 'digits' StringBuilder
        while (matcher.find()) {
            String match = matcher.group();
            appendDigit(digits, match);
        }

        // If 'digits' has only one digit, append a copy to make the length 2
        if (digits.length() == 1) {
            digits.append(digits.charAt(0));
        }

        // Combine the digits and convert to an integer
        return Integer.parseInt(digits.toString());
    }

// Helper method to combine digits (including words representing digits) in a string
    private int combineDigitsIncludingWords(String line) {
        StringBuilder digits = new StringBuilder();

        // Regular expression to match words (including digit words) in the line
        Pattern pattern = Pattern.compile("(one|two|three|four|five|six|seven|eight|nine|\\d)");

        for (int i = 0; i < line.length(); i++) {
            String remainder = line.substring(i);
            Matcher matcher = pattern.matcher(remainder);

            // Find and append digits and words representing digits to the 'digits' StringBuilder
            while (matcher.find()) {
                String match = matcher.group();
                if (Character.isDigit(match.charAt(0))) {
                    // If the match starts with a digit, append it to digits
                    appendDigit(digits, match);
                } else if (isDigitWord(match)) {
                    // If the match is a word representing a digit, append its value to digits
                    appendDigit(digits, String.valueOf(getDigitValue(match)));
                }
            }
        }

        // If 'digits' has only one digit, append a copy to make the length 2
        if (digits.length() == 1) {
            digits.append(digits.charAt(0));
        }

        // Combine the digits and convert to an integer
        return Integer.parseInt(digits.toString());
    }

    // Helper method to append a digit to the 'digits' StringBuilder
    private void appendDigit(StringBuilder digits, String digit) {
        if (digits.length() < 2) {
            digits.append(digit);
        } else {
            // Replace the last digit in 'digits' with the most recently encountered digit
            digits.setCharAt(1, digit.charAt(0));
        }
    }

    // Helper method to check if a word represents a digit
    private boolean isDigitWord(String word) {
        return switch (word.toLowerCase()) {
            case "one" -> true;
            case "two" -> true;
            case "three" -> true;
            case "four" -> true;
            case "five" -> true;
            case "six" -> true;
            case "seven" -> true;
            case "eight" -> true;
            case "nine" -> true;
            // Add more words as needed
            default -> false;
        };
    }

    // Helper method to get the numeric value of a word representing a digit
    private int getDigitValue(String word) {
        return switch (word.toLowerCase()) {
            case "one" -> 1;
            case "two" -> 2;
            case "three" -> 3;
            case "four" -> 4;
            case "five" -> 5;
            case "six" -> 6;
            case "seven" -> 7;
            case "eight" -> 8;
            case "nine" -> 9;
            // Add more words as needed
            default -> 0;
        };
    }

    @Override
    public void solveOne(ArrayList<String> fileLines) {
        int sum = 0;

        for (String line : fileLines) {
            // Find and combine all occurrences of digits in the line
            int combinedDigits = combineDigits(line);

            //System.out.println("Combined digits for line '" + line + "': " + combinedDigits);
            // Add the resulting integer to the sum
            sum += combinedDigits;
        }

        // Print the sum of the integers
        System.out.println("Sum of integers: " + sum);
    }
    @Override
    public void solveTwo(ArrayList<String> fileLines) {
        int sum = 0;

        for (String line : fileLines) {
            // Find and combine all occurrences of digits (including words representing digits) in the line
            int combinedDigits = combineDigitsIncludingWords(line);

            // Print each integer returned by combineDigitsIncludingWords
            System.out.println("Combined digits for line '" + line + "': " + combinedDigits);
            if (combinedDigits < 11 || combinedDigits > 99) {
                System.out.println(combinedDigits);
            }

            // Add the resulting integer to the sum
            sum += combinedDigits;
        }

        // Print the sum of the integers
        System.out.println("Sum of integers for solveTwo: " + sum);
    }

    @Override
    public ArrayList<String> testInput() {
        return null;
    }

    // Method to generate the first test input
    private static ArrayList<String> testInputOne() {
        ArrayList<String> testInput = new ArrayList<>();
        testInput.add("1abc2");
        testInput.add("pqr3stu8vwx");
        testInput.add("a1b2c3d4e5f");
        testInput.add("treb7uchet");
        return testInput;
    }

    // Method to generate the second test input
    private static ArrayList<String> testInputTwo() {
        ArrayList<String> testInput = new ArrayList<>();
        testInput.add("two1nine");
        testInput.add("twoneight");
        testInput.add("twone");
        testInput.add("eightwothree");
        testInput.add("abcone2threexyz");
        testInput.add("xtwone3four");
        testInput.add("4nineeightseven2");
        testInput.add("zoneight234");
        testInput.add("7pqrstsixteen");
        return testInput;
    }

    public static void main(String[] args) {
        // Test input for solveOne
        ArrayList<String> testInputOne = testInputOne();

        // Create an instance of DayOne
        Day01 day01 = new Day01();

        // Call solveOne with the test input for solveOne
        day01.solveOne(testInputOne);

        // Test input for solveTwo
        ArrayList<String> testInputTwo = testInputTwo();

        // Call solveTwo with the test input for solveTwo
        day01.solveTwo(testInputTwo);

        // Continue with InputReader
        ArrayList<String> fileLines = InputReader.lineByLine("input/day01");

        // Call solveOne and solveTwo with fileLines
        day01.solveOne(fileLines);
        day01.solveTwo(fileLines);

        // Continue with the rest of the program...
    }


}