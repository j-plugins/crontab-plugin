package com.github.xepozz.crontab.ide.documentation

import com.github.xepozz.crontab.language.psi.CrontabCronExpression
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiWhiteSpace

object CrontabDocumentationUtils {
    fun findCronExpressionDocumentation(element: CrontabCronExpression?) =
        findCronExpressionDocumentationElements(element)
            .map { it.text.replaceFirst("[# ]+", "") }
            .reversed()
            .joinToString("\n")


    fun findCronExpressionDocumentationElements(element: CrontabCronExpression?): List<PsiComment> {
        if (element == null) return emptyList<PsiComment>()

        val result = mutableListOf<PsiComment>()
        var element = element.prevSibling
        while (element is PsiComment || element is PsiWhiteSpace) {
            if (element is PsiComment) {
                result.add(element)
            }
            element = element.prevSibling
        }

        return result
    }
}