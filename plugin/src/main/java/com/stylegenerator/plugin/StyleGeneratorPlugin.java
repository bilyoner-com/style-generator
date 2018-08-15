package com.stylegenerator.plugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * Main plugin class.
 */
public class StyleGeneratorPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        try {
            project.getExtensions().create("styleGenerator", StyleGeneratorPluginExtension.class);
            project.getTasks().create("generateStyle", StyleGeneratorPluginTask.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
