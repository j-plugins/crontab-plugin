package com.github.xepozz.crontab.language.psi

import com.github.xepozz.crontab.language.CrontabFile
import com.github.xepozz.crontab.language.CrontabFileType
import com.intellij.openapi.project.Project
import com.intellij.psi.util.PsiTreeUtil

object CrontabElementFactory {
    fun createCrontabTimeRange(project: Project, first: Int, last: Int): CrontabTimeRange {
        val file = createFile(project, "$first-$last")

        return PsiTreeUtil.findChildOfType(file, CrontabTimeRange::class.java) as CrontabTimeRange
    }

    fun createFile(project: Project, text: String): CrontabFile {
        val name = "dummy.crontab"
        return com.intellij.psi.PsiFileFactory.getInstance(project)
            .createFileFromText(name, CrontabFileType.INSTANCE, text) as CrontabFile
    }
}