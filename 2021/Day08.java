import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;


public class Day08 {

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
            String[] signalPattern = tmp[0].strip().split(" ");
            String[] outputs = tmp[1].strip().split(" ");
            for (String output : outputs) {
                int l = output.length();
                lengths.put(l, lengths.get(l)+1);
            }
        }
        // System.out.format("Part 2: %d\n", );
    }
    public static void main(String[] args) {
        solveOne(args[0]);
        // solveTwo(args[0]);
    }
}