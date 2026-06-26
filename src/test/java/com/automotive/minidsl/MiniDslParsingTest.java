package com.automotive.minidsl;

import com.automotive.minidsl.ast.Field;
import com.automotive.minidsl.ast.Operator;
import com.automotive.minidsl.ast.Rule;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MiniDslParsingTest {
    private final MiniDslEngine engine = new MiniDslEngine();

    @Test
    void parsesRuleWithOtherwiseBranch() {
        String dsl = "if age > 18 then repeat name 4 times otherwise repeat name 2 times.";

        Rule rule = engine.parse(dsl);

        assertThat(rule.condition().field()).isEqualTo(Field.AGE);
        assertThat(rule.condition().operator()).isEqualTo(Operator.GT);
        assertThat(rule.condition().rawValue()).isEqualTo("18");
        assertThat(rule.thenAction().times()).isEqualTo(4);
        assertThat(rule.elseAction()).isNotNull();
        assertThat(rule.elseAction().times()).isEqualTo(2);
    }

    @Test
    void parsesRuleWithoutOtherwiseBranch() {
        String dsl = "if age >= 60 then repeat name 5 times.";

        Rule rule = engine.parse(dsl);

        assertThat(rule.condition().operator()).isEqualTo(Operator.GTE);
        assertThat(rule.thenAction().times()).isEqualTo(5);
        assertThat(rule.elseAction()).isNull();
    }
}

