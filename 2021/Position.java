public class Position {
    int x, y, z;
    public Position() {
        // Default to origo
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public double distanceFromOrigo() {
        /**
         * Calculate the distance from origo.
         */
        double distance = Math.sqrt(
            Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2)
        );
        return distance;
    }

    public double distanceFromPoint(int x, int y, int z) {
        /**
         * Calculate the distance from the given point x, y, z.
         */
        double distance = Math.sqrt(
            Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2) + Math.pow(this.z - z, 2)
        );
        return distance;
    }
}
