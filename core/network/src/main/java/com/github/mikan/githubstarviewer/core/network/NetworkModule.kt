package com.github.mikan.githubstarviewer.core.network

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun provideKtorLogger(): Logger = object : Logger {
        override fun log(message: String) {
            Log.i("Ktor", message)
        }
    }

    @Provides
    @Singleton
    fun provideKtorClient(
//        okhttpLogger: HttpLoggingInterceptor,
        ktorLogger: Logger,
    ): HttpClient = HttpClient(OkHttp) {
        // engine {
        //    addInterceptor(logging)
        // }
        install(Logging) {
            logger = ktorLogger
            level = LogLevel.BODY
        }
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                }
            )
        }
        install(DefaultRequest) {
            url("https://api.github.com/")
            headers {
                append("Accept", "application/vnd.github+json")
                append("X-GitHub-Api-Version", "2022-11-28")
                append(
                    "Authorization",
                    "Bearer ${BuildConfig.API_TOKEN}"
                )
            }
        }
    }
}
