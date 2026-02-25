package com.compose.babyai.ui.screens.cart.paymentAndShipping

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.compose.babyai.R
import com.compose.babyai.navigation.Routes
import com.compose.babyai.ui.screens.cart.CartItem
import com.compose.babyai.ui.screens.cart.CartItemCard
import com.compose.babyai.ui.screens.cart.getDummyCartItems
import com.compose.babyai.ui.theme.BabyAITheme
import com.compose.babyai.ui.theme.PrimaryColor

@Composable
fun PaymentScreen(navController: NavHostController) {
    var selectedPayment by remember { mutableStateOf("Credit") }
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
            PaymentHeader(onBackClick = { navController.popBackStack() })

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp),
                contentPadding = PaddingValues(bottom = 20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                // Item Card
                items(getDummyCartItems()) { item ->
                    CartItemCard(item, onItemClick = { navController.navigate(Routes.ProductDetail.route)})
                }

                // Order Summary
                item {
                    OrderSummaryCard()
                }

                // Shipping Address
                item {
                    PaymentShippingAddressSection(address = "26, Duong So 2, Thao Dien Wa...",
                        onAddClick = { navController.navigate(Routes.AddShippingAddress.route)},
                        onEditClick = { navController.navigate(Routes.SavedAddress.route)},
                        empty = false)
                }

                // Payment Option - Credit
                item {
                    PaymentMethodItem(
                        icon = R.drawable.card_ic,
                        title = "Credit",
                        subtitle = "Add and secure cards as per Bank Guidelines",
                        isSelected = selectedPayment == "Credit",
                        onPaymentMethodClick = { selectedPayment = "Credit" },
                    )
                }

                // Payment Option - COD
                item {
                    PaymentMethodItem(
                        icon = R.drawable.pod_ic,
                        title = "Pay on Delivery",
                        isSelected = selectedPayment == "Pay on Delivery",
                        onPaymentMethodClick = { selectedPayment = "Pay on Delivery" }
                    )
                }

                item {
                    Button(
                        onClick = { /* Handle Payment */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(40.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)
                    ) {
                        Text(
                            text = "Pay Now $58.82",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = FontFamily(Font(R.font.quicksand_medium)),
                            color = Color.White
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun PaymentHeader(onBackClick: () -> Unit) {
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
            text = stringResource(R.string.Payments),
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
            color = Color.Black
        )
    }
}

@Composable
fun OrderSummaryCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = stringResource(R.string.Order_Summary),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(12.dp))
            SummaryRow(label = "Subtotal", value = "$49.98")
            SummaryRow(label = "Shipping", value = "FREE", valueColor = Color(0xFF4CAD02))
            SummaryRow(label = "Estimated Tax", value = "$8.84")
            
            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(thickness = 1.dp, color = Color(0xFF828282))
            Spacer(modifier = Modifier.height(12.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.Total),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                    color = Color.Black
                )
                Text(
                    text = "$58.82",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun SummaryRow(label: String, value: String, valueColor: Color = Color.Black) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.quicksand_regular)),
            fontWeight = FontWeight.Normal,
            color = Color(0XFF828282)
        )
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily(Font(R.font.varela_round)),
            color = valueColor
        )
    }
}

@Composable
fun PaymentShippingAddressSection(address: String ,onAddClick:() -> Unit, onEditClick: () -> Unit,empty : Boolean = false) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(R.string.Shipping_Address),
                    fontSize = 18.sp,
                    color = Color(0XFF828282),
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.quicksand_semibold))
                )
                Text(
                    text = if (empty) stringResource(R.string.No_shipping_address_found) else address,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily(Font(R.font.varela_round)),
                    color = Color(0xFF1C1C1C),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            IconButton(onClick = {
                if (empty) {
                    onAddClick()
                } else {
                    onEditClick()
                }
            }
            ) {
                Icon(
                    painter = painterResource(id = if (empty) R.drawable.add_ic else R.drawable.edit_ic),
                    contentDescription = "Edit",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(48.dp)
                )
            }
        }
    }
}

@Composable
fun PaymentMethodItem(
    icon: Int,
    title: String,
    subtitle: String? = null,
    onPaymentMethodClick: () -> Unit,
    isSelected: Boolean = false,
) {
    Card(
        onClick = onPaymentMethodClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, if (isSelected) PrimaryColor else Color.Transparent)
    ){
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                    color = Color.Black
                )
                if (subtitle != null) {
                    Text(
                        text = subtitle,
                        fontSize = 14.sp,
                        color = Color(0XFF1C1C1C),
                        fontFamily = FontFamily(Font(R.font.quicksand_regular))
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentScreenPreview() {
    BabyAITheme {
        PaymentScreen(navController = rememberNavController())
    }
}
