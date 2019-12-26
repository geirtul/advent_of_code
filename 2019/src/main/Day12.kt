package main

import java.io.File
import kotlin.math.abs
import org.apache.commons.math3.util.ArithmeticUtils.lcm

// Day twelve of advent of code 2019


class IntVector3D(var x: Long, var y: Long, var z: Long) {

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

    fun equals(b: IntVector3D): Boolean {
        return this.x == b.x && this.y == b.y && this.z == b.z
    }
    // Dot product with another IntVector3D
    fun dot(b: IntVector3D): Long {
        return this.x*b.x + this.y*b.y + this.z*b.z
    }

    // print vector
    fun print(){
        println("[${this.x}, ${this.y}, ${this.z}]")
    }
}

class Moon(var position: IntVector3D) {
    var velocity: IntVector3D = IntVector3D(0, 0, 0)
    val position0: IntVector3D = IntVector3D(position.x, position.y, position.z)

    // Class methods
    fun applyGravityX(b: Moon) {
        when {
            this.position.x < b.position.x -> this.velocity.x += 1
            this.position.x > b.position.x -> this.velocity.x -= 1
        }
    }
    fun applyGravityY(b: Moon) {
        when {
            this.position.y < b.position.y -> this.velocity.y += 1
            this.position.y > b.position.y -> this.velocity.y -= 1
        }
    }
    fun applyGravityZ(b: Moon) {
        when {
            this.position.z < b.position.z -> this.velocity.z += 1
            this.position.z > b.position.z -> this.velocity.z -= 1
        }
    }
    fun applyVelocityX() {
        this.position.x += this.velocity.x
    }
    fun applyVelocityY() {
        this.position.y += this.velocity.y
    }
    fun applyVelocityZ() {
        this.position.z += this.velocity.z
    }

    fun calcPotentialEnergy(): Long {
        return abs(this.position.x) + abs(this.position.y) + abs(this.position.z)
    }

    fun calcKineticEnergy(): Long {
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
                    IntVector3D(found[0].value.toLong(), found[1].value.toLong(), found[2].value.toLong())))
        }
    }

    fun step(moons: MutableList<Moon>): MutableList<Moon> {
        for (i in 0 until moons.size) {
            for (j in 0 until moons.size) {
                if (i == j) continue
                moons[i].applyGravityX(moons[j])
                moons[i].applyGravityY(moons[j])
                moons[i].applyGravityZ(moons[j])
            }
        }
        moons.forEach {
            it.applyVelocityX()
            it.applyVelocityY()
            it.applyVelocityZ()
        }
        return moons
    }
    fun stepX(moons: MutableList<Moon>): MutableList<Moon> {
        for (i in 0 until moons.size) {
            for (j in 0 until moons.size) {
                if (i == j) continue
                moons[i].applyGravityX(moons[j])
            }
        }
        moons.forEach { it.applyVelocityX() }
        return moons
    }
    fun stepY(moons: MutableList<Moon>): MutableList<Moon> {
        for (i in 0 until moons.size) {
            for (j in 0 until moons.size) {
                if (i == j) continue
                moons[i].applyGravityY(moons[j])
            }
        }
        moons.forEach { it.applyVelocityY() }
        return moons
    }
    fun stepZ(moons: MutableList<Moon>): MutableList<Moon> {
        for (i in 0 until moons.size) {
            for (j in 0 until moons.size) {
                if (i == j) continue
                moons[i].applyGravityZ(moons[j])
            }
        }
        moons.forEach { it.applyVelocityZ() }
        return moons
    }

    fun solvePart1() {
        var moons = this.parsedInput.toMutableList()

        repeat(1000) { moons = step(moons) }
        var totEnergy: Long = 0
        for (moon in moons) {
            val pot = moon.calcPotentialEnergy()
            val kin = moon.calcKineticEnergy()
            val tot = pot*kin
            totEnergy += tot
        }
        println(totEnergy)
    }
    fun solvePart2() {
        var moons = this.parsedInput.toMutableList()
        var timer: Long = 1
        /*
        while (true) {
            timer++
            if (timer % 10000000 == 0.toLong()) {
                println(timer)
            }
            moons = step(moons)
            var counter: Long = 0
            for (moon in moons) {
                if (moon.position.equals(moon.position0)) counter++
            }
            if (counter == 4.toLong()) {
                println("Found X at $timer")
                break
            }
        }
        */

        while (true) {
            timer++
            moons = stepX(moons)
            var counter: Long = 0
            moons.forEach { if (it.position.x == it.position0.x) counter++ }
            if (counter == 4.toLong()) {
                println("Found X at $timer")
                break
            }
        }
        timer = 1
        moons = this.parsedInput.toMutableList()
        while (true) {
            timer++
            moons = stepY(moons)
            var counter: Long = 0
            moons.forEach { if (it.position.y == it.position0.y) counter++ }
            if (counter == 4.toLong()) {
                println("Found Y at $timer")
                break
            }
        }
        timer = 1
        moons = this.parsedInput.toMutableList()
        while (true) {
            timer++
            moons = stepZ(moons)
            var counter: Long = 0
            moons.forEach { if (it.position.z == it.position0.z) counter++ }
            if (counter == 4.toLong()) {
                println("Found Z at $timer")
                break
            }
        }
    }
}

fun main() {
    val today = Day12()
    today.parseInput("src/main/inputs/input12.txt")
    //today.solvePart1()
    today.solvePart2()
    //332477126821644
}
