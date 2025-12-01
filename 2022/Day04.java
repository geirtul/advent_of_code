import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.*;

public class Day04 { 

    public static void solveOne(String filename) {

        ArrayList<String> pairs = Reader.readLines(filename);
        ArrayList<int[]> section_numbers = readSectionNumbers(pairs);
        int num_overlaps = 0;

        for (int[] s : section_numbers) {
            if (s[0] >= s[2] && s[1] <= s[3]) {
                num_overlaps++;
                continue;
            } else if (s[2] >= s[0] && s[3] <= s[1]) {
                num_overlaps++;
                continue;
            }
        }

        System.out.format("Part 1: %d\n", num_overlaps);
    }

    public static void solveTwo(String filename) {

        ArrayList<String> pairs = Reader.readLines(filename);
        ArrayList<int[]> section_numbers = readSectionNumbers(pairs);
        int num_overlaps = 0;

        for (int[] s : section_numbers) {
            if (s[0] >= s[2] && s[0] <= s[3]) {
                num_overlaps++;
                continue;
            } else if (s[1] >= s[2] && s[1] <= s[3]) {
                num_overlaps++;
                continue;
            } else if (s[2] >= s[0] && s[2] <= s[1]) {
                num_overlaps++;
                continue;
            } else if (s[3] >= s[0] && s[3] <= s[1]) {
                num_overlaps++;
                continue;
            }
        }
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