package com.compose.babyai.ui.component.uiInput

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.zIndex
import com.compose.babyai.R
import com.compose.babyai.data.model.BannerItem
import com.compose.babyai.ui.theme.PrimaryColor
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue

@Composable
fun InputTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    modifier: Modifier = Modifier,
    leadingIcon: Painter? = null
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
}


@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonColors: Color = PrimaryColor
){
    Button(
        onClick = { onClick() },
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
            color = Color.White
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
    modifier: Modifier = Modifier
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
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BannerCarousel(
    banners: List<BannerItem>,
    modifier: Modifier = Modifier,
    onBannerClick: (BannerItem) -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { banners.size }
    )

    // Auto-scroll
    LaunchedEffect(pagerState.currentPage) {
        delay(3000)
        val nextPage = (pagerState.currentPage + 1) % banners.size
        pagerState.animateScrollToPage(nextPage)
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 40.dp),
            pageSpacing = (-20).dp,
            modifier = Modifier.height(200.dp)
        ) { page ->

            val pageOffset =
                ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

            val scale = lerp(0.92f, 1f, 1f - pageOffset.coerceIn(0f, 1f))
            val alpha = lerp(0.6f, 1f, 1f - pageOffset.coerceIn(0f, 1f))

            Box(
                modifier = Modifier
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        this.alpha = alpha
                    }
                    .zIndex(1f - pageOffset)
                    .clip(RoundedCornerShape(24.dp))
                    .clickable { onBannerClick(banners[page]) }
            ) {
                BannerCard(banners[page])
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        PagerIndicator(
            size = banners.size,
            currentPage = pagerState.currentPage
        )
    }
}


@Composable
fun BannerCard(banner: BannerItem) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(20.dp))
    ) {

        Image(
            painter = painterResource(banner.image),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Gradient Overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.horizontalGradient(
                        listOf(
                            Color.Black.copy(alpha = 0.6f),
                            Color.Transparent
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(16.dp)
        ) {
            Text(
                text = banner.title,
                color = Color.White,
                fontSize = 28.sp,
                fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = banner.subtitle,
                color = Color.White.copy(alpha = 0.9f),
                fontFamily = FontFamily(Font(R.font.nunito_regular)),
                fontSize = 16.sp
            )
        }
    }
}


@Composable
fun PagerIndicator(size: Int, currentPage: Int) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(size) { index ->
            Box(
                modifier = Modifier
                    .size(if (index == currentPage) 8.dp else 6.dp)
                    .clip(CircleShape)
                    .background(
                        if (index == currentPage)
                            Color.White
                        else
                            Color.White.copy(alpha = 0.4f)
                    )
            )
        }
    }
}
