package week01.day05

import week01.day03.Car
import week01.day03.Engine
import week01.day03.Owner

// extension (a)
fun Car.isOld(): Boolean {
    return year < 2010
}

// extension (b)
fun String?.orGuest(): String {
    return if (this.isNullOrBlank()) "Guest" else this
}

// also: встроенная проверка-инвариант
fun Car.debugCheck(): Car {
    return this.also {
        if (mileageKm >= 0) println("check mileage ok" )
        if (mileageKm < 0) println("check mileage FAIL")
    }
}


// with: короткое описание двигателя
fun engineSummary(engine: Engine): String {
    return with(engine) {"Engine hp: ${this.hp}, volume: ${this.volume}, type: ${this.type}"}
}


// let: два значения через Pair (без if)
fun String.toWordsCount(): Int {
    val parts = this.split(" ")
    var count = 0
    for (p in parts) {
        if (p.isNotEmpty()) count++
    }
    return count
}

fun main() {
    // extensions
    val car1 = Car("PEPE228", "Fa", 100, 500000, Engine(10000, 1000.0, "PokoProMax4Ultra"), Owner("Me", "+5", 1000))
    if (car1.isOld()) {
        println("U dinosaur, congratulations !")
    }
    println()

    val imDinosaur = "imDinosaur"
    val imGuest = null
    println(imDinosaur.orGuest())
    println(imGuest.orGuest())
    println()

    // Scope functions
    // apply (чтоб не писать по несколько раз carProfile.put я использую apply). Тут даже this не понадобился.
    val carProfile = mutableMapOf<String, Any>()
    carProfile.apply {
        put("vin", car1.vin)
        put("model", car1.model)
        put("hp", car1.engine.hp)
    }
    println(carProfile)

    // also: лог без поломки цепочки
    val resultText = car1
        .also { println("DEBUG before: vin=${it.vin}, model=${it.model}, year=${it.year}") }
        .also { println("DEBUG same object? ${it === car1}") } // док-во: это тот же объект
        .run { "OK -> $model ($year), mileage=$mileageKm km, hp=${engine.hp}" } // результат String
    println(resultText)
    println()

    // also: встроенная проверка-инвариант
    car1.debugCheck().debugCheck()
    println()

    // with: короткое описание двигателя
    val b58 = Engine(387, 3.0, "B")
    println(engineSummary(b58))
    println()

    // let: два значения через Pair (без if)
    val testStr = "hello kotlin scope functions"
        .let { it.toWordsCount() }
        .let { "Words = $it" }
    println(testStr)
}
