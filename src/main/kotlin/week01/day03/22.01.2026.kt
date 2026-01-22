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

// #3
fun addMileage(car: Car, km: Int): Car {
    return car.copy(mileageKm = car.mileageKm + km)
}


fun sellCar(car: Car, newOwner: Owner?): Car {
    return car.copy(owner = newOwner)
}


fun upgradeEngine(car: Car, hpDelta: Int): Car {
    return car.copy(engine = car.engine.copy(hp = car.engine.hp + hpDelta))
}


fun main() {

    // #2
    val car1 = Car("ABCD1234", "BMW", 2019, 160000, Engine(190, 2.0, "D"), Owner(name = "Dmitry", experienceYears = 3))
    val car2 = Car("ABCD1234", "BMW", 2019, 160000, Engine(190, 2.0, "D"), Owner(name = "Dmitry", experienceYears = 3))
    println(car1 == car2) // true
    val testHashSet1 = hashSetOf<Car>(car1, car2)
    println(testHashSet1.size) // 1
    val testHashMap1 = hashMapOf<Car, Int>()
    testHashMap1[car1] = 999
    println(testHashMap1[car2]) // 999

    // #3
    println("car1 = ${addMileage(car1, 100)}")
    println("car2 = $car2")

    val mishaOwner = Owner("Misha", experienceYears = 2)
    val sold = sellCar(car1, mishaOwner)
    println("old car1 = $car1")
    println("new car  = $sold")

    println("upgrade engine ${upgradeEngine(car1, 197)}")
    println("car1 = $car1")

    // #4
    val b47 = Engine(190, 2.0, "D")
    val (hp, volume, type) = b47
    val b47Hp = b47.component1()
    val b47Volume = b47.component2()
    val b47Type = b47.component3()

}