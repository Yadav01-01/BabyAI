package com.compose.babyai.ui.screens.profile.myOrder

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.compose.babyai.R
import com.compose.babyai.data.model.BottomSheetOption
import com.compose.babyai.navigation.Routes
import com.compose.babyai.ui.component.uiInput.CommonTopBar
import com.compose.babyai.ui.component.dialog.SelectionBottomSheet
import com.compose.babyai.ui.dialog.OrderCancelledDialog
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


// Data Models
data class OrderItem(
    val id: String,
    val name: String,
    val color: String,
    val size: String,
    val price: Double,
    val originalPrice: Double? = null,
    val imageUrl: Int? = null
)

data class PriceBreakdown(
    val amount: Double,
    val shipping: Double,
    val tax: Double,
    val total: Double
)

data class DeliveryDetails(
    val name: String,
    val address: String,
    val city: String,
    val state: String,
    val zipCode: String,
    val country: String,
    val phone: String
)

data class TrackingInfo(
    val trackingNumber: String,
    val estimatedDelivery: Date
)

data class PaymentInfo(
    val method: String,
    val lastFourDigits: String
)

enum class OrderStatus {
    ORDER_PLACED,
    PROCESSING,
    SHIPPED,
    IN_TRANSIT,
    OUT_FOR_DELIVERY,
    DELIVERED
}

data class TrackingEvent(
    val status: OrderStatus,
    val title: String,
    val location: String,
    val description: String,
    val timestamp: Date
)

data class OrderSummary(
    val orderId: String,
    val orderDate: Date,
    val items: List<OrderItem>,
    val priceBreakdown: PriceBreakdown,
    val deliveryDetails: DeliveryDetails,
    val trackingInfo: TrackingInfo,
    val paymentInfo: PaymentInfo,
    val trackingHistory: List<TrackingEvent>
)

// Color Scheme
object OrderColors {
    val Primary = Color(0xFF00BCD4)
    val Background = Color(0xFFE8F5F7)
    val CardBackground = Color.White
    val TextPrimary = Color(0xFF2D3748)
    val TextSecondary = Color(0xFF718096)
    val Green = Color(0xFF10B981)
    val Red = Color(0xFFEF4444)
    val Orange = Color(0xFFFF9800)
    val Yellow = Color(0xFFFFC107)
}

fun dummyOrderSummary(): OrderSummary {
    return OrderSummary(
        orderId = "ORDER #123456",
        orderDate = Date(),

        items = listOf(
            OrderItem(
                id = "1",
                name = "Baby Cotton Dress",
                color = "Pink",
                size = "M",
                price = 29.99,
                originalPrice = 39.99,
                imageUrl = R.drawable.dummy_img
            ),
            OrderItem(
                id = "2",
                name = "Soft Baby Shoes",
                color = "White",
                size = "6-12 Months",
                price = 19.99,
                imageUrl = R.drawable.dummy_img
            )
        ),

        priceBreakdown = PriceBreakdown(
            amount = 49.98,
            shipping = 0.0,
            tax = 4.50,
            total = 54.48
        ),

        deliveryDetails = DeliveryDetails(
            name = "Vipin Khatri",
            address = "123 Baby Street",
            city = "New Delhi",
            state = "Delhi",
            zipCode = "110001",
            country = "India",
            phone = "+91 9876543210"
        ),

        trackingInfo = TrackingInfo(
            trackingNumber = "TRK987654321",
            estimatedDelivery = Date(System.currentTimeMillis() + 3 * 24 * 60 * 60 * 1000)
        ),

        paymentInfo = PaymentInfo(
            method = "Credit Card",
            lastFourDigits = "4242"
        ),

        trackingHistory = listOf(
            TrackingEvent(
                status = OrderStatus.ORDER_PLACED,
                title = "Order Placed",
                location = "New Delhi",
                description = "Your order has been placed",
                timestamp = Date(System.currentTimeMillis() - 3 * 60 * 60 * 1000)
            ),
            TrackingEvent(
                status = OrderStatus.SHIPPED,
                title = "Order Shipped",
                location = "Delhi Hub",
                description = "Your order has been shipped",
                timestamp = Date(System.currentTimeMillis() - 2 * 60 * 60 * 1000)
            ),
            TrackingEvent(
                status = OrderStatus.OUT_FOR_DELIVERY,
                title = "Out for Delivery",
                location = "Local Delivery Center",
                description = "Delivery partner is on the way",
                timestamp = Date(System.currentTimeMillis() - 30 * 60 * 1000)
            )
        )
    )
}

