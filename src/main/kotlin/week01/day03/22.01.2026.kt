package week01.day03


// #1
data class Engine (
    val hp: Int,
    val volume: Double,
    val type: String
)

data class Owner (
    val name: String,
    val phone: String? = null,
    val experienceYears: Int
)

data class Car (
    val vin: String,
    val model: String,
    val year: Int,
    val mileageKm: Int,
    val engine: Engine,
    val owner: Owner?
)

data class ServiceRecord(
    val date: String,
    val mileageKm: Int,
    val items: List<String>,
    val totalCost: Int
)

data class Garage(
    val cars: List<Car>
)

fun main() {

    // #2
    val car1 = Car("ABCD1234", "BMW", 2019, 160000, Engine(190, 2.0, "D"), Owner(name = "Dmitry", experienceYears = 3))
    val car2 = Car("ABCD1234", "BMW", 2019, 160000, Engine(190, 2.0, "D"), Owner(name = "Dmitry", experienceYears = 3))
    println(car1 == car2) // true
    val testHashSet1 = hashSetOf<Car>(car1, car2)
    println(testHashSet1.size) // 1
    val testHashMap1 = hashMapOf<Car, Int>()
    testHashMap1[car1] = 999
    println(testHashMap1[car2])
}