import java.util.ArrayList;
import java.util.Arrays;


public class Day09 {

    public static ArrayList<int[]> solveOne(String filename) {
        int[][] heightmap = Reader.readIntegerArray2D(filename);

        // Find low points
        boolean right, left, up, down;
        ArrayList<int[]> lowPoints = new ArrayList<int[]>();
        for (int i = 0; i < heightmap.length; i++) {
            for (int j = 0; j < heightmap[0].length; j++) {
                // Right and left
                if (j == 0) { // Left-most column
                    right = heightmap[i][j] < heightmap[i][j + 1];
                    left = true; // true by default
                } else if (j == heightmap[0].length - 1) { // Right-most column
                    right = true; // true by default
                    left = heightmap[i][j] < heightmap[i][j - 1];
                } else {
                    right = heightmap[i][j] < heightmap[i][j + 1];
                    left = heightmap[i][j] < heightmap[i][j - 1];
                }

                // Up and down
                if (i == 0) { // Top row
                    up = true; // true by default for top row
                    down = heightmap[i][j] < heightmap[i + 1][j];
                } else if (i == heightmap.length - 1) { // Handle bottom row
                    up = heightmap[i][j] < heightmap[i - 1][j];
                    down = true; // true by default for bottom row
                } else {
                    up = heightmap[i][j] < heightmap[i - 1][j];
                    down = heightmap[i][j] < heightmap[i + 1][j];
                }

                // Check
                if (right && left && up && down) {
                    lowPoints.add(new int[]{i, j});
                }
            }
        }

        int riskLevel = 0;
        for (int[] coord: lowPoints) {
            riskLevel += heightmap[coord[0]][coord[1]] + 1;
        }

        System.out.format("Part 1: %d\n", riskLevel);
        return lowPoints;
    }

    public static void solveTwo(String filename, ArrayList<int[]> lowPoints) {
        int[][] heightmap = Reader.readIntegerArray2D(filename);
        
        // System.out.format("Part 2: %d\n", );
    }
    public static void main(String[] args) {
        ArrayList<int[]> lowPoints = solveOne(args[0]);
        solveTwo(args[0], lowPoints);
    }
}