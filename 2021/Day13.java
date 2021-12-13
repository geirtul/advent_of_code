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

        // Set grid
        int xMax = 0; int yMax = 0;
        for (int[] coord : coords) {
            if (coord[0] > xMax) {
                xMax = coord[0];
            }
            if (coord[1] > yMax) {
                yMax = coord[1];
            }
            
        }
        int[][] grid = new int[yMax+1][xMax+1];
        for (int[] co : coords) {
            grid[co[1]][co[0]] = 1;
        }

        // Fold
        int foldCount = 0;
        for (String[] fold: folds) {
            String axis = fold[0];
            int foldPoint = Integer.parseInt(fold[1]);
            if (axis.equals("x")) {
                for (int i = 0; i <= yMax; i++) {                                    
                    for (int j = foldPoint+1; j <= xMax; j++) {
                        if (grid[i][j] == 1) {
                            int diff = j - foldPoint;
                            grid[i][foldPoint - diff] = 1;
                            grid[i][j] = 0;
                        }
                    }
                }
                xMax = xMax/2;
            } else {
                for (int i = 0; i <= xMax; i++) {
                    for (int j = foldPoint+1; j <= yMax; j++) {
                        if (grid[j][i] == 1) {
                            int diff = j - foldPoint;
                            grid[foldPoint - diff][i] = 1;
                            grid[j][i] = 0;
                        }
                    }
                }
                yMax = yMax/2;
            }

            foldCount++;
            int sum = 0;
            for (int[] is : grid) {
                sum += Arrays.stream(is).reduce(0, (a, b) -> a + b);
            }

            // Print solution part 1
            if (foldCount == 1) {
                System.out.format("Part 1: %d\n", sum);
            }
        }

        // Print final grid
        for (int i = 0; i < yMax; i++) {
            for (int j = 0; j < xMax; j++) {
                if (grid[i][j] == 0) {
                    System.out.printf(".");
                } else {
                    System.out.printf("#");
                }
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        solveOne(args[0]);
    }
}