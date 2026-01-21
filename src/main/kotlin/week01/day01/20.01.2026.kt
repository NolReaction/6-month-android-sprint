package week01.day01

import kotlin.random.Random
import kotlin.text.iterator


fun main() {
    // #1
    println("(#1) sum/diff/comp")
    print("a = ")
    var a = readln().toInt()
    print("b = ")
    var b = readln().toInt()
    println("sum = ${a + b}")
    println("diff = ${a - b}")
    println("comp = ${a * b}")
    println()

    // #2
    println("(#2) even/odd")
    print("a = ")
    a = readln().toInt()
    if (a % 2 == 0) println("$a = even") else println("$a = odd")
    println()

    // #3
    println("(#3) ><=")
    print("a = ")
    a = readln().toInt()
    print("b = ")
    b = readln().toInt()
    if (a > b) println("a > b ($a; $b)")
    else if (a < b) println("a < b ($a; $b)")
    else println("a == b ($a; $b)")
    println()

    // #4
    println("(#4) 1 to n")
    print("n = ")
    var n = readln().toLong()
    for (i in 1L..n) {
        println(i)
    }
    println()

    // #5 (я так понимаю это арифметическая прогрессия)
    println("(#5) sum 1 to n")
    print("n = ")
    n = readln().toLong()
    var x = 0L
    for (i in 1L..n) {
        x += i
    }
    println(x)
    println()

    // #6 (а тут факториал)
    println("(#6) factorial!")
    print("n = ")
    n = readln().toLong()
    x = 1L
    for (i in 1L..n) {
        x *= i
    }
    println(x)
    println()

    // #7
    println("(#7) length + first + last")
    print("str = ")
    var str = readln().toString()
    var length = 0
    for (letter in str) {
        length += 1
    }
    println("length = $length")

    var first = str[0]
    println("first = $first")

    var last = str[length-1]
    println("last = $last")
    println()

    // #8
    println("(#8) number count")
    print("number = ")
    var number = readln().toString()
    length = 0
    for (letter in number) {
        length += 1
    }
    println(length)
    println()

    // #9
    println("(#9) n random numbers -> min/max")
    print("y = ")
    var y = readln().toInt()
    var arr = IntArray(y)

    // fill array
    for (i in 0 until y) {
        arr[i] = Random.nextInt(1,101)
    }

    // print array
    for (i in 0 until y) {
        print("${arr[i]} ")
    }
    println()

    var min = arr[0] // инициализация
    var max = arr[0] // инициализация

    for (i in 0 until y) {
        val v = arr[i]
        if (v < min) min = v
        if (v > max) max = v
    }
    println("min = $min")
    println("max = $max")

    println()

    // #10
    println("(#10) n random numbers -> average value")
    y = readln().toInt()
    arr = IntArray(y)

    // fill array
    for (i in 0 until y) {
        arr[i] = Random.nextInt(1,101)
    }

    // print array
    for (i in 0 until y) {
        print("${arr[i]} ")
    }
    println()

    var sum: Long = 0
    for (num in arr) {
        sum += num
    }
    println("Sum = $sum")
    var average: Double = sum / y.toDouble()
    println("Average = $average")

    println()

    // FizzBuzz
    val num = readln().toInt()
    if (num % 3 == 0 && num % 5 == 0) println("FizzBuzz")
    else if (num % 3 == 0) println("Fizz")
    else if (num % 5 == 0) println("Buzz")
    else println(num)

    println()
}