import java.io.File
import java.util.Arrays
import kotlin.math.*

// Day ten of advent of code 2019

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

fun readCoord(coord: String): MutableList<Int> {
    /*
    Reads one coordinate and returns them as a pair of ints
    */
    val coords: MutableList<Int> = coord.split(",").map {el -> el.toInt()}.toMutableList()
    return coords
}

fun calcIntersect(p1: MutableList<Int>, p2: MutableList<Int>): MutableList<MutableList<Int>> {
    /* 
    returns intersecting gridpoints between two asteroids
    */
    val x1 = p1.get(0)
    val x2 = p2.get(0)
    val y1 = p1.get(1)
    val y2 = p2.get(1)
    val dx = x2-x1
    val dy = y2-y1

    var intersects = mutableListOf<MutableList<Int>>()
    if (dy == 0 && dx != 0) {
        for (i in min(x1,x2)..max(x1,x2)) {
            if (i == x1 || i == x2) continue
            val y = dy*(i - x1) + dx*y1
            if (y % dx == 0) {
                intersects.add(mutableListOf(i, y/dx))
            }
        }
    } else if (dx == 0 && dy != 0) {
        for (i in min(y1,y2)..max(y1,y2)) {
            if (i == y1 || i == y2) continue
            val x = dx*(i - y1) + dy*x1
            if (x % dy == 0) {
                intersects.add(mutableListOf(x/dy, i))
            }
        }
    } else if (dx != 0 && dy != 0) {
        for (i in min(x1,x2)..max(x1,x2)) {
            if (i == x1 || i == x2) continue
            val y = dy*(i - x1) + dx*y1
            if (y % dx == 0) {
                intersects.add(mutableListOf(i, y/dx))
            }
        }
    }
    return intersects
}

fun hasIntersect(intersects: MutableList<MutableList<Int>>, coords: List<MutableList<Int>>):
Boolean {
    // Return truemeans there is an asteroid in the way
    // return 0 means there isn't
    for (i in intersects) {
        for (c in coords) {
            if (i == c ) {
                //print("Intersect: [${i.get(0)}, ${i.get(1)}]\n")
                return true
            }
        }
    }
    return false
}

fun calcAngle(p: MutableList<Int>): Double {
    val x = p.get(0).toDouble()
    val y = p.get(1).toDouble()
    val hyp = hypot(x, y)
    val angle = asin(y/hyp)
    return angle
}


fun calcDistance(p: MutableList<Int>): Double {
    return hypot(p.get(0).toDouble(), p.get(1).toDouble())
}

