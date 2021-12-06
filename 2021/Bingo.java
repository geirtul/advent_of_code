import java.util.ArrayList;

public class Bingo {
    String[] bingoNumbers;
    ArrayList<BingoBoard> boards;
    ArrayList<String> bingoLog;

    public Bingo() {
        this.boards = new ArrayList<BingoBoard>();
        this.bingoLog= new ArrayList<String>();
    }

    public void loadGame(String filename) {
        /**
         * Read in all information about the bingo game. Line 1 are
         * numbers drawn. The rest of the lines contain bingoboards.
         */

        ArrayList<String> bingoInfo = Reader.readLines(filename);

        // Get numbers drawn
        this.bingoNumbers = bingoInfo.get(0).split(",");

        // Loop over the file, storing rows of boards, skipping empty rows.
        String[][] currentBoard = new String[5][5];
        int currentRow = 0; // row of board we're currently building

        for (int i = 2; i < bingoInfo.size(); i++) {
            String[] row = bingoInfo.get(i).strip().split("\\s+");
            
            // Skip empty row between boards
            if (row.length < 5) {
                continue;
            }

            currentBoard[currentRow] = row;
            
            // Board complete, store and reset currentBoard
            if (currentRow == 4) {
                this.boards.add(new BingoBoard(currentBoard, this.boards.size()));
                currentBoard = new String[5][5];
                currentRow = 0;
                continue;
            }
            currentRow++;
        }
    }

    public void drawNumber(String number) {
        /**
         * Given a number that should be 'drawn', loop over boards to mark those 
         * the number is present.
         */

        for (BingoBoard board : this.boards) {
            if (board.hasBingo) {
                continue;
            }
            board.markBoard(number);
            board.checkBoard();
            if (board.hasBingo) {
                int boardsum = board.calculateBoardSum();
                int score = boardsum * Integer.parseInt(number);
                this.bingoLog.add(
                    String.format(
                        "Bingo on board %d, %s | Number: %s Boardsum: %d, score: %d\n", 
                        board.boardID,
                        board.whereBingo,
                        number,
                        boardsum, 
                        score
                    )
                );
            }
        }
    }

    public void play() {
        /**
         * Draw numbers until we have a bingo!
         */
        for (String number : this.bingoNumbers) {
            drawNumber(number);
        }
    }
}
