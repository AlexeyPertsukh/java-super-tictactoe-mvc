package com.company.model;

public enum Figure {
    EMPTY(' '),
    X('X'),
    ZERO('0');

    private final char symbol;

    Figure(char symbol) {
        this.symbol = symbol;
    }

    public boolean isEmpty() {
        return this == EMPTY;
    }

    public char getSymbol() {
        return symbol;
    }
}
