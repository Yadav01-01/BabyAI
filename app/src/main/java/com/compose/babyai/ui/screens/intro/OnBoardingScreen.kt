package com.compose.babyai.ui.screens.intro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.compose.babyai.R
import com.compose.babyai.data.model.OnBoardingPage
import com.compose.babyai.navigation.Routes
import com.compose.babyai.ui.theme.*
import kotlinx.coroutines.launch


val onBoardingPages = listOf(
    OnBoardingPage(
        imageMain = R.drawable.onb1,
        title = "Perfect Fit, Every Time",
        description = "Our AI analyzes your baby's proportions to find outfits that fit just right."
    ),
    OnBoardingPage(
        imageMain = R.drawable.onb2,
        title = "Style, Comfort & Care",
        description = "Discover complete outfit looks - soft, safe, and made for your baby’s comfort."
    ),
    OnBoardingPage(
        imageMain = R.drawable.onb3,
        title = "Shop Smarter, Not Harder",
        description = "Get personalized outfit picks and buy them instantly from trusted brands."
    )
)

@Composable
fun OnBoardingScreen(navController: NavHostController) {

    val pagerState = rememberPagerState(pageCount = { onBoardingPages.size })
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.plain_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(Modifier.height(8.dp))

            // 🔹 Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baby_ai),
                    contentDescription = null
                )

                Text(
                    text = "Skip",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.outfit_semibold)),
                    modifier = Modifier.clickable {
                        navController.navigate(Routes.Login.route) {
                            popUpTo(Routes.Splash.route) { inclusive = true }
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 🔹 Progress Indicator
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(onBoardingPages.size) { index ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(
                                if (index <= pagerState.currentPage)
                                    PrimaryColor
                                else
                                    ProgressBarBg
                            )
                    )
                }
            }

            Spacer(modifier = Modifier.weight(0.4f))

            // 🔹 Pager
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxWidth()
            ) { page ->

                val item = onBoardingPages[page]

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    contentAlignment = Alignment.Center
                ) {

                    Image(
                        painter = painterResource(R.drawable.onb_back_img),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    Image(
                        painter = painterResource(id = item.imageMain),
                        contentDescription = null
                    )
                }
            }

            Spacer(modifier = Modifier.weight(0.4f))

            // 🔹 Text
            val page = onBoardingPages[pagerState.currentPage]

            Text(
                text = page.title,
                fontSize = 28.sp,
                fontFamily = FontFamily(Font(R.font.baloo2_medium)),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = page.description,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.nunito_regular)),
                textAlign = TextAlign.Center,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(48.dp))

            // 🔹 Next Button
            Button(
                onClick = {
                    if (pagerState.currentPage < onBoardingPages.lastIndex) {
                        scope.launch {
                            pagerState.animateScrollToPage(
                                pagerState.currentPage + 1
                            )
                        }
                    } else {
                        navController.navigate(Routes.Login.route) {
                            popUpTo(Routes.Splash.route) { inclusive = true }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)
            ) {
                Text(
                    text = if (pagerState.currentPage == onBoardingPages.lastIndex)
                        "Get Started"
                    else "Next",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.museomoderno_medium)),
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}


