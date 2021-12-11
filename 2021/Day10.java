import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
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

    public static void solve(String filename) {
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

        HashMap<Character, Integer> corruptCount = new HashMap<Character, Integer>();
        corruptCount.put(')', 0);
        corruptCount.put('}', 0);
        corruptCount.put(']', 0);
        corruptCount.put('>', 0);

        int syntaxErrorScore = 0;
        ArrayList<Integer> completeScores = new ArrayList<Integer>();
        for (int i = 0; i < lines.size(); i++) {
            boolean corrupted = false;
            
            Deque<Character> chunk = new ArrayDeque<Character>();
            forLoop:
            for (Character c : lines.get(i)) {
                switch (c) {
                    case '(': chunk.push(c); break;
                    case '[': chunk.push(c); break;
                    case '{': chunk.push(c); break;
                    case '<': chunk.push(c); break;
                    case ')':
                        if (!chunk.peek().equals('(')) {
                            syntaxErrorScore += 3;
                            corrupted = true;
                            break forLoop;
                        }
                        chunk.pop(); 
                        break;
                    case ']':
                        if (!chunk.peek().equals('[')) {
                            syntaxErrorScore += 57;
                            corrupted = true;
                            break forLoop;
                        }
                        chunk.pop(); 
                        break;
                    case '}':
                        if (!chunk.peek().equals('{')) {
                            syntaxErrorScore += 1197;
                            corrupted = true;
                            break forLoop;
                        }
                        chunk.pop(); 
                        break;
                    case '>':
                        if (!chunk.peek().equals('<')) {
                            syntaxErrorScore += 25137;
                            corrupted = true;
                            break forLoop;
                        }
                        chunk.pop(); 
                        break;
                    default:
                        break;
                }
            }
            if (!corrupted) {
                int completeScore = 0;
                for (Character c : chunk) {
                    completeScore *= 5;
                    switch (c) {
                        case '(': completeScore += 1; break;
                        case '[': completeScore += 2; break;
                        case '{': completeScore += 3; break;
                        case '<': completeScore += 4; break;
                        default:
                            break;
                    }
                }
                completeScores.add(completeScore);
            }
        }
        // Find middle score
        Collections.sort(completeScores);
        int middle = completeScores.size()/2;
        System.out.format("Part 1: %d\n", syntaxErrorScore);
        System.out.format("Part 2: %d\n", completeScores.get(middle));
    }

    public static void main(String[] args) {
        solve(args[0]);
        // Part 2, 27994957 too high
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
            // if (firstCorruptPos < Integer.MAX_VALUE) {
            //     firstCorruptPos = Integer.MAX_VALUE;
            //     syntaxErrorScore += scores.get(line[firstCorruptPos]);
            // }