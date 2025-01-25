plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlinAndroid)

    alias(libs.plugins.githubStarViewer.detekt)
}

android {
    namespace = "com.github.mikan.githubstarreview.data.api"
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
    implementation(libs.kotlinx.coroutines.android)
}
