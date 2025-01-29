package com.github.xepozz.crontab.ide

import com.github.xepozz.crontab.ide.actions.CrontabRunCommandAction
import com.github.xepozz.crontab.language.psi.CrontabCommand
import com.intellij.execution.lineMarker.RunLineMarkerContributor
import com.intellij.icons.AllIcons
import com.intellij.psi.PsiElement

internal class CrontabRunCommandMarkerContributor : RunLineMarkerContributor() {
    override fun getInfo(leaf: PsiElement): Info? {
        val element = leaf.parent as? CrontabCommand ?: return null

        return Info(AllIcons.Actions.Execute, arrayOf(CrontabRunCommandAction(element.text))) { "Run in Terminal" }
    }
}