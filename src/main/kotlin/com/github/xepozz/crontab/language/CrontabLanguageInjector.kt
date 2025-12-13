package com.github.xepozz.crontab.language

import com.github.xepozz.crontab.language.psi.CrontabCommand
import com.intellij.lang.Language
import com.intellij.lang.injection.MultiHostInjector
import com.intellij.lang.injection.MultiHostRegistrar
import com.intellij.psi.PsiElement

class CrontabLanguageInjector : MultiHostInjector {
    val shellLanguage = Language.findLanguageByID("Shell Script")
    override fun getLanguagesToInject(registrar: MultiHostRegistrar, element: PsiElement) {
        if (shellLanguage == null) return

        when (element) {
            is CrontabCommand -> {
                val textRange = element.textRange
                if (!textRange.isEmpty && element.text.isNotEmpty()) {
                    val range = textRange.shiftLeft(element.textOffset)
                    registrar.startInjecting(shellLanguage)
                        .addPlace(null, null, element, range)
                        .doneInjecting()
                }
            }
        }
    }

    override fun elementsToInjectIn() = listOf(CrontabCommand::class.java)
}