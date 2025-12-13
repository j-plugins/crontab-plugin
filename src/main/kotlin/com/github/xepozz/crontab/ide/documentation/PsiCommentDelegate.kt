package com.github.xepozz.crontab.ide.documentation

import com.intellij.extapi.psi.ASTDelegatePsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElement

open class PsiCommentDelegate(val myComment: PsiComment) : PsiComment, ASTDelegatePsiElement() {
    override fun getTokenType() = myComment.tokenType

    override fun getParent(): PsiElement? = myComment.parent

    override fun getNode(): ASTNode = myComment.node
}