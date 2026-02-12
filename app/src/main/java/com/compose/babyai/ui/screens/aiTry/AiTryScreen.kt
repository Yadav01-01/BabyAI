package com.compose.babyai.ui.screens.aiTry

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.compose.babyai.R
import com.compose.babyai.data.model.AiTryResultItem
import com.compose.babyai.data.model.BabyProfile
import com.compose.babyai.navigation.Routes
import com.compose.babyai.ui.component.uiInput.AiTryHeader
import com.compose.babyai.ui.theme.BabyAITheme

@Composable
fun AiTryScreen(navController: NavHostController) {
    val babies = listOf(
        BabyProfile(1, R.drawable.onb1),
        BabyProfile(2, R.drawable.onb2),
        BabyProfile(3, R.drawable.onb3)
    )
    var selectedBaby by remember { mutableStateOf(babies.first()) }

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
                .padding(horizontal = 20.dp)
        ) {

            AiTryHeader(
                babyProfiles = babies,
                selectedProfile = selectedBaby,
                onProfileSelected = { selectedBaby = it },
                onClickScan = { navController.navigate(Routes.AiScan.route) }
            )


            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = stringResource(R.string.Selected_Outfits),
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

            val aiTryResultList = listOf(
                AiTryResultItem(
                    title = "Baby Sky Blue Stripes",
                    price = "24.99",
                    originalPrice = "54.99",
                    imageRes = R.drawable.try_dummy
                ),
                AiTryResultItem(
                    title = "Pink Polka Dot Dress",
                    price = "19.99",
                    originalPrice = "49.99",
                    imageRes = R.drawable.try_dummy
                ),
                AiTryResultItem(
                    title = "Yellow Floral Dress",
                    price = "29.99",
                    originalPrice = "59.99",
                    imageRes = R.drawable.try_dummy
                )
            )

            LazyColumn(
                contentPadding = PaddingValues(bottom = 20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(aiTryResultList) { item ->
                    AiTryResultCard(
                        title = item.title,
                        price = item.price,
                        originalPrice = item.originalPrice,
                        imageRes = item.imageRes,
                        onClickImage = { navController.navigate(Routes.AiFullScreenTry.route) }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
    }
}

@Composable
fun AiTryResultCard(
    title: String,
    price: String,
    originalPrice: String,
    imageRes: Int,
    modifier: Modifier = Modifier,
    onClickImage:() -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(30.dp)),
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(650.dp)
                    .background(Color(0xFFF48FB1)) // Pinkish background from image
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize().clickable{ onClickImage() },
                    contentScale = ContentScale.Crop
                )

                // Top Buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Remove Button
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(40.dp))
                            .border(1.dp, Color.White, RoundedCornerShape(40.dp))
                            .background(Color.Transparent)
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                            .clickable { },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.round_cross),
                            contentDescription = "Remove",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Remove",
                            color = Color.White,
                            fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    // Right Icons
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        IconButton(
                            onClick = { },
                            modifier = Modifier
                                .size(48.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable._60_cam),
                                contentDescription = "Focus",
                                tint = Color.Unspecified,
                            )
                        }

                        IconButton(
                            onClick = { },
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(Color.White)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.download_ic), // Reusing share as download placeholder
                                contentDescription = "Download",
                                tint = Color.Unspecified,
                            )
                        }
                    }
                }

                // Color Selector overlay at bottom
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 24.dp)
                        .clip(RoundedCornerShape(25.dp))
                        .background(Color.White.copy(alpha = 0.3f))
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ColorDot(color = Color(0xFFFFE0B2))
                        ColorDot(color = Color(0xFF4FC3F7), isSelected = true)
                        ColorDot(color = Color(0xFFFFF176))
                    }
                }
            }

            // Bottom Content
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                        fontFamily = FontFamily(Font(R.font.baloo2_semibold))
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "$$price",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            fontFamily = FontFamily(Font(R.font.baloo2_semibold))
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "$$originalPrice",
                            fontSize = 16.sp,
                            color = Color(0XFF828282),
                            textDecoration = TextDecoration.LineThrough,
                            fontFamily = FontFamily(Font(R.font.nunito_regular))
                        )
                    }
                }

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    IconButton(
                        onClick = { },
                        modifier = Modifier
                            .size(48.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.yellow_ic),
                            contentDescription = "Favorite",
                            tint = Color.Unspecified,
                        )
                    }

                    IconButton(
                        onClick = { },
                        modifier = Modifier
                            .size(48.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.share_ic),
                            contentDescription = "Share",
                            tint = Color.Unspecified,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ColorDot(color: Color, isSelected: Boolean = false) {
    Box(
        modifier = Modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(color)
            .then(
                if (isSelected) Modifier.border(2.dp, Color.White, CircleShape)
                else Modifier
            )
    )
}

data class OutfitData(val title: String, val price: String, val imageRes: Int, val isFavorite: Boolean)


@Preview(showBackground = true)
@Composable
fun AiTryScreenPreview() {
    BabyAITheme {
        AiTryScreen(navController = rememberNavController())
    }
}
