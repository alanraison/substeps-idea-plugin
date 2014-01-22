/*
 * Substeps lexer
 */
package com.technophobia.substeps.idea;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
%%
%class _SubstepsLexer
%implements FlexLexer
%unicode
%line
%column
%function advance
%type IElementType
%eof{ return;
%eof}

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\f]
substeps ::= definition*
definition ::= "Define:" WHITESPACE? ( )

%state WAITING_VALUE

%%
<YY_INITIAL> {
"Define:" { return DEFINE; }
}
<WAITING_VALUE> {
}