package com.github.xepozz.crontab.ide.inspections

import com.intellij.codeInspection.LocalQuickFix

abstract class CrontabScheduleQuickFix : LocalQuickFix {
    override fun getFamilyName() = this.name

    abstract override fun getName(): String
}