package com.automotive.minidsl;

import com.automotive.minidsl.ast.Rule;
import com.automotive.minidsl.codegen.JavaCodeGenerator;
import com.automotive.minidsl.parser.MiniDslParser;
import com.automotive.minidsl.validation.MiniDslValidator;

public class MiniDslEngine {
    private final MiniDslParser parser = new MiniDslParser();
    private final MiniDslValidator validator = new MiniDslValidator();
    private final JavaCodeGenerator codeGenerator = new JavaCodeGenerator();

    public Rule parse(String dsl) {
        Rule rule = parser.parse(dsl);
        validator.validate(rule);
        return rule;
    }

    public String generateJava(String dsl) {
        Rule rule = parse(dsl);
        return codeGenerator.generate(rule);
    }
}

