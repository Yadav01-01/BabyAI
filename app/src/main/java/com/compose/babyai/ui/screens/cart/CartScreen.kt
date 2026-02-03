package com.compose.babyai.ui.screens.cart

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.compose.babyai.R
import com.compose.babyai.ui.theme.PrimaryColor

@Composable
fun CartScreen(navController: NavHostController) {
    val quicksandSemiBold = remember { FontFamily(Font(R.font.quicksand_semibold)) }

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

        Column(modifier = Modifier.fillMaxSize().statusBarsPadding()) {
            // Header
            Text(
                text = "Cart",
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = quicksandSemiBold,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
                color = Color.Black
            )

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Shipping Address
                item {
                    ShippingAddressSection(onEditClick = {  })
                }

                // Cart Items
                items(getDummyCartItems()) { item ->
                    CartItemCard(item)
                }

                // Related Outfit Header
                item {
                    Column(modifier = Modifier.padding(top = 8.dp, start = 10.dp, end = 10.dp)) {
                        Text(
                            text = "Related Outfit",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                            color = Color.Black
                        )
                        Text(
                            text = "AI Suggests: 6-9M based on your baby's profile",
                            fontSize = 14.sp,
                            color = Color(0xFF828282),
                            fontFamily = FontFamily(Font(R.font.varela_round))
                        )
                    }
                }

                // Related Outfits Horizontal List
                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(bottom = 100.dp) // Space for bottom button
                    ) {
                        items(getDummyRelatedItems()) { item ->
                            RelatedOutfitItem(item, quicksandSemiBold)
                        }
                    }
                }
            }
        }
    }



     /*   // Checkout Button - Fixed at bottom
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color.White.copy(alpha = 0.9f))
                .padding(20.dp)
                .navigationBarsPadding()
        ) {
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)
            ) {
                Text(
                    text = "Checkout & Pay",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = balooBold,
                    color = Color.White
                )
            }
        }*/

}

@Composable
fun ShippingAddressSection( onEditClick: () -> Unit ) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Shipping Address",
                    fontSize = 18.sp,
                    color = Color(0xFF828282),
                    fontFamily = FontFamily(Font(R.font.quicksand_semibold))
                )
                Text(
                    text = "26, Duong So 2, Thao Dien Wa...",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.varela_round)),
                    color = Color(0xFF1C1C1C),
                )
            }
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(PrimaryColor)
                    .clickable { onEditClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.edit_ic), // Ensure this exists or use Icons.Default.Edit
                    contentDescription = "Edit",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(48.dp)
                )
            }
        }
    }
}

@Composable
fun CartItemCard(item: CartItem) {
    Card(
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
                    .height(125.dp).width(150.dp)
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    // 🔹 Price Section (Left)
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

                    // 🔹 Quantity Stepper (Right)
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFE0F7F7))
                                .clickable { /* decrease */ },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.minus_ic),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )
                        }

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = "${item.quantity}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(PrimaryColor)
                                .clickable { /* increase */ },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.plus_ic),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun RelatedOutfitItem(item: RelatedItem, quicksandBold: FontFamily) {
    Card(
        modifier = Modifier.width(170.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Box {
                Image(
                    painter = painterResource(id = item.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(215.dp)
                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
                    contentScale = ContentScale.Crop
                )
                // Size tag
                Surface(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(8.dp),
                    shape = RoundedCornerShape(8.dp),
                    color = Color.White.copy(alpha = 0.9f)
                ) {
                    Text(
                        text = item.size,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = quicksandBold,
                        color = Color(0xFF272727)
                    )
                }
                
                // Favorite overlay
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.7f))
                        .clickable { },
                    contentAlignment = Alignment.Center
                ) {
                   Icon(
                       painter = painterResource(id = R.drawable.fav_item),
                       contentDescription = null,
                       tint = Color.Unspecified,
                       modifier = Modifier.size(18.dp)
                   )
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = item.title,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = quicksandBold,
                color = Color(0xFF272727),
                maxLines = 1
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "$${item.price}",
                            fontSize = 14.sp,
                            color = PrimaryColor,
                            fontWeight = FontWeight.Bold,
                            fontFamily = quicksandBold
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "$${item.originalPrice}",
                            fontSize = 10.sp,
                            color = Color(0xFFBDBDBD),
                            textDecoration = TextDecoration.LineThrough,
                            fontFamily = quicksandBold
                        )
                    }
                }
                
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(PrimaryColor)
                        .clickable { },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.cart_ic),
                        contentDescription = "Add to cart",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

data class CartItem(
    val title: String,
    val color: String,
    val size: String,
    val price: String,
    val originalPrice: String,
    val quantity: Int,
    val imageRes: Int
)

data class RelatedItem(
    val title: String,
    val size: String,
    val price: String,
    val originalPrice: String,
    val imageRes: Int
)

fun getDummyCartItems() = listOf(
    CartItem("BabyStyle Co.Adorable Pink Polka Dot Dress", "Pink", "XS", "24.99", "34.99", 1, R.drawable.dummy_img),
    CartItem("BabyStyle Co.Adorable Pink Polka Dot Dress", "Pink", "XS", "24.99", "34.99", 1, R.drawable.dummy_img)
)

fun getDummyRelatedItems() = listOf(
    RelatedItem("BabySky Blue Stripes", "6-9M", "24.99", "34.99", R.drawable.dummy_img),
    RelatedItem("BabySky Blue Stripes", "3-6M", "24.99", "34.99", R.drawable.dummy_img),
    RelatedItem("BabySky Blue Stripes", "9-12M", "29.99", "39.99", R.drawable.dummy_img)
)
