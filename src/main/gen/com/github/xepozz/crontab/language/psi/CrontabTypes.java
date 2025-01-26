// This is a generated file. Not intended for manual editing.
package com.github.xepozz.crontab.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.xepozz.crontab.language.psi.impl.*;

public interface CrontabTypes {

  IElementType COMMAND = new CrontabElementType("COMMAND");
  IElementType COMMENT = new CrontabElementType("COMMENT");
  IElementType CRON_EXPRESSION = new CrontabElementType("CRON_EXPRESSION");
  IElementType SCHEDULE = new CrontabElementType("SCHEDULE");
  IElementType TIME_POINTER = new CrontabElementType("TIME_POINTER");

  IElementType CONTENT = new CrontabTokenType("CONTENT");
  IElementType NEWLINE = new CrontabTokenType("NEWLINE");
  IElementType NUMBER = new CrontabTokenType("NUMBER");
  IElementType SINGLE_COMMENT = new CrontabTokenType("SINGLE_COMMENT");
  IElementType SLASH = new CrontabTokenType("SLASH");
  IElementType STAR = new CrontabTokenType("STAR");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == COMMAND) {
        return new CrontabCommandImpl(node);
      }
      else if (type == COMMENT) {
        return new CrontabCommentImpl(node);
      }
      else if (type == CRON_EXPRESSION) {
        return new CrontabCronExpressionImpl(node);
      }
      else if (type == SCHEDULE) {
        return new CrontabScheduleImpl(node);
      }
      else if (type == TIME_POINTER) {
        return new CrontabTimePointerImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
