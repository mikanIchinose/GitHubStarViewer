plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)

    alias(libs.plugins.githubStarViewer.detekt)
    alias(libs.plugins.githubStarViewer.unitTest)
}

android {
    namespace = "com.github.mikan.githubstarviewer.feature.repositories.domain"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    runtimeOnly(projects.feature.repositories.data)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(libs.kotlinx.coroutines.android)

    testImplementation(projects.core.testing)

    // paging
    implementation(libs.androidx.paging.runtime)
    testImplementation(libs.androidx.paging.common)
}
