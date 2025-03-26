package com.github.xepozz.crontab.ide.completion

import com.github.xepozz.crontab.CrontabIcons
import com.github.xepozz.crontab.language.psi.CrontabTypes
import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.project.DumbAware
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.util.elementType
import com.intellij.util.ProcessingContext

class CrontabShortcutCompletionContributor : CompletionContributor(), DumbAware {
    init {
        extend(
            CompletionType.BASIC,
            PlatformPatterns.psiElement()
                .withParent(PlatformPatterns.psiElement(CrontabTypes.TIME_SHORTCUT)),
            object : CompletionProvider<CompletionParameters>(), DumbAware {
                override fun addCompletions(
                    parameters: CompletionParameters,
                    context: ProcessingContext,
                    result: CompletionResultSet
                ) {
                    val element = parameters.position

                    val hasLeadingAt = element.prevSibling.elementType == CrontabTypes.AT

                    arrayOf(
                        "@hourly",
                        "@daily",
                        "@weekly",
                        "@monthly",
                        "@yearly",
                        "@annually",
                        "@reboot",
                    )
                        .map {
                            LookupElementBuilder.create(it)
                                .withBaseLookupString(if (hasLeadingAt) it.substring(1) else it)
                                .withIcon(CrontabIcons.FILE)
                        }
                        .apply { result.addAllElements(this) }
                }
            }
        )
    }
}