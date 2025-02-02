// This is a generated file. Not intended for manual editing.
package com.github.xepozz.crontab.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CrontabTimeRangeStep extends PsiElement {

  @Nullable
  CrontabTimeAny getTimeAny();

  @Nullable
  CrontabTimeExact getTimeExact();

  @Nullable
  CrontabTimeRange getTimeRange();

}
