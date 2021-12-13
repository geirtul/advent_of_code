import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;


public class Day10 {

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

        int syntaxErrorScore = 0;
        ArrayList<BigInteger> completeScores = new ArrayList<BigInteger>();
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
                // System.out.println(chunk);
                BigInteger completeScore = new BigInteger("0");
                while (chunk.size() > 0) {
                    Character c = chunk.pop();
                    completeScore = completeScore.multiply(new BigInteger("5"));
                    // Flipped score because our deque contains the
                    // matching brackets
                    switch (c) {
                        case '(': completeScore = completeScore.add(new BigInteger("1")); break;
                        case '[': completeScore = completeScore.add(new BigInteger("2")); break;
                        case '{': completeScore = completeScore.add(new BigInteger("3")); break;
                        case '<': completeScore = completeScore.add(new BigInteger("4")); break;
                        default:
                            break;
                    }
                }
                completeScores.add(completeScore);
                // System.out.println(completeScore);
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