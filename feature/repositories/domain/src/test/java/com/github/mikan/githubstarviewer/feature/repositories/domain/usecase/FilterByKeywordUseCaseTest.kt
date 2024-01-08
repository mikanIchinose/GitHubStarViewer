package com.github.mikan.githubstarviewer.feature.repositories.domain.usecase

import com.github.mikan.githubstarviewer.core.testing.bdd.Given
import com.github.mikan.githubstarviewer.core.testing.bdd.Then
import com.github.mikan.githubstarviewer.core.testing.bdd.When
import com.github.mikan.githubstarviewer.feature.repositories.domain.model.RepositoryDomainModel
import kotlin.test.Test
import kotlin.test.assertEquals

class FilterByKeywordUseCaseTest {
    @Test
    fun filter_by_keyword() {
        Given {
            val sut = FilterByKeywordUseCase()
            val repositories = listOf(
                // matched name
                createRepositoryDomainModel(
                    nameWithOwner = "mikan/ddu-source-around",
                    description = "description",
                ),
                // matched description
                createRepositoryDomainModel(
                    nameWithOwner = "Shougo/ddu-source-file",
                    description = "mikan",
                ),
                // unmatched
                createRepositoryDomainModel(
                    nameWithOwner = "Shougo/ddu-source-file",
                    description = "ddu source for file",
                ),
            )
            When {
                val result = sut(
                    include = "mikan",
                    repositories = repositories
                )
                Then {
                    assertEquals(
                        listOf(repositories[0], repositories[1]),
                        result,
                        "キーワードを含むリポジトリが抜けています"
                    )
                }
            }
        }
    }

    @Test
    fun filter_not_by_keyword() {
        Given {
            val sut = FilterByKeywordUseCase()
            val repositories = listOf(
                // unmatched name
                createRepositoryDomainModel(
                    nameWithOwner = "mikan/ddu-source-around",
                    description = "description",
                ),
                // unmatched description
                createRepositoryDomainModel(
                    nameWithOwner = "Shougo/ddu-source-file",
                    description = "mikan",
                ),
                // matched
                createRepositoryDomainModel(
                    nameWithOwner = "Shougo/ddu-source-file",
                    description = "ddu source for file",
                ),
            )
            When {
                val result = sut(
                    exclude = "mikan",
                    repositories = repositories
                )
                Then {
                    assertEquals(
                        listOf(repositories[2]),
                        result,
                        "キーワードを含んだリポジトリが含まれています"
                    )
                }
            }
        }
    }

    @Test
    fun filter_and_filter_not_by_keyword() {
        Given {
            val sut = FilterByKeywordUseCase()
            val repositories = listOf(
                createRepositoryDomainModel(
                    nameWithOwner = "mikan/ddu-source-around",
                    description = "description",
                ),
                createRepositoryDomainModel(
                    nameWithOwner = "Shougo/ddu-source-file",
                    description = "mikan",
                ),
                createRepositoryDomainModel(
                    nameWithOwner = "Shougo/ddu-source-file",
                    description = "ddu source for file",
                ),
            )
            When {
                val result = sut(
                    include = "mikan",
                    exclude = "Shougo",
                    repositories = repositories
                )
                Then {
                    assertEquals(
                        listOf(repositories[0]),
                        result,
                        "キーワードを含むリポジトリが抜けています"
                    )
                }
            }
        }
    }

    private fun createRepositoryDomainModel(
        nameWithOwner: String,
        description: String,
    ): RepositoryDomainModel {
        return RepositoryDomainModel(
            nameWithOwner = nameWithOwner,
            description = description,
            url = "",
            stars = 0,
            language = null,
            languages = emptyList(),
            license = null,
        )
    }
}
