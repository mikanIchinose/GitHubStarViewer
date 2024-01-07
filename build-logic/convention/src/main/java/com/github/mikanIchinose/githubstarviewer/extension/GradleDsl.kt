package com.github.mikanIchinose.githubstarviewer.extension

import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.implementation(
    artifact: MinimalExternalModuleDependency,
) {
    add("implementation", artifact)
}

fun DependencyHandlerScope.implementationPlatform(
    artifact: MinimalExternalModuleDependency,
) {
    add("implementation", platform(artifact))
}

fun DependencyHandlerScope.testImplementation(
    artifact: MinimalExternalModuleDependency,
) {
    add("testImplementation", artifact)
}

fun DependencyHandlerScope.androidTestImplementation(
    artifact: MinimalExternalModuleDependency,
) {
    add("androidTestImplementation", artifact)
}