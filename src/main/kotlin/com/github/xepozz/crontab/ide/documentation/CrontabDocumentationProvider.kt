package com.github.xepozz.crontab.ide.documentation

import com.github.xepozz.crontab.ide.CronScheduleDescriber
import com.github.xepozz.crontab.ide.CrontabGuruUtils
import com.github.xepozz.crontab.language.CrontabFile
import com.github.xepozz.crontab.language.psi.CrontabCommand
import com.github.xepozz.crontab.language.psi.CrontabCronExpression
import com.github.xepozz.crontab.language.psi.CrontabSchedule
import com.intellij.extapi.psi.ASTDelegatePsiElement
import com.intellij.lang.ASTNode
import com.intellij.lang.documentation.DocumentationMarkup
import com.intellij.lang.documentation.DocumentationProvider
import com.intellij.lang.documentation.QuickDocHighlightingHelper
import com.intellij.markdown.utils.doc.DocMarkdownToHtmlConverter
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiDocCommentBase
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.presentation.java.SymbolPresentationUtil
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.sh.ShLanguage
import java.util.function.Consumer

class CrontabDocumentationProvider : DocumentationProvider {
    override fun getUrlFor(element: PsiElement?, originalElement: PsiElement?): List<String>? {
        if (element !is CrontabSchedule) return null
        return listOf(CrontabGuruUtils.generateCrontabGuruUrl(element))
    }

    override fun getCustomDocumentationElement(
        editor: Editor,
        file: PsiFile,
        contextElement: PsiElement?,
        targetOffset: Int
    ): PsiElement? {
        if (file !is CrontabFile) return null

        return PsiTreeUtil.findFirstParent(contextElement) { it is CrontabSchedule }
    }

    /**
     * Extracts the key, value, file and documentation comment of a Simple key/value entry and returns
     * a formatted representation of the information.
     */
    override fun generateDoc(element: PsiElement?, originalElement: PsiElement?): String? {
        return when (element) {
            is CrontabSchedule -> {
                val file = SymbolPresentationUtil.getFilePathPresentation(element.containingFile)
                val cronExpression = element.parent as? CrontabCronExpression
                val command = cronExpression?.children[1] as? CrontabCommand
                val docComment = CrontabDocumentationUtils.findCronExpressionDocumentation(cronExpression)

                renderFullDoc(element, command, file, docComment, element.project)
            }

            else -> null
        }
    }

    override fun getQuickNavigateInfo(element: PsiElement?, originalElement: PsiElement?): String? {
        return when (element) {
            is CrontabSchedule -> CronScheduleDescriber.asHumanReadable(element.text)

            else -> "element.text: ${element?.text}"
        }
        return null
    }

    override fun generateRenderedDoc(comment: PsiDocCommentBase) = markdownToHtml(comment.text, comment.project)

    fun markdownToHtml(string: String, project: Project) = string
        .split("\n")
        .joinToString("\n") { it.replaceFirst(Regex("#+\\s+"), "") }
        .let { DocMarkdownToHtmlConverter.convert(project, it) }

    override fun findDocComment(file: PsiFile, range: TextRange): PsiDocCommentBase? {
        val element = file.findElementAt(range.startOffset) as? PsiComment ?: return null

        return object : PsiCommentDelegate(element), PsiDocCommentBase {
            override fun getOwner() = element
        }
    }

    override fun collectDocComments(file: PsiFile, sink: Consumer<in PsiDocCommentBase>) {
        PsiTreeUtil.findChildrenOfType(file, CrontabCronExpression::class.java)
            .forEach { expression ->
                CrontabDocumentationUtils.findCronExpressionDocumentationElements(expression)
                    .forEach { comment ->
                        sink.accept(object : PsiCommentDelegate(comment), PsiDocCommentBase {
                            override fun getOwner() = expression
                        })
                    }
            }
    }

    override fun generateHoverDoc(element: PsiElement, originalElement: PsiElement?) =
        generateDoc(element, originalElement)

    private fun addKeyValueSection(key: String?, value: String?, sb: StringBuilder) {
        sb.append(DocumentationMarkup.SECTION_HEADER_START)
        sb.append(key)
        sb.append(DocumentationMarkup.SECTION_SEPARATOR)
        sb.append(value)
        sb.append(DocumentationMarkup.SECTION_END)
    }

    private fun renderFullDoc(
        schedule: CrontabSchedule,
        command: CrontabCommand?,
        file: String?,
        docComment: String,
        project: Project,
    ) = buildString {

        append(DocumentationMarkup.DEFINITION_START)
        append(CronScheduleDescriber.asHumanReadable(schedule.text))
        append(DocumentationMarkup.DEFINITION_END)

        append(DocumentationMarkup.SECTIONS_START)
        append(DocumentationMarkup.SECTION_START)
        if (command?.text != null) {
            try {
                QuickDocHighlightingHelper.getStyledCodeBlock(project, ShLanguage.INSTANCE, command.text)
                    .apply { append(this) }
            } catch (_: Exception) {
                append("<pre><code>${command.text}</code></pre>")
            }
        }
        append(DocumentationMarkup.SECTION_END)
        append(DocumentationMarkup.SECTIONS_END)

        if (docComment.isNotEmpty()) {
            append(DocumentationMarkup.CONTENT_START)
            append(markdownToHtml(docComment, project))
            append(DocumentationMarkup.CONTENT_END)
        }
        append(DocumentationMarkup.SECTIONS_START)
        append(DocumentationMarkup.SECTION_START)
        addKeyValueSection("Schedule:", schedule.text, this)
        addKeyValueSection("Source:", file, this)
        append(DocumentationMarkup.SECTION_END)
        append(DocumentationMarkup.SECTIONS_END)
    }
}

open class PsiCommentDelegate(val myComment: PsiComment) : PsiComment, ASTDelegatePsiElement() {
    override fun getTokenType() = myComment.tokenType

    override fun getParent(): PsiElement? = myComment.parent

    override fun getNode(): ASTNode = myComment.node
}