package it.ciscosistem.actormodel;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.junit.Test;

public class ActorModelUmlMojoTest extends AbstractMojoTestCase {

    public static final String TEST_POM_PATH = "src/test/resources/test-pom.xml";
    public static final String GOAL = "uml";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Files.deleteIfExists(Paths.get(ActorModelUmlMojo.DEFAULT_OUTPUT_PATH));
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        Files.deleteIfExists(Paths.get(ActorModelUmlMojo.DEFAULT_OUTPUT_PATH));
    }

    @Test
    public void testMojo() throws Exception {
        assertFalse(Files.exists(Paths.get(ActorModelUmlMojo.DEFAULT_OUTPUT_PATH)));
        ActorModelUmlMojo mojo = (ActorModelUmlMojo) lookupMojo(GOAL, TEST_POM_PATH);
        mojo.execute();
        assertTrue(Files.exists(Paths.get(ActorModelUmlMojo.DEFAULT_OUTPUT_PATH)));
    }

}
