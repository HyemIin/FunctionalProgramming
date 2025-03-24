import exercizes.chap2.FunStack
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.math.BigDecimal

class RpnCalc {

    val operationMap = mapOf<String, (BigDecimal, BigDecimal) -> BigDecimal>(
        "+" to BigDecimal::plus,
        "-" to BigDecimal::minus,
        "*" to BigDecimal::multiply,
        "/" to BigDecimal::divide
    )

    val funStack = FunStack<BigDecimal>()

    fun calc(expr: String): BigDecimal {

        return expr.split(" ")
            .fold(funStack,::reduce)
            .pop().first
    }

    fun reduce(stack: FunStack<BigDecimal>, token: String): FunStack<BigDecimal> {
        return if (token in operationMap.keys) {
            val (a, aStack) = stack.pop()
            val (b,bStack) = aStack.pop()
            bStack.push(operationMap[token]!!(a, b))
        } else {
            stack.push(BigDecimal(token))
        }
    }

    @Test
    fun `rpn calc`() {
        val rpnCalc = RpnCalc()
        expectThat(rpnCalc.calc("1 2 + 3 *")).isEqualTo(BigDecimal("9"))
    }
    @Test
    fun `rpn calc2`() {
        val rpnCalc = RpnCalc()
        val res1 = "4 5 +" // 9.0
        val res2 = "6 2 /" // 3.0
        val res3 = "5 6 2 1 + / *"// 10.0
        val res4 = "2 5 * 4 + 3 2 * 1 + /"// 2.0
        expectThat(rpnCalc.calc(res1)).isEqualTo(BigDecimal("9"))
        expectThat(rpnCalc.calc(res2)).isEqualTo(BigDecimal("3"))
//        expectThat(rpnCalc.calc(res3)).isEqualTo(BigDecimal("10"))
//        expectThat(rpnCalc.calc(res4)).isEqualTo(BigDecimal("2"))
    }

}

