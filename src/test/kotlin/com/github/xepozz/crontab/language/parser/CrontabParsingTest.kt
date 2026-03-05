package com.github.xepozz.crontab.language.parser

import com.intellij.testFramework.ParsingTestCase

class CrontabParsingTest : ParsingTestCase(
    "",
    "crontab",
    CrontabParserDefinition()
) {
    override fun getTestDataPath(): String =
        "src/test/testData/parser"

    private fun doTreeTest(text: String, expectedTree: String) {
        val file = createPsiFile("test", text)
        val actual = toParseTreeText(file, true, false).trim()
        assertEquals(expectedTree.trimIndent().trim(), actual)
    }

    fun testSimpleWildcardCommand() {
        doTreeTest(
            "* * * * * echo hello", """
            Crontab File
              CrontabCronExpressionImpl(CrontabElementType(CRON_EXPRESSION))
                CrontabScheduleImpl(CrontabElementType(SCHEDULE))
                  CrontabMinutePatternImpl(CrontabElementType(MINUTE_PATTERN))
                    CrontabTimeListImpl(CrontabElementType(TIME_LIST))
                      CrontabTimeListItemImpl(CrontabElementType(TIME_LIST_ITEM))
                        CrontabTimeAnyImpl(CrontabElementType(TIME_ANY))
                          PsiElement(CrontabTokenType.STAR)('*')
                  CrontabHourPatternImpl(CrontabElementType(HOUR_PATTERN))
                    CrontabTimeListImpl(CrontabElementType(TIME_LIST))
                      CrontabTimeListItemImpl(CrontabElementType(TIME_LIST_ITEM))
                        CrontabTimeAnyImpl(CrontabElementType(TIME_ANY))
                          PsiElement(CrontabTokenType.STAR)('*')
                  CrontabDayPatternImpl(CrontabElementType(DAY_PATTERN))
                    CrontabTimeListImpl(CrontabElementType(TIME_LIST))
                      CrontabTimeListItemImpl(CrontabElementType(TIME_LIST_ITEM))
                        CrontabTimeAnyImpl(CrontabElementType(TIME_ANY))
                          PsiElement(CrontabTokenType.STAR)('*')
                  CrontabMonthPatternImpl(CrontabElementType(MONTH_PATTERN))
                    CrontabTimeListImpl(CrontabElementType(TIME_LIST))
                      CrontabTimeListItemImpl(CrontabElementType(TIME_LIST_ITEM))
                        CrontabTimeAnyImpl(CrontabElementType(TIME_ANY))
                          PsiElement(CrontabTokenType.STAR)('*')
                  CrontabWeekPatternImpl(CrontabElementType(WEEK_PATTERN))
                    CrontabTimeListImpl(CrontabElementType(TIME_LIST))
                      CrontabTimeListItemImpl(CrontabElementType(TIME_LIST_ITEM))
                        CrontabTimeAnyImpl(CrontabElementType(TIME_ANY))
                          PsiElement(CrontabTokenType.STAR)('*')
                CrontabCommandImpl(CrontabElementType(COMMAND))
                  PsiElement(CrontabTokenType.CONTENT)('echo hello')
        """
        )
    }

    fun testRangeStepListWeekday() {
        doTreeTest(
            "1-4,5-10/2 * 1 * MON ls -la", """
            Crontab File
              CrontabCronExpressionImpl(CrontabElementType(CRON_EXPRESSION))
                CrontabScheduleImpl(CrontabElementType(SCHEDULE))
                  CrontabMinutePatternImpl(CrontabElementType(MINUTE_PATTERN))
                    CrontabTimeListImpl(CrontabElementType(TIME_LIST))
                      CrontabTimeListItemImpl(CrontabElementType(TIME_LIST_ITEM))
                        CrontabTimeRangeImpl(CrontabElementType(TIME_RANGE))
                          CrontabTimeExactNumberImpl(CrontabElementType(TIME_EXACT_NUMBER))
                            PsiElement(CrontabTokenType.NUMBER)('1')
                          PsiElement(CrontabTokenType.HYPHEN)('-')
                          CrontabTimeExactNumberImpl(CrontabElementType(TIME_EXACT_NUMBER))
                            PsiElement(CrontabTokenType.NUMBER)('4')
                      PsiElement(CrontabTokenType.COMMA)(',')
                      CrontabTimeListItemImpl(CrontabElementType(TIME_LIST_ITEM))
                        CrontabTimeRangeStepImpl(CrontabElementType(TIME_RANGE_STEP))
                          CrontabTimeRangeImpl(CrontabElementType(TIME_RANGE))
                            CrontabTimeExactNumberImpl(CrontabElementType(TIME_EXACT_NUMBER))
                              PsiElement(CrontabTokenType.NUMBER)('5')
                            PsiElement(CrontabTokenType.HYPHEN)('-')
                            CrontabTimeExactNumberImpl(CrontabElementType(TIME_EXACT_NUMBER))
                              PsiElement(CrontabTokenType.NUMBER)('10')
                          PsiElement(CrontabTokenType.SLASH)('/')
                          CrontabTimeExactNumberImpl(CrontabElementType(TIME_EXACT_NUMBER))
                            PsiElement(CrontabTokenType.NUMBER)('2')
                  CrontabHourPatternImpl(CrontabElementType(HOUR_PATTERN))
                    CrontabTimeListImpl(CrontabElementType(TIME_LIST))
                      CrontabTimeListItemImpl(CrontabElementType(TIME_LIST_ITEM))
                        CrontabTimeAnyImpl(CrontabElementType(TIME_ANY))
                          PsiElement(CrontabTokenType.STAR)('*')
                  CrontabDayPatternImpl(CrontabElementType(DAY_PATTERN))
                    CrontabTimeListImpl(CrontabElementType(TIME_LIST))
                      CrontabTimeListItemImpl(CrontabElementType(TIME_LIST_ITEM))
                        CrontabTimeExactNumberImpl(CrontabElementType(TIME_EXACT_NUMBER))
                          PsiElement(CrontabTokenType.NUMBER)('1')
                  CrontabMonthPatternImpl(CrontabElementType(MONTH_PATTERN))
                    CrontabTimeListImpl(CrontabElementType(TIME_LIST))
                      CrontabTimeListItemImpl(CrontabElementType(TIME_LIST_ITEM))
                        CrontabTimeAnyImpl(CrontabElementType(TIME_ANY))
                          PsiElement(CrontabTokenType.STAR)('*')
                  CrontabWeekPatternImpl(CrontabElementType(WEEK_PATTERN))
                    CrontabTimeListImpl(CrontabElementType(TIME_LIST))
                      CrontabTimeListItemImpl(CrontabElementType(TIME_LIST_ITEM))
                        CrontabTimeExactDayImpl(CrontabElementType(TIME_EXACT_DAY))
                          PsiElement(CrontabTokenType.WEEKDAY)('MON')
                CrontabCommandImpl(CrontabElementType(COMMAND))
                  PsiElement(CrontabTokenType.CONTENT)('ls -la')
        """
        )
    }

    fun testMonthRangeAndWeekdayRange() {
        doTreeTest(
            "0 0 1 JAN-DEC MON-FRI /usr/bin/backup", """
            Crontab File
              CrontabCronExpressionImpl(CrontabElementType(CRON_EXPRESSION))
                CrontabScheduleImpl(CrontabElementType(SCHEDULE))
                  CrontabMinutePatternImpl(CrontabElementType(MINUTE_PATTERN))
                    CrontabTimeListImpl(CrontabElementType(TIME_LIST))
                      CrontabTimeListItemImpl(CrontabElementType(TIME_LIST_ITEM))
                        CrontabTimeExactNumberImpl(CrontabElementType(TIME_EXACT_NUMBER))
                          PsiElement(CrontabTokenType.NUMBER)('0')
                  CrontabHourPatternImpl(CrontabElementType(HOUR_PATTERN))
                    CrontabTimeListImpl(CrontabElementType(TIME_LIST))
                      CrontabTimeListItemImpl(CrontabElementType(TIME_LIST_ITEM))
                        CrontabTimeExactNumberImpl(CrontabElementType(TIME_EXACT_NUMBER))
                          PsiElement(CrontabTokenType.NUMBER)('0')
                  CrontabDayPatternImpl(CrontabElementType(DAY_PATTERN))
                    CrontabTimeListImpl(CrontabElementType(TIME_LIST))
                      CrontabTimeListItemImpl(CrontabElementType(TIME_LIST_ITEM))
                        CrontabTimeExactNumberImpl(CrontabElementType(TIME_EXACT_NUMBER))
                          PsiElement(CrontabTokenType.NUMBER)('1')
                  CrontabMonthPatternImpl(CrontabElementType(MONTH_PATTERN))
                    CrontabTimeListImpl(CrontabElementType(TIME_LIST))
                      CrontabTimeListItemImpl(CrontabElementType(TIME_LIST_ITEM))
                        CrontabTimeRangeMonthImpl(CrontabElementType(TIME_RANGE_MONTH))
                          CrontabTimeExactMonthImpl(CrontabElementType(TIME_EXACT_MONTH))
                            PsiElement(CrontabTokenType.MONTH)('JAN')
                          PsiElement(CrontabTokenType.HYPHEN)('-')
                          CrontabTimeExactMonthImpl(CrontabElementType(TIME_EXACT_MONTH))
                            PsiElement(CrontabTokenType.MONTH)('DEC')
                  CrontabWeekPatternImpl(CrontabElementType(WEEK_PATTERN))
                    CrontabTimeListImpl(CrontabElementType(TIME_LIST))
                      CrontabTimeListItemImpl(CrontabElementType(TIME_LIST_ITEM))
                        CrontabTimeRangeDayImpl(CrontabElementType(TIME_RANGE_DAY))
                          CrontabTimeExactDayImpl(CrontabElementType(TIME_EXACT_DAY))
                            PsiElement(CrontabTokenType.WEEKDAY)('MON')
                          PsiElement(CrontabTokenType.HYPHEN)('-')
                          CrontabTimeExactDayImpl(CrontabElementType(TIME_EXACT_DAY))
                            PsiElement(CrontabTokenType.WEEKDAY)('FRI')
                CrontabCommandImpl(CrontabElementType(COMMAND))
                  PsiElement(CrontabTokenType.CONTENT)('/usr/bin/backup')
        """
        )
    }

    fun testWildcardStep() {
        doTreeTest(
            "*/15 */2 * * * cmd", """
            Crontab File
              CrontabCronExpressionImpl(CrontabElementType(CRON_EXPRESSION))
                CrontabScheduleImpl(CrontabElementType(SCHEDULE))
                  CrontabMinutePatternImpl(CrontabElementType(MINUTE_PATTERN))
                    CrontabTimeListImpl(CrontabElementType(TIME_LIST))
                      CrontabTimeListItemImpl(CrontabElementType(TIME_LIST_ITEM))
                        CrontabTimeRangeStepImpl(CrontabElementType(TIME_RANGE_STEP))
                          CrontabTimeAnyImpl(CrontabElementType(TIME_ANY))
                            PsiElement(CrontabTokenType.STAR)('*')
                          PsiElement(CrontabTokenType.SLASH)('/')
                          CrontabTimeExactNumberImpl(CrontabElementType(TIME_EXACT_NUMBER))
                            PsiElement(CrontabTokenType.NUMBER)('15')
                  CrontabHourPatternImpl(CrontabElementType(HOUR_PATTERN))
                    CrontabTimeListImpl(CrontabElementType(TIME_LIST))
                      CrontabTimeListItemImpl(CrontabElementType(TIME_LIST_ITEM))
                        CrontabTimeRangeStepImpl(CrontabElementType(TIME_RANGE_STEP))
                          CrontabTimeAnyImpl(CrontabElementType(TIME_ANY))
                            PsiElement(CrontabTokenType.STAR)('*')
                          PsiElement(CrontabTokenType.SLASH)('/')
                          CrontabTimeExactNumberImpl(CrontabElementType(TIME_EXACT_NUMBER))
                            PsiElement(CrontabTokenType.NUMBER)('2')
                  CrontabDayPatternImpl(CrontabElementType(DAY_PATTERN))
                    CrontabTimeListImpl(CrontabElementType(TIME_LIST))
                      CrontabTimeListItemImpl(CrontabElementType(TIME_LIST_ITEM))
                        CrontabTimeAnyImpl(CrontabElementType(TIME_ANY))
                          PsiElement(CrontabTokenType.STAR)('*')
                  CrontabMonthPatternImpl(CrontabElementType(MONTH_PATTERN))
                    CrontabTimeListImpl(CrontabElementType(TIME_LIST))
                      CrontabTimeListItemImpl(CrontabElementType(TIME_LIST_ITEM))
                        CrontabTimeAnyImpl(CrontabElementType(TIME_ANY))
                          PsiElement(CrontabTokenType.STAR)('*')
                  CrontabWeekPatternImpl(CrontabElementType(WEEK_PATTERN))
                    CrontabTimeListImpl(CrontabElementType(TIME_LIST))
                      CrontabTimeListItemImpl(CrontabElementType(TIME_LIST_ITEM))
                        CrontabTimeAnyImpl(CrontabElementType(TIME_ANY))
                          PsiElement(CrontabTokenType.STAR)('*')
                CrontabCommandImpl(CrontabElementType(COMMAND))
                  PsiElement(CrontabTokenType.CONTENT)('cmd')
        """
        )
    }

    fun testShortcutSchedule() {
        doTreeTest(
            "@reboot /usr/bin/startup", """
            Crontab File
              CrontabCronExpressionImpl(CrontabElementType(CRON_EXPRESSION))
                CrontabScheduleImpl(CrontabElementType(SCHEDULE))
                  CrontabTimeShortcutImpl(CrontabElementType(TIME_SHORTCUT))
                    PsiElement(CrontabTokenType.AT)('@')
                    PsiElement(CrontabTokenType.CONTENT)('reboot')
                CrontabCommandImpl(CrontabElementType(COMMAND))
                  PsiElement(CrontabTokenType.CONTENT)('/usr/bin/startup')
        """
        )
    }

    fun testVariableDefinition() {
        doTreeTest(
            "PATH=/usr/bin", """
            Crontab File
              CrontabVariableDefinitionImpl(CrontabElementType(VARIABLE_DEFINITION))
                CrontabVariableNameImpl(CrontabElementType(VARIABLE_NAME))
                  PsiElement(CrontabTokenType.IDENTIFIER)('PATH')
                PsiElement(CrontabTokenType.EQUAL_SIGN)('=')
                CrontabVariableValueImpl(CrontabElementType(VARIABLE_VALUE))
                  PsiElement(CrontabTokenType.CONTENT)('/usr/bin')
        """
        )
    }

    fun testComment() {
        doTreeTest(
            "# this is a comment", """
            Crontab File
              PsiComment(CrontabTokenType.SINGLE_COMMENT)('# this is a comment')
        """
        )
    }

    fun testBackslashContinuation() {
        doTreeTest(
            "* * * * * echo \\\n  hello", """
            Crontab File
              CrontabCronExpressionImpl(CrontabElementType(CRON_EXPRESSION))
                CrontabScheduleImpl(CrontabElementType(SCHEDULE))
                  CrontabMinutePatternImpl(CrontabElementType(MINUTE_PATTERN))
                    CrontabTimeListImpl(CrontabElementType(TIME_LIST))
                      CrontabTimeListItemImpl(CrontabElementType(TIME_LIST_ITEM))
                        CrontabTimeAnyImpl(CrontabElementType(TIME_ANY))
                          PsiElement(CrontabTokenType.STAR)('*')
                  CrontabHourPatternImpl(CrontabElementType(HOUR_PATTERN))
                    CrontabTimeListImpl(CrontabElementType(TIME_LIST))
                      CrontabTimeListItemImpl(CrontabElementType(TIME_LIST_ITEM))
                        CrontabTimeAnyImpl(CrontabElementType(TIME_ANY))
                          PsiElement(CrontabTokenType.STAR)('*')
                  CrontabDayPatternImpl(CrontabElementType(DAY_PATTERN))
                    CrontabTimeListImpl(CrontabElementType(TIME_LIST))
                      CrontabTimeListItemImpl(CrontabElementType(TIME_LIST_ITEM))
                        CrontabTimeAnyImpl(CrontabElementType(TIME_ANY))
                          PsiElement(CrontabTokenType.STAR)('*')
                  CrontabMonthPatternImpl(CrontabElementType(MONTH_PATTERN))
                    CrontabTimeListImpl(CrontabElementType(TIME_LIST))
                      CrontabTimeListItemImpl(CrontabElementType(TIME_LIST_ITEM))
                        CrontabTimeAnyImpl(CrontabElementType(TIME_ANY))
                          PsiElement(CrontabTokenType.STAR)('*')
                  CrontabWeekPatternImpl(CrontabElementType(WEEK_PATTERN))
                    CrontabTimeListImpl(CrontabElementType(TIME_LIST))
                      CrontabTimeListItemImpl(CrontabElementType(TIME_LIST_ITEM))
                        CrontabTimeAnyImpl(CrontabElementType(TIME_ANY))
                          PsiElement(CrontabTokenType.STAR)('*')
                CrontabCommandImpl(CrontabElementType(COMMAND))
                  PsiElement(CrontabTokenType.CONTENT)('echo \\n  hello')
        """
        )
    }

    fun testFullCrontabFile() {
        doTreeTest(
            """
            # crontab
            PATH=/usr/bin
            */5 0 1-15 JAN MON-FRI /usr/bin/job --flag
            @daily /usr/bin/cleanup
        """.trimIndent(), """
            Crontab File
              PsiComment(CrontabTokenType.SINGLE_COMMENT)('# crontab')
              PsiElement(CrontabTokenType.EOL)('\n')
              CrontabVariableDefinitionImpl(CrontabElementType(VARIABLE_DEFINITION))
                CrontabVariableNameImpl(CrontabElementType(VARIABLE_NAME))
                  PsiElement(CrontabTokenType.IDENTIFIER)('PATH')
                PsiElement(CrontabTokenType.EQUAL_SIGN)('=')
                CrontabVariableValueImpl(CrontabElementType(VARIABLE_VALUE))
                  PsiElement(CrontabTokenType.CONTENT)('/usr/bin')
              PsiElement(CrontabTokenType.EOL)('\n')
              CrontabCronExpressionImpl(CrontabElementType(CRON_EXPRESSION))
                CrontabScheduleImpl(CrontabElementType(SCHEDULE))
                  CrontabMinutePatternImpl(CrontabElementType(MINUTE_PATTERN))
                    CrontabTimeListImpl(CrontabElementType(TIME_LIST))
                      CrontabTimeListItemImpl(CrontabElementType(TIME_LIST_ITEM))
                        CrontabTimeRangeStepImpl(CrontabElementType(TIME_RANGE_STEP))
                          CrontabTimeAnyImpl(CrontabElementType(TIME_ANY))
                            PsiElement(CrontabTokenType.STAR)('*')
                          PsiElement(CrontabTokenType.SLASH)('/')
                          CrontabTimeExactNumberImpl(CrontabElementType(TIME_EXACT_NUMBER))
                            PsiElement(CrontabTokenType.NUMBER)('5')
                  CrontabHourPatternImpl(CrontabElementType(HOUR_PATTERN))
                    CrontabTimeListImpl(CrontabElementType(TIME_LIST))
                      CrontabTimeListItemImpl(CrontabElementType(TIME_LIST_ITEM))
                        CrontabTimeExactNumberImpl(CrontabElementType(TIME_EXACT_NUMBER))
                          PsiElement(CrontabTokenType.NUMBER)('0')
                  CrontabDayPatternImpl(CrontabElementType(DAY_PATTERN))
                    CrontabTimeListImpl(CrontabElementType(TIME_LIST))
                      CrontabTimeListItemImpl(CrontabElementType(TIME_LIST_ITEM))
                        CrontabTimeRangeImpl(CrontabElementType(TIME_RANGE))
                          CrontabTimeExactNumberImpl(CrontabElementType(TIME_EXACT_NUMBER))
                            PsiElement(CrontabTokenType.NUMBER)('1')
                          PsiElement(CrontabTokenType.HYPHEN)('-')
                          CrontabTimeExactNumberImpl(CrontabElementType(TIME_EXACT_NUMBER))
                            PsiElement(CrontabTokenType.NUMBER)('15')
                  CrontabMonthPatternImpl(CrontabElementType(MONTH_PATTERN))
                    CrontabTimeListImpl(CrontabElementType(TIME_LIST))
                      CrontabTimeListItemImpl(CrontabElementType(TIME_LIST_ITEM))
                        CrontabTimeExactMonthImpl(CrontabElementType(TIME_EXACT_MONTH))
                          PsiElement(CrontabTokenType.MONTH)('JAN')
                  CrontabWeekPatternImpl(CrontabElementType(WEEK_PATTERN))
                    CrontabTimeListImpl(CrontabElementType(TIME_LIST))
                      CrontabTimeListItemImpl(CrontabElementType(TIME_LIST_ITEM))
                        CrontabTimeRangeDayImpl(CrontabElementType(TIME_RANGE_DAY))
                          CrontabTimeExactDayImpl(CrontabElementType(TIME_EXACT_DAY))
                            PsiElement(CrontabTokenType.WEEKDAY)('MON')
                          PsiElement(CrontabTokenType.HYPHEN)('-')
                          CrontabTimeExactDayImpl(CrontabElementType(TIME_EXACT_DAY))
                            PsiElement(CrontabTokenType.WEEKDAY)('FRI')
                CrontabCommandImpl(CrontabElementType(COMMAND))
                  PsiElement(CrontabTokenType.CONTENT)('/usr/bin/job --flag')
              PsiElement(CrontabTokenType.EOL)('\n')
              CrontabCronExpressionImpl(CrontabElementType(CRON_EXPRESSION))
                CrontabScheduleImpl(CrontabElementType(SCHEDULE))
                  CrontabTimeShortcutImpl(CrontabElementType(TIME_SHORTCUT))
                    PsiElement(CrontabTokenType.AT)('@')
                    PsiElement(CrontabTokenType.CONTENT)('daily')
                CrontabCommandImpl(CrontabElementType(COMMAND))
                  PsiElement(CrontabTokenType.CONTENT)('/usr/bin/cleanup')
        """
        )
    }
}
