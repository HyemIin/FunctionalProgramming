package exercizes.chap2

import com.ubertob.pesticide.core.DDT
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
class FunStackTest() {

    @Test
    fun `push into the stack`() {
        val stack1 = FunStack<Char>()
        val stack2 = stack1.push('A')
        expectThat(stack1.size()).isEqualTo(0)
        expectThat(stack2.size()).isEqualTo(1)
    }

    @Test
    fun `push push pop`() {
        val (b, stack) = FunStack<Char>()
            .push('A')
            .push('B')
            .pop()
        expectThat(stack.size()).isEqualTo(1)
        expectThat(b).isEqualTo('B')
    }
}

class FunStack<T>(private val items: List<T> = emptyList()) {
    fun push(text: T): FunStack<T> = FunStack(listOf(text) + items)
    fun pop(): Pair<T, FunStack<T>> = items.first() to FunStack(items.drop(1))
    fun size(): Int = items.size
}