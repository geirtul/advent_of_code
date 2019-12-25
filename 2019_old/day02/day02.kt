import java.io.File

// Day two of advent of code 2019

fun runIntcode(intcode: MutableList<Int>): MutableList<Int> {
    var tmp = intcode.toMutableList()
    for (i in 0..intcode.size step 4) {
        val a_idx = tmp[i+1]
        val b_idx = tmp[i+2]
        val c_idx = tmp[i+3]

        if (tmp[i] == 1) {
            tmp.set(c_idx, tmp[a_idx]+tmp[b_idx])
        } else if (tmp[i] == 2) {
            tmp.set(c_idx, tmp[a_idx]*tmp[b_idx])
        } else if (tmp[i] == 99) {
            return tmp
        }
    }
    return tmp
}

fun findInputs(intcode: MutableList<Int>, target: Int): Int {
    for (i in 0..100) {
        for (j in 0..100) {
            var tmp = intcode.toMutableList()
            tmp.set(1, i)
            tmp.set(2, j)
            var new = runIntcode(tmp)
            if (new[0] == target) {
                print("noun: $i, verb: $j\n")
            }
        }
    }
    return 0
}


fun main(args: Array<String>) {
    val file = File("input.txt")
    var intcode = mutableListOf<Int>()
    // Read file directly into content list from trimmed string
    file.readText().trim().split(",").forEach {e -> intcode.add(e.toInt())}
    var new_intcode = runIntcode(intcode)
    print(new_intcode[0].toString() + "\n")
    findInputs(intcode, 19690720)
}
