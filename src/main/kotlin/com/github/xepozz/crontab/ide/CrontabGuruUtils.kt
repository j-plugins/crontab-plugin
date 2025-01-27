package com.github.xepozz.crontab.ide

import com.github.xepozz.crontab.language.psi.CrontabSchedule
import com.intellij.ide.BrowserUtil

object CrontabGuruUtils {
    fun openInBrowser(crontabSchedule: CrontabSchedule) {
        BrowserUtil.browse(
            "https://crontab.guru/#" + crontabSchedule.text.split(Regex("[\\s\\t]+")).joinToString("_")
        )
    }
}