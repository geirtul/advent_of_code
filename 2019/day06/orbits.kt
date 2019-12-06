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

        // Check if node 1 exist in tree
        var found_1 = 0
        if (getNode(nodes[1], tree).name != "notfound") {
            found_1 = 1
        }
        // Check if node 0 exists in tree
        var found_0 = 0
        if (getNode(nodes[0], tree).name != "notfound") {
            found_0 = 1
        }

        // If node1 wasn't found, make a new node
        if (found_1 == 0) {
            var node1 = Node(nodes[1])
            tree.add(node1)
        }

        // If we didn't find node 0, make that one too
        if (found_0 == 0) {
            var node0 = Node(nodes[0])
            tree.add(node0)
        }

        // Now we know both nodes exists
        var n0 = getNode(nodes[0], tree)
        var n1 = getNode(nodes[1], tree)
        n1.orbit = n0

        // set orbit of COM
        var n_com = Node("nothing")
        getNode("COM", tree).orbit = n_com
    }
    return tree
}

fun getNode(searchname: String, tree: MutableList<Node>): Node {
    for (n in tree) {
        if (n.name == searchname) {
            return n
        }
    }
    // Didn't find, return a 'null' node
    var notfound = Node("notfound")
    return notfound
}

fun DFS(node: Node, primary: Node) {
    // Calculate number of orbits for a given node in the tree using
    // depth first search
    if (node.name != "COM"){
        primary.orbitcount++
        DFS(node.orbit, primary)
    }
}

fun DFSOrbitedBy(node: Node, orbited: MutableList<Node>) {
    // Set orbited_by for each node using depth first search
    if (orbited.size != 0) {
        node.orbited_by = orbited.toMutableList()
    }

    if (node.name != "COM") {
        if (getNode(node.name) != "notfound") {
            orbited.add(node)
        }
        DFSOrbitedBy(node.orbit, orbited)
    }
}

fun main(args: Array<String>) {
    val file = File("input.txt")
    var orbit_list: List<String> = file.readLines()
    var universe = buildTree(orbit_list)

    var tot = 0
    for (p in universe) {
        var orbited_by_list: MutableList<Node> = mutableListOf()
        DFS(p, p)
        DFSOrbitedBy(p, orbited_by_list)
        tot += p.orbitcount
        print("Orbited by:")
        print(p.orbited_by)
        print("\n")
    }
    print("Total number of orbits: $tot\n")
}
