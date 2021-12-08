import java.util.ArrayList;
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

        int requiredFuel = 100000000;
        int biggest = 0;
        for (int num : positions) {
            if (num > biggest) {
                biggest = num;
            }            
        }
        for (int i = 0; i < biggest; i++) {
            int sum = 0;
            for (int j = 0; j < positions.size(); j++) {
                int x = Math.abs(i - positions.get(j));
                sum += (x*x + x)*0.5;
            }
            if (sum < requiredFuel) {
                requiredFuel = sum;
            }
            
        }

        System.out.format("Part 2: %d\n", requiredFuel);
    }
    public static void main(String[] args) {
        solveOne(args[0]);
        solveTwo(args[0]);
    }
}