import java.util.ArrayList;

public class Day04 implements Day<ArrayList<String>> {
    @Override
    public void solveOne(ArrayList<String> input) {
        ArrayList<Card> cards = CardProcessor.processCards(input);
        CardProcessor.calculatePoints(cards);
        int totalPoints = CardProcessor.calculateTotalPoints(cards);
        System.out.println("Total Points: " + totalPoints);
    }

    @Override
    public void solveTwo(ArrayList<String> input) {
        ArrayList<Card> cards = CardProcessor.processCards(input);
        CardProcessor.calculatePoints(cards);
        int numCards = CardProcessor.processGameNewRules(cards);

        System.out.println("Total cards: " + numCards);

    }

    public static ArrayList<String> testInput() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53");
        cards.add("Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19");
        cards.add("Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1");
        cards.add("Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83");
        cards.add("Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36");
        cards.add("Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11");

        return cards;
    }

    public static void main(String[] args) {

        Day04 day04 = new Day04();
        ArrayList<String> testInput = testInput();

        day04.solveOne(testInput);

        InputReader inputReader = new InputReader();
        ArrayList<String> fileInput = inputReader.lineByLine("input/day04");
        day04.solveOne(fileInput);

        day04.solveTwo(testInput);
        day04.solveTwo(fileInput);

    }
}