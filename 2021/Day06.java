import java.util.ArrayList;

public class Day06 {

    public static void solveOne(String filename, int days) {
        String input = Reader.readLine(filename);
        String[] progenitors = input.split(",");
        int numberOfFish = progenitors.length;

        for (String stage : progenitors) {
            LanternFish fish = new LanternFish(Integer.parseInt(stage));
            for (int i = 0; i < days; i++) {
                fish.decrementStage();
            }
            numberOfFish += fish.countChildren();
        }
        System.out.format("Part 1: %d\n", numberOfFish);
    }

    public static void solveTwo(String filename, int days) {
        String input = Reader.readLine(filename);
        String[] progenitors = input.split(",");
        int numberOfFish = progenitors.length;

        for (String stage : progenitors) {
            LanternFish fish = new LanternFish(Integer.parseInt(stage));
            for (int i = 0; i < days; i++) {
                fish.decrementStage();
            }
            numberOfFish += fish.countChildren();
        }
        System.out.format("Part 1: %d\n", numberOfFish);
        //System.out.format("Part 2: %s\n", );
    }
    public static void main(String[] args) {
        solveOne(args[0], 80);
        solveTwo(args[0], 256);
    }
}