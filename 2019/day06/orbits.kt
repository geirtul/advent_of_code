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
    if (curr.name == target.name) {
        return count
    } else {
        return getDistance(curr.orbit, target, count+1)
    }
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
