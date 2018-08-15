package com.stylegenerator.plugin.util;

public final class StringUtil {

    private static final String UNDER_SCORE = "_";

    private StringUtil() {
    }

    public static String convertCamelCase(String s) {
        if (s.contains(UNDER_SCORE)) {
            return convertCamelCaseFromUnderScore(s);
        } else {
            return capitalizeFirstLetter(s);
        }
    }

    private static String convertCamelCaseFromUnderScore(String s) {
        StringBuilder camelCaseString = new StringBuilder();
        String[] parts = s.split(UNDER_SCORE);
        for (String part : parts) {
            camelCaseString.append(capitalizeFirstLetter(part));
        }
        return camelCaseString.toString();
    }

    private static String capitalizeFirstLetter(String s) {
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
}
