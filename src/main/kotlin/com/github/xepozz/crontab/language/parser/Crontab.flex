package com.github.xepozz.crontab.language.parser;

import com.github.xepozz.crontab.language.psi.CrontabTypes;
import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;

%%
%class CrontabLexer
%implements FlexLexer
%unicode
%ignorecase
%function advance
%type IElementType
%eof{  return;
%eof}

SINGLE_COMMENT=#[^\n]*

NUMBER=[0-9]+
WHITESPACE=[ \h\f\t]+
NEWLINE=\r|\n|\r\n
STAR="*"
COMMA=","
AT="@"

SLASH="/"
HYPHEN=\-
EQUAL_SIGN="="
IDENTIFIER=[a-zA-Z][a-zA-Z0-9_-]*
DOUBLE_QUOTED_TEXT = \" (\\\" | [^\n\"])* \"
SINGLE_QUOTED_TEXT = "'" (\\"'" | [^\n'])* "'"
QUOTED_TEXT = {SINGLE_QUOTED_TEXT} | {DOUBLE_QUOTED_TEXT}

DAY_PATTERN = (MON|TUE|WED|THU|FRI|SAT|SUN)
MONTH_PATTERN = (JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)
KEYWORD_YEARLY = "yearly"
KEYWORD_ANNUALLY = "annually"
KEYWORD_MONTHLY = "monthly"
KEYWORD_WEEKLY = "weekly"
KEYWORD_DAILY = "daily"
KEYWORD_HOURLY = "hourly"
KEYWORD_REBOOT = "reboot"

%state COMMAND, SCHEDULE, VARIABLE, SIMPLE_SYNTAX
%%
<YYINITIAL> {
    {SINGLE_COMMENT}                             { return CrontabTypes.SINGLE_COMMENT; }
    {STAR}                                       { yybegin(SCHEDULE); return CrontabTypes.STAR; }
    {NUMBER}                                     { yybegin(SCHEDULE); return CrontabTypes.NUMBER; }
    {IDENTIFIER}                                 { yybegin(VARIABLE); return CrontabTypes.IDENTIFIER; }
    {AT}                                         { yybegin(SIMPLE_SYNTAX); return CrontabTypes.AT; }
}
<VARIABLE> {
    {EQUAL_SIGN}                                 { return CrontabTypes.EQUAL_SIGN; }
    {QUOTED_TEXT}|[^ \s\t\n\=]+                  { return CrontabTypes.CONTENT; }
}
<SCHEDULE> {
    {STAR}                                       { return CrontabTypes.STAR; }
    {NUMBER}                                     { return CrontabTypes.NUMBER; }
    {SLASH}                                      { return CrontabTypes.SLASH; }
    {COMMA}                                      { return CrontabTypes.COMMA; }
    {HYPHEN}                                     { return CrontabTypes.HYPHEN; }
    {DAY_PATTERN}                                { return CrontabTypes.DAY; }
    {MONTH_PATTERN}                              { return CrontabTypes.MONTH; }
    {WHITESPACE}                                 { return TokenType.WHITE_SPACE; }
    ([^]|\/\D)                                   { yypushback(1); yybegin(COMMAND); }
}
<COMMAND> {
    ([^\s][^\n]*)                                { yybegin(YYINITIAL); return CrontabTypes.CONTENT; }
}
<SIMPLE_SYNTAX> {
    [^\s]+                                       { return CrontabTypes.CONTENT; }
    {WHITESPACE}                                 { return TokenType.WHITE_SPACE; }
    ([\s][^])                                    { yypushback(yylength()); yybegin(COMMAND); }
}

{WHITESPACE}                                     { return TokenType.WHITE_SPACE; }
{NEWLINE}                                        { yybegin(YYINITIAL); return CrontabTypes.NEWLINE; }

[^]                                              { return TokenType.BAD_CHARACTER; }
//[^]                                              { throw new Error("Illegal character <"+yytext()+">"); }