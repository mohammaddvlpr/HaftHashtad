package com.example.hafthashtad.screen.home


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.hafthashtad.R
import com.example.hafthashtad.screen.home.models.CatUiModel


@Composable
fun CatItem(modifier: Modifier = Modifier, onClick: (CatUiModel) -> Unit, model: CatUiModel) {
    Card(
        modifier = modifier,
        onClick = { onClick(model) },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp, pressedElevation = 12.dp),
        shape = MaterialTheme.shapes.medium
    ) {

        Row(Modifier.padding(8.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
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

            Text(
                text = model.name,
                maxLines = 2,
                style = MaterialTheme.typography.titleMedium,
            )


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
                isFavourite = false
            )
        )
    }
}