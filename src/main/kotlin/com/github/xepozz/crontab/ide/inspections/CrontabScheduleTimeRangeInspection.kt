package com.github.xepozz.crontab.ide.inspections

import com.github.xepozz.crontab.ide.CrontabTimeRangeUtil
import com.github.xepozz.crontab.language.CrontabFile
import com.github.xepozz.crontab.language.psi.CrontabDayPattern
import com.github.xepozz.crontab.language.psi.CrontabHourPattern
import com.github.xepozz.crontab.language.psi.CrontabMinutePattern
import com.github.xepozz.crontab.language.psi.CrontabMonthPattern
import com.github.xepozz.crontab.language.psi.CrontabRecursiveVisitor
import com.github.xepozz.crontab.language.psi.CrontabTimeExactDay
import com.github.xepozz.crontab.language.psi.CrontabTimeExactNumber
import com.github.xepozz.crontab.language.psi.CrontabTimeList
import com.github.xepozz.crontab.language.psi.CrontabTimeRange
import com.github.xepozz.crontab.language.psi.CrontabVisitor
import com.github.xepozz.crontab.language.psi.CrontabWeekPattern
import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiFile

class CrontabScheduleTimeRangeInspection : LocalInspectionTool() {
    override fun getStaticDescription(): String = "Crontab schedule mistakes"

    override fun isAvailableForFile(file: PsiFile): Boolean = file is CrontabFile

    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean) = object : CrontabVisitor() {
        override fun visitTimeRange(element: CrontabTimeRange) {
//                println("before: ${element.first}, after: ${element.second}")

            if (element.first > element.second) {
                CrontabInspectionUtil.registerSwapRange(holder, element)
            }
            if (element.first == element.second) {
                CrontabInspectionUtil.registerSimplifyRange(holder, element)
            }
            super.visitTimeRange(element)
        }

        override fun visitTimeList(element: CrontabTimeList) {
            if (element.timeListItemList.size == 1) return

            val before = element.text
            val after = CrontabTimeRangeUtil.collapseRanges(element).joinToString(",")

//                println("before: $before, after: $after")
            if (before != after) {
                CrontabInspectionUtil.registerCollapseRangeList(holder, element, after)
            }
            super.visitTimeList(element)
        }

        override fun visitMinutePattern(element: CrontabMinutePattern) {
            element.accept(object : CrontabRecursiveVisitor() {
                override fun visitTimeExactNumber(o: CrontabTimeExactNumber) {
                    when (o.text.toIntOrNull()) {
                        null -> return
                        !in 0..59 -> CrontabInspectionUtil.registerMinuteOverlaps(holder, o)
                    }
                    super.visitTimeExactNumber(o)
                }
            })

            super.visitMinutePattern(element)
        }

        override fun visitHourPattern(element: CrontabHourPattern) {
            element.accept(object : CrontabRecursiveVisitor() {

            })

            super.visitHourPattern(element)
        }

        override fun visitDayPattern(element: CrontabDayPattern) {
            element.accept(object : CrontabRecursiveVisitor() {
                override fun visitTimeExactNumber(o: CrontabTimeExactNumber) {
                    when (o.text.toIntOrNull()) {
                        null -> return
                        !in 1..31 -> CrontabInspectionUtil.registerDayOverlaps(holder, o)
                    }
                    super.visitTimeExactNumber(o)
                }
            })

            super.visitDayPattern(element)
        }

        override fun visitMonthPattern(element: CrontabMonthPattern) {
            element.accept(object : CrontabRecursiveVisitor() {
                override fun visitTimeExactNumber(o: CrontabTimeExactNumber) {
                    when (o.text.toIntOrNull()) {
                        null -> return
                        !in 1..12 -> CrontabInspectionUtil.registerMonthNumberOverlaps(holder, o)
                    }
                    super.visitTimeExactNumber(o)
                }

                override fun visitTimeExactDay(o: CrontabTimeExactDay) {
                    if (!CronPatterns.MONTHS_PATTERN.matches(o.text)) {
                        CrontabInspectionUtil.registerMonthTextOverlaps(holder, o)
                    }
                    super.visitTimeExactDay(o)
                }
            })

            super.visitMonthPattern(element)
        }

        override fun visitWeekPattern(element: CrontabWeekPattern) {
            element.accept(object : CrontabRecursiveVisitor() {
                override fun visitTimeExactNumber(o: CrontabTimeExactNumber) {
                    when (o.text.toIntOrNull()) {
                        null -> return
                        !in 0..6 -> CrontabInspectionUtil.registerWeekdayNumberOverlaps(holder, o)
                    }
                    super.visitTimeExactNumber(o)
                }

                override fun visitTimeExactDay(o: CrontabTimeExactDay) {
                    if (!CronPatterns.DAYS_PATTERN.matches(o.text)) {
                        CrontabInspectionUtil.registerWeekdayTextOverlaps(holder, o)
                    }
                    super.visitTimeExactDay(o)
                }
            })

            super.visitWeekPattern(element)
        }
    }
}