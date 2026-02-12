package com.compose.babyai.ui.screens.profile.myOrder.trackReturn

import com.compose.babyai.R
import com.compose.babyai.ui.screens.profile.myOrder.OrderItem
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.compose.babyai.ui.component.uiInput.CommonTopBar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class ReturnTrackingEvent(
    val status: ReturnStatus,
    val title: String,
    val location: String,
    val description: String,
    val timestamp: Date
)

data class TrackReturnUiState(
    val statusCard: ReturnStatusCardData,
    val refundCard: RefundCardData,
    val items: List<OrderItem>,
    val trackingHistory: List<ReturnTrackingEvent>
)

data class ReturnStatusCardData(
    val title: String,
    val subtitle: String,
    val description: String,
    val lastUpdated: Date,
    val icon: Int
)

data class RefundCardData(
    val title: String,
    val paymentMethod: String,
    val expectedDate: String,
    val icon: Int
)


@Composable
fun TrackReturnScreen(
    navController: NavHostController
) {
    val returnHistory = dummyReturnHistory()
    val uiState = remember {
        TrackReturnUiState(
            statusCard = ReturnStatusCardData(
                title = "Return in Progress",
                subtitle = "Quality Inspection",
                description = "Your return has been received and inspected.",
                lastUpdated = Date(),
                icon = R.drawable.ic_box_white_bg_icon
            ),
            refundCard = RefundCardData(
                title = "Refund Amount",
                paymentMethod = "Visa ending in 4242",
                expectedDate = "Expected by Dec 10, 2024",
                icon = R.drawable.ic_return_track_card_icon
            ),
            items = dummyReturnItems(),
            trackingHistory = dummyReturnHistory()
        )
    }
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
                title = "Track Return",
                onBackClick = { navController.navigateUp() }
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                //.padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(19.dp),
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {

                item { ReturnStatusCard(uiState.statusCard) }

                item { RefundAmountCard(uiState.refundCard) }
                item {
                    Spacer(Modifier.height(8.dp))
                    HorizontalDivider(color = Color(0xFFBFBFBF))
                }
                item {
                    Text(
                        "Package Contents",
                        fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                        fontSize = 17.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 25.dp)
                    )
                }

                items(dummyReturnItems()) {
                    ReturnItemCard(it)
                }

                item {
                    Spacer(Modifier.height(12.dp))
                    HorizontalDivider(color = Color(0xFFBFBFBF))

                }

                item {
                    Text(
                        "Return Tracking History",
                        fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 25.dp, top = 8.dp)
                    )
                }

                itemsIndexed(returnHistory) { index, event ->
                    ReturnTrackingItem(
                        event = event,
                        isLast = index == returnHistory.lastIndex
                    )
                }
            }
        }
    }
}

@Composable
fun ReturnStatusCard(data: ReturnStatusCardData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFB9EFEF)),
        //shape = RoundedCornerShape(20.dp)
    ) {
        Column(modifier = Modifier.padding(vertical = 16.dp, horizontal = 30.dp)) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(data.icon/*R.drawable.ic_box_white_bg_icon*/),
                    contentDescription = null,
                    modifier = Modifier.size(36.dp)
                )
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(
                        data.title/*"Return in Progress"*/,
                        fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                        fontSize = 15.sp,
                        color = Color(0xFF1C1C1C)
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        data.subtitle/*"Quality Inspection"*/,
                        fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                        fontSize = 13.sp,
                        color = Color(0xFF828282)
                    )
                }
            }

            Spacer(Modifier.height(8.dp))

            Text(
                data.description/*"Your return has been received and inspected. Refund processing has started."*/,
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                color = Color(0xFF1C1C1C)
            )

            Spacer(Modifier.height(6.dp))

            Text(
                "Last updated: ${
                    SimpleDateFormat("MMM dd, yyyy h:mm a", Locale.getDefault())
                        .format(data.lastUpdated)
                }" /*"Last updated: Dec 8, 2024 at 4:00 PM"*/,
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.quicksand_medium)),
                color = Color(0xFF1C1C1C)
            )
        }
    }
}

