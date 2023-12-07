import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardHand implements Comparable<CardHand> {

    private List<Character> cards;
    private long bet;
    private String handType;
    private Map<Character, Integer> counts;

    public CardHand(List<Character> cards, long bet) {
        this.cards = cards;
        this.bet = bet;
        countCharacters();
        determineHandType();
    }

    public void countCharacters() {
        Map<Character, Integer> characterCountMap = new HashMap<>();

        for (char c : this.cards) {
            // If the character is already in the map, increment its count
            if (characterCountMap.containsKey(c)) {
                characterCountMap.put(c, characterCountMap.get(c) + 1);
            } else {
                // If the character is not in the map, add it with count 1
                characterCountMap.put(c, 1);
            }
        }

        this.counts = characterCountMap;

    }

    public void determineHandType() {
        if (this.counts.size() == 1) {
            setHandType("five"); //Five of a kind
        } else if (this.counts.size() == 2) {
            // Either full house or four of a kind (four)
            if (this.counts.containsValue(4)) {
                setHandType("four");
            } else {
                setHandType("house");
            }
        } else if (this.counts.size() == 3) {
            // either three of a kind or two pairs
            if (this.counts.containsValue(3)) {
                setHandType("three");
            } else {
                setHandType("pairs");
            }
        } else if (this.counts.size() == 4) {
            setHandType("pair"); // One pair
        } else {
            setHandType("high"); // high card
        }
    }

    public List<Character> getCards() {
        return cards;
    }

    public void setCards(List<Character> cards) {
        this.cards = cards;
    }

    public long getBet() {
        return bet;
    }

    public void setBet(long bet) {
        this.bet = bet;
    }

    public String getHandType() {
        return handType;
    }

    public void setHandType(String handType) {
        this.handType = handType;
    }

    public Map<Character, Integer> getCounts() {
        return counts;
    }

    public void setCounts(Map<Character, Integer> counts) {
        this.counts = counts;
    }

    @Override
    public String toString() {
        return "CardHand{" +
                "cards=" + cards +
                ", bet=" + bet +
                ", handType='" + handType + '\'' +
                ", counts=" + counts +
                '}';
    }

    @Override
    public int compareTo(CardHand otherHand) {
        // Compare by handType first
        int handTypeComparison = compareHandTypes(this.handType, otherHand.getHandType());
        if (handTypeComparison != 0) {
            return handTypeComparison;
        }

        // If handTypes are equal, compare by high card
        return compareHighCards(this.cards, otherHand.getCards());
    }

    private int compareHandTypes(String handType1, String handType2) {
        // Implement a custom comparison for handTypes
        // Adjust the order based on your requirements
        String[] handTypeOrder = {"five", "four", "house", "three", "pairs", "pair", "high"};
        int index1 = getIndex(handTypeOrder, handType1);
        int index2 = getIndex(handTypeOrder, handType2);

        return Integer.compare(index1, index2);
    }

    private int compareHighCards(List<Character> cards1, List<Character> cards2) {
        // Implement a custom comparison for high cards
        // Adjust the order based on your requirements
        Character[] highCardOrder = {'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2'};

        for (int i = 0; i < cards1.size(); i++) {
            char card1 = cards1.get(i);
            char card2 = cards2.get(i);

            int index1 = getIndex(highCardOrder, card1);
            int index2 = getIndex(highCardOrder, card2);

            int comparison = Integer.compare(index1, index2);
            if (comparison != 0) {
                return comparison;
            }
        }

        return 0; // Both hands are equal
    }

    private <T> int getIndex(T[] array, T element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(element)) {
                return i;
            }
        }
        return -1; // Element not found
    }
}