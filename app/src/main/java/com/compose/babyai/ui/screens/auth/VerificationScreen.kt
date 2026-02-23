package com.compose.babyai.ui.screens.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.compose.babyai.R
import com.compose.babyai.navigation.Routes
import com.compose.babyai.ui.component.uiInput.AppButton
import com.compose.babyai.ui.component.uiInput.BabyAiTopBar
import com.compose.babyai.ui.component.dialog.SuccessDialog
import com.compose.babyai.ui.theme.BgColor
import com.compose.babyai.ui.theme.PrimaryColor
import com.compose.babyai.viewModel.auth.VerificationViewModel
import kotlinx.coroutines.delay

@SuppressLint("DefaultLocale")
@Composable
fun VerificationScreen(navController: NavHostController,
                       viewModel: VerificationViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val focusRequesters = remember { List(5) { FocusRequester() } }


  /*  LaunchedEffect(key1 = timeLeft) {
        if (timeLeft > 0) {
            delay(1000L)
            timeLeft--
        }
    }

    val isOtpFilled = otpValues.all { it.isNotEmpty() }*/

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

        // Bottom Image
        Image(
            painter = painterResource(id = R.drawable.main_bg),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
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

            BabyAiTopBar { navController.popBackStack() }

            Spacer(modifier = Modifier.height(60.dp))

            Text(
                text = "Verify Your Number",
                fontSize = 28.sp,
                fontFamily = FontFamily(Font(R.font.baloo2_medium)),
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Enter the 5-digit code we sent to\n+1 ******1234",
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.nunito_regular)),
                color = Color.Black,
                textAlign = TextAlign.Center,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            // OTP Input
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                state.otpValues.forEachIndexed { index, value ->
                    OtpTextField(
                        value = value,
                        onValueChange = { newValue ->
                            viewModel.onOtpChange(index, newValue)

                            if (newValue.isNotEmpty() && index < 4) {
                                focusRequesters[index + 1].requestFocus()
                            }
                        },
                        modifier = Modifier
                            .size(60.dp)
                            .focusRequester(focusRequesters[index]),
                        isFilled = value.isNotEmpty()
                    )
                }
            }
            if (state.otpError != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = state.otpError!!,
                    color = Color.Red,
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_regular))
                )
            }



            Spacer(modifier = Modifier.height(32.dp))

            // Verify & Continue Button
            AppButton(
                text = "Verify & Continue",
                onClick = { viewModel.verifyOtp() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                buttonColors = if (state.isOtpFilled) PrimaryColor else Color(0xFFBDBEC8)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Resend Timer
            Text(
                text = buildAnnotatedString {
                    append("Didn't get it? ")
                    if (state.timeLeft > 0) {
                        withStyle(style = SpanStyle(color = Color(0xFF6A7193))) {
                            append("Resend in ")
                        }
                        withStyle(style = SpanStyle(color = PrimaryColor)) {
                            append(String.format("00:%02d", state.timeLeft))
                        }
                    } else {
                        withStyle(style = SpanStyle(color = PrimaryColor, fontWeight = FontWeight.Bold)) {
                            append("Resend")
                        }
                    }
                },
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.nunito_regular)),
                modifier = Modifier.clickable(enabled = state.timeLeft == 0) {
                    viewModel.resendOtp()
                }
            )

            Spacer(modifier = Modifier.weight(1f))
        }
    }
    if (state.showSuccessDialog) {
        SuccessDialog {
            viewModel.dismissDialog()
            navController.navigate(Routes.ProfileSetup.route)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isFilled: Boolean
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .clip(CircleShape)
            .background(if (isFilled) Color(0xFFFBD606) else Color.Transparent),
        shape = CircleShape,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = if (isFilled) Color(0xFFFBD606) else Color(0xFFA0A8B9),
            unfocusedBorderColor = if (isFilled) Color(0xFFFBD606) else Color(0xFFA0A8B9),
            focusedContainerColor = if (isFilled) Color(0xFFFBD606) else Color.Transparent,
            unfocusedContainerColor = if (isFilled) Color(0xFFFBD606) else Color.Transparent,
        ),
        textStyle = TextStyle(
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = if (isFilled) Color.White else Color.Black,
            fontFamily = FontFamily(Font(R.font.outfit_regular))
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true
    )
}
