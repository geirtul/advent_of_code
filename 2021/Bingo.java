import java.util.ArrayList;
import java.util.Arrays;

public class Bingo {
    String[] bingoNumbers;
    ArrayList<BingoBoard> boards;

    public Bingo() {
        this.boards = new ArrayList<BingoBoard>();
    }

    public void loadGame(String filename) {
        /**
         * Read in all information about the bingo game. Line 1 are
         * numbers drawn. The rest of the lines contain bingoboards.
         */

        ArrayList<String> bingoInfo = Reader.readLines(filename);

        // Get numbers drawn
        this.bingoNumbers = bingoInfo.get(0).split(",");
        // this.bingoNumbers = Arrays.stream(
        //     bingoInfo.get(0).split(",")
        // ).mapToInt(Integer::parseInt).toArray();
        ArrayList<String[][]> bingoBoards = new ArrayList<String[][]>();
        String[][] currentBoard = new String[5][5];
        int curr_row = 0;
        for (int i = 2; i < bingoInfo.size(); i++) {
            String[] row = bingoInfo.get(i).strip().split("\\s+");
            if (row.length < 5) {
                continue;
            }
            currentBoard[curr_row] = row;
            
            if (curr_row == 4) {
                bingoBoards.add(currentBoard);
                currentBoard = new String[5][5];
                curr_row = 0;
                continue;
            }
            curr_row++;
        }
        // Finally, add each array of numbers to boards as BingoBoard class instance.
        for (String[][] board : bingoBoards) {
            BingoBoard curr_board = new BingoBoard(board);
            this.boards.add(curr_board);
        }
    }
}
