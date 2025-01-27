package com.github.xepozz.crontab.language.psi

import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil

object CrontabPsiTreeUtils {
    fun findCrontabSchedule(psiElement: PsiElement): CrontabSchedule? =
        PsiTreeUtil.collectParents(psiElement, CrontabSchedule::class.java, true)
        { it is CrontabCronExpression }
            .firstOrNull()

}