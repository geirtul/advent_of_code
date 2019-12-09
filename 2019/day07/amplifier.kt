import java.io.File
import kotlin.system.*

// Day seven of advent of code 2019

class Amplifier(input_code: MutableList<Int>, name: String) {
    val name = name
    var intcode = input_code.toMutableList()
    var phase: Int = 0
    var signal: Int = 0
    var halted: Boolean = false
    var output: Int = 0
    var input_count = 0
    var i: Int = 0

    fun amplify(keep_intcode: Boolean) {
        // Takes a true or fale value for reset, which decides whether
        // or not to reset the intcode after each amplification.
        var code = intcode.toMutableList()
        while (this.i <= code.size-1) {
            // Process the opcode and parameters
            val inst = code[this.i].toString()
            var opcode = inst.last().toString().toInt()
            var opcode2 = 0

            // Check if opcode is 99
            if (opcode == 9 && inst.length > 1) {
                opcode2 = inst[inst.length-2].toString().toInt()
            }
            if (opcode2 == 9) {
                this.halted = true
                return
            }

            var par1 = 0
            var par2 = 0
            //var par3 = 0
            if (inst.length == 3) {
                par1 = inst[0].toString().toInt()
                par2 = 0
                //par3 = 0
            } else if (inst.length == 4) {
                par1 = inst[1].toString().toInt()
                par2 = inst[0].toString().toInt()
                //par3 = 0
            } else if (inst.length == 5) {
                par1 = inst[2].toString().toInt()
                par2 = inst[1].toString().toInt()
                //par3 = inst[0].toString().toInt()
            }
            
            // Set indices based on parameters
            var a: Int
            var b: Int
            //var c: Int
            if (par1 == 0) {
                a = code[this.i+1]
            } else {
                a = i+1
            }
            if (par2 == 0) {
                b = code[this.i+2]
            } else {
                b = i+2
            }
            /*
            if (par3 == 0) {
                c = code[this.i+3]
            } else {
                c = this.i+3
            }
            */

            // Run instruction
            if (opcode == 1) {
                code.set(code[this.i+3], code[a]+code[b])
                this.i += 4
            } else if (opcode == 2) {
                code.set(code[this.i+3], code[a]*code[b])
                this.i += 4
            } else if (opcode == 3) {
                if (input_count == 0) {
                    code.set(code[this.i+1], this.phase)
                    input_count++
                } else {
                    code.set(code[this.i+1], this.signal)
                }
                this.i += 2
            } else if (opcode == 4) {
                this.output = code.get(a)
                this.i += 2
                if (keep_intcode) this.intcode = code
                return
            } else if (opcode == 5) {
                if (code[a] != 0) {
                    this.i = code[b]
                } else {
                    this.i += 3
                }
            } else if (opcode == 6) {
                if (code[a] == 0) {
                    this.i = code[b]
                } else {
                    this.i += 3
                }
            } else if (opcode == 7) {
                if (code[a] < code[b]) {
                    code.set(code[this.i+3], 1)
                } else {
                    code.set(code[this.i+3], 0)
                }
                this.i += 4
            } else if (opcode == 8) {
                if (code[a] == code[b]) {
                    code.set(code[this.i+3], 1)
                } else {
                    code.set(code[this.i+3], 0)
                }
                this.i += 4
            }
        }
        if (keep_intcode) this.intcode = code
    }
}


fun generatePhases(possible_phases: List<Int>): MutableList<List<Int>> {
    // Generates all valid phase combinations
    var phaselist: MutableList<List<Int>> = mutableListOf<List<Int>>()
    for (i in possible_phases) {
        for (j in possible_phases) {
            if (j == i) continue
            for (k in possible_phases) {
                if (k == j || k == i) continue
                for (l in possible_phases) {
                    if (l == k || l == j || l == i) continue
                    for (m in possible_phases) {
                        if (m == l || m == k || m == j || m == i) continue
                        phaselist.add(listOf(i,j,k,l,m))
                    }
                }
            }
        }
    }
    return phaselist
}

fun main(args: Array<String>) {
    if (args.size == 0) {
        println("Provide filname and which part to do (1 or 2)")
        return
    }
    val filename = args[0]
    var part = args[1].toInt()
    val file = File(filename)
    var intcode = mutableListOf<Int>()
    // Read file directly into content list from trimmed string
    file.readText().trim().split(",").forEach {e -> intcode.add(e.toInt())}

    if (part == 1) {
        // ====== Part 1
        // Make amplifiers
        var amplifiers = listOf(Amplifier(intcode, "A"), Amplifier(intcode, "B"), Amplifier(intcode, "C"), Amplifier(intcode, "D"), Amplifier(intcode, "E"))
        var max_sig = 0
        var phases = listOf(0,1,2,3,4)
        var phase_set: List<Int> = listOf()
        var plist = generatePhases(phases)
        for (p in plist) {
            var signal = 0
            for (i in 0..amplifiers.size-1) {
                amplifiers[i].phase = p[i]
            }
            for (amp in amplifiers) {
                amp.signal = signal
                amp.amplify(keep_intcode=false)
                signal = amp.output
            }
            if (signal > max_sig) {
                max_sig = signal
                phase_set = p
            }
        }
        print("Part 1 maximum signal: $max_sig\n")
        print("Part 1 phase set:\n")
        print(phase_set)
        print("\n")
    }
    if (part == 2) {
        // ====== Part 2
        // Make persistent set of amplifiers
        var max_sig = 0
        var phases = listOf(5,6,7,8,9)
        var phase_set: List<Int> = listOf()
        var plist = generatePhases(phases)
        for (p in plist) {
            var amplifiers = listOf(Amplifier(intcode, "A"), Amplifier(intcode, "B"), Amplifier(intcode, "C"), Amplifier(intcode, "D"), Amplifier(intcode, "E"))
            var signal = 0
            var run = true
            for (i in 0..amplifiers.size-1) {
                amplifiers[i].phase = p[i]
            }
            // Run feedback loop until halt signal
            while (run) {
                for (amp in amplifiers) {
                    amp.signal = signal
                    amp.amplify(keep_intcode=true)
                    signal = amp.output
                }
                if (amplifiers.last().halted) break
            }
            if (signal > max_sig) {
                max_sig = signal
                phase_set = p
            }
        }
        print("Part 2 maximum signal: $max_sig\n")
        print("Part 2 phase set:\n")
        print(phase_set)
        print("\n")
    }
}
