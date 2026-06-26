package com.automotive.minidsl;

public class App {
    public static void main(String[] args) {
        String dsl = "if age > 18 then repeat name 4 times otherwise repeat name 2 times.";
        MiniDslEngine engine = new MiniDslEngine();
        String generatedJava = engine.generateJava(dsl);
        System.out.println(generatedJava);
    }
}

