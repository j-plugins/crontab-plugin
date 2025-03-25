package com.github.xepozz.crontab.language

import com.github.xepozz.crontab.language.psi.CrontabTimePointer
import com.github.xepozz.crontab.language.psi.CrontabTimeShortcut
import com.github.xepozz.crontab.language.psi.CrontabVariableName
import com.github.xepozz.crontab.language.psi.CrontabVariableValue
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.psi.PsiElement

class CrontabAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        when (element) {
            is CrontabTimeShortcut, is CrontabTimePointer -> {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(element.textRange)
                    .textAttributes(SCHEDULE_HIGHLIGHT)
                    .create()
            }

            is CrontabVariableName -> {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(element.textRange)
                    .textAttributes(IDENTIFIER_HIGHLIGHT)
                    .create()
            }

            is CrontabVariableValue -> {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(element.textRange)
                    .textAttributes(STRING_HIGHLIGHT)
                    .create()
            }

//            is CrontabCommand -> {
//                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
//                    .range(element.textRange)
//                    .textAttributes(COMMAND_HIGHLIGHT)
//                    .create()
//            }
        }
    }

    companion object {
        val SCHEDULE_HIGHLIGHT = TextAttributesKey.createTextAttributesKey(
            "CRONTAB_SCHEDULE",
            DefaultLanguageHighlighterColors.KEYWORD,
        )
        private val IDENTIFIER_HIGHLIGHT = TextAttributesKey.createTextAttributesKey(
            "CRONTAB_IDENTIFIER",
            DefaultLanguageHighlighterColors.IDENTIFIER,
        )
        private val STRING_HIGHLIGHT = TextAttributesKey.createTextAttributesKey(
            "CRONTAB_STRING",
            DefaultLanguageHighlighterColors.STRING,
        )
        private val OPERATION_HIGHLIGHT = TextAttributesKey.createTextAttributesKey(
            "CRONTAB_OPERATION",
            DefaultLanguageHighlighterColors.OPERATION_SIGN,
        )
//        private val COMMAND_HIGHLIGHT = TextAttributesKey.createTextAttributesKey(
//            "CRONTAB_COMMAND",
//            DefaultLanguageHighlighterColors.STRING,
//        )
    }
}