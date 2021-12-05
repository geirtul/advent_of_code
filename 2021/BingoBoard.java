import java.util.Arrays;

public class BingoBoard {
    String[][] board;

    public BingoBoard(String[][] board) {
        this.board = board;
    }

    public void checkBoard(String number) {
        /**
         * Checks for <number> on the board. If the number exists, change
         * that number on the board to "true" and check if any rows or columns
         * are all true.
         */
        
        // Look for <number> in board and mark as True if found.
        for (int i = 0; i < this.board.length; i++) {
           continue; 
        }
    }

    public void printBoard() {
        /**
         * Output the board in a 5x5 grid
         */
        for (String[] row : this.board) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
}
