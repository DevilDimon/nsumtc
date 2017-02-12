package ru.nsu.fit.g14201.dserov;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.nsu.fit.g14201.dserov.lexer.Lexer;
import ru.nsu.fit.g14201.dserov.lexer.LexerException;
import ru.nsu.fit.g14201.dserov.parser.ParseException;
import ru.nsu.fit.g14201.dserov.parser.Parser;

import java.io.StringReader;

/**
 * Created by dserov on 12/02/2017.
 */
public class ParserTest {
    @Test(expectedExceptions = LexerException.class)
    public void testEmpty() throws LexerException, ParseException {
        String test = "";
        Lexer lexer = new Lexer(new StringReader(test));
        Parser parser = new Parser(lexer);
        parser.calculate();
    }
}