//OrderSummaryScreen
@Composable
fun OrderSummaryScreen(
    navController: NavHostController,
    orderId: String,
    orderStatus: String

) {
    var showReturnReasonSheet by remember { mutableStateOf(false) }
    var showCancelOrderSheet by remember { mutableStateOf(false) }
    var showOrderCancelledDialog by remember { mutableStateOf(false) }
    var showOrderReturnDialog by remember { mutableStateOf(false) }
    var selectedReason by remember { mutableStateOf<BottomSheetOption?>(null) }
    var returnComment by remember { mutableStateOf("") }
    var  comment  by remember { mutableStateOf("") }
    val reasons1 = listOf(
        BottomSheetOption(1, "Ordered by mistake"),
        BottomSheetOption(2, "Unhappy with the quality"),
        BottomSheetOption(3, "Delivery time is too long"),
        BottomSheetOption(4, "Ordered wrong size/item"),
        BottomSheetOption(5, "No longer needed"),
        BottomSheetOption(6, "Other")
    )
    val reasons = listOf(
        BottomSheetOption(1, "Item doesn't fit"),
        BottomSheetOption(2, "Unhappy with the quality"),
        BottomSheetOption(3, "Wrong item received"),
        BottomSheetOption(4, "Item damaged"),
        BottomSheetOption(5, "Ordered by mistake"),
        BottomSheetOption(6, "Other")
    )


    val orderSummary = getOrderSummaryBasedOnStatus(orderId, orderStatus)
    Box(modifier = Modifier.fillMaxSize())
    {

        Image(
            painter = painterResource(id = R.drawable.main_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillWidth
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
        )
        {
            CommonTopBar(
                title = "My Order Summary",
                onBackClick = {
                    navController.navigateUp()
                },
                modifier = Modifier.fillMaxWidth()
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            )
            {
                // 🔹 Content
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                )
                {
                    item {Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = Color(0xFFE9FAFA),
                                shape = RoundedCornerShape(15.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = Color(0xFF1ECBCC),
                                shape = RoundedCornerShape(15.dp)
                            ),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OrderHeaderCard(
                            orderId = orderSummary.orderId,
                            orderDate = orderSummary.orderDate,
                            onInvoiceClick = {

                            }
                        )
                        Text(
                            "Order Summary",
                            fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 17.sp,
                            color = Color(0xFF000000),
                            modifier = Modifier.padding(start = 10.dp)
                        )
                        OrderItemsCard(items = orderSummary.items)
                        ItemsSummaryCard(
                            items = orderSummary.items,
                            priceBreakdown = orderSummary.priceBreakdown
                        )
                        PriceBreakdownCard(priceBreakdown = orderSummary.priceBreakdown)
                        Card(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = OrderColors.CardBackground
                            ),
                            shape = RoundedCornerShape(30.dp)
                            //   elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                            ) {
                                Spacer(Modifier.height(3.dp))
                            DeliveryDetailsCard(deliveryDetails = orderSummary.deliveryDetails)
                            Spacer(Modifier.height(12.dp))
                            HorizontalDivider(color = Color(0xFFBFBFBF))
                            Spacer(Modifier.height(12.dp))
                            TrackingInfoCard(trackingInfo = orderSummary.trackingInfo)
                                Spacer(Modifier.height(5.dp))
                            }
                        }
                        PaymentMethodCard(paymentInfo = orderSummary.paymentInfo)
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedButton(
                                onClick = {

                                },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = OrderColors.Primary
                                )
                            ) {
                                Image(
                                    painterResource(R.drawable.ic_truck_empty),
                                    contentDescription = null,
                                    modifier = Modifier.size(23.dp)
                                )
                                Spacer(Modifier.width(3.dp))
                                Text("Track Order",
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
                                    color = Color(0xFF000000))
                            }

                            Button(
                                onClick = { },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF1ECBCC)
                                )
                            ) {
                                Text("Reorder",
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
                                    color = Color(0xFFFFFFFF))
                            }
                        }
                        Spacer(Modifier.height(12.dp))
                        HorizontalDivider(color = Color(0xFFBFBFBF))
                        Spacer(Modifier.height(12.dp))
                        TrackingHistoryCard(trackingHistory = orderSummary.trackingHistory)

                            Spacer(Modifier.height(10.dp))
                    }
                    }


                    item {
                        Spacer(Modifier.height(13.dp))
                    }

                    item {
                        Button(
                            onClick = { showReturnReasonSheet = true},
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFFA500)
                            ),
                            shape = RoundedCornerShape(25.dp)
                        ) {
                            Icon(Icons.Default.Refresh, contentDescription = null)
                            Spacer(Modifier.width(8.dp))
                            Text("Return Order",
                                color = Color.White,
                                fontSize = 15.sp,
                                fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
                                fontWeight = FontWeight.SemiBold)
                        }
                    }
                    item {
                        Spacer(Modifier.height(13.dp))
                    }
                    item {
                        Text(
                            text = "Returns accepted within 30 days of delivery",
                            color = Color(0xFF828282),
                            fontSize = 11.sp,
                            fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp)
                        )
                    }
                    item {
                        Spacer(Modifier.height(5.dp))
                    }
                    item {
                        Button(
                            onClick = { showCancelOrderSheet = true},
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFA3230)
                            ),
                            shape = RoundedCornerShape(25.dp)
                        ) {
                            Icon(painterResource(R.drawable.ic_track_close_icon),
                                contentDescription = null,
                                modifier = Modifier.size(21.dp)
                            )
                            Spacer(Modifier.width(8.dp))
                            Text("Cancel Order", color = Color.White,
                                fontSize = 15.sp,
                                fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
                                fontWeight = FontWeight.SemiBold)
                        }
                    }
                    item {
                        Spacer(Modifier.height(10.dp))
                    }
                    item {
                        Text(
                            text = "We'll attempt to recall the shipment",
                            color = Color(0xFF828282),
                            fontSize = 11.sp,
                            fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp)
                        )
                    }
                    item {
                        Spacer(Modifier.height(10.dp))
                    }
                }

            }
    }
    }
    if (showReturnReasonSheet) {
        SelectionBottomSheet(
            title = "Reason for Return",
            options = reasons,
            selectedOption = selectedReason,
            onOptionSelect = { selectedReason = it },
            comment = comment,
            onCommentChange = { comment = it },
            commentPlaceholder = "Tell us more about why you're returning this order...",
            onConfirm = {
                showReturnReasonSheet = false

                // Delay dialog slightly to avoid sheet/dialog overlap
                showOrderReturnDialog = true
            },
            onDismiss = {
                showReturnReasonSheet = false
            },
        )
    }
    if (showCancelOrderSheet) {
        SelectionBottomSheet(
            title = "Reason for Cancellation",
            options = reasons1,
            selectedOption = selectedReason,
            onOptionSelect = { selectedReason = it },
            comment = comment,
            onCommentChange = { comment = it },
            commentPlaceholder = "Tell us more about why you're canceling this order...",
            onConfirm = { showCancelOrderSheet = false
                showOrderCancelledDialog = true},
            onDismiss = { showCancelOrderSheet = false }
        )
    }
    if (showOrderCancelledDialog) {
        OrderCancelledDialog(
            title = "Order Cancelled",
            description = "Your order cancellation has been \n confirmed.",
            onDismiss = { showOrderCancelledDialog = false },
            onBackToHome = { showOrderCancelledDialog = false
            navController.navigate(Routes.Home.route)
            }
        )
    }

    if (showOrderReturnDialog) {
        OrderCancelledDialog(
            title = "Order Return",
            description = "Your order return has been \n confirmed.",
            onDismiss = {
                showOrderReturnDialog = false
            },
            onBackToHome = {
                showOrderReturnDialog = false
                navController.navigate(Routes.Home.route)
            }
        )
    }
}


