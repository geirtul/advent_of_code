public class Day02GameInfo {
    int maxBlue = 0;
    int maxGreen = 0;
    int maxRed = 0;
    int gamePower;

    void calculateGamePower() {
        gamePower = maxBlue * maxGreen * maxRed;
    }
}