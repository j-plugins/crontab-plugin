package com.github.xepozz.crontab.ide.inspections

import com.github.xepozz.crontab.language.psi.CrontabElementFactory
import com.github.xepozz.crontab.language.psi.CrontabTimeList
import com.github.xepozz.crontab.language.psi.CrontabTimeRange
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement

object CrontabInspectionUtil {
    fun registerSwapRange(holder: ProblemsHolder, element: CrontabTimeRange) {
        val interval = "${element.second}-${element.first}"

        holder.registerProblem(
            element,
            "Invalid time range. First interval must be greater than last.",
            object : CrontabScheduleQuickFix() {
                override fun getName() = "Replace with \"$interval\""

                override fun applyFix(project: Project, descriptor: ProblemDescriptor) {
                    val psiElement = descriptor.psiElement as? CrontabTimeRange ?: return
                    if (psiElement.isWritable) {
                        psiElement.replace(
                            CrontabElementFactory.createCrontabTimeRange(
                                project,
                                psiElement.second,
                                psiElement.first,
                            )
                        )
                    }
                }
            }
        )
    }

    fun registerSimplifyRange(holder: ProblemsHolder, element: CrontabTimeRange) {
        val interval = "${element.first}"

        holder.registerProblem(
            element,
            "Invalid time range. One of intervals can be eliminated.",
            object : CrontabScheduleQuickFix() {
                override fun getName() = "Replace with \"$interval\""

                override fun applyFix(project: Project, descriptor: ProblemDescriptor) {
                    val psiElement = descriptor.psiElement as? CrontabTimeRange ?: return
                    if (psiElement.isWritable) {
                        psiElement.replace(
                            CrontabElementFactory.createCrontabTimeExact(
                                project,
                                psiElement.first,
                            )
                        )
                    }
                }
            }
        )
    }

    fun registerCollapseRangeList(holder: ProblemsHolder, element: CrontabTimeList, after: String) {
        holder.registerProblem(
            element,
            "List of intervals can be collapsed.",
            ProblemHighlightType.WARNING,
            object : CrontabScheduleQuickFix() {
                override fun getName() = "Collapse list"

                override fun applyFix(project: Project, descriptor: ProblemDescriptor) {
                    val psiElement = descriptor.psiElement as? CrontabTimeList ?: return
                    if (psiElement.isWritable) {
                        psiElement.replace(
                            CrontabElementFactory.createCrontabTimeList(
                                project,
                                listOf(after),
                            )
                        )
                    }
                }
            }
        )
    }

    fun registerMinuteOverlaps(holder: ProblemsHolder, element: PsiElement) {
        holder.registerProblem(
            element,
            "Minute pattern should be in range 0-59.",
            ProblemHighlightType.ERROR,
        )
    }

    fun registerHourOverlaps(holder: ProblemsHolder, element: PsiElement) {
        holder.registerProblem(
            element,
            "Hour pattern should be in range 0-23.",
            ProblemHighlightType.ERROR,
        )
    }

    fun registerDayOverlaps(holder: ProblemsHolder, element: PsiElement) {
        holder.registerProblem(
            element,
            "Day pattern should be in range 1-31.",
            ProblemHighlightType.ERROR,
        )
    }

    fun registerMonthNumberOverlaps(holder: ProblemsHolder, element: PsiElement) {
        holder.registerProblem(
            element,
            "Month pattern should be in range 1-12.",
            ProblemHighlightType.ERROR,
        )
    }

    fun registerMonthTextOverlaps(holder: ProblemsHolder, element: PsiElement) {
        holder.registerProblem(
            element,
            "Month pattern should be one of values: ${CronPatterns.MONTHS.joinToString { ", " }}.",
            ProblemHighlightType.ERROR,
        )
    }

    fun registerWeekdayNumberOverlaps(holder: ProblemsHolder, element: PsiElement) {
        holder.registerProblem(
            element,
            "Weekday pattern should be in range 0-6.",
            ProblemHighlightType.ERROR,
        )
    }

    fun registerWeekdayTextOverlaps(holder: ProblemsHolder, element: PsiElement) {
        holder.registerProblem(
            element,
            "Weekday pattern should be one of values: ${CronPatterns.DAYS.joinToString { ", " }}.",
            ProblemHighlightType.ERROR,
        )
    }
}