package com.github.xepozz.crontab.language.parser

import com.github.xepozz.crontab.language.CrontabFile
import com.github.xepozz.crontab.language.CrontabLanguage
import com.github.xepozz.crontab.language.psi.CrontabTokenSets
import com.github.xepozz.crontab.language.psi.CrontabTypes
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet

internal class CrontabParserDefinition : ParserDefinition {
    override fun createLexer(project: Project) = CrontabLexerAdapter()

    override fun getCommentTokens() = CrontabTokenSets.COMMENTS

    override fun getWhitespaceTokens(): TokenSet = CrontabTokenSets.WHITESPACES

    override fun getStringLiteralElements(): TokenSet = CrontabTokenSets.STRING_LITERALS

    override fun createParser(project: Project?) = CrontabParser()

    override fun getFileNodeType() = FILE

    override fun createFile(viewProvider: FileViewProvider) = CrontabFile(viewProvider)

    override fun createElement(node: ASTNode): PsiElement = CrontabTypes.Factory.createElement(node)

    companion object {
        val FILE = IFileElementType(CrontabLanguage.INSTANCE)
    }
}