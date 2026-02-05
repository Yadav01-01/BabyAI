package com.compose.babyai.ui.screens.wardrobe

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.compose.babyai.R
import com.compose.babyai.data.model.BuyAgainItem
import com.compose.babyai.ui.component.closest.PreviousBoughtCard
import com.compose.babyai.ui.component.closest.SuggestedOutfit
import com.compose.babyai.ui.screens.aiTry.OutfitData
import com.compose.babyai.ui.screens.cart.CartItemCard
import com.compose.babyai.ui.screens.cart.RelatedOutfitItem
import com.compose.babyai.ui.screens.cart.getDummyCartItems
import com.compose.babyai.ui.screens.cart.getDummyRelatedItems
import com.compose.babyai.ui.theme.PrimaryColor

@Composable
fun WardrobeScreen(navController: NavHostController) {

    val buyAgainList = listOf(BuyAgainItem(imageRes = R.drawable.dummy_img, title = "BabySky Blue Stripes", price = "$249.99"),
        BuyAgainItem(imageRes = R.drawable.dummy_img, title = "BabySky Blue Stripes", price = "$249.99"),
        BuyAgainItem(imageRes = R.drawable.dummy_img, title = "BabySky Blue Stripes", price = "$234"),
        BuyAgainItem(imageRes = R.drawable.dummy_img, title = "BabySky Blue Stripes", price = "$234"),
    )
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

    Box(modifier = Modifier.fillMaxSize()) {

        // Background
        Image(
            painter = painterResource(id = R.drawable.main_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .statusBarsPadding()
        ) {

            // Header
            Text(
                text = stringResource(R.string.My_Closet),
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
                color = Color.Black,
                modifier = Modifier.padding( vertical = 12.dp)
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    bottom = 20.dp //  normal padding only
                ),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                item {
                    Text(
                        text = stringResource(R.string.baby_wardrobe_collection),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                        color = Color.Black,
                    )

                    Text(
                        text = stringResource(R.string.Previously_Bought),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily(Font(R.font.nunito_regular)),
                        color = Color(0xFFB0B0B0),
                    )
                }

                items(outfits.chunked(2)) { pair ->

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        // Left card
                        PreviousBoughtCard(
                            modifier = Modifier.weight(1f),
                            outfit = pair[0],
                            onBuyAgainClick = {
                                // handle buy again
                            },
                            onFavClick = {
                                // handle fav click
                            }
                        )

                        // Right card (if exists)
                        if (pair.size > 1) {
                            PreviousBoughtCard(
                                modifier = Modifier.weight(1f),
                                outfit = pair[1],
                                onBuyAgainClick = {
                                    // handle buy again
                                },
                                onFavClick = {
                                    // handle fav click
                                }
                            )
                        } else {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    Text(
                        text = stringResource(R.string.suggested_outfit),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                        color = Color.Black,
                    )
                }

                items(getDummyCartItems()) { item ->
                    SuggestedOutfit(item)
                }

                item {
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
    }
}

