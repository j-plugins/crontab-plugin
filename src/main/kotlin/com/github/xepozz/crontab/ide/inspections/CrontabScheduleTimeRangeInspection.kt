package com.github.xepozz.crontab.ide.inspections

import com.github.xepozz.crontab.language.CrontabFile
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
                if (element.first > element.second) {
                    CrontabInspectionUtil.registerSwapRange(holder, element)
                }
                super.visitTimeRange(element)
            }
        }
    }

}