package com.github.xepozz.crontab.ide

import com.intellij.codeInsight.hints.declarative.InlayHintsProviderFactory
import com.intellij.codeInsight.hints.declarative.InlayProviderInfo
import com.intellij.lang.Language

class CrontabInlayHintsProviderFactory : InlayHintsProviderFactory {
    override fun getProviderInfo(
        language: Language,
        providerId: String
    ) = InlayProviderInfo(
        provider = CrontabInlayHintsProvider(),
        providerId = providerId,
        options = emptySet(),
        isEnabledByDefault = true,
        providerName = CrontabInlayHintsProvider::class.java.name
    )


    override fun getProvidersForLanguage(language: Language) = listOf(getProviderInfo(language, "crontab"))

    override fun getSupportedLanguages() = emptySet<Language>()
}