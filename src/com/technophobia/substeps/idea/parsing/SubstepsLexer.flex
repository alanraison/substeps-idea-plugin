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
COMMENT_START="#"
END_OF_LINE_COMMENT={COMMENT_START}[^\r\n]*
TEXT=[^<>\ \t\f\r\n][^<>\n\r]*

%state COMMENT
%state DEFINITION, STEP
%xstate VAR

%%
<YYINITIAL, STEP> {
  {END_OF_LINE_COMMENT} { return SubstepsTokenTypes.END_OF_LINE_COMMENT; }
  {WHITE_SPACE}         { return SubstepsTokenTypes.WHITE_SPACE; }
  {DEFINE}              { yybegin(DEFINITION); return SubstepsTokenTypes.DEFINE; }
}

<DEFINITION> {
  {TEXT}                { return SubstepsElementTypes.TEXT; }
  {EOL}                 { yybegin(STEP); }
  {END_OF_LINE_COMMENT} { yybegin(STEP); return SubstepsTokenTypes.END_OF_LINE_COMMENT; }
}

<VAR> {
  {TEXT}                { return SubstepsElementTypes.TEXT; }
  {VAR_END}             { yybegin(previousState); }
}

<DEFINITION,STEP> {
  {VAR_START}           { previousState = yystate(); yybegin(VAR); return SubstepsTokenTypes.VARIABLE; }
}

<STEP> {
  {TEXT}             { return SubstepsElementTypes.TEXT; }
}

  [^] { return SubstepsTokenTypes.BAD_CHARACTER; }