@Composable
fun OrderHeaderCard(
    orderId: String,
    orderDate: Date,
    onInvoiceClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFB9EFEF)
        ),
      //  elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
               /* Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(OrderColors.Primary.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Refresh,
                        contentDescription = null,
                        tint = OrderColors.Primary,
                        modifier = Modifier.size(24.dp)
                    )
                }*/
                Image(
                    painter = painterResource(id = R.drawable.ic_box_white_bg_icon),
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(35.dp)

                )

                Column {
                    Text(
                        orderId,
                        fontFamily = FontFamily(Font(R.font.varela_round)),
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp,
                        color = Color(0xFF1C1C1C)
                    )
                    Text(
                        SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(orderDate),
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF828282)
                    )
                }
            }

         /*   Button(
                onClick = onInvoiceClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = OrderColors.Primary
                ),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Icon(
                    Icons.Default.Refresh,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(Modifier.width(6.dp))
                Text("Invoice", fontSize = 14.sp)
            }*/
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFF1ECBCC))
                    .border(1.dp, color = Color(0xFF179899), shape = RoundedCornerShape(10.dp))
                    .clickable { onInvoiceClick() }
                    .padding(horizontal = 10.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                       painter = painterResource(R.drawable.ic_download_icon1) ,
                        contentDescription = null,
                        modifier = Modifier.size(21.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "Invoice",
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.nunito_medium)),
                        color = Color.White
                    )
                }
            }

        }
    }
}

