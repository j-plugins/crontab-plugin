// This is a generated file. Not intended for manual editing.
package com.github.xepozz.crontab.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CrontabCronExpression extends PsiElement {

  @Nullable
  CrontabCommand getCommand();

  @NotNull
  CrontabSchedule getSchedule();

}
