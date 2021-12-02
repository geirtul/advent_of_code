public class Submarine {
    Position position;
    int aim;

    public Submarine() {
        this.position = new Position();
        this.aim = 0;
    }

    public void move(String direction, int distance) {
        // We're using a flipped z-axis to have "down" being positive z
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
        // We're using a flipped z-axis to have "down" being positive z
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
}