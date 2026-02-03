package com.compose.babyai.navigation

sealed class Routes(val route: String) {

    object Splash : Routes("splash")
    object OnBoarding : Routes("onboarding")
    object Login : Routes("login")
    object OtpVerify : Routes("otpVerify")
    object ProfileSetup : Routes("profileSetup")
    object ProfileReady : Routes("profileReady")
    object Home : Routes("home")
    object ProductDetail : Routes("productDetail")
    object Search : Routes("search")
    object Wishlist : Routes("wishlist")
    object Main : Routes("main")
    object Wardrobe : Routes("wardrobe")
    object BabyProfile : Routes("babyProfile")
    object Cart : Routes("cart")
    object AiTry : Routes("aiTry")
    object Filter : Routes("filter")
    object AllCategory : Routes("allCategory")
    object Payment : Routes("payment")

}
