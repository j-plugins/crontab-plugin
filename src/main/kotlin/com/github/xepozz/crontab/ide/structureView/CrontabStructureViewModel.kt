package com.github.xepozz.crontab.ide.structureView

import com.github.xepozz.crontab.language.psi.CrontabCommand
import com.github.xepozz.crontab.language.psi.CrontabSchedule
import com.github.xepozz.crontab.language.psi.CrontabVariableDefinition
import com.intellij.ide.structureView.StructureViewModel.ElementInfoProvider
import com.intellij.ide.structureView.StructureViewModelBase
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.Sorter
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiFile

class CrontabStructureViewModel(editor: Editor?, psiFile: PsiFile) :
    StructureViewModelBase(psiFile, editor, RecursiveStructureViewElement(psiFile)), ElementInfoProvider {

    override fun getSorters() = arrayOf(Sorter.ALPHA_SORTER)

    override fun isAlwaysShowsPlus(element: StructureViewTreeElement) = false

    override fun isAlwaysLeaf(element: StructureViewTreeElement) = when (element.value) {
        is CrontabCommand, is CrontabSchedule, is CrontabVariableDefinition -> true
        else -> false
    }

    override fun getSuitableClasses() = arrayOf(CrontabCommand::class.java)
}