@Composable
fun OrderItemsCard(items: List<OrderItem>) {
  /*  Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = OrderColors.CardBackground
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {*/
        Column(modifier = Modifier.padding(10.dp)) {


            items.forEach { item ->
                OrderItemRow(item)
                if (item != items.last()) {
                    Spacer(Modifier.height(12.dp))
            //        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f))
                  //  Spacer(Modifier.height(12.dp))
                }
            }
        }
   // }
}

@Composable
fun OrderItemRow(item: OrderItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = OrderColors.CardBackground
        ),
        shape = RoundedCornerShape(30.dp)
        //elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            )
            {
                // Placeholder for product image
                Box(
                    modifier = Modifier.width(115.dp).height(95.dp)
                        .clip(RoundedCornerShape(20.dp)),
                        //.background(Color.LightGray.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(item.imageUrl?:R.drawable.dummy_img),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.matchParentSize()
                    )
                }

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        item.name,
                        fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp,
                        color = Color(0xFF1C1C1C),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(Modifier.height(2.dp))
                    Text(
                        "Color: ${item.color}",
                        fontFamily = FontFamily(Font(R.font.outfit_regular)),
                        fontWeight = FontWeight.Normal,
                        fontSize = 11.sp,
                        color = Color(0xFF000000)
                    )
                    Spacer(Modifier.height(2.dp))
                    Text(
                        "Size: ${item.size}",
                        fontFamily = FontFamily(Font(R.font.outfit_regular)),
                        fontWeight = FontWeight.Normal,
                        fontSize = 11.sp,
                        color = Color(0xFF000000)
                    )
                    Spacer(Modifier.weight(1f))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "$${String.format("%.2f", item.price)}",
                            fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = Color(0xFF1ECBCC)
                        )
                        if (item.originalPrice != null) {
                            Text(
                                "$${String.format("%.2f", item.originalPrice)}",
                                fontFamily = FontFamily(Font(R.font.outfit_regular)),
                                fontWeight = FontWeight.Normal,
                                fontSize = 9.sp,
                                color = Color(0xFF828282)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ItemsSummaryCard(
    items: List<OrderItem>,
    priceBreakdown: PriceBreakdown
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = OrderColors.CardBackground
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                "Items in Order",
                fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp,
                color = Color(0xFF000000),
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // Calculate item names from the items list
            val itemDescriptions = items.map { "${it.name} - Size ${it.size}" }

            itemDescriptions.forEachIndexed { index, itemDesc ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        itemDesc,
                        fontFamily = FontFamily(Font(R.font.quicksand_regular)),
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp,
                        color = Color(0xFF828282),
                        modifier = Modifier.weight(1f)
                    )
                    val isFree = items[index].price == 0.0
                    //"$${String.format("%.2f", items[index].price)}"
                    Text(
                        if (isFree) "FREE" else "$${String.format("%.2f", items[index].price)}",
                        fontFamily = FontFamily(Font(R.font.varela_round)),
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp,
                        color = if (isFree) Color(0xFF4CAD02) else Color(0xFF000000),
                    )
                }
                if (index < itemDescriptions.size - 1) {
                    Spacer(Modifier.height(8.dp))
                }
            }

            Spacer(Modifier.height(12.dp))
            HorizontalDivider(color = Color(0xFF828282))
            Spacer(Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Total",
                    fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color(0xFF000000),
                )
                Text(
                    "$${String.format("%.2f", priceBreakdown.total)}",
                    fontFamily = FontFamily(Font(R.font.quicksand_bold)),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color(0xFF000000),
                )
            }
        }
    }
}