fun evalCandidates(curr_angle: Double, candidates: MutableList<MutableList<Int>>): MutableList<Int> {
    // returns the asteroid that is to be destroyed
    var correct_candidate = candidates.get(0)
    for (i in 1..candidates.size-1) {
        val a_angle = calcAngle(candidates.get(i))
        val b_angle = calcAngle(correct_candidate)
        if (b_angle < a_angle) continue
        val diff_a = abs(a_angle - curr_angle)
        val diff_b = abs(b_angle - curr_angle)
        //println("a: ${candidates.get(i)} diff = $diff_a")
        //println("c: ${correct_candidate} diff = $diff_b")

        // We're moving in the direction of negative angle
        if (diff_a < diff_b) correct_candidate = candidates.get(i)
        else if (diff_b < diff_a) continue
        else {
            val a_dist = calcDistance(candidates.get(i))
            val b_dist = calcDistance(correct_candidate)
            if (a_dist < b_dist) correct_candidate = candidates.get(i)
            else if (b_dist < a_dist) continue
        }
    }
    return correct_candidate
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
    if (part == 1) {
        println("Part $part:")
        val coords = generateCoords(lines).map {el -> readCoord(el)}
        var max_visible = 0
        var asteroid = mutableListOf<Int>(0,0)
        for(i in 0..coords.size-1) {
            var visible = 0
            for (j in 0..coords.size-1) {
                if (i == j) continue
                var points = calcIntersect(coords.get(i), coords.get(j))
                if (!hasIntersect(points, coords)) {
                    visible++
                }
            }
            if (visible > max_visible) {
                max_visible = visible
                asteroid.set(0,coords.get(i).get(0))
                asteroid.set(1,coords.get(i).get(1))
            }
        }
        print("Best asteroid at ${asteroid}, with $max_visible visible\n")
    }
    if (part == 2) {
        println("Part $part:")
        val coords = generateCoords(lines).map {el -> readCoord(el)}.toMutableList()
        var asteroid = mutableListOf(28,29)

        // Remove 0,0 from list of viable asteroids.
        for (i in 0..coords.size-1) {
            if (asteroid == coords.get(i)){
                coords.removeAt(i)
                break
            }
        }
        // Make our asteroid the center point (0,0) and flip the y-axis to
        // NORMAL DIRECTION DJEEZ PLEEZ AOC
        for (c in coords) {
            c.set(0, c.get(0)-asteroid.get(0))
            c.set(1, -(c.get(1)-asteroid.get(1)))
        }
        
        // Set starting angle
        var angle: Double = PI/2 + 0.01
        // Upper right quadrant first
        println("Upper right ================================")
        var candidates = coords.filter {it.get(0) >= 0 && it.get(1) > 0}.toMutableList()

        // Sort candidates by angle, then distance (close to distant)
        var sorted_candidates = candidates.sortedWith(compareBy({-calcAngle(it)}, {calcDistance(it)}))
        var n_prints = 1
        for (i in 0..sorted_candidates.size-1){
            val x = sorted_candidates.get(i).get(0)
            val y = sorted_candidates.get(i).get(1)
            val curr_angle = calcAngle(sorted_candidates.get(i))
            if (abs(curr_angle-angle) < 1e-10) continue
            println("$n_prints: Coord: ${x+28}, ${29-y} | angle: $curr_angle")
            angle = curr_angle
            n_prints++
            for (j in 0..coords.size-1) {
                if (coords.get(j) == sorted_candidates.get(i)) {
                    coords.removeAt(j)
                    break
                }
            }
        }
        // Bottom right quadrant
        println("Bottom right ================================")
        // Set starting angle
        var angle2: Double = 0.01
        var candidates2 = coords.filter {it.get(0) > 0 && it.get(1) <= 0}.toMutableList()

        // Sort candidates by angle, then distance (close to distant)
        var sorted_candidates2 = candidates2.sortedWith(compareBy({-calcAngle(it)}, {-calcDistance(it)}))
        for (i in 0..sorted_candidates2.size-1){
            val x = sorted_candidates2.get(i).get(0)
            val y = sorted_candidates2.get(i).get(1)
            val curr_angle = calcAngle(sorted_candidates2.get(i))
            if (abs(curr_angle-angle2) < 1e-10) continue
            println("$n_prints: Coord: ${x+28}, ${29-y} | angle: $curr_angle")
            angle2 = curr_angle
            n_prints++
        }

        // Bottom left quadrant
        println("Bottom left ================================")
        // Set starting angle
        var angle3: Double = 0.01
        var candidates3 = coords.filter {it.get(0) <= 0 && it.get(1) < 0}.toMutableList()

        // Sort candidates by angle, then distance (close to distant)
        var sorted_candidates3 = candidates3.sortedWith(compareBy({calcAngle(it)}, {calcDistance(it)}))
        for (i in 0..sorted_candidates3.size-1){
            val x = sorted_candidates3.get(i).get(0)
            val y = sorted_candidates3.get(i).get(1)
            val curr_angle = calcAngle(sorted_candidates3.get(i))
            if (abs(curr_angle-angle3) < 1e-10) {
                continue
            }
            println("$n_prints: Coord: ${x+28}, ${29-y} | angle: $curr_angle")
            angle3 = curr_angle
            n_prints++
        }

        // Top left quadrant
        println("Upper left ================================")
        // Set starting angle
        var angle4: Double = -0.01
        var candidates4 = coords.filter {it.get(0) <= 0 && it.get(1) >= 0}.toMutableList()

        // Sort candidates by angle, then distance (close to distant)
        var sorted_candidates4 = candidates4.sortedWith(compareBy({calcAngle(it)}, {calcDistance(it)}))
        for (i in 0..sorted_candidates4.size-1){
            val x = sorted_candidates4.get(i).get(0)
            val y = sorted_candidates4.get(i).get(1)
            val curr_angle = calcAngle(sorted_candidates4.get(i))
            if (abs(curr_angle-angle4) < 1e-10) {
                continue
            }
            println("$n_prints: Coord: ${x+28}, ${29-y} | angle: $curr_angle")
            angle4 = curr_angle
            n_prints++
        }
        // Upper right quadrant second run
        // Set starting angle
        var angle5: Double = PI/2 + 0.01
        println("Upper right 2 ================================")
        var candidates5 = coords.filter {it.get(0) >= 0 && it.get(1) > 0}.toMutableList()

        // Sort candidates by angle, then distance (close to distant)
        var sorted_candidates5 = candidates5.sortedWith(compareBy({-calcAngle(it)}, {calcDistance(it)}))
        for (i in 0..sorted_candidates5.size-1){
            val x = sorted_candidates5.get(i).get(0)
            val y = sorted_candidates5.get(i).get(1)
            val curr_angle = calcAngle(sorted_candidates5.get(i))
            if (abs(curr_angle-angle5) < 1e-10) continue
            println("$n_prints: Coord: ${x+28}, ${29-y} | angle: $curr_angle")
            angle5 = curr_angle
            n_prints++
            for (j in 0..coords.size-1) {
                if (coords.get(j) == sorted_candidates5.get(i)) {
                    coords.removeAt(j)
                    break
                }
            }
        }
    }
}
