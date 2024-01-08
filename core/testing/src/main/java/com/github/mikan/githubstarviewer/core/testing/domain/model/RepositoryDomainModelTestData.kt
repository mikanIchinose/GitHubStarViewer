package com.github.mikan.githubstarviewer.core.testing.domain.model

import com.github.mikan.githubstarviewer.feature.repositories.domain.model.RepositoryDomainModel

val sampleRepositoryDomainModels = listOf(
    RepositoryDomainModel(
        nameWithOwner = "mikan/ddu-source-around",
        description = "description",
        url = "",
        stars = 0,
        language = "Kotlin",
        languages = listOf("Kotlin"),
        license = "MIT",
    ),
    RepositoryDomainModel(
        nameWithOwner = "Shougo/ddu-source-file",
        description = "mikan",
        url = "",
        stars = 0,
        language = "Kotlin",
        languages = listOf("Kotlin"),
        license = "MIT",
    ),
    RepositoryDomainModel(
        nameWithOwner = "Shougo/ddu-source-file",
        description = "ddu source for file",
        url = "",
        stars = 0,
        language = "Kotlin",
        languages = listOf("Kotlin"),
        license = "MIT",
    ),
)