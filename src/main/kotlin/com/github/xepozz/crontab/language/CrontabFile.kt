package com.github.xepozz.crontab.language

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider

class CrontabFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, CrontabLanguage.INSTANCE) {
    override fun getFileType() = CrontabFileType.INSTANCE

    override fun toString() = "Crontab File"
}