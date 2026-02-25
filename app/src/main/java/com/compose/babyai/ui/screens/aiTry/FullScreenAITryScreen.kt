package com.compose.babyai.ui.screens.aiTry


import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.compose.babyai.R
import com.compose.babyai.navigation.Routes
import com.compose.babyai.ui.component.uiInput.IconCircleButton
import com.compose.babyai.ui.theme.BabyAITheme
import com.compose.babyai.ui.theme.PrimaryColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@Composable
fun FullScreenAITryScreen(navController: NavHostController) {
    val context = LocalContext.current
    // Sample data
    val outfits = remember {
        listOf(
            OutfitItem(
                id = "1",
                title = "BabySky Blue Stripes",
                price = "24.99",
                originalPrice = "54.99",
                imageRes = R.drawable.try_dummy,
                availableColors = listOf(
                    Color(0xFFBDBDBD),
                    Color(0xFF4FC3F7),
                    Color(0xFFFFD54F)
                ),
                isFavorite = false,
                discountPercentage = 54
            ),
            OutfitItem(
                id = "2",
                title = "Pink Polka Dot Dress",
                price = "19.99",
                originalPrice = "49.99",
                imageRes = R.drawable.try_dummy,
                availableColors = listOf(
                    Color(0xFFE91E63),
                    Color(0xFFFFFFFF),
                    Color(0xFF9C27B0)
                ),
                isFavorite = true,
                discountPercentage = 60
            ),
            OutfitItem(
                id = "3",
                title = "Yellow Floral Dress",
                price = "29.99",
                originalPrice = "59.99",
                imageRes = R.drawable.try_dummy,
                availableColors = listOf(
                    Color(0xFFFFEB3B),
                    Color(0xFFFF9800),
                    Color(0xFF8BC34A)
                ),
                isFavorite = false,
                discountPercentage = 50
            )
        )
    }

    var showCartSuccess by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        FullScreenReelContent(
            outfits = outfits,
            onBack = { navController.popBackStack() },
            onAddToCart = { outfitId ->
                showCartSuccess = true
                coroutineScope.launch {
                    delay(2000)
                    showCartSuccess = false
                }
                // TODO: Add to cart logic
            },
            onRemove = { outfitId ->
                // TODO: Remove outfit logic
                navController.popBackStack()
            },
            onBuyNow = { outfitId ->
                 navController.navigate(Routes.Payment.route)
            },
            onColorSelected = { outfitId, color ->
                // TODO: Update selected color
            },
            onShare = { outfitId ->
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, "Hey! Check this out 🚀")
                }

                context.startActivity(
                    Intent.createChooser(intent, "Share via")
                )
            },
            onDownload = { outfitId ->
                // TODO: Download image
            },
            onToggleFavorite = { outfitId ->
                // TODO: Toggle favorite
            }
        )

        // Cart success toast
        AnimatedVisibility(
            visible = showCartSuccess,
            enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 200.dp)
        ) {
            SuccessToast(
                message = "Added to the cart",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
private fun FullScreenReelContent(
    outfits: List<OutfitItem>,
    onBack: () -> Unit,
    onAddToCart: (String) -> Unit,
    onRemove: (String) -> Unit,
    onBuyNow: (String) -> Unit,
    onColorSelected: (String, Color) -> Unit,
    onShare: (String) -> Unit,
    onDownload: (String) -> Unit,
    onToggleFavorite: (String) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { outfits.size })
    val haptic = LocalHapticFeedback.current

    // Track current page for analytics
    LaunchedEffect(pagerState.currentPage) {
        snapshotFlow { pagerState.currentPage }
            .distinctUntilChanged()
            .collect { page ->
                haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                // Analytics: Track outfit view
            }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Vertical Pager
        VerticalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            key = { outfits[it].id }
        ) { page ->
            val outfit = outfits[page]

            FullScreenReelCard(
                outfit = outfit,
                onAddToCart = { onAddToCart(outfit.id) },
                onRemove = { onRemove(outfit.id) },
                onBuyNow = { onBuyNow(outfit.id) },
                onColorSelected = { color -> onColorSelected(outfit.id, color) },
                onShare = { onShare(outfit.id) },
                onDownload = { onDownload(outfit.id) },
                onBackPress = { onBack() },
                currentPage = page,
                totalPages = outfits.size
            )
        }
    }
}