@Composable
fun RefundAmountCard(data: RefundCardData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE9FAFA)),
        shape = RoundedCornerShape(15.dp)
    ) {
        Row(
            modifier = Modifier.padding(15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                Image(
                    painter = painterResource(data.icon/*R.drawable.ic_return_track_card_icon*/),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(
                        data.title/*"Refund Amount"*/,
                        fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                        fontSize = 16.sp,
                        color = Color(0xFF1C1C1C)
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        data.paymentMethod/*"Visa ending in 4242"*/,
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                        color = Color(0xFF828282)
                    )
                }
            }

            Icon(
                painter = painterResource(R.drawable.ic_copy_icons),
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                tint = Color.Unspecified
            )
        }

        Text(
            data.expectedDate/*"Expected in your account by Dec 10, 2024"*/,
            fontSize = 13.sp,
            fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
            color = Color(0xFF1C1C1C),
            modifier = Modifier.padding(start = 15.dp, bottom = 15.dp)
        )
    }
}

@Composable
fun ReturnItemCard(item: OrderItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier
                .height(107.dp)
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Image(
                painter = painterResource(item.imageUrl ?: R.drawable.dummy_img),
                contentDescription = null,
                modifier = Modifier
                    .width(115.dp)
                    .height(95.dp)
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop
            )


            Column(modifier = Modifier.weight(1f)) {
                Text(
                    item.name,
                    fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                    color = Color(0xFF1C1C1C),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(5.dp))
                Text(
                    "2 items",
                    fontFamily = FontFamily(Font(R.font.outfit_regular)),
                    fontWeight = FontWeight.Normal,
                    fontSize = 11.sp,
                    color = Color(0xFF000000)
                )
                Spacer(Modifier.weight(1f))
                Text(
                    "$${item.price}",
                    fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color(0xFF1ECBCC)
                )
            }
        }
    }
}

