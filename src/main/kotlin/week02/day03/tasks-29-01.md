## Task 1 - Generic Box

```kotlin
class Box<T>(val value: T)
```

Создай:

* `Box<Int>`
* `Box<String>`

Выведи `value`.

---

## Task 2 - Generic функция

Напиши функцию:

```kotlin
fun <T> printTwice(value: T)
```

Она должна печатать значение **два раза**.

---

## Task 3 - Ограничение типа

Напиши функцию:

```kotlin
fun <T : Number> square(x: T): Double
```

Возвращает квадрат числа.

---

## Task 4 - `out` (чтение)

```kotlin
open class Animal
class Cat : Animal()
```

Создай `List<Cat>` и передай её в функцию:

```kotlin
fun feedAll(animals: List<Animal>)
```

---

## Task 5 - `in` (запись)

```kotlin
fun addCat(list: MutableList<in Cat>)
```

Передай туда `MutableList<Animal>`.