@Composable
private fun FullScreenReelCard(
    outfit: OutfitItem,
    onAddToCart: () -> Unit,
    onRemove: () -> Unit,
    onBuyNow: () -> Unit,
    onColorSelected: (Color) -> Unit,
    onShare: () -> Unit,
    onDownload: () -> Unit,
    onBackPress: () -> Unit,
    currentPage: Int,
    totalPages: Int
) {
    var selectedColor by remember { mutableStateOf(outfit.availableColors.firstOrNull() ?: Color(0xFF4FC3F7)) }
    var isMenuExpanded by remember { mutableStateOf(false) }
    var swipeOffset by remember { mutableFloatStateOf(0f) }
    var isAdded by remember { mutableStateOf(false) }
    var isFavorite by remember { mutableStateOf(outfit.isFavorite) }

    val swipeThreshold = 120f
    val haptic = LocalHapticFeedback.current

    // Arrow animation
    val infiniteTransition = rememberInfiniteTransition(label = "arrowAnim")
    val arrowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(900),
            repeatMode = RepeatMode.Reverse
        ),
        label = "arrowAlpha"
    )

    // Reset added state
    LaunchedEffect(isAdded) {
        if (isAdded) {
            delay(1500)
            isAdded = false
        }
    }

    // Reset states when changing pages
    LaunchedEffect(currentPage) {
        isMenuExpanded = false
        swipeOffset = 0f
        isAdded = false
        selectedColor = outfit.availableColors.firstOrNull() ?: Color(0xFF4FC3F7)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Main Image
        Image(
            painter = painterResource(outfit.imageRes),
            contentDescription = outfit.title,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Top Gradient Overlay
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.7f),
                            Color.Transparent
                        )
                    )
                )
        )

        // Bottom Gradient Overlay
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(300.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.9f)
                        )
                    )
                )
        )

        // Header Buttons
        Box {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Favorite button
                IconButton(
                    onClick = { onBackPress() },
                    modifier = Modifier
                        .size(52.dp)
                        .clip(CircleShape)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.draw_back_ic),
                        contentDescription = "Back",
                        tint = Color.White,
                    )
                }

                // Menu button
                IconButton(
                    onClick = { isMenuExpanded = !isMenuExpanded },
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color.White, CircleShape)
                ) {
                    Icon(
                        painter = painterResource(
                            if (isMenuExpanded) R.drawable.menu_cross
                            else R.drawable.dot_ic
                        ),
                        contentDescription = "Menu",
                        tint = Color.Unspecified
                    )
                }
            }

            // Menu dropdown
            AnimatedVisibility(
                visible = isMenuExpanded,
                enter = fadeIn() + slideInVertically { -it / 2 },
                exit = fadeOut() + slideOutVertically { -it / 2 },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 72.dp, end = 20.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconCircleButton(if (isFavorite) R.drawable.white_frame else R.drawable.new_fav) {
                        isMenuExpanded = false
                        // Frame action
                        isFavorite = !isFavorite
                    }
                    IconCircleButton(R.drawable.share_ic) {
                        isMenuExpanded = false
                        onShare()
                    }
                    IconCircleButton(R.drawable.download_ic) {
                        isMenuExpanded = false
                        onDownload()
                    }
                }
            }
        }

        // Swipe to cart overlay
        AnimatedVisibility(
            visible = !isAdded && !isMenuExpanded,
            modifier = Modifier.align(Alignment.CenterEnd),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Column(
                modifier = Modifier
                    .padding(end = 20.dp)
                    .pointerInput(Unit) {
                        detectHorizontalDragGestures(
                            onHorizontalDrag = { _, dragAmount ->
                                swipeOffset += dragAmount
                            },
                            onDragEnd = {
                                if (swipeOffset > swipeThreshold) {
                                    isAdded = true
                                    onAddToCart()
                                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                }
                                swipeOffset = 0f
                            }
                        )
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.arow_swipe),
                        contentDescription = null,
                        tint = Color.White.copy(alpha = arrowAlpha),
                        modifier = Modifier.size(60.dp)
                    )
                    Icon(
                        painter = painterResource(R.drawable.arow_swipe),
                        contentDescription = null,
                        tint = Color.White.copy(alpha = arrowAlpha * 0.7f),
                        modifier = Modifier.size(45.dp)
                    )
                    Icon(
                        painter = painterResource(R.drawable.arow_swipe),
                        contentDescription = null,
                        tint = Color.White.copy(alpha = arrowAlpha * 0.4f),
                        modifier = Modifier.size(30.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Swipe Right to\nAdd to Cart",
                    color = Color.White.copy(alpha = 0.9f),
                    fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            }
        }

        // Added to cart confirmation
       /* AnimatedVisibility(
            visible = isAdded,
            modifier = Modifier.align(Alignment.Center),
            enter = fadeIn() + scaleIn(),
            exit = fadeOut() + scaleOut()
        ) {
            Box(
                modifier = Modifier
                    .background(
                        Color.Black.copy(alpha = 0.8f),
                        RoundedCornerShape(16.dp)
                    )
                    .padding(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = null,
                        tint = Color.Green,
                        modifier = Modifier.size(28.dp)
                    )
                    Text(
                        text = "Added to Cart!",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.baloo2_semibold))
                    )
                }
            }
        }*/

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
                OutlinedButton(
                    onClick = onRemove,
                    modifier = Modifier
                        .height(44.dp)
                        .width(120.dp),
                    shape = RoundedCornerShape(40.dp),
                    border = BorderStroke(1.5.dp, Color.White),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.Transparent
                    ),
                    contentPadding = PaddingValues(horizontal = 16.dp)
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
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                // Buy Now Button
                Button(
                    onClick = onBuyNow,
                    modifier = Modifier
                        .height(44.dp)
                        .width(140.dp),
                    shape = RoundedCornerShape(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryColor
                    ),
                    border = BorderStroke(1.5.dp, Color.White),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.buy_ic),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "Buy Now",
                            color = Color.White,
                            fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
                            fontSize = 16.sp,
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
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = outfit.title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "$${outfit.price}",
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontFamily = FontFamily(Font(R.font.baloo2_semibold))
                        )
                        if (outfit.originalPrice.isNotEmpty()) {
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = "$${outfit.originalPrice}",
                                fontSize = 18.sp,
                                color = Color.White.copy(alpha = 0.6f),
                                textDecoration = TextDecoration.LineThrough,
                                fontFamily = FontFamily(Font(R.font.nunito_regular))
                            )
                        }
                    }
                }

                // Color Selection Box
                if (outfit.availableColors.isNotEmpty()) {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(30.dp))
                            .background(Color.White)
                            .padding(horizontal = 10.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        outfit.availableColors.take(3).forEach { color ->
                            ColorOptionCircle(
                                color = color,
                                isSelected = selectedColor == color
                            ) {
                                selectedColor = it
                                onColorSelected(it)
                            }
                        }
                    }
                }
            }

            // Page counter
            /*Text(
                text = "${currentPage + 1} / $totalPages",
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.7f),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp),
                fontFamily = FontFamily(Font(R.font.nunito_regular))
            )*/
        }
    }
}

