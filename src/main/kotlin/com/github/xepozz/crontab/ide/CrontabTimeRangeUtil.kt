package com.github.xepozz.crontab.ide

import com.github.xepozz.crontab.language.psi.CrontabTimeList

object CrontabTimeRangeUtil {
    fun expandRanges(element: CrontabTimeList): Set<Int> {
        val input = element.timeListItemList
            .mapNotNull {
                when {
                    it.timeRange != null -> "${it.timeRange!!.text}"
                    it.timeExactNumber != null -> "${it.timeExactNumber!!.text}"
                    else -> null
                }
            }
        return expandRanges(input)
    }

    fun collapseRanges(element: CrontabTimeList): List<String> {
        val input = element.timeListItemList
            .mapNotNull {
                when {
                    it.timeRange != null -> "${it.timeRange!!.text}"
                    it.timeExactNumber != null -> "${it.timeExactNumber!!.text}"
                    else -> null
                }
            }
        return collapseRanges(input)
    }

    fun collapseRanges(input: List<String>): List<String> {
        val numbers = expandRanges(input)
        if (numbers.isEmpty()) return emptyList()

        // Sort the numbers and then collapse ranges
        val sortedNumbers = numbers.sorted()
        val result = mutableListOf<String>()
        var rangeStart = sortedNumbers[0]
        var rangeEnd = sortedNumbers[0]

        for (i in 1 until sortedNumbers.size) {
            if (sortedNumbers[i] == rangeEnd + 1) {
                rangeEnd = sortedNumbers[i]
            } else {
                if (rangeStart == rangeEnd) {
                    result.add("$rangeStart")
                } else {
                    result.add("$rangeStart-$rangeEnd")
                }
                rangeStart = sortedNumbers[i]
                rangeEnd = sortedNumbers[i]
            }
        }

        // Add the last range
        if (rangeStart == rangeEnd) {
            result.add("$rangeStart")
        } else if (rangeStart + 1 == rangeEnd) {
            result.add("$rangeStart,$rangeEnd")
        } else {
            result.add("$rangeStart-$rangeEnd")
        }

        return result
    }

    fun expandRanges(input: List<String>): Set<Int> {
        if (input.isEmpty()) return emptySet()
        val numbers = mutableSetOf<Int>()

        // Parse the input and collect all individual numbers and ranges
        for (item in input) {
            if (item.contains("-")) {
                // Handle range
                val parts = item.split("-")
                val start = parts[0].toInt()
                val end = parts[1].toInt()
                numbers.addAll(start..end)
            } else {
                // Handle individual number
                numbers.add(item.toInt())
            }
        }
        return numbers
    }
}