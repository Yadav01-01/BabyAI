package com.compose.babyai.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
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
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.navigation.NavHostController
import com.compose.babyai.R
import com.compose.babyai.data.model.BabyProfile
import com.compose.babyai.navigation.Routes
import com.compose.babyai.ui.component.dialog.AgeBottomSheet
import com.compose.babyai.ui.component.uiInput.DetailHeading
import com.compose.babyai.ui.component.dialog.ShareBottomSheet
import com.compose.babyai.ui.theme.PrimaryColor
import com.compose.babyai.util.BottomCurveShape
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@Composable
fun ProductDetailScreen(navController: NavHostController) {

    var selectedSize by remember { mutableStateOf("6-9 M") }
    var selectedColor by remember { mutableStateOf(Color(0xFF8EBAE5)) }
    var shareDialog by remember { mutableStateOf(false) }
    var ageDialog by remember { mutableStateOf(false) }
    var isFavorite by remember { mutableStateOf(false) }
    val babies = listOf(
        BabyProfile(1, R.drawable.onb1),
        BabyProfile(2, R.drawable.onb2),
        BabyProfile(3, R.drawable.onb3)
    )

    var selectedBaby by remember { mutableStateOf(babies.first()) }
    var expanded by remember { mutableStateOf(false) }
    var isTried by remember { mutableStateOf(false) }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
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
                        .height(500.dp)  // ← Reduced height
                ) {
                    // Background with curved shape
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(BottomCurveShape())
                            .background(Color(0xFFFFD6C2))
                            .drawBehind {
                                val strokeWidth = 4.dp.toPx()

                                val path = Path().apply {
                                    moveTo(0f, size.height - 80f)

                                    quadraticBezierTo(
                                        size.width / 2,
                                        size.height + 40f,
                                        size.width,
                                        size.height - 80f
                                    )
                                }

                                drawPath(
                                    path = path,
                                    color = Color.Black,
                                    style = Stroke(width = strokeWidth)
                                )
                            }
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
                                .padding(horizontal = 20.dp, vertical = 16.dp),
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
                                IconButton(onClick = { isFavorite = !isFavorite }) {
                                    Icon(
                                        painter = painterResource(
                                            if (isFavorite) R.drawable.trans_fac_ic
                                            else R.drawable.fav_item
                                        ),
                                        contentDescription = null,
                                        tint = Color.Unspecified,
                                        modifier = Modifier.size(52.dp)
                                    )
                                }

                                Spacer(Modifier.height(8.dp))

                                // Profile Container
                                Row(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(22.dp))
                                        .background(Color.White.copy(alpha = 0.9f))
                                        .padding(horizontal = 10.dp, vertical = 6.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    // Arrow
                                    Icon(
                                        painter = painterResource(id = R.drawable.left_arrow),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(16.dp)
                                            .clickable { expanded = !expanded },
                                        tint = Color.Unspecified
                                    )

                                    Spacer(modifier = Modifier.width(6.dp))

                                    // Selected Profile
                                    Image(
                                        painter = painterResource(id = selectedBaby.imageRes),  // ← Changed
                                        contentDescription = "Profile",
                                        modifier = Modifier
                                            .size(36.dp)
                                            .clip(CircleShape)
                                            .clickable { expanded = !expanded },
                                        contentScale = ContentScale.Crop
                                    )

                                    // INLINE EXPANDING LIST
                                    AnimatedVisibility(
                                        visible = expanded,
                                        enter = expandHorizontally() + fadeIn(),
                                        exit = shrinkHorizontally() + fadeOut()
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.padding(start = 6.dp)
                                        ) {
                                            babies  // ← Changed
                                                .filter { it.id != selectedBaby.id }  // ← Changed
                                                .take(3)
                                                .forEach { baby ->
                                                    Image(
                                                        painter = painterResource(id = baby.imageRes),
                                                        contentDescription = null,
                                                        modifier = Modifier
                                                            .padding(start = 6.dp)
                                                            .size(32.dp)
                                                            .clip(CircleShape)
                                                            .border(
                                                                1.dp,
                                                                Color.LightGray,
                                                                CircleShape
                                                            )
                                                            .clickable {
                                                                selectedBaby = baby  // ← Changed
                                                                expanded = false
                                                            },
                                                        contentScale = ContentScale.Crop
                                                    )
                                                }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // Button positioned at bottom center, overlapping the curved edge
                    Button(
                        onClick = { navController.navigate(Routes.AiTry.route)},
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .offset(y = 10.dp)
                            .height(56.dp)
                            .width(260.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = if (isTried) PrimaryColor else Color.Black),
                        shape = RoundedCornerShape(28.dp),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painterResource(if (isTried) R.drawable.humbleicons_ai else R.drawable.green_star),
                                null,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = if (isTried) "View in Fitting Room" else "Try Now (AI Try-On)",
                                color = Color.White,
                                fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp
                            )
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
                        modifier = Modifier
                            .clip(RoundedCornerShape(22.dp))
                            .background(Color.White.copy(alpha = 0.9f))
                            .padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Arrow
                        Icon(
                            painter = painterResource(id = R.drawable.left_arrow),
                            contentDescription = null,
                            modifier = Modifier
                                .size(16.dp)
                                .clickable { expanded = !expanded },
                            tint = Color.Unspecified
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        // Selected Profile
                        Image(
                            painter = painterResource(id = selectedBaby.imageRes),  // ← Changed
                            contentDescription = "Profile",
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .clickable { expanded = !expanded },
                            contentScale = ContentScale.Crop
                        )

                        // INLINE EXPANDING LIST
                        AnimatedVisibility(
                            visible = expanded,
                            enter = expandHorizontally() + fadeIn(),
                            exit = shrinkHorizontally() + fadeOut()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(start = 6.dp)
                            ) {
                                babies  // ← Changed
                                    .filter { it.id != selectedBaby.id }  // ← Changed
                                    .take(3)
                                    .forEach { baby ->
                                        Image(
                                            painter = painterResource(id = baby.imageRes),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .padding(start = 6.dp)
                                                .size(32.dp)
                                                .clip(CircleShape)
                                                .border(
                                                    1.dp,
                                                    Color.LightGray,
                                                    CircleShape
                                                )
                                                .clickable {
                                                    selectedBaby = baby  // ← Changed
                                                    expanded = false
                                                },
                                            contentScale = ContentScale.Crop
                                        )
                                    }
                            }
                        }
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

                    Spacer(Modifier.height(200.dp))

                }
            }
        }

        Column(modifier = Modifier
            .align(Alignment.BottomCenter)
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 15.dp)) {

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
            var offsetX by remember { mutableFloatStateOf(0f) }
            var isCompleted by remember { mutableStateOf(false) }
            var maxDragDistance by remember { mutableFloatStateOf(0f) }
            val density = LocalDensity.current

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .clip(RoundedCornerShape(40.dp))
                    .background(PrimaryColor)
                    .padding(4.dp)
                    .onGloballyPositioned { coordinates ->
                        // Calculate max drag distance once layout is measured
                        with(density) {
                            maxDragDistance = coordinates.size.width.toFloat() - 56.dp.toPx() - 8.dp.toPx()
                        }
                    },
                contentAlignment = Alignment.CenterStart
            ) {
                // Text with fade out effect as slider moves
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp)
                        .alpha(1f - (offsetX / 200f).coerceIn(0f, 1f)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.slide_arr),
                        tint = Color.Unspecified,
                        contentDescription = null,
                        modifier = Modifier.height(22.dp).width(29.dp).padding(start = 15.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "Slide to add in Fitting room",
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                }

                // Draggable slider button
                Box(
                    modifier = Modifier
                        .offset { IntOffset(offsetX.roundToInt(), 0) }
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .pointerInput(Unit) {
                            detectHorizontalDragGestures(
                                onDragEnd = {
                                    if (offsetX >= maxDragDistance * 0.8f) {
                                        // Completed - snap to end
                                        offsetX = maxDragDistance
                                        isCompleted = true

                                        // TODO: Add to fitting room action here
                                        // onAddToFittingRoom()

                                    } else {
                                        // Not completed - animate back to start
                                        offsetX = 0f
                                    }
                                },
                                onHorizontalDrag = { change, dragAmount ->
                                    change.consume()
                                    offsetX = (offsetX + dragAmount).coerceIn(0f, maxDragDistance)
                                }
                            )
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painterResource(R.drawable.slideiiic),
                        null,
                        tint = Color.Unspecified,
                    )
                }
            }

// Success state - show after completion
            if (isCompleted) {
                LaunchedEffect(Unit) {
                    delay(300)
                    // Reset or navigate
                    // isCompleted = false
                    // offsetX = 0f
                }
            }

            Spacer(Modifier.height(5.dp))
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
