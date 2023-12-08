import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day08 implements Day<ArrayList<String>> {

    public static Map<String, Node> parseGraph(List<String> input) {
        Map<String, Node> nodesMap = new HashMap<>();

        for (String line : input) {
            String[] parts = line.split(" = ");
            String nodeName = parts[0].trim();
            String[] connections = parts[1].replaceAll("[() ]", "").split(",");

            Node node = nodesMap.computeIfAbsent(nodeName, Node::new);

            for (String connection : connections) {
                Node connectedNode = nodesMap.computeIfAbsent(connection, Node::new);
                if (node.left == null) {
                    node.setLeft(connectedNode);
                } else {
                    node.setRight(connectedNode);
                }
            }
        }

        return nodesMap;
    }
    @Override
    public void solveOne(ArrayList<String> input) {

        char[] instructions = input.get(0).toCharArray();
        Map<String, Node> nodesMap = parseGraph(input.subList(2, input.size()));

        // Traverse the nodes from AAA to ZZZ
        Node currNode = nodesMap.get("AAA");
        int currInstruction = 0;
        int count = 0;
        while (!currNode.name.equals("ZZZ")) {
            if (instructions[currInstruction] == 'L') {
                currNode = currNode.left;
            } else {
                currNode = currNode.right;
            }

            count++;
            currInstruction = currInstruction < instructions.length - 1 ? currInstruction + 1 : 0;
            if (currNode.name.equals("ZZZ")) {
                break;
            }
        }

        System.out.println("Solving part one: Number of steps from AAA to ZZZ = " + count);

    }

    @Override
    public void solveTwo(ArrayList<String> input) {

        char[] instructions = input.get(0).toCharArray();
        Map<String, Node> nodesMap = parseGraph(input.subList(2, input.size()));

        ArrayList<Node> endsWithA = new ArrayList<>(nodesMap.values().stream()
                .filter(n -> n.name.endsWith("A"))
                .toList());

        long[] counts = new long[endsWithA.size()];
        for (int i = 0; i < endsWithA.size(); i++) {
            Node currNode = endsWithA.get(i);
            int count = 0;
            int currInstruction = 0;
            while (!currNode.name.endsWith("Z")) {
                if (instructions[currInstruction] == 'L') {
                    currNode = currNode.left;
                } else {
                    currNode = currNode.right;
                }

                count++;
                currInstruction = currInstruction < instructions.length - 1 ? currInstruction + 1 : 0;
            }
            counts[i] = count;
        }

        System.out.println("Solving part two: Number of steps from AAA to ZZZ = " + LCMCalculator.calculateLCM(counts));

    }

    @Override
    public ArrayList<String> testInput() {
        ArrayList<String> output = new ArrayList<>();
        output.add("RL");
        output.add("");
        output.add("AAA = (BBB, CCC)");
        output.add("BBB = (DDD, EEE)");
        output.add("CCC = (ZZZ, GGG)");
        output.add("DDD = (DDD, DDD)");
        output.add("EEE = (EEE, EEE)");
        output.add("GGG = (GGG, GGG)");
        output.add("ZZZ = (ZZZ, ZZZ)");

        return output;
    }

    public ArrayList<String> testInputTwo() {
        ArrayList<String> output = new ArrayList<>();
        output.add("LLR");
        output.add("");
        output.add("AAA = (BBB, BBB)");
        output.add("BBB = (AAA, ZZZ)");
        output.add("ZZZ = (ZZZ, ZZZ)");

        return output;
    }

    public ArrayList<String> testInputThree() {
        ArrayList<String> output = new ArrayList<>();
        output.add("LR");
        output.add("");
        output.add("11A = (11B, XXX)");
        output.add("11B = (XXX, 11Z)");
        output.add("11Z = (11B, XXX)");
        output.add("22A = (22B, XXX)");
        output.add("22B = (22C, 22C)");
        output.add("22C = (22Z, 22Z)");
        output.add("22Z = (22B, 22B)");
        output.add("XXX = (XXX, XXX)");

        return output;
    }

        public static void main(String[] args) {
            Day08 day08 = new Day08();

            day08.solveOne(day08.testInput());
            day08.solveOne(day08.testInputTwo());
            ArrayList<String> fileInput = InputReader.lineByLine("input/day08");
            day08.solveOne(fileInput);

            day08.solveTwo(day08.testInputThree());
            day08.solveTwo(fileInput);

        }
    }
