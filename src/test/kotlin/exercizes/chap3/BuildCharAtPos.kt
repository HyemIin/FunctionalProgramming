package exercizes.chap3

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo


class BuildCharAtPos() {

    fun buildCharAtPos(s: String): (Int) -> Char = { index -> s[index] }

    @Test
    fun buildCharAtPosTest() {
        val myCharAtPos = buildCharAtPos("Kotlin")

        expectThat(myCharAtPos(0)).isEqualTo('K')
        expectThat(myCharAtPos(3)).isEqualTo('l')
        expectThat(myCharAtPos(5)).isEqualTo('n')
    }

}
