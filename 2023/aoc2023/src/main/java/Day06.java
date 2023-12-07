import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day06 implements Day<ArrayList<String>>{

    private List<Integer> parseValues(String line) {

        // Convert the tokens to integers
        return Arrays.stream(line.trim().split("\\s+"))
                .skip(1)
                .map(Integer::parseInt)
                .toList();
    }

    public List<Long> calculateWinningStrategies(long time, long distance) {
        List<Long> winningVelocities = new ArrayList<>();
        long velocity = 1;
        long timeLeft = time - velocity;
        while (velocity < time - 1 && timeLeft > 0) {
            if (velocity * timeLeft > distance){
                winningVelocities.add(velocity);
            }
            velocity++;
            timeLeft--;
        }

        return winningVelocities;
    }



    @Override
    public void solveOne(ArrayList<String> input) {
        List<Integer> time = parseValues(input.get(0));
        List<Integer> distance = parseValues(input.get(1));

        long result = 1;
        for (int i = 0; i < time.size(); i++) {
            List<Long> wins = calculateWinningStrategies(time.get(i), distance.get(i));
            result *= wins.size();
        }
        System.out.println("Solving part one: " + result);
    }

    @Override
    public void solveTwo(ArrayList<String> input) {
        long time = Long.parseLong(parseValues(input.get(0)).stream()
                .map(Object::toString)
                .collect(Collectors.joining()));
        long distance = Long.parseLong(parseValues(input.get(1)).stream()
                .map(Object::toString)
                .collect(Collectors.joining()));

        long result = calculateWinningStrategies(time, distance).size();
        System.out.println("Solving part two: " + result);
    }

    public ArrayList<String> testInput() {
        return new ArrayList<>(List.of(
                "Time:      7  15   30",
                "Distance:  9  40  200"
        ));
    }

    public static void main(String[] args) {
        Day06 day06 = new Day06();
        ArrayList<String> fileInput = InputReader.lineByLine("input/day06");
        ArrayList<String> testInput = day06.testInput();

        day06.solveOne(testInput);
        day06.solveOne(fileInput);

        day06.solveTwo(testInput);
        day06.solveTwo(fileInput);

    }
}

