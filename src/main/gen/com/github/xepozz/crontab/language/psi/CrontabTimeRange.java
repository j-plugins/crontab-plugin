// This is a generated file. Not intended for manual editing.
package com.github.xepozz.crontab.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import kotlin.ranges.IntRange;

public interface CrontabTimeRange extends PsiElement {

  int getFirst();

  int getSecond();

  @NotNull IntRange getIntRange();

}
