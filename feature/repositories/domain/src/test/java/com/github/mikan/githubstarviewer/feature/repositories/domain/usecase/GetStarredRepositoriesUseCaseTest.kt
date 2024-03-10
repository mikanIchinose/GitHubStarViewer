package com.github.mikan.githubstarviewer.feature.repositories.domain.usecase

import com.github.mikan.githubstarviewer.core.testing.bdd.GivenSuspend
import com.github.mikan.githubstarviewer.core.testing.bdd.ThenSuspend
import com.github.mikan.githubstarviewer.core.testing.bdd.WhenSuspend
import com.github.mikan.githubstarviewer.core.testing.repository.TestGitHubRepository
import com.github.mikan.githubstarviewer.core.testing.repository.model.sampleLanguages
import com.github.mikan.githubstarviewer.core.testing.repository.model.sampleRepositoryModels
import com.github.mikan.githubstarviewer.feature.repositories.domain.model.RepositoryDomainModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test
import kotlin.test.assertEquals

// TODO: 削除予定
class GetStarredRepositoriesUseCaseTest {
    @Test
    @DisplayName(
        "RepositoryとLanguagesを結合してDomainModelに変換する"
    )
    fun Repository_and_Languages_merge_into_DomainModel() = runTest {
        GivenSuspend {
            val repository = TestGitHubRepository()
            val sut = GetStarredRepositoriesUseCase(repository)
            repository.sendRepositories(sampleRepositoryModels)
            repository.sendLanguages(sampleLanguages)

            WhenSuspend {
                val actual = sut().first()

                ThenSuspend {
                    val expect = sampleRepositoryModels.map {
                        RepositoryDomainModel(
                            nameWithOwner = it.fullName,
                            description = it.description,
                            url = it.url,
                            stars = it.stars,
                            language = it.language,
                            languages = sampleLanguages,
                            license = it.license?.name,
                        )
                    }
                    assertEquals(expect, actual)
                }
            }
        }
    }
}
