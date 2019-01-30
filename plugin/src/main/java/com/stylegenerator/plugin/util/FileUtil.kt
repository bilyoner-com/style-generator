package com.stylegenerator.plugin.util

import java.io.File

object FileUtil {

    private val SEPARATOR = File.separator

    fun getFile(vararg paths: String): File {
        return File(getPath(*paths))
    }

    fun getPath(vararg paths: String): String {
        return paths.joinToString(SEPARATOR)
    }
}
