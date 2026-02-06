package com.compose.babyai.ui.screens.cart.paymentAndShipping

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.compose.babyai.R
import com.compose.babyai.ui.component.AppButton
import com.compose.babyai.ui.component.CardTextField
import com.compose.babyai.ui.theme.BabyAITheme
import com.compose.babyai.ui.theme.PrimaryColor

@Composable
fun ShippingAddressScreen(navController: NavHostController) {
    var selectedType by remember { mutableStateOf("Home") }
    var flatNo by remember { mutableStateOf("") }
    var floor by remember { mutableStateOf("") }
    var area by remember { mutableStateOf("") }
    var landmark by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var pincode by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        // Background
        Image(
            painter = painterResource(id = R.drawable.main_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Header
            AddressHeader(onBackClick = { navController.popBackStack() })

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(30.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp).imePadding()
                    ) {
                        Text(
                            text = "Save Address as*",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            AddressTypeItem(
                                label = "Home",
                                isSelected = selectedType == "Home",
                                onClick = { selectedType = "Home" },
                                modifier = Modifier.weight(1f)
                            )
                            AddressTypeItem(
                                label = "Office",
                                isSelected = selectedType == "Office",
                                onClick = { selectedType = "Office" },
                                modifier = Modifier.weight(1f)
                            )
                            AddressTypeItem(
                                label = "Other",
                                isSelected = selectedType == "Other",
                                onClick = { selectedType = "Other" },
                                modifier = Modifier.weight(1f)
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        CardTextField(
                            value = flatNo,
                            onValueChange = { flatNo = it },
                            placeholderText = "Flat/ House no/ Building name"
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        CardTextField(
                            value = floor,
                            onValueChange = { floor = it },
                            placeholderText = "Floor (Optional)"
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        CardTextField(
                            value = area,
                            onValueChange = { area = it },
                            placeholderText = "Area/ Sector/ Locality"
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        CardTextField(
                            value = landmark,
                            onValueChange = { landmark = it },
                            placeholderText = "Nearby Landmark (Optional)"
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        CardTextField(
                            value = city,
                            onValueChange = { city = it },
                            placeholderText = "City/ District"
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        CardTextField(
                            value = pincode,
                            onValueChange = { pincode = it },
                            placeholderText = "Pincode"
                        )

                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                AppButton(
                    text = "Submit",
                    onClick = { /* Handle Submit */ },
                )
                
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun AddressHeader(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .size(52.dp)
                .clip(CircleShape)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.draw_back_ic),
                contentDescription = "Back",
                tint = Color.Unspecified
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "Shipping Address",
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
            color = Color.Black
        )
    }
}

@Composable
fun AddressTypeItem(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(44.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(if (isSelected) PrimaryColor else Color.Transparent)
            .border(
                width = 1.dp,
                color = if (isSelected) Color(0XFF179899) else Color(0xFF000000),
                shape = RoundedCornerShape(22.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily(Font(R.font.varela_round)),
            color = if (isSelected) Color.White else Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShippingAddressScreenPreview() {
    BabyAITheme {
        ShippingAddressScreen(navController = rememberNavController())
    }
}
