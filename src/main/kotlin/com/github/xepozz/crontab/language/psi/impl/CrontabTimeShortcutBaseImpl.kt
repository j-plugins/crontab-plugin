package com.github.xepozz.crontab.language.psi.impl

import com.github.xepozz.crontab.CrontabIcons
import com.github.xepozz.crontab.language.psi.CrontabTimeShortcut
import com.github.xepozz.crontab.language.psi.CrontabTypes
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.ide.projectView.PresentationData
import com.intellij.lang.ASTNode

abstract class CrontabTimeShortcutBaseImpl : ASTWrapperPsiElement, CrontabTimeShortcut {
    constructor(node: ASTNode) : super(node)

    override fun getText(): String {
        val keyNode = this.node.findChildByType(CrontabTypes.CONTENT)

        return keyNode?.text ?: ""
    }

    override fun getPresentation() = PresentationData(text, null, getIcon(0), null)

    override fun getIcon(flags: Int) = CrontabIcons.FILE

    override fun getName() = text

    override fun getValue() = text
}