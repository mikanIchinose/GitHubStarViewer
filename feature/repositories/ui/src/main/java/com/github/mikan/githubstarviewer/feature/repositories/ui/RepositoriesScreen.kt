package com.github.mikan.githubstarviewer.feature.repositories.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Balance
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sell
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.mikan.githubstarviewer.feature.repositories.domain.model.RepositoryDomainModel
import com.github.mikan.githubstarviewer.feature.repositories.ui.theme.ProgrammingLanguageColor

@Composable
fun RepositoriesScreen(
    viewModel: RepositoriesViewModel = viewModel()
) {
    val repositories = viewModel.repositories
    LaunchedEffect(repositories) {
        viewModel.load()
    }

    if (repositories != null) {
        RepositoryList(repositories)
    } else {
        CircularProgressIndicator()
    }
}

@Composable
fun RepositoryList(
    repositories: List<RepositoryDomainModel>,
) {
    if (repositories.isEmpty()) {
        // Show empty state
        return
    }

    LazyColumn {
        items(repositories, key = { it.nameWithOwner }) { repository ->
            RepositoryItem(
                name = repository.nameWithOwner,
                language = repository.language,
                license = repository.license,
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun RepositoryItem(
    name: String,
    language: String?,
    license: String?
) {
    Column {
        Text(text = name)
        Row {
            Text(text = language ?: "")
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = license ?: "")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Filter(
    keyword: String,
    onKeywordChange: (String) -> Unit,
    languages: List<String>,
) {
    var active by rememberSaveable { mutableStateOf(false) }
    SearchBar(
        shape = CircleShape,
        query = keyword,
        onQueryChange = onKeywordChange,
        onSearch = { active = false },
        active = active,
        onActiveChange = { active = it },
        placeholder = { Text(text = "リポジトリを検索") },
        trailingIcon = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.Search, contentDescription = null)
                }
                LanguageFilterButton(languages = languages)
                OtherFilterButton()
            }
        }
    ) {}
}

@Composable
fun LanguageFilterButton(
    languages: List<String>
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    Box {
        Box(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape,
                )
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Text(text = "Lang", color = MaterialTheme.colorScheme.onPrimary)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            languages.forEach {
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = {
                        expanded = false
                    },
                )
            }
        }
    }
}

@Composable
fun OtherFilterButton() {
    var expanded by rememberSaveable { mutableStateOf(false) }
    Box {
        IconButton(
            onClick = { expanded = true },
        ) {
            Icon(
                Icons.Default.Menu,
                contentDescription = null,
            )
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            var enabled1 by rememberSaveable { mutableStateOf(false) }
            var enabled2 by rememberSaveable { mutableStateOf(false) }
            DropdownMenuItem(
                leadingIcon = { Icon(Icons.Default.Star, null) },
                text = { Text("star") },
                trailingIcon = {
                    if (enabled1) {
                        Icon(Icons.Default.Check, null)
                    }
                },
                onClick = { enabled1 = !enabled1 },
            )
            DropdownMenuItem(
                leadingIcon = { Icon(Icons.Default.Favorite, null) },
                text = { Text("alpha") },
                trailingIcon = {
                    if (enabled2) {
                        Icon(Icons.Default.Check, null)
                    }
                },
                onClick = { enabled2 = !enabled2 },
            )
        }
    }
}

data class Repository(
    val nameWithOwner: String,
    val url: String,
    val iconImageUrl: String,
    val star: Int,
    val description: String? = null,
    val programmingLanguage: String? = null,
    val license: String? = null,
    val tag: String? = null,
)

@Composable
fun RepositoryItem(
    repository: Repository,
    onClickRepository: (url: String) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(4.dp)
            .clickable(onClick = { onClickRepository(repository.url) }),
        verticalAlignment = Alignment.Bottom
    ) {
        Box(
            modifier = Modifier.fillMaxHeight(),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.github_icon_light),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }
        Spacer(modifier = Modifier.size(12.dp))
        Column {
            Text(
                text = repository.nameWithOwner,
                fontWeight = FontWeight.Bold,
            )
            repository.description?.let {
                Text(
                    text = it,
                    fontSize = 12.sp,
                )
                Spacer(modifier = Modifier.height(2.dp))
            }
            Row {
                repository.programmingLanguage?.let {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ProgrammingLanguageTip(programmingLanguage = it)
                        Spacer(modifier = Modifier.size(4.dp))
                        SmallText(text = it)
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.StarBorder,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    SmallText(text = repository.star.toString())
                }
                repository.tag?.let {
                    Spacer(modifier = Modifier.size(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Sell,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        SmallText(text = it)
                    }
                }
                repository.license?.let {
                    Spacer(modifier = Modifier.size(8.dp))
                    Row {
                        Icon(
                            Icons.Default.Balance,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        SmallText(text = "$it license")
                    }
                }
            }
        }
    }
}

@Composable
fun ProgrammingLanguageTip(programmingLanguage: String) {
    val color = ProgrammingLanguageColor.fromLanguageName(programmingLanguage).color
    Box(
        modifier = Modifier
            .size(12.dp)
            .background(color, CircleShape)
    )
}

@Composable
fun SmallText(
    text: String,
) {
    Text(text = text, fontSize = 12.sp)
}

@Preview
@Composable
private fun PreviewFilter() {
    Filter(keyword = "", onKeywordChange = {}, languages = listOf("Kotlin", "Java"))
}

@Preview
@Composable
private fun PreviewLanguageFilter() {
    LanguageFilterButton(languages = listOf("Kotlin", "Java"))
}

@Preview
@Composable
private fun PreviewRepositoryItem() {
    Surface(modifier = Modifier.fillMaxSize()) {
        val repositories = listOf(
            Repository(
                nameWithOwner = "BuilderIO/qwik",
                url = "",
                iconImageUrl = "",
                star = 19138,
                description = "Instant-loading web app, without effort",
                programmingLanguage = "TypeScript",
                tag = "v1.2.17",
                license = "MIT",
            ),
            Repository(
                nameWithOwner = "pdvrieze/xmlutil",
                url = "",
                iconImageUrl = "",
                star = 0,
            ),
            Repository(
                nameWithOwner = "google/secrets-gradle-plugin",
                url = "",
                iconImageUrl = "",
                star = 872,
                programmingLanguage = "Kotlin",
                tag = "v2.0.1",
                license = "Apache-2.0",
            ),
            Repository(
                nameWithOwner = "passy/build-time-tracker-plugin",
                url = "",
                iconImageUrl = "",
                star = 1201,
                programmingLanguage = "Groovy",
                tag = "v0.11.1",
                license = "Apache-2.0",
            ),
        )
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            repositories.forEach {
                RepositoryItem(
                    repository = it,
                    onClickRepository = {},
                )
                Divider()
            }
        }
    }
}
