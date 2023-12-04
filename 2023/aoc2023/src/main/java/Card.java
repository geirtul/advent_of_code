import java.util.ArrayList;

public class Card {
    int id;
    ArrayList<Integer> winningNumbers;
    ArrayList<Integer> candidateNumbers;
    int points;
    int numberOfMatches;

    Card(int id, ArrayList<Integer> winningNumbers, ArrayList<Integer> candidateNumbers) {
        this.id = id;
        this.winningNumbers = winningNumbers;
        this.candidateNumbers = candidateNumbers;
    }

    @Override
    public String toString() {
        return "Card Number: " + id +
                ", Winning Numbers: " + winningNumbers +
                ", Candidate Numbers: " + candidateNumbers +
                ", Points: " + points +
                ", Matches: " + numberOfMatches;
    }
}
