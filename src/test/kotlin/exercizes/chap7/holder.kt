package exercizes.chap7

data class Holder<T>(private val value: T) {
    fun <U> transform(f: (T) -> U): Holder<U> = Holder( f(value) )
    companion object {
        fun <T, U> lift(f: (T) -> U): (Holder<T>) -> Holder<U> =
            { c: Holder<T> -> c.transform(f) }
    }
}