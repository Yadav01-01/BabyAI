package com.compose.babyai.ui.screens.profile.settings

//TermsAndConditionsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.compose.babyai.ui.component.uiInput.CommonTopBar
//PrivacyPolicyScreen
@Composable
fun TermsAndConditionsScreen(
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
                title = "Terms & Conditions",
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
                    text = "Your privacy is our highest priority. Babyfy collects only the information needed to deliver a personalized, seamless, and enjoyable experience as you manage your baby’s wardrobe and explore new outfit recommendations. This may include basic profile details, your baby’s information (such as name or nickname), uploaded wardrobe photos, purchase history, preferences, and app interactions.\n" +
                            "\n" +
                            "We do not share or sell your personal data to third parties without your consent. All information is stored securely and handled in accordance with applicable privacy regulations to ensure your safety and trust.\n" +
                            "\n" +
                            "You maintain full control over your data. At any time, you can update, export, or delete your information through your profile settings. Babyfy is committed to protecting your family’s privacy while providing a warm, smart, and parent-friendly experience.",
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