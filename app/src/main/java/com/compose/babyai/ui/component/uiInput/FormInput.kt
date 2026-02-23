package com.compose.babyai.ui.component.uiInput

import android.annotation.SuppressLint
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
    error: String? = null
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
        leadingIcon = {
            leadingIcon?.let {
                Icon(
                    painter = it,
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
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
            focusedTextColor = Color(0xFF0B4747),     // 👈 typed text (focused)
            unfocusedTextColor = Color(0x806A7193)
        ),
        singleLine = true
    )
}

@SuppressLint("FrequentlyChangingValue")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BannerCarousel(
    banners: List<BannerItem>,
    modifier: Modifier = Modifier,
    onBannerClick: (BannerItem) -> Unit
) {
    if (banners.isEmpty()) return

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { banners.size }
    )

    // Auto-scroll
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            if (!pagerState.isScrollInProgress) {
                val nextPage = (pagerState.currentPage + 1) % banners.size
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    val swipeProgress = pagerState.currentPageOffsetFraction.absoluteValue

    // Smooth animated values for the behind card
    val behindScale by animateFloatAsState(
        targetValue = lerp(0.88f, 1f, swipeProgress),
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "behindScale"
    )

    val behindTranslationX by animateFloatAsState(
        targetValue = lerp(40f, 0f, swipeProgress),
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessMediumLow
        ),
        label = "behindTranslationX"
    )

    val behindTranslationY by animateFloatAsState(
        targetValue = lerp(16f, 0f, swipeProgress),
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessMediumLow
        ),
        label = "behindTranslationY"
    )

    val behindAlpha by animateFloatAsState(
        targetValue = lerp(0.65f, 1f, swipeProgress),
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
        label = "behindAlpha"
    )

    val nextPage = (pagerState.currentPage + 1) % banners.size

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(220.dp)
            .padding(horizontal = 16.dp)
    ) {

        // ── BEHIND CARD ──
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    scaleX = behindScale
                    scaleY = behindScale
                    translationX = behindTranslationX
                    translationY = behindTranslationY
                    alpha = behindAlpha
                    transformOrigin = TransformOrigin(1f, 0.5f)
                }
                .clip(RoundedCornerShape(32.dp))
        ) {
            BannerCard(banners[nextPage])
        }

        // ── ACTIVE CARD (via HorizontalPager) ──
        HorizontalPager(
            state = pagerState,
            pageSpacing = 0.dp,
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier.fillMaxSize(),
            /*beyondBoundsPageCount = 1*/
        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(2f)
                    .shadow(
                        elevation = 12.dp,
                        shape = RoundedCornerShape(32.dp),
                        clip = false
                    )
                    .clip(RoundedCornerShape(32.dp))
                    .clickable { onBannerClick(banners[page]) }
            ) {
                BannerCard(banners[page])
            }
        }

        // ── INDICATOR ──
        PagerIndicator(
            size = banners.size,
            currentPage = pagerState.currentPage,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 22.dp, end = 14.dp)
        )
    }
}

@Composable
fun BannerCard(banner: BannerItem) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(32.dp))
    ) {
        Image(
            painter = painterResource(banner.image),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Dark Gradient Overlay for text readability (Right side focused)
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
                textAlign = TextAlign.End
            )

            Text(
                text = banner.subtitle,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.nunito_regular)),
                fontSize = 16.sp,
                textAlign = TextAlign.End
            )
        }
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