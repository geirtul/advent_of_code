import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class Day10 {

    public static int findClosingBracket(char[] text, HashMap<Character, Character> brackets, int openPos) {
        int closePos = openPos;
        int counter = 1;
        while (counter > 0) {
            try {
                char c = text[++closePos];
                if (brackets.containsKey(c)) {
                    counter++;
                }
                else if (brackets.values().contains(c)) {
                    counter--;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                return openPos;
            }
        }
        // System.out.printf("Test %c, %c : %d, %d\n", text[openPos], text[closePos], openPos, closePos);
        if (!brackets.get(text[openPos]).equals(text[closePos])) {
            System.out.printf("Expected %c, found %c instead.\n", brackets.get(text[openPos]), text[closePos]);
            return closePos;
        }
        return openPos;
    }

    public static void solveOne(String filename) {
        ArrayList<char[]> lines = Reader.readCharArray(filename);
        HashMap<Character, Character> brackets = new HashMap<Character, Character>();
        brackets.put('(', ')');
        brackets.put('{', '}');
        brackets.put('[', ']');
        brackets.put('<', '>');

        HashMap<Character, Integer> scores = new HashMap<Character, Integer>();
        scores.put(')', 3);
        scores.put('}', 57);
        scores.put(']', 1197);
        scores.put('>', 25137);

        int syntaxErrorScore = 0;
        boolean stop = false;
        // int firstCorruptPos = Integer.MAX_VALUE;
        System.out.printf("%d\n", lines.size());
        for (char[] line : lines) {
            for (int i = 0; i < line.length; i++) {
                if (stop) {
                    break;
                }
                if (brackets.containsKey(line[i])) {
                    int counter = 1;
                    for (int j = i+1; j < line.length; j++) {
                        char c = line[j];
                        if (brackets.containsKey(c)) {
                            counter++;
                        } else if (brackets.values().contains(c)) {
                            counter--;
                        }

                        if (counter == 0 && !brackets.get(line[i]).equals(line[j])) {
                            // System.out.printf("Expected %c, found %c instead.\n", brackets.get(line[i]), line[j]);
                            syntaxErrorScore += scores.get(line[j]);
                            stop = true;
                            break;
                        } else if (counter == 0 && brackets.get(line[i]).equals(line[j])) {
                            break;
                        }
                    }
                    // System.out.printf("Test %c, %c : %d, %d\n", text[openPos], text[closePos], openPos, closePos);
                    // int corruptPos = findClosingBracket(line, brackets, i);
                    // if (corruptPos != i && corruptPos < firstCorruptPos) {
                    //     firstCorruptPos = corruptPos;
                    // }
                    // if (corruptPos != i) {
                    //     syntaxErrorScore += scores.get(line[corruptPos]);
                    // }
                }
            }
            stop = false;
            // if (firstCorruptPos < Integer.MAX_VALUE) {
            //     firstCorruptPos = Integer.MAX_VALUE;
            //     syntaxErrorScore += scores.get(line[firstCorruptPos]);
            // }
        }
        System.out.format("Part 1: %d\n", syntaxErrorScore);
    }

    public static void solveTwo(String filename) {
        int[][] heightmap = Reader.readIntegerArray2D(filename);
        
        // System.out.format("Part 2: %d\n", );
    }
    public static void main(String[] args) {
        solveOne(args[0]);
        // 171939 too high
        // solveTwo(args[0]);
    }
}