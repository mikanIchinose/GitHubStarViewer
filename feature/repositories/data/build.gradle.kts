plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinSerialization)

    alias(libs.plugins.githubStarViewer.detekt)
}

android {
    namespace = "com.github.mikan.githubstarviewer.feature.repositories.data.impl"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
    }

    val javaVersion = JavaVersion.VERSION_17
    compileOptions {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }
    kotlinOptions {
        jvmTarget = javaVersion.toString()
    }
}

dependencies {
    implementation(projects.feature.repositories.domain)
    runtimeOnly(projects.core.network)

    // dependency injection
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.ktor.client.core)
    implementation(libs.kotlinx.serialization.json)

    // paging
    implementation(libs.androidx.paging.runtime)
    testImplementation(libs.androidx.paging.common)
}
