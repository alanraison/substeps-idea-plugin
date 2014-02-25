package com.technophobia.substeps.idea.parsing;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import com.technophobia.substeps.idea.psi.SubstepsFile;
import org.jetbrains.annotations.NotNull;

/**
 * Created by alan on 24/02/14.
 */
public class SubstepsParserDefinition implements ParserDefinition {
    private static final Logger LOG = Logger.getInstance("#com.technophobia.substeps.idea.parsing.SubstepsParserDefinition");

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new SubstepsLexer();
    }

    @Override
    public IFileElementType getFileNodeType() {
        return SubstepsFileElementTypes.FILE;
    }

    @Override
    public PsiParser createParser(Project project) {
        return new SubstepsParser();
    }

    @NotNull
    @Override
    public TokenSet getWhitespaceTokens() {
        return SubstepsTokenTypes.WHITESPACES;
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return SubstepsTokenTypes.COMMENTS;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        return SubstepsElementTypes.Factory.createElement(node);
    }

    @Override
    public PsiFile createFile(FileViewProvider viewProvider) {
        return new SubstepsFile(viewProvider);
    }

    @Override
    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
        // TODO: confirm
        return SpaceRequirements.MAY;
    }
}
