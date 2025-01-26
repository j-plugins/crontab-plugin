package com.github.xepozz.crontab.language

import com.github.xepozz.crontab.language.psi.CrontabCommand
import com.intellij.lang.Language
import com.intellij.lang.injection.MultiHostInjector
import com.intellij.lang.injection.MultiHostRegistrar
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiLanguageInjectionHost

class CrontabLanguageInjector : MultiHostInjector {
    val shellLanguage = Language.findLanguageByID("Shell Script")
    override fun getLanguagesToInject(registrar: MultiHostRegistrar, element: PsiElement) {
        if (shellLanguage == null) return

        when (element) {
            is CrontabCommand -> {
                registrar.startInjecting(shellLanguage)
                    .addPlace(null, null, element, TextRange(0, element.textLength))
                    .doneInjecting()
            }
        }
    }

    override fun elementsToInjectIn(): List<Class<out PsiElement>> {
        return listOf(PsiLanguageInjectionHost::class.java)
    }
}