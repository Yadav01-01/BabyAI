package com.compose.babyai.ui.screens.aiTry

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
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
fun FullScreenAITryScreen(navController: NavHostController) {
    var selectedColor by remember { mutableStateOf(Color(0xFF4FC3F7)) }

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        // Main Image
        Image(
            painter = painterResource(id = R.drawable.try_dummy), // Replace with actual image
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Top Gradient Overlay
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Black.copy(alpha = 0.6f), Color.Transparent)
                    )
                )
        )

        // Bottom Gradient Overlay
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(250.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f))
                    )
                )
        )

        // Header Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .size(56.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.draw_back_ic),
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

            IconButton(
                onClick = { /* Menu Action */ },
                modifier = Modifier
                    .size(52.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.dot_ic),
                    contentDescription = "Menu",
                    tint = Color.Unspecified
                )
            }
        }

        // Bottom Content
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 20.dp)
                .padding(bottom = 40.dp)
        ) {
            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Remove Button
                Row(
                    modifier = Modifier
                        .width(112.dp)
                        .height(40.dp)
                        .clip(RoundedCornerShape(40.dp))
                        .border(
                            width = 1.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(40.dp)
                        )
                        .clickable { /* Remove Action */ }
                        .padding(horizontal = 16.dp), // remove vertical padding
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.round_cross),
                        contentDescription = "Remove",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = "Remove",
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
                        fontSize = 14.sp, // better fit for 40dp height
                        fontWeight = FontWeight.SemiBold
                    )
                }


                // Buy Now Button
                Button(
                    onClick = { /* Buy Now Action */ },
                    modifier = Modifier
                        .height(40.dp)
                        .width(130.dp),
                    border = BorderStroke(1.dp, Color.White),
                    shape = RoundedCornerShape(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryColor
                    ),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.buy_ic),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Buy Now",
                            color = Color.White,
                            fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Info and Color Selection
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column {
                    Text(
                        text = "BabySky Blue Stripes",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.baloo2_semibold))
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "$24.99",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White,
                            fontFamily = FontFamily(Font(R.font.baloo2_semibold))
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "$54.99",
                            fontSize = 16.sp,
                            color = Color.White.copy(alpha = 0.6f),
                            textDecoration = TextDecoration.LineThrough,
                            fontFamily = FontFamily(Font(R.font.nunito_regular))
                        )
                    }
                }

                // Color Selection Box
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(30.dp))
                        .background(Color.White)
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ColorOptionCircle(color = Color(0xFFBDBDBD), isSelected = selectedColor == Color(0xFFBDBDBD)) { selectedColor = it }
                    ColorOptionCircle(color = Color(0xFF4FC3F7), isSelected = selectedColor == Color(0xFF4FC3F7)) { selectedColor = it }
                    ColorOptionCircle(color = Color(0xFFFFD54F), isSelected = selectedColor == Color(0xFFFFD54F)) { selectedColor = it }
                }
            }
        }
    }
}

@Composable
fun ColorOptionCircle(
    color: Color,
    isSelected: Boolean,
    onClick: (Color) -> Unit
) {
    Box(
        modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(color)
            .border(
                width = if (isSelected) 2.dp else 0.dp,
                color = if (isSelected) PrimaryColor else Color.Transparent,
                shape = CircleShape
            )
            .clickable { onClick(color) }
    )
}

