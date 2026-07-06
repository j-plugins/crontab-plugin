package com.github.xepozz.crontab.ide

import org.junit.Assert.assertEquals
import org.junit.Test

class CrontabTimeRangeUtilTest {
    @Test
    fun `keeps two item ranges expanded everywhere`() {
        assertEquals(
            listOf("1", "2", "4"),
            CrontabTimeRangeUtil.collapseRanges(listOf("1", "2", "4")),
        )
    }

    @Test
    fun `collapses ranges with at least three values`() {
        assertEquals(
            listOf("1-3", "5"),
            CrontabTimeRangeUtil.collapseRanges(listOf("1", "2", "3", "5")),
        )
    }

    @Test
    fun `ignores malformed range items`() {
        assertEquals(
            setOf(4),
            CrontabTimeRangeUtil.expandRanges(listOf("1-2-3", "4")),
        )
    }
}
