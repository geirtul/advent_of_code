import java.util.ArrayList;
import java.util.Arrays;

public class Line2D {
    int[][] points;

    public Line2D(int[] points){
        this.points = generatePoints(points);
    }


    public int[][] generatePoints(int[] points) {
        /**
         * Generate the points on the line between <start> and <end>
         */

        int x1, y1, x2, y2;
        x1 = points[0];
        y1 = points[1];
        x2 = points[2];
        y2 = points[3];

        // Determine line direction
        String dir;
        int p1, p2;
        int diff = 0;
        if (x1 != x2 && y1 == y2) {
            dir = "horizontal";
            p1 = Math.min(x1, x2);
            p2 = Math.max(x1, x2);
        } else if (x1 == x2 && y1 != y2) {
            dir = "vertical";
            p1 = Math.min(y1, y2);
            p2 = Math.max(y1, y2);
        } else {
            dir = "diagonal";
            p1 = Math.min(x1, x2);
            p2 = Math.max(x1, x2);
            diff = y1 - x1;
        }
        int[][] linePoints = new int[p2 - p1 + 1][2];
        int pointsIdx = 0;
        for (int i = p1; i <= p2; i++) {
            if (dir.equals("horizontal")) {
                linePoints[pointsIdx] = new int[]{i, y1};
            } else if (dir.equals("vertical")) {
                linePoints[pointsIdx] = new int[]{x1, i};
            } else {
                if (y1 < x1) {
                    linePoints[pointsIdx] = new int[]{i, p2-pointsIdx};
                } else if (x1 < y1) {
                    linePoints[pointsIdx] = new int[]{i, i + diff};
                } else {
                    linePoints[pointsIdx] = new int[]{i, i};
                }
            }
            pointsIdx++;
        }

        return linePoints;
    }

    public ArrayList<Integer> overlappingPoints(Line2D other) {
        /**
         * Generate a list of indices for points on this.line which
         * overlap point on <other>.
         */

        ArrayList<Integer> overlap = new ArrayList<Integer>();
        for (int i = 0; i < this.points.length; i++) {
            for (int j = 0; j < other.points.length; j++) {
                int x1, x2, y1, y2;
                x1 = this.points[i][0];
                y1 = this.points[i][1];
                x2 = other.points[j][0];
                y2 = other.points[j][1];
                if (x1 == x2 && y1 == y2) {
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
