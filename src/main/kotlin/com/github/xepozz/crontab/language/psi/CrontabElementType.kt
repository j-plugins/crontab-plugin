package com.github.xepozz.crontab.language.psi

import com.github.xepozz.crontab.language.CrontabLanguage
import com.intellij.psi.tree.IElementType

class CrontabElementType(debugName: String) : IElementType("CrontabElementType($debugName)", CrontabLanguage.INSTANCE)