@Composable
private fun IconCircleButton(
    iconRes: Int,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(48.dp)
            .background(Color.White, CircleShape)
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(24.dp)
        )
    }
}



@Composable
private fun PageIndicator(
    currentPage: Int,
    pageCount: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(pageCount) { index ->
            Box(
                modifier = Modifier
                    .size(
                        width = 4.dp,
                        height = if (currentPage == index) 32.dp else 16.dp
                    )
                    .clip(RoundedCornerShape(2.dp))
                    .background(
                        if (currentPage == index)
                            Color.White
                        else
                            Color.White.copy(alpha = 0.5f)
                    )
                    .animateContentSize()
            )
        }
    }
}

@Composable
fun SuccessToast(
    message: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                color = Color(0XFF8F8F8F),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ad_cart_tick),
                contentDescription = null,
                tint = Color.Unspecified, // better green
                modifier = Modifier.wrapContentSize()
            )

            Text(
                text = message,
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily(Font(R.font.baloo2_medium))
            )
        }
    }
}

// Data class
data class OutfitItem(
    val id: String,
    val title: String,
    val price: String,
    val originalPrice: String = "",
    val imageRes: Int,
    val availableColors: List<Color> = emptyList(),
    val isFavorite: Boolean = false,
    val discountPercentage: Int = 0
)

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

@Preview(showBackground = true)
@Composable
fun FullScreenAITryScreenPreview() {
    BabyAITheme {
        FullScreenAITryScreen(navController = rememberNavController())
    }
}
