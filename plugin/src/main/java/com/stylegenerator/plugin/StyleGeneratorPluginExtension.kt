package com.stylegenerator.plugin

/**
 * Class to hold plugin extensions.
 */
open class StyleGeneratorPluginExtension {

    companion object {
        val DEFAULT = StyleGeneratorPluginExtension()
    }

    var parentStyle = "TextAppearance.AppCompat"

    var generateUnusedStyles = false

    var minTextSize = 8
    var maxTextSize = 32
}
