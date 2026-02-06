package com.compose.babyai.ui.screens.settings

//SettingsScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.compose.babyai.R
import com.compose.babyai.navigation.Routes
import com.compose.babyai.ui.component.CommonTopBar


@Composable
fun SettingsScreen(
    navController: NavHostController
) {
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
                .padding(horizontal = 16.dp)
        ) {


            CommonTopBar(
                title = "Settings",
                onBackClick = {
                    navController.navigateUp()
                }
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Settings Items
                SettingsItem(
                    icon = R.drawable.ic_about_baby_icon,
                    title = "About Babyfy",

                    onClick = {
                        navController.navigate(Routes.AboutBabyfyScreen.route)
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))

                SettingsItem(
                    icon = R.drawable.ic_faq_icon_setting,
                    title = "FAQs",

                    onClick = {
                        navController.navigate(Routes.FrequentlyAskQuestionsScreen.route)
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))

                SettingsItem(
                    icon = R.drawable.ic_term_and_condition_setiing,
                    title = "Terms & Conditions",

                    onClick = {
//TermsAndConditionsScreen
                        navController.navigate(Routes.TermsAndConditionsScreen.route)
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))

                SettingsItem(
                    icon = R.drawable.ic_privacy_icon_setting,
                    title = "Privacy Policy",

                    onClick = {
                        navController.navigate(Routes.PrivacyPolicyScreen.route)

                    }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }

    }
}

@Composable
fun SettingsItem(
    icon: Int,
    title: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(68.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick() },
        shape = RoundedCornerShape(55.dp),
        color = Color(0xFFE9FAFA),
        border = BorderStroke(width = 1.dp, color = Color(0xFFB9EFEF)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon Container
            /*      Box(
                      modifier = Modifier
                          .size(48.dp)
                          .clip(CircleShape)
                          .background(iconBackgroundColor.copy(alpha = 0.3f)),
                      contentAlignment = Alignment.Center
                  ) {
                      Text(
                          text = icon,
                          fontSize = 24.sp
                      )
                  }*/
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )

            Spacer(modifier = Modifier.width(14.dp))

            // Title
            Text(
                text = title,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.varela_round)),
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )

            // Arrow Icon
            Image(
                painter = painterResource(R.drawable.ic_right_arrow),
                contentDescription = "Navigate",
                modifier = Modifier.size(13.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SettingsScreenPreview() {
    // SettingsScreen()
}