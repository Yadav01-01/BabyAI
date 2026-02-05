package com.compose.babyai.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.compose.babyai.R
import com.compose.babyai.ui.screens.aiTry.OutfitData
import com.compose.babyai.ui.theme.PrimaryColor

@Composable
fun OutfitTryCard(outfit: OutfitData,modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(bottom = 20.dp)
            ) {
                Image(
                    painter = painterResource(id = outfit.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(215.dp)
                        .clip(RoundedCornerShape(25.dp)),
                    contentScale = ContentScale.Crop
                )

                IconButton(
                    onClick = { },
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.fav_item),
                        contentDescription = "Favorite",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(38.dp)
                    )
                }

                // AI Try-on button
                Card(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .offset(y = 25.dp)
                        .size(50.dp)
                        .zIndex(1f),
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Icon(
                            painter = painterResource(id = R.drawable.try_angle),
                            contentDescription = "Try on",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(50.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = outfit.title,
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1C1C1C),
                textAlign = TextAlign.Center
            )

            Text(
                text = outfit.price,
                fontSize = 14.sp,
                color = PrimaryColor,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
fun AiTryHeader(onClickProfile: () -> Unit = {}, onClickScan: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Fitting room",
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
            color = Color.Black
        )

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(22.dp))
                .clickable { onClickProfile() }
                .background(Color.White)
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Back Arrow
            Icon(
                painter = painterResource(id = R.drawable.left_arrow),
                contentDescription = "Back",
                modifier = Modifier.size(16.dp),
                tint = Color.Unspecified
            )

            Spacer(modifier = Modifier.width(6.dp))

            // Profile Image
            Image(
                painter = painterResource(id = R.drawable.onb1),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(6.dp))

            // Scan Icon
            IconButton(
                onClick = { onClickScan() },
                modifier = Modifier.size(46.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.scan_ic),
                    contentDescription = "Scan",
                    tint = Color.Unspecified
                )
            }
        }
    }
}
