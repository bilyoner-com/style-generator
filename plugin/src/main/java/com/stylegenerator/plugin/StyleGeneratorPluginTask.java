package com.stylegenerator.plugin;

import com.stylegenerator.plugin.util.ResourceReader;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.File;

/**
 * Plugin task class.
 */
public class StyleGeneratorPluginTask extends DefaultTask {

    @TaskAction
    public void createFiles() throws Exception {

        final StyleGeneratorPluginExtension extension = getProject().getExtensions()
                .findByType(StyleGeneratorPluginExtension.class);

        final File outputDirectory = getProject().file("src/main/res/values");

        try {
            new StyleGenerator(
                    outputDirectory,
                    ResourceReader.getFontNames(),
                    ResourceReader.getColorNames(),
                    extension.parentStyle,
                    extension.minTextSize,
                    extension.maxTextSize
            ).generateResources();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
