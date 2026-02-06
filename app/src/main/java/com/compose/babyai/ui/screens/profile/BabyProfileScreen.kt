package com.compose.babyai.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.compose.babyai.R
import com.compose.babyai.navigation.Routes
import com.compose.babyai.ui.component.LogoutButton
import com.compose.babyai.ui.component.NotificationItemFromVM
import com.compose.babyai.ui.component.ProfileHeaderCard
import com.compose.babyai.ui.component.QuickActionItem
import com.compose.babyai.ui.component.SectionTitle
import com.compose.babyai.ui.component.SettingItem
import com.compose.babyai.ui.dialog.LogOutDialog
import com.compose.babyai.viewModel.BabyProfileViewModel

@Composable
fun BabyProfileScreen(navController: NavHostController) {

    val viewModel: BabyProfileViewModel = viewModel()
    val state by viewModel.uiState.collectAsState()
    var showLogoutDialog by remember { mutableStateOf(false) }

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
                .padding(horizontal = 26.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Profile",
                fontSize = 22.sp,
                color = Color.Black,
                fontFamily = FontFamily(Font(R.font.quicksand_semibold))
            )

            Spacer(modifier = Modifier.height(16.dp))

            ProfileHeaderCard(
                state = state,
                babies = state.babies,
                onBabyClick = {

                },
                onEditClick = {
                    navController.navigate(Routes.EditBabyProfile.route)
                },
                onAddBabyClick = {

                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            SectionTitle("Quick Actions")

            QuickActionItem(
                icon = R.drawable.ic_blue_heart_icon,
                title = "My Wishlist",
                subtitle = "${state.wishlistCount} Outfits Saved",
                onNextScreenClick = {
//Wishlist
                    navController.navigate(Routes.Wishlist.route)
                }
            )

            QuickActionItem(
                icon = R.drawable.ic_blue_box_icon,
                title = "My Orders",
                subtitle = "View order history",
                onNextScreenClick = {
                    navController.navigate(Routes.MyOrdersScreen.route)
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            SectionTitle("Settings")

            SettingItem(R.drawable.ic_subscriptions_icon , "My Subscriptions", {
                //SubscriptionScreen
                navController.navigate(Routes.SubscriptionScreen.route)
            })
            SettingItem(R.drawable.ic_payment_card_icon, "Payment Methods", {
                //PaymentMethodsScreen
                navController.navigate(Routes.PaymentMethodsScreen.route)
            })
            SettingItem(R.drawable.ic_customer_call_icon, "Help & Support", {
                //HelpSupportScreen
                navController.navigate(Routes.HelpSupportScreen.route)
            })
            SettingItem(R.drawable.ic_setting_icon, "App Settings", {
                navController.navigate(Routes.SettingsScreen.route)
            })

            NotificationItemFromVM(
                enabled = state.notificationEnabled,
                onToggle = viewModel::toggleNotification
            )

            Spacer(modifier = Modifier.height(30.dp))
            LogoutButton(onLogOutClick={
                showLogoutDialog= true
            })
            Spacer(modifier = Modifier.height(140.dp))
        }
    }
    if (showLogoutDialog) {
        LogOutDialog(onDismiss = {showLogoutDialog = false},
            onLogout = {showLogoutDialog = false
                navController.navigate(Routes.Login.route)
            }
        )
    }
}


