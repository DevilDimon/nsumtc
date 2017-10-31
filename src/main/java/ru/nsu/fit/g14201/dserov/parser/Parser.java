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

    public int calculate() throws LexerException, ParserException {
        lexeme = lexer.getLexeme();
        int result = parseExpression();
        if (!lexeme.equals(new Lexeme(LexemeType.EOF))) {
            throw new ParserException();
        }
        return result;
    }

    private int parseExpression() throws LexerException, ParserException {
        int total = parseTerm();
        while (lexeme.getType() == LexemeType.PLUS || lexeme.getType() == LexemeType.MINUS) {
            int sign = lexeme.getType() == LexemeType.PLUS ? +1 : -1;
            lexeme = lexer.getLexeme();
            total += sign * parseTerm();
        }
        return total;
    }

    private int parseTerm() throws LexerException, ParserException {
        int total = parseFactor();
        while (lexeme.getType() == LexemeType.MULTIPLY || lexeme.getType() == LexemeType.DIVIDE) {
            if (lexeme.getType() == LexemeType.MULTIPLY) {
                lexeme = lexer.getLexeme();
                total *= parseFactor();
            } else {
                lexeme = lexer.getLexeme();
                total /= parseFactor();
            }
        }
        return total;
    }

    private int parseFactor() throws LexerException, ParserException {
        int total = parsePower();
        if (lexeme.getType() == LexemeType.POWER) {
            lexeme = lexer.getLexeme();
            return (int) Math.pow(total, parseFactor());
        } else {
            return total;
        }
    }

    private int parsePower() throws LexerException, ParserException {
        boolean isMinus = lexeme.getType() == LexemeType.MINUS;
        if (isMinus) {
            lexeme = lexer.getLexeme();
        }
        return isMinus ? -parseAtom() : parseAtom();
    }

    private int parseAtom() throws LexerException, ParserException {
        if (lexeme.getType() != LexemeType.NUMBER && lexeme.getType() != LexemeType.LEFT_BRACKET) {
            throw new ParserException();
        }
        if (lexeme.getType() == LexemeType.LEFT_BRACKET) {
            lexeme = lexer.getLexeme();
            int total = parseExpression();
            if (lexeme.getType() != LexemeType.RIGHT_BRACKET) {
                throw new ParserException();
            }
            lexeme = lexer.getLexeme();
            return total;
        } else {
            int result =  Integer.parseInt(lexeme.getLexeme());
            lexeme = lexer.getLexeme();
            return result;
        }
    }
}
