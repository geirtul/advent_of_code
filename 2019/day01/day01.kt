import java.io.File

// Day one of advent of code 2019

fun fuel(mass: Int): Int {
    return mass/3 - 2
}

fun fuelMass(mass: Int): Int {
    var fuel_total: Int = 0
    var curr_fuel: Int = fuel(mass)
    while(curr_fuel > 0) {
        fuel_total += curr_fuel
        curr_fuel = fuel(curr_fuel)
    }
    return fuel_total
}

fun main(args: Array<String>) {
    val file = File("input.txt")
    var mass_list:List<String> = file.readLines()

    var fuel_mass: Int = 0
    var fuel_total: Int = 0
    for(mass in mass_list){
        fuel_mass += fuel(mass.toInt())
        fuel_total += fuelMass(mass.toInt())
    }
    println("Fuel part 1: $fuel_mass")
    println("Fuel part 2: $fuel_total")
}
