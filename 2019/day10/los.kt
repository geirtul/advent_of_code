import java.io.File
import java.util.Arrays
import kotlin.math.*

// Day eight of advent of code 2019

fun generateCoords(grid: List<String>): List<String> {
    /* 
    Takes the input and generates a list of coordinates
    */
    var tmp_coords = ""
    for (i in 0..grid.size-1) {
        for (j in 0..grid[0].length-1) {
            if (grid[i][j].toString() == "#") tmp_coords += ";$j,$i"
        }
    }
    return tmp_coords.split(";").drop(1)
}

fun readCoord(coord: String): List<Int> {
    /*
    Reads one coordinate and returns them as a pair of ints
    */
    val coords: List<Int> = coord.split(",").map {el -> el.toInt()}
    return coords
}

fun calcIntersect(p1: List<Int>, p2: List<Int>): List<List<Int>> {
    /* 
    returns intersecting gridpoints between two asteroids
    */
    val x1 = p1.get(0)
    val x2 = p2.get(0)
    val y1 = p1.get(1)
    val y2 = p2.get(1)
    val dx = x2-x1
    val dy = y2-y1

    var intersects = mutableListOf<List<Int>>()
    // If no change in one of x or y, it's a straight line
    // if equal change, all points on diagonal between them
    if (dx == 0) {
        for (i in (min(y1,y2) + 1)..(max(y1,y2) - 1)) intersects.add(listOf(x1, i))
        return intersects
    } else if (dy == 0) {
        for (i in (min(x1,x2) + 1)..(max(x1,x2) - 1)) intersects.add(listOf(i, y1))
        return intersects
    } else if (dx == dy) {
        for (i in (min(x1,x2) + 1)..(max(x1,x2) - 1)) intersects.add(listOf(i,i))
        return intersects
    } else {
        // not a straight line, both vx and vy nonzero
        for (i in x1..x2) {
            if (i == x1 || i == x2) continue
            val y = dy*(i - x1) + dx*y1
            if (y % dx == 0) {
                intersects.add(listOf(i, y))
            }
        }
    }
    return intersects
}

fun hasIntersect(intersects: List<List<Int>>, coords: List<List<Int>>):
Boolean {
    // Return truemeans there is an asteroid in the way
    // return 0 means there isn't
    for (i in intersects) {
        for (c in coords) {
            if (i.get(0) == c.get(0) && i.get(1) == c.get(1)) {
                return true
            }
        }
    }
    return false
}


fun main(args: Array<String>) {
    if (args.size == 0) {
        println("Provide part and filename.")
        println("Ex: $ java -jar file.jar 1 test1.txt")
        return
    }
    val part = args[0].toInt()
    val filename = args[1]
    val file = File(filename)
    val lines = file.readLines()
    println("Part $part:")
    val coords = generateCoords(lines).map {el -> readCoord(el)}
    var max_visible = 0
    var asteroid = mutableListOf(0,0)
    for(i in 0..coords.size-1) {
        var visible = 0
        print("Standing on ${coords.get(i)} I can see: ")
        for (j in 0..coords.size-1) {
            if (i == j) continue
            var points = calcIntersect(coords.get(i), coords.get(j))
            if (!hasIntersect(points, coords)) {
                visible++
                print("${coords.get(j)} ")
            }
        }
        println()
        if (visible > max_visible) {
            max_visible = visible
            asteroid.set(0,coords.get(i).get(0))
            asteroid.set(1,coords.get(i).get(1))
        }
    }
    print("Best asteroid at ${asteroid}, with $max_visible visible\n")
}