/*@Composable
fun ReturnTrackingItem(
    event: TrackingEvent,
    isLast: Boolean
) {
    Row(modifier = Modifier.fillMaxWidth()) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(40.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_order_icon_track),
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )

            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(3.dp)
                        .height(50.dp)
                        .background(Color(0xFFB9EFEF))
                )
            }
        }

        Column(
            modifier = Modifier.padding(start = 12.dp, bottom = 20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    event.title,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.varela_round))
                )
                Text(
                    SimpleDateFormat("h:mm a", Locale.getDefault()).format(event.timestamp),
                    fontSize = 11.sp,
                    color = Color.Gray
                )
            }

            Text(
                event.location,
                fontSize = 12.sp,
                color = Color(0xFF828282)
            )

            Text(
                event.description,
                fontSize = 12.sp,
                color = Color(0xFF828282)
            )

            Text(
                SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(event.timestamp),
                fontSize = 11.sp,
                color = Color(0xFF0B4747)
            )
        }
    }
}*/
@Composable
fun ReturnTrackingItem(
    event: ReturnTrackingEvent,
    isLast: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp)
            .padding(bottom = if (isLast) 0.dp else 16.dp)
    ) {

        // LEFT SIDE (ICON + LINE)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(45.dp)
        ) {

            Image(
                painter = painterResource(getReturnIcon(event.status)),
                contentDescription = null,
                modifier = Modifier.size(33.dp)
            )

            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .height(38.dp)
                        .background(Color(0xFFB9EFEF))
                )
            }
        }

        // RIGHT SIDE (TEXT)
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    event.title,
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.varela_round)),
                    color = Color(0xFF1C1C1C)
                )

                Text(
                    SimpleDateFormat("h:mm a", Locale.getDefault())
                        .format(event.timestamp),
                    fontFamily = FontFamily(Font(R.font.varela_round)),
                    fontSize = 10.sp,
                    color = Color(0xFF999999)
                )
            }

            Text(
                event.location,
                fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                fontWeight = FontWeight.SemiBold,
                fontSize = 11.sp,
                color = Color(0xFF828282),
                modifier = Modifier.padding(top = 2.dp)
            )

            Text(
                event.description,
                fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                fontWeight = FontWeight.SemiBold,
                fontSize = 10.sp,
                color = Color(0xFF828282),
                modifier = Modifier.padding(top = 4.dp)
            )

            Text(
                SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
                    .format(event.timestamp),
                fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                fontWeight = FontWeight.SemiBold,
                fontSize = 10.sp,
                color = Color(0xFF0B4747),
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

fun dummyReturnHistory() = listOf(
    ReturnTrackingEvent(
        ReturnStatus.RETURN_REQUESTED,
        "Return Requested",
        "Online Portal",
        "Return request submitted and approved",
        Date()
    ),
    ReturnTrackingEvent(
        ReturnStatus.LABEL_GENERATED,
        "Return Label Generated",
        "Babyfy Store",
        "Return shipping label sent to your email",
        Date()
    ),
    ReturnTrackingEvent(
        ReturnStatus.PACKAGE_PICKED,
        "Package Picked Up",
        "123 Main Street, Apt 4B",
        "Carrier picked up the return package",
        Date()
    ),
    ReturnTrackingEvent(
        ReturnStatus.IN_TRANSIT,
        "In Transit to Warehouse",
        "Distribution Hub - Newark, NJ",
        "Return package is on the way back",
        Date()
    ),
    ReturnTrackingEvent(
        ReturnStatus.RECEIVED_AT_WAREHOUSE,
        "Returns Center - New York, NY",
        "Online Portal",
        "Package received and inspection started",
        Date()
    ),
    ReturnTrackingEvent(
        ReturnStatus.QUALITY_INSPECTION,
        "Returns Center - New York, NY",
        "Carrier Facility",
        "Items inspected and approved for refund",
        Date()
    ),
    ReturnTrackingEvent(
        ReturnStatus.REFUND_PROCESSING,
        "Refund Processing",
        "Finance Department",
        "Refund initiated to original payment method",
        Date()
    ),
    ReturnTrackingEvent(
        ReturnStatus.REFUND_COMPLETED,
        "Refund Completed",
        "Your Bank Account",
        "Refund completed. Please allow 3-5 business days for funds to appear",
        Date()
    )
)

fun dummyReturnItems() = listOf(
    OrderItem("1", "BabyStyle Co. Dress", "Pink", "M", 49.99, imageUrl = R.drawable.dummy_img),
    OrderItem("2", "Kids Wear Set", "Blue", "L", 64.99, imageUrl = R.drawable.dummy_img)
)


enum class ReturnStatus {
    RETURN_REQUESTED,
    LABEL_GENERATED,
    PACKAGE_PICKED,
    IN_TRANSIT,
    RECEIVED_AT_WAREHOUSE,
    QUALITY_INSPECTION,
    REFUND_PROCESSING,
    REFUND_COMPLETED
}

@Composable
fun getReturnIcon(status: ReturnStatus): Int {
    return when (status) {
        ReturnStatus.RETURN_REQUESTED -> R.drawable.ic_order_icon_track
        ReturnStatus.LABEL_GENERATED -> R.drawable.ic_order_icon_track
        ReturnStatus.PACKAGE_PICKED -> R.drawable.ic_shipped_icon_trac
        ReturnStatus.IN_TRANSIT -> R.drawable.ic_shipped_icon_trac
        ReturnStatus.RECEIVED_AT_WAREHOUSE -> R.drawable.ic_order_icon_track
        ReturnStatus.QUALITY_INSPECTION -> R.drawable.ic_quality_check_icon
        ReturnStatus.REFUND_PROCESSING -> R.drawable.ic_refund_processing_icon
        ReturnStatus.REFUND_COMPLETED -> R.drawable.ic_develered_icon_track
    }
}