package exercizes.chap7

fun <T> tryAndCatch(block: () -> T): Outcome<ThrowableError, T> = try {
    Success(block())
} catch (t: Throwable) {
    Failure(ThrowableError(t))
}

data class ThrowableError(val t: Throwable) : OutcomeError {
    override val msg: String = t.message.orEmpty()
}

fun todayGreetings(dateString: String) =
    tryAndCatch { LocalDate.parse(dateString) }
        .transform { "Today is $it" }
        .recover { "Error parsing the date ${it.msg}" }