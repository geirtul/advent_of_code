import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.*;

public class Bingo {
    int[] bingoNumbers;

    public void loadGame(String filename) {
        /**
         * Read in all information about the bingo game. Line 1 are
         * numbers drawn. The rest of the lines contain bingoboards.
         */

        ArrayList<String> bingoInfo = Reader.readLines(filename);

        // Get numbers drawn
        this.bingoNumbers = Arrays.stream(
            bingoInfo.get(0).split(",")
        ).mapToInt(Integer::parseInt).toArray();
        
    }
}
