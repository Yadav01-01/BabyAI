package com.compose.babyai.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.compose.babyai.navigation.NavGraph
import com.compose.babyai.navigation.Routes
import com.compose.babyai.ui.screens.aiTry.AiTryScreen
import com.compose.babyai.ui.screens.cart.CartScreen
import com.compose.babyai.ui.screens.home.HomeScreen
import com.compose.babyai.ui.screens.profile.babyProfile.BabyProfileScreen
import com.compose.babyai.ui.screens.wardrobe.WardrobeScreen

@Composable
fun MainScreen(rootNavController: NavHostController) {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomBarRoutes = remember {
        setOf(
            Routes.Home.route,
            Routes.Wardrobe.route,
            Routes.AiTry.route,
            Routes.Cart.route,
            Routes.BabyProfile.route
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,
            contentWindowInsets = WindowInsets(0),
            bottomBar = {
                if (
                    currentRoute != null &&
//                    bottomBarRoutes.any { currentRoute.startsWith(it) }
                    bottomBarRoutes.contains(currentRoute)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(131.dp))
                            .padding(horizontal = 20.dp)
                            .padding(bottom = 20.dp)
                            .zIndex(1f)
                    ) {
                        BottomNavigationBar(
                            navController = navController
                        )
                    }
                }
            }
        ) { innerPadding ->

            // Completely transparent content
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    /*.padding(innerPadding)*/
                    .background(Color.Transparent)
            )
            {
                NavGraph(
                    navController = navController,
                    startDestination = Routes.Home.route
                )
            }
        }
    }

}

/*
@Composable
fun MainScreen(navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomBarRoutes = setOf(
        Routes.Home.route,
        Routes.Wardrobe.route,
        Routes.AiTry.route,
        Routes.Cart.route,
        Routes.BabyProfile.route
    )

    Scaffold(
        bottomBar = {
            if (bottomBarRoutes.contains(currentRoute)) {
                BottomNavigationBar(modifier = Modifier,navController)
            }
        }
    ) { innerPadding ->

        Box(modifier = Modifier.padding(innerPadding)) {

            when (currentRoute) {

                Routes.Home.route -> HomeScreen(navController)
                Routes.Wardrobe.route -> WardrobeScreen(navController)
                Routes.AiTry.route -> AiTryScreen(navController)
                Routes.Cart.route -> CartScreen(navController)
                Routes.BabyProfile.route -> BabyProfileScreen(navController)

            }
        }
    }
}*/
