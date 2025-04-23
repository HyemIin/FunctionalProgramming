package exercizes.chap7

interface OutcomeError {
    val msg: String
}

sealed class Outcome<out E : OutcomeError, out T> {
    fun <U> transform(f: (T) -> U): Outcome<E, U> =
    when (this) {
        is Success -> f(value).asSuccess()
        is Failure -> this
    }
}

data class Success<T> internal constructor(val value: T):
    Outcome<Nothing, T>()
data class Failure<E : OutcomeError> internal constructor(val error: E):
Outcome<E, Nothing>()

fun <E : OutcomeError> T.asFailure(): Outcome<E, Nothing> = Failure(this)
fun <T> T.asSuccess(): Outcome<Nothing, T> = Success(this)