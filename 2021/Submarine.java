public class Submarine {
    Position position;

    public Submarine() {
        this.position = new Position();
    }

    public void move(String direction, Integer distance) {
        // We're using a flipped z-axis to have "down" being positive z
        switch (direction) {
            case "forward": position.update("x", distance);
                break;
            case "down": position.update("z", distance);
                break;
            case "up": position.update("z", -distance);
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        Submarine sub = new Submarine();
    }
}