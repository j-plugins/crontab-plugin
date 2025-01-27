package com.github.xepozz.crontab.language.psi.impl

import com.github.xepozz.crontab.language.psi.CrontabVariableDefinition
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

abstract class CrontabVariableBaseImpl :
    ASTWrapperPsiElement,
    CrontabVariableDefinition {
    constructor(node: ASTNode) : super(node)

    override fun getText(): String {
        return this.node.text
    }

//    override fun isValidHost() = this.node.findChildByType(CrontabTypes.CONTENT) != null
//
//    override fun updateText(newText: String): PsiLanguageInjectionHost? {
//        val keyNode = this.node.findChildByType(CrontabTypes.CONTENT) as? LeafElement ?: return null
//        keyNode.replaceWithText(newText)
//
//        return this
//    }
//
//    override fun createLiteralTextEscaper(): LiteralTextEscaper<out PsiLanguageInjectionHost?> {
//        return LiteralTextEscaper.createSimple(this, true)
//    }
}