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
    object EditBabyProfile : Routes("editBabyProfile")
    object MyOrdersScreen : Routes("myOrdersScreen")
    object SettingsScreen : Routes("settingsScreen")
    object FrequentlyAskQuestionsScreen : Routes("frequentlyAskQuestionsScreen")
    object HelpSupportScreen : Routes("helpSupportScreen")
    object TermsAndConditionsScreen : Routes("termsAndConditionsScreen")
    object PrivacyPolicyScreen : Routes("privacyPolicyScreen")
    object AboutBabyfyScreen : Routes("aboutBabyfyScreen")
    object PaymentMethodsScreen : Routes("paymentMethodsScreen")
    object AllCategory : Routes("allCategory")
    object Payment : Routes("payment")
    object AddShippingAddress : Routes("addShippingAddress")
    object SavedAddress : Routes("savedAddress")
    object AddNewAddress : Routes("addNewAddress/{type}") {
        fun createRoute(type: String) = "addNewAddress/$type"
    }
    object AiBufferingScreen : Routes("aiBuffering")
    object AddNewCardScreen : Routes("addNewCardScreen")
    object SubscriptionScreen : Routes("subscriptionScreen")
    object TrackReturnScreen : Routes("trackReturnScreen")
    /*object OrderSummaryScreen : Routes("orderSummaryScreen")*/
    object OrderSummaryScreen : Routes("orderSummaryScreen/{orderId}/{status}") {
        fun createRoute(orderId: String, status: String) = "orderSummaryScreen/$orderId/$status"
    }
    object AiFullScreenTry : Routes("aiFullScreenTry")
    object AiScan : Routes("aiScan/{navFrom}"){
        fun createRoute(navFrom: String) = "aiScan/$navFrom"
    }
    object CamPreview : Routes("camPreview/{imageUri}") {
        fun createRoute(imageUri: String) = "camPreview/$imageUri"
    }
    object AddBabyProfile : Routes("addBaby")


}
