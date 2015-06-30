package it.ciscosistem.actormodel.java;

import java.util.ArrayList;
import java.util.List;

import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.testing.stubs.MavenProjectStub;

public class TestMavenProjectStub extends MavenProjectStub {
    @Override
    public List<String> getRuntimeClasspathElements() throws DependencyResolutionRequiredException {
        List<String> runtimeClassPathElements = new ArrayList<>();
        runtimeClassPathElements.add("it.ciscosistem.actormodel.java.model.Fuu");
        runtimeClassPathElements.add("it.ciscosistem.actormodel.java.model.FuuChild1");
        runtimeClassPathElements.add("it.ciscosistem.actormodel.java.model.FuuChild2");
        runtimeClassPathElements.add("it.ciscosistem.actormodel.java.model.FuuParent");
        return runtimeClassPathElements;
    }
}
