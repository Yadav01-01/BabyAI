package com.compose.babyai.ui.screens.cart.paymentAndShipping

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
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
import com.compose.babyai.ui.component.dialog.AddressNOrderSuccessDialog
import com.compose.babyai.ui.theme.BabyAITheme

@Composable
fun AddNewAddressScreen(navController: NavHostController,type: String?) {
    var selectedType by remember { mutableStateOf("Home") }
    var flatNo by remember { mutableStateOf("") }
    var floor by remember { mutableStateOf("") }
    var area by remember { mutableStateOf("") }
    var landmark by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var pincode by remember { mutableStateOf("") }
    var addressSuccessDialog by remember { mutableStateOf(false) }


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
            AddNewAddressHeader(type,onBackClick = { navController.popBackStack() })

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(20.dp)
                            .imePadding()
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

                        CurvedImageTextField(
                            value = flatNo,
                            onValueChange = { flatNo = it },
                            placeholder = "Flat/ House no/ Building name"
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        CurvedImageTextField(
                            value = floor,
                            onValueChange = { floor = it },
                            placeholder = "Floor (Optional)"
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        CurvedImageTextField(
                            value = area,
                            onValueChange = { area = it },
                            placeholder = "Area/ Sector/ Locality"
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        CurvedImageTextField(
                            value = landmark,
                            onValueChange = { landmark = it },
                            placeholder = "Nearby Landmark (Optional)"
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        CurvedImageTextField(
                            value = city,
                            onValueChange = { city = it },
                            placeholder = "City/ District"
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        CurvedImageTextField(
                            value = pincode,
                            onValueChange = { pincode = it },
                            placeholder = "Pincode"
                        )

                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                AppButton(
                    text = if (type == "Add") "Save Address" else "Save Address Changes",
                    onClick = { addressSuccessDialog = true },
                )

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
    if (addressSuccessDialog){
        AddressNOrderSuccessDialog(onDismiss = { addressSuccessDialog = false })
    }
}

@Composable
fun AddNewAddressHeader(type: String?,onBackClick: () -> Unit) {
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
            text = if (type == "Add") "Add New Address" else " Edit Address",
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
            color = Color.Black
        )
    }
}

@Composable
fun CurvedImageTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {

        // Background image (curved border image)
        Image(
            painter = painterResource(id = R.drawable.input_bg),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize().background(Color.Transparent),
            contentScale = ContentScale.FillBounds
        )

        // TextField overlay
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily(Font(R.font.varela_round)),
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 14.dp
                )
                .align(Alignment.BottomStart),
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily(Font(R.font.varela_round)),
                        color = Color(0XFF363636)
                    )
                }
                innerTextField()
            }
        )
    }
}



@Preview(showBackground = true)
@Composable
fun AddNewAddressScreenPreview() {
    BabyAITheme {
        AddNewAddressScreen(navController = rememberNavController(),type = "Add")
    }
}
