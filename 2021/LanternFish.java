import java.util.ArrayList;

public class LanternFish {
    int stage;
    ArrayList<LanternFish> children;

    public LanternFish(int stage){
        this.stage = stage;
        this.children = new ArrayList<LanternFish>();
    }

    public void decrementStage() {
        /**
         * Decrement the stage of this LanternFish.
         */
        boolean spawnFish = false;
        if (this.stage == 0) {
            this.stage = 6;
            spawnFish = true;
        } else {
            this.stage--;
            this.decrementChildren();
        }
        if (spawnFish) {
            this.children.add(new LanternFish(8));
        }
        // System.out.format(",%d", this.stage);
    }

    public void decrementChildren() {
        /**
         * Decrement stages of children of this LanternFish.
         */
        for (LanternFish fish : this.children) {
            fish.decrementStage();
        }
    }

    public int countChildren() {
        /**
         * recursively count children of this fish
         */
        int count = this.children.size();
        for (LanternFish fish : this.children) {
            count += fish.countChildren();
        }
        return count;
    }
}
