package week01.day07

import java.time.Instant
import java.time.Duration

val aliases = mapOf(
    "help" to "help", "h" to "help",
    "list" to "list", "l" to "list",
    "add" to "add", "ad" to "add",
    "find" to "find", "f" to "find",
    "start" to "start", "star" to "start",
    "done" to "done",
    "del" to "del", "delete" to "del", "dl" to "del",
    "stat" to "stat",
    "exit" to "exit", "quit" to "exit", "q" to "exit", "e" to "exit"
)

fun helloMessage() {
    println(
        """
            Commands:
                help            - show this help
                list            - print all Tasks
                add             - add Task
                find[id]        - find Task by id
                start[id]       - start Task with id
                done[id]        - done Task with id
                del[id]         - delete Task with id
                stat            - show statistic
                exit            - quit
                
                (example - find 12) (example - del 12) (example - exit)
        """.trimIndent()
    )
}

enum class Priority {
    LOW,
    MEDIUM,
    HIGH
}

sealed class TaskStatus {
    object New : TaskStatus() {
        override fun toString() = "NEW"
    }

    data class InProgress(
        val startedAt: Instant?
    ) : TaskStatus()

    data class Done(
        val finishedAt: Instant?,
        val spentTime: Duration?
    ) : TaskStatus()

    data class Removed(
        val removedAt: Instant?,
        val reason: String?
    ) : TaskStatus()
}

data class Task(
    val id: Int,
    val title: String,
    val priority: Priority,
    val notes: String?,
    val status: TaskStatus
)

fun addTask(taskList: MutableList<Task>): Task {
    // Выдаём новое id
    val id = nextId(taskList)

    // Выдаём title
    val title = run {
        while (true) {
            print("Title > ")
            val t = readln().trim()
            if (t.isBlank()) {
                println("Print Title")
                continue
            }
            return@run t
        }
        error("addTask: unreachable")
    }

    // Выдаём priority
    val priority: Priority
    while (true) {
        println(
            """
        Choose a priority:
            1.HIGH
            2.MEDIUM
            3.LOW
    """.trimIndent()
        )
        val response = readln().trim()
        priority = when(response) {
            "1" -> Priority.HIGH
            "2" -> Priority.MEDIUM
            "3" -> Priority.LOW
            else -> { println("Usage: 1 / 2 / 3"); continue }
        }
        break
    }

    // Выдаём notes (заметку)
    print("Note > ")
    val notes = readln().trim().ifEmpty { null }

    // Выдаём статус
    val status = TaskStatus.New

    // Добавляю новый task в хранилище и возвращаю task
    val task = Task(id, title, priority, notes, status)
    taskList.add(task)
    return task
}

fun listTasks(taskList: List<Task>) {
    for (task in taskList) println(task)
}

fun showTaskById(taskList: List<Task>, id: Int): Task? {
    return taskList.find { it.id == id }
}

fun startTask(taskList: MutableList<Task>, id: Int): Task? {
    val index = taskList.indexOfFirst { it.id == id }
    if (index == -1) return null

    val targetTask = taskList[index]
    if (targetTask.status !is TaskStatus.New) {
        return targetTask
    }

    val updated = targetTask.copy(
        status = TaskStatus.InProgress(Instant.now())
    )

    taskList[index] = updated
    return updated
}

fun doneTask(taskList: MutableList<Task>, id: Int): Task? {
    val index = taskList.indexOfFirst { it.id == id }
    if (index == -1) return null

    val targetTask = taskList[index]

    val inProgress = targetTask.status as? TaskStatus.InProgress
        ?: return targetTask

    val startedAt = inProgress.startedAt
        ?: return targetTask

    val finishedAt = Instant.now()
    val spentTime = Duration.between(startedAt, finishedAt)

    val updated = targetTask.copy(
        status = TaskStatus.Done(
            finishedAt,
            spentTime
        )
    )

    taskList[index] = updated
    return updated
}

