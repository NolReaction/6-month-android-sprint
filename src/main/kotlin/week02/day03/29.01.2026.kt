package week02.day03

fun main() {
    // Task 1
    class Box<T>(val value: T)
    val boxInt = Box<Int>(10)
    val boxString = Box<String>("10")
    println(boxInt.value)
    println(boxString.value)
    println()

    // Task 2
    fun <T> printTwice(value: T) {
        println(value)
        println(value)
    }
    printTwice(123)
    printTwice("pepe")
    println()

    // Task 3
    fun <T : Number> square(x: T): Double {
        val y = x.toDouble()
        return y * y
    }

    // Task 4
    open class Animal
    class Cat : Animal()

    val cats = listOf<Cat>()

    fun feedAll(animals: List<Animal>) {
        println("Animals $animals were feed")
    }

    feedAll(cats) // типо я могу передать Cat, а принимаемый тип Animal
    println()

    // Task 5
    fun addCat(list: MutableList<in Cat>) {
        list.add(Cat())
        println(list)
    }

    val animals = mutableListOf<Animal>()
    addCat(animals)
    println(animals.size)

}