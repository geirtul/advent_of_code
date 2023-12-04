import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardProcessor {

    private static final Pattern CARD_PATTERN = Pattern.compile("Card\\s*(\\d+):\\s*([\\d\\s]+)\\s*\\|\\s*([\\d\\s]+)");

    public static ArrayList<Card> processCards(ArrayList<String> cards) {
        ArrayList<Card> cardList = new ArrayList<>();

        for (String card : cards) {
            Matcher matcher = CARD_PATTERN.matcher(card);

            if (matcher.matches()) {
                int cardNumber = Integer.parseInt(matcher.group(1));
                ArrayList<Integer> winningNumbers = extractNumbers(matcher.group(2));
                ArrayList<Integer> candidateNumbers = extractNumbers(matcher.group(3));

                // Creating a Day04Card object
                Card day04Card = new Card(cardNumber, winningNumbers, candidateNumbers);

                // Adding the object to the list
                cardList.add(day04Card);
            }
        }

        // Returning the list of Day04Card objects
        return cardList;
    }

    public static void calculatePoints(ArrayList<Card> cardList) {
        for (Card card : cardList) {
            calculatePointsForCard(card);
        }
    }


    private static void calculatePointsForCard(Card card) {
        int matches = (int) (card.candidateNumbers.stream()
                            .filter(card.winningNumbers::contains)
                            .count());

        card.points = (int) Math.pow(2, matches - 1);
        card.numberOfMatches = matches;
    }

    public static int calculateTotalPoints(ArrayList<Card> cardList) {
        int totalPoints = 0;

        for (Card card : cardList) {
            totalPoints += card.points;
        }

        return totalPoints;
    }

    public static int processGameNewRules(ArrayList<Card> cardList) {
        HashMap<Integer, Card> cards = new HashMap<>();
        HashMap<Integer, ArrayList<Card>> result = new HashMap<>();

        for (Card card: cardList) {
            cards.put(card.id, card);
        }
        for (int i = 1; i < cardList.size() - 1; i++) {
            addNewCards(result, cards, cards.get(i));
            //System.out.println("After card " + i + " looping over:");
            if (result.containsKey(i+1)) {
                for (Card card : result.get(i + 1)) {
                    //System.out.println("\t" + card);
                    addNewCards(result, cards, card);
                }
            }
        }

//        for (int i = 2; i < cardList.size(); i++) {
//            for (Day04Card card: result.get(i)) {
//                addNewCards(result, cards, card);
//            }
//        }

        int totalCards = 0;
        for (ArrayList<Card> resultCards: result.values()) {
            totalCards += resultCards.size();
            for (Card card: resultCards) {
                //System.out.print(card.id + " ");
            }
            //System.out.println();

        }

        return totalCards + cards.size();
    }

    public static void addNewCards(HashMap<Integer, ArrayList<Card>> result, HashMap<Integer, Card> cards, Card card) {
        if (card.numberOfMatches > 0) {
            int startIdx = card.id + 1;
            int endIdx = card.id + card.numberOfMatches;
            //System.out.println("For card " + card.id + ":");
            for (int j = startIdx; j <= endIdx; j++)  {
                //System.out.println("\tAdding card " + j);
                Card newCard = cards.get(j);
                if (result.containsKey(j)) {
                    result.get(j).add(newCard);
                } else {
                    result.put(j, new ArrayList<>(Collections.singletonList(newCard)));
                }
            }
        }
    }

    private static ArrayList<Integer> extractNumbers(String numberString) {
        return Stream.of(numberString.trim().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}