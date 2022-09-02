package com.company.model;

public enum Figure {
    NULL(' '),
    X('X'),
    ZERO('0'),
    SHARP('#');

    private final char symbol;

    Figure(char symbol) {
        this.symbol = symbol;
    }

    public boolean isNull() {
        return this == NULL;
    }

    public char getChar() {
        return symbol;
    }
}
