import java.util.ArrayList;
import java.util.HashMap;

public class Day03 { 

    public static void solveOne(String filename) {

        ArrayList<char[]> rucksacks = Reader.readCharArray(filename);
        int priority_sum = 0;
        for (char[] rucksack : rucksacks) {
            HashMap<Character, Integer> compartment = new HashMap<Character, Integer>(rucksack.length);
            int count = 0;
            while (count < rucksack.length) {
                // Create first compartment
                if (count < rucksack.length / 2) {
                    compartment.put(rucksack[count], 1);
                } else {
                    // Found duplicate in second compartment
                    if (compartment.containsKey(rucksack[count])) {
                        priority_sum += calculatePriority(rucksack[count]);
                        break;
                    }
                }
                count++;
            }
        }
        System.out.format("Part 1: %d\n", priority_sum);
    }

    public static void solveTwo(String filename) {

        ArrayList<char[]> rucksacks = Reader.readCharArray(filename);
        int priority_sum = 0;
        for (int i = 0; i < rucksacks.size() - 2; i += 3) {
            HashMap<Character, Integer> first = new HashMap<Character, Integer>(rucksacks.get(i).length);
            HashMap<Character, Integer> overlap = new HashMap<Character, Integer>(128);
            for (char ch : rucksacks.get(i)) {
                first.put(ch, 1);
            }
            for (char ch : rucksacks.get(i + 1)) {
                if (first.containsKey(ch)) {
                    overlap.put(ch, 1);
                }
            }
            for (char ch : rucksacks.get(i + 2)) {
                if (overlap.containsKey(ch)) {
                    priority_sum += calculatePriority(ch);
                    break;
                }
            }
        }
        System.out.format("Part 2: %d\n", priority_sum);
    }
    
    public static int calculatePriority(char ch) {
        int val = (int) ch;
        if (val >= 97) {
            //lowercase
            return val - 96;
        } else {
            // uppercase
            return val - 38;
        }
    }

    public static void main(String[] args) {
        solveOne(args[0]);
        solveTwo(args[0]);
    }
}