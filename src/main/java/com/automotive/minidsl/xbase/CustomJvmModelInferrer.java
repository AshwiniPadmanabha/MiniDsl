package com.automotive.minidsl.xbase;

import com.automotive.minidsl.ast.RepeatAction;
import com.automotive.minidsl.ast.Rule;

public class CustomJvmModelInferrer {
    private final CustomXbaseTypeComputer typeComputer;

    public CustomJvmModelInferrer(CustomXbaseTypeComputer typeComputer) {
        this.typeComputer = typeComputer;
    }

    public String inferExecuteMethodBody(Rule rule) {
        StringBuilder out = new StringBuilder();
        out.append("if (").append(typeComputer.computeConditionExpression(rule.condition())).append(") {\n");
        appendRepeat(out, rule.thenAction(), 1);
        out.append("}");

        if (rule.elseAction() != null) {
            out.append(" else {\n");
            appendRepeat(out, rule.elseAction(), 1);
            out.append("}");
        }

        return out.toString();
    }

    private void appendRepeat(StringBuilder out, RepeatAction action, int indentLevel) {
        StringBuilder indentBuilder = new StringBuilder();
        for (int i = 0; i < indentLevel; i++) {
            indentBuilder.append("    ");
        }
        String indent = indentBuilder.toString();
        out.append(indent).append("for (int i = 0; i < ").append(action.times()).append("; i++) {\n");
        out.append(indent).append("    System.out.println(person.getName());\n");
        out.append(indent).append("}\n");
    }
}