fun deleteTask(taskList: MutableList<Task>, id: Int): Task? {
    val index = taskList.indexOfFirst { it.id == id }
    if (index == -1) return null

    val targetTask = taskList[index]

    if (targetTask.status is TaskStatus.Removed) return targetTask

    val updated = targetTask.copy(
        status = TaskStatus.Removed(
            removedAt = Instant.now(),
            reason = null // надо потом добавить вопрос пользователю
        )
    )

    taskList[index] = updated
    return updated
}

fun statisticTask(taskList: List<Task>): String {
    val total = taskList.size

    var new = 0
    var inProgress = 0
    var done = 0
    var removed = 0

    for (task in taskList) {
        when (task.status) {
            is TaskStatus.New -> new++
            is TaskStatus.InProgress -> inProgress++
            is TaskStatus.Done -> done++
            is TaskStatus.Removed -> removed++
        }
    }

    var high = 0
    var medium = 0
    var low = 0

    for (task in taskList) {
        when(task.priority) {
            Priority.HIGH -> high++
            Priority.MEDIUM -> medium++
            Priority.LOW -> low++
        }
    }

    var totalSpent = Duration.ZERO
    var doneWithTimeCount = 0

    for (task in taskList) {
        val st = task.status
        if (st is TaskStatus.Done) {
            val spent = st.spentTime
            if (spent != null) {
                totalSpent = totalSpent.plus(spent)
                doneWithTimeCount++
            }
        }
    }

    val avgSpent = if (doneWithTimeCount == 0) {
        Duration.ZERO
    } else {
        totalSpent.dividedBy(doneWithTimeCount.toLong())
    }

    val resultStr = """
        Total: $total
        NEW: $new
        IN_PROGRESS: $inProgress
        DONE: $done
        REMOVED: $removed

        By priority:
        HIGH: $high
        MEDIUM: $medium
        LOW: $low

        Time spent (DONE):
        Total: $totalSpent
        Avg per task: $avgSpent
    """.trimIndent()

    return resultStr
}

fun nextId(taskList: List<Task>): Int {
    val maxId = taskList.maxOfOrNull { it.id } ?: -1
    return maxId + 1
}

fun main() {
    helloMessage()

    // Хранение данных
    val taskList = mutableListOf<Task>(
        Task(0, "First task", Priority.HIGH, null, TaskStatus.Done(null, null))
    )

    while (true) {
        print("> ")

        // Ввод начальной команды
        val line = readln().trim()
        if (line.isEmpty()) continue
        val parts = line.split(Regex("\\s+"))
        val rawCmd = parts.getOrNull(0)?.lowercase() ?: ""
        val cmd = aliases[rawCmd] ?: rawCmd // command (example add)
        val arg = parts.getOrNull(1) // id (example - 12)
        val id = arg?.toIntOrNull()

        when (cmd) {
            "help" -> helloMessage()
            "exit" -> break
            "list" -> listTasks(taskList)
            "add" -> addTask(taskList).also {
                println("A new task has been created:")
                println(it)
            }
            "find" -> if (id == null) println("Usage: find <id>") else showTaskById(taskList, id).also {
                if (it == null) println("Non-existent id") else println(it)
            }
            "start" -> {
                if (id == null) println("Usage: start <id>")
                else startTask(taskList, id)?.also { println("Started: $it") }
                    ?: println("Non-existent id")
            }
            "done" -> {
                if (id == null) println("Usage: done <id>")
                else doneTask(taskList, id)?.also { println("Done: $it") }
                    ?: println("Non-existent id")
            }
            "del" -> {
                if (id == null) println("Usage: del <id>")
                else deleteTask(taskList, id)
                    ?.also { println("Removed: $it") }
                    ?: println("Non-existent id")
            }
            "stat" -> println(statisticTask(taskList))
            else -> println("Unknown command: $rawCmd")
        }
    }
}