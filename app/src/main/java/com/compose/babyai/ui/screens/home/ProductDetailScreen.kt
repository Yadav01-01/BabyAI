package com.compose.babyai.ui.screens.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.compose.babyai.R
import com.compose.babyai.ui.component.AgeBottomSheet
import com.compose.babyai.ui.component.DetailHeading
import com.compose.babyai.ui.component.ShareBottomSheet
import com.compose.babyai.ui.theme.PrimaryColor

@Composable
fun ProductDetailScreen(navController: NavHostController) {

    var selectedSize by remember { mutableStateOf("6-9 M") }
    var selectedColor by remember { mutableStateOf(Color(0xFF8EBAE5)) }
    var shareDialog by remember { mutableStateOf(false) }
    var ageDialog by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        // ───────────── IMAGE HEADER ─────────────
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.dummy_img),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                // Top actions
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {

                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(R.drawable.draw_back_ic),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(60.dp)
                        )
                    }

                    Column(horizontalAlignment = Alignment.End) {
                        IconButton(onClick = { }) {
                            Icon(
                                painter = painterResource(R.drawable.trans_fac_ic),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(52.dp)
                            )
                        }

                        Spacer(Modifier.height(8.dp))

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(53.dp))
                                .background(Color.White.copy(alpha = 0.8f))
                                .padding(2.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(R.drawable.left_arrow),
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(Modifier.width(4.dp))
                                Image(
                                    painter = painterResource(R.drawable.onb1),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(44.dp)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }
                }

                // AI Try button (scrolls with content)
                Button(
                    onClick = { },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .offset(y = 28.dp)
                        .height(56.dp)
                        .width(260.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    shape = RoundedCornerShape(28.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painterResource(R.drawable.ai_star),
                            null,
                            tint = PrimaryColor,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text("Try Now (AI Try-On)", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        // spacing after image
        item { Spacer(Modifier.height(40.dp)) }

        // ───────────── DETAILS ─────────────
        item {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Cute Baby mini OutFit",
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                        Text(
                            text = "Perfect comfort for your little one",
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.nunito_regular)),
                            fontWeight = FontWeight.Normal,
                            color = Color(0XFFB0B0B0)
                        )
                    }
                    IconButton(onClick = { shareDialog = true }) {
                        Icon(
                            painter = painterResource(R.drawable.share_ic),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(48.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "$249.99",
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
                        fontWeight = FontWeight.SemiBold,
                        color = PrimaryColor
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "$54.99",
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.outfit_regular)),
                        fontWeight = FontWeight.Normal,
                        color = Color(0XFFB0B0B0),
                        textDecoration = TextDecoration.LineThrough
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Select Size
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Select Size",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.baloo2_medium)),
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(40.dp))
                            .background(Color.Transparent)
                            .border(1.dp, PrimaryColor, RoundedCornerShape(40.dp))
                            .padding(horizontal = 16.dp, vertical = 6.dp)
                            .clickable{ ageDialog = true }
                    ) {
                        Text(text = selectedSize,
                            color = Color.Black,
                            fontFamily = FontFamily(Font(R.font.nunito_regular)),
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ai_yellow_ic),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Black,
                                    fontWeight = FontWeight.Normal
                                )
                            ) {
                                append("AI Suggests: ")
                            }

                            withStyle(
                                style = SpanStyle(
                                    color = Color(0XFFB0B0B0),
                                    fontWeight = FontWeight.Normal
                                )
                            ) {
                                append("6–9M based on your baby's profile")
                            }
                        },
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.nunito_regular))
                    )

                }

                Spacer(modifier = Modifier.height(16.dp))
                DashedDivider(color = PrimaryColor)
                Spacer(modifier = Modifier.height(16.dp))

                // Color Selection
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DetailHeading("Color")

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        ColorCircle(Color(0xFF8EBAE5), selectedColor == Color(0xFF8EBAE5)) { selectedColor = it }
                        ColorCircle(Color(0xFFFFE178), selectedColor == Color(0xFFFFE178)) { selectedColor = it }
                        ColorCircle(Color(0xFFFF4848), selectedColor == Color(0xFFFF4848)) { selectedColor = it }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                DashedDivider(color = PrimaryColor)
                Spacer(modifier = Modifier.height(16.dp))

                // Switch Baby Profiles
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DetailHeading("Switch Baby Profiles")
                    AvatarPreviewRow(
                        images = listOf(
                            R.drawable.onb1,
                            R.drawable.onb2,
                            R.drawable.onb3,
                            R.drawable.onb1
                        )
                    )

                }

                Spacer(modifier = Modifier.height(16.dp))
                DashedDivider(color = PrimaryColor)
                Spacer(modifier = Modifier.height(16.dp))

                // Materials
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    DetailHeading("Materials")
                    Text("Cotton, Polyester",
                        color = Color(0XFFB0B0B0),
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.varela_round)),
                        fontWeight = FontWeight.Normal
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                DashedDivider(color = PrimaryColor)
                Spacer(modifier = Modifier.height(16.dp))

                // Description
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {

                    // Left
                    DetailHeading("Description")

                    // Right
                    Text(
                        text = "Soft and comfortable dress with cute polka dots. Perfect for everyday wear and special occasions. Machine washable.",
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 12.dp),
                        color = Color(0xFFB0B0B0),
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.nunito_regular)),
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.End,
                        maxLines = 6
                    )
                }

            }
        }

        // ───────────── CTA BUTTONS (SCROLLABLE) ─────────────
        item {
            Column(modifier = Modifier.padding(18.dp)) {

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    OutlinedButton(
                        onClick = {},
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        shape = RoundedCornerShape(28.dp),
                        border = BorderStroke(1.dp, Color.Black)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.cart_btn),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(28.dp)
                            )

                            Spacer(modifier = Modifier.width(5.dp))

                            Text(
                                text = "Add to Cart",
                                fontFamily = FontFamily(Font(R.font.baloo2_medium)),
                                color = Color.Black,
                                fontWeight = FontWeight.Medium,
                                fontSize = 18.sp
                            )
                        }
                    }


                    Button(
                        onClick = {},
                        modifier = Modifier.weight(1f).height(56.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFBD606))
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.buy_ic),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(28.dp)
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = "Buy Now",
                                fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
                                color = Color.Black,
                                fontWeight = FontWeight.Medium,
                                fontSize = 18.sp
                            )
                        }
                    }
                }

                Spacer(Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .clip(RoundedCornerShape(40.dp))
                        .background(PrimaryColor)
                        .padding(4.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text("Slide to add in Fitting room", color = Color.White, fontWeight = FontWeight.Bold)
                    }

                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(painterResource(R.drawable.slide_ic),
                            null,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(52.dp))
                    }

                    // Add Chevron arrows
                    Row(modifier = Modifier.padding(start = 70.dp)) {
                        repeat(3) {
                            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, tint = Color.White.copy(alpha = 0.5f), modifier = Modifier.size(16.dp))
                        }
                    }
                }

                Spacer(Modifier.height(32.dp))
            }
        }
    }
    if (shareDialog) {
        ShareBottomSheet(onDismiss = { shareDialog = false }, modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 20.dp))
    }
    if (ageDialog) {
        AgeBottomSheet(onDismiss = { ageDialog = false }, selectedAge = "0-3 Months", onAgeSelect = { }, onNextClick = { },modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 20.dp))
    }

}





