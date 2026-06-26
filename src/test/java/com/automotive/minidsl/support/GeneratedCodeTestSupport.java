package com.automotive.minidsl.support;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public final class GeneratedCodeTestSupport {
    private GeneratedCodeTestSupport() {
    }

    public static Class<?> compileGeneratedClass(String source, String fqcn) throws Exception {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            throw new IllegalStateException("No Java compiler available. Run tests with a JDK.");
        }

        Path root = Files.createTempDirectory("minidsl-generated");
        Path sourceFile = root.resolve(fqcn.replace('.', '/') + ".java");
        Files.createDirectories(sourceFile.getParent());
        Files.write(sourceFile, source.getBytes(StandardCharsets.UTF_8));

        try (StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, StandardCharsets.UTF_8)) {
            fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(root.toFile()));
            Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(sourceFile.toFile());
            String classpath = System.getProperty("java.class.path");
            boolean success = compiler.getTask(null, fileManager, null, Arrays.asList("-classpath", classpath), null, compilationUnits).call();
            if (!success) {
                throw new AssertionError("Generated Java failed to compile");
            }
        }

        URLClassLoader loader = new URLClassLoader(new URL[]{root.toUri().toURL()}, Thread.currentThread().getContextClassLoader());
        return Class.forName(fqcn, true, loader);
    }

    public static String runExecute(Class<?> generatedClass, Object person) throws Exception {
        Object instance = generatedClass.getDeclaredConstructor().newInstance();
        Method execute = generatedClass.getMethod("execute", person.getClass());

        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try (PrintStream capture = new PrintStream(output, true, StandardCharsets.UTF_8.name())) {
            System.setOut(capture);
            execute.invoke(instance, person);
        } finally {
            System.setOut(originalOut);
        }
        return output.toString(StandardCharsets.UTF_8.name());
    }
}

