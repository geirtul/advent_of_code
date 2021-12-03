import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;

public class Submarine {
    Position position;
    int aim;
    int gammaRate, epsilonRate, powerConsumption;
    ArrayList<int[]> positionLog;
    ArrayList<String> diagnosticReport;
    
    public Submarine() {
        this.position = new Position();
        this.aim = 0;
        this.positionLog = new ArrayList<int[]>();
    }

    public void move(String direction, int distance) {
        /**
         * Move the submarine in one direction. Before the move,
         * store the current position. Flipped z-axis.
         */
        this.positionLog.add(this.position.getCurrentPosition());

        switch (direction) {
            case "forward": this.position.x += distance;
                break;
            case "down": this.position.z += distance;
                break;
            case "up": this.position.z -= distance;
                break;
            default:
                break;
        }
    }
    public void moveWithAim(String direction, int distance) {
        /**
         * Move the submarine in one direction. Before the move,
         * store the current position. Flipped z-axis. Aim included.
         */
        this.positionLog.add(this.position.getCurrentPosition());

        switch (direction) {
            case "forward": 
                this.position.x += distance;
                this.position.z += this.aim * distance;
                break;
            case "down": this.aim += distance;
                break;
            case "up": this.aim -= distance;
                break;
            default:
                break;
        }
    }

    public void writePositionLog(String filename) {
        /**
         * Write the submarines' position log to file as csv.
         */
        try {
            Writer w = new FileWriter(filename);
            for (int[] pos : this.positionLog) {
                String output = String.format("%d,%d,%d\n", pos[0], pos[1], pos[2]);
                w.write(output);
            }
            w.close();
            System.out.format("Wrote positionLog to %s\n", filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processDiagnosticReport() {
        /**
         * Calculate the gamma and epsilon rate from the diagnostic report.
         */
        HashMap<Integer, Integer> counts = new HashMap<Integer, Integer>();
        // init hashmap keys and value counts
        for (int i = 0; i < this.diagnosticReport.get(0).length(); i++) {
            Integer tmp = 0;
            counts.put(Integer.valueOf(i), tmp);
        }
        // Loop over numbers in list and add the digits to it's
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
        System.out.println(gamma.toString());
        System.out.println(epsilon.toString());
        this.gammaRate = Integer.parseInt(gamma.toString(), 2);
        this.epsilonRate = Integer.parseInt(epsilon.toString(), 2);
        this.powerConsumption = this.gammaRate * this.epsilonRate;
    }
}
