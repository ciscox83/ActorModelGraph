package it.ciscosistem.actormodel.java;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

import it.ciscosistem.actormodel.scala.ActorModelGenerator;

@Mojo(name = "uml",
        requiresDependencyResolution = ResolutionScope.RUNTIME,
        requiresProject = true)
public class ActorModelMojo extends AbstractMojo {
    public static final String DEFAULT_OUTPUT_PATH = "target/actor_model_graph.txt";

    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    private MavenProject project;

    @Parameter(property = "uml.inputPackage", defaultValue = ".")
    private String inputPackage;

    @Parameter(property = "uml.outputPath", defaultValue = DEFAULT_OUTPUT_PATH)
    private String outputPath;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            ActorModelGenerator.generate(inputPackage, outputPath, runtimeClassPath());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (DependencyResolutionRequiredException e) {
            e.printStackTrace();
        }
    }

    private URLClassLoader runtimeClassPath() throws MalformedURLException, DependencyResolutionRequiredException {
        List runtimeClasspathElements = project.getRuntimeClasspathElements();
        URL[] runtimeUrls = new URL[runtimeClasspathElements.size()];
        for (int i = 0; i < runtimeClasspathElements.size(); i++) {
            String element = (String) runtimeClasspathElements.get(i);
            runtimeUrls[i] = new File(element).toURI().toURL();
        }
        return new URLClassLoader(runtimeUrls,
                Thread.currentThread().getContextClassLoader());
    }
}
