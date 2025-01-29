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
  IElementType TIME_ANY = new CrontabElementType("TIME_ANY");
  IElementType TIME_EXACT = new CrontabElementType("TIME_EXACT");
  IElementType TIME_LIST = new CrontabElementType("TIME_LIST");
  IElementType TIME_LIST_ITEM = new CrontabElementType("TIME_LIST_ITEM");
  IElementType TIME_PERIODIC = new CrontabElementType("TIME_PERIODIC");
  IElementType TIME_POINTER = new CrontabElementType("TIME_POINTER");
  IElementType TIME_RANGE = new CrontabElementType("TIME_RANGE");
  IElementType TIME_RANGE_STEP = new CrontabElementType("TIME_RANGE_STEP");
  IElementType VARIABLE_DEFINITION = new CrontabElementType("VARIABLE_DEFINITION");
  IElementType VARIABLE_NAME = new CrontabElementType("VARIABLE_NAME");
  IElementType VARIABLE_VALUE = new CrontabElementType("VARIABLE_VALUE");

  IElementType COMMA = new CrontabTokenType("COMMA");
  IElementType CONTENT = new CrontabTokenType("CONTENT");
  IElementType EQUAL_SIGN = new CrontabTokenType("EQUAL_SIGN");
  IElementType HYPHEN = new CrontabTokenType("HYPHEN");
  IElementType IDENTIFIER = new CrontabTokenType("IDENTIFIER");
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
      else if (type == TIME_ANY) {
        return new CrontabTimeAnyImpl(node);
      }
      else if (type == TIME_EXACT) {
        return new CrontabTimeExactImpl(node);
      }
      else if (type == TIME_LIST) {
        return new CrontabTimeListImpl(node);
      }
      else if (type == TIME_LIST_ITEM) {
        return new CrontabTimeListItemImpl(node);
      }
      else if (type == TIME_PERIODIC) {
        return new CrontabTimePeriodicImpl(node);
      }
      else if (type == TIME_POINTER) {
        return new CrontabTimePointerImpl(node);
      }
      else if (type == TIME_RANGE) {
        return new CrontabTimeRangeImpl(node);
      }
      else if (type == TIME_RANGE_STEP) {
        return new CrontabTimeRangeStepImpl(node);
      }
      else if (type == VARIABLE_DEFINITION) {
        return new CrontabVariableDefinitionImpl(node);
      }
      else if (type == VARIABLE_NAME) {
        return new CrontabVariableNameImpl(node);
      }
      else if (type == VARIABLE_VALUE) {
        return new CrontabVariableValueImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
