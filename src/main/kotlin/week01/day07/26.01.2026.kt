package week01.day07

import java.time.Instant
import java.time.Duration

val aliases = mapOf(
    "exit" to "exit", "quit" to "exit",
    "del" to "del", "delete" to "del", "dl" to "del",
    "help" to "help", "h" to "help"
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
    object New : TaskStatus()

    data class InProgress(
        val startedAt: Instant
    ) : TaskStatus()

    data class Done(
        val finishedAt: Instant,
        val spentTime: Duration
    ) : TaskStatus()

    data class Removed(
        val removedAt: Instant,
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

fun addTask() {

}

fun listTasks() {

}

fun showTasksById() {

}

fun startTask() {

}

fun doneTask() {

}

fun deleteTask() {

}

fun statisticTask() {

}

fun main() {
    helloMessage()

    while (true) {
        print("> ")
        val line = readln().trim()
        if (line.isEmpty()) continue

        val parts = line.split(Regex("\\s+"))
        val rawCmd = parts.getOrNull(0)?.lowercase() ?: ""
        val cmd = aliases[rawCmd] ?: rawCmd // command (example add)
        val arg = parts.getOrNull(1) // id (example - 12)

        when (cmd) {
            "help" -> helloMessage()
            "exit" -> break
            "list" -> listTasks()
            "add" -> addTask()
            "find" -> showTasksById(/* id */)
            "start" -> startTask(/* id */)
            "done" -> doneTask(/* id */)
            "del" -> deleteTask(/* id */)
            "stat" -> statisticTask()
            else -> println("Unknown command: $rawCmd")
        }
    }
}