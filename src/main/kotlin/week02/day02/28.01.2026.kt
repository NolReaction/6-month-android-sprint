package week02.day02

import week02.day01.digitCount

fun main() {
    // Task 1 / Task 2
    val nums = listOf(3, 5, 2, 10, 7)
    val foldSum = nums.fold(0) {acc, i -> acc + i}
    println("foldSum: $foldSum")

    val foldMultiply = nums.fold(1) {acc, i -> acc * i}
    println("foldMultiply: $foldMultiply")

    println()

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

    println()

    val countOfWords = words.groupingBy { it }.eachCount()
    println(countOfWords) // Надо перерешивать это задание, потому что ответ в самом задании...
    println()

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



    // Допы
    data class Message(val user: String, val text: String, val likes: Int)

    val messages = listOf(
        Message("Dima", "kotlin is cool", 5),
        Message("Nat", "map and filter", 2),
        Message("Dima", "groupBy is hard", 7),
        Message("Vanya", "reduce and fold", 1),
        Message("Dima", "kotlin kotlin kotlin", 3),
        Message("Nat", "kotlin is ok", 4),
        Message("Oksana", "ui ui ui", 6),
        Message("Vanya", "math and code", 3),
        Message("Nat", "map map", 1),
        Message("Oksana", "android kotlin", 2)
    )

    val countOfLikesEveryUser = messages
        .groupingBy { it.user }
        .fold(0) {acc, msg -> acc + msg.likes}
    println(countOfLikesEveryUser)

    val countOfMessageEveryUser = messages
        .groupingBy { it.user }.eachCount()
    println(countOfMessageEveryUser)

}