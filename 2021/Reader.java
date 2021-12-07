import java.util.Scanner;
import java.util.ArrayList;
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

    public static ArrayList<char[]> readCharArray(String filename) {
        /**
         * When you want each line as a string.
         */
        ArrayList<char[]> lines = new ArrayList<char[]>();
        try {
            File input = new File(filename);
            Scanner s = new Scanner(input);
        
            // Loop over input and store lines as list
            while (s.hasNextLine()) {
                String line = s.nextLine();
                lines.add(line.toCharArray());
            }
            s.close();
            return lines;

        } catch (Exception e) {
            e.printStackTrace();
            return lines;
        }
    }

    public static ArrayList<int[]> readLinesRegex(String filename, String regex) {
        /**
         * Read a file line by line, grabbing the matching objects
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
            }
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allCoordinates;

    }

    
}