package week02.day04

data class Habit(
    val id: String,
    val title: String,
    val doneCount: Int
)

interface HabitRepository {
    fun add(habit: Habit)
    fun getAll(): List<Habit>
    fun findById(id: String): Habit?
    fun update(habit: Habit)
}

class InMemoryHabitRepository : HabitRepository {
    override fun add(habit: Habit) {
        mp[habit.id] = habit
    }

    override fun getAll(): List<Habit> {
        return mp.values.toList()
    }

    override fun findById(id: String): Habit? {
        return mp[id]
    }

    override fun update(habit: Habit) {
        mp[habit.id] = habit // Как будто то же самое что и add, но на сколько я понимаю, разница здесь чисто семантическая. Map и так перезапишет значение, если оно будет одинаковое.
    }

    private val mp = mutableMapOf<String, Habit>()
}

class HabitService(private val repo: HabitRepository) {
    private var nextId = 0
    fun createHabit(title: String): Habit {
        nextId++
        val newHabit = Habit(nextId.toString(), title, 0)
        repo.add(newHabit)
        return newHabit
        // 1) создать Habit (сгенерировать id, doneCount=0)
        // 2) repo.add(habit)
        // 3) вернуть habit
    }

    fun markDone(id: String): Habit? {
        val searchedHabit = repo.findById(id) ?: return null
        val newHabit = searchedHabit.copy(doneCount = searchedHabit.doneCount + 1)
        repo.update(newHabit)
        return newHabit
        // 1) найти habit: repo.findById(id)
        // 2) если null -> вернуть null
        // 3) иначе сделать новыйHabit = habit.copy(doneCount = habit.doneCount + 1)
        // 4) repo.update(newHabit)
        // 5) вернуть newHabit
    }

    fun listHabits(): List<Habit> {
        return repo.getAll()
        // вернуть repo.getAll()
    }
}

fun main() {
    val repo = InMemoryHabitRepository()
    val service = HabitService(repo)
    while (true) {
        println(
            """
        1. add
        2. list
        3. mark done
        0. exit
    """.trimIndent()
        )
        val userEnter = readln().toIntOrNull()
        when (userEnter) {
            1 -> {
                print("Print title > ")
                val created = service.createHabit(readln())
                println("Service created. ID: ${created.id}, title: ${created.title}")
            }
            2 -> println(service.listHabits())
            3 -> {
                print("Print id > ")
                val serDone = service.markDone(readln())
                println(serDone)
            }
            0 -> break
            else -> {
                println("Incorrect enter, try again.")
            }
        }
    }
}