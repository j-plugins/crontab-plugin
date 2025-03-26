// This is a generated file. Not intended for manual editing.
package com.github.xepozz.crontab.language.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.github.xepozz.crontab.language.psi.CrontabTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class CrontabParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return crontabFile(b, l + 1);
  }

  /* ********************************************************** */
  // CONTENT
  public static boolean COMMAND(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "COMMAND")) return false;
    if (!nextTokenIs(b, CONTENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CONTENT);
    exit_section_(b, m, COMMAND, r);
    return r;
  }

  /* ********************************************************** */
  // SINGLE_COMMENT
  public static boolean COMMENT(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "COMMENT")) return false;
    if (!nextTokenIs(b, SINGLE_COMMENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SINGLE_COMMENT);
    exit_section_(b, m, COMMENT, r);
    return r;
  }

  /* ********************************************************** */
  // (TIME_POINTER TIME_POINTER TIME_POINTER TIME_POINTER TIME_POINTER) | TIME_SHORTCUT
  public static boolean SCHEDULE(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SCHEDULE")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SCHEDULE, "<schedule>");
    r = SCHEDULE_0(b, l + 1);
    if (!r) r = TIME_SHORTCUT(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // TIME_POINTER TIME_POINTER TIME_POINTER TIME_POINTER TIME_POINTER
  private static boolean SCHEDULE_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SCHEDULE_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = TIME_POINTER(b, l + 1);
    r = r && TIME_POINTER(b, l + 1);
    r = r && TIME_POINTER(b, l + 1);
    r = r && TIME_POINTER(b, l + 1);
    r = r && TIME_POINTER(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // STAR
  public static boolean TIME_ANY(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TIME_ANY")) return false;
    if (!nextTokenIs(b, STAR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STAR);
    exit_section_(b, m, TIME_ANY, r);
    return r;
  }

  /* ********************************************************** */
  // DAY
  public static boolean TIME_EXACT_DAY(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TIME_EXACT_DAY")) return false;
    if (!nextTokenIs(b, DAY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DAY);
    exit_section_(b, m, TIME_EXACT_DAY, r);
    return r;
  }

  /* ********************************************************** */
  // MONTH
  public static boolean TIME_EXACT_MONTH(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TIME_EXACT_MONTH")) return false;
    if (!nextTokenIs(b, MONTH)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, MONTH);
    exit_section_(b, m, TIME_EXACT_MONTH, r);
    return r;
  }

  /* ********************************************************** */
  // NUMBER
  public static boolean TIME_EXACT_NUMBER(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TIME_EXACT_NUMBER")) return false;
    if (!nextTokenIs(b, NUMBER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NUMBER);
    exit_section_(b, m, TIME_EXACT_NUMBER, r);
    return r;
  }

  /* ********************************************************** */
  // TIME_LIST_ITEM (COMMA TIME_LIST_ITEM)*
  public static boolean TIME_LIST(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TIME_LIST")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TIME_LIST, "<time list>");
    r = TIME_LIST_ITEM(b, l + 1);
    r = r && TIME_LIST_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA TIME_LIST_ITEM)*
  private static boolean TIME_LIST_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TIME_LIST_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!TIME_LIST_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "TIME_LIST_1", c)) break;
    }
    return true;
  }

  // COMMA TIME_LIST_ITEM
  private static boolean TIME_LIST_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TIME_LIST_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && TIME_LIST_ITEM(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // TIME_RANGE_STEP | TIME_RANGE | TIME_RANGE_DAY | TIME_RANGE_MONTH | TIME_EXACT_NUMBER | TIME_EXACT_DAY | TIME_EXACT_MONTH | TIME_ANY
  public static boolean TIME_LIST_ITEM(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TIME_LIST_ITEM")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TIME_LIST_ITEM, "<time list item>");
    r = TIME_RANGE_STEP(b, l + 1);
    if (!r) r = TIME_RANGE(b, l + 1);
    if (!r) r = TIME_RANGE_DAY(b, l + 1);
    if (!r) r = TIME_RANGE_MONTH(b, l + 1);
    if (!r) r = TIME_EXACT_NUMBER(b, l + 1);
    if (!r) r = TIME_EXACT_DAY(b, l + 1);
    if (!r) r = TIME_EXACT_MONTH(b, l + 1);
    if (!r) r = TIME_ANY(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // TIME_LIST
  public static boolean TIME_POINTER(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TIME_POINTER")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TIME_POINTER, "<time pointer>");
    r = TIME_LIST(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // NUMBER HYPHEN NUMBER
  public static boolean TIME_RANGE(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TIME_RANGE")) return false;
    if (!nextTokenIs(b, NUMBER)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, TIME_RANGE, null);
    r = consumeTokens(b, 2, NUMBER, HYPHEN, NUMBER);
    p = r; // pin = 2
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // DAY HYPHEN DAY
  public static boolean TIME_RANGE_DAY(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TIME_RANGE_DAY")) return false;
    if (!nextTokenIs(b, DAY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DAY, HYPHEN, DAY);
    exit_section_(b, m, TIME_RANGE_DAY, r);
    return r;
  }

  /* ********************************************************** */
  // MONTH HYPHEN MONTH
  public static boolean TIME_RANGE_MONTH(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TIME_RANGE_MONTH")) return false;
    if (!nextTokenIs(b, MONTH)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, MONTH, HYPHEN, MONTH);
    exit_section_(b, m, TIME_RANGE_MONTH, r);
    return r;
  }

  /* ********************************************************** */
  // (TIME_RANGE | TIME_EXACT_NUMBER | TIME_ANY) SLASH NUMBER
  public static boolean TIME_RANGE_STEP(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TIME_RANGE_STEP")) return false;
    if (!nextTokenIs(b, "<time range step>", NUMBER, STAR)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, TIME_RANGE_STEP, "<time range step>");
    r = TIME_RANGE_STEP_0(b, l + 1);
    r = r && consumeTokens(b, 1, SLASH, NUMBER);
    p = r; // pin = 2
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // TIME_RANGE | TIME_EXACT_NUMBER | TIME_ANY
  private static boolean TIME_RANGE_STEP_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TIME_RANGE_STEP_0")) return false;
    boolean r;
    r = TIME_RANGE(b, l + 1);
    if (!r) r = TIME_EXACT_NUMBER(b, l + 1);
    if (!r) r = TIME_ANY(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // AT CONTENT
  public static boolean TIME_SHORTCUT(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TIME_SHORTCUT")) return false;
    if (!nextTokenIs(b, AT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, AT, CONTENT);
    exit_section_(b, m, TIME_SHORTCUT, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean VARIABLE_NAME(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VARIABLE_NAME")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, VARIABLE_NAME, r);
    return r;
  }

  /* ********************************************************** */
  // CONTENT
  public static boolean VARIABLE_VALUE(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VARIABLE_VALUE")) return false;
    if (!nextTokenIs(b, CONTENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CONTENT);
    exit_section_(b, m, VARIABLE_VALUE, r);
    return r;
  }

  /* ********************************************************** */
  // SCHEDULE COMMAND
  public static boolean cronExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "cronExpression")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CRON_EXPRESSION, "<cron expression>");
    r = SCHEDULE(b, l + 1);
    p = r; // pin = 1
    r = r && COMMAND(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // item_*
  static boolean crontabFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "crontabFile")) return false;
    while (true) {
      int c = current_position_(b);
      if (!item_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "crontabFile", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // cronExpression | variableDefinition | COMMENT
  static boolean item_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_")) return false;
    boolean r;
    r = cronExpression(b, l + 1);
    if (!r) r = variableDefinition(b, l + 1);
    if (!r) r = COMMENT(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // VARIABLE_NAME EQUAL_SIGN VARIABLE_VALUE
  public static boolean variableDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableDefinition")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, VARIABLE_DEFINITION, null);
    r = VARIABLE_NAME(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, consumeToken(b, EQUAL_SIGN));
    r = p && VARIABLE_VALUE(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

}
