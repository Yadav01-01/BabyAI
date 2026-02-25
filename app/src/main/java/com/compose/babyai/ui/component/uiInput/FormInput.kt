package com.compose.babyai.ui.component.uiInput

import android.annotation.SuppressLint
import androidx.compose.animation.core.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.zIndex
import com.compose.babyai.R
import com.compose.babyai.data.model.BannerItem
import com.compose.babyai.ui.theme.PrimaryColor
import kotlinx.coroutines.delay
import java.util.prefs.Preferences
import kotlin.math.absoluteValue

@Composable
fun InputTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    modifier: Modifier = Modifier,
    leadingIcon: Painter? = null,
    trailingIcon: (@Composable (() -> Unit))? = null,
    error: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    Column {
        OutlinedTextField(
            value = value,
            onValueChange = { onValueChange(it) },
            placeholder = {
                Text(
                    text = placeholderText,
                    color = Color(0XFF6A7193),
                    fontFamily = FontFamily(Font(R.font.nunito_regular)),
                )
            },
            leadingIcon = if (leadingIcon != null) {
                {
                    Icon(
                        painter = leadingIcon,
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
            } else null,
            trailingIcon = trailingIcon,
            modifier = modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(28.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedBorderColor = PrimaryColor,
                unfocusedBorderColor = Color(0xFFE0E0E0),
                cursorColor = PrimaryColor
            ),
            singleLine = true,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions
        )

        if (error != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = error,
                color = Color.Red,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.nunito_regular))
            )
        }
    }
}

@Composable
fun ProfileTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    modifier: Modifier = Modifier,
    trailingIcon: (@Composable (() -> Unit))? = null,
    error: String? = null
) {
    Column {
        OutlinedTextField(
            value = value,
            onValueChange = { onValueChange(it) },
            placeholder = {
                Text(
                    text = placeholderText,
                    color = Color(0XFF6A7193),
                    fontFamily = FontFamily(Font(R.font.nunito_regular)),
                )
            },
            trailingIcon = trailingIcon,
            modifier = modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(28.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedBorderColor = PrimaryColor,
                unfocusedBorderColor = Color(0xFFE0E0E0),
                cursorColor = PrimaryColor
            ),
            singleLine = true
        )

        if (error != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = error,
                color = Color.Red,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.nunito_regular))
            )
        }
    }
}


@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonColors: Color = PrimaryColor,
    textColor: Color = Color.White,
    isNextEnabled : Boolean = true
){
    Button(
        onClick = { onClick() },
        enabled = isNextEnabled ,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(40.dp),
        colors = ButtonDefaults.buttonColors(containerColor = buttonColors)
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily(Font(R.font.baloo2_medium)),
            fontSize = 18.sp,
            color = textColor
        )
    }
}

@Composable
fun BabyAiTopBar(
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {

        // 🔹 Back icon (LEFT)
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back_ic),
                contentDescription = "Back",
                modifier = Modifier.size(24.dp),
                tint = Color.Unspecified
            )
        }

        // 🔹 Baby AI logo (CENTER)
        Image(
            painter = painterResource(id = R.drawable.baby_ai),
            contentDescription = "Baby AI Logo",
            modifier = Modifier
                .align(Alignment.Center)
                .height(24.dp)
        )
    }
}

@Composable
fun CardTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        placeholder = {
            Text(
                text = placeholderText,
                color = Color(0XFF6A7193),
                fontFamily = FontFamily(Font(R.font.nunito_regular)),
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(28.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedBorderColor = PrimaryColor,
            unfocusedBorderColor = Color(0xFFE0E0E0),
            cursorColor = PrimaryColor
        ),
        singleLine = true
    )
}

