package com.github.xepozz.crontab.language.psi.impl

import com.github.xepozz.crontab.language.psi.CrontabTimeRange
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

abstract class CrontabTimeRangeBaseImpl : ASTWrapperPsiElement, CrontabTimeRange {
    constructor(node: ASTNode) : super(node)

    override fun getText(): String {
        return this.node.text
    }
}