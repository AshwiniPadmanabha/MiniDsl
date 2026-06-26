package com.automotive.minidsl.parser;

import com.automotive.minidsl.ast.Condition;
import com.automotive.minidsl.ast.Field;
import com.automotive.minidsl.ast.Operator;
import com.automotive.minidsl.ast.RepeatAction;
import com.automotive.minidsl.ast.Rule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MiniDslParser {
    private static final Pattern RULE_PATTERN = Pattern.compile(
            "^\\s*if\\s+(name|gender|age)\\s*(==|!=|>=|<=|>|<)\\s*(\"(?:[^\"]|\\\\\")*\"|\\d+)\\s+then\\s+repeat\\s+name\\s+(\\d+)\\s+times(?:\\s+otherwise\\s+repeat\\s+name\\s+(\\d+)\\s+times)?\\s*\\.\\s*$",
            Pattern.CASE_INSENSITIVE
    );

    public Rule parse(String input) {
        Matcher matcher = RULE_PATTERN.matcher(input);
        if (!matcher.matches()) {
            throw new ParseException("Invalid MiniDSL syntax. Expected: if <field> <operator> <value> then repeat name <n> times [otherwise repeat name <m> times].");
        }

        Field field = Field.fromToken(matcher.group(1).toLowerCase());
        Operator operator = Operator.fromToken(matcher.group(2));
        String rawValue = matcher.group(3);

        int thenTimes = parsePositiveInt(matcher.group(4), "then");
        RepeatAction thenAction = new RepeatAction(thenTimes);

        String elseTimesRaw = matcher.group(5);
        RepeatAction elseAction = elseTimesRaw == null ? null : new RepeatAction(parsePositiveInt(elseTimesRaw, "otherwise"));

        return new Rule(new Condition(field, operator, rawValue), thenAction, elseAction);
    }

    private int parsePositiveInt(String raw, String branch) {
        try {
            int value = Integer.parseInt(raw);
            if (value < 0) {
                throw new ParseException("repeat count in " + branch + " branch must be >= 0");
            }
            return value;
        } catch (NumberFormatException ex) {
            throw new ParseException("Invalid repeat count: " + raw);
        }
    }
}

