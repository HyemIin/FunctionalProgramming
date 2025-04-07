package exercizes.chap4


typealias FUN<A, B> = (A) -> B

infix fun <A: Any, B: Any, C: Any> FUN<A, B?>.andUnlessNull(
    other: FUN<B, C?>): FUN<A, C?> = { a: A ->
    val b = this(a)
    if (b != null) other(b) else null
}
