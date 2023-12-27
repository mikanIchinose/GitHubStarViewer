package com.github.mikan.githubstarviewer.feature.repositories.ui.theme

import androidx.compose.ui.graphics.Color

enum class ProgrammingLanguageColor(val languageName: String, val color: Color) {
    Java("Java", Color(0xFFB07219)),
    Kotlin("Kotlin", Color(0xFFA97BFF)),
    Groovy("Groovy", Color(0xFF4298B8)),
    Swift("Swift", Color(0xFFF05138)),
    Dart("Dart", Color(0xFF00B4AB)),
    TypeScript("TypeScript", Color(0xFF3178C6)),
    JavaScript("JavaScript", Color(0xFFF1E05A)),
    Python("Python", Color(0xFF3572A5)),
    Lua("Lua", Color(0xFF000080)),
    VimScript("Vim Script", Color(0xFF199F4B)),
    Shell("Shell", Color(0xFF89E051)),
    Go("Go", Color(0xFF00ADD8)),
    Rust("Rust", Color(0xFFDEA584)),
    Other("Other", Color.Gray), ;

    companion object {
        fun fromLanguageName(languageName: String): ProgrammingLanguageColor {
            return entries.find { it.languageName == languageName } ?: Other
        }
    }
}