package com.example.hafthashtad.screen.detail

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.hafthashtad.R
import com.example.hafthashtad.screen.detail.models.CatDetailUiModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatDetailScreenContent(
    modifier: Modifier = Modifier,
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
                    navigationIconContentColor = MaterialTheme.colorScheme.primary
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
    }
}

@Preview
@Composable
fun CatDetailContentPreview() {
    CatDetailScreenContent(
        onNavigateToParent = {}
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
