import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.*;

public class Day05 { 

    public static void solveOne(String filename) {

        ArrayList<String> inputs = Reader.readLines(filename);
        String top_crates = "";

        String columns_regex = "(\\[[A-Z]\\]|\\s\\s\\s)\\s";
        Pattern pattern = Pattern.compile(columns_regex);
        ArrayList<String[]> columns = new ArrayList<String[]>();
        
        int idx_first_move = 0;
        for (int i = 0; i < inputs.size(); i++) {
            Matcher matcher = pattern.matcher(inputs.get(i));
            matcher.find();
            if (matcher.matches()) {
                // Handle matches

            } else {
                // if we didn't find a match, we've reached the column numbers
                idx_first_move = i+2;
                break;
            }
        }

        for (int i = idx_first_move; i < inputs.size(); i++) {

        }

        System.out.format("Part 1: %s\n", top_crates);
    }

    public static void solveTwo(String filename) {

        ArrayList<String> pairs = Reader.readLines(filename);
        ArrayList<int[]> section_numbers = readSectionNumbers(pairs);
        int num_overlaps = 0;

        System.out.format("Part 2: %d\n", num_overlaps);
    }

    public static ArrayList<int[]> readSectionNumbers(ArrayList<String> pairs) {
        /*
         * Read section numbers using regex
        */

        ArrayList<int[]> sections = new ArrayList<int[]>();
        String regex = "(\\d+)-(\\d+),(\\d+)-(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        for (String line: pairs) {
            int[] section_numbers = new int[4];
            Matcher matcher = pattern.matcher(line);
            matcher.find();
            if (matcher.matches()) {
                section_numbers[0] = Integer.parseInt(matcher.group(1));
                section_numbers[1] = Integer.parseInt(matcher.group(2));
                section_numbers[2] = Integer.parseInt(matcher.group(3));
                section_numbers[3] = Integer.parseInt(matcher.group(4));
            }
            sections.add(section_numbers);
        }
        return sections;
    }
    
    public static void main(String[] args) {
        solveOne(args[0]);
        solveTwo(args[0]);
    }
}