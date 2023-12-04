import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day02GameProcessor {

    public static void processGame(String input, Map<Integer, Day02GameInfo> gameInfoMap) {
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
        Day02GameInfo day02GameInfo = new Day02GameInfo();
        processColors(parts[1], day02GameInfo);
        day02GameInfo.calculateGamePower();

        // Store the max color numbers for the game
        gameInfoMap.put(gameNumber, day02GameInfo);
    }

    private static void processColors(String colors, Day02GameInfo day02GameInfo) {
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
                    day02GameInfo.maxBlue = Math.max(day02GameInfo.maxBlue, number);
                    break;
                case "green":
                    day02GameInfo.maxGreen = Math.max(day02GameInfo.maxGreen, number);
                    break;
                case "red":
                    day02GameInfo.maxRed = Math.max(day02GameInfo.maxRed, number);
                    break;
                // Add more cases if you have additional colors
            }
        }
    }

    static boolean isGameValid(int gameNumber, Map<Integer, Day02GameInfo> gameInfoMap, int maxBlue, int maxGreen, int maxRed) {
        Day02GameInfo day02GameInfo = gameInfoMap.get(gameNumber);

        if (day02GameInfo == null) {
            System.out.println("Game information not found for Game " + gameNumber);
            return false;
        }

        return day02GameInfo.maxBlue <= maxBlue && day02GameInfo.maxGreen <= maxGreen && day02GameInfo.maxRed <= maxRed;
    }

    static int calculateMinCubes(Map<Integer, Day02GameInfo> gameInfoMap) {
        int result = 1;

        for (Day02GameInfo day02GameInfo : gameInfoMap.values()) {
            result *= day02GameInfo.maxBlue * day02GameInfo.maxGreen * day02GameInfo.maxRed;
        }

        return result;
    }
}