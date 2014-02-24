package com.technophobia.substeps.idea.parsing;
import com.intellij.lexer.*;
import com.intellij.psi.tree.IElementType;

%%

%{
  private int previousState;

  public _SubstepsLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _SubstepsLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode
%eof{ return;
%eof}

EOL="\r"|"\n"|"\r\n"
LINE_WS=[\ \t\f]
WHITE_SPACE=({LINE_WS}|{EOL})+

DEFINE="Define:"
VAR_START="<"
VAR_END=">"
END_OF_LINE_COMMENT="#"[^\r\n]*
TEXT=[^<>\ \t\f\r\n][^<>\n\r]*

%state COMMENT
%state DEF_PART, STEP
%xstate VAR

%%
<YYINITIAL, STEP> {
  {END_OF_LINE_COMMENT} { return SubstepsTokenTypes.END_OF_LINE_COMMENT; }
  {WHITE_SPACE}         { return SubstepsTokenTypes.WHITE_SPACE; }
  {DEFINE}              { yybegin(DEF_PART); return SubstepsTokenTypes.DEF_PART; }
}

<DEF_PART> {
  {TEXT}                { return SubstepsTokenTypes.DEF_PART; }
  {EOL}                 { yybegin(STEP); }
  {END_OF_LINE_COMMENT} { yybegin(STEP); return SubstepsTokenTypes.END_OF_LINE_COMMENT; }
}

<VAR> {
  {TEXT}                {  }
  {VAR_END}             { yybegin(previousState); }
}

<DEF_PART,STEP> {
  {VAR_START}           { previousState = yystate(); yybegin(VAR); return SubstepsTokenTypes.VARIABLE; }
}

<STEP> {
  {TEXT}             { }
}

  [^] { return SubstepsTokenTypes.BAD_CHARACTER; }
