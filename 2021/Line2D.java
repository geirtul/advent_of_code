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

        int x1, y1, x2, y2;
        x1 = start[0];
        y1 = start[1];
        x2 = end[0];
        y2 = end[1];
        int sizeX = Math.abs(x2 - x1);
        int sizeY = Math.abs(y2 - y1);
        int[][] points = new int[Math.max(sizeX, sizeY) + 1][2];
        int idxStart = 0;
        if (sizeX < sizeY){
            idxStart = 1;
        }
        int p1 = Math.min(start[idxStart], end[idxStart]);
        int p2 = Math.max(start[idxStart], end[idxStart]);
        int pointsIdx = 0;
        for (int i = p1; i <= p2; i++) {
            if (sizeX > sizeY) {
                points[pointsIdx] = new int[]{i, end[1]};
            } else if (sizeY > sizeX){
                points[pointsIdx] = new int[]{end[0], i};
            } else {
                points[pointsIdx] = new int[]{i, i};
            }
            pointsIdx++;
        }

        return points;
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
