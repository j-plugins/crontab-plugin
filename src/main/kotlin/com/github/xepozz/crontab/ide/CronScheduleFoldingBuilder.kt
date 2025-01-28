package com.github.xepozz.crontab.ide

import com.github.xepozz.crontab.language.CrontabFile
import com.github.xepozz.crontab.language.psi.CrontabSchedule
import com.intellij.lang.ASTNode
import com.intellij.lang.folding.CustomFoldingBuilder
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.project.DumbService
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil

class CronScheduleFoldingBuilder : CustomFoldingBuilder() {
    override fun buildLanguageFoldRegions(
        resultSet: MutableList<FoldingDescriptor>,
        element: PsiElement,
        document: Document,
        p3: Boolean
    ) {
        val element = element as? CrontabFile ?: return
        if (DumbService.isDumb(element.project)) return

        PsiTreeUtil.findChildrenOfType(element, CrontabSchedule::class.java)
            .map {
                FoldingDescriptor(
                    it.node,
                    it.textRange,
                    FoldingGroup.newGroup("Crontab Schedule Group"),
                )
            }
            .forEach { resultSet.add(it) }

    }

    override fun getLanguagePlaceholderText(node: ASTNode, textRange: TextRange): String? {
        val element = node.psi as? CrontabSchedule ?: return null

        try {
            return CronScheduleDescriber.asHumanReadable(element.text)
        } catch (_: IllegalArgumentException) {
        }
        return null
    }

    override fun isRegionCollapsedByDefault(node: ASTNode): Boolean = true
}
