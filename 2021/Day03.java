import java.util.ArrayList;

public class Day03 {

    public static void solveOne(String filename) {
        ArrayList<String> diagnosticReport = Reader.readLines(filename);
        Submarine sub = new Submarine();
        sub.diagnosticReport = diagnosticReport;
        sub.processDiagnosticReport();
        System.out.format("Part 1: %d\n", sub.powerConsumption);
    }

    public static void solveTwo(String filename) {
        //System.out.format("Part 2: %d\n", solution);
    }

    public static void main(String[] args) {
        solveOne(args[0]);
        //solveTwo(args[0]);
    }
    
}
