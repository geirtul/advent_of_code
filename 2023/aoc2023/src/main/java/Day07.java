import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day07 implements Day<ArrayList<String>>{

    public static List<CardHand> parseHands(ArrayList<String> input) {

        List<CardHand> hands = new ArrayList<>();

        for (String line: input) {

            String[] parts = line.split(" ");

            // Extract characters from the first element
            char[] characters = parts[0].toCharArray();
            List<Character> cards = new ArrayList<>();
            for (char c : characters) {
                cards.add(c);
            }

            // Convert the second element to a long
            long bet = Long.parseLong(parts[1]);

            hands.add(new CardHand(cards, bet));
        }

        return hands;
    }

    @Override
    public void solveOne(ArrayList<String> input) {

        List<CardHand> hands = parseHands(input);
        Collections.sort(hands);
        int rank = hands.size();
        long totalWinnings = 0;
        for (CardHand hand: hands) {
            totalWinnings += rank * hand.getBet();
            rank--;
        }
        System.out.println("Solving part one: Total winnings = " + totalWinnings);

    }

    @Override
    public void solveTwo(ArrayList<String> input) {

    }

    @Override
    public ArrayList<String> testInput() {
        ArrayList<String> input = new ArrayList<>();
        input.add("32T3K 765");
        input.add("T55J5 684");
        input.add("KK677 28");
        input.add("KTJJT 220");
        input.add("QQQJA 483");
        return input;
    }

    public static void main(String[] args) {
        Day07 day07 = new Day07();

        day07.solveOne(day07.testInput());
        ArrayList<String> fileInput = InputReader.lineByLine("input/day07");

        day07.solveOne(fileInput);

    }

}
