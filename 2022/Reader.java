import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.*;
import java.io.File;

/**
 * Reader class to not have to copy paste lots of Scanner code
 */
public class Reader {

    public static ArrayList<Integer> readInts(String filename) {
        /**
         * When the input is just a long list of one integer per line.
         */

        ArrayList<Integer> numbers = new ArrayList<Integer>();
        try {
            File input = new File(filename);
            Scanner s = new Scanner(input);
        
            // Loop over input and store ints in a list
            while (s.hasNext()) {
                int number = s.nextInt();
                numbers.add(number);
            }
            s.close();
            return numbers;

        } catch (Exception e) {
            e.printStackTrace();
            return numbers;
        }
    }

    public static ArrayList<String> readLines(String filename) {
        /**
         * When you want each line as a string.
         */
        ArrayList<String> lines = new ArrayList<String>();
        try {
            File input = new File(filename);
            Scanner s = new Scanner(input);
        
            // Loop over input and store lines as list
            while (s.hasNextLine()) {
                String line = s.nextLine();
                lines.add(line);
            }
            s.close();
            return lines;

        } catch (Exception e) {
            e.printStackTrace();
            return lines;
        }
    }

    public static String readLine(String filename) {
        /**
         * When you want just the one line.
         */
        String line = "";
        try {
            File input = new File(filename);
            Scanner s = new Scanner(input);
        
            line = s.nextLine();
            s.close();
            return line;

        } catch (Exception e) {
            e.printStackTrace();
            return line;
        }
    }

    public static ArrayList<char[]> readCharArray(String filename) {
        /**
         * When you want each line as a char array
         */
        ArrayList<char[]> lines = new ArrayList<char[]>();
        try {
            File input = new File(filename);
            Scanner s = new Scanner(input);
        
            // Loop over input and store lines as list
            while (s.hasNextLine()) {
                String line = s.nextLine().strip();
                lines.add(line.toCharArray());
            }
            s.close();
            return lines;

        } catch (Exception e) {
            e.printStackTrace();
            return lines;
        }
    }

    public static int[][] readIntegerArray2D(String filename) {
        /**
         * When you want the end result to be a 2D array of integers.
         */
        ArrayList<int[]> lines = new ArrayList<int[]>();
        int[][] outputs;
        try {
            File input = new File(filename);
            Scanner s = new Scanner(input);
        
            // Loop over input and store lines as list
            while (s.hasNextLine()) {
                int[] line = s.nextLine().chars().map(x -> x - '0').toArray();
                lines.add(line);
            }
            s.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        outputs = new int[lines.size()][lines.get(0).length];
        for (int i = 0; i < lines.size(); i++) {
            outputs[i] = lines.get(i);
        }

        return outputs;
    }

    public static ArrayList<int[]> readDay5(String filename, String regex) {
        /**
         * Read a file line by line, grabbing the matching objects,
         * Really specific to Day05 input 2021.
         */
        ArrayList<int[]> allCoordinates = new ArrayList<int[]>();
        Pattern pattern = Pattern.compile(regex);
        try {
            File input = new File(filename);
            Scanner s = new Scanner(input);
            
            // Loop over input and store lines as list
            while (s.hasNextLine()) {
                int[] coordinates = new int[4];
                String line = s.nextLine();
                Matcher matcher = pattern.matcher(line);
                matcher.find();
                if (matcher.matches()) {
                    coordinates[0] = Integer.parseInt(matcher.group(1));
                    coordinates[1] = Integer.parseInt(matcher.group(2));
                    coordinates[2] = Integer.parseInt(matcher.group(3));
                    coordinates[3] = Integer.parseInt(matcher.group(4));
                }
                allCoordinates.add(coordinates);
            }
            s.close();
            return allCoordinates;
        } catch (Exception e) {
            e.printStackTrace();
            return allCoordinates;
        }

    }
}