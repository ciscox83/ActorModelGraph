package cs.actormodel.java;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import cs.actormodel.scala.ActorModelGenerator;

public class ActorModelGeneratorJavaTest {

    private final String OUTPUT_PATH = "target/java_model.txt";
    private final String INPUT_PACKAGE = "cs.actormodel.java";

    private final String expected_output = getExpectedOutput();

    @Test
    public void simpleTest() throws IOException {
        ActorModelGenerator.generate(INPUT_PACKAGE, OUTPUT_PATH, getContextClassLoader());
        String actual_output = new String(Files.readAllBytes(Paths.get(OUTPUT_PATH)));
        assertEquals(expected_output, actual_output);
    }

    private URLClassLoader getContextClassLoader() {
        return (URLClassLoader) (Thread.currentThread().getContextClassLoader());
    }

    private String getExpectedOutput() {
        StringBuilder sb = new StringBuilder();
        sb.append("@startuml\n");
        sb.append("[Fuu] --> [FuuChild1]\n");
        sb.append("[Fuu] --> [FuuChild2]\n");
        sb.append("[FuuParent] --> [Fuu]\n");
        sb.append("@enduml");
        return sb.toString();
    }
}
