package com.compose.babyai.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.compose.babyai.R
import com.compose.babyai.ui.theme.PrimaryColor
import kotlin.collections.listOf
import kotlin.ranges.contains
import kotlin.text.get

@Composable
fun FilterScreen(navController: NavHostController) {
    var selectedCategory by remember { mutableStateOf("Price") }
    val selectedOptions = remember { mutableStateMapOf<String, SnapshotStateList<String>>() }


    val categories = listOf(
        "Price", "Brand", "Gender", "Discount", "Age & Size", "Fabric",
        "Pattern", "Occasion", "Color", "Ratings", "Fit", "New Arrivals", "Type", "Offers"
    )

    val optionsMap = mapOf(
        "Price" to listOf("$ 199 and above", "$ 249 and below", "$ 149 - $ 199", "$ 299 - $ 499", "$ 549 - $ 699", "$ 749 - $ 999"),
        "Gender" to listOf("Boy", "Girl", "Unisex"),
        "Brand" to listOf("BabyGoy", "Little Star", "TinyTots", "Mommy's Choice"),
        "Color" to listOf("Blue", "Red", "Green", "Pink", "White", "Black"),
        "Discount" to listOf("10 % Off", "20 % Off", "30 % Off", "40 % Off", "50 % Off", "60 % Off", "70 % Off"),
        "Age & Size" to listOf("New Born", "3-6 month", "6-9 months", "9-12 months","1-2 year","2-4 year","XS","M","L","XL","XXL"),
        "Fabric" to listOf( "Organic Cotton", "Bamboo Fabric", "Muslin Cotton","Cotton Lycra",
            "Modal", "Tencel", "Fleece (Baby Soft)", "Knitted Cotton",
            "Satin (Soft Baby Satin)", "Soft Jersey Knit", "Ribbed Knit Fabric", "Linen-Cotton Blend",
            "Sherpa", "Flannel", "Soft Nylon", "Felt (Soft Baby-Safe)", "Velvet"),
        "Pattern" to listOf( "Solid", "Stripes", "Checks", "Polka Dots",
            "Color Block", "Ombre", "Gradient", "Animals (bears, bunnies, ducks, elephants)", "Stars",
            "Clouds", "Cars / Trucks", "Dinosaurs", "Moons", "Balloons"),
        "Occasion" to listOf( "Daily Wear", "Playtime", "Sleepwear / Nightwear", "Casual Outings", "Summer Wear", "Winter Wear", "Monsoon Wear", "Holiday / Vacation", "Birthday", "Cake Smash", "Monthly Milestones (1–12 months)", "Baby Shower", "Photoshoot / Studio Shoot",
            "Welcome Home / Naming Ceremony"),
        "Ratings" to listOf( "5 ⭐", "4 ⭐ ", "3 ⭐ ", "2 ⭐ ", "1 ⭐ "),
        "Fit" to listOf( "Snap Fit", "Regular Onesie Fit", "Envelope Neck Fit", "Kimono Wrap Fit", "Stretch Fit", "Diaper-Friendly Fit", "Elastic Waist Fit", "Jogger Fit", "Legging Fit", "A-Line Fit", "Loose Fit", "Comfort Fit",
            "Cartoon Style Fit", "Hooded Comfort Fit", "Pullover Fit", "Jacket Fleece Fit"),
        "New Arrivals" to listOf(    "New Rompers",
            "New Onesie Fit", "Seasonal Dresses", "New Co-ord Sets", "Festive Outfits",
            "Party Wear", "Exclusive New Collection", "Headbands", "Caps & Hats", "Mittens & Booties",
            "Gloves & Swaddles", "Hair Accessories", "Soft Toys (Safe & non-toxic)", "Gift Sets",
            "Winter Footwear", "Socks Collection"),
        "Type" to listOf( "Rompers", "Bodysuits", "Onesies",
            "Sleepwear / Nightwear", "T-Shirts", "Shorts", "Dresses", "Jackets & Sweaters",
            "Co-ord Sets", "Traditional Wear", "Party Wear", "Casual Wear", "Seasonal Wear (Summer / Winter)"),
        "Offers" to listOf(    "New User Offer",
            "Flat ₹249 Off",
            "Buy 1 Get 1",
            "Buy 2 Get 1",
            "Season Sale",
            "Flash Deals",
            "Clearance Sale",
            "Festive Offer",
            "Limited Time Offer",
            "Combo Deals",
            "Bundle & Save")

    )

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

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
        ) {
            // Header
            FilterHeader(
                onBack = { navController.popBackStack() },
                onClose = { navController.popBackStack() }
            )

            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                // Left Column: Categories
                LazyColumn(
                    modifier = Modifier
                        .weight(0.35f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFFFFFBE6)) // Light yellowish background
                        .padding(top = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(categories) { category ->
                        CategoryItem(
                            title = category,
                            isSelected = selectedCategory == category,
                            onClick = { selectedCategory = category }
                        )
                    }
                }

                // Right Column: Options
                Box(
                    modifier = Modifier
                        .weight(0.65f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White)
                ) {

                    val currentOptions = optionsMap[selectedCategory] ?: emptyList()

                    // 🔹 Scrollable list
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp).padding(bottom = 60.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(currentOptions) { option ->
                            val isChecked =
                                selectedOptions[selectedCategory]?.contains(option) == true

                            FilterOptionItem(
                                title = option,
                                isChecked = selectedOptions[selectedCategory]?.contains(option) == true,
                                onCheckedChange = { checked ->
                                    val list = selectedOptions.getOrPut(selectedCategory) {
                                        mutableStateListOf()
                                    }

                                    if (checked) {
                                        if (!list.contains(option)) list.add(option)
                                    } else {
                                        list.remove(option)
                                    }
                                }
                            )

                        }
                    }

                    // 🔹 Sticky footer
                    FilterFooter(
                        resultCount = 1477,
                        onApply = { navController.popBackStack() },
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                    )
                }

            }
        }
    }
}

