public class Day03 {

    public static void solveOne(String filename) {
        Submarine sub = new Submarine();
        sub.diagnostic.loadDiagnosticReport(filename);
        sub.diagnostic.calculatePowerConsumption();
        System.out.format("Part 1: %d\n", sub.diagnostic.powerConsumption);
    }

    public static void solveTwo(String filename) {
        Submarine sub = new Submarine();
        sub.diagnostic.loadDiagnosticReport(filename);
        sub.diagnostic.calculateLifeSupportRating();
        System.out.format("Part 2: %d\n", sub.diagnostic.oxygenGeneratorRating);
        System.out.format("Part 2: %d\n", sub.diagnostic.co2ScrubberRating);
        System.out.format("Part 2: %d\n", sub.diagnostic.lifeSupportRating);
    }

    public static void main(String[] args) {
        solveOne(args[0]);
        solveTwo(args[0]);
    }
    
}
