package com.compose.babyai.ui.screens.profile.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.compose.babyai.R
import com.compose.babyai.ui.component.uiInput.CommonButtonCard
import com.compose.babyai.ui.component.uiInput.CommonOutlinedTextFieldCard
import com.compose.babyai.ui.component.uiInput.CommonOutlinedTextFieldCardNumber
import com.compose.babyai.ui.component.uiInput.CommonTopBar
import com.compose.babyai.ui.component.datepicker.DatePickerModal

@Composable
fun AddNewCardScreen(navController: NavHostController) {

    var cardholderName by remember { mutableStateOf("") }
    var cardNumber by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }
    var expiry by remember { mutableStateOf("") }
    var datePicker by remember { mutableStateOf("MM-DD-YYYY") }
    var showDatePicker by remember { mutableStateOf(false) }

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
                title = "Add New Card",
                onBackClick = {
                    navController.navigateUp()
                },
                modifier = Modifier.fillMaxWidth()
            )

            // Scrollable Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .verticalScroll(scrollState)
                    .padding(horizontal = 16.dp)
            ) {

                // Cardholder's Name Input
                CommonOutlinedTextFieldCard(
                    value = cardholderName,
                    onValueChange = { cardholderName = it },
                    hintText = "Cardholder's Name",
                    leadingIconResId = R.drawable.ic_user_icon,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Card Number Input
                CommonOutlinedTextFieldCardNumber(
                    value = cardNumber,
                    onValueChange = { cardNumber = it },
                    hintText = "Card Number",
                    leadingIconResId = R.drawable.ic_hash_icon,
                    keyboardType = KeyboardType.Number,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // CVV and Expiry Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // CVV Input
                    CommonOutlinedTextFieldCard(
                        value = cvv,
                        onValueChange = { if (it.length <= 3) cvv = it },
                        hintText = "CVV",
                        leadingIconResId = R.drawable.ic_cvv_icon,
                        keyboardType = KeyboardType.Number,
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp)
                    )

                    // Expiry Date Input
                    CommonOutlinedTextFieldCard(
                        value = expiry,
                        onValueChange = {
                            // Format as MM/YYYY
                            val digits = it.filter { char -> char.isDigit() }
                            expiry = when {
                                digits.length <= 2 -> digits
                                digits.length <= 6 -> "${digits.take(2)}/${digits.drop(2)}"
                                else -> "${digits.take(2)}/${digits.drop(2).take(4)}"
                            }
                        },
                        hintText = "MM/YYYY",
                        leadingIconResId = R.drawable.ic_month_date_icon,
                        keyboardType = KeyboardType.Number,
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp)
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))
            }

            // Add Card Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
            ) {
                CommonButtonCard(
                    title = "Add Card",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    fontSize = 18.sp,
                    radius = 40.dp,
                    onClick = {
                        // Handle add card logic
                        navController.navigateUp()
                    }
                )
            }
        }

    }
    if (showDatePicker) {
        DatePickerModal(
            onDateSelected = { date ->
                date?.let {
                    datePicker = it

                }
                showDatePicker = false
            },
            onDismiss = { showDatePicker = false }
        )
    }
}