@Composable
fun AvatarPreviewRow(
    images: List<Int>, // later: List<String> (URLs)
    maxVisible: Int = 2,
    modifier: Modifier = Modifier
) {
    val visibleImages = images.take(maxVisible)
    val remainingCount = images.size - maxVisible

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = painterResource(R.drawable.left_arrow),
            contentDescription = null,
            modifier = Modifier.size(14.dp)
        )

        Spacer(modifier = Modifier.width(6.dp))

        visibleImages.forEachIndexed { index, imageRes ->
            Image(
                painter = painterResource(imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .border(
                        width = if (index == 0) 1.dp else 0.dp,
                        color = Color.LightGray,
                        shape = CircleShape
                    ),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(8.dp))
        }

        if (remainingCount > 0) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE9FAFA))
                    .border(1.dp, PrimaryColor, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "+$remainingCount",
                    color = PrimaryColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}


@Composable
fun ColorCircle(color: Color, isSelected: Boolean, onClick: (Color) -> Unit) {
    Box(
        modifier = Modifier
            .size(26.dp)
            .clip(CircleShape)
            .background(color)
            .border(
                width = 1.dp,
                color = if (isSelected) Color.Black else Color.Transparent,
                shape = CircleShape
            )
            .then(
                if (isSelected) Modifier.padding(2.dp).border(1.dp, Color.Black, CircleShape) else Modifier
            )
            .clickable { onClick(color) }
    )
}

@Composable
fun DashedDivider(color: Color) {
    Canvas(modifier = Modifier.fillMaxWidth().height(1.dp)) {
        drawLine(
            color = color,
            start = androidx.compose.ui.geometry.Offset(0f, 0f),
            end = androidx.compose.ui.geometry.Offset(size.width, 0f),
            pathEffect = androidx.compose.ui.graphics.PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
        )
    }
}
