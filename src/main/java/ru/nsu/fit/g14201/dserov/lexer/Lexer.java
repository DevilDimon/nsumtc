package ru.nsu.fit.g14201.dserov.lexer;

import ru.nsu.fit.g14201.dserov.lexer.Lexeme;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by dserov on 12/02/2017.
 */
public class Lexer {
    private Reader reader;

    private int buffer;

    public Lexer(Reader reader) {
        this.reader = reader;
        buffer = -2;
    }

    public Lexeme getLexeme() throws LexerException {
        try {
            int ch = buffer;
            buffer = -2;
            if (ch == -2) {
                do {
                    ch = reader.read();
                } while (ch == ' ' || ch == '\t' || ch == '\n');
            }

            if (ch == -1) {
                return new Lexeme(LexemeType.EOF);
            }

            if (Character.isDigit(ch)) {
                StringBuilder resultSb = new StringBuilder((char) ch + "");
                while (true) {
                    int nextCh = reader.read();
                    buffer = nextCh;
                    if (Character.isDigit(ch)) {
                        resultSb.append((char) nextCh);
                    } else {
                        return new Lexeme(LexemeType.NUMBER, resultSb.toString());
                    }
                }
            } else {
                switch (ch) {
                    case '+':
                        return new Lexeme(LexemeType.PLUS);
                    case '-':
                        return new Lexeme(LexemeType.MINUS);
                    case '*':
                        return new Lexeme(LexemeType.MULTIPLY);
                    case '/':
                        return new Lexeme(LexemeType.DIVIDE);
                    case '(':
                        return new Lexeme(LexemeType.LEFT_BRACKET);
                    case ')':
                        return new Lexeme(LexemeType.RIGHT_BRACKET);
                    case '^':
                        return new Lexeme(LexemeType.POWER);
                    default:
                        throw new LexerException();
                }
            }

        } catch (IOException e) {
            return new Lexeme(LexemeType.EOF);
        }
    }
}
