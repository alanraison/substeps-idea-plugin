package com.technophobia.substeps.idea.parsing;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by alan on 06/03/14.
 */
public class LexerTest {
    SubstepsLexer lexer;

    @Before
    public void setUp() {
        lexer = new SubstepsLexer();
    }

    @Test
    public void shouldAllowDefine() {
        lexer.start("Define:");
        assertThat(lexer.getTokenType(), equalTo(SubstepsTokenTypes.DEFINE));
    }

    @Test
    public void shouldAllowStartWithComment() {
        lexer.start("#comment");
        assertThat(lexer.getTokenType(), equalTo(SubstepsTokenTypes.END_OF_LINE_COMMENT));
    }

    @Test
    public void shouldNotAllowStartWithJunk() {
        lexer.start("foo");
        assertThat(lexer.getTokenType(), equalTo(SubstepsTokenTypes.BAD_CHARACTER));
    }

    @Test
    public void shouldAllowWhitespaceBeforeDefine() {
        lexer.start("   Define:");
        assertThat(lexer.getTokenType(), equalTo(SubstepsTokenTypes.WHITE_SPACE));
        lexer.advance();
        assertThat(lexer.getTokenType(), equalTo(SubstepsTokenTypes.DEFINE));
    }

    @Test
    public void shouldReadDefineAndStepDef() {
        lexer.start("Define: Given I have a valid substep");
        lexer.advance();
        lexer.advance(); //whitespace
        assertThat(lexer.getTokenText(), equalTo("Given I have a valid substep"));
        assertThat(lexer.getTokenType(), equalTo(SubstepsElementTypes.TEXT_LITERAL));
    }

    @Test
    public void shouldReadSingleStepSubstepDef() {
        lexer.start("Define: Given I have a valid substep\n" +
                "   Given I have set up the test substep");
        lexer.advance(); //Define:
        lexer.advance(); //whitespace
        lexer.advance(); //step def
        lexer.advance(); //whitespace
        assertThat(lexer.getTokenText(), equalTo("Given I have set up the test substep"));
        assertThat(lexer.getTokenType(), equalTo(SubstepsElementTypes.TEXT_LITERAL));
    }

    @Test
    public void shouldReadVariablesInStepDefs() {
        lexer.start("Define: Given I have <variable> set\n" +
                "   Set the variable <variable>");
        lexer.advance();//Define
        lexer.advance();//whitespace
        lexer.advance();//step def
        assertThat(lexer.getTokenType(), equalTo(SubstepsTokenTypes.VARIABLE));
        lexer.advance();
        assertThat(lexer.getTokenType(), equalTo(SubstepsElementTypes.TEXT_LITERAL));
        assertThat(lexer.getTokenText(), equalTo("variable"));
        lexer.advance();//whitespace
        lexer.advance();//set
        lexer.advance();//whitespace
        lexer.advance();//step_part
        lexer.advance();//whitespace
        assertThat(lexer.getTokenType(), equalTo(SubstepsTokenTypes.VARIABLE));
        lexer.advance();
        assertThat(lexer.getTokenType(), equalTo(SubstepsElementTypes.TEXT_LITERAL));
        assertThat(lexer.getTokenText(), equalTo("variable"));
    }
}
