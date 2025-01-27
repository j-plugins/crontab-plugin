// This is a generated file. Not intended for manual editing.
package com.github.xepozz.crontab.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.xepozz.crontab.language.psi.CrontabTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.github.xepozz.crontab.language.psi.*;

public class CrontabTimePointerImpl extends ASTWrapperPsiElement implements CrontabTimePointer {

  public CrontabTimePointerImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CrontabVisitor visitor) {
    visitor.visitTimePointer(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CrontabVisitor) accept((CrontabVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public CrontabTimeAny getTimeAny() {
    return findChildByClass(CrontabTimeAny.class);
  }

  @Override
  @Nullable
  public CrontabTimeList getTimeList() {
    return findChildByClass(CrontabTimeList.class);
  }

  @Override
  @Nullable
  public CrontabTimePeriodic getTimePeriodic() {
    return findChildByClass(CrontabTimePeriodic.class);
  }

  @Override
  @Nullable
  public CrontabTimeRangeStep getTimeRangeStep() {
    return findChildByClass(CrontabTimeRangeStep.class);
  }

}