@Composable
fun PriceBreakdownCard(priceBreakdown: PriceBreakdown) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = OrderColors.CardBackground
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "Price Breakdown",
                fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp,
                color = Color(0xFF000000),
                modifier = Modifier.padding(bottom = 12.dp)
            )

            PriceRow(
                label = "Amount (1 Item)",
                value = priceBreakdown.amount,
                valueColor = OrderColors.TextPrimary
            )
            Spacer(Modifier.height(6.dp))

            PriceRow(
                label = "Shipping",
                value = priceBreakdown.shipping,
                valueColor = if (priceBreakdown.shipping == 0.0) OrderColors.Green else OrderColors.TextPrimary,
                isFree = priceBreakdown.shipping == 0.0
            )
            Spacer(Modifier.height(6.dp))

            PriceRow(
                label = "Estimated Tax",
                value = priceBreakdown.tax,
                valueColor = OrderColors.TextPrimary
            )

            Spacer(Modifier.height(12.dp))
            HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f))
            Spacer(Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Total",
                    fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color =  Color(0xFF000000),
                )
                Text(
                    "$${String.format("%.2f", priceBreakdown.total)}",
                    fontFamily = FontFamily(Font(R.font.quicksand_bold)),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color =  Color(0xFF000000),
                )
            }
        }
    }
}

@Composable
fun PriceRow(
    label: String,
    value: Double,
    valueColor: Color,
    isFree: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            label,
            fontFamily = FontFamily(Font(R.font.quicksand_regular)),
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp,
            color = Color(0xFF828282),
        )
        Text(
            if (isFree) "FREE" else "$${String.format("%.2f", value)}",
            fontFamily = FontFamily(Font(R.font.varela_round)),
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp,
            color = if (isFree) Color(0xFF4CAD02) else Color(0xFF000000),
        )
    }
}

@Composable
fun DeliveryDetailsCard(deliveryDetails: DeliveryDetails) {
    InfoCard(
        icon = R.drawable.delevery_loc_icon,
        iconColor = OrderColors.Primary,
        title = "Delivery Details"
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                deliveryDetails.name,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = OrderColors.TextPrimary
            )
            Text(
                deliveryDetails.address,
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.varela_round)),
                color = Color(0xFF1C1C1C)
            )
            Text(
                "${deliveryDetails.city}, ${deliveryDetails.state} ${deliveryDetails.zipCode}",
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.varela_round)),
                color = Color(0xFF1C1C1C)
            )
            Text(
                deliveryDetails.country,
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.varela_round)),
                color = Color(0xFF1C1C1C)
            )
            Text(
                deliveryDetails.phone,
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.varela_round)),
                color = Color(0xFF1C1C1C)
            )
        }
    }
}

@Composable
fun TrackingInfoCard(trackingInfo: TrackingInfo) {
    InfoCard(
        icon = R.drawable.ic_tracktor_icon,
        iconColor = OrderColors.Primary,
        title = "Tracking Information"
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                trackingInfo.trackingNumber,
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.varela_round)),
                color = Color(0xFF1C1C1C)
            )
            Text(
                "Est. Delivery: ${SimpleDateFormat("MMMM d, yyyy", Locale.getDefault()).format(trackingInfo.estimatedDelivery)}",
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.varela_round)),
                color = Color(0xFF1C1C1C)
            )
        }
    }
}

@Composable
fun PaymentMethodCard(paymentInfo: PaymentInfo) {
    InfoCard1(
        icon = R.drawable.ic_yellow_card_icon,
        iconColor = OrderColors.Yellow,
        title = "Payment Method",
    ) {
        Text(
            "${paymentInfo.method} ending in ${paymentInfo.lastFourDigits}",
            fontSize = 13.sp,
            fontFamily = FontFamily(Font(R.font.varela_round)),
            color = Color(0xFF828282)
        )
    }
}

@Composable
fun InfoCard(
    icon: Int,
    iconColor: Color,
    title: String,
    content: @Composable () -> Unit
) {
/*    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = OrderColors.CardBackground
        ),
        shape = RoundedCornerShape(30.dp)
     //   elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {*/
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
       /*     Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(iconColor.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {*/
                Image(
                    painter = painterResource(icon),
                    contentDescription = null,
                   // tint = iconColor,
                    modifier = Modifier.size(45.dp)
                )
//            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    title,
                    fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp,
                    color = Color(0xFF828282),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                content()
            }
        }
   // }
}


