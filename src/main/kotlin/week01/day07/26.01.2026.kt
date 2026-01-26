package week01.day07

import java.sql.Time

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
        val startedAt: Time
    ) : TaskStatus()

    data class Done(
        val endAt: Time,
        val spentTime: Time
    ) : TaskStatus()

    data class Removed(
        val removedAt: Time,
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
    while (true) {
        helloMessage()
    }
}