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
    b = adapt_builder_(t, b, this, EXTENDS_SETS_);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return crontab_file(b, l + 1);
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(DAY_PATTERN, HOUR_PATTERN, MINUTE_PATTERN, MONTH_PATTERN,
      TIME_POINTER, WEEK_PATTERN),
  };

  /* ********************************************************** */
  // CONTENT | EOL
  public static boolean command(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "command")) return false;
    if (!nextTokenIs(b, "<command>", CONTENT, EOL)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COMMAND, "<command>");
    r = consumeToken(b, CONTENT);
    if (!r) r = consumeToken(b, EOL);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // schedule command
  public static boolean cron_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "cron_expression")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CRON_EXPRESSION, "<cron expression>");
    r = schedule(b, l + 1);
    p = r; // pin = 1
    r = r && command(b, l + 1);
    exit_section_(b, l, m, r, p, CrontabParser::cron_expression_recover);
    return r || p;
  }

  /* ********************************************************** */
  // !(EOL)
  static boolean cron_expression_recover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "cron_expression_recover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !consumeToken(b, EOL);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // item_*
  static boolean crontab_file(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "crontab_file")) return false;
    while (true) {
      int c = current_position_(b);
      if (!item_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "crontab_file", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // time_pointer
  public static boolean day_pattern(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "day_pattern")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, DAY_PATTERN, "<day pattern>");
    r = time_pointer(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // time_pointer
  public static boolean hour_pattern(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "hour_pattern")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, HOUR_PATTERN, "<hour pattern>");
    r = time_pointer(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // variable_definition | cron_expression | SINGLE_COMMENT | EOL
  static boolean item_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = variable_definition(b, l + 1);
    if (!r) r = cron_expression(b, l + 1);
    if (!r) r = consumeToken(b, SINGLE_COMMENT);
    if (!r) r = consumeToken(b, EOL);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // time_pointer
  public static boolean minute_pattern(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "minute_pattern")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, MINUTE_PATTERN, "<minute pattern>");
    r = time_pointer(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // time_pointer
  public static boolean month_pattern(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "month_pattern")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, MONTH_PATTERN, "<month pattern>");
    r = time_pointer(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (minute_pattern hour_pattern day_pattern month_pattern week_pattern) | time_shortcut
  public static boolean schedule(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "schedule")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SCHEDULE, "<schedule>");
    r = schedule_0(b, l + 1);
    if (!r) r = time_shortcut(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // minute_pattern hour_pattern day_pattern month_pattern week_pattern
  private static boolean schedule_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "schedule_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = minute_pattern(b, l + 1);
    r = r && hour_pattern(b, l + 1);
    r = r && day_pattern(b, l + 1);
    r = r && month_pattern(b, l + 1);
    r = r && week_pattern(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // STAR
  public static boolean time_any(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "time_any")) return false;
    if (!nextTokenIs(b, STAR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STAR);
    exit_section_(b, m, TIME_ANY, r);
    return r;
  }

  /* ********************************************************** */
  // WEEKDAY
  public static boolean time_exact_day(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "time_exact_day")) return false;
    if (!nextTokenIs(b, WEEKDAY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, WEEKDAY);
    exit_section_(b, m, TIME_EXACT_DAY, r);
    return r;
  }

  /* ********************************************************** */
  // MONTH
  public static boolean time_exact_month(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "time_exact_month")) return false;
    if (!nextTokenIs(b, MONTH)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, MONTH);
    exit_section_(b, m, TIME_EXACT_MONTH, r);
    return r;
  }

  /* ********************************************************** */
  // NUMBER
  public static boolean time_exact_number(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "time_exact_number")) return false;
    if (!nextTokenIs(b, NUMBER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NUMBER);
    exit_section_(b, m, TIME_EXACT_NUMBER, r);
    return r;
  }

  /* ********************************************************** */
  // time_list_item (COMMA time_list_item)*
  public static boolean time_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "time_list")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TIME_LIST, "<time list>");
    r = time_list_item(b, l + 1);
    r = r && time_list_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA time_list_item)*
  private static boolean time_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "time_list_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!time_list_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "time_list_1", c)) break;
    }
    return true;
  }

  // COMMA time_list_item
  private static boolean time_list_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "time_list_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && time_list_item(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // time_range_step | time_range | time_range_day | time_range_month | time_exact_number | time_exact_day | time_exact_month | time_any
  public static boolean time_list_item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "time_list_item")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TIME_LIST_ITEM, "<time list item>");
    r = time_range_step(b, l + 1);
    if (!r) r = time_range(b, l + 1);
    if (!r) r = time_range_day(b, l + 1);
    if (!r) r = time_range_month(b, l + 1);
    if (!r) r = time_exact_number(b, l + 1);
    if (!r) r = time_exact_day(b, l + 1);
    if (!r) r = time_exact_month(b, l + 1);
    if (!r) r = time_any(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // time_list
  public static boolean time_pointer(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "time_pointer")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TIME_POINTER, "<time pointer>");
    r = time_list(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // time_exact_number HYPHEN time_exact_number
  public static boolean time_range(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "time_range")) return false;
    if (!nextTokenIs(b, NUMBER)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, TIME_RANGE, null);
    r = time_exact_number(b, l + 1);
    r = r && consumeToken(b, HYPHEN);
    p = r; // pin = 2
    r = r && time_exact_number(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // time_exact_day HYPHEN time_exact_day
  public static boolean time_range_day(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "time_range_day")) return false;
    if (!nextTokenIs(b, WEEKDAY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = time_exact_day(b, l + 1);
    r = r && consumeToken(b, HYPHEN);
    r = r && time_exact_day(b, l + 1);
    exit_section_(b, m, TIME_RANGE_DAY, r);
    return r;
  }

  /* ********************************************************** */
  // time_exact_month HYPHEN time_exact_month
  public static boolean time_range_month(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "time_range_month")) return false;
    if (!nextTokenIs(b, MONTH)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = time_exact_month(b, l + 1);
    r = r && consumeToken(b, HYPHEN);
    r = r && time_exact_month(b, l + 1);
    exit_section_(b, m, TIME_RANGE_MONTH, r);
    return r;
  }

  /* ********************************************************** */
  // (time_range | time_exact_number | time_any) SLASH time_exact_number
  public static boolean time_range_step(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "time_range_step")) return false;
    if (!nextTokenIs(b, "<time range step>", NUMBER, STAR)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, TIME_RANGE_STEP, "<time range step>");
    r = time_range_step_0(b, l + 1);
    r = r && consumeToken(b, SLASH);
    p = r; // pin = 2
    r = r && time_exact_number(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // time_range | time_exact_number | time_any
  private static boolean time_range_step_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "time_range_step_0")) return false;
    boolean r;
    r = time_range(b, l + 1);
    if (!r) r = time_exact_number(b, l + 1);
    if (!r) r = time_any(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // AT CONTENT
  public static boolean time_shortcut(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "time_shortcut")) return false;
    if (!nextTokenIs(b, AT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, AT, CONTENT);
    exit_section_(b, m, TIME_SHORTCUT, r);
    return r;
  }

  /* ********************************************************** */
  // variable_name EQUAL_SIGN variable_value
  public static boolean variable_definition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_definition")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, VARIABLE_DEFINITION, null);
    r = variable_name(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, consumeToken(b, EQUAL_SIGN));
    r = p && variable_value(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean variable_name(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_name")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, VARIABLE_NAME, r);
    return r;
  }

  /* ********************************************************** */
  // CONTENT
  public static boolean variable_value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_value")) return false;
    if (!nextTokenIs(b, CONTENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CONTENT);
    exit_section_(b, m, VARIABLE_VALUE, r);
    return r;
  }

  /* ********************************************************** */
  // time_pointer
  public static boolean week_pattern(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "week_pattern")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, WEEK_PATTERN, "<week pattern>");
    r = time_pointer(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

}
