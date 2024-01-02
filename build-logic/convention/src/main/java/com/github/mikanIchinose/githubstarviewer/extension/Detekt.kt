package com.github.mikanIchinose.githubstarviewer.extension

import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import io.gitlab.arturbosch.detekt.report.ReportMergeTask
import org.gradle.api.Project
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType
import java.util.Properties

internal fun Project.setupDetekt(extension: DetektExtension) {
    val properties = Properties().apply {
        rootProject
            .file("config/detekt/detekt.properties")
            .inputStream()
            .let {
                load(it)
                it.close()
            }
    }
    extension.apply {
        properties.getProperty("autoCorrect")?.let {
            autoCorrect = it.toBoolean()
        }
        parallel = true
    }
    mergeDetektReports()
}

private fun Project.mergeDetektReports() {
    val reportMergeTaskProvider = if (!rootProject.tasks.names.contains("reportMerge")) {
        rootProject.tasks.register("reportMerge", ReportMergeTask::class) {
            output.set(rootProject.layout.buildDirectory.file("reports/detekt/merge.xml"))
        }
    } else {
        rootProject.tasks.named("reportMerge") as TaskProvider<ReportMergeTask>
    }

    plugins.withType<io.gitlab.arturbosch.detekt.DetektPlugin> {
        tasks.withType<io.gitlab.arturbosch.detekt.Detekt> detekt@{
            finalizedBy(reportMergeTaskProvider)

            source = project.files("./").asFileTree

            include("**/*.kt")
            include("**/*.kts")
            exclude("**/resources/**")
            exclude("**/build/**")


            reportMergeTaskProvider.configure {
                input.from(this@detekt.xmlReportFile) // or .sarifReportFile
            }
        }
    }
}