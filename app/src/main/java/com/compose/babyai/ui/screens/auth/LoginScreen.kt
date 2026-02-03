package com.compose.babyai.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.compose.babyai.R
import com.compose.babyai.navigation.Routes
import com.compose.babyai.ui.component.AppButton
import com.compose.babyai.ui.component.InputTextField
import com.compose.babyai.ui.theme.BgColor
import com.compose.babyai.ui.theme.PrimaryColor


@Composable
fun LoginScreen(navController: NavHostController) {
    var name by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var isPhoneSelected by remember { mutableStateOf(true) }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(BgColor, Color.White)
                    )
                )
        )

        //  Bottom Image
        Image(
            painter = painterResource(id = R.drawable.lower_bg),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
                .align(Alignment.BottomCenter),
            contentScale = ContentScale.FillWidth
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(40.dp))

            Image(
                painter = painterResource(id = R.drawable.baby_ai),
                contentDescription = null,
                modifier = Modifier.height(40.dp)
            )

            Spacer(modifier = Modifier.height(60.dp))

            Text(
                text = "Let’s get you started",
                fontSize = 28.sp,
                fontFamily = FontFamily(Font(R.font.baloo2_medium)),
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Enter your number to receive a\nverification code.",
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.nunito_regular)),
                color = Color.Black,
                textAlign = TextAlign.Center,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            InputTextField(
                value = name,
                onValueChange = { name = it },
                placeholderText = "Parent/Guardian Full Name",
                leadingIcon = painterResource(id = R.drawable.person)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Email / Phone Toggle
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .clip(RoundedCornerShape(26.dp))
                    .background(Color.White)
                    .padding(4.dp)
            ) {
                Row(modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(30.dp))
                            .background(if (!isPhoneSelected) Color.Black else Color.Transparent)
                            .clickable { isPhoneSelected = false },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Email",
                            color = if (!isPhoneSelected) Color.White else Color.Black,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily(Font(R.font.outfit_regular)),
                            fontSize = 16.sp
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(22.dp))
                            .background(if (isPhoneSelected) Color.Black else Color.Transparent)
                            .clickable { isPhoneSelected = true },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Phone No.",
                            color = if (isPhoneSelected) Color.White else Color.Black,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily(Font(R.font.outfit_regular)),
                            fontSize = 16.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Phone Number Field
            InputTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                placeholderText = "Phone Number",
                leadingIcon = painterResource(id = R.drawable.phone_ic)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Send Code Button
            AppButton(
                text = "Send Code",
                onClick = { navController.navigate(Routes.OtpVerify.route) }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Continue as Guest
            Row(
                modifier = Modifier.clickable { navController.navigate(Routes.Main.route) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.guest), // Placeholder icon
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Continue as Guest",
                    color = Color(0XFF6A7193),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily(Font(R.font.outfit_regular))
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Bottom Text
            Text(
                text = buildAnnotatedString {
                    append("By continuing, you agree to our\n")
                    withStyle(style = SpanStyle(fontFamily = FontFamily(Font(R.font.baloo2_medium)),
                        fontWeight = FontWeight.Medium,color = PrimaryColor, textDecoration = TextDecoration.Underline)) {
                        append("Terms & Privacy Policy.")
                    }
                },
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.baloo2_regular)),
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = Color.Black,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }

}
