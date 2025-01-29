package com.github.xepozz.crontab.language.psi.impl

import com.github.xepozz.crontab.language.psi.CrontabVariableDefinition
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode

abstract class CrontabVariableBaseImpl :
    ASTWrapperPsiElement,
    CrontabVariableDefinition {
    constructor(node: ASTNode) : super(node)

    override fun getText() = this.node.text

    override fun getIcon(flags: Int) = AllIcons.Nodes.Variable
}