@Composable
fun InfoCard1(
    icon: Int,
    iconColor: Color,
    title: String,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFEF9DA)
        ),
        shape = RoundedCornerShape(15.dp)
        //   elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            /*     Box(
                     modifier = Modifier
                         .size(40.dp)
                         .clip(CircleShape)
                         .background(iconColor.copy(alpha = 0.15f)),
                     contentAlignment = Alignment.Center
                 ) {*/
            Image(
                painter = painterResource(icon),
                contentDescription = null,
                // tint = iconColor,
                modifier = Modifier.size(45.dp)
            )
//            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    title,
                    fontFamily = FontFamily(Font(R.font.varela_round)),
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    color = Color(0xFF1C1C1C),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                content()
            }
        }
    }
}

@Composable
fun TrackingHistoryCard(trackingHistory: List<TrackingEvent>) {
/*    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = OrderColors.CardBackground
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {*/
        Column(modifier = Modifier.padding(horizontal = 10.dp)) {

            Text(
                "Tracking History",
                fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color(0xFF000000),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            trackingHistory.forEachIndexed { index, event ->
                TrackingEventItem(
                    event = event,
                    isLast = index == trackingHistory.size - 1
                )
            }
        }
  //  }
}

@Composable
fun TrackingEventItem(event: TrackingEvent, isLast: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = if (isLast) 0.dp else 16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(50.dp)
        ) {
  /*          Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(
                        when (event.status) {
                            OrderStatus.DELIVERED -> OrderColors.Green.copy(alpha = 0.15f)
                            OrderStatus.OUT_FOR_DELIVERY -> OrderColors.Orange.copy(alpha = 0.15f)
                            else -> OrderColors.Primary.copy(alpha = 0.15f)
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {*/
                Image( painterResource(
                    when (event.status) {
                        OrderStatus.ORDER_PLACED -> R.drawable.ic_order_icon_track
                        OrderStatus.PROCESSING -> R.drawable.ic_order_icon_track
                        OrderStatus.SHIPPED -> R.drawable.ic_shipped_icon_trac
                        OrderStatus.IN_TRANSIT -> R.drawable.ic_shipped_icon_trac
                        OrderStatus.OUT_FOR_DELIVERY -> R.drawable.ic_out_of_delevery_icon
                        OrderStatus.DELIVERED -> R.drawable.ic_develered_icon_track
                    }),
                    contentDescription = null,
                /*    tint = when (event.status) {
                        OrderStatus.DELIVERED -> OrderColors.Green
                        OrderStatus.OUT_FOR_DELIVERY -> OrderColors.Orange
                        else -> OrderColors.Primary
                    },*/
                    modifier = Modifier.size(35.dp)
                )
           // }

            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .height(40.dp)
                        .background(Color(0xFFB9EFEF))
                )
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    event.title,
                    fontFamily = FontFamily(Font(R.font.varela_round)),
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    color = Color(0xFF1C1C1C)
                )
                Text(
                    SimpleDateFormat("h:mm a", Locale.getDefault()).format(event.timestamp),
                    fontFamily = FontFamily(Font(R.font.varela_round)),
                    fontWeight = FontWeight.Normal,
                    fontSize = 11.sp,
                    color = Color(0xFF999999)
                )
            }

            Text(
                event.location,
                fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp,
                color = Color(0xFF828282),
                modifier = Modifier.padding(top = 2.dp)
            )

            Text(
                event.description,
                fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                fontWeight = FontWeight.SemiBold,
                fontSize = 11.sp,
                color = Color(0xFF828282),
                modifier = Modifier.padding(top = 4.dp)
            )

            Text(
                SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(event.timestamp),
                fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                fontWeight = FontWeight.SemiBold,
                fontSize = 11.sp,
                color = Color(0xFF0B4747),
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

// Status के आधार पर अलग-अलग डमी डेटा जेनरेट करने वाला फंक्शन
fun getOrderSummaryBasedOnStatus(orderId: String, status: String): OrderSummary {
    return when (status) {
        "DELIVERED" -> getDeliveredOrderSummary(orderId)
        "CANCELLED" -> getCancelledOrderSummary(orderId)
        "SHIPPED" -> getShippedOrderSummary(orderId)
        "PROCESSING" -> getProcessingOrderSummary(orderId)
        else -> getDefaultOrderSummary(orderId)
    }
}

// DELIVERED स्टेटस के लिए डमी डेटा
fun getDeliveredOrderSummary(orderId: String): OrderSummary {
    return OrderSummary(
        orderId = orderId,
        orderDate = Date(),
        items = listOf(
            OrderItem(
                id = "1",
                name = "Baby Cotton Dress",
                color = "Pink",
                size = "M",
                price = 29.99,
                originalPrice = 39.99,
                imageUrl = R.drawable.dummy_img
            )
        ),
        priceBreakdown = PriceBreakdown(
            amount = 29.99,
            shipping = 0.0,
            tax = 3.00,
            total = 32.99
        ),
        deliveryDetails = DeliveryDetails(
            name = "Vipin Khatri",
            address = "123 Baby Street",
            city = "New Delhi",
            state = "Delhi",
            zipCode = "110001",
            country = "India",
            phone = "+91 9876543210"
        ),
        trackingInfo = TrackingInfo(
            trackingNumber = "TRK987654321",
            estimatedDelivery = Date() // Already delivered
        ),
        paymentInfo = PaymentInfo(
            method = "Credit Card",
            lastFourDigits = "4242"
        ),
        trackingHistory = listOf(
            TrackingEvent(
                status = OrderStatus.ORDER_PLACED,
                title = "Order Placed",
                location = "New Delhi",
                description = "Your order has been placed",
                timestamp = Date(System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000) // 7 days ago
            ),
            TrackingEvent(
                status = OrderStatus.PROCESSING,
                title = "Processing",
                location = "Warehouse",
                description = "Your order is being processed",
                timestamp = Date(System.currentTimeMillis() - 6 * 24 * 60 * 60 * 1000)
            ),
            TrackingEvent(
                status = OrderStatus.SHIPPED,
                title = "Order Shipped",
                location = "Delhi Hub",
                description = "Your order has been shipped",
                timestamp = Date(System.currentTimeMillis() - 5 * 24 * 60 * 60 * 1000)
            ),
            TrackingEvent(
                status = OrderStatus.IN_TRANSIT,
                title = "In Transit",
                location = "In Transit",
                description = "Your order is on the way",
                timestamp = Date(System.currentTimeMillis() - 3 * 24 * 60 * 60 * 1000)
            ),
            TrackingEvent(
                status = OrderStatus.OUT_FOR_DELIVERY,
                title = "Out for Delivery",
                location = "Local Delivery Center",
                description = "Delivery partner is on the way",
                timestamp = Date(System.currentTimeMillis() - 2 * 24 * 60 * 60 * 1000)
            ),
            TrackingEvent(
                status = OrderStatus.DELIVERED,
                title = "Delivered",
                location = "Your Address",
                description = "Order has been delivered",
                timestamp = Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000) // 1 day ago
            )
        )
    )
}

