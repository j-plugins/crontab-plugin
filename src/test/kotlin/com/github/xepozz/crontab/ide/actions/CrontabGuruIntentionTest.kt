package com.github.xepozz.crontab.ide.actions

import com.intellij.testFramework.fixtures.BasePlatformTestCase

class CrontabGuruIntentionTest : BasePlatformTestCase() {
    fun testAvailableOnlyInsideSchedule() {
        val file = myFixture.configureByText("test.crontab", "PATH=/usr/bin\n* * * * * cmd")
        val intention = CrontabGuruIntention()

        val variableElement = file.findElementAt(0)!!
        val scheduleElement = file.findElementAt(file.text.indexOf("*"))!!

        assertFalse(intention.isAvailable(project, myFixture.editor, variableElement))
        assertTrue(intention.isAvailable(project, myFixture.editor, scheduleElement))
    }
}
