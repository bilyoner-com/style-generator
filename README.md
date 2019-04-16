# Style Generator
Style-Generator is a Gradle plugin to generate style definitions.

## Download
Latest version: [![](https://jitpack.io/v/bilyoner-com/style-generator.svg)](https://jitpack.io/#bilyoner-com/style-generator)

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}
dependencies {
    classpath 'com.github.bilyoner-com:style-generator:1.2.0'
}
```

```groovy
apply plugin: 'com.stylegenerator.plugin'

styleGenerator {
    parentStyle = 'TextAppearance.AppCompat'

    minTextSize = 10
    maxTextSize = 12
}
```

## How To Use
Style-Generator uses font files located under `src/main/res/font` folder, defined colors in `colors.xml` and text size range given by user.
You can generate `style_text_appearance.xml` file under `src/main/res/values` folder by running `generateStyle` task.
Generated style definitions looks like:
```xml
    <style name="TextStyle.MediumFont">
        <item name="android:fontFamily">@font/medium_font</item>
    </style>

    <style name="TextStyle.MediumFont.Red">
        <item name="android:textColor">@color/red</item>
    </style>

    <style name="TextStyle.MediumFont.Red.12">
        <item name="android:textSize">12sp</item>
    </style>
```

You can use these definitions in layout files:
```xml
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        android:textAppearance="@style/TextStyle.MediumFont.Red.12" />
```


## License
Style Generator binaries and source code can be used according to the [Apache License, Version 2.0](LICENSE).
