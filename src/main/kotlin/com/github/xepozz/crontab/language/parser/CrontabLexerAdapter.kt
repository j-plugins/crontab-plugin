package com.github.xepozz.crontab.language.parser

import com.intellij.lexer.FlexAdapter

class CrontabLexerAdapter : FlexAdapter(CrontabLexer(null))