@Composable
fun FilterHeader(onBack: () -> Unit, onClose: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Back button with "double circle" effect
            IconButton(onBack) {
                Icon(
                    painter = painterResource(id = R.drawable.draw_back_ic),
                    contentDescription = "Back",
                    modifier = Modifier.size(60.dp),
                    tint = Color.Black
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Filters",
                fontSize = 22.sp,
                fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        }

        IconButton(onClick = onClose, modifier = Modifier.size(44.dp).background(color = Color.White, shape = CircleShape)) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                modifier = Modifier.size(20.dp),
                tint = Color.Black
            )
        }
    }
}

@Composable
fun CategoryItem(title: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) Color.Black else Color.Transparent)
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            fontSize = 15.sp,
            fontFamily = FontFamily(Font(R.font.baloo2_regular )),
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
            color = if (isSelected) Color.White else Color(0xFF848484)
        )
    }
}

@Composable
fun FilterOptionItem(title: String, isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCheckedChange(!isChecked) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(if (isChecked) Color(0xFFFFD147) else Color.Transparent)
                .border(
                    width = 1.dp,
                    color = if (isChecked) Color(0xFFFFD147) else Color.LightGray,
                    shape = RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            if (isChecked) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = title,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.nunito_regular)),
            color = Color.Black
        )
    }
}

@Composable
fun FilterFooter(modifier: Modifier = Modifier, resultCount: Int, onApply: () -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 5.dp, vertical = 5.dp)
            .navigationBarsPadding(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(56.dp)
                .border(1.dp, Color(0xFFB0B0B0), RoundedCornerShape(40.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "$resultCount Result",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                color = Color(0xFFB0B0B0)
            )
        }

        Button(
            onClick = onApply,
            modifier = Modifier
                .weight(1f)
                .height(56.dp),
            shape = RoundedCornerShape(40.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)
        ) {
            Text(
                text = "Apply",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }
    }
}

fun <T> mutableStateSetOf(vararg elements: T) = elements.toMutableSet()
