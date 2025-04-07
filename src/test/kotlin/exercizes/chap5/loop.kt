package exercizes.chap5

fun Int.collatz() = collatzR(listOf(), this)
tailrec fun collatzR(acc: List<Int>, x: Int): List<Int> {
    val currentAcc = acc + x
    if (x == 1) return currentAcc
    else if (x % 2 == 0) {
        return collatzR(currentAcc, x / 2)
    }
    else {
        return collatzR(currentAcc, 3 * x + 1)
    }
}

class SomeClass {
    private val factor: Int = 1

    fun calc(value: Int): Int {
        return value * this.factor
    }
}
