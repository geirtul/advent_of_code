import java.util.*;

public class Almanac {
    public Map<String, List<List<Long>>> parseInput(List<String> inputList) {
        Map<String, List<List<Long>>> almanacMap = new HashMap<>();
        String currentTitle = null;

        // Get seeds
        almanacMap.put("seeds", List.of(extractSeeds(inputList.get(0))));

        // Get each remaining block of info.
        for (int i = 2; i < inputList.size(); i++) {
            String line = inputList.get(i);
            if (line.isEmpty()) {
                // End of block, reset title and move on
                if (currentTitle != null) {
                    currentTitle = null;
                }
            } else {
                // New block, parse title and add arraylist to map
                if (currentTitle == null) {
                    currentTitle = line.replaceAll(" map:", "");
                    almanacMap.put(currentTitle, new ArrayList<>());
                } else {
                    // Add the elements to the current block
                    almanacMap.get(currentTitle).add(extractNumbers(line));
                }
            }
        }

        return almanacMap;
    }

    // Helper method to extract seeds from the first line
    private List<Long> extractSeeds(String line) {
        List<Long> numbers = new ArrayList<>();
        String[] parts = line.split(":")[1].trim().split("\\s+");
        for (String part : parts) {
            numbers.add(Long.parseLong(part));
        }
        return numbers;
    }

    // Helper method to extract numbers from a line
    private List<Long> extractNumbers(String line) {
        List<Long> numbers = new ArrayList<>();
        String[] parts = line.split("\\s+");
        for (String part : parts) {
            numbers.add(Long.parseLong(part));
        }
        return numbers;
    }

    public long sourceToDestination(long source, List<List<Long>> destinationList) {
        for (List<Long> destMap: destinationList) {
            long destinationRangeStart = destMap.get(0);
            long sourceRangeStart = destMap.get(1);
            long rangeLength = destMap.get(2);

            // Check if the source is within the range
            if (source >= sourceRangeStart && source < sourceRangeStart + rangeLength) {
                return destinationRangeStart + (source - sourceRangeStart);
            }
        }
        return source;
    }

    private List<String> createTitlesList() {
        List<String> titlesList = new ArrayList<>();
        titlesList.add("seed-to-soil");
        titlesList.add("soil-to-fertilizer");
        titlesList.add("fertilizer-to-water");
        titlesList.add("water-to-light");
        titlesList.add("light-to-temperature");
        titlesList.add("temperature-to-humidity");
        titlesList.add("humidity-to-location");
        return titlesList;
    }

    public long seedToLocation(long seed, Map<String, List<List<Long>>> destinationMap) {
        long curr_source = seed;
        for (String title: createTitlesList()) {
            curr_source = sourceToDestination(curr_source, destinationMap.get(title));
        }
        return curr_source;
    }

    public List<Long> seedRangeToSeeds(List<Long> originalSeeds) {
        List<Long> newSeeds = new ArrayList<>();
        for (int i = 0; i < originalSeeds.size() - 1; i += 2) {
            long startSeed = originalSeeds.get(i);
            long currSeed = startSeed;
            long range = originalSeeds.get(i+1);
            while (currSeed < startSeed + range - 1) {
                newSeeds.add(currSeed);
                currSeed++;
            }
        }
        return newSeeds;
    }
}
