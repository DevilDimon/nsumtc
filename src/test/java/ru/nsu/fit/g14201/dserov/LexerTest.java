package ru.nsu.fit.g14201.dserov;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.nsu.fit.g14201.dserov.lexer.Lexeme;
import ru.nsu.fit.g14201.dserov.lexer.LexemeType;
import ru.nsu.fit.g14201.dserov.lexer.Lexer;
import ru.nsu.fit.g14201.dserov.lexer.LexerException;

import java.io.StringReader;
import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class LexerTest {
    @Test
    public void testEmpty() throws LexerException {
        String test = "";
        Lexer lexer = new Lexer(new StringReader(test));
        Assert.assertEquals(lexer.getLexeme(), new Lexeme(LexemeType.EOF));
    }

    @Test
    public void testSingleNumber() throws LexerException {
        String test = "1234";
        Lexer lexer = new Lexer(new StringReader(test));
        Assert.assertEquals(lexer.getLexeme(), new Lexeme(LexemeType.NUMBER, "1234"));
        Assert.assertEquals(lexer.getLexeme(), new Lexeme(LexemeType.EOF));
    }

    @Test
    public void testTerminals() throws LexerException {
        String test = "1+456-58999*33/92^(4*228)";
        Lexer lexer = new Lexer(new StringReader(test));
        ArrayList<Lexeme> refList = new ArrayList<>();
        refList.add(new Lexeme(LexemeType.NUMBER, "1"));
        refList.add(new Lexeme(LexemeType.PLUS));
        refList.add(new Lexeme(LexemeType.NUMBER, "456"));
        refList.add(new Lexeme(LexemeType.MINUS));
        refList.add(new Lexeme(LexemeType.NUMBER, "58999"));
        refList.add(new Lexeme(LexemeType.MULTIPLY));
        refList.add(new Lexeme(LexemeType.NUMBER, "33"));
        refList.add(new Lexeme(LexemeType.DIVIDE));
        refList.add(new Lexeme(LexemeType.NUMBER, "92"));
        refList.add(new Lexeme(LexemeType.POWER));
        refList.add(new Lexeme(LexemeType.LEFT_BRACKET));
        refList.add(new Lexeme(LexemeType.NUMBER, "4"));
        refList.add(new Lexeme(LexemeType.MULTIPLY));
        refList.add(new Lexeme(LexemeType.NUMBER, "228"));
        refList.add(new Lexeme(LexemeType.RIGHT_BRACKET));
        refList.add(new Lexeme(LexemeType.EOF));

        ArrayList<Lexeme> actList = new ArrayList<>();

        for (int i = 0; i < 16; i++) {
            actList.add(lexer.getLexeme());
        }

        Assert.assertEquals(actList, refList);
    }

    @Test
    public void testWhitespaces() throws LexerException {
        String test = "    7   +  \n  89^   12 \t\t\t   -9";
        Lexer lexer = new Lexer(new StringReader(test));
        ArrayList<Lexeme> refList = new ArrayList<>();
        refList.add(new Lexeme(LexemeType.NUMBER, "7"));
        refList.add(new Lexeme(LexemeType.PLUS));
        refList.add(new Lexeme(LexemeType.NUMBER, "89"));
        refList.add(new Lexeme(LexemeType.POWER));
        refList.add(new Lexeme(LexemeType.NUMBER, "12"));
        refList.add(new Lexeme(LexemeType.MINUS));
        refList.add(new Lexeme(LexemeType.NUMBER, "9"));

        ArrayList<Lexeme> actList = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            actList.add(lexer.getLexeme());
        }

        Assert.assertEquals(actList, refList);
    }
}
