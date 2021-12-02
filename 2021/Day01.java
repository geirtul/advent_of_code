import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class Day01 { 

    public static void solveOne(String filename) {

        ArrayList<Integer> depths = Reader.readInts(filename);

        int count = 0;
        for (int i = 1; i < depths.size(); i++) {
            if (depths.get(i) > depths.get(i-1)) {
                count++;
            }
        }
        System.out.format("Part 1: %d\n", count);
    }

    public static void solveTwo(String filename) {

        ArrayList<Integer> depths = Reader.readInts(filename);

        int prev_sum =  depths.get(0) + depths.get(1) + depths.get(2);
        int count = 0;
        for (int i = 3; i < depths.size(); i++) {
            int curr_sum =  depths.get(i) + depths.get(i-1) + depths.get(i-2);
            if (curr_sum > prev_sum) {
                count++;
            }
            prev_sum = curr_sum;
        }
        System.out.format("Part 2: %d\n", count);
    }

    public static void main(String[] args) {
        solveOne(args[0]);
        solveTwo(args[0]);
    }
}