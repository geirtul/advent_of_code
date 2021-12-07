import java.util.ArrayList;
import java.util.Arrays;

public class Day05 {

    public static void solveOne(String filename) {
        ArrayList<int[]> test = Reader.readDay5(filename, "(\\d+),(\\d+) -> (\\d+),(\\d+)");
        ArrayList<Line2D> lines = new ArrayList<Line2D>();
        int xMax = 0;
        int yMax = 0;
        for (int[] coords : test) {
            int[] start = new int[]{coords[0], coords[1]};
            int[] end = new int[]{coords[2], coords[3]};
            
            // Skip lines that are not horizontal or vertical
            if (start[0] != end[0] && start[1] != end[1]) {
                continue;
            }

            // Update xmax and ymax
            xMax = Math.max(Math.max(start[0], end[0]), xMax);
            yMax = Math.max(Math.max(start[1], end[1]), yMax);
            
            lines.add(new Line2D(start, end));
        }
        // Set up array to keep track of points where all lines
        int[][] grid = new int[xMax + 1][yMax + 1];
        for (int i = 0; i < lines.size(); i++) {
            Line2D currentLine = lines.get(i);
            for (int j = i + 1; j < lines.size(); j++) {
                Line2D otherLine = lines.get(j);
                for (Integer idx : currentLine.overlappingPoints(otherLine)) {
                    int[] point = currentLine.points[idx];
                    grid[point[0]][point[1]]++;
                }
            }
        }

        int numOverlapping = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] > 0) {
                    numOverlapping++;
                }
            }
            
        }
        System.out.format("Part 1: %d\n", numOverlapping);
    }

    public static void solveTwo(String filename) {
        ArrayList<int[]> test = Reader.readDay5(filename, "(\\d+),(\\d+) -> (\\d+),(\\d+)");
        ArrayList<Line2D> lines = new ArrayList<Line2D>();
        int xMax = 0;
        int yMax = 0;
        for (int[] coords : test) {
            int[] start = new int[]{coords[0], coords[1]};
            int[] end = new int[]{coords[2], coords[3]};
            
            // Update xmax and ymax
            xMax = Math.max(Math.max(start[0], end[0]), xMax);
            yMax = Math.max(Math.max(start[1], end[1]), yMax);
            
            lines.add(new Line2D(start, end));
        }
        // Set up array to keep track of points where all lines
        int[][] grid = new int[xMax + 1][yMax + 1];
        for (int i = 0; i < lines.size(); i++) {
            Line2D currentLine = lines.get(i);
            for (int j = i + 1; j < lines.size(); j++) {
                Line2D otherLine = lines.get(j);
                for (Integer idx : currentLine.overlappingPoints(otherLine)) {
                    int[] point = currentLine.points[idx];
                    grid[point[0]][point[1]]++;
                }
            }
        }

        int numOverlapping = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] > 0) {
                    numOverlapping++;
                }
            }
            
        }
        for (Line2D meh : lines) {
            meh.printLine();
        }
        System.out.format("Part 2: %d\n", numOverlapping);
        // 7196 too low
    }
    public static void main(String[] args) {
        // solveOne(args[0]);
        solveTwo(args[0]);
    }
}

