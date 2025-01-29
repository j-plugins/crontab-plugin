package com.github.xepozz.crontab.ide

import com.github.xepozz.crontab.language.psi.CrontabSchedule
import com.intellij.ide.BrowserUtil

object CrontabGuruUtils {
    fun openInBrowser(crontabSchedule: CrontabSchedule) {
        BrowserUtil.browse(
            generateCrontabGuruUrl(crontabSchedule)
        )
    }

    fun generateCrontabGuruUrl(crontabSchedule: CrontabSchedule): String = "https://crontab.guru/#" +
            crontabSchedule.text.split(Regex("[\\s\\t]+")).joinToString("_")
}