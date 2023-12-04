import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameProcessor {

    public static void processGame(String input, Map<Integer, GameInfo> gameInfoMap) {
        // Split the input string on ":"
        String[] parts = input.split(":");

        if (parts.length != 2) {
            //System.out.println("Invalid input format for a game: " + input);
            return;
        }

        // Extract the game number
        int gameNumber;
        try {
            gameNumber = Integer.parseInt(parts[0].trim().replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            System.out.println("Invalid game number format: " + parts[0]);
            return;
        }

        // Extract and process the color information
        GameInfo gameInfo = new GameInfo();
        processColors(parts[1], gameInfo);
        gameInfo.calculateGamePower();

        // Store the max color numbers for the game
        gameInfoMap.put(gameNumber, gameInfo);
    }

    private static void processColors(String colors, GameInfo gameInfo) {
        // Define a regex pattern to match color numbers
        Pattern pattern = Pattern.compile("(\\d+)\\s+(blue|green|red)");
        Matcher matcher = pattern.matcher(colors);

        // Iterate through the matches
        while (matcher.find()) {
            // Extract the color and number from the match
            String color = matcher.group(2).toLowerCase();
            int number = Integer.parseInt(matcher.group(1));

            // Update the max number for each color
            switch (color) {
                case "blue":
                    gameInfo.maxBlue = Math.max(gameInfo.maxBlue, number);
                    break;
                case "green":
                    gameInfo.maxGreen = Math.max(gameInfo.maxGreen, number);
                    break;
                case "red":
                    gameInfo.maxRed = Math.max(gameInfo.maxRed, number);
                    break;
                // Add more cases if you have additional colors
            }
        }
    }

    static boolean isGameValid(int gameNumber, Map<Integer, GameInfo> gameInfoMap, int maxBlue, int maxGreen, int maxRed) {
        GameInfo gameInfo = gameInfoMap.get(gameNumber);

        if (gameInfo == null) {
            System.out.println("Game information not found for Game " + gameNumber);
            return false;
        }

        return gameInfo.maxBlue <= maxBlue && gameInfo.maxGreen <= maxGreen && gameInfo.maxRed <= maxRed;
    }

    static int calculateMinCubes(Map<Integer, GameInfo> gameInfoMap) {
        int result = 1;

        for (GameInfo gameInfo : gameInfoMap.values()) {
            result *= gameInfo.maxBlue * gameInfo.maxGreen * gameInfo.maxRed;
        }

        return result;
    }
}