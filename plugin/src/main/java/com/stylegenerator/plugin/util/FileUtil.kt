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

    fun contains(fileName: String, vararg texts: String, fileFilter: (File) -> Boolean): Boolean {
        return FileUtil.contains(getFile(fileName), *texts, fileFilter = fileFilter)
    }

    fun contains(file: File, vararg texts: String, fileFilter: (File) -> Boolean): Boolean {
        val subFiles = file.listFiles()
        subFiles?.let {

            for (subFile in subFiles) {

                if (fileFilter.invoke(subFile)) {
                    continue
                }

                when (subFile.isDirectory) {
                    true -> {
                        if (contains(subFile, *texts, fileFilter = fileFilter)) {
                            return true
                        }
                    }
                    else -> {

                        val lines = subFile.readLines()
                        for (line in lines) {
                            texts.forEach { text ->
                                if (line.contains(text)) {
                                    return true
                                }
                            }
                        }
                    }
                }
            }
        }

        return false
    }
}
