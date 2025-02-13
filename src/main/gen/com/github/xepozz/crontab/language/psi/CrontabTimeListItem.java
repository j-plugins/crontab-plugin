// This is a generated file. Not intended for manual editing.
package com.github.xepozz.crontab.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CrontabTimeListItem extends PsiElement {

  @Nullable
  CrontabTimeAny getTimeAny();

  @Nullable
  CrontabTimeExactDay getTimeExactDay();

  @Nullable
  CrontabTimeExactMonth getTimeExactMonth();

  @Nullable
  CrontabTimeExactNumber getTimeExactNumber();

  @Nullable
  CrontabTimeRange getTimeRange();

  @Nullable
  CrontabTimeRangeStep getTimeRangeStep();

}
