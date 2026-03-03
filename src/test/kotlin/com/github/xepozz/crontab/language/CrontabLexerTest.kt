package com.github.xepozz.crontab.language

import com.github.xepozz.crontab.language.parser.CrontabLexerAdapter
import com.intellij.lexer.Lexer
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import com.intellij.testFramework.LexerTestCase

class CrontabLexerTest : LexerTestCase() {
    override fun createLexer(): Lexer = CrontabLexerAdapter()

    override fun getDirPath(): String = "src/test/testData/lexer"

    fun testSimpleCronExpression() {
        doTest(
            "* * * * * ls -la",
            """
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.CONTENT ('ls -la')
            """.trimIndent()
        )
    }

    fun testNumberSchedule() {
        doTest(
            "0 4 2 * * ls -la",
            """
            CrontabTokenType.NUMBER ('0')
            WHITE_SPACE (' ')
            CrontabTokenType.NUMBER ('4')
            WHITE_SPACE (' ')
            CrontabTokenType.NUMBER ('2')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.CONTENT ('ls -la')
            """.trimIndent()
        )
    }

    fun testVariableDefinition() {
        doTest(
            "PWD = '/my/dir'",
            """
            CrontabTokenType.IDENTIFIER ('PWD')
            WHITE_SPACE (' ')
            CrontabTokenType.EQUAL_SIGN ('=')
            WHITE_SPACE (' ')
            CrontabTokenType.CONTENT (''/my/dir'')
            """.trimIndent()
        )
    }

    fun testComment() {
        doTest(
            "# This is a comment",
            """
            CrontabTokenType.SINGLE_COMMENT ('# This is a comment')
            """.trimIndent()
        )
    }

    fun testTimeShortcutDaily() {
        doTest(
            "@daily mkdir \"dirname\"",
            """
            CrontabTokenType.AT ('@')
            CrontabTokenType.CONTENT ('daily')
            WHITE_SPACE (' ')
            CrontabTokenType.CONTENT ('mkdir "dirname"')
            """.trimIndent()
        )
    }

    fun testTimeShortcutReboot() {
        doTest(
            "@reboot /bin/bash echo",
            """
            CrontabTokenType.AT ('@')
            CrontabTokenType.CONTENT ('reboot')
            WHITE_SPACE (' ')
            CrontabTokenType.CONTENT ('/bin/bash echo')
            """.trimIndent()
        )
    }

    fun testNamedWeekday() {
        doTest(
            "* * * * MON ls",
            """
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.WEEKDAY ('MON')
            WHITE_SPACE (' ')
            CrontabTokenType.CONTENT ('ls')
            """.trimIndent()
        )
    }

    fun testNamedMonth() {
        doTest(
            "* * * JAN * ls",
            """
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.MONTH ('JAN')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.CONTENT ('ls')
            """.trimIndent()
        )
    }

    fun testRange() {
        doTest(
            "1-5 * * * * ls",
            """
            CrontabTokenType.NUMBER ('1')
            CrontabTokenType.HYPHEN ('-')
            CrontabTokenType.NUMBER ('5')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.CONTENT ('ls')
            """.trimIndent()
        )
    }

    fun testStep() {
        doTest(
            "*/5 * * * * ls",
            """
            CrontabTokenType.STAR ('*')
            CrontabTokenType.SLASH ('/')
            CrontabTokenType.NUMBER ('5')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.CONTENT ('ls')
            """.trimIndent()
        )
    }

    fun testList() {
        doTest(
            "1,2,3 * * * * ls",
            """
            CrontabTokenType.NUMBER ('1')
            CrontabTokenType.COMMA (',')
            CrontabTokenType.NUMBER ('2')
            CrontabTokenType.COMMA (',')
            CrontabTokenType.NUMBER ('3')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.CONTENT ('ls')
            """.trimIndent()
        )
    }

    fun testMultilineCommand() {
        doTest(
            "* * * * * echo hello \\\n  && echo world",
            """
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.CONTENT ('echo hello \\\n  && echo world')
            """.trimIndent()
        )
    }

    fun testMultilineCommandMultipleLines() {
        doTest(
            "0 3 * * * /usr/local/bin/backup.sh \\\n  --source /data \\\n  --dest /backup",
            """
            CrontabTokenType.NUMBER ('0')
            WHITE_SPACE (' ')
            CrontabTokenType.NUMBER ('3')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.CONTENT ('/usr/local/bin/backup.sh \\\n  --source /data \\\n  --dest /backup')
            """.trimIndent()
        )
    }

    fun testMultipleLines() {
        doTest(
            "# comment\n* * * * * ls\n",
            """
            CrontabTokenType.SINGLE_COMMENT ('# comment')
            CrontabTokenType.EOL ('\n')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.CONTENT ('ls')
            CrontabTokenType.EOL ('\n')
            """.trimIndent()
        )
    }

    fun testWeekdayRange() {
        doTest(
            "* * * * MON-FRI ls",
            """
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.WEEKDAY ('MON')
            CrontabTokenType.HYPHEN ('-')
            CrontabTokenType.WEEKDAY ('FRI')
            WHITE_SPACE (' ')
            CrontabTokenType.CONTENT ('ls')
            """.trimIndent()
        )
    }

    fun testMonthRange() {
        doTest(
            "* * * JAN-DEC * ls",
            """
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.MONTH ('JAN')
            CrontabTokenType.HYPHEN ('-')
            CrontabTokenType.MONTH ('DEC')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.CONTENT ('ls')
            """.trimIndent()
        )
    }

    fun testEmptyLine() {
        doTest(
            "\n",
            """
            CrontabTokenType.EOL ('\n')
            """.trimIndent()
        )
    }

    fun testRangeStep() {
        doTest(
            "1-10/2 * * * * ls",
            """
            CrontabTokenType.NUMBER ('1')
            CrontabTokenType.HYPHEN ('-')
            CrontabTokenType.NUMBER ('10')
            CrontabTokenType.SLASH ('/')
            CrontabTokenType.NUMBER ('2')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.STAR ('*')
            WHITE_SPACE (' ')
            CrontabTokenType.CONTENT ('ls')
            """.trimIndent()
        )
    }
}
