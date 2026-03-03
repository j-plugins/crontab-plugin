package com.github.xepozz.crontab.language

import com.github.xepozz.crontab.language.parser.CrontabParserDefinition
import com.intellij.testFramework.ParsingTestCase

class CrontabParsingTest : ParsingTestCase("", "cron", CrontabParserDefinition()) {

    override fun getTestDataPath(): String = "src/test/testData/parser"

    override fun skipSpaces(): Boolean = true

    override fun includeRanges(): Boolean = false

    fun testSimpleExpression() {
        doTest(true)
    }

    fun testNumberSchedule() {
        doTest(true)
    }

    fun testVariableDefinition() {
        doTest(true)
    }

    fun testComment() {
        doTest(true)
    }

    fun testTimeShortcut() {
        doTest(true)
    }

    fun testRange() {
        doTest(true)
    }

    fun testStep() {
        doTest(true)
    }

    fun testList() {
        doTest(true)
    }

    fun testMultilineCommand() {
        doTest(true)
    }

    fun testWeekdayRange() {
        doTest(true)
    }

    fun testMonthRange() {
        doTest(true)
    }

    fun testMixedFile() {
        doTest(true)
    }

    fun testMultipleExpressions() {
        doTest(true)
    }

    fun testRangeStep() {
        doTest(true)
    }
}
