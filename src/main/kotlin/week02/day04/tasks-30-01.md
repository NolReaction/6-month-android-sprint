## Task 1 - Модель

**Сделай:**

* `data class Habit(val id: String, val title: String, val doneCount: Int)`

> `doneCount` потом будем увеличивать через `copy`.

---

## Task 2 - Интерфейс репозитория

**Сделай интерфейс:**

* `add(habit)`
* `getAll()`
* `findById(id)`
* `update(habit)`

---

## Task 3 - InMemory реализация

**Сделай класс**, который хранит `MutableMap<String, Habit>`.

---

## Task 4 - Service (SRP)

**Сделай сервис**, который:

1. `createHabit(title)` → создаёт id, добавляет в repo
2. `markDone(id)` → берёт habit, делает `copy(doneCount = doneCount + 1)`, сохраняет
3. `listHabits()` → отдаёт список

Сервис **не печатает**, только возвращает данные/результат.

---

## Task 5 - Console UI

Простейшее меню (цикл):

* `1` добавить
* `2` список
* `3` отметить выполнено
* `0` выход

UI только читает строки и вызывает сервис.