@Composable
fun SearchBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    placeholderText: String,
    icon: Painter? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    modifier: Modifier = Modifier,
    containerColor : Color = Color.White
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange, // cleaner
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        placeholder = {
            Text(
                text = placeholderText,
                color = Color(0xFFB0B0B0),
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.quicksand_regular))
            )
        },
        leadingIcon = icon?.let {
            {
                Icon(
                    painter = it,
                    contentDescription = null,
                    tint = Color(0xFFB0B0B0)
                )
            }
        },
        shape = RoundedCornerShape(28.dp),
        singleLine = true,
        enabled = enabled,
        readOnly = readOnly,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            disabledContainerColor = Color.White,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            cursorColor = Color.Black
        )
    )
}

@Composable
fun DetailHeading(heading : String){
    Text(heading,fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.baloo2_medium)),
        fontWeight = FontWeight.Medium,
        color = Color.Black)
}

@Composable
fun InputTextFieldWithoutIcon(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        placeholder = {
            Text(
                text = placeholderText,
                color = Color(0X806A7193),
                fontFamily = FontFamily(Font(R.font.nunito_regular)),
            )
        },
        textStyle = TextStyle(
            color = Color(0xFF0B4747),
            fontSize = 15.sp,
            fontFamily = FontFamily(Font(R.font.nunito_regular)),
            fontWeight = FontWeight.Normal
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(28.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedBorderColor = Color(0xFFE0E0E0),
            unfocusedBorderColor = Color(0xFFE0E0E0),
            cursorColor = Color(0xFFE0E0E0),
            focusedTextColor = Color(0xFF0B4747),     //  typed text (focused)
            unfocusedTextColor = Color(0x806A7193)
        ),
        singleLine = true
    )
}

@Composable
fun BannerCarousel(
    banners: List<BannerItem>,
    modifier: Modifier = Modifier,
    onBannerClick: (BannerItem) -> Unit
) {
    if (banners.isEmpty()) return

    val deckState = rememberStackedDeckState(itemCount = banners.size)
    val density = LocalDensity.current

    // Smooth animated index — drives visual position without recomposition per frame
    val smoothIndex by animateFloatAsState(
        targetValue = deckState.activeIndex.toFloat(),
        animationSpec = spring(
            stiffness = Spring.StiffnessMedium,
            dampingRatio = Spring.DampingRatioNoBouncy
        ),
        label = "smoothIndex"
    )

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 16.dp)
            .clipToBounds()
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragEnd = { deckState.onDragEnd() },
                    onDragCancel = { deckState.onDragEnd() },
                    onHorizontalDrag = { _, delta -> deckState.onDrag(delta) }
                )
            }
    ) {
        val cardWidth = maxWidth - 40.dp
        val cardWidthPx = with(density) { cardWidth.toPx() }
        val peekOffsetPx = with(density) { 24.dp.toPx() }

        // dragProgress: how far dragged as a fraction of card width
        val dragProgress = if (cardWidthPx != 0f) deckState.dragOffsetX / cardWidthPx else 0f

        // currentViewIndex: fractional index of what's "centered" right now
        val currentViewIndex = smoothIndex - dragProgress

        banners.forEachIndexed { index, banner ->
            // pos: where this card sits relative to the viewport center
            val pos = index.toFloat() - currentViewIndex

            // Only render cards in visible range: 1 card leaving left + up to 3 stacked right
            if (pos < -1.2f || pos >= 4f) return@forEachIndexed

            // Stable click target — avoid lambda allocation on every recompose
            val isActive = pos in -0.01f..0.01f

            Box(
                modifier = Modifier
                    .width(cardWidth)
                    .height(220.dp)
                    .graphicsLayer {
                        if (pos < 0f) {
                            // Card swiping OUT to the left
                            translationX = pos * cardWidthPx
                            alpha = (1f + pos).coerceAtLeast(0f)
                        } else {
                            // Cards stacked to the right
                            val fraction = pos.coerceIn(0f, 3f)
                            val scale = 1f - (0.05f * fraction)

                            translationX = fraction * peekOffsetPx
                            translationY = fraction * 8f * density.density
                            scaleX = scale
                            scaleY = scale
                            alpha = 1f - (0.2f * fraction).coerceAtMost(0.6f)
                        }
                    }
                    // Left cards on top (zIndex ~100), stacked right cards descend
                    .zIndex(if (pos < 0f) 100f else 100f - pos * 10f)
                    .clip(RoundedCornerShape(32.dp))
                    .clickable(
                        // Only the front card is clickable; suppress ripple on stacked cards
                        enabled = pos > -0.5f && pos < 0.5f,
                        onClick = { onBannerClick(banner) }
                    )
            ) {
                BannerCard(
                    banner = banner,
                    bannerCount = banners.size,
                    activeIndex = deckState.activeIndex,    //  passed explicitly — no scope leak
                )
            }
        }
    }
}

