package com.github.xepozz.crontab.ide.structureView

import com.github.xepozz.crontab.language.CrontabFile
import com.github.xepozz.crontab.language.psi.CrontabCommand
import com.github.xepozz.crontab.language.psi.CrontabCronExpression
import com.github.xepozz.crontab.language.psi.CrontabSchedule
import com.github.xepozz.crontab.language.psi.CrontabVariableDefinition
import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.util.PsiTreeUtil

class RecursiveStructureViewElement(val myElement: NavigatablePsiElement) :
    StructureViewTreeElement,
    SortableTreeElement {
    override fun navigate(requestFocus: Boolean) = myElement.navigate(requestFocus)

    override fun canNavigate() = myElement.canNavigate()

    override fun canNavigateToSource() = myElement.canNavigateToSource()

    override fun getValue() = myElement

    override fun getPresentation() = myElement.presentation
        ?: PresentationData(myElement.text, null, myElement.getIcon(0), null)

    override fun getChildren(): Array<out TreeElement> {
        return when (myElement) {
            is CrontabFile -> PsiTreeUtil.getChildrenOfAnyType(
                myElement,
                CrontabCronExpression::class.java,
                CrontabVariableDefinition::class.java
            )
                .map { RecursiveStructureViewElement(it) }
                .toTypedArray()

            is CrontabCronExpression -> PsiTreeUtil.getChildrenOfAnyType(
                myElement,
                CrontabSchedule::class.java,
                CrontabCommand::class.java
            )
                .map { RecursiveStructureViewElement(it) }
                .toTypedArray()

            else -> StructureViewTreeElement.EMPTY_ARRAY
        }
    }

    override fun getAlphaSortKey(): String {
        val name = myElement.name
        return name ?: ""
    }
}