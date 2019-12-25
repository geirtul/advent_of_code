import java.io.File
import kotlin.math.abs
// Day three of advent of code 2019
fun wireCoordinates(wire: String): MutableList<List<Int>> {
    val commands = wire.trim().split(",")
    var coordinates = mutableListOf<List<Int>>()
    coordinates.add(listOf<Int>(0, 0))
    for (command in commands) {
        val dir = command.slice(0..0)
        val dist: Int = command.slice(1..command.length-1).toInt()
        if (dir == "R") {
            var steps = 0
            while (steps < dist) {
                val curr_coord = coordinates.last()
                var curr_x = curr_coord.get(0)
                var curr_y = curr_coord.get(1)
                coordinates.add(listOf(curr_x+1, curr_y))
                steps ++
            }
        } else if (dir == "L") {
            var steps = 0
            while (steps < dist) {
                val curr_coord = coordinates.last()
                var curr_x = curr_coord.get(0)
                var curr_y = curr_coord.get(1)
                coordinates.add(listOf(curr_x-1, curr_y))
                steps ++
            }
        } else if (dir == "U") {
            var steps = 0
            while (steps < dist) {
                val curr_coord = coordinates.last()
                var curr_x = curr_coord.get(0)
                var curr_y = curr_coord.get(1)
                coordinates.add(listOf(curr_x, curr_y+1))
                steps ++
            }
        } else if (dir == "D") {
            var steps = 0
            while (steps < dist) {
                val curr_coord = coordinates.last()
                var curr_x = curr_coord.get(0)
                var curr_y = curr_coord.get(1)
                coordinates.add(listOf(curr_x, curr_y-1))
                steps ++
            }
        }
    }
    coordinates.removeAt(0)
    return coordinates
}

fun mhDist(coordinate: List<Int>): Int {
    val dist = abs(coordinate.get(0)) + abs(coordinate.get(1))
    return dist
}

fun main(args: Array<String>) {
    val file = File("input.txt")
    // Read file directly into content list from trimmed string
    val wires: List<String> = file.readLines()
    val wire1 = wireCoordinates(wires[0])
    val wire2 = wireCoordinates(wires[1])
    var max_dist = 99999

    val part1 = 0
    val part2 = 1

    if (part1 == 1) {
        for (coord in wire1) {
            if (coord in wire2) {
                val mh_dist = mhDist(coord)
                if (mh_dist < max_dist) {
                    max_dist = mh_dist
                }
            }
        }
        print("Part 1: Minimum dist: $max_dist\n")
    }
    // Coordinate index is number of steps
    // Find coordinate index in both wires
    // sum the indices
    if (part2 == 1) {
        var min_stepsum = 999999999
        for (coord in wire1) {
            if (coord in wire2) {
                val idx_a = wire1.indexOf(coord)
                val idx_b = wire2.indexOf(coord)
                val stepsum = idx_a + idx_b + 2
                if (stepsum < min_stepsum) {
                    min_stepsum = stepsum
                }
            }
        }
        print("Part 1: Minimum steps: $min_stepsum\n")
    }
}
