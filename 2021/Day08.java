import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Day08 {

    public static HashMap<String, Integer> decodeSignalPatterns (String [] signalPatterns) {
        HashMap<Integer, String> numbers = new HashMap<>();
        for (String pattern : signalPatterns) {
            switch (pattern.length()) {
                case 2: numbers.put(1, pattern);
                    break;
                case 3: numbers.put(7, pattern);
                    break;
                case 4: numbers.put(4, pattern);
                    break;
                case 7: numbers.put(8, pattern);
                    break;
            
                default:
                    break;
            }
        }
        // Find 9: subtracting 4 and 7 from patterns with length 6
        // If only one char remains, that pattern decodes to 9
        ArrayList<Character> check = new ArrayList<Character>();
        for (Character c : (numbers.get(4) + numbers.get(7)).toCharArray()) {
            if (!check.contains(c)) {
                check.add(c);
            }
        }

        for (String pattern : signalPatterns) {
            if (pattern.length() == 6) {
                int count = 0;
                for (Character c : pattern.toCharArray()) {
                    if (!check.contains(c)) {
                        count++;
                    }
                }
                if (count == 1) {
                    numbers.put(9, pattern);
                    pattern = "";
                    break;
                }
            }
        }
            
        // Find 2: subtracting 9 from patterns with length 5
        // If only one char is not found in 9, that pattern decodes to 2.
        check.clear();
        for (Character c : (numbers.get(9)).toCharArray()) {
            if (!check.contains(c)) {
                check.add(c);
            }
        }

        for (String pattern : signalPatterns) {
            if (pattern.length() == 5) {
                int count = 0;
                for (Character c : pattern.toCharArray()) {
                    if (!check.contains(c)) {
                        count++;
                    }
                }
                if (count == 1) {
                    numbers.put(2, pattern);
                    pattern = "";
                    break;
                }
            }
        }

        // Find 3 and 5: subtracting 2 from remaining patterns with length 5
        // If only one char is not found => 3, else 5.
        check.clear();
        for (Character c : (numbers.get(2)).toCharArray()) {
            if (!check.contains(c)) {
                check.add(c);
            }
        }

        for (String pattern : signalPatterns) {
            if (pattern.length() == 5) {
                int count = 0;
                for (Character c : pattern.toCharArray()) {
                    if (!check.contains(c)) {
                        count++;
                    }
                }
                if (count == 1) {
                    numbers.put(3, pattern);
                    pattern = "";
                }
                if (count == 2) {
                    numbers.put(5, pattern);
                    pattern = "";
                }
            }
        }
        // Find 6 and 0: subtracting 5 from length 6
        // If only one char is not found => 6, else 0.
        check.clear();
        for (Character c : (numbers.get(5)).toCharArray()) {
            if (!check.contains(c)) {
                check.add(c);
            }
        }

        for (String pattern : signalPatterns) {
            if (pattern.length() == 5) {
                int count = 0;
                for (Character c : pattern.toCharArray()) {
                    if (!check.contains(c)) {
                        count++;
                    }
                }
                if (count == 1) {
                    numbers.put(6, pattern);
                    pattern = "";
                }
                if (count == 2) {
                    numbers.put(0, pattern);
                    pattern = "";
                }
            }
        }

        // Build the reversed hashmap with signalpattern : number
        HashMap<String, Integer> codes = new HashMap<String, Integer>();
        for (Map.Entry<Integer, String> entry : numbers.entrySet()) {
            codes.put(entry.getValue(), entry.getKey());
        }

        return codes;
    }

    public static void solveOne(String filename) {
        ArrayList<String> inputs = Reader.readLines(filename);

        // Initialize hashmap with zeros
        HashMap<Integer, Integer> lengths = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            lengths.put(i, 0);
        }

        // Count number of digits in outputs.
        for (String input : inputs) {
            String[] tmp = input.split("\\|");
            String[] outputs = tmp[1].strip().split(" ");
            for (String output : outputs) {
                int l = output.length();
                lengths.put(l, lengths.get(l)+1);
            }
        }

        int sum = lengths.get(2) + lengths.get(4) + lengths.get(3) + lengths.get(7);
        System.out.format("Part 1: %d\n", sum);
    }

    public static void solveTwo(String filename) {
        ArrayList<String> inputs = Reader.readLines(filename);

        // Initialize hashmap with zeros
        for (String input : inputs) {
            String[] tmp = input.split("\\|");
            String[] signalPatterns = tmp[0].strip().split(" ");
            String[] outputs = tmp[1].strip().split(" ");
            HashMap<String, Integer> codes = decodeSignalPatterns(signalPatterns);
            for (String s : outputs) {
                System.out.print(codes.get(s));
            }
            System.out.println("");
        }
        // System.out.format("Part 2: %d\n", );
    }
    public static void main(String[] args) {
        solveOne(args[0]);
        solveTwo(args[0]);
    }
}