// ─────────────────────────────────────────────────────────
// BannerCard — self-contained, no outer scope dependencies
// ─────────────────────────────────────────────────────────

@Composable
fun BannerCard(
    banner: BannerItem,
    bannerCount: Int,           //  explicit params instead of capturing outer state
    activeIndex: Int,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
        //  Removed redundant clip — parent Box already clips
    ) {
        Image(
            painter = painterResource(banner.image),
            contentDescription = banner.title,   //  accessibility
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Gradient for text readability — right-side focused
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.7f)
                        ),
                        startX = 200f
                    )
                )
        )

        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 24.dp, top = 16.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = banner.title,
                color = Color.White,
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.baloo2_bold)),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End,
                maxLines = 2,                   //  prevent unbounded text expansion
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(Modifier.height(4.dp))       //  explicit spacing instead of implicit padding

            Text(
                text = banner.subtitle,
                color = Color.White.copy(alpha = 0.85f),   //  slight dim for hierarchy
                fontFamily = FontFamily(Font(R.font.nunito_regular)),
                fontSize = 16.sp,
                textAlign = TextAlign.End,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }

        //  Moved indicator OUT of the Column — it belongs at the bottom, not top
        PagerIndicator(
            size = bannerCount,
            currentPage = activeIndex,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 22.dp, end = 14.dp)
        )
    }
}


@Composable
fun PagerIndicator(size: Int, currentPage: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(size) { index ->
            val isSelected = index == currentPage
            Box(
                modifier = Modifier
                    .height(6.dp)
                    .width(if (isSelected) 24.dp else 6.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            )
        }
    }
}

@Composable
fun PreferencesSearchBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    placeholderText: String,
    icon: Painter? = null,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange, // cleaner
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        placeholder = {
            Text(
                text = placeholderText,
                color = Color(0xFFB0B0B0),
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.quicksand_regular))
            )
        },
        leadingIcon = icon?.let {
            {
                Icon(
                    painter = it,
                    contentDescription = null,
                    tint = Color(0xFFB0B0B0)
                )
            }
        },
        shape = RoundedCornerShape(28.dp),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color(0XFFE9FAFA),
            unfocusedContainerColor = Color(0XFFE9FAFA),
            disabledContainerColor = Color.White,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            cursorColor = Color.Black
        )
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AutoSlidingCardStackCarousel() {
    val pageCount = 5
    val pagerState = rememberPagerState(pageCount = { pageCount })

    LaunchedEffect(pagerState.currentPage) {
        delay(3000)
        val next = (pagerState.currentPage + 1) % pageCount
        pagerState.animateScrollToPage(next)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .clipToBounds(), //  Prevent left side visibility
        contentAlignment = Alignment.CenterStart
    ) {

        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(end = 80.dp),
            pageSpacing = (-120).dp, // strong overlap
            modifier = Modifier.fillMaxWidth()
        ) { page ->

            val pageOffset =
                (pagerState.currentPage - page) +
                        pagerState.currentPageOffsetFraction

            val absOffset = pageOffset.absoluteValue

            Card(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .graphicsLayer {

                        //  Hide previous cards completely
                        if (page < pagerState.currentPage) {
                            alpha = 0f
                        } else {

                            // Stack only to RIGHT
                            translationX = absOffset * 80f

                            scaleX = 1f - (absOffset * 0.05f)
                            scaleY = 1f - (absOffset * 0.05f)

                            alpha = 1f
                        }
                    }
                    .zIndex((pageCount - page).toFloat())
                    .height(220.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Card $page",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}