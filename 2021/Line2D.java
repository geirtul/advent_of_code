import java.util.ArrayList;
import java.util.Arrays;

public class Line2D {
    int[][] points;

    public Line2D(int[] start, int[] end){
        this.points = generatePoints(start, end);
    }


    public int[][] generatePoints(int[] start, int[] end) {
        /**
         * Generate the points on the line between <start> and <end>
         */

        int sizeX = Math.abs(end[0] - start[0]);
        int sizeY = Math.abs(end[1] - start[1]);
        int[][] points = new int[sizeX + sizeY + 1][2];
        int idx = 0;
        for (int i = start[0]; i <= end[0]; i++) {
            for (int j = start[1]; j <= end[1]; j++) {
                points[idx] = new int[]{i, j};
                idx++;
            }
        }

        return points;
    }

    public ArrayList<Integer> overlappingPoint(Line2D other) {
        /**
         * Generate a list of indices for points on this.line which
         * overlap point on <other>.
         */

        ArrayList<Integer> overlap = new ArrayList<Integer>();
        for (int i = 0; i < this.points.length; i++) {
            for (int j = 0; j < other.points.length; j++) {
                if (this.points[i][0] == other.points[j][0] && this.points[i][1] == other.points[j][1]) {
                    overlap.add(i);
                }                
            }
            
        }

        return overlap;

    }

    public void printLine() {
        System.out.println(Arrays.deepToString(points));
    }
}
