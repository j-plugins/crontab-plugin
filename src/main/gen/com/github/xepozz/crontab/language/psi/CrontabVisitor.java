// This is a generated file. Not intended for manual editing.
package com.github.xepozz.crontab.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLanguageInjectionHost;

public class CrontabVisitor extends PsiElementVisitor {

  public void visitCommand(@NotNull CrontabCommand o) {
    visitPsiLanguageInjectionHost(o);
  }

  public void visitComment(@NotNull CrontabComment o) {
    visitPsiElement(o);
  }

  public void visitSchedule(@NotNull CrontabSchedule o) {
    visitPsiElement(o);
  }

  public void visitTimeAny(@NotNull CrontabTimeAny o) {
    visitPsiElement(o);
  }

  public void visitTimeExact(@NotNull CrontabTimeExact o) {
    visitPsiElement(o);
  }

  public void visitTimeList(@NotNull CrontabTimeList o) {
    visitPsiElement(o);
  }

  public void visitTimeListItem(@NotNull CrontabTimeListItem o) {
    visitPsiElement(o);
  }

  public void visitTimePeriodic(@NotNull CrontabTimePeriodic o) {
    visitPsiElement(o);
  }

  public void visitTimePointer(@NotNull CrontabTimePointer o) {
    visitPsiElement(o);
  }

  public void visitTimeRange(@NotNull CrontabTimeRange o) {
    visitPsiElement(o);
  }

  public void visitTimeRangeStep(@NotNull CrontabTimeRangeStep o) {
    visitPsiElement(o);
  }

  public void visitVariableName(@NotNull CrontabVariableName o) {
    visitPsiElement(o);
  }

  public void visitVariableValue(@NotNull CrontabVariableValue o) {
    visitPsiElement(o);
  }

  public void visitCronExpression(@NotNull CrontabCronExpression o) {
    visitPsiElement(o);
  }

  public void visitVariableDefinition(@NotNull CrontabVariableDefinition o) {
    visitPsiElement(o);
  }

  public void visitPsiLanguageInjectionHost(@NotNull PsiLanguageInjectionHost o) {
    visitElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
