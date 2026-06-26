package com.automotive.minidsl.xbase;

import com.automotive.minidsl.ast.Rule;

public class CustomXbaseCompiler {
    private final CustomJvmModelInferrer inferrer;

    public CustomXbaseCompiler(CustomJvmModelInferrer inferrer) {
        this.inferrer = inferrer;
    }

    public String compileExecuteMethodBody(Rule rule) {
        return inferrer.inferExecuteMethodBody(rule);
    }
}