// CANCELLED स्टेटस के लिए डमी डेटा
fun getCancelledOrderSummary(orderId: String): OrderSummary {
    return OrderSummary(
        orderId = orderId,
        orderDate = Date(),
        items = listOf(
            OrderItem(
                id = "1",
                name = "Baby Cotton Dress",
                color = "Blue",
                size = "L",
                price = 39.99,
                originalPrice = 49.99,
                imageUrl = R.drawable.dummy_img
            )
        ),
        priceBreakdown = PriceBreakdown(
            amount = 39.99,
            shipping = 5.0,
            tax = 4.00,
            total = 48.99
        ),
        deliveryDetails = DeliveryDetails(
            name = "Vipin Khatri",
            address = "123 Baby Street",
            city = "New Delhi",
            state = "Delhi",
            zipCode = "110001",
            country = "India",
            phone = "+91 9876543210"
        ),
        trackingInfo = TrackingInfo(
            trackingNumber = "TRK123456789",
            estimatedDelivery = Date(System.currentTimeMillis() + 2 * 24 * 60 * 60 * 1000)
        ),
        paymentInfo = PaymentInfo(
            method = "Credit Card",
            lastFourDigits = "1234"
        ),
        trackingHistory = listOf(
            TrackingEvent(
                status = OrderStatus.ORDER_PLACED,
                title = "Order Placed",
                location = "New Delhi",
                description = "Your order has been placed",
                timestamp = Date(System.currentTimeMillis() - 5 * 24 * 60 * 60 * 1000)
            ),
            TrackingEvent(
                status = OrderStatus.PROCESSING,
                title = "Processing",
                location = "Warehouse",
                description = "Your order is being processed",
                timestamp = Date(System.currentTimeMillis() - 4 * 24 * 60 * 60 * 1000)
            ),
            TrackingEvent(
                status = OrderStatus.SHIPPED,
                title = "Order Cancelled",
                location = "System",
                description = "Order has been cancelled",
                timestamp = Date(System.currentTimeMillis() - 3 * 24 * 60 * 60 * 1000)
            )
        )
    )
}

