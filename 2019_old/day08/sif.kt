import java.io.File
import java.util.Arrays

// Day eight of advent of code 2019


fun makeImage(h: Int, w: Int, pixels: String): Array<Array<Int>> {
    val n_pixels = pixels.length
    val ppl = h*w // pixels per layer
    val n_layers =  n_pixels/ppl
    var layers = arrayOf<Array<Int>>()
    for (i in 0..n_layers-1) {
        var layer = arrayOf<Int>()
        for (j in 0..ppl-1) {
            layer += 0
        }
        layers += layer
    }
    for (i in 0..layers.size-1) {
        for (j in 0..ppl-1) {
            layers[i][j] = pixels[i*(ppl) + j].toString().toInt()
        }
    }
    return layers
}

fun findZeroLayer(image: Array<Array<Int>>): Int {
    // Get layer in image with fewest 0 elemens.
    // returns index
    var zero_idx = 0
    var min_zeros = 99999999
    for (i in 0..image.size-1) {
        var n_zeros = image[i].count {it == 0}
        if (n_zeros < min_zeros) {
            zero_idx = i
            min_zeros = n_zeros
        }
    }
    return zero_idx
}

fun checkPixel(p_idx: Int, image: Array<Array<Int>>): Int {
    // Check which pixel in the layers will be the resulting value
    for (i in 0..image.size-1) {
        var pixel = image[i][p_idx]
        if (pixel == 0 || pixel == 1) return pixel
        else continue
    }
    return -1
}

fun printImageLayer(layer: Array<Int>, height: Int, width: Int) {
    for (i in 0..height-1) {
        for (j in 0..width-1) {
            var pix = layer[i*width + j]
            print("$pix ")
        }
        println()
    }
}

fun main(args: Array<String>) {
    if (args.size == 0) {
        println("Provide filname")
        return
    }
    val filename = args[0]
    val file = File(filename)
    val pixels = file.readText().trim()
    var image = makeImage(25, 6, pixels)
    val idx = findZeroLayer(image)
    var num_1 = image[idx].count {it == 1}
    var num_2 = image[idx].count {it == 2}
    var part1 = num_1*num_2
    print("Part 1: $part1\n")
    
    var decoded_image = image[0].copyOf()
    for (i in 0..image[0].size-1) {
        decoded_image.set(i, checkPixel(i, image))
    }
    println("Part 2 image output:")
    printImageLayer(decoded_image, 6, 25)

}
