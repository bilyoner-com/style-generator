package com.stylegenerator.plugin.util

import java.util.Arrays

object StyleUtil {

    const val STYLE_NAME_PREFIX = "TextStyle"

    fun getStyleName(vararg styleNameParams: Any, separator: String = "."): String {
        return Arrays.asList(*styleNameParams)
                .mapTo(arrayListOf()) { StringUtil.convertCamelCase(it.toString()) }
                .apply { add(0, STYLE_NAME_PREFIX) }
                .joinToString(separator)
    }

    fun getItemName(itemTagType: ItemTagType): String {
        return when (itemTagType) {
            ItemTagType.FONT -> {
                "android:fontFamily"
            }
            ItemTagType.COLOR -> {
                "android:textColor"
            }
            ItemTagType.SIZE -> {
                "android:textSize"
            }
        }
    }

    fun getItemValue(itemTagType: ItemTagType, value: Any): String {
        return when (itemTagType) {
            ItemTagType.FONT -> {
                "@font/$value"
            }
            ItemTagType.COLOR -> {
                "@color/$value"
            }
            ItemTagType.SIZE -> {
                "${value}sp"
            }
        }
    }

    enum class ItemTagType {
        FONT,
        COLOR,
        SIZE
    }
}
