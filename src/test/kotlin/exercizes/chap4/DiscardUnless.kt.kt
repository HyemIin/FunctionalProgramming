package exercizes.chap4

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

data class ToDoItem(
    val description: String,
    val status: ToDoStatus = ToDoStatus.Done
)

enum class ToDoStatus { Todo, InProgress, Done, Blocked }


fun <T> T.discardUnless(predicate: T.() -> Boolean): T? =
    if (predicate(this)) this else null


class test() {
    @Test
    fun test1() {
        val itemInProgress = ToDoItem("doing something",
            status= ToDoStatus.InProgress)
        val itemBlocked = ToDoItem("must do something",
            status= ToDoStatus.Blocked)

        expectThat(
            itemInProgress.discardUnless { status == ToDoStatus.InProgress }
        ).isEqualTo(itemInProgress)

        expectThat(
            itemBlocked.discardUnless { status == ToDoStatus.InProgress }
        ).isEqualTo(null)
    }

}
