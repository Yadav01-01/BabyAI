package com.compose.babyai.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.compose.babyai.ui.screens.aiTry.AiTryScreen
import com.compose.babyai.ui.screens.auth.LoginScreen
import com.compose.babyai.ui.screens.auth.VerificationScreen
import com.compose.babyai.ui.screens.home.*
import com.compose.babyai.ui.screens.intro.OnBoardingScreen
import com.compose.babyai.ui.screens.intro.SplashScreen
import com.compose.babyai.ui.screens.main.MainScreen
import com.compose.babyai.ui.screens.authProfile.ProfileReadyScreen
import com.compose.babyai.ui.screens.authProfile.ProfileSetupScreen
import com.compose.babyai.ui.screens.cart.CartScreen
import com.compose.babyai.ui.screens.myOrder.MyOrdersScreen
import com.compose.babyai.ui.screens.payment.PaymentMethodsScreen
import com.compose.babyai.ui.screens.profile.BabyProfileScreen
import com.compose.babyai.ui.screens.profile.EditBabyProfile
import com.compose.babyai.ui.screens.settings.AboutBabyfyScreen
import com.compose.babyai.ui.screens.settings.FrequentlyAskQuestionsScreen
import com.compose.babyai.ui.screens.settings.HelpSupportScreen
import com.compose.babyai.ui.screens.settings.PrivacyPolicyScreen
import com.compose.babyai.ui.screens.settings.SettingsScreen
import com.compose.babyai.ui.screens.settings.TermsAndConditionsScreen
import com.compose.babyai.ui.screens.wardrobe.WardrobeScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = Routes.Splash.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(Routes.Splash.route) {
            SplashScreen(navController)
        }

        composable(Routes.OnBoarding.route) {
            OnBoardingScreen(navController)
        }

        composable(Routes.Login.route) {
            LoginScreen(navController)
        }

        composable(Routes.OtpVerify.route){
            VerificationScreen(navController)
        }

        composable(Routes.ProfileSetup.route) {
            ProfileSetupScreen(navController)
        }

        composable(Routes.ProfileReady.route) {
            ProfileReadyScreen(navController)
        }

        composable(Routes.Home.route) {
            HomeScreen(navController)
        }

        composable(Routes.ProductDetail.route) {
            ProductDetailScreen(navController)
        }

        composable(Routes.Search.route) {
            SearchScreen(navController)
        }

        composable(Routes.Wishlist.route) {
            WishlistScreen(navController)
        }

        composable(Routes.Main.route){
            MainScreen(navController)
        }

        composable(Routes.Wardrobe.route){
            WardrobeScreen(navController)
        }

        composable(Routes.BabyProfile.route){
            BabyProfileScreen(navController)
        }

        composable(Routes.Cart.route){
            CartScreen(navController)
        }

        composable(Routes.AiTry.route){
            AiTryScreen(navController)
        }

        composable(Routes.Filter.route){
            FilterScreen(navController)
        }

        composable(Routes.EditBabyProfile.route){
            EditBabyProfile(navController)
        }
        composable(Routes.MyOrdersScreen.route){
            MyOrdersScreen(navController)
        }
        composable(Routes.SettingsScreen.route){
            SettingsScreen(navController)
        }
        composable(Routes.FrequentlyAskQuestionsScreen.route){
            FrequentlyAskQuestionsScreen(navController)
        }
        composable(Routes.HelpSupportScreen.route){
            HelpSupportScreen(navController)
        }
        composable(Routes.TermsAndConditionsScreen.route){
            TermsAndConditionsScreen(navController)
        }

        composable(Routes.PrivacyPolicyScreen.route){
            PrivacyPolicyScreen(navController)
        }
        composable(Routes.AboutBabyfyScreen.route){
            AboutBabyfyScreen(navController)
        }
        composable(Routes.PaymentMethodsScreen.route){
            PaymentMethodsScreen(navController)
        }


    }
}
