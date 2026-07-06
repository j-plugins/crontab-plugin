package com.github.xepozz.crontab.ide.documentation

import com.github.xepozz.crontab.language.psi.CrontabVariableName
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.testFramework.fixtures.BasePlatformTestCase

class CrontabDocumentationProviderTest : BasePlatformTestCase() {
    fun testVariableDocumentationUsesVariableValue() {
        val file = myFixture.configureByText("test.crontab", "PATH=/usr/bin")
        val variableName = PsiTreeUtil.findChildOfType(file, CrontabVariableName::class.java)!!

        val documentation = CrontabDocumentationProvider().generateDoc(variableName, variableName)!!

        assertTrue(documentation.contains("PATH = /usr/bin"))
        assertTrue(documentation.contains("/usr/bin"))
        assertFalse(documentation.contains("PATH = null"))
    }
}
