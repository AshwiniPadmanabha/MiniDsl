package com.automotive.minidsl;

import com.automotive.minidsl.codegen.JavaCodeGenerator;
import com.automotive.minidsl.model.Person;
import com.automotive.minidsl.support.GeneratedCodeTestSupport;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class MiniDslExecutionTest {
    private final MiniDslEngine engine = new MiniDslEngine();

    @Test
    void generatedClassExecutesExpectedThenBranch() throws Exception {
        String dsl = "if age > 18 then repeat name 4 times otherwise repeat name 2 times.";
        String source = engine.generateJava(dsl);

        Class<?> generatedClass = GeneratedCodeTestSupport.compileGeneratedClass(
                source,
                JavaCodeGenerator.GENERATED_PACKAGE + "." + JavaCodeGenerator.GENERATED_CLASS
        );

        String output = GeneratedCodeTestSupport.runExecute(generatedClass, new Person("Rahul", "Male", 25));

        assertThat(nonBlankLines(output))
                .containsExactly("Rahul", "Rahul", "Rahul", "Rahul");
    }

    @Test
    void generatedClassExecutesExpectedElseBranch() throws Exception {
        String dsl = "if gender == \"Male\" then repeat name 3 times otherwise repeat name 1 times.";
        String source = engine.generateJava(dsl);

        Class<?> generatedClass = GeneratedCodeTestSupport.compileGeneratedClass(
                source,
                JavaCodeGenerator.GENERATED_PACKAGE + "." + JavaCodeGenerator.GENERATED_CLASS
        );

        String output = GeneratedCodeTestSupport.runExecute(generatedClass, new Person("Asha", "Female", 25));

        assertThat(nonBlankLines(output))
                .containsExactly("Asha");
    }

    private List<String> nonBlankLines(String output) {
        return Arrays.stream(output.split("\\r?\\n"))
                .filter(line -> !line.trim().isEmpty())
                .collect(Collectors.toList());
    }
}

