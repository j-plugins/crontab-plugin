// This is a generated file. Not intended for manual editing.
package com.github.xepozz.crontab.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiLiteralValue;

public interface CrontabSchedule extends NavigatablePsiElement, PsiLiteralValue {

  @Nullable
  CrontabTimeShortcut getTimeShortcut();

  @NotNull
  List<CrontabTimePointer> getTimePointerList();

}
