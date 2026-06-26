package com.automotive.minidsl.codegen;

import com.automotive.minidsl.ast.Rule;
import com.automotive.minidsl.xbase.CustomJvmModelInferrer;
import com.automotive.minidsl.xbase.CustomXbaseCompiler;
import com.automotive.minidsl.xbase.CustomXbaseTypeComputer;

public class JavaCodeGenerator {
    public static final String GENERATED_PACKAGE = "com.automotive.minidsl.generated";
    public static final String GENERATED_CLASS = "MyMiniDSL";
    private final CustomXbaseCompiler compiler =
            new CustomXbaseCompiler(new CustomJvmModelInferrer(new CustomXbaseTypeComputer()));

    public String generate(Rule rule) {
        StringBuilder out = new StringBuilder();
        out.append("package ").append(GENERATED_PACKAGE).append(";\n\n");
        out.append("import com.automotive.minidsl.model.Person;\n");
        out.append("\n");
        out.append("public class ").append(GENERATED_CLASS).append(" {\n");
        out.append("    public void execute(Person person) {\n");
        String methodBody = compiler.compileExecuteMethodBody(rule)
                .replace("\n", "\n        ");
        out.append("        ").append(methodBody).append("\n");
        out.append("\n");
        out.append("    }\n");
        out.append("}\n");
        return out.toString();
    }
}

