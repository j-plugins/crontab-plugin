package com.github.xepozz.crontab.language.parser

import com.github.xepozz.crontab.language.psi.CrontabTypes
import com.intellij.lexer.FlexAdapter
import com.intellij.lexer.MergingLexerAdapter
import com.intellij.psi.tree.TokenSet

class CrontabLexerAdapter : MergingLexerAdapter(
    FlexAdapter(CrontabLexer(null)),
    TokenSet.create(CrontabTypes.SINGLE_COMMENT, CrontabTypes.EOL)
)