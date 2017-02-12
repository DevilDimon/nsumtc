package ru.nsu.fit.g14201.dserov.lexer;

/**
 * Created by dserov on 12/02/2017.
 */
public class Lexeme {
    private LexemeType type;
    private String lexeme;

    public Lexeme(LexemeType type, String lexeme) {
        this.type = type;
        this.lexeme = lexeme;
    }

    public LexemeType getType() {
        return type;
    }

    public String getLexeme() {
        return lexeme;
    }
}
