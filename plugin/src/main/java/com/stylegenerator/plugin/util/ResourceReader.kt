package com.stylegenerator.plugin.util

import java.util.ArrayList

object ResourceReader {

    private const val FONTS_FOLDER = "src/main/res/font"
    private const val COLORS_FILE = "src/main/res/values/colors.xml"

    private const val EXTENSION_SEPARATOR = "."

    fun getFontNames(path: String): List<String> {
        val fonts = FileUtil.getFile(path, FONTS_FOLDER).list()
        return when {
            fonts.isNullOrEmpty() -> emptyList()
            else -> fonts.map { filename ->
                filename.substring(0, filename.lastIndexOf(EXTENSION_SEPARATOR))
            }
        }
    }

    fun getColorNames(path: String): List<String> {
        val colors = XMLUtil.readFromXML(FileUtil.getFile(path, COLORS_FILE), "color")
        return ArrayList(colors.keys)
    }
}
