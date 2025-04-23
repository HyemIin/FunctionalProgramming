package exercizes.chap7

fun <F : OutcomeError> transformFailure(f: (E) -> F): Outcome<F, T> = when (this) {
    is Success -> Success(value)
    is Failure -> Failure(f(error))
}

fun sendEmail(fileName: String): Outcome<EmailError, Unit> =
    readFile(fileName) // Outcome<FileError, String>
        .transformFailure { EmailError(it.msg) } // Outcome<EmailError, String>
        .onFailure { return@sendEmail it.asFailure() } // Early return if Failure
        .let(::sendTextByEmail)