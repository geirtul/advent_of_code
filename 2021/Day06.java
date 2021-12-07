import java.util.ArrayList;

import javax.security.auth.callback.LanguageCallback;

public class Day06 {

    public static void solveOne() {
        String input = "3,4,3,1,2";
        ArrayList<LanternFish> progenitors = new ArrayList<LanternFish>();
        for (String stage : input.split(",")) {
            progenitors.add(new LanternFish(Integer.parseInt(stage)));
        }
        for (int i = 0; i <= 18; i++) {
            System.out.format("Day %d:", i);
            for (LanternFish fish : progenitors) {
                fish.decrementStage();
            }            
            int numberOfFish = progenitors.size();
            for (LanternFish fish: progenitors) {
                numberOfFish += fish.countChildren();
            }
            System.out.println(numberOfFish);
        }

        
        //System.out.format("Part 1: %s\n", );
    }

    public static void solveTwo(String filename) {
        //System.out.format("Part 2: %s\n", );
    }
    public static void main(String[] args) {
        solveOne();
        // solveTwo(args[0]);
    }
}