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
  // TIME_POINTER+
  public static boolean SCHEDULE(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SCHEDULE")) return false;
    if (!nextTokenIs(b, "<schedule>", NUMBER, STAR)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SCHEDULE, "<schedule>");
    r = TIME_POINTER(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!TIME_POINTER(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "SCHEDULE", c)) break;
    }
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (STAR SLASH NUMBER) | STAR | NUMBER
  public static boolean TIME_POINTER(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TIME_POINTER")) return false;
    if (!nextTokenIs(b, "<time pointer>", NUMBER, STAR)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TIME_POINTER, "<time pointer>");
    r = TIME_POINTER_0(b, l + 1);
    if (!r) r = consumeToken(b, STAR);
    if (!r) r = consumeToken(b, NUMBER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // STAR SLASH NUMBER
  private static boolean TIME_POINTER_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TIME_POINTER_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, STAR, SLASH, NUMBER);
    exit_section_(b, m, null, r);
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
  // cronExpression | COMMENT | NEWLINE
  static boolean item_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_")) return false;
    boolean r;
    r = cronExpression(b, l + 1);
    if (!r) r = COMMENT(b, l + 1);
    if (!r) r = consumeToken(b, NEWLINE);
    return r;
  }

}
