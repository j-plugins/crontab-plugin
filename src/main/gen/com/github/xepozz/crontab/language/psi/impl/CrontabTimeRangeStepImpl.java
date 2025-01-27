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

public class CrontabTimeRangeStepImpl extends ASTWrapperPsiElement implements CrontabTimeRangeStep {

  public CrontabTimeRangeStepImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CrontabVisitor visitor) {
    visitor.visitTimeRangeStep(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CrontabVisitor) accept((CrontabVisitor)visitor);
    else super.accept(visitor);
  }

}
