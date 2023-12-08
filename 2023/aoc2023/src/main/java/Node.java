

public class Node {
    String name;
    Node left;
    Node right;

    public Node(String name) {
        this.name = name;
        this.left = null;
        this.right = null;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Name: "
                + name
                + ", Left: "
                + this.left.name
                + ", Right: "
                + this.right.name;
    }
}