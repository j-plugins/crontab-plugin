package com.github.xepozz.crontab.language

import com.github.xepozz.crontab.CrontabIcons
import com.intellij.openapi.fileTypes.LanguageFileType

class CrontabFileType private constructor() : LanguageFileType(CrontabLanguage.INSTANCE) {
    override fun getName() = "Crontab File"

    override fun getDescription() = "Crontab language file"

    override fun getDefaultExtension() = ""

    override fun getIcon() = CrontabIcons.FILE

    companion object {
        @JvmStatic
        val INSTANCE = CrontabFileType()
    }
}