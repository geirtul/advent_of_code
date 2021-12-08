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
        int diffY = 0;
        int diffX = 0;
        int diffXY = 0;
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
            diffY = Math.abs(y2 - y1);
            diffX = Math.abs(x2 - x1);
            diffXY = Math.abs(x1 - y1);
        }
        int[][] linePoints = new int[p2 - p1 + 1][2];
        int pointsIdx = 0;
        if (dir.equals("horizontal")) {
            for (int i = p1; i <= p2; i++) {
                linePoints[pointsIdx] = new int[]{i, y1};
                pointsIdx++;
            }
        }
        if (dir.equals("vertical")) {
            for (int i = p1; i <= p2; i++) {
                linePoints[pointsIdx] = new int[]{x1, i};
                pointsIdx++;
            }
        }
        if (dir.equals("diagonal")) {
            if (x2 > x1 && y2 < y1) { // down right
                for (int i = p1; i <= p2; i++) {
                    linePoints[pointsIdx] = new int[]{i, y1 - pointsIdx};
                    pointsIdx++;
                }
            }
            if (x2 > x1 && y2 > y1) { // up right
                for (int i = p1; i <= p2; i++) {
                    linePoints[pointsIdx] = new int[]{i, y1 + pointsIdx};
                    pointsIdx++;
                }
            }
            if (x2 < x1 && y2 < y1) { // down left
                for (int i = p1; i <= p2; i++) {
                    linePoints[pointsIdx] = new int[]{i, y2 + pointsIdx};
                    pointsIdx++;
                }
            }
            if (x2 < x1 && y2 > y1) {// up left
                for (int i = p1; i <= p2; i++) {
                    linePoints[pointsIdx] = new int[]{i, y2-pointsIdx};
                    pointsIdx++;
                }
            }
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
