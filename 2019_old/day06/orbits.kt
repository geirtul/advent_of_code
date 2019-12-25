import java.io.File

// Day six of advent of code 2019

class Node(name: String) {
    val name = name
    lateinit var orbit: Node
    var orbited_by: MutableList<Node> = mutableListOf()
    var orbitcount = 0
}

fun buildTree(orbit_list: List<String>): MutableList<Node> {
    // Builds a list of objects with its direct orbit
    var tree: MutableList<Node> = mutableListOf()

    // Create nodes
    for (orb in orbit_list) {
        val nodes = orb.split(")")

        // Check if nodes exist in tree
        var found_0 = 0
        var found_1 = 0
        if (getNode(nodes[0], tree).name != "notfound") found_0 = 1
        if (getNode(nodes[1], tree).name != "notfound") found_1 = 1

        // Make new nodes as needed
        if (found_0 == 0) tree.add(Node(nodes[0]))
        if (found_1 == 0) tree.add(Node(nodes[1]))

        // Populate orbit of node 1
        getNode(nodes[1], tree).orbit = getNode(nodes[0], tree)
    }
    // set orbit of COM to recognizable node
    getNode("COM", tree).orbit = Node("nothing")
    return tree
}

fun getNode(searchname: String, tree: MutableList<Node>): Node {
    for (n in tree) {
        if (n.name == searchname) return n
    }
    // If not found, return node with recognizable name
    return Node("notfound")
}

fun DFS(node: Node, primary: Node) {
    // Calculate number of orbits for a given node in the tree using
    // depth first search
    if (node.name != "COM"){
        primary.orbitcount++
        DFS(node.orbit, primary)
    }
}

fun DFSOrbitedBy(curr_n: Node, orbited: MutableList<Node>) {
    // Set orbited_by for each node using depth first search
    if (curr_n.name != "COM") {
        for (n in orbited) {
            if (getNode(n.name, curr_n.orbited_by).name == "notfound") {
                curr_n.orbited_by.add(n)
            }
        }
        orbited.add(curr_n)
        DFSOrbitedBy(curr_n.orbit, orbited)
    } else {
        for (n in orbited) {
            if (getNode(n.name, curr_n.orbited_by).name == "notfound") {
                curr_n.orbited_by.add(n)
            }
        }
    }
}

fun getRootDFS(curr: Node, target: Node): Node{
    // Find the first node orbited by first curr when searching
    // depth first from start node
    for (n in curr.orbited_by) {
        if (n.name == target.name) return curr
    }
    return getRootDFS(curr.orbit, target)
}

fun getDistance(curr: Node, target: Node, count: Int): Int {
    // Calc distance from first curr node to target node
    if (curr.name == target.name) return count
    else return getDistance(curr.orbit, target, count+1)
}

fun main(args: Array<String>) {
    if (args.size == 0) {
        println("Provide filname")
        return
    }
    val filename = args[0]
    val file = File(filename)
    var orbit_list: List<String> = file.readLines()
    var universe = buildTree(orbit_list)

    var tot = 0
    for (p in universe) {
        DFS(p, p)
        tot += p.orbitcount
    }
    print("Part 1 total number of orbits: $tot\n")
    print("Starting part 2 (slow with large input)...\n")
    for (p in universe) {
        var orbited_by_list: MutableList<Node> = mutableListOf()
        DFSOrbitedBy(p, orbited_by_list)
    }
    var you = getNode("YOU", universe)
    var santa = getNode("SAN", universe)
    var root = getRootDFS(you, santa)
    var a = getDistance(you, root, -1)
    var b = getDistance(santa, root, -1)
    print("Part 2: Minimum orbital switches = ${a+b}\n")
}
