package com.stylegenerator.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Main plugin class.
 */
class StyleGeneratorPlugin : Plugin<Project> {

    override fun apply(project: Project) {

        try {
            project.extensions.create("styleGenerator", StyleGeneratorPluginExtension::class.java)
            project.tasks.create("generateStyle", StyleGeneratorPluginTask::class.java)

        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}
