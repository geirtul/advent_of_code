import java.util.ArrayList;

public class Day02 { 

    public static void solveOne(String filename) {
        ArrayList<String> instructions = Reader.readLines(filename);
        Submarine sub = new Submarine();
        for (String instruction : instructions) {
            String[] tmp = instruction.split(" ");
            
            sub.move(tmp[0], Integer.parseInt(tmp[1]));
        }

        int solution = sub.position.x * sub.position.z;
        System.out.format("Part 1: %d\n", solution);
    }

    public static void solveTwo(String filename) {
        ArrayList<String> instructions = Reader.readLines(filename);
        Submarine sub = new Submarine();
        for (String instruction : instructions) {
            String[] tmp = instruction.split(" ");
            
            sub.moveWithAim(tmp[0], Integer.parseInt(tmp[1]));
        }

        int solution = sub.position.x * sub.position.z;
        System.out.format("Part 2: %d\n", solution);
    }

    public static void main(String[] args) {
        solveOne(args[0]);
        solveTwo(args[0]);
    }
}