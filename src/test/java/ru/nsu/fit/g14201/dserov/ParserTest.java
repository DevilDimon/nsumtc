package ru.nsu.fit.g14201.dserov;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.nsu.fit.g14201.dserov.lexer.Lexer;
import ru.nsu.fit.g14201.dserov.lexer.LexerException;
import ru.nsu.fit.g14201.dserov.parser.ParserException;
import ru.nsu.fit.g14201.dserov.parser.Parser;

import java.io.StringReader;

/**
 * Created by dserov on 12/02/2017.
 */
public class ParserTest {
    @Test(expectedExceptions = ParserException.class)
    public void testEmpty() throws LexerException, ParserException {
        String test = "";
        Lexer lexer = new Lexer(new StringReader(test));
        Parser parser = new Parser(lexer);
        parser.calculate();
    }

    @Test
    public void testSingleNumber() throws LexerException, ParserException {
        String test = "777";
        Lexer lexer = new Lexer(new StringReader(test));
        Parser parser = new Parser(lexer);
        Assert.assertEquals(parser.calculate(), 777);
    }

    @Test
    public void testNumbers() throws LexerException, ParserException {
        String test = "2*9 + 9^2 + 6/(-1*2) + 7 + 2*45 + 1^100 + ((3))";
        Lexer lexer = new Lexer(new StringReader(test));
        Parser parser = new Parser(lexer);
        Assert.assertEquals(parser.calculate(), 197);
    }

    @Test(expectedExceptions = ParserException.class)
    public void testBadLeftBracket() throws LexerException, ParserException {
        String test = "((3)";
        Lexer lexer = new Lexer(new StringReader(test));
        Parser parser = new Parser(lexer);
        parser.calculate();
    }

    @Test(expectedExceptions = ParserException.class)
    public void testBadRightBracket() throws LexerException, ParserException {
        String test = "(3))";
        Lexer lexer = new Lexer(new StringReader(test));
        Parser parser = new Parser(lexer);
        parser.calculate();
    }

    @Test(expectedExceptions = LexerException.class)
    public void testUnexpectedSymbol() throws LexerException, ParserException {
        String test = "11124 + £ + 7";
        Lexer lexer = new Lexer(new StringReader(test));
        Parser parser = new Parser(lexer);
        parser.calculate();
    }

    @Test(expectedExceptions = ParserException.class)
    public void testInvalidExpression() throws LexerException, ParserException {
        String test = "2 0 +++ 19 / 2";
        Lexer lexer = new Lexer(new StringReader(test));
        Parser parser = new Parser(lexer);
        parser.calculate();
    }
}
