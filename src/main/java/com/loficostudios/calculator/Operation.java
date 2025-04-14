package com.loficostudios.calculator;

public enum Operation {
    EQUAL('='),
    ADD('+'),
    SUBTRACT('-'),
    MULTIPLY('*'),
    DIVIDE('/');

    private final char symbol;

    Operation(char symbol) {
        this.symbol = symbol;
    }

    public char symbol() {
        return symbol;
    }
}