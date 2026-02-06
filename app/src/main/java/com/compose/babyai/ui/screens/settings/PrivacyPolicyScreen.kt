package com.compose.babyai.ui.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.compose.babyai.R
import com.compose.babyai.ui.component.CommonTopBar

//AboutBabyfyScreen

@Composable
fun PrivacyPolicyScreen(
    navController: NavHostController
) {

    val scrollState = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.main_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillWidth
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            CommonTopBar(
                title = "Privacy Policy",
                onBackClick = {
                    navController.navigateUp()
                }
            )

            // Scrollable Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(horizontal = 24.dp)
                    .padding(top = 24.dp, bottom = 80.dp)
            ) {
                // First Paragraph
                Text(
                    text = "By using Babyfy, you agree to follow our app guidelines and help us create a safe, secure, and enjoyable experience for all parents. Babyfy provides AI-assisted wardrobe organization, outfit suggestions, and shopping recommendations for your baby. These features are for convenience and guidance only and should not replace personal judgment when selecting products for your child.\n" +
                            "\n" +
                            "You agree to use the app responsibly, upload accurate information about your baby’s wardrobe, and respect the privacy of any shared content. Babyfy may update features, design, or policies to improve your experience. Any changes will be reflected in our latest Terms & Conditions.\n" +
                            "\n" +
                            "By continuing to use the app, you acknowledge and accept the most recent version of our Privacy Policy and Terms.",
                    fontSize = 15.sp,
                    color = Color.Black,
                    fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                    lineHeight = 24.sp,
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.height(24.dp))


            }
        }


    }
}