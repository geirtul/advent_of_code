import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Day03 implements Day<char[][]> {

    public static class NumberSymbol {
        int number;
        char symbol;
        int symbolRow;
        int symbolCol;

        private UUID id;

        NumberSymbol(int number, char symbol, int symbolRow, int symbolCol) {
            this.number = number;
            this.symbol = symbol;
            this.symbolRow = symbolRow;
            this.symbolCol = symbolCol;
            this.id = UUID.randomUUID();
        }

        public boolean compareSymbolIndices(NumberSymbol numberSymbol) {
            if (this == numberSymbol) {
                return false;
            } else {
                return (numberSymbol.symbolRow == this.symbolRow && numberSymbol.symbolCol == this.symbolCol);
            }
        }

        public UUID getId() {
            return id;
        }
        @Override
        public String toString() {
            return "NumberSymbol{" +
                    "number=" + number +
                    ", symbol=" + symbol +
                    ", symbolRow=" + symbolRow +
                    ", symbolCol=" + symbolCol +
                    '}';
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }

            if (!(obj instanceof NumberSymbol n)) {
                return false;
            }

            return this.symbol == n.symbol && this.number == n.number && this.compareSymbolIndices(n);
        }
    }

    public static ArrayList<NumberSymbol> extractNumbers(char[][] input) {
        ArrayList<NumberSymbol> numberSymbols = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            int startIdx = -1;
            int endIdx = -1;


            for (int j = 0; j < input[i].length; j++) {
                if (Character.isDigit(input[i][j])) {
                    // If the character is a digit, update start and end indices
                    if (startIdx == -1) {
                        startIdx = j;
                    }
                    endIdx = j;
                } else if (startIdx != -1) {
                    // If we encounter a non-digit after a digit sequence, it's the end of the number
                    processNumber(input, i, startIdx, endIdx, numberSymbols);
                    startIdx = -1;  // Reset for the next sequence of digits
                }
            }

            // Check if there's a number at the end of the row
            if (startIdx != -1) {
                processNumber(input, i, startIdx, endIdx, numberSymbols);
            }
        }
        return numberSymbols;
    }

    private static void processNumber(char[][] input, int row, int startIdx, int endIdx, ArrayList<NumberSymbol> numberSymbols) {
        // Retrieve the number and find symbols around it using the indices
        StringBuilder number = new StringBuilder();

        for (int j = startIdx; j <= endIdx; j++) {
            number.append(input[row][j]);
        }

        for (int j = startIdx; j <= Math.min(endIdx, input[0].length); j++) {
            NumberSymbol adjacentSymbol = getAdjacentSymbol(input, row, j, number);
            if (adjacentSymbol.symbol != '.') {
                numberSymbols.add(adjacentSymbol);
                return;
            }
        }
    }

    private static NumberSymbol getAdjacentSymbol(char[][] input, int row, int col, StringBuilder number) {
        // Check for adjacent symbols (up, down, left, right, diagonal)
        int[] rowOffsets = {-1, 1, 0, 0, -1, -1, 1, 1};
        int[] colOffsets = {0, 0, -1, 1, -1, 1, -1, 1};
        NumberSymbol numberSymbol = new NumberSymbol(
                Integer.parseInt(number.toString()),
                '.',
                row,
                col);

        for (int i = 0; i < 8; i++) {
            int newRow = row + rowOffsets[i];
            int newCol = col + colOffsets[i];

            if (isValidIndex(input, newRow, newCol) && !Character.isDigit(input[newRow][newCol]) && input[newRow][newCol] != '.') {
                numberSymbol.symbol = input[newRow][newCol];
                numberSymbol.symbolRow = newRow;
                numberSymbol.symbolCol = newCol;
                return numberSymbol;
            }
        }

        return numberSymbol;
    }

    private static boolean isValidIndex(char[][] input, int row, int col) {
        return row >= 0 && row < input.length && col >= 0 && col < input[row].length;
    }

    @Override
    public void solveOne(char[][] input) {
        // Implementation for part one of the day's problem
        ArrayList<NumberSymbol> numberSymbols = extractNumbers(input);
        //System.out.println("Solving part one...");
        // Your code here for solving part one using the char[][] input
        int numberSum = 0;
        for (NumberSymbol numberSymbol: numberSymbols) {
            numberSum += numberSymbol.number;
        }
        System.out.println(numberSum);
    }

    @Override
    public void solveTwo(char[][] input) {
        // Implementation for part two of the day's problem
        int gearRatio = 0;
        ArrayList<NumberSymbol> numberSymbols = extractNumbers(input);
        ArrayList<NumberSymbol> newNumbers = new ArrayList<>();
        for (NumberSymbol numberSymbol: numberSymbols) {
            if (numberSymbol.symbol == '*') {
                newNumbers.add(numberSymbol);
            }
        }

        HashMap<UUID, NumberSymbol> checked = new HashMap<>();
        for (NumberSymbol curr: newNumbers) {
            ArrayList<NumberSymbol> adjacent = new ArrayList<>();
            for (NumberSymbol tmp: newNumbers) {
                if (curr.compareSymbolIndices(tmp) && tmp != curr && !checked.containsKey(curr.getId())) {
                    adjacent.add(tmp);
                    checked.put(curr.getId(), curr);
                }
            }
            if (adjacent.size() == 1 && !checked.containsKey(adjacent.get(0).getId())) {
                gearRatio += curr.number * adjacent.get(0).number;
            }
        }

        System.out.println(gearRatio);
    }

    public static char[][] testInput() {
        return new char[][]{
                {'4', '6', '7', '.', '.', '1', '1', '4', '.'},
                {'.', '.', '.', '*', '.', '.', '.', '.', '.'},
                {'.', '.', '3', '5', '.', '.', '6', '3', '3'},
                {'.', '.', '.', '.', '.', '#', '.', '.', '.'},
                {'6', '1', '7', '*', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '+', '.', '5', '8'},
                {'.', '.', '5', '9', '2', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '7', '5', '5'},
                {'.', '.', '.', '$', '.', '*', '.', '.', '.'},
                {'.', '6', '6', '4', '.', '5', '9', '8', '.'}
        };
    }

    public static void main(String[] args) {
        Day03 day03 = new Day03();
        day03.solveOne(testInput());

        // Solve part one
        InputReader inputReader = new InputReader();
        char[][] engine = inputReader.fileToCharArray("input/day_three");
        day03.solveOne(engine);

        // Solve part two
        day03.solveTwo(testInput());
        day03.solveTwo(engine);

    }
}