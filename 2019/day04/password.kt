import java.io.File

// Day four of advent of code 2019

fun checkPassword(password: Int): Int {
    val s = password.toString()
    var adjacency = 0
    for (i in 0..s.length-2) {
        // Check for no decrease
        if (s[i] > s[i+1]) {
            return 0
        }
        // Check adjacency
        if (s[i] == s[i+1]) {
            adjacency += 1
        }
    }
    if (adjacency > 0) {
        return 1
    } else {
        return 0
    }
}

fun checkPasswordTwo(password: Int): Int {
    val s = password.toString()
    var adjacency = 0
    var i = 0
    while (i <= s.length-2) {
        // Check for no decrease
        if (s[i].toInt() > s[i+1].toInt()) {
            return 0
        }
        // Check adjacency
        var adjacent = 0
        for (j in i..s.length-2) {
            if (s[i] == s[j+1]) {
                adjacent += 1
            } else {
                break
            }
        }
        if (adjacent == 1) {
            adjacency += 1
        }
        // Increment i
        if (adjacent > 0) {
            i += adjacent
        } else {
            i += 1
        }
    }
    if (adjacency > 0) {
        return 1
    } else {
        return 0
    }
}

fun main(args: Array<String>) {
    val file = File("input.txt")
    val limits = file.readText().trim().split("-")
    val lower = limits.get(0).toInt()
    val upper = limits.get(1).toInt()
    var count = 0
    for (password in lower..upper) {
        if (checkPassword(password) == 1) {
            count += 1
        }
    }
    print("Part 1 number of passwords: $count\n")
    var count_two = 0
    for (password in lower..upper) {
        if (checkPasswordTwo(password) == 1) {
            count_two += 1
        }
    }
    print("Part 2 number of passwords: $count_two\n") // 972, 1630
}
