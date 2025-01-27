package com.github.xepozz.crontab.ide.actions

import com.github.xepozz.crontab.language.CrontabFile
import com.github.xepozz.crontab.language.psi.CrontabCronExpression
import com.github.xepozz.crontab.language.psi.CrontabSchedule
import com.intellij.ide.BrowserUtil
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil

class OpenCrontabGuruAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val editor = e.getData(CommonDataKeys.EDITOR) ?: return
        val psiFile = e.getData(CommonDataKeys.PSI_FILE) as? CrontabFile ?: return
        val psiElement = psiFile.findElementAt(editor.caretModel.offset) ?: return

        val crontabSchedule = findCrontabSchedule(psiElement)

        if (crontabSchedule != null) {
            BrowserUtil.browse(
                "https://crontab.guru/#" + crontabSchedule.text.split(Regex("[\\s\\t]+")).joinToString("_")
            )
        }
    }

    override fun update(e: AnActionEvent) {
        val editor = e.getData(CommonDataKeys.EDITOR) ?: return
        val psiFile = e.getData(CommonDataKeys.PSI_FILE) as? CrontabFile ?: return
        val psiElement = psiFile.findElementAt(editor.caretModel.offset) ?: return

        val crontabSchedule = findCrontabSchedule(psiElement)

        e.presentation.isEnabledAndVisible = crontabSchedule != null
    }

    override fun getActionUpdateThread() = ActionUpdateThread.BGT

    private fun findCrontabSchedule(psiElement: PsiElement): CrontabSchedule? =
        PsiTreeUtil.collectParents(psiElement, CrontabSchedule::class.java, true)
        { it is CrontabCronExpression }
            .firstOrNull()

}