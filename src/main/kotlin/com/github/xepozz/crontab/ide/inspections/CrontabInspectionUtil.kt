package com.github.xepozz.crontab.ide.inspections

import com.github.xepozz.crontab.language.psi.CrontabElementFactory
import com.github.xepozz.crontab.language.psi.CrontabTimeRange
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.openapi.project.Project

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
}