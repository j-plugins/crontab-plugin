package com.github.xepozz.crontab.language

import com.github.xepozz.crontab.language.psi.CrontabSchedule
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.psi.PsiElement

class CrontabAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        when (element) {
            is CrontabSchedule -> {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(element.textRange)
                    .textAttributes(SCHEDULE_HIGHLIGHT)
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
        private val SCHEDULE_HIGHLIGHT = TextAttributesKey.createTextAttributesKey(
            "CRONTAB_SCHEDULE",
            DefaultLanguageHighlighterColors.FUNCTION_DECLARATION,
        )
//        private val COMMAND_HIGHLIGHT = TextAttributesKey.createTextAttributesKey(
//            "CRONTAB_COMMAND",
//            DefaultLanguageHighlighterColors.STRING,
//        )
    }
}