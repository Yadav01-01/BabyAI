package com.compose.babyai.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.compose.babyai.R
import com.compose.babyai.navigation.Routes
import com.compose.babyai.ui.component.uiInput.OutfitTryCard
import com.compose.babyai.ui.screens.aiTry.OutfitData

@Composable
fun WishlistScreen(navController: NavHostController) {
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
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.main_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillWidth
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Search Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .size(60.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.draw_back_ic),
                        contentDescription = "Back",
                        tint = Color.Unspecified
                    )
                }

                Text(
                    text = "My Wishlist",
                    fontSize = 22.sp,
                    fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 20.dp)
            ) {

                // Trending Grid
                items(outfits.chunked(2)) { pair ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        OutfitTryCard(
                            modifier = Modifier.weight(1f),
                            onItemClick = { navController.navigate(Routes.ProductDetail.route) },
                            outfit = pair[0]
                        )

                        if (pair.size > 1) {
                            OutfitTryCard(
                                modifier = Modifier.weight(1f),
                                onItemClick = { navController.navigate(Routes.ProductDetail.route) },
                                outfit = pair[1]
                            )
                        } else {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}