package exercizes.chap5

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

data class Elevator(val floor: Int)
sealed class Direction
object Up : Direction()
object Down : Direction()

val values = listOf(Up, Up, Down, Up, Down, Down, Up, Up, Up, Down)
val tot = values.fold(Elevator(0)){
    acc: Elevator, direction: Direction ->
    when (direction) {
        is Up -> acc.copy(floor = acc.floor + 1)
        is Down -> acc.copy(floor = acc.floor - 1)
    }
}
@Test
fun test() {
    expectThat(tot).isEqualTo(Elevator(2))
}
