package com.compose.babyai.ui.screens.cart.paymentAndShipping

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
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
import com.compose.babyai.navigation.Routes
import com.compose.babyai.ui.component.AppButton
import com.compose.babyai.ui.theme.BabyAITheme
import com.compose.babyai.ui.theme.PrimaryColor

@Composable
fun SavedAddressScreen(navController: NavHostController) {
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
            SavedAddressHeader(onBackClick = { navController.popBackStack() })

            Spacer(Modifier.height(15.dp))

            AppButton(
                text = "+ Add New Address",
                onClick = { navController.navigate(Routes.AddNewAddress.createRoute("Add")) },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(vertical = 10.dp),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp)
            ) {

                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {

                            // Section Title INSIDE Card
                            Text(
                                text = "Your Saved Addresses",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                color = Color.Black
                            )

                            val addresses = listOf(
                                AddressItemData(
                                    "Home",
                                    "123, Street, Anywhere, 11001",
                                    R.drawable.exp_home
                                ),
                                AddressItemData(
                                    "Office",
                                    "123, Street, Anywhere, 11001",
                                    R.drawable.office
                                )
                            )

                            // Address Cards
                            addresses.forEach { address ->
                                SavedAddressCard(
                                    address = address,
                                    onDeleteClick = {},
                                    onEditClick = { navController.navigate(Routes.AddNewAddress.createRoute("Edit"))}
                                )
                            }
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun SavedAddressHeader(onBackClick: () -> Unit) {
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
        ) {
            Icon(
                painter = painterResource(id = R.drawable.draw_back_ic),
                contentDescription = "Back",
                tint = Color.Unspecified
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "Saved Address",
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
            color = Color.Black
        )
    }
}

data class AddressItemData(val title: String, val detail: String, val iconRes: Int)

@Composable
fun SavedAddressCard(
    address: AddressItemData,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFCCCCCC))
    ) {
        Column {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = address.iconRes),
                    contentDescription = null,
                    tint = PrimaryColor,
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = address.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                        color = Color.Black
                    )
                    Text(
                        text = address.detail,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily(Font(R.font.varela_round)),
                        color = Color(0XFF363636)
                    )
                }
            }
            
            HorizontalDivider(thickness = 1.dp, color = Color(0xFFCCCCCC))
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .clickable { onDeleteClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Delete",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily(Font(R.font.quicksand_medium)),
                        color = Color(0xFFFF0000)
                    )
                }
                
                VerticalDivider(
                    modifier = Modifier,
                    thickness = 1.dp,
                    color = Color(0xFFCCCCCC)
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .clickable { onEditClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Edit",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily(Font(R.font.quicksand_medium)),
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SavedAddressScreenPreview() {
    BabyAITheme {
        SavedAddressScreen(navController = rememberNavController())
    }
}
