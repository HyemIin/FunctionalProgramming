package exercizes.chap5

import org.junit.jupiter.api.Test
import strikt.api.expectThat

sealed class JsonCompactor{
    abstract val jsonCompacted: String
    abstract fun compact(c: Char): JsonCompactor
}

data class InQuotes(override val jsonCompacted: String) : JsonCompactor() {
    override fun compact(c: Char): JsonCompactor = when (c) {
        '"' -> OutQuotes(jsonCompacted + c)
        '\\' -> Escaped(jsonCompacted + c)
        else -> InQuotes(jsonCompacted + c)
    }
}

// 따옴표 밖에 있는 상태
data class OutQuotes(override val jsonCompacted: String) : JsonCompactor() {
    override fun compact(c: Char): JsonCompactor = when {
        c == '"' -> InQuotes(jsonCompacted + c)
        c.isWhitespace() -> this
        else -> OutQuotes(jsonCompacted + c)
    }
}

data class Escaped(override val jsonCompacted: String) : JsonCompactor() {
    override fun compact(c: Char): JsonCompactor = InQuotes(jsonCompacted + c)
}

val jsonText = """{ "my greetings" : "hello world! \"How are you?\"" }"""
val expected = """{"my greetings":"hello world! \"How are you?\""}"""
