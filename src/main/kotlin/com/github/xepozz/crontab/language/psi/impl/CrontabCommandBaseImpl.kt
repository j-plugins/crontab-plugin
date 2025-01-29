package com.github.xepozz.crontab.language.psi.impl

import com.github.xepozz.crontab.language.psi.CrontabCommand
import com.github.xepozz.crontab.language.psi.CrontabTypes
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.icons.AllIcons
import com.intellij.ide.projectView.PresentationData
import com.intellij.lang.ASTNode
import com.intellij.psi.LiteralTextEscaper
import com.intellij.psi.PsiLanguageInjectionHost
import com.intellij.psi.impl.source.tree.LeafElement

abstract class CrontabCommandBaseImpl : ASTWrapperPsiElement, CrontabCommand, PsiLanguageInjectionHost {
    constructor(node: ASTNode) : super(node)

    override fun getText(): String {
        val keyNode = this.node.findChildByType(CrontabTypes.CONTENT)

        return keyNode?.text ?: ""
    }

    override fun getPresentation() = PresentationData(text, null, getIcon(0), null)

    override fun getIcon(flags: Int) = AllIcons.Nodes.Console

    override fun isValidHost() = true

    override fun updateText(newText: String): PsiLanguageInjectionHost? {
        val keyNode = this.node.findChildByType(CrontabTypes.CONTENT) as? LeafElement ?: return null
        keyNode.replaceWithText(newText)

        return this
    }

    override fun createLiteralTextEscaper(): LiteralTextEscaper<out PsiLanguageInjectionHost?> {
        return LiteralTextEscaper.createSimple(this, true)
    }
}