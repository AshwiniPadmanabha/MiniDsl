package com.automotive.minidsl.ast;

public enum Operator {
    GT(">"),
    GTE(">="),
    LT("<"),
    LTE("<="),
    EQ("=="),
    NE("!=");

    private final String token;

    Operator(String token) {
        this.token = token;
    }

    public String token() {
        return token;
    }

    public static Operator fromToken(String token) {
        switch (token) {
            case ">":
                return GT;
            case ">=":
                return GTE;
            case "<":
                return LT;
            case "<=":
                return LTE;
            case "==":
                return EQ;
            case "!=":
                return NE;
            default:
                throw new IllegalArgumentException("Unknown operator: " + token);
        }
    }
}

