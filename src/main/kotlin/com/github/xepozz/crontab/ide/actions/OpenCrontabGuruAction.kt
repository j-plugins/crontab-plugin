package com.github.xepozz.crontab.ide.actions

import com.github.xepozz.crontab.ide.CrontabGuruUtils
import com.github.xepozz.crontab.language.CrontabFile
import com.github.xepozz.crontab.language.psi.CrontabPsiTreeUtils
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys

class OpenCrontabGuruAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        val editor = event.getData(CommonDataKeys.EDITOR) ?: return
        val psiFile = event.getData(CommonDataKeys.PSI_FILE) as? CrontabFile ?: return
        val psiElement = psiFile.findElementAt(editor.caretModel.offset) ?: return

        val crontabSchedule = CrontabPsiTreeUtils.findCrontabSchedule(psiElement)

        if (crontabSchedule != null) {
            CrontabGuruUtils.openInBrowser(crontabSchedule)
        }
    }

    override fun update(event: AnActionEvent) {
        println("call update $event")
        val editor = event.getData(CommonDataKeys.EDITOR) ?: return
        val psiFile = event.getData(CommonDataKeys.PSI_FILE) as? CrontabFile ?: return
        val psiElement = psiFile.findElementAt(editor.caretModel.offset) ?: return

        val crontabSchedule = CrontabPsiTreeUtils.findCrontabSchedule(psiElement)

        event.presentation.isEnabledAndVisible = crontabSchedule != null
    }

    override fun getActionUpdateThread() = ActionUpdateThread.BGT
}