import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Day05 implements Day<List<String>> {
    @Override
    public void solveOne(List<String> input) {
        Almanac almanac = new Almanac();
        Map<String, List<List<Long>>> maps = almanac.parseInput(input);
        long min_location = Long.MAX_VALUE;
        for (Long seed : maps.get("seeds").get(0)) {
            long location = almanac.seedToLocation(seed, maps);
            if (location < min_location) {
                min_location = location;
            }
        }
        System.out.println("Solving part one: Lowest location number is " + min_location);
    }

    @Override
    public void solveTwo(List<String> input) {
        Almanac almanac = new Almanac();
        Map<String, List<List<Long>>> maps = almanac.parseInput(input);
        maps.put("seeds", List.of(almanac.seedRangeToSeeds(maps.get("seeds").get(0))));
        long min_location = Long.MAX_VALUE;
        for (Long seed : maps.get("seeds").get(0)) {
            long location = almanac.seedToLocation(seed, maps);
            if (location < min_location) {
                min_location = location;
            }
        }
        System.out.println("Solving part two: Lowest location number is " + min_location);
    }

    public List<String> testInput() {
        // Example input
        List<String> inputList = new ArrayList<>();
        inputList.add("seeds: 79 14 55 13");
        inputList.add("");
        inputList.add("seed-to-soil map:");
        inputList.add("50 98 2");
        inputList.add("52 50 48");
        inputList.add("");
        inputList.add("soil-to-fertilizer map:");
        inputList.add("0 15 37");
        inputList.add("37 52 2");
        inputList.add("39 0 15");
        inputList.add("");
        inputList.add("fertilizer-to-water map:");
        inputList.add("49 53 8");
        inputList.add("0 11 42");
        inputList.add("42 0 7");
        inputList.add("57 7 4");
        inputList.add("");
        inputList.add("water-to-light map:");
        inputList.add("88 18 7");
        inputList.add("18 25 70");
        inputList.add("");
        inputList.add("light-to-temperature map:");
        inputList.add("45 77 23");
        inputList.add("81 45 19");
        inputList.add("68 64 13");
        inputList.add("");
        inputList.add("temperature-to-humidity map:");
        inputList.add("0 69 1");
        inputList.add("1 0 69");
        inputList.add("");
        inputList.add("humidity-to-location map:");
        inputList.add("60 56 37");
        inputList.add("56 93 4");

        return inputList;
    }

    public static void main(String[] args) {
        Day05 day05 = new Day05();
        List<String> testInputs = day05.testInput();
        List<String> fileInputs = InputReader.lineByLine("input/day05");

        day05.solveOne(testInputs);
        //day05.solveOne(fileInputs);
        day05.solveTwo(testInputs);
        day05.solveTwo(fileInputs);



    }
}
