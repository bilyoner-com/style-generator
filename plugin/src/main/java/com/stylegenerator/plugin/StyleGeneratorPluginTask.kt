package com.stylegenerator.plugin

import com.stylegenerator.plugin.util.ResourceReader
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Plugin task class.
 */
open class StyleGeneratorPluginTask : DefaultTask() {

    @TaskAction
    fun createFiles() {

        val extension = project.extensions
                .findByType(StyleGeneratorPluginExtension::class.java)
                ?: StyleGeneratorPluginExtension.DEFAULT

        val projectPath = project.projectDir.path

        try {
            StyleGenerator(
                    projectPath = projectPath,
                    fontNames = ResourceReader.getFontNames(projectPath),
                    colorNames = ResourceReader.getColorNames(projectPath),
                    parentStyle = extension.parentStyle,
                    generateUnusedStyles = extension.generateUnusedStyles,
                    minTextSize = extension.minTextSize,
                    maxTextSize = extension.maxTextSize
            ).generateResources()
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}
