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

public class CrontabVariableDefinitionImpl extends CrontabVariableBaseImpl implements CrontabVariableDefinition {

  public CrontabVariableDefinitionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CrontabVisitor visitor) {
    visitor.visitVariableDefinition(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CrontabVisitor) accept((CrontabVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public CrontabVariableName getVariableName() {
    return findNotNullChildByClass(CrontabVariableName.class);
  }

  @Override
  @Nullable
  public CrontabVariableValue getVariableValue() {
    return findChildByClass(CrontabVariableValue.class);
  }

}
