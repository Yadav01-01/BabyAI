package com.compose.babyai.ui.screens.authProfile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.compose.babyai.R
import com.compose.babyai.navigation.Routes
import com.compose.babyai.ui.component.AppButton
import com.compose.babyai.ui.theme.BgColor
import com.compose.babyai.ui.theme.PrimaryColor

@Composable
fun ProfileReadyScreen(navController: NavHostController) {

    Box(modifier = Modifier.fillMaxSize()) {

        // Background
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(BgColor, Color.White)
                    )
                )
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(bottom = 30.dp)
        ) {

            item { Spacer(modifier = Modifier.height(40.dp)) }

            item {
                Image(
                    painter = painterResource(id = R.drawable.baby_ai),
                    contentDescription = null,
                    modifier = Modifier.height(40.dp)
                )
            }

            item { Spacer(modifier = Modifier.height(10.dp)) }

            item {
                Image(
                    painter = painterResource(R.drawable.ready_ic),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp),
                    contentScale = ContentScale.FillWidth
                )
            }

            item { Spacer(modifier = Modifier.height(20.dp)) }

            item {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                color = PrimaryColor,
                                fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
                                fontSize = 24.sp
                            )
                        ) {
                            append("All Done!\n")
                        }

                        withStyle(
                            SpanStyle(
                                color = Color.Black,
                                fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
                                fontSize = 24.sp
                            )
                        ) {
                            append("Your Baby’s Style Is Ready!")
                        }
                    },
                    textAlign = TextAlign.Center
                )
            }

            item { Spacer(modifier = Modifier.height(10.dp)) }

            item {
                Text(
                    text = "Babify is now ready with smart AI recommendations tailored to your little one.",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_regular)),
                    color = Color(0XFFB0B0B0),
                    textAlign = TextAlign.Center,
                    lineHeight = 22.sp,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }

            item { Spacer(modifier = Modifier.height(20.dp)) }

            item {
                AppButton(
                    text = "Start Exploring",
                    onClick = { navController.navigate(Routes.Home.route) },
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }
        }
    }
}
