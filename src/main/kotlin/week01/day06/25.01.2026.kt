package week01.day06

import week01.day03.Car
import week01.day03.Engine
import week01.day03.Owner

fun main() {
    // Список машин для решения сегодняшних заданий
    val cars: List<Car> = listOf(
        Car(
            vin = "WBA00000000000001",
            model = "BMW G20 320d",
            year = 2019,
            mileageKm = 160_000,
            engine = Engine(190, 2.0, "D"),
            owner = Owner(name = "Dima", phone = null, experienceYears = 3)
        ),
        Car(
            vin = "WDD00000000000002",
            model = "Mercedes C200",
            year = 2016,
            mileageKm = 95_000,
            engine = Engine(184, 2.0, "P"),
            owner = Owner(name = "Misha", phone = "+49-111", experienceYears = 2)
        ),
        Car(
            vin = "WAU00000000000003",
            model = "Audi A4 2.0T",
            year = 2014,
            mileageKm = 210_000,
            engine = Engine(225, 2.0, "P"),
            owner = null
        ),
        Car(
            vin = "JHM00000000000004",
            model = "Honda Civic",
            year = 2008,
            mileageKm = 180_000,
            engine = Engine(140, 1.8, "P"),
            owner = Owner(name = "Nikita", phone = "+49-222", experienceYears = 7)
        ),
        Car(
            vin = "WVW00000000000005",
            model = "VW Golf",
            year = 2012,
            mileageKm = 130_000,
            engine = Engine(122, 1.4, "P"),
            owner = null
        ),
        Car(
            vin = "WBA00000000000006",
            model = "BMW F30 320i",
            year = 2015,
            mileageKm = 88_000,
            engine = Engine(184, 2.0, "P"),
            owner = Owner(name = "Alex", phone = null, experienceYears = 5)
        ),
        Car(
            vin = "WBA00000000000007",
            model = "BMW G20 320d",
            year = 2020,
            mileageKm = 42_000,
            engine = Engine(190, 2.0, "D"),
            owner = Owner(name = "Dima", phone = "+49-333", experienceYears = 3)
        ),
        Car(
            vin = "WAU00000000000008",
            model = "Audi Q5 2.0T",
            year = 2018,
            mileageKm = 105_000,
            engine = Engine(252, 2.0, "P"),
            owner = Owner(name = "Misha", phone = null, experienceYears = 2)
        ),
        Car(
            vin = "5YJ00000000000009",
            model = "Tesla Model 3",
            year = 2021,
            mileageKm = 60_000,
            engine = Engine(283, 0.0, "E"),
            owner = Owner(name = "Kate", phone = "+49-444", experienceYears = 1)
        ),
        Car(
            vin = "WDD00000000000010",
            model = "Mercedes C200",
            year = 2017,
            mileageKm = 155_000,
            engine = Engine(184, 2.0, "P"),
            owner = null
        ),

        // VIN дубликаты (для задания про дубли)
        Car(
            vin = "WBA00000000000006", // дубль VIN (как у BMW F30 выше)
            model = "BMW F30 320i",
            year = 2015,
            mileageKm = 88_000,
            engine = Engine(184, 2.0, "P"),
            owner = Owner(name = "Alex", phone = null, experienceYears = 5)
        ),
        Car(
            vin = "WAU00000000000003", // дубль VIN (как у Audi A4 выше)
            model = "Audi A4 2.0T",
            year = 2014,
            mileageKm = 210_000,
            engine = Engine(225, 2.0, "P"),
            owner = null
        )
    )

    println("Cars.size = ${cars.size}")
    println()

    // Задача 1 - Print summary (List)
    for (car in cars) {
        println(car)
    }
    println()

    // Задача 2 - Filter (List -> List)
    val filtered = cars.filter { it.mileageKm < 100000 }
    val vins = filtered.map { it.vin }

    println("Count ${filtered.size}; VIN: $vins")
    println()

    // Задача 3 - Map/Transform (List -> List)
    val lineCars = cars.map { "${it.model} ${it.year}: ${it.mileageKm} km" }
    println(lineCars)
    println()

    // Задача 4 - Find min/max (List)
    var minYearCar = cars[0]
    var maxMileageKmCar = cars[0]
    for (car in cars) {

        if (car.year < minYearCar.year) {
            minYearCar = car
        }

        if (car.mileageKm > maxMileageKmCar.mileageKm) {
            maxMileageKmCar = car
        }
    }
    println(minYearCar)
    println(maxMileageKmCar)
    println()

    // Задача 5 - Set уникальных моделей (List -> Set)
    val modelSet = cars.map { it.model }.toSet()
    println("Unique models count = ${modelSet.size}")
    for (m in modelSet) println(m)
    println()

    // Задача 6 - Проверка дублей VIN (List -> Set + logic)
    var duplicateVin = 0
    val seen = mutableSetOf<String>() // List перекидываю в set. List это линейный поиск O(n), значит весь цикл снова станет O(n²) на больших данных.
    val dups = mutableSetOf<String>() // Тут тоже самое делаю что и с seen
    for (car in cars) {
        // получается, что нам надо сравнивать каждый vin со всеми возможными и так для всех O(n²) ?
        // нужно придумать способ быстрее...
        if (car.vin in seen) {
            dups.add(car.vin)
            duplicateVin++
        } else seen.add(car.vin)
    }
    println("Количество дубликатов: $duplicateVin")
    println(dups)
    println()

    // Задача 7 - Map VIN -> Car (индекс)
    val mapVinCar = mutableMapOf<String, Car>()
    for (car in cars) {
        mapVinCar[car.vin] = car
    }

    for ((vin, car) in mapVinCar) {
        println("$vin -> ${car.model} ${car.year}")
    }

    println("Тест на null")
    val existingVin = cars[0].vin
    val missingVin = "ABOBA3000"
    println("Проверка по VIN первой машины: ${mapVinCar[existingVin]}")
    println("Проверка по VIN ABOBA3000:  ${mapVinCar[missingVin]}")
    println()
}
