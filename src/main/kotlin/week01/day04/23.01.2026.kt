package week01.day04

// #1
enum class Gear(val ratio: Double) {
    PARK(0.0),
    REVERSE(-3.2),
    NEUTRAL(0.0),
    DRIVE(3.5);

    fun canMove(): Boolean {
        return (this == DRIVE) || (this == REVERSE)
    }
}


// #2
fun gearHint(gear: Gear): String {
    val hint = when(gear) {
        Gear.PARK -> "Parking"
        Gear.REVERSE -> "Going back"
        Gear.NEUTRAL -> "Parking with no brakes :D"
        Gear.DRIVE -> "Go ahead"
    }
    return hint
}


// #3
sealed class LoadResult {
    object Loading: LoadResult()
    data class Success(val data: String): LoadResult()
    data class Error(val message: String, val code: Int?): LoadResult()
}

fun render(result: LoadResult): String {
    return when(result) {
        LoadResult.Loading -> "loading"
        is LoadResult.Success -> "200: ${result.data}"
        is LoadResult.Error -> "500: ${result.message} (${result.code})"
    }
}

// #4
sealed class UiEvent {
    object Back: UiEvent()
    data class Toast(val text: String): UiEvent()
    data class Navigate(val route: String): UiEvent()
}

fun handle(event: UiEvent): String {
    return when(event) {
        UiEvent.Back -> "NAVIGATE:/profile"
        is UiEvent.Toast -> "This is TOAST: ${event.text}"
        is UiEvent.Navigate -> "NAVIGATE:/${event.route}"
    }
}

fun main() {
    // #1
    for (g in Gear.entries) {
        println("${g.name} ratio = ${g.ratio}; Can move = ${g.canMove()}")
    }
    println()

    // #2
    for (g in Gear.entries) {
        println("${g.name} hint = ${gearHint(g)}")
    }
    println()


    // #3
    val r1: LoadResult = LoadResult.Loading
    val r2: LoadResult = LoadResult.Success("Hello")
    val r3: LoadResult = LoadResult.Error("Not found", 404)

    println(render(r1))
    println(render(r2))
    println(render(r3))
    println()

    // #4
    val h1 = UiEvent.Back
    val h2 = UiEvent.Toast("HELLO baby :D")
    val h3 = UiEvent.Navigate("menu")

    println(handle(h1))
    println(handle(h2))
    println(handle(h3))
    println()

    // #5 Enum vs Sealed (file)
}