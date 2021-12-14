import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.stream.IntStream;


public class Day14 {

    public static void solveOne(String filename) {
        ArrayList<String> lines = Reader.readLines(filename);
        LinkedList<String> polymer = new LinkedList<String>();
        for (String el : lines.get(0).split("")) {
            polymer.add(el);
        }

        HashMap<String, String> pairs = new HashMap<String, String>();
        for (int i = 2; i < lines.size(); i++) {
            String[] tmp = lines.get(i).split(" -> ");
            pairs.put(tmp[0], tmp[1]);
        }

        for (int i = 0; i < 40; i++) {
            int indexDiff = 0;
            TreeMap<Integer, String> insertions = new TreeMap<Integer, String>();
            for (int j = 0; j < polymer.size() - 1; j++) {
                String segment = polymer.get(j) + polymer.get(j + 1);
                insertions.put(j + 1 + indexDiff, pairs.get(segment));
                indexDiff++;
            }
            Iterator<Integer> itr = insertions.keySet().iterator();
            while (itr.hasNext()) {
                int key = itr.next();
                polymer.add(key, insertions.get(key));
            }
        }

        // Count elements
        HashMap <String, Integer> count = new HashMap<String, Integer>();
        for (String s : polymer) {
            if (count.containsKey(s)) {
                count.put(s, count.get(s) + 1);
            } else {
                count.put(s, 1);
            }
        }
        int[] frequencies = count.values().stream().mapToInt(x -> x).toArray();
        Arrays.sort(frequencies);
        System.out.printf("Part 1: %d\n", frequencies[frequencies.length - 1] - frequencies[0]);

    }

    public static void main(String[] args) {
        solveOne(args[0]);
    }
}