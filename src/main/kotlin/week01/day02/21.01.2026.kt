package week01.day02
// #1 - безопасный парсинг Int
fun strToInt (str : String?): Int? {
    return str?.toIntOrNull()
}

// #2 - “приветствие”
fun greeting(name : String?): String {
    return if (name.isNullOrBlank()) "Hello, stranger" else "Hello, $name"
}

// #3.1 - длина строки
fun safeLength(s: String?): Int {
    return if (s.isNullOrEmpty()) 0 else s.length
}

// #3.2 - длина строки с использованием элвиса
fun safeLengthElvis(s: String?): Int {
    return s?.length ?: 0
}

// #4 - цепочка вызовов
data class Engine(val hp: Int?)
data class Car(val model: String?, val engine: Engine?)

fun hpLine(car: Car?): String {
    val hp = car?.engine?.hp
    return "HP: ${hp ?: "unknown"}"
}

// #5 - let
fun hpLineLet(car: Car?) : String {
    return car?.engine?.hp?.let { hp ->
        "HP: $hp"
    } ?: "HP: unknown"
}

// UI-модель
data class UserUi(val title: String, val subtitle: String)
data class User(val name: String?, val age: Int?)

fun mapToUi(user: User?): UserUi {
    // user == null
    val u = user ?: return UserUi("Guest", "Not logged in")

    // title from name (null or blank -> default)
    val title = u.name?.takeUnless { it.isBlank() } ?: "Unknown name"

    // subtitle from age (null -> ?)
    val subtitle = "Age: ${u.age?.toString() ?: "?"}"

    return UserUi(title, subtitle)
}

// #6 = unsafe (!!)
fun unsafeNameLength(user: User?): Int {
    return user!!.name!!.length
}

fun main() {
    println("#1")
    println(strToInt("1312312"))

    println("\n#2")
    println(greeting(""))

    println("\n#3.1")
    println(safeLength("Hello"))

    println("\n#3.2")
    println(safeLengthElvis("HelloWorld"))

    println("\n#4")
    val b47 = Engine(190)
    val bmwG20320d = Car("BMW", b47)
    println(hpLine(bmwG20320d))

    println("\n#5")
    println(hpLineLet(bmwG20320d))

    println("\n#6")
    println(unsafeNameLength(User("Dima", 22)))

    println("\n#UI-модель")
    println(mapToUi(null))
    println(mapToUi(User("", null)))
    println(mapToUi(User("Dima", 22)))
}