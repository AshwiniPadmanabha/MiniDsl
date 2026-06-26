package com.automotive.minidsl;

import com.automotive.minidsl.codegen.JavaCodeGenerator;
import com.automotive.minidsl.support.GeneratedCodeTestSupport;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MiniDslCompilationTest {
    private final MiniDslEngine engine = new MiniDslEngine();

    @Test
    void generatedJavaCompiles() throws Exception {
        String dsl = "if gender == \"Male\" then repeat name 3 times otherwise repeat name 1 times.";

        String source = engine.generateJava(dsl);
        Class<?> generatedClass = GeneratedCodeTestSupport.compileGeneratedClass(
                source,
                JavaCodeGenerator.GENERATED_PACKAGE + "." + JavaCodeGenerator.GENERATED_CLASS
        );

        assertThat(generatedClass.getSimpleName()).isEqualTo(JavaCodeGenerator.GENERATED_CLASS);
    }
}

