package com.github.xepozz.crontab.language.psi.impl

import com.github.xepozz.crontab.language.psi.CrontabTimeRange

object CrontabImplUtil {
    @JvmStatic
    fun getFirst(element: CrontabTimeRange) = element.node.firstChildNode.text.toIntOrNull() ?: 0

    @JvmStatic
    fun getSecond(element: CrontabTimeRange): Int = element.node.lastChildNode.text.toIntOrNull() ?: 0

    @JvmStatic
    fun getIntRange(element: CrontabTimeRange): IntRange = IntRange(element.first, element.second)
}
