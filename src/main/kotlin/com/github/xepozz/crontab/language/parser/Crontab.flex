package com.github.xepozz.crontab.language.parser;

import com.github.xepozz.crontab.language.psi.CrontabTypes;
import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;

%%
%class CrontabLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

SINGLE_COMMENT=#[^\n]*

WHITESPACE=[ \t]+
NEWLINE=\r|\n|\r\n
TEXT=[^ \*\,\d\n][^\n]*
STAR="*"
COMMA=","
NUMBER=[0-9]
SLASH=\/

%state EXPRESSION
%%
<YYINITIAL> {
    {SINGLE_COMMENT}       { return CrontabTypes.COMMENT; }
    {STAR}                 { return CrontabTypes.STAR; }
    {SLASH}                { return CrontabTypes.SLASH; }
    {COMMA}                { return CrontabTypes.COMMA; }
    {NUMBER}+              { return CrontabTypes.NUMBER; }
    {WHITESPACE}+          { return TokenType.WHITE_SPACE; }
    {TEXT}                 { return CrontabTypes.CONTENT; }
}

{WHITESPACE}+          { return TokenType.WHITE_SPACE; }
{NEWLINE}              { yybegin(YYINITIAL); return CrontabTypes.NEWLINE; }

[^]                              { throw new Error("Illegal character <"+yytext()+">"); }