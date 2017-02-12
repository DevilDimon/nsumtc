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

    public Lexeme(LexemeType type) {
        this.type = type;
    }

    public LexemeType getType() {
        return type;
    }

    public String getLexeme() {
        return lexeme;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Lexeme) {
            Lexeme anOther = (Lexeme) other;
            if (type != anOther.type) {
                return false;
            }

            if (lexeme == null) {
                return anOther.lexeme == null;
            }

            return lexeme.equals(anOther.lexeme);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return type.hashCode() ^ (lexeme == null ? 0 : lexeme.hashCode());
    }
}
