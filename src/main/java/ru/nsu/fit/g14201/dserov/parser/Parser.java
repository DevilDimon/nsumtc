package ru.nsu.fit.g14201.dserov.parser;

import ru.nsu.fit.g14201.dserov.lexer.Lexeme;
import ru.nsu.fit.g14201.dserov.lexer.LexemeType;
import ru.nsu.fit.g14201.dserov.lexer.Lexer;
import ru.nsu.fit.g14201.dserov.lexer.LexerException;

/**
 * Created by dserov on 12/02/2017.
 */
public class Parser {
    private Lexer lexer;
    private Lexeme lexeme;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public int calculate() throws LexerException, ParseException {
        lexeme = lexer.getLexeme();
        int result = parseExpression();
        if (!lexeme.equals(new Lexeme(LexemeType.EOF))) {
            throw new ParseException();
        }
        return result;
    }

    private int parseExpression() throws LexerException, ParseException {
        int total = parseTerm();
        while (lexeme.getType() == LexemeType.PLUS || lexeme.getType() == LexemeType.MINUS) {
            int sign = lexeme.getType() == LexemeType.PLUS ? +1 : -1;
            total += sign * parseTerm();
            lexeme = lexer.getLexeme();
        }
        return total;
    }

    private int parseTerm() throws LexerException, ParseException {
        int total = parseFactor();
        while (lexeme.getType() == LexemeType.MULTIPLY || lexeme.getType() == LexemeType.DIVIDE) {
            if (lexeme.getType() == LexemeType.MULTIPLY) {
                total *= parseFactor();
            } else {
                total /= parseFactor();
            }
            lexeme = lexer.getLexeme();
        }
        return total;
    }

    private int parseFactor() throws LexerException, ParseException {
        int total = parsePower();
        if (lexeme.getType() == LexemeType.POWER) {
            return (int) Math.pow(total, parseFactor());
        } else {
            return total;
        }
    }

    private int parsePower() throws LexerException, ParseException {
        return lexeme.getType() == LexemeType.MINUS ? -parseAtom() : parseAtom();
    }

    private int parseAtom() throws LexerException, ParseException {
        if (lexeme.getType() != LexemeType.NUMBER && lexeme.getType() != LexemeType.LEFT_BRACKET) {
            throw new ParseException();
        }
        if (lexeme.getType() == LexemeType.LEFT_BRACKET) {
            int total = parseExpression();
            if (lexeme.getType() != LexemeType.RIGHT_BRACKET) {
                throw new ParseException();
            }
            return total;
        } else {
            return Integer.parseInt(lexeme.getLexeme());
        }
    }
}
