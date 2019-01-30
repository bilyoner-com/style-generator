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

        val outputDirectory = project.file("src/main/res/values")

        try {
            StyleGenerator(
                    outputDirectory,
                    ResourceReader.getFontNames(),
                    ResourceReader.getColorNames(),
                    extension!!.parentStyle,
                    extension.minTextSize,
                    extension.maxTextSize
            ).generateResources()
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}
