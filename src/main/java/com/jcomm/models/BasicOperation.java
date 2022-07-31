package com.jcomm.models;

public enum BasicOperation implements Operation {

    PLUS("+") {
        public double apply(double x, double y) { return x + y;}
    };

    private final String symbol;
    BasicOperation(String symbol) {
        this.symbol = symbol;
    }

    @Override public String toString() {
        return symbol;
    }
}
