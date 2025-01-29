// This is a generated file. Not intended for manual editing.
package com.github.xepozz.crontab.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.NavigatablePsiElement;

public interface CrontabCronExpression extends NavigatablePsiElement {

  @Nullable
  CrontabCommand getCommand();

  @NotNull
  CrontabSchedule getSchedule();

}
