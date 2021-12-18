import java.util.ArrayList;
import java.util.Arrays;

public class Day11 {

    public static void solveOne(String filename) {
        int[][] octopuses = Reader.readIntegerArray2D(filename);
        
        int num_steps = 1000;
        int flashes = 0;

        for (int step = 0; step < num_steps; step++) {
            
            // Step start. Increment all energy levels by 1. First round of flashes.
            for (int i = 0; i < octopuses.length; i++) {
                for (int j = 0; j < octopuses[0].length; j++) {
                    octopuses[i][j]++;
                }
            }
            
            int[][] flash_check = new int[octopuses.length][octopuses[0].length];
            int new_flashes = 1; // Just needs to be > 0
            
            while (new_flashes > 0) {
                for (int i = 0; i < octopuses.length; i++) {
                    for (int j = 0; j < octopuses[0].length; j++) {
                        if (octopuses[i][j] > 9 && flash_check[i][j] == 0) {
                            octopuses[i][j] = 0;
                            flashes++;
                            flash_check[i][j]++;
                        }
                    }
                }

                // Count number of new flashes
                int counter = 0;
                for (int i = 0; i < flash_check.length; i++) {
                    for (int j = 0; j < flash_check[0].length; j++) {
                        if (flash_check[i][j] == 1) {
                            counter++;
                        }
                    }
                }
                new_flashes = counter;
                
                // Increment surrounding octopuses
                for (int i = 0; i < octopuses.length; i++) {
                    for (int j = 0; j < octopuses[0].length; j++) {
                        if (flash_check[i][j] == 1) {
                            // Flashed this step, increment surrounding
                            for (int k = i - 1; k <= i + 1; k++) {
                                for (int l = j - 1; l <= j + 1; l++) {
                                    if(k == i && l == j) {
                                        continue;
                                    }
                                    try {
                                        if (flash_check[k][l] == 0) {
                                            octopuses[k][l]++;
                                        }
                                    } catch (ArrayIndexOutOfBoundsException e) {
                                        //Do nothing, it's an edge
                                    }
                                }
                            }
                            // Increment flash_check so we don't increment surrounding again later.
                            flash_check[i][j]++;
                        }
                    }
                }
            }
            int num_zeros = 0;
            for (int i = 0; i < flash_check.length; i++) {
                for (int j = 0; j < flash_check[0].length; j++) {
                    if (octopuses[i][j] == 0) {
                        num_zeros++;
                    }                    
                }
            }
            if (num_zeros == octopuses.length * octopuses[0].length) {
                System.out.printf("Part 2: %d\n", step+1);
                break;
            }
            if (step == 100) {
                System.out.printf("Part 1: %d\n", flashes);
            }
        }
    }

    public static void main(String[] args) {
        solveOne(args[0]);
    }
}