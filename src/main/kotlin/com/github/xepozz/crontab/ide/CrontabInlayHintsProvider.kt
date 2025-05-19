package com.github.xepozz.crontab.ide

import com.github.xepozz.crontab.language.psi.CrontabCronExpression
import com.github.xepozz.crontab.language.psi.CrontabElementFactory
import com.github.xepozz.crontab.language.psi.CrontabSchedule
import com.intellij.codeInsight.hints.declarative.HintFormat
import com.intellij.codeInsight.hints.declarative.InlayHintsCollector
import com.intellij.codeInsight.hints.declarative.InlayHintsProvider
import com.intellij.codeInsight.hints.declarative.InlayTreeSink
import com.intellij.codeInsight.hints.declarative.InlineInlayPosition
import com.intellij.codeInsight.hints.declarative.SharedBypassCollector
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.endOffset

class CrontabInlayHintsProvider : InlayHintsProvider {
    override fun createCollector(
        file: PsiFile,
        editor: Editor
    ): InlayHintsCollector {
        return object : SharedBypassCollector {
            override fun collectFromElement(element: PsiElement, sink: InlayTreeSink) {
                val crontabFile = CrontabElementFactory.createFile(element.project, element.text)
                val expression = crontabFile.children.getOrNull(0) as? CrontabCronExpression ?: return
                if (expression.schedule.text != element.text) {
                    return
                }

                val schedules = PsiTreeUtil.findChildrenOfType(crontabFile, CrontabSchedule::class.java)
//                println("schedules: ${schedules}")

                if (schedules.isNotEmpty()) {

                    val offset = when {
                        element.nextSibling.text in arrayOf("\"", "'", "`") -> element.nextSibling.endOffset
                        else -> element.endOffset
                    }
                    sink.addPresentation(
                        position = InlineInlayPosition(offset, false, 100),
                        hintFormat = HintFormat.default,
                    ) {
                        text(CronScheduleDescriber.asHumanReadable(element.text))
                    }
                }
            }
        }
    }
}