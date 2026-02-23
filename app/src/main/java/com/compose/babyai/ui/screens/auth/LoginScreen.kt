package com.compose.babyai.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.arpitkatiyarprojects.countrypicker.CountryPicker
import com.arpitkatiyarprojects.countrypicker.models.SelectedCountryDisplayProperties
import com.arpitkatiyarprojects.countrypicker.models.SelectedCountryProperties
import com.compose.babyai.R
import com.compose.babyai.data.uistate.ContactType
import com.compose.babyai.navigation.Routes
import com.compose.babyai.ui.component.uiInput.AppButton
import com.compose.babyai.ui.component.uiInput.InputTextField
import com.compose.babyai.ui.theme.BgColor
import com.compose.babyai.ui.theme.PrimaryColor
import com.compose.babyai.viewModel.auth.LoginViewModel


@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val isPhoneSelected = state.selectedContactType == ContactType.PHONE


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
            painter = painterResource(id = R.drawable.main_bg),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            contentScale = ContentScale.Crop
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
                text = stringResource(R.string.logheading),
                fontSize = 28.sp,
                fontFamily = FontFamily(Font(R.font.baloo2_medium)),
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.logsubheading),
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.nunito_regular)),
                color = Color.Black,
                textAlign = TextAlign.Center,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

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
                            .background(if (state.selectedContactType == ContactType.EMAIL) Color.Black else Color.Transparent)
                            .clickable { viewModel.onContactTypeChange(ContactType.EMAIL) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Email",
                            color = if (state.selectedContactType == ContactType.EMAIL) Color.White else Color.Black,
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
                            .background(if (state.selectedContactType == ContactType.PHONE) Color.Black else Color.Transparent)
                            .clickable { viewModel.onContactTypeChange(ContactType.PHONE) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Phone No.",
                            color = if (state.selectedContactType == ContactType.PHONE) Color.White else Color.Black,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily(Font(R.font.outfit_regular)),
                            fontSize = 16.sp
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            InputTextField(
                value = state.name,
                onValueChange = { value -> viewModel.onNameChange(value) },
                placeholderText = "Parent/Guardian Full Name",
                leadingIcon = painterResource(id = R.drawable.person),
                error = state.nameError
            )

            Spacer(modifier = Modifier.height(16.dp))
            if (isPhoneSelected){
                // Phone Number Field
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(18.dp)) {

                    Box(
                        modifier = Modifier.height(55.dp)
                            .border(1.dp, color = Color(0xFFB1B5BE), shape = RoundedCornerShape(40.dp)),
                        contentAlignment = Alignment.Center
                    ) {

                        CountryPicker(
                            defaultCountryCode = "us",
                            selectedCountryDisplayProperties = SelectedCountryDisplayProperties(
                                properties = SelectedCountryProperties(
                                    showCountryFlag = true,
                                    showCountryName = false,
                                    showCountryPhoneCode = false,
                                    showCountryCode = false,
                                    showDropDownIcon = true,
                                    spaceAfterCountryFlag = 6.dp,
                                    dropDownIconComposable = {
                                        Image(
                                            painterResource(R.drawable.ic_dropdown_icon_download),
                                            contentDescription = "Select Country",
                                            // tint = Color.Black
                                            modifier = Modifier.padding(start = 10.dp, end = 5.dp)
                                                .size(12.dp)
                                        )
                                    }
                                )
                            ),
                            onCountrySelected = { country ->
                                // Handle country selection
                            },
                            modifier = Modifier.scale(0.75f).padding(start = 10.dp)
                        )

                    }

                    InputTextField(
                        value = state.phoneNumber,
                        onValueChange = { value -> viewModel.onPhoneChange(value) },
                        placeholderText = "Phone Number",
                        leadingIcon = painterResource(id = R.drawable.phone_ic),
                    )
                }

            }else{
                InputTextField(
                    value = state.email,
                    onValueChange = { value -> viewModel.onEmailChange(value) },
                    placeholderText = "Email Address",
                    leadingIcon = painterResource(id = R.drawable.ic_email_icon),
                    error = if (state.selectedContactType == ContactType.EMAIL)
                        state.contactError
                    else null
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            // Send Code Button
            AppButton(
                text = "Send Code",
                onClick = { viewModel.sendCode(navController) }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Continue as Guest
            Row(
                modifier = Modifier.clickable {
                    navController.navigate(Routes.Main.route) {
                        popUpTo(Routes.Login.route) {
                            inclusive = true
                        }
                    }
                },
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

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_4
)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    LoginScreen(navController = navController)
}
