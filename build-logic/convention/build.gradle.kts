import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.githun.mikan.githubstarviewer.buildlogic"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
val javaVersion = JavaVersion.VERSION_17
java {
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = javaVersion.toString()
    }
}

dependencies {
//    compileOnly(libs.android.gradlePlugin)
//    compileOnly(libs.android.tools.common)
//    compileOnly(libs.firebase.crashlytics.gradlePlugin)
//    compileOnly(libs.firebase.performance.gradlePlugin)
//    compileOnly(libs.kotlin.gradlePlugin)
//    compileOnly(libs.ksp.gradlePlugin)
//    compileOnly(libs.room.gradlePlugin)
//    implementation(libs.truth)
    implementation(libs.detekt.gradle.plugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("detekt") {
            id = "githubstarviewer.detekt"
            implementationClass = "DetektPlugin"
        }
    }
}