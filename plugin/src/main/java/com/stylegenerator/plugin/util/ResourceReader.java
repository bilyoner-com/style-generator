package com.stylegenerator.plugin.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class ResourceReader {

    private static final String FONTS_FOLDER = "src/main/res/font";
    private static final String COLORS_FILE = "src/main/res/values/colors.xml";

    private static final String EXTENSION_SEPARATOR = ".";

    private ResourceReader() {
    }

    public static List<String> getFontNames() {
        List<String> fontsWithoutExtension = Collections.emptyList();

        String[] fonts = new File(FONTS_FOLDER).list();
        if (fonts != null && fonts.length > 0) {
            fontsWithoutExtension = Arrays.stream(fonts)
                    .map(filename -> filename.substring(0, filename.lastIndexOf(EXTENSION_SEPARATOR)))
                    .collect(Collectors.toList());
        }

        return fontsWithoutExtension;
    }

    public static List<String> getColorNames() {
        Map<String, String> colors = XMLHelper.readFromXML(new File(COLORS_FILE), "color");
        return new ArrayList<>(colors.keySet());
    }
}
