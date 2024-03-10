package com.github.mikan.githubstarviewer.feature.repositories.ui.model

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.github.mikan.githubstarviewer.feature.repositories.domain.model.RepositoryDomainModel

internal data class RepositoryUiModel(
    val nameWithOwner: String,
    val url: String,
    val iconImageUrl: String,
    val description: String? = null,
    val star: Int,
    val programmingLanguage: String? = null,
    val tag: String? = null,
    val license: String? = null,
)

internal fun RepositoryDomainModel.toUiModel() = RepositoryUiModel(
    nameWithOwner = nameWithOwner,
    url = "",
    iconImageUrl = "",
    star = 0,
    description = description,
    programmingLanguage = language,
    license = license,
    tag = null,
)

internal class RepositoryUiModelPreviewParameterProvider :
    PreviewParameterProvider<RepositoryUiModel> {
    override val values = sequenceOf(
        RepositoryUiModel(
            nameWithOwner = "BuilderIO/qwik",
            url = "",
            iconImageUrl = "",
            star = 19138,
            description = "Instant-loading web app, without effort",
            programmingLanguage = "TypeScript",
            tag = "v1.2.17",
            license = "MIT",
        ),
        RepositoryUiModel(
            nameWithOwner = "pdvrieze/xmlutil",
            url = "",
            iconImageUrl = "",
            star = 0,
        ),
        RepositoryUiModel(
            nameWithOwner = "google/secrets-gradle-plugin",
            url = "",
            iconImageUrl = "",
            star = 872,
            programmingLanguage = "Kotlin",
            tag = "v2.0.1",
            license = "Apache-2.0",
        ),
        RepositoryUiModel(
            nameWithOwner = "passy/build-time-tracker-plugin",
            url = "",
            iconImageUrl = "",
            star = 1201,
            programmingLanguage = "Groovy",
            tag = "v0.11.1",
            license = "Apache-2.0",
        ),
    )
}
