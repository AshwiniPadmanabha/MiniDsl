package com.automotive.minidsl.validation;

import com.automotive.minidsl.ast.Condition;
import com.automotive.minidsl.ast.Field;
import com.automotive.minidsl.ast.Operator;
import com.automotive.minidsl.ast.Rule;

public class MiniDslValidator {
    public void validate(Rule rule) {
        validateCondition(rule.condition());
    }

    private void validateCondition(Condition condition) {
        Field field = condition.field();
        Operator operator = condition.operator();
        String rawValue = condition.rawValue();

        if (field == Field.AGE) {
            if (isQuotedString(rawValue)) {
                throw new ValidationException("age must be compared with a numeric value");
            }
            return;
        }

        if (!isQuotedString(rawValue)) {
            throw new ValidationException(field.name().toLowerCase() + " must be compared with a quoted string value");
        }

        if (operator != Operator.EQ && operator != Operator.NE) {
            throw new ValidationException("Only == and != are supported for string fields");
        }
    }

    private boolean isQuotedString(String rawValue) {
        return rawValue.length() >= 2 && rawValue.startsWith("\"") && rawValue.endsWith("\"");
    }
}

