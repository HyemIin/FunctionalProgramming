package exercizes.chap5

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

interface Monoid<T> {
    val identity: T
    val calculation: (T, T) -> T

    fun List<T>.fold(): T = this.fold(identity, calculation)
}

fun <T> Monoid(identity: T, calculation: (T, T) -> T): Monoid<T> = object : Monoid<T> {
    override val identity: T = identity
    override val calculation: (T, T) -> T = calculation
}
