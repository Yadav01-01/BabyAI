package com.compose.babyai.ui.screens.profile.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.compose.babyai.R
import com.compose.babyai.ui.component.uiInput.CommonButton2
import com.compose.babyai.ui.component.uiInput.CommonOutlinedTextField
import com.compose.babyai.ui.component.uiInput.CommonTopBar

import com.compose.babyai.ui.component.uiInput.InputField1

//HelpSupportScreen
@Composable
fun HelpSupportScreen(
    navController: NavHostController,

    ) {

    var  name  by remember { mutableStateOf("") }
    var  phone  by remember { mutableStateOf("") }
    var  email  by remember { mutableStateOf("") }
    var  subject by remember { mutableStateOf("") }
    var  query  by remember { mutableStateOf("") }

    val context = LocalContext.current
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
                title = "Help & Support",
                onBackClick = {
                    navController.navigateUp()
                }
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .verticalScroll(rememberScrollState())
            ) {


                // ===== ILLUSTRATION =====
                Image(
                    painter = painterResource(R.drawable.ic_help_support_illustration),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth().padding(top = 20.dp)
                        .height(260.dp)
                )

                Spacer(Modifier.height(30.dp))

                // ===== NAME =====
                CommonOutlinedTextField(
                    value = name,
                    onValueChange = { name=it },
                    hintText = "Emma Jonson",
                    leadingIconResId = R.drawable.ic_user_icons,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    keyboardType = KeyboardType.Text
                )

                Spacer(Modifier.height(16.dp))

                // ===== PHONE =====
                CommonOutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it  },
                    hintText = "+1 (XXX) XXX-XXXX",
                    leadingIconResId = R.drawable.ic_call,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    keyboardType = KeyboardType.Phone
                )

                Spacer(Modifier.height(16.dp))

                // ===== EMAIL =====
                CommonOutlinedTextField(
                    value = email,
                    onValueChange = { email= it},
                    hintText = "Email ID",
                    leadingIconResId = R.drawable.ic_email,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    keyboardType = KeyboardType.Email
                )

                Spacer(Modifier.height(16.dp))

                // ===== SUBJECT =====
                CommonOutlinedTextField(
                    value = subject,
                    onValueChange = { subject =it },
                    hintText = "Subject",
                    leadingIconResId = R.drawable.ic_subject,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    keyboardType = KeyboardType.Text
                )

                Spacer(Modifier.height(16.dp))

                // ===== QUERY =====
                InputField1(
                    input = query,
                    onValueChange = { query =it },
                    placeholder = "Enter Your Query",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                )

                Spacer(Modifier.height(28.dp))

                // ===== SUBMIT BUTTON =====
                CommonButton2(
                    title = "Submit",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    fontSize = 18.sp,
                    onClick = {

                    }
                )

                Spacer(Modifier.height(30.dp))
            }
        }
    }
}
