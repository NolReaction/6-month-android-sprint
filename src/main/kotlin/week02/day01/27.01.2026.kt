package week02.day01

fun Int.digitCount(): Int = kotlin.math.abs(this.toLong()).toString().length

// Задание 4 - data class + map
data class User(val name: String, val age: Int)

// Задание 5 - flatMap (вложенные списки)
data class Group(val students: List<String>)

// Задание 6 - комбинированное: flatMap + filter (+ map)
data class Order(val id: Int, val items: List<Item>)
data class Item(val name: String, val price: Int)

fun main() {
    // Задание 1 - map
    val nums = listOf(1, 2, 3, 4, 5)
    println(nums.map { it * it })
    println()

    // Задание 2 - filter
    println(nums.filter { it % 2 == 0 })

    // Задание 3.1 - map + filter
    val numList = listOf(
        listOf(1234, 5678, 9010),
        listOf(1111, 2222, 3333),
        listOf(4321, 8765, 9900)
    )
    val flatNumList = numList.flatMap { it }
    val filtered = flatNumList.filter { it > it.digitCount() }
    println(filtered) // чуть изменил задание, по-этому сделаю ещё одно
    println()


    // Задание 3.2 - map + filter
    val words = listOf("kotlin", "map", "flatMap", "ui")
    val result = words
        .filter { it.length > 3 }
        .map { it.uppercase() }
    println(result)
    println()

    // Задание 4 - data class + map
    val listOfUsers = listOf<User>(
        User("Dima", 22),
        User("Nat", 20),
        User("Vanya", 22),
        User("Oksana", 10),
        User("Sergey", 15)

    )
    val adultNames = listOfUsers
        .filter { it.age > 18 }
        .map { it.name }
    println(adultNames)
    println()


    // Задание 5 - flatMap (вложенные списки)
    val groups = listOf(
        Group(listOf("Dima", "Misha")),
        Group(listOf("Vanya")),
        Group(listOf("Oksana", "Sergey"))
    )
    println(groups.flatMap { it.students })
    println()

    // Задание 6 - комбинированное: flatMap + filter (+ map)
    // Взял готовый список
    val orders = listOf(
        Order(
            id = 1,
            items = listOf(
                Item("Pencil", 20),
                Item("Notebook", 120),
                Item("Backpack", 350)
            )
        ),
        Order(
            id = 2,
            items = listOf(
                Item("Coffee", 90),
                Item("Headphones", 199),
                Item("Cable", 100)
            )
        ),
        Order(
            id = 3,
            items = listOf(
                Item("Mouse", 110),
                Item("Keyboard", 99),
                Item("Monitor", 700)
            )
        )
    )

    val expensive  = orders
        .flatMap { order ->
            order.items.map { item ->
                order.id to item
            }
        }
        .filter { (_, item) -> item.price > 100 }
    println(expensive)
}