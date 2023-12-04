import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Day02 implements Day<ArrayList<String>> {

    @Override
    public void solveOne(ArrayList<String> games) {
        // Implementation for solveOne method
        // Map to store max color numbers for each game
        Map<Integer, GameInfo> gameInfoMap = new HashMap<>();

        // Set the available number of blocks for each color
        int maxRedBlocks = 12;
        int maxGreenBlocks = 13;
        int maxBlueBlocks = 14;

        // Sum up the game numbers for valid games
        int sumValidGames = 0;

        // Process each input line from the ArrayList
        for (String game : games) {
            GameProcessor.processGame(game, gameInfoMap);
        }

        // Check if each game is valid
        for (Map.Entry<Integer, GameInfo> entry : gameInfoMap.entrySet()) {
            int gameNumber = entry.getKey();
            boolean isValid = GameProcessor.isGameValid(gameNumber, gameInfoMap, maxBlueBlocks, maxGreenBlocks, maxRedBlocks);
            if (isValid) {
                sumValidGames += gameNumber;
            }
            //System.out.println("Game " + gameNumber + " is " + (isValid ? "valid" : "invalid"));
        }
        System.out.println("Solving Part One: " + sumValidGames);
    }

    @Override
    public void solveTwo(ArrayList<String> games) {
        // Map to store max color numbers for each game
        Map<Integer, GameInfo> gameInfoMap = new HashMap<>();
        // Process each input line from the ArrayList
        for (String game : games) {
            GameProcessor.processGame(game, gameInfoMap);
        }

        int sumGamePowers = 0;

        for (GameInfo gameInfo : gameInfoMap.values()) {
            sumGamePowers += gameInfo.gamePower;
        }

        System.out.println("Solving Part Two: " + sumGamePowers);
    }

    private static ArrayList<String> testInput() {
        // Create an ArrayList to store the input lines
        ArrayList<String> testGames = new ArrayList<>();

        // Sample input lines
        testGames.add("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green");
        testGames.add("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue");
        testGames.add("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red");
        testGames.add("Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red");
        testGames.add("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green");

        return testGames;
    }
    public static void main(String[] args) {
        // Example usage in the main method
        Day02 day02 = new Day02();

        ArrayList<String> testGames = testInput();

        // Solve testcase
        System.out.println("Testcases:");
        day02.solveOne(testGames);
        day02.solveTwo(testGames);

        // Continue with InputReader and solve puzzle input
        InputReader inputReader = new InputReader();
        ArrayList<String> fileLines = inputReader.lineByLine("input/dat02");
        System.out.println("\nCases:");
        day02.solveOne(fileLines);
        day02.solveTwo(fileLines);
    }
}