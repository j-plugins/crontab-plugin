package com.github.xepozz.crontab.ide

import com.github.xepozz.crontab.language.psi.CrontabTimeList
import com.github.xepozz.crontab.language.psi.impl.CrontabImplUtil

object CrontabTimeRangeUtil {
    fun canCollapseRanges(element: CrontabTimeList): Boolean {
        return element.timeListItemList.all {
            val range = it.timeRange
            when {
                it.timeExactNumber != null -> true
                range != null -> CrontabImplUtil.getFirst(range) <= CrontabImplUtil.getSecond(range)
                else -> false
            }
        }
    }

    fun expandRanges(element: CrontabTimeList): Set<Int> {
        return expandRanges(element.numericItems())
    }

    fun collapseRanges(element: CrontabTimeList): List<String> {
        return collapseRanges(element.numericItems())
    }

    fun collapseRanges(input: List<String>): List<String> {
        val numbers = expandRanges(input)
        if (numbers.isEmpty()) return emptyList()

        // Sort the numbers and then collapse ranges
        val sortedNumbers = numbers.sorted()
        val result = mutableListOf<String>()
        var rangeStart = sortedNumbers[0]
        var rangeEnd = sortedNumbers[0]

        fun addRange(start: Int, end: Int) {
            when {
                start == end -> result.add("$start")
                start + 1 == end -> {
                    result.add("$start")
                    result.add("$end")
                }

                else -> result.add("$start-$end")
            }
        }

        for (i in 1 until sortedNumbers.size) {
            if (sortedNumbers[i] == rangeEnd + 1) {
                rangeEnd = sortedNumbers[i]
            } else {
                addRange(rangeStart, rangeEnd)
                rangeStart = sortedNumbers[i]
                rangeEnd = sortedNumbers[i]
            }
        }

        addRange(rangeStart, rangeEnd)

        return result
    }

    fun expandRanges(input: List<String>): Set<Int> {
        if (input.isEmpty()) return emptySet()
        val numbers = mutableSetOf<Int>()

        // Parse the input and collect all individual numbers and ranges
        for (item in input) {
            if ("-" in item) {
                val parts = item.split("-")
                if (parts.size != 2) continue
                val start = parts[0].toIntOrNull() ?: continue
                val end = parts[1].toIntOrNull() ?: continue
                numbers.addAll(start..end)
            } else {
                numbers.add(item.toIntOrNull() ?: continue)
            }
        }
        return numbers
    }

    private fun CrontabTimeList.numericItems(): List<String> {
        return timeListItemList.mapNotNull {
            it.timeRange?.text ?: it.timeExactNumber?.text
        }
    }
}
