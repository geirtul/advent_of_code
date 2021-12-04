import java.util.ArrayList;
import java.util.HashMap;

/**
 * Helper class for Submarine to handle diagnostics reports.
 */
public class Diagnostic {
    int gammaRate, epsilonRate, powerConsumption;
    int oxygenGeneratorRating, co2ScrubberRating, lifeSupportRating;
    ArrayList<String> diagnosticReport;

    public void loadDiagnosticReport(String filename) {
        /**
         * Read the diagnosics report file and store it as arraylist.
         */
        this.diagnosticReport = Reader.readLines(filename);
    }

    public void calculatePowerConsumption() {
        /**
         * Calculate the gamma and epsilon rate from the diagnostic report,
         * and form those calculate power consumption.
         */

        HashMap<Integer, Integer> counts = new HashMap<Integer, Integer>();
        // init hashmap keys and value counts
        for (int i = 0; i < this.diagnosticReport.get(0).length(); i++) {
            Integer tmp = 0;
            counts.put(Integer.valueOf(i), tmp);
        }
        // Loop over numbers in list and add the digits to its
        // corresponding key
        for (String number  : this.diagnosticReport) {
            int digit;
            for (int i = 0; i < number.length(); i++) {
                digit = Integer.parseInt(String.valueOf(number.charAt(i)));
                counts.put(i, counts.get(i) + digit);
            }
        }
        StringBuilder gamma = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();
        int check = this.diagnosticReport.size()/2;
        for (int i = 0; i < counts.keySet().size(); i++) {
            // if more ones than zeros, else
            if (counts.get(i) > check) {
                gamma.append("1");
                epsilon.append("0");
            } else {
                gamma.append("0");
                epsilon.append("1");
            }
        }

        // Set powerConsumption details
        this.gammaRate = Integer.parseInt(gamma.toString(), 2);
        this.epsilonRate = Integer.parseInt(epsilon.toString(), 2);
        this.powerConsumption = this.gammaRate * this.epsilonRate;
    }

    public void calculateLifeSupportRating() {
        /**
         * Calculate life support rating based on oxygen generator rating
         * and CO2 scrubber rating.
         */

        HashMap<Integer, Integer> counts = new HashMap<Integer, Integer>();
        // init hashmap keys and value counts
        for (int i = 0; i < this.diagnosticReport.get(0).length(); i++) {
            Integer tmp = 0;
            counts.put(Integer.valueOf(i), tmp);
        }

        // Loop over numbers in list and add the digits to it's
        // corresponding key
        for (int i = 0; i < this.diagnosticReport.get(0).length(); i++) {
            int digit;
            for (String number  : this.diagnosticReport) {
                digit = Integer.parseInt(String.valueOf(number.charAt(i)));
                counts.put(i, counts.get(i) + digit);
            }
        }
        StringBuilder gamma = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();
        int check = this.diagnosticReport.size()/2;
        for (int i = 0; i < counts.keySet().size(); i++) {
            // if more ones than zeros, else
            if (counts.get(i) > check) {
                gamma.append("1");
                epsilon.append("0");
            } else {
                gamma.append("0");
                epsilon.append("1");
            }
        }

        // Set powerConsumption details
        this.oxygenGeneratorRating = Integer.parseInt(gamma.toString(), 2);
        this.co2ScrubberRating = Integer.parseInt(epsilon.toString(), 2);
        this.lifeSupportRating = this.oxygenGeneratorRating * this.co2ScrubberRating;
    }

    private void getMostCommonDigit(ArrayList<String> array, int position) {
        /**
         * Get the most common digit at <position> across a section of a diagnostic report.
         */

        
    }
    
}
