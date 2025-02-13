package com.github.xepozz.crontab.ide.inspections

import com.github.xepozz.crontab.ide.CrontabTimeRangeUtil
import com.github.xepozz.crontab.language.CrontabFile
import com.github.xepozz.crontab.language.psi.CrontabTimeList
import com.github.xepozz.crontab.language.psi.CrontabTimeRange
import com.github.xepozz.crontab.language.psi.CrontabVisitor
import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.PsiFile

class CrontabScheduleTimeRangeInspection : LocalInspectionTool() {
    override fun getStaticDescription(): String = "Crontab schedule mistakes"

    override fun isAvailableForFile(file: PsiFile): Boolean = file is CrontabFile

    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
        return object : CrontabVisitor() {
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
                    CrontabInspectionUtil.registerCollapseRangeList(holder, element)
                }
                super.visitTimeList(element)
            }
        }
    }

}