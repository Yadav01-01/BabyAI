package com.compose.babyai.ui.screens.subscription

import android.R.attr.scaleX
import android.util.Log

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex

import androidx.navigation.NavHostController
import kotlin.math.absoluteValue
import com.compose.babyai.R
import com.compose.babyai.ui.component.CommonTopBar
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext

//SubscriptionScreen
data class SubscriptionPlan(
    val color: Color,
    val price: String,
    val features: List<String>
)

@Composable
fun SubscriptionScreen(navController: NavHostController) {
    val context = LocalContext.current
    val subscriptionPlans = listOf(
        SubscriptionPlan(
            color = Color(0xFF4DD4D4),
            price = "$199",
            features = listOf(
                "AI Outfit Try-on",
                "Limited Daily Style Suggestion",
                "Baby Closet Save Limit Number 10",
                "Limited Advanced Matching"
            )
        ),
        SubscriptionPlan(
            color = Color(0xFFFFC107),
            price = "$299",
            features = listOf(
                "AI Outfit Try-on",
                "Unlimited Daily Style Suggestion",
                "Baby Closet Save Limit Number 20",
                "Advanced Matching"
            )
        ),
        SubscriptionPlan(
            color = Color(0xFF4CAF50),
            price = "$399",
            features = listOf(
                "AI Outfit Try-on",
                "Premium Daily Style Suggestion",
                "Unlimited Baby Closet Save",
                "Premium Advanced Matching"
            )
        )
    )
    var cardOrder by remember {
        mutableStateOf(subscriptionPlans)
    }
    var currentIndex by remember { mutableStateOf(0) }
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
                .statusBarsPadding()
                .navigationBarsPadding()
        ) {


            CommonTopBar(
                title = "Add New Card",
                onBackClick = {
                    navController.navigateUp()
                },
                modifier = Modifier.fillMaxWidth()
            )



            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Unlock More Styles for Your Baby!",
                    fontSize = 18.sp,
                    color = Color(0xFF000000),
                    fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Personalized outfits, wardrobe scanning & premium looks",
                    fontSize = 15.sp,
                    color = Color(0xFFB0B0B0),
                    fontFamily = FontFamily(Font(R.font.nunito_regular)),
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = Modifier.height(30.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp),
                    contentAlignment = Alignment.Center
                ) {
                /*    SwipeableSubscriptionCards(
                        plans = subscriptionPlans,
                        currentIndex = currentIndex,
                        onSwipe = { currentIndex = it }
                    )*/
                    SwipeableSubscriptionCards(
                        plans = cardOrder,
                        onSwipe = { newList ->
                            cardOrder = newList
                        }
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

            }
        }
    }
}


@Composable
fun SubscriptionCard(
    plan: SubscriptionPlan,
    offset: Int,
    dragOffset: Float,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val animatedOffset by animateFloatAsState(
        targetValue = offset.toFloat(),
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "card_offset"
    )

    val scale = when (offset) {
        0 -> 0.81f - (dragOffset.absoluteValue / 3000f)
        1 -> 0.8f
        2 -> 0.8f
        else -> 0.7f
    }

    val rotation = when (offset) {
        1 -> -15f
        2 -> 15f
        else -> 0f
    }

    val translationX = when (offset) {
        0 -> dragOffset * 0.5f
        1 -> -40f
        2 -> 40f
        else -> 0f
    }

    /*    val translationY = when (offset) {
            1 -> 60f
            2 -> 60f
            else -> 0f
        }*/
    val translationY = when (offset) {
        0 -> 80f    // 🔽 top card thoda niche
        1 -> 80f
        2 -> 90f
        else -> 0f
    }

    val zIndex = when (offset) {
        0 -> 3f
        1 -> 2f
        2 -> 1f
        else -> 0f
    }

    Box(
        modifier = modifier
            .zIndex(zIndex)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                rotationZ = rotation
                this.translationX = translationX
                this.translationY = translationY
            },
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .width(310.dp)
                .height(440.dp),
            shape = RoundedCornerShape(32.dp),
            colors = CardDefaults.cardColors(
                containerColor = plan.color
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Crown icon
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .background(
                            Color(0x42FFFFFF),
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.ic_crown_icon),
                        contentDescription = "",
                        modifier = Modifier.size(19.dp)
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))

                // Price
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = plan.price,
                        fontSize = 29.sp,
                        fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                    Text(
                        text = "/month",
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(28.dp))

                // Features
                plan.features.forEach { feature ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_verified_icon),
                            contentDescription = null,
                            modifier = Modifier.size(21.dp)
                        )
                        Spacer(modifier = Modifier.width(15.dp))
                        Text(
                            text = feature,
                            color = Color.White,
                            fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp,
                            lineHeight = 20.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(35.dp))

                // Upgrade Button
                Button(
                    onClick = {
                        Toast
                            .makeText(
                                context,
                                "You selected plan ${plan.price}",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(35.dp)
                ) {
                    Text(
                        text = "Upgrade Plan",
                        color = Color(0xFF000000),
                        fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}
@Composable
fun SwipeableSubscriptionCards(
    plans: List<SubscriptionPlan>,
    onSwipe: (List<SubscriptionPlan>) -> Unit
) {
    var offsetX by remember { mutableStateOf(0f) }
    val swipeThreshold = 150f

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(plans) {
                detectDragGestures(
                    onDrag = { change, dragAmount ->
                        change.consume()
                        offsetX += dragAmount.x
                    },
                    onDragEnd = {
                        if (offsetX < -swipeThreshold) {
                            // 🔁 LEFT SWIPE → rotate left
                            val newOrder =
                                plans.drop(1) + plans.first()
                            onSwipe(newOrder)
                        } else if (offsetX > swipeThreshold) {
                            // 🔁 RIGHT SWIPE → rotate right
                         /*   val newOrder =
                                listOf(plans.last()) + plans.dropLast(1)
                            onSwipe(newOrder)*/
                            val newOrder =
                                plans.drop(1) + plans.first()
                            onSwipe(newOrder)
                        }
                        offsetX = 0f
                    }
                )
            }
    ) {
        // 🔥 BACK → FRONT
        for (i in 2 downTo 0) {
            SubscriptionCard(
                plan = plans[i],
                offset = i,
                dragOffset = if (i == 0) offsetX else 0f,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}


