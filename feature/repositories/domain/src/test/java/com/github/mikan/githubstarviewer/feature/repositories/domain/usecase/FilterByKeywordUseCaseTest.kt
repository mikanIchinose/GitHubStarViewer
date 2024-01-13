package com.github.mikan.githubstarviewer.feature.repositories.domain.usecase

import com.github.mikan.githubstarviewer.core.testing.bdd.Given
import com.github.mikan.githubstarviewer.core.testing.bdd.Then
import com.github.mikan.githubstarviewer.core.testing.bdd.When
import com.github.mikan.githubstarviewer.core.testing.domain.model.sampleRepositoryDomainModels
import com.github.mikan.githubstarviewer.feature.repositories.domain.model.RepositoryDomainModel
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("キーワードによるフィルター")
class FilterByKeywordUseCaseTest {
    private lateinit var sut: FilterByKeywordUseCase

    @BeforeTest
    fun setUp() {
        // Arrange
        sut = FilterByKeywordUseCase()
    }

    @Test
    @DisplayName("キーワードを持つものが絞り込まれること")
    fun `Repositories containing the given keyword are filtered`() {
        Given {
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
                    repositories = repositories,
                    include = "mikan",
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
    @DisplayName("キーワードを持たないものが絞り込まれること")
    fun `Repositories containing the given keyword are not filtered`() {
        Given {
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
                    repositories = repositories,
                    exclude = "mikan",
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
    @DisplayName("包含キーワードを持ち、除外キーワードを持たないものが絞り込まれること")
    fun `Repositories included a keyword and excluding another are filtered`() {
        Given {
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
                    repositories = repositories,
                    include = "mikan",
                    exclude = "Shougo",
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

    @Test
    @DisplayName("空文字で絞り込む = 変化なし")
    fun `Repositories are not filtered by a blank keyword`() {
        Given {
            When {
                val result = sut(
                    repositories = sampleRepositoryDomainModels
                )
                Then {
                    assertEquals(
                        sampleRepositoryDomainModels,
                        result
                    )
                }
            }
        }
    }

    @Test
    @DisplayName("包含キーワードと除外キーワードは一致してはいけない")
    fun `included-keyword and excluded-keyword must not same`() {
        // Arrange
        val sut = FilterByKeywordUseCase()
        // Assert
        assertThrows<IllegalArgumentException> {
            sut(
                repositories = sampleRepositoryDomainModels,
                include = "mikan",
                exclude = "mikan",
            )
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
