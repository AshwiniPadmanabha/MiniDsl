package com.automotive.minidsl.ast;

public enum Field {
    NAME,
    GENDER,
    AGE;

    public static Field fromToken(String token) {
        switch (token) {
            case "name":
                return NAME;
            case "gender":
                return GENDER;
            case "age":
                return AGE;
            default:
                throw new IllegalArgumentException("Unknown field: " + token);
        }
    }
}

