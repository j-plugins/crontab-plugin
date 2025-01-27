package com.github.xepozz.crontab.language.psi.impl

import com.github.xepozz.crontab.language.psi.CrontabTimeRange

object CrontabImplUtil {
    @JvmStatic
    fun getFirst(element: CrontabTimeRange) = element.node.firstChildNode.text.toInt()

    @JvmStatic
    fun getSecond(element: CrontabTimeRange): Int = element.node.lastChildNode.text.toInt()

    @JvmStatic
    fun getIntRange(element: CrontabTimeRange): IntRange = IntRange(
        element.node.lastChildNode.text.toInt(),
        element.node.firstChildNode.text.toInt(),
    )
}
