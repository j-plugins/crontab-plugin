package com.github.xepozz.crontab.language.psi

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiRecursiveVisitor

open class CrontabRecursiveVisitor : CrontabVisitor(), PsiRecursiveVisitor {
    override fun visitElement(element: PsiElement) {
        super.visitElement(element)
        element.acceptChildren(this)
    }
}