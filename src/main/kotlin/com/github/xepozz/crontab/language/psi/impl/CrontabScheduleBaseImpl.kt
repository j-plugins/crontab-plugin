package com.github.xepozz.crontab.language.psi.impl

import com.github.xepozz.crontab.language.CrontabAnnotator
import com.github.xepozz.crontab.language.psi.CrontabSchedule
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.icons.AllIcons
import com.intellij.ide.projectView.PresentationData
import com.intellij.lang.ASTNode

abstract class CrontabScheduleBaseImpl : ASTWrapperPsiElement, CrontabSchedule {
    constructor(node: ASTNode) : super(node)

    override fun getText() = this.node.text

    override fun getValue() = this.text

    override fun getPresentation() = PresentationData(text, null, getIcon(0), CrontabAnnotator.SCHEDULE_HIGHLIGHT)

    override fun getIcon(flags: Int) = AllIcons.Nodes.DataTables
}