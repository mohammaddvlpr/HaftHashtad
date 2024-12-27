package com.example.hafthashtad.screen.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.hafthashtad.R
import com.example.hafthashtad.screen.detail.models.CatDetailUiModel
import com.example.hafthashtad.screen.detail.models.DetailScreenState

@Composable
fun CatDetailScreen(
    viewModel: DetailScreenViewModel = hiltViewModel(),
    onNavigateToParent: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    CatDetailScreenContent(state = state, onNavigateToParent = onNavigateToParent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatDetailScreenContent(
    modifier: Modifier = Modifier,
    state: DetailScreenState,
    onNavigateToParent: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.cat_detail)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                navigationIcon = {
                    Icon(
                        modifier = Modifier.clickable(onClick = onNavigateToParent),
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = "Navigation icon",
                    )
                }
            )
        },
    ) {
        Column(
            Modifier
                .padding(it)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(state.newsDetailUiModel.imageUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.img_place_holder),
                error = painterResource(id = R.drawable.img_place_holder),
                contentScale = ContentScale.Crop,
                contentDescription = "Content Image"
            )

            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = state.newsDetailUiModel.name,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = state.newsDetailUiModel.description,
                style = MaterialTheme.typography.bodyLarge
            )

        }

    }
}

@Preview
@Composable
fun CatDetailContentPreview() {
    CatDetailScreenContent(
        onNavigateToParent = {},
        state = DetailScreenState(fakeCatDetailUiModel)
    )
}

val fakeCatDetailUiModel = CatDetailUiModel(
    id = "0",
    name = "Sample name",
    imageUrl = "https://gratisography.com/wp-content/uploads/2024/01/gratisography-cyber-kitty-800x525.jpg",
    description = "There are five distinct icon themes: Filled, Outlined, Rounded, TwoTone," +
            " and Sharp. Each theme contains the same icons, but with a distinct visual" +
            " style. You should typically choose one theme and use it across your " +
            "application for consistency. For example, you may want to use a property " +
            "or a typealias to refer to a specific theme, so it can be accessed in " +
            "a semantically meaningful way from inside other composables."
)
