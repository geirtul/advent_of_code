import java.util.ArrayList;
import java.util.Arrays;

public class BingoBoard {
    String[][] board;
    int boardID;
    Boolean hasBingo;
    String whereBingo;

    public BingoBoard(String[][] board, int id) {
        this.boardID = id;
        this.board = board;
        this.hasBingo = false;
    }

    public void markBoard(String number) {
        /**
         * Checks for <number> on the board. If the number exists, mark
         * that number on the board to "x" and check if any rows or columns
         * are all true.
         * 
         */
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[0].length; j++) {
                if (this.board[i][j].equals(number)) {
                    this.board[i][j] = "x";
                }
            }
        }
        checkBoard();
    }

    public void checkBoard() {
        /**
         * Check if the board has a row or column that is completed.
         * That is, all elements of the row are filled with "x".
         */
        
        // Check rows
        for (int i = 0; i < this.board.length; i++) {
            int numberOfMarked = 0;
            for (int j = 0; j < this.board[0].length; j++) {
                if (this.board[i][j].equals("x")) {
                    numberOfMarked++;
                }
            }
            if (numberOfMarked == 5) {
                this.hasBingo = true;
                this.whereBingo = String.format("Row %d", i);
            }
            
        }

        // Check columns
        for (int i = 0; i < this.board.length; i++) {
            int numberOfMarked = 0;
            for (int j = 0; j < this.board[0].length; j++) {
                if (this.board[j][i].equals("x")) {
                    numberOfMarked++;
                }
            }
            if (numberOfMarked == 5) {
                this.hasBingo = true;
                this.whereBingo = String.format("Column %d", i);
            }
        }
    }

    public int calculateBoardSum() {
        /**
         * Calculate the sum of non "x" elements on the bingoboard.
         */

        int boardsum = 0;
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[0].length; j++) {
                if (!this.board[i][j].equals("x")) {
                    boardsum += Integer.parseInt(this.board[i][j]);
                }                                
            }
        }
        return boardsum;
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
