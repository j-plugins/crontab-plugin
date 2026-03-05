package com.github.xepozz.crontab.language.parser

import com.github.xepozz.crontab.language.psi.CrontabTypes
import com.intellij.lexer.Lexer
import com.intellij.psi.tree.IElementType
import com.intellij.testFramework.LexerTestCase

class CrontabLexerTest : LexerTestCase() {
    override fun createLexer(): Lexer = CrontabLexerAdapter()

    override fun getDirPath(): String = ""

    private fun collectTokens(text: String): List<Pair<IElementType, String>> {
        val lexer = createLexer()
        lexer.start(text)
        val tokens = mutableListOf<Pair<IElementType, String>>()
        while (lexer.tokenType != null) {
            tokens.add(lexer.tokenType!! to text.substring(lexer.tokenStart, lexer.tokenEnd))
            lexer.advance()
        }
        return tokens
    }

    private fun contentTokens(text: String) = collectTokens(text).filter { it.first == CrontabTypes.CONTENT }

    private fun eolTokens(text: String) = collectTokens(text).filter { it.first == CrontabTypes.EOL }

    fun testSimpleCommand() {
        val tokens = contentTokens("* * * * * echo hello")
        assertEquals(1, tokens.size)
        assertEquals("echo hello", tokens[0].second)
    }

    fun testBackslashContinuation() {
        val tokens = contentTokens(
            """
            * * * * * echo hello \
              world
        """.trimIndent()
        )
        assertEquals(1, tokens.size)
        assertEquals("echo hello \\\n  world", tokens[0].second)
    }

    fun testMultipleBackslashContinuations() {
        val tokens = contentTokens(
            """
            * * * * * echo \
              hello \
              world
        """.trimIndent()
        )
        assertEquals(1, tokens.size)
        assertEquals("echo \\\n  hello \\\n  world", tokens[0].second)
    }

    fun testBackslashContinuationFollowedByNextLine() {
        val text = """
            * * * * * echo \
              hello
            * * * * * second
        """.trimIndent()
        val content = contentTokens(text)
        assertEquals(2, content.size)
        assertEquals("echo \\\n  hello", content[0].second)
        assertEquals("second", content[1].second)
        assertEquals(1, eolTokens(text).size)
    }

    fun testNoBackslashContinuation() {
        val text = """
            * * * * * echo hello
            * * * * * world
        """.trimIndent()
        val content = contentTokens(text)
        assertEquals(2, content.size)
        assertEquals("echo hello", content[0].second)
        assertEquals("world", content[1].second)
        assertEquals(1, eolTokens(text).size)
    }

    fun testBackslashInMiddleOfCommand() {
        val tokens = contentTokens("""* * * * * echo he\llo""")
        assertEquals(1, tokens.size)
        assertEquals("echo he\\llo", tokens[0].second)
    }

    fun testCommandEndingWithBackslashAtEof() {
        val tokens = contentTokens("""* * * * * echo hello\""")
        assertEquals(1, tokens.size)
        assertEquals("echo hello\\", tokens[0].second)
    }

    fun testThreeSimpleCommands() {
        val text = """
            * * * * * first
            0 12 * * * second
            30 6 1 * * third
        """.trimIndent()
        val content = contentTokens(text)
        assertEquals(3, content.size)
        assertEquals("first", content[0].second)
        assertEquals("second", content[1].second)
        assertEquals("third", content[2].second)
        assertEquals(2, eolTokens(text).size)
    }

    fun testContinuationBetweenSimpleCommands() {
        val text = """
            * * * * * first
            0 12 * * * echo \
              hello
            30 6 1 * * third
        """.trimIndent()
        val content = contentTokens(text)
        assertEquals(3, content.size)
        assertEquals("first", content[0].second)
        assertEquals("echo \\\n  hello", content[1].second)
        assertEquals("third", content[2].second)
        assertEquals(2, eolTokens(text).size)
    }

    fun testMultipleContinuationCommands() {
        val text = """
            * * * * * a \
              b
            0 12 * * * c \
              d \
              e
        """.trimIndent()
        val content = contentTokens(text)
        assertEquals(2, content.size)
        assertEquals("a \\\n  b", content[0].second)
        assertEquals("c \\\n  d \\\n  e", content[1].second)
        assertEquals(1, eolTokens(text).size)
    }

    fun testCommandsWithCommentsAndVariables() {
        val text = """
            # comment
            PATH=/usr/bin
            * * * * * run \
              cmd
            0 12 * * * other
        """.trimIndent()
        val content = contentTokens(text)
        assertEquals(3, content.size)
        assertEquals("/usr/bin", content[0].second)
        assertEquals("run \\\n  cmd", content[1].second)
        assertEquals("other", content[2].second)

        val comments = collectTokens(text).filter { it.first == CrontabTypes.SINGLE_COMMENT }
        assertEquals(1, comments.size)
    }

    fun testCommandsWithShortcutSchedule() {
        val text = """
            @reboot startup
            * * * * * every_minute \
              continued
            @daily cleanup
        """.trimIndent()
        val commandContents = contentTokens(text).map { it.second }
        assertTrue("startup" in commandContents)
        assertTrue("every_minute \\\n  continued" in commandContents)
        assertTrue("cleanup" in commandContents)
    }
}
