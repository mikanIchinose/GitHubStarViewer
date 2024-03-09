plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlinAndroid)

    alias(libs.plugins.ksp)

    alias(libs.plugins.githubStarViewer.detekt)
}

android {
    namespace = "com.github.mikan.githubstarreviewer.core.data.impl"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
}
