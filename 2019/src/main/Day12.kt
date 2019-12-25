package main

import java.io.File
import kotlin.math.abs

// Day twelve of advent of code 2019


class IntVector3D(var x: Int, var y: Int, var z: Int) {

    // Operator overrides
    // Operations with scalars
    operator fun plus(scalar: Int) {
        this.x += scalar
        this.y += scalar
        this.z += scalar
    }

    operator fun minus(scalar: Int) {
        this.x -= scalar
        this.y -= scalar
        this.z -= scalar
    }

    operator fun times(scalar: Int) {
        this.x *= scalar
        this.y *= scalar
        this.z *= scalar
    }

    // Operations with other vectors (Vector3D)
    operator fun plus(b: IntVector3D): IntVector3D {
        return IntVector3D(this.x + b.x, this.y + b.y, this.z + b.z)
    }

    operator fun minus(b: IntVector3D): IntVector3D {
        return IntVector3D(this.x - b.x, this.y - b.y, this.z - b.z)
    }

    // Dot product with another IntVector3D
    fun dot(b: IntVector3D): Int {
        return this.x*b.x + this.y*b.y + this.z*b.z
    }

    // print vector
    fun print(){
        println("[${this.x}, ${this.y}, ${this.z}]")
    }
}

class Moon(var position: IntVector3D) {
    var velocity: IntVector3D = IntVector3D(0, 0, 0)

    // Class methods
    fun applyGravity(b: Moon) {
        when {
            this.position.x < b.position.x -> this.velocity.x += 1
            this.position.x > b.position.x -> this.velocity.x -= 1
        }
        when {
            this.position.y < b.position.y -> this.velocity.y += 1
            this.position.y > b.position.y -> this.velocity.y -= 1
        }
        when {
            this.position.z < b.position.z -> this.velocity.z += 1
            this.position.z > b.position.z -> this.velocity.z -= 1
        }
    }
    fun applyVelocity() {
        this.position = this.position + this.velocity
    }

    fun calcPotentialEnergy(): Int {
        return abs(this.position.x) + abs(this.position.y) + abs(this.position.z)
    }

    fun calcKineticEnergy(): Int {
        return abs(this.velocity.x) + abs(this.velocity.y) + abs(this.velocity.z)
    }

    fun print() {
        print("pos=<x=${this.position.x}, y=${this.position.y}, z=${this.position.z}, ")
        print("vel=<x=${this.velocity.x}, y=${this.velocity.y}, z=${this.velocity.z}\n")
    }
}

class Day12 {
    var parsedInput: MutableList<Moon> = mutableListOf<Moon>()

    fun parseInput(filename: String) {
        val file = File(filename)
        val lines = file.readLines()
        val pattern = "-?\\d+".toRegex()
        for (line in lines) {
            val found = pattern.findAll(line).toList()
            this.parsedInput.add(
                Moon(
                    IntVector3D(found[0].value.toInt(), found[1].value.toInt(), found[2].value.toInt())))
        }
    }

    fun step(moons: MutableList<Moon>): MutableList<Moon> {
        for (i in 0 until moons.size) {
            for (j in 0 until moons.size) {
                if (i == j) continue
                moons[i].applyGravity(moons[j])
            }
        }
        moons.forEach { it.applyVelocity() }
        return moons
    }

    fun solvePart1() {
        var moons = this.parsedInput.toMutableList()

        repeat(1000) { moons = step(moons) }
        var totEnergy = 0
        for (moon in moons) {
            val pot = moon.calcPotentialEnergy()
            val kin = moon.calcKineticEnergy()
            val tot = pot*kin
            //println("pot = $pot, kin = $kin, tot = $tot")
            totEnergy += tot
        }
        println(totEnergy)
    }
}

fun main() {
    val today = Day12()
    today.parseInput("src/main/inputs/input12.txt")
    today.solvePart1()
}
