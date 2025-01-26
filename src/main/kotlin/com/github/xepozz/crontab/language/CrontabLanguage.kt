package com.github.xepozz.crontab.language

import com.intellij.lang.Language

class CrontabLanguage : Language("Crontab") {
    companion object {
        val INSTANCE = CrontabLanguage();
    }
}