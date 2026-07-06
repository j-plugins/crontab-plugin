package com.github.xepozz.crontab.language.psi.impl

import com.github.xepozz.crontab.language.psi.CrontabTimeRange

object CrontabImplUtil {
    @JvmStatic
    fun getFirst(element: CrontabTimeRange) = element.timeExactNumberList.getOrNull(0)?.text?.toIntOrNull() ?: 0

    @JvmStatic
    fun getSecond(element: CrontabTimeRange): Int = element.timeExactNumberList.getOrNull(1)?.text?.toIntOrNull() ?: 0

    @JvmStatic
    fun getIntRange(element: CrontabTimeRange): IntRange = IntRange(getFirst(element), getSecond(element))
}
