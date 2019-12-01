import java.io.File

// Day one of advent of code 2019

fun main(args: Array<String>) {
    val file = File("input.txt")
    var mass_list:List<String> = file.readLines()
    var fuel_needed: Int = 0
    var fuel_total: Int = 0
    for(mass in mass_list){
        var fuel: Int = mass.toInt()/3 - 2
        fuel_needed += fuel
        fuel_total += fuel

        // Part 2
        while(fuel > 0) {
            fuel = fuel/3 - 2
            if (fuel > 0) {
                fuel_total += fuel
            }
        }
    }
    println("Fuel part 1: $fuel_needed")
    println("Fuel part 2: $fuel_total")
}
