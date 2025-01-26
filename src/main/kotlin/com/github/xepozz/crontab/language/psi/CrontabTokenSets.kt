package com.github.xepozz.crontab.language.psi

import com.intellij.psi.tree.TokenSet

object CrontabTokenSets {
    val EMPTY_SET = TokenSet.EMPTY

    val COMMENTS = TokenSet.create(CrontabTypes.COMMENT)
    val STRING_LITERALS = TokenSet.create(CrontabTypes.COMMAND, CrontabTypes.CONTENT)
    val WHITESPACES = TokenSet.WHITE_SPACE
}