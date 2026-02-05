package com.compose.babyai.ui.screens.aiTry

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.compose.babyai.R
import com.compose.babyai.ui.component.AiTryHeader
import com.compose.babyai.ui.component.OutfitTryCard
import com.compose.babyai.ui.component.SearchBar
import com.compose.babyai.ui.theme.BabyAITheme
import com.compose.babyai.ui.theme.PrimaryColor

@Composable
fun AiTryScreen(navController: NavHostController) {
    var searchQuery by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.main_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(horizontal = 20.dp)
        ) {
            AiTryHeader()

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = stringResource(R.string.Select_Outfits),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                color = Color.Black
            )
            Text(
                text = stringResource(R.string.Select_Outfits_Desc),
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.nunito_regular)),
                fontWeight = FontWeight.Normal,
                color = Color(0xFFB0B0B0)
            )

            Spacer(modifier = Modifier.height(24.dp))

            SearchBar(
                searchQuery = searchQuery,
                onSearchQueryChange = { searchQuery = it },
                placeholderText = "Search outfits, brands or categories",
                icon = painterResource(id = R.drawable.search_ic)
            )

            Spacer(modifier = Modifier.height(24.dp))

            val outfits = remember {
                listOf(
                    OutfitData("BabySky Blue Stripes", "$249.99", R.drawable.dummy_img, true),
                    OutfitData("BabySky Blue Stripes", "$249.99", R.drawable.dummy_img, false),
                    OutfitData("BabySky Blue Stripes", "$249.99", R.drawable.dummy_img, false),
                    OutfitData("BabySky Blue Stripes", "$249.99", R.drawable.dummy_img, true),
                    OutfitData("BabySky Blue Stripes", "$249.99", R.drawable.dummy_img, false),
                    OutfitData("BabySky Blue Stripes", "$249.99", R.drawable.dummy_img, false)
                )
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(bottom = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(outfits) { outfit ->
                    OutfitTryCard(outfit = outfit)
                }
            }
        }
    }
}

data class OutfitData(val title: String, val price: String, val imageRes: Int, val isFavorite: Boolean)


@Preview(showBackground = true)
@Composable
fun AiTryScreenPreview() {
    BabyAITheme {
        AiTryScreen(navController = rememberNavController())
    }
}
