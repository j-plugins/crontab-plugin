package com.github.xepozz.crontab.language

import com.intellij.testFramework.fixtures.BasePlatformTestCase

class CrontabFileTypeTest : BasePlatformTestCase() {
    fun testDefaultExtension() {
        assertEquals("crontab", CrontabFileType.INSTANCE.defaultExtension)
    }
}
