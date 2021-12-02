public class Position {
    int x, y, z;
    public Position() {
        // Default to origo
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public void update(String direction, int distance) {
        switch (direction) {
            case "x": this.x += distance;
                break;
            case "y": this.y += distance;
                break;
            case "z": this.z += distance;
                break;
            default:
                break;
        }
    }

    public double distanceFromOrigo() {
        double distance = Math.sqrt(
            Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2)
        );
        return distance;
    }

    public double distanceFromPoint(int x, int y, int z) {
        double distance = Math.sqrt(
            Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2) + Math.pow(this.z - z, 2)
        );
        return distance;
    }
}
