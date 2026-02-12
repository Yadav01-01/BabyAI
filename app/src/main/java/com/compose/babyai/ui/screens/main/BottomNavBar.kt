package com.compose.babyai.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.compose.babyai.R
import com.compose.babyai.navigation.Routes

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    val items = listOf(
        BottomNavItemData(
            selectedIcon = R.drawable.selected_home_ic,
            unSelectedIcon = R.drawable.home_ic,
            route = Routes.Home.route
        ),
        BottomNavItemData(
            selectedIcon = R.drawable.selected_wardrobe_ic,
            unSelectedIcon = R.drawable.wordrobe_ic,
            route = Routes.Wardrobe.route
        ),
        BottomNavItemData(
            selectedIcon = R.drawable.selected_ai_try,
            unSelectedIcon = R.drawable.ai_try,
            route = Routes.AiTry.route,
            isCenter = true
        ),
        BottomNavItemData(
            selectedIcon = R.drawable.selected_cart_ic,
            unSelectedIcon = R.drawable.cart_ic,
            route = Routes.Cart.route
        ),
        BottomNavItemData(
            selectedIcon = R.drawable.selected_profile_ic,
            unSelectedIcon = R.drawable.profile_ic,
            route = Routes.BabyProfile.route
        )
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(131.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(88.dp)
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            items.forEach { item ->

                val isSelected = currentRoute?.startsWith(item.route) == true
                val iconRes =
                    if (isSelected) item.selectedIcon else item.unSelectedIcon

                Box(
                    modifier = Modifier
                        .size(if (item.isCenter) 76.dp else 56.dp)
                        .clip(CircleShape)
                        .clickable {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(iconRes),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

            }
        }
    }
}

data class BottomNavItemData(
    val selectedIcon: Int,
    val unSelectedIcon: Int,
    val route: String,
    val isCenter: Boolean = false
)
