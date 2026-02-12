package com.compose.babyai.ui.screens.profile.payment

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
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
import com.compose.babyai.ui.component.uiInput.CommonButton3
import com.compose.babyai.ui.component.uiInput.CommonTopBar

//PaymentMethodsScreen
data class PaymentCard(
    val id: Int,
    val holderName: String,
    val last4: String,
    val cvv: String,
    val expiry: String
)

@Composable
fun PaymentMethodsScreen(navController: NavHostController) {

    val cards = remember {
        listOf(
            PaymentCard(1, "Emily Smith", "1234", "569", "09/27"),
            PaymentCard(2, "Emily Smith", "5678", "590", "19/27"),
            PaymentCard(3, "Emily Smith", "9012", "569", "12/27")
        )
    }
    /*  val cards = remember {
          emptyList<PaymentCard>()
      }*/

    var selectedCardId by remember { mutableStateOf<Int?>(1) }
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
                title = "Payment Methods",
                onBackClick = {
                    navController.navigateUp()
                },
                modifier = Modifier.fillMaxWidth()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {

                if (cards.isEmpty()) {
                    // ✅ Empty state
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No data found",
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center,
                            fontFamily = FontFamily(Font(R.font.sf_medium)),
                            color = Color.Black
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        contentPadding = PaddingValues(
                            top = 16.dp,
                            bottom = 90.dp
                        )
                    ) {
                        items(cards) { card ->
                            PaymentCardItem(
                                card = card,
                                isSelected = card.id == selectedCardId,
                                onClick = { selectedCardId = card.id }
                            )
                        }
                    }
                }
            }
            Spacer(Modifier.height(10.dp))
            CommonButton3(
                title = "Add New Card",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
                    .height(52.dp),
                fontSize = 18.sp,
                radius = 40.dp,
                onClick = {
                   navController.navigate(Routes.AddNewCardScreen.route)
                }
            )
        }
    }
}

@Composable
fun PaymentCardItem(
    card: PaymentCard,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    val borderColor = if (isSelected) Color(0xFFFBD606) else Color.Transparent
    val blurRadius = if (isSelected) 0.dp else 2.dp
    val alphaValue = if (isSelected) 1f else 0.6f

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(40.dp))
            .padding(horizontal = 16.dp)
            .graphicsLayer { alpha = alphaValue }
            .blur(blurRadius)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { onClick() },
            shape = RoundedCornerShape(40.dp),
            border = BorderStroke(2.dp, borderColor),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            // 🔥 Tum yahan apni IMAGE laga sakte ho
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                Color(0xFFDCBE42), // Yellow Gold
                                Color(0xFFC24063), // Rose
                                Color(0xFFDA87BD)  // Pink Purple
                            )
                        )
                    )
                    .padding(20.dp)
            ) {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                    // Top Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = card.holderName,
                            color = Color(0xFF363636),
                            fontFamily = FontFamily(Font(R.font.sf_medium)),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium
                        )

                        // Card Brand Placeholder
                        /*    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.White.copy(alpha = 0.7f), CircleShape)
                    )*/
                        Image(
                            painter = painterResource(R.drawable.ic_card_icon),
                            contentDescription = "",
                            modifier = Modifier
                                .width(60.dp)
                                .height(37.dp)

                        )
                    }
                    Spacer(Modifier.height(10.dp))

                    // Card Number
                    Text(
                        text = "**** **** **** ${card.last4}",
                        fontFamily = FontFamily(Font(R.font.sf_medium)),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF363636)
                    )
                    Spacer(Modifier.weight(1f))
                    // Bottom Info
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                "CVV",
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.sf_medium)),
                                color = Color(0xFF363636),
                            )
                            Text(
                                card.cvv,
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.sf_regular)),
                                fontWeight = FontWeight.Normal,
                                color = Color(0xFF363636)
                            )
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                "Expires on",
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.sf_medium)),
                                color = Color(0xFF363636)
                            )
                            Text(
                                card.expiry,
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.sf_regular)),
                                fontWeight = FontWeight.Normal,
                                color = Color(0xFF363636)
                            )
                        }
                    }
                }
            }
        }
    }
}
