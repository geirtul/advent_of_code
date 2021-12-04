import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class Submarine {
    Position position;
    Diagnostic diagnostic;
    int aim;
    ArrayList<int[]> positionLog;
    
    public Submarine() {
        this.position = new Position();
        this.aim = 0;
        this.positionLog = new ArrayList<int[]>();
        this.diagnostic = new Diagnostic();
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
}
