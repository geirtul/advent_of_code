import java.util.ArrayList;
import java.util.Arrays;


public class Day09 {

    public static void solveOne(String filename) {
        int[][] heightmap = Reader.readIntegerArray2D(filename);
        for (int[] tmp: heightmap) {
            System.out.println(Arrays.toString(tmp));
        }

        // System.out.format("Part 1: %d\n", sum);
    }

    public static void solveTwo(String filename) {
        int[][] heightmap = Reader.readIntegerArray2D(filename);
        // System.out.format("Part 2: %d\n", );
    }
    public static void main(String[] args) {
        solveOne(args[0]);
        // solveTwo(args[0]);
    }
}