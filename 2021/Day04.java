public class Day04 {

    public static void solveOne(String filename) {
        Submarine sub = new Submarine();
        sub.bingoGame = new Bingo();
        sub.bingoGame.loadGame(filename);
        sub.bingoGame.play();
        System.out.format("Part 1: %s\n", sub.bingoGame.bingoLog.get(0));
    }

    public static void solveTwo(String filename) {
        Submarine sub = new Submarine();
        sub.bingoGame = new Bingo();
        sub.bingoGame.loadGame(filename);
        sub.bingoGame.play();
        System.out.format("Part 2: %s\n", sub.bingoGame.bingoLog.get(sub.bingoGame.bingoLog.size() - 1));
    }
    public static void main(String[] args) {
        solveOne(args[0]);
        solveTwo(args[0]);
    }
}