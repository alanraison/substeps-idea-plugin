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

END_OF_LINE_COMMENT="#"[^\r\n]*
TEXT=[^<>\n \t][^<>\n]*

%state COMMENT
%state DEF_PART, STEP
%xstate VAR

%%
<YYINITIAL, STEP> {
  {END_OF_LINE_COMMENT} { return SubstepsTokenTypes.END_OF_LINE_COMMENT; }
}
<YYINITIAL> {
  {WHITE_SPACE}      { return SubstepsTokenTypes.WHITE_SPACE; }

  "Define:"          { yybegin(DEF_PART); return SubstepsTokenTypes.DEF_PART; }
}

<DEF_PART> {
  {TEXT}             { return SubstepsTokenTypes.DEF_PART; }
  {EOL}              { yybegin(STEP); }
  {END_OF_LINE_COMMENT} { yybegin(STEP); return SubstepsTokenTypes.END_OF_LINE_COMMENT; }
}

<VAR> {
  {TEXT}             {  }
  ">"                { yybegin(previousState); }
}

<DEF_PART,STEP> {
  "<"                { previousState = yystate(); yybegin(VAR); return SubstepsTokenTypes.VARIABLE; }
}

<STEP> {
  {TEXT}             { }
}

  [^] { return SubstepsTokenTypes.BAD_CHARACTER; }
