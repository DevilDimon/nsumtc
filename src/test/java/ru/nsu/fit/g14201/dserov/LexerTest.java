package ru.nsu.fit.g14201.dserov;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.nsu.fit.g14201.dserov.lexer.Lexeme;
import ru.nsu.fit.g14201.dserov.lexer.LexemeType;
import ru.nsu.fit.g14201.dserov.lexer.Lexer;
import ru.nsu.fit.g14201.dserov.lexer.LexerException;

import java.io.StringReader;

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


}
