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

        int requiredFuel = 0;
        for (Integer pos : positions) {
            requiredFuel += Math.abs(pos - median);
        }
        
        System.out.format("Part 1: %d\n", requiredFuel);
    }

    public static void solveTwo(String filename) {
        String input = Reader.readLine(filename);
        ArrayList<Integer> positions = new ArrayList<Integer>();
        for (String position : input.split(",")) {
            positions.add(Integer.parseInt(position));
        }
        positions.sort(Comparator.naturalOrder());
        int avg;
        int tmp = 0;
        for (Integer pos : positions) {
            tmp += pos;
        }
        avg = (int)Math.ceil((double)tmp/positions.size());

        int requiredFuel = 0;
        for (Integer pos : positions) {
            int diff = Math.abs(pos - avg);
            int fuel = 0;
            for (int i = 1; i <= diff; i++) {
                fuel+=i;                
            }
            requiredFuel += fuel;
        }
        
        System.out.format("Part 2: %d\n", requiredFuel);
    }
    public static void main(String[] args) {
        // solveOne(args[0]);
        solveTwo(args[0]);
        // 94813677 too high
    }
}