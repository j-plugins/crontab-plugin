// This is a generated file. Not intended for manual editing.
package com.github.xepozz.crontab.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.xepozz.crontab.language.psi.CrontabTypes.*;
import com.github.xepozz.crontab.language.psi.*;
import kotlin.ranges.IntRange;

public class CrontabTimeRangeImpl extends CrontabTimeRangeBaseImpl implements CrontabTimeRange {

  public CrontabTimeRangeImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CrontabVisitor visitor) {
    visitor.visitTimeRange(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CrontabVisitor) accept((CrontabVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<CrontabTimeExactNumber> getTimeExactNumberList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CrontabTimeExactNumber.class);
  }

  @Override
  public int getFirst() {
    return CrontabImplUtil.getFirst(this);
  }

  @Override
  public int getSecond() {
    return CrontabImplUtil.getSecond(this);
  }

  @Override
  public @NotNull IntRange getIntRange() {
    return CrontabImplUtil.getIntRange(this);
  }

}
