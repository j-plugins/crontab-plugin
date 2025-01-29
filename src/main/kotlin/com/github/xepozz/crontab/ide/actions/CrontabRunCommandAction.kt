package com.github.xepozz.crontab.ide.actions

import com.github.xepozz.crontab.language.psi.CrontabVariableDefinition
import com.intellij.execution.ProgramRunnerUtil
import com.intellij.execution.RunManager
import com.intellij.execution.configuration.EnvironmentVariablesData
import com.intellij.execution.executors.DefaultRunExecutor
import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.util.text.StringUtil
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.sh.run.ShConfigurationType
import com.intellij.sh.run.ShRunConfiguration

class CrontabRunCommandAction : AnAction {
    private val command: String

    constructor(command: String) : super("Run \"$command\" in Terminal", null, AllIcons.Actions.Execute) {
        this.command = command
    }

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return

        val runManager = RunManager.getInstance(project)
        val configurationSettings = runManager.createConfiguration("Cron Command", ShConfigurationType::class.java)

        val runConfiguration = configurationSettings.configuration as ShRunConfiguration
        runConfiguration.scriptText = command
        runConfiguration.isExecuteScriptFile = false
        runConfiguration.scriptWorkingDirectory = project.basePath
        val psiFile = e.getData(CommonDataKeys.PSI_FILE) ?: return
        val variablesMap = mutableMapOf<String, String>()

        PsiTreeUtil.findChildrenOfType(psiFile, CrontabVariableDefinition::class.java)
            .forEach {
                val value = it.variableValue ?: return@forEach
                variablesMap[it.variableName.text] = StringUtil.unquoteString(value.text)
            }

        val envVariablesData = EnvironmentVariablesData.create(variablesMap, true)

        runConfiguration.envData = envVariablesData

        ProgramRunnerUtil.executeConfiguration(configurationSettings, DefaultRunExecutor.getRunExecutorInstance())
    }

    override fun getActionUpdateThread() = ActionUpdateThread.BGT
}