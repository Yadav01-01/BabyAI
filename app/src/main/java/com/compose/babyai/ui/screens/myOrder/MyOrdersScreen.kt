package com.compose.babyai.ui.screens.myOrder

//MyOrdersScreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.compose.babyai.R
import com.compose.babyai.navigation.Routes
import com.compose.babyai.ui.component.TopBar

// Data class for Order
data class Order(
    val orderId: String,
    val date: String,
    val status: OrderStatus1
)

enum class OrderStatus1(val displayName: String, val color: Color) {
    DELIVERED("Delivered", Color(0xFF127A7A)),
    CANCELLED("Cancelled", Color(0xFF595959)),
    SHIPPED("Shipped", Color(0xFF1ECBCC)),
    PROCESSING("Processing", Color(0xFFFBD606))
}

@Composable
fun MyOrdersScreen(navController: NavHostController) {
    // Sample orders data
    val orders = listOf(
        Order("ORD-2025-001", "November 28, 2025", OrderStatus1.DELIVERED),
        Order("ORD-2025-002", "November 15, 2025", OrderStatus1.CANCELLED),
        Order("ORD-2025-003", "December 1, 2025", OrderStatus1.SHIPPED),
        Order("ORD-2025-004", "December 3, 2025", OrderStatus1.PROCESSING)
    )

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
            // Top App Bar
            TopBar(onBackClick = {
                navController.navigateUp()
            }, onSearchClick = {
//TrackReturnScreen
                navController.navigate(Routes.TrackReturnScreen.route)
            }, onWishListClick = {

            })

            Spacer(modifier = Modifier.height(20.dp))
            // Orders List
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                orders.forEach { order ->
                    OrderCard(order = order, orderClick = {orderId, status ->
                        //OrderSummaryScreen
                        navController.navigate(Routes.OrderSummaryScreen.createRoute(orderId, status))
                    })
                }
            }
        }

    }
}



@Composable
fun OrderCard(order: Order,orderClick:(String, String)->Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(27.dp))
            .background(Color(0xFFB9EFEF))
            .clickable { orderClick(order.orderId, order.status.name) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {


                Image(
                    painter = painterResource(id = R.drawable.ic_box_white_bg_icon),
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(33.dp)

                )

                // Order Details
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = order.orderId,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.varela_round)),
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF1C1C1C)
                    )
                    Text(
                        text = order.date,
                        fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                        fontSize = 12.sp,
                        color = Color(0xFF828282)
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Status Badge
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(40.dp))
                        .background(order.status.color).width(95.dp)
                        .padding( vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = order.status.displayName,
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.nunito_medium)),
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        color = if (order.status.displayName == "Processing") Color.Black else Color.White
                    )
                }

                // Arrow Icon
                Image(
                    painter = painterResource(R.drawable.ic_right_arrow),
                    contentDescription = "Details",
                    modifier = Modifier.size(13.dp)
                )
            }
        }
    }
}


