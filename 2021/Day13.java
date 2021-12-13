import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;


public class Day13 {

    public static void solveOne(String filename) {
        ArrayList<String> lines = Reader.readLines(filename);
        // Get coordinates and fold instructions
        ArrayList<int[]> coords = new ArrayList<int[]>();
        ArrayList<String[]> folds = new ArrayList<String[]>();
        for (int i = 0; i < lines.size(); i++) {
            String l = lines.get(i);
            if (l.length() < 3) {
                continue;
            }
            if (!l.startsWith("fold")) {
                String[] tmp = l.split(",");
                coords.add(new int[]{Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1])});
            } else if (l.startsWith("fold")) {
                folds.add(l.split("fold along ")[1].split("="));
            }
        }


        // System.out.format("Part 1: %d\n", );
    }

    public static void solveTwo(String filename) {
        // System.out.format("Part 2: %d\n", );
    }
    public static void main(String[] args) {
        solveOne(args[0]);
        // Part 2, 27994957 too high
    }
}