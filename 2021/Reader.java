import java.util.Scanner;
import java.util.ArrayList;
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

    
}