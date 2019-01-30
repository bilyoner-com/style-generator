package com.stylegenerator.plugin.util

object StringUtil {

    private const val UNDER_SCORE = "_"

    fun convertCamelCase(s: String): String {
        return s.split(UNDER_SCORE)
                .joinToString("") {
                    it.capitalize()
                }
    }
}
