import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


public class Day07 {

    public static void solveOne(String filename) {
        String input = Reader.readLine(filename);
        ArrayList<Integer> positions = new ArrayList<Integer>();
        for (String position : input.split(",")) {
            positions.add(Integer.parseInt(position));
        }
        positions.sort(Comparator.naturalOrder());
        int median = positions.get(positions.size()/2);
        System.out.println(median);
        //System.out.format("Part 1: %s\n", );
    }

    public static void solveTwo(String filename) {
        //System.out.format("Part 2: %s\n", );
    }
    public static void main(String[] args) {
        solveOne(args[0]);
        // solveTwo(args[0]);
    }
}