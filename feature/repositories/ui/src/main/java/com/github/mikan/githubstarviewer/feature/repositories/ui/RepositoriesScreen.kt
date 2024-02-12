package com.github.mikan.githubstarviewer.feature.repositories.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Balance
import androidx.compose.material.icons.filled.Sell
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.mikan.githubstarviewer.feature.repositories.ui.model.RepositoryUiModel
import com.github.mikan.githubstarviewer.feature.repositories.ui.model.RepositoryUiModelPreviewParameterProvider
import com.github.mikan.githubstarviewer.feature.repositories.ui.theme.ProgrammingLanguageColor

@Composable
fun RepositoriesScreen(
    viewModel: RepositoriesViewModel = viewModel(),
) {
    // val items = viewModel.items.collectAsLazyPagingItems()
    // RepositoriesScreen(
    //     uiModels = items.itemSnapshotList.items,
    //     isLoading = items.loadState.refresh is LoadState.Loading,
    //     isAppendLoading = items.loadState.append is LoadState.Loading,
    // )
}

// Pagingの世界から切り離された優しい世界
@Composable
private fun RepositoriesScreen(
    uiModels: List<RepositoryUiModel>,
    isLoading: Boolean,
    isAppendLoading: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter,
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(count = uiModels.size) { index ->
                val uiModel = uiModels[index]
                RepositoryItem(
                    uiModel = uiModel,
                    onClick = { println(uiModel.url) },
                )
            }
            if (isAppendLoading) {
                item {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
private fun RepositoryItem(
    uiModel: RepositoryUiModel,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RepositoryIcon(imageUrl = uiModel.iconImageUrl)
        Column {
            // name
            Text(
                text = uiModel.nameWithOwner,
                fontWeight = FontWeight.Bold,
            )
            // description
            uiModel.description?.let {
                Text(
                    text = it,
                    fontSize = 12.sp,
                )
            }
            // metadata
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                uiModel.programmingLanguage?.let {
                    RepositoryMetaData(
                        icon = {
                            val color = ProgrammingLanguageColor.fromLanguageName(it)
                            ProgrammingLanguageTip(color)
                        },
                        text = it
                    )
                }
                RepositoryMetaData(
                    icon = {
                        Icon(
                            Icons.Default.StarBorder,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    },
                    text = "${uiModel.star}"
                )
                uiModel.tag?.let {
                    RepositoryMetaData(
                        icon = {
                            Icon(
                                Icons.Default.Sell,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                        },
                        text = it
                    )
                }
                uiModel.license?.let {
                    RepositoryMetaData(
                        icon = {
                            Icon(
                                Icons.Default.Balance,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                        },
                        text = it
                    )
                }
            }
        }
    }
}

@Composable
private fun RepositoryMetaData(
    icon: @Composable () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        icon()
        Text(text = text, fontSize = 12.sp)
    }
}

@Composable
private fun RepositoryIcon(
    imageUrl: String,
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

@Composable
private fun ProgrammingLanguageTip(color: ProgrammingLanguageColor) {
    Box(
        modifier = Modifier
            .size(12.dp)
            .background(color.color, CircleShape)
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewRepositoryItem(
    @PreviewParameter(RepositoryUiModelPreviewParameterProvider::class)
    uiModel: RepositoryUiModel,
) {
    RepositoryItem(
        uiModel = uiModel,
        onClick = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewRepositoriesScreen() {
    Scaffold {
        RepositoriesScreen(
            uiModels = RepositoryUiModelPreviewParameterProvider().values.toList(),
            isLoading = false,
            isAppendLoading = false,
            modifier = Modifier.padding(it),
        )
    }
}
