package com.technophobia.substeps.idea.parsing;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

/**
 * Created by alan on 23/02/14.
 */
public interface SubstepsTokenTypes {
    //IFileElementType FILE = new IFileElementType("SubstepsFile",)
    IElementType WHITE_SPACE = TokenType.WHITE_SPACE;
    IElementType BAD_CHARACTER = TokenType.BAD_CHARACTER;

    IElementType END_OF_LINE_COMMENT = new SubstepsToken("END_OF_LINE_COMMENT");
    IElementType DEFINE = new SubstepsToken("DEFINE");
    IElementType VARIABLE = new SubstepsToken("VARIABLE");
    IElementType STEP = new SubstepsToken("STEP");

    TokenSet WHITESPACES = TokenSet.create(WHITE_SPACE);
    TokenSet COMMENTS = TokenSet.create(END_OF_LINE_COMMENT);
    TokenSet STEPS = TokenSet.create(STEP);
}
