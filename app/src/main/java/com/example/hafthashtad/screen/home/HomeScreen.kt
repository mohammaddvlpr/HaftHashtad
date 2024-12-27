package com.example.hafthashtad.screen.home


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.hafthashtad.R
import com.example.hafthashtad.screen.home.models.CatUiModel
import kotlinx.coroutines.flow.flow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    onNavigateToDetail: (id: String) -> Unit,
    pagingItems: LazyPagingItems<CatUiModel>,
    onFavouriteClick: (catUiModel: CatUiModel) -> Unit,
) {


    Scaffold(modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.home)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Red,
                    titleContentColor = Color.White
                )
            )
        }) {
        LazyColumn(
            Modifier.padding(it),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                count = pagingItems.itemCount,
                key = pagingItems.itemKey { model -> model.id }
            ) { index ->
                val model = pagingItems[index]
                if (model != null)
                    CatItem(
                        modifier = Modifier.fillParentMaxWidth(),
                        onClick = { onNavigateToDetail(model.id) },
                        model = model,
                        onFavouriteClick = onFavouriteClick
                    )
            }

            pagingItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item { Loading(modifier = Modifier.fillParentMaxSize()) }
                    }

                    loadState.refresh is LoadState.Error -> {
                        val error = pagingItems.loadState.refresh as LoadState.Error
                        item {
                            Error(
                                modifier = Modifier.fillParentMaxSize(),
                                message = error.error.localizedMessage
                            ) { retry() }
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item { LoadingNextPageItem(modifier = Modifier) }
                    }

                    loadState.append is LoadState.Error -> {
                        val error = pagingItems.loadState.append as LoadState.Error
                        item {
                            Error(
                                modifier = Modifier,
                                message = error.error.localizedMessage
                            ) { retry() }
                        }
                    }
                }
            }
        }


    }
}

@Preview
@Composable
fun HomeScreenContentPreview() {
    val fakeCatUiModel = CatUiModel(
        id = "0",
        name = "Sample name",
        imageUrl = "",
        isFavourite = false
    )

    HomeScreenContent(
        onNavigateToDetail = { },
        pagingItems = flow { emit(PagingData.from(listOf(fakeCatUiModel))) }.collectAsLazyPagingItems(),
        onFavouriteClick = {}
    )
}


@Composable
fun CatItem(
    modifier: Modifier = Modifier,
    onClick: (CatUiModel) -> Unit,
    model: CatUiModel,
    onFavouriteClick: (model: CatUiModel) -> Unit
) {
    Card(
        modifier = modifier,
        onClick = { onClick(model) },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp, pressedElevation = 12.dp),
        shape = MaterialTheme.shapes.medium
    ) {

        Row(Modifier.padding(8.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(model.imageUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.img_place_holder),
                error = painterResource(id = R.drawable.img_place_holder),
                modifier = Modifier
                    .clip(MaterialTheme.shapes.small)
                    .size(96.dp),
                contentScale = ContentScale.Crop,

                contentDescription = "Cat Image"
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = model.name,
                maxLines = 2,
                style = MaterialTheme.typography.titleMedium,
            )

            Spacer(modifier = Modifier.weight(1f))

            IconButton(
                modifier = Modifier.align(Alignment.Bottom),
                onClick = { onFavouriteClick(model) }) {
                Icon(
                    imageVector = if (model.isFavourite)
                        Icons.Default.Favorite
                    else
                        Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    tint = Color.Red
                )
            }


        }
    }
}

@Composable
fun Loading(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.loading_message),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        CircularProgressIndicator(Modifier.padding(top = 10.dp))
    }
}

@Composable
fun LoadingNextPageItem(modifier: Modifier) {
    CircularProgressIndicator(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Composable
fun Error(
    modifier: Modifier = Modifier,
    message: String?,
    onClickRetry: () -> Unit
) {
    Row(
        modifier = modifier.padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message ?: stringResource(id = R.string.unknown_error_message),
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.weight(1f),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        OutlinedButton(onClick = onClickRetry) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}

@Preview
@Composable
fun CatItemPreview() {
    Box(
        Modifier
            .background(Color.White)
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        CatItem(
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
            model = CatUiModel(
                id = "0",
                name = "Sample name",
                imageUrl = "https://gratisography.com/wp-content/uploads/2024/01/gratisography-cyber-kitty-800x525.jpg",
                isFavourite = true
            ),
            onFavouriteClick = {}
        )
    }
}