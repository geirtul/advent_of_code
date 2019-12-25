package test

import java.io.File
import java.util.Arrays

// Day twelve of advent of code 2019
class Moon(){
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
}
