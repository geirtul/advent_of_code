import java.io.File

// Day five of advent of code 2019

fun runIntcode(intcode: MutableList<Int>): MutableList<Int> {
    var tmp = intcode.toMutableList()
    var i: Int = 0
    while (i < intcode.size) {
        // Process the opcode and parameters
        val inst = tmp[i].toString()
        //print("inst i=$i: $inst\n")
        var opcode = inst.last().toString().toInt()
        var par1 = 0
        var par2 = 0
        var par3 = 0
        if (inst.length == 3) {
            par1 = inst[0].toString().toInt()
            par2 = 0
            par3 = 0
        } else if (inst.length == 4) {
            par1 = inst[1].toString().toInt()
            par2 = inst[0].toString().toInt()
            par3 = 0
        } else if (inst.length == 5) {
            par1 = inst[2].toString().toInt()
            par2 = inst[1].toString().toInt()
            par3 = inst[0].toString().toInt()
        }
        
        // Set indices based on parameters
        var a = 0
        var b = 0 
        if (par1 == 0) {
            a = tmp[i+1]
        } else {
            a = i+1
        }
        if (par2 == 0) {
            b = tmp[i+2]
        } else {
            b = i+2
        }
        //print("i: $i | $inst | $opcode | $par1 | $par2 | $par3 | $a | $b\n")

        // Run instruction
        if (opcode == 1) {
            tmp.set(tmp[i+3], tmp[a]+tmp[b])
            i += 4
        } else if (opcode == 2) {
            tmp.set(tmp[i+3], tmp[a]*tmp[b])
            i += 4
        } else if (opcode == 3) {
            val input = 1
            tmp.set(a, input)
            i += 2
        } else if (opcode == 4) {
            val output = tmp.get(a)
            print("$output ")
            i += 2
        } else if (opcode == 9) {
            return tmp
        }
    }
    return tmp
}
fun runIntcodeTwo(intcode: MutableList<Int>): MutableList<Int> {
    var tmp = intcode.toMutableList()
    var i: Int = 0
    while (i <= intcode.size-4) {
        // Process the opcode and parameters
        val inst = tmp[i].toString()
        var opcode = inst.last().toString().toInt()
        var par1 = 0
        var par2 = 0
        var par3 = 0
        if (inst.length == 3) {
            par1 = inst[0].toString().toInt()
            par2 = 0
            par3 = 0
        } else if (inst.length == 4) {
            par1 = inst[1].toString().toInt()
            par2 = inst[0].toString().toInt()
            par3 = 0
        } else if (inst.length == 5) {
            par1 = inst[2].toString().toInt()
            par2 = inst[1].toString().toInt()
            par3 = inst[0].toString().toInt()
        }
        
        // Set indices based on parameters
        var a = 0
        var b = 0 
        var c = 0
        if (par1 == 0) {
            a = tmp[i+1]
        } else {
            a = i+1
        }
        if (par2 == 0) {
            b = tmp[i+2]
        } else {
            b = i+2
        }
        if (par3 == 0) {
            c = tmp[i+3]
        } else {
            c = i+3
        }

        // Run instruction
        if (opcode == 1) {
            tmp.set(tmp[i+3], tmp[a]+tmp[b])
            i += 4
        } else if (opcode == 2) {
            tmp.set(tmp[i+3], tmp[a]*tmp[b])
            i += 4
        } else if (opcode == 3) {
            val input = 5
            tmp.set(a, input)
            i += 2
        } else if (opcode == 4) {
            val output = tmp.get(a)
            print("$output ")
            i += 2
        } else if (opcode == 5) {
            if (tmp[a] != 0) {
                i = tmp[b]
            } else {
                i += 3
            }
        } else if (opcode == 6) {
            if (tmp[a] == 0) {
                i = tmp[b]
            } else {
                i += 3
            }
        } else if (opcode == 7) {
            if (tmp[a] < tmp[b]) {
                tmp.set(tmp[i+3], 1)
            } else {
                tmp.set(tmp[i+3], 0)
            }
            i += 4
        } else if (opcode == 8) {
            if (tmp[a] == tmp[b]) {
                tmp.set(tmp[i+3], 1)
            } else {
                tmp.set(tmp[i+3], 0)
            }
            i += 4
        } else if (opcode == 9) {
            return tmp
        }
    }
    return tmp
}

fun main(args: Array<String>) {
    val file = File("input.txt")
    var intcode = mutableListOf<Int>()
    // Read file directly into content list from trimmed string
    file.readText().trim().split(",").forEach {e -> intcode.add(e.toInt())}
    //print("Running part 1:\n")
    //val a = runIntcode(intcode)
    print("\nRunning part 2: \n")
    val b = runIntcodeTwo(intcode)
    print("\n")
}
