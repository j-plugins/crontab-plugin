package com.github.xepozz.crontab.ide.documentation

import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiWhiteSpace

object CrontabDocumentationUtils {
    fun findCrontabElementDocumentation(element: PsiElement?): String =
        findContextualDocumentationElement(element)?.text?.replaceFirst("[# ]+", "") ?: ""

    fun findContextualDocumentationElement(element: PsiElement?): PsiComment? {
        if (element == null) return null

        var element = element.prevSibling
        while (element is PsiComment || element is PsiWhiteSpace) {
            if (element is PsiComment) {
                return element
            }
            element = element.prevSibling
        }

        return null
    }
}