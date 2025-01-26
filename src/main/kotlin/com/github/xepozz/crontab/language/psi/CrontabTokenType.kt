package com.github.xepozz.crontab.language.psi

import com.github.xepozz.crontab.language.CrontabLanguage
import com.intellij.psi.tree.IElementType

class CrontabTokenType(debugName: String) : IElementType(debugName, CrontabLanguage.INSTANCE) {
    override fun toString() = "CrontabTokenType." + super.toString()
}