package com.github.xepozz.crontab.ide.inspections

import com.intellij.codeInsight.daemon.impl.HighlightInfo
import com.intellij.testFramework.fixtures.BasePlatformTestCase

class CrontabScheduleTimeRangeInspectionTest : BasePlatformTestCase() {
    override fun setUp() {
        super.setUp()
        myFixture.enableInspections(CrontabScheduleTimeRangeInspection())
    }

    fun testWildcardStepZeroIsHighlighted() {
        assertHasHighlight(
            "*/0 * * * * cmd",
            "Step value should be greater than 0.",
        )
    }

    fun testMonthNameInHourFieldIsHighlighted() {
        assertHasHighlight(
            "0 JAN * * * cmd",
            "Hour pattern should contain numeric values only.",
        )
    }

    fun testMonthNameInWeekFieldIsHighlighted() {
        assertHasHighlight(
            "0 0 * * JAN cmd",
            "Weekday pattern should be one of values: MON, TUE, WED, THU, FRI, SAT, SUN.",
        )
    }

    fun testMixedStepListDoesNotOfferCollapseQuickFix() {
        myFixture.configureByText("test.crontab", "1,*/5 * * * * cmd")
        myFixture.doHighlighting()

        val hasCollapseFix = myFixture.getAllQuickFixes().any { it.text == "Collapse list" }

        assertFalse(hasCollapseFix)
    }

    private fun assertHasHighlight(text: String, description: String) {
        myFixture.configureByText("test.crontab", text)

        val descriptions = myFixture.doHighlighting()
            .mapNotNull(HighlightInfo::getDescription)

        assertTrue(
            "Expected highlight \"$description\" in $descriptions",
            description in descriptions,
        )
    }
}
