package com.github.xepozz.crontab.ide.actions

import com.github.xepozz.crontab.CrontabIcons
import com.github.xepozz.crontab.ide.CrontabGuruUtils
import com.github.xepozz.crontab.language.psi.CrontabPsiTreeUtils
import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Iconable
import com.intellij.psi.PsiElement

class CrontabGuruIntention : PsiElementBaseIntentionAction(), Iconable {
    override fun invoke(
        project: Project,
        editor: Editor,
        element: PsiElement
    ) {
        val crontabSchedule = CrontabPsiTreeUtils.findCrontabSchedule(element)

        if (crontabSchedule != null) {
            CrontabGuruUtils.openInBrowser(crontabSchedule)
        }
    }

    override fun isAvailable(project: Project, editor: Editor, element: PsiElement) = true

    override fun getFamilyName() = "Crontab intentions"

    override fun getText() = "Open in crontab.guru"

    override fun getIcon(flags: Int) = CrontabIcons.GURU
}