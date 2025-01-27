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
  // TIME_POINTER TIME_POINTER TIME_POINTER TIME_POINTER TIME_POINTER
  public static boolean SCHEDULE(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SCHEDULE")) return false;
    if (!nextTokenIs(b, "<schedule>", NUMBER, STAR)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, SCHEDULE, "<schedule>");
    r = TIME_POINTER(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, TIME_POINTER(b, l + 1));
    r = p && report_error_(b, TIME_POINTER(b, l + 1)) && r;
    r = p && report_error_(b, TIME_POINTER(b, l + 1)) && r;
    r = p && TIME_POINTER(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
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
  // NUMBER
  public static boolean TIME_EXACT(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TIME_EXACT")) return false;
    if (!nextTokenIs(b, NUMBER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NUMBER);
    exit_section_(b, m, TIME_EXACT, r);
    return r;
  }

  /* ********************************************************** */
  // TIME_LIST_ITEM (COMMA TIME_LIST_ITEM)*
  public static boolean TIME_LIST(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TIME_LIST")) return false;
    if (!nextTokenIs(b, NUMBER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = TIME_LIST_ITEM(b, l + 1);
    r = r && TIME_LIST_1(b, l + 1);
    exit_section_(b, m, TIME_LIST, r);
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
  // TIME_RANGE | TIME_EXACT
  public static boolean TIME_LIST_ITEM(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TIME_LIST_ITEM")) return false;
    if (!nextTokenIs(b, NUMBER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = TIME_RANGE(b, l + 1);
    if (!r) r = TIME_EXACT(b, l + 1);
    exit_section_(b, m, TIME_LIST_ITEM, r);
    return r;
  }

  /* ********************************************************** */
  // STAR SLASH NUMBER
  public static boolean TIME_PERIODIC(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TIME_PERIODIC")) return false;
    if (!nextTokenIs(b, STAR)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, TIME_PERIODIC, null);
    r = consumeTokens(b, 2, STAR, SLASH, NUMBER);
    p = r; // pin = 2
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // TIME_PERIODIC | TIME_RANGE_STEP | TIME_LIST | TIME_ANY
  public static boolean TIME_POINTER(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TIME_POINTER")) return false;
    if (!nextTokenIs(b, "<time pointer>", NUMBER, STAR)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TIME_POINTER, "<time pointer>");
    r = TIME_PERIODIC(b, l + 1);
    if (!r) r = TIME_RANGE_STEP(b, l + 1);
    if (!r) r = TIME_LIST(b, l + 1);
    if (!r) r = TIME_ANY(b, l + 1);
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
  // NUMBER SLASH NUMBER
  public static boolean TIME_RANGE_STEP(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TIME_RANGE_STEP")) return false;
    if (!nextTokenIs(b, NUMBER)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, TIME_RANGE_STEP, null);
    r = consumeTokens(b, 2, NUMBER, SLASH, NUMBER);
    p = r; // pin = 2
    exit_section_(b, l, m, r, p, null);
    return r || p;
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
    if (!nextTokenIs(b, "<cron expression>", NUMBER, STAR)) return false;
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
  // cronExpression | variableDefinition | COMMENT | NEWLINE
  static boolean item_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_")) return false;
    boolean r;
    r = cronExpression(b, l + 1);
    if (!r) r = variableDefinition(b, l + 1);
    if (!r) r = COMMENT(b, l + 1);
    if (!r) r = consumeToken(b, NEWLINE);
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
