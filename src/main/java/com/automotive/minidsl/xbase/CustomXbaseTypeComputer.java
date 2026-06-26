package com.automotive.minidsl.xbase;

import com.automotive.minidsl.ast.Condition;
import com.automotive.minidsl.ast.Field;
import com.automotive.minidsl.ast.Operator;

public class CustomXbaseTypeComputer {
    public String computeConditionExpression(Condition condition) {
        if (condition.field() == Field.AGE) {
            return "person.getAge() " + condition.operator().token() + " " + condition.rawValue();
        }

        String accessor = condition.field() == Field.NAME ? "person.getName()" : "person.getGender()";
        if (condition.operator() == Operator.EQ) {
            return "java.util.Objects.equals(" + accessor + ", " + condition.rawValue() + ")";
        }
        if (condition.operator() == Operator.NE) {
            return "!java.util.Objects.equals(" + accessor + ", " + condition.rawValue() + ")";
        }

        throw new IllegalArgumentException("Unsupported operator for string comparison: " + condition.operator());
    }
}

