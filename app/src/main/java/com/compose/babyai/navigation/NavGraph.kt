package com.compose.babyai.navigation

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.compose.babyai.ui.screens.aiTry.AIBufferingScreen
import com.compose.babyai.ui.screens.aiTry.AiTryScreen
import com.compose.babyai.ui.screens.aiTry.CamPreviewScreen
import com.compose.babyai.ui.screens.aiTry.FullScreenAITryScreen
import com.compose.babyai.ui.screens.aiTry.ScanScreen
import com.compose.babyai.ui.screens.auth.LoginScreen
import com.compose.babyai.ui.screens.auth.VerificationScreen
import com.compose.babyai.ui.screens.home.*
import com.compose.babyai.ui.screens.intro.OnBoardingScreen
import com.compose.babyai.ui.screens.intro.SplashScreen
import com.compose.babyai.ui.screens.main.MainScreen
import com.compose.babyai.ui.screens.authProfile.ProfileReadyScreen
import com.compose.babyai.ui.screens.authProfile.ProfileSetupScreen
import com.compose.babyai.ui.screens.cart.CartScreen
import com.compose.babyai.ui.screens.cart.paymentAndShipping.AddNewAddressScreen
import com.compose.babyai.ui.screens.profile.myOrder.MyOrdersScreen
import com.compose.babyai.ui.screens.profile.payment.PaymentMethodsScreen
import com.compose.babyai.ui.screens.cart.paymentAndShipping.PaymentScreen
import com.compose.babyai.ui.screens.cart.paymentAndShipping.SavedAddressScreen
import com.compose.babyai.ui.screens.cart.paymentAndShipping.ShippingAddressScreen
import com.compose.babyai.ui.screens.profile.babyProfile.AddBabyProfile
import com.compose.babyai.ui.screens.profile.myOrder.OrderSummaryScreen
import com.compose.babyai.ui.screens.profile.payment.AddNewCardScreen
import com.compose.babyai.ui.screens.profile.babyProfile.BabyProfileScreen
import com.compose.babyai.ui.screens.profile.babyProfile.EditBabyProfile
import com.compose.babyai.ui.screens.profile.settings.AboutBabyfyScreen
import com.compose.babyai.ui.screens.profile.settings.FrequentlyAskQuestionsScreen
import com.compose.babyai.ui.screens.profile.settings.HelpSupportScreen
import com.compose.babyai.ui.screens.profile.settings.PrivacyPolicyScreen
import com.compose.babyai.ui.screens.profile.settings.SettingsScreen
import com.compose.babyai.ui.screens.profile.settings.TermsAndConditionsScreen
import com.compose.babyai.ui.screens.profile.subscription.SubscriptionScreen
import com.compose.babyai.ui.screens.profile.myOrder.trackReturn.TrackReturnScreen
import com.compose.babyai.ui.screens.wardrobe.WardrobeScreen
import androidx.core.net.toUri

@RequiresApi(Build.VERSION_CODES.O)
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

        composable( Routes.AllCategory.route){
            CategoryScreen(navController)
        }

        composable(Routes.Payment.route) {
            PaymentScreen(navController)
        }

        composable(Routes.AddShippingAddress.route) {
            ShippingAddressScreen(navController)
        }

        composable(Routes.SavedAddress.route) {
            SavedAddressScreen(navController)
        }

        composable(
            route = Routes.AddNewAddress.route,
            arguments = listOf(navArgument("type") {
                    type = NavType.StringType
                    nullable = true })
        ) { backStackEntry ->
            val type = backStackEntry.arguments?.getString("type") ?: ""
            AddNewAddressScreen(navController = navController, type = type)
        }

        composable(Routes.AiBufferingScreen.route){
            AIBufferingScreen(navController)
        }
        composable(Routes.AddNewCardScreen.route){
            AddNewCardScreen(navController)
        }
        composable(Routes.SubscriptionScreen.route){
            SubscriptionScreen(navController)
        }
        composable(Routes.TrackReturnScreen.route){
            TrackReturnScreen(navController)
        }

        composable(
            route = Routes.OrderSummaryScreen.route,
            arguments = listOf(
                navArgument("orderId") { type = NavType.StringType },
                navArgument("status") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val orderId = backStackEntry.arguments?.getString("orderId") ?: ""
            val status = backStackEntry.arguments?.getString("status") ?: ""
            OrderSummaryScreen(
                navController = navController,
                orderId = orderId,
                orderStatus = status
            )
        }

        composable(Routes.AiFullScreenTry.route){
            FullScreenAITryScreen(navController)
        }

        composable(
            route = Routes.AiScan.route,
            arguments = listOf(navArgument("navFrom") { type = NavType.StringType })
        ) { backStackEntry ->
            val navFrom = backStackEntry.arguments?.getString("navFrom") ?: ""
            ScanScreen(navController,navFrom)
        }

        composable(
            route = Routes.CamPreview.route,
            arguments = listOf(navArgument("imageUri") { type = NavType.StringType })
        ) { backStackEntry ->
            val imageUri = backStackEntry.arguments?.getString("imageUri") ?: ""
            CamPreviewScreen(navController, Uri.decode(imageUri).toUri())
        }

        composable(Routes.AddBabyProfile.route) {
            AddBabyProfile(navController)
        }


    }
}
