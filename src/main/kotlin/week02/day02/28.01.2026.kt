package week02.day02

fun main() {
    // Task 1 / Task 2
    val nums = listOf(3, 5, 2, 10, 7)
    val foldSum = nums.fold(0) {acc, i -> acc + i}
    println("foldSum: $foldSum")

    val foldMultiply = nums.fold(1) {acc, i -> acc * i}
    println("foldMultiply: $foldMultiply")

    val reduceMax = nums.reduce { acc, i -> maxOf(acc, i) }
    println("reduceMax: $reduceMax")
    println()

    // Task 3
    val wordsCsv = listOf("a", "bb", "ccc", "d")
    val foldStr = wordsCsv.fold("") {acc, string ->
        if (acc.isBlank()) string else "$acc,$string"
    }
    println(foldStr)

    // Task 3 (я делал бы через reduce с проверкой на null, вместо fold)
    val reduceStr = wordsCsv.reduce { acc, string -> "$acc,$string" }
    println(reduceStr)
    println()

    // Task 4 / Task 5
    val words = listOf(
        "kotlin", "key", "map", "math", "ui",
        "kotlin", "map", "kotlin", "math"
    )
    val groupByFirstChar = words.groupBy { it.first() }
    println(groupByFirstChar)

    // И добавлю ещё решение одно ради интереса, только уже с set
    val groupByFirstCharSet = words.toSet().groupBy { it.first() }
    println(groupByFirstCharSet)

    val countOfWords = words.groupingBy { it }.eachCount()
    println(countOfWords) // Надо перерешивать это задание, потому что ответ в самом задании...

    // Task 6
    data class Purchase(val user: String, val category: String, val amount: Int)

    val purchases = listOf(
        Purchase("Dima", "Food", 120),
        Purchase("Dima", "Tech", 900),
        Purchase("Dima", "Taxi", 220),

        Purchase("Nat", "Food", 80),
        Purchase("Nat", "Food", 150),
        Purchase("Nat", "Tech", 400),

        Purchase("Vanya", "Food", 200),
        Purchase("Vanya", "Tech", 300),
        Purchase("Vanya", "Taxi", 90),

        Purchase("Oksana", "Food", 60),
        Purchase("Oksana", "Taxi", 140),
        Purchase("Oksana", "Tech", 110)
    )



}