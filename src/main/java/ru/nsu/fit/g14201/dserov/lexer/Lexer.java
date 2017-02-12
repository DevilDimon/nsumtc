package ru.nsu.fit.g14201.dserov.lexer;

import ru.nsu.fit.g14201.dserov.lexer.Lexeme;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by dserov on 12/02/2017.
 */
public class Lexer {
    private Reader reader;

    public Lexer(Reader reader) {
        this.reader = reader;
    }

    public Lexeme getLexeme() {
        try {
            int ch = -1;
            do {
                ch = reader.read();
            } while (ch == ' ' || ch == '\t' || ch == '\n');

            if (ch == -1) {
                return new Lexeme(LexemeType.EOF, null);
            }

            switch (ch) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    
            }

        } catch (IOException e) {
            return new Lexeme(LexemeType.EOF, null);
        }
    }
}