// SHIPPED स्टेटस के लिए डमी डेटा
fun getShippedOrderSummary(orderId: String): OrderSummary {
    return OrderSummary(
        orderId = orderId,
        orderDate = Date(),
        items = listOf(
            OrderItem(
                id = "1",
                name = "Soft Baby Shoes",
                color = "White",
                size = "6-12 Months",
                price = 19.99,
                imageUrl = R.drawable.dummy_img
            )
        ),
        priceBreakdown = PriceBreakdown(
            amount = 19.99,
            shipping = 0.0,
            tax = 2.00,
            total = 21.99
        ),
        deliveryDetails = DeliveryDetails(
            name = "Vipin Khatri",
            address = "123 Baby Street",
            city = "New Delhi",
            state = "Delhi",
            zipCode = "110001",
            country = "India",
            phone = "+91 9876543210"
        ),
        trackingInfo = TrackingInfo(
            trackingNumber = "TRK555555555",
            estimatedDelivery = Date(System.currentTimeMillis() + 2 * 24 * 60 * 60 * 1000)
        ),
        paymentInfo = PaymentInfo(
            method = "Credit Card",
            lastFourDigits = "5555"
        ),
        trackingHistory = listOf(
            TrackingEvent(
                status = OrderStatus.ORDER_PLACED,
                title = "Order Placed",
                location = "New Delhi",
                description = "Your order has been placed",
                timestamp = Date(System.currentTimeMillis() - 3 * 24 * 60 * 60 * 1000)
            ),
            TrackingEvent(
                status = OrderStatus.PROCESSING,
                title = "Processing",
                location = "Warehouse",
                description = "Your order is being processed",
                timestamp = Date(System.currentTimeMillis() - 2 * 24 * 60 * 60 * 1000)
            ),
            TrackingEvent(
                status = OrderStatus.SHIPPED,
                title = "Order Shipped",
                location = "Delhi Hub",
                description = "Your order has been shipped",
                timestamp = Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000)
            )
        )
    )
}

// PROCESSING स्टेटस के लिए डमी डेटा
fun getProcessingOrderSummary(orderId: String): OrderSummary {
    return OrderSummary(
        orderId = orderId,
        orderDate = Date(),
        items = listOf(
            OrderItem(
                id = "1",
                name = "Baby Cotton Dress",
                color = "Pink",
                size = "M",
                price = 29.99,
                originalPrice = 39.99,
                imageUrl = R.drawable.dummy_img
            ),
            OrderItem(
                id = "2",
                name = "Soft Baby Shoes",
                color = "White",
                size = "6-12 Months",
                price = 19.99,
                imageUrl = R.drawable.dummy_img
            )
        ),
        priceBreakdown = PriceBreakdown(
            amount = 49.98,
            shipping = 0.0,
            tax = 4.50,
            total = 54.48
        ),
        deliveryDetails = DeliveryDetails(
            name = "Vipin Khatri",
            address = "123 Baby Street",
            city = "New Delhi",
            state = "Delhi",
            zipCode = "110001",
            country = "India",
            phone = "+91 9876543210"
        ),
        trackingInfo = TrackingInfo(
            trackingNumber = "TRK987654321",
            estimatedDelivery = Date(System.currentTimeMillis() + 5 * 24 * 60 * 60 * 1000)
        ),
        paymentInfo = PaymentInfo(
            method = "Credit Card",
            lastFourDigits = "4242"
        ),
        trackingHistory = listOf(
            TrackingEvent(
                status = OrderStatus.ORDER_PLACED,
                title = "Order Placed",
                location = "New Delhi",
                description = "Your order has been placed",
                timestamp = Date(System.currentTimeMillis() - 3 * 60 * 60 * 1000) // 3 hours ago
            ),
            TrackingEvent(
                status = OrderStatus.PROCESSING,
                title = "Processing",
                location = "Warehouse",
                description = "Your order is being processed",
                timestamp = Date() // Currently processing
            )
        )
    )
}

// Default डमी डेटा
fun getDefaultOrderSummary(orderId: String): OrderSummary {
    // Your existing dummyOrderSummary() function
    return dummyOrderSummary().copy(orderId = orderId)
}
