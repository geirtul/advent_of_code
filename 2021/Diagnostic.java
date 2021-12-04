import java.util.ArrayList;

/**
 * Helper class for Submarine to handle diagnostics reports.
 */
public class Diagnostic {
    int gammaRate, epsilonRate, powerConsumption;
    int oxygenGeneratorRating, co2ScrubberRating, lifeSupportRating;
    ArrayList<char[]> diagnosticReport;

    public void loadDiagnosticReport(String filename) {
        /**
         * Read the diagnosics report file and store it as arraylist.
         */

        this.diagnosticReport = Reader.readCharArray(filename);
    }

    public void calculatePowerConsumption() {
        /**
         * Calculate the gamma and epsilon rate from the diagnostic report,
         * and form those calculate power consumption.
         */

        // Loop over numbers in list and add the digits to it's
        // corresponding key
        StringBuilder gamma = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();
        
        int mostCommon;
        for (int i = 0; i < this.diagnosticReport.get(0).length; i++) {
            mostCommon = mostCommonColumnDigit(this.diagnosticReport, i);
            gamma.append(Integer.toString(mostCommon));
            epsilon.append(Integer.toString(Math.abs(mostCommon - 1)));
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
        ArrayList<char[]> most = findOxygenGeneratorRating(this.diagnosticReport, 0);
        ArrayList<char[]> least = findCO2ScrubberRating(this.diagnosticReport, 0);
        this.oxygenGeneratorRating = Integer.parseInt(String.valueOf(most.get(0)), 2);
        this.co2ScrubberRating = Integer.parseInt(String.valueOf(least.get(0)), 2);
        this.lifeSupportRating = this.oxygenGeneratorRating * this.co2ScrubberRating;
    }

    private ArrayList<char[]> findOxygenGeneratorRating(ArrayList<char[]> inList, int position) {
        /**
         * Follow the list of most common elements in diagnostic report
         * until the length of the array is 1.
         */

        
        if (inList.size() == 1) {
            return inList;
        }

        ArrayList<char[]> currentMostCommon = new ArrayList<char[]>();
        int mostCommonDigit = mostCommonColumnDigit(inList, position);
        for (char[] ca: inList) {
            if (Character.getNumericValue(ca[position]) == mostCommonDigit) {
                currentMostCommon.add(ca);
            }
        }
        return findOxygenGeneratorRating(currentMostCommon, position + 1);
    }

    private ArrayList<char[]> findCO2ScrubberRating(ArrayList<char[]> inList, int position) {
        /**
         * Follow the list of most common elements in diagnostic report
         * until the length of the array is 1.
         */
        if (inList.size() == 1) {
            return inList;
        }

        ArrayList<char[]> currentLeastCommon = new ArrayList<char[]>();
        int leastCommonDigit = leastCommonColumnDigit(inList, position);
        for (char[] ca: inList) {
            if (Character.getNumericValue(ca[position]) == leastCommonDigit) {
                currentLeastCommon.add(ca);
            }
        }
        return findCO2ScrubberRating(currentLeastCommon, position + 1);
    }

    public int mostCommonColumnDigit(ArrayList<char[]> inList, int position) {
        /**
         * Get the most common digit at <position> across a section of a diagnostic report.
         */
        int count = 0;
        for (char[] number  : inList) {
            count += Character.getNumericValue(number[position]);
        }
        if (count >= (int)Math.ceil((double)inList.size()/2)) {
            return 1;
        } else {
            return 0;
        }
    }
    
    public int leastCommonColumnDigit(ArrayList<char[]> inList, int position) {
        /**
         * Get the least common digit at <position> in an arraylist of char arrays.
         */
        int count = 0;
        for (char[] number  : inList) {
            count += Character.getNumericValue(number[position]);
        }
        if (count < (int)Math.ceil((double)inList.size()/2)) {
            return 1;
        } else {
            return 0;
        }
    }
}
