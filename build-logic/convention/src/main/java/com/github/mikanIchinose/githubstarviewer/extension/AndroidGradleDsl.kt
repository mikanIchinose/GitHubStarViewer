package com.github.mikanIchinose.githubstarviewer.extension

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

fun Project.android(action: LibraryExtension.() -> Unit) {
    extensions.configure(action)
}

fun Project.setUpJunit5() {
    android {
        @Suppress("UnstableApiUsage")
        testOptions {
            unitTests.all {
                it.useJUnitPlatform()
            }
        }
    }
}
