package com.compose.babyai.ui.component.closest

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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.compose.babyai.R
import com.compose.babyai.ui.screens.aiTry.OutfitData
import com.compose.babyai.ui.screens.cart.CartItem
import com.compose.babyai.ui.theme.PrimaryColor

@Composable
fun SuggestedOutfit(item: CartItem,onClickItem: () -> Unit) {
    Card(
        onClick = onClickItem,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1C1C1C),
                    fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                    maxLines = 2
                )
                Text(
                    text = "Color: ${item.color}",
                    fontSize = 12.sp,
                    color = Color.Black,
                    fontFamily =  FontFamily(Font(R.font.outfit_regular))
                )
                Text(
                    text = "Size: ${item.size}",
                    fontSize = 12.sp,
                    color = Color.Black,
                    fontFamily =  FontFamily(Font(R.font.outfit_regular))
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "$${item.price}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = PrimaryColor,
                        fontFamily = FontFamily(Font(R.font.quicksand_semibold))
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "$${item.originalPrice}",
                        fontSize = 10.sp,
                        color = Color(0xFF828282),
                        textDecoration = TextDecoration.LineThrough,
                        fontFamily = FontFamily(Font(R.font.outfit_regular))
                    )
                }

            }
        }
    }
}

@Composable
fun PreviousBoughtCard(outfit: OutfitData,modifier: Modifier = Modifier,onItemClick: () -> Unit,onBuyAgainClick: () -> Unit,onFavClick: () -> Unit) {
    Card(
        onClick = onItemClick,
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
                        .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)),
                    contentScale = ContentScale.Crop
                )

                // Buy Again button
                Card(
                    onClick = {
                        onBuyAgainClick()
                    },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .offset(y = 15.dp)
                        .height(32.dp)
                        .width(110.dp)
                        .zIndex(1f),
                    shape = RoundedCornerShape(92.dp),
                    colors = CardDefaults.cardColors(containerColor = PrimaryColor),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.buy_ic),
                            contentDescription = "Buy Now",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(22.dp)
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = "Buy Again",
                            fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                            color = Color.White,
                            fontWeight = FontWeight.Medium,
                            fontSize = 13.sp
                        )
                    }
                }

            }

            Spacer(modifier = Modifier.height(5.dp))

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