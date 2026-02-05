package com.compose.babyai.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.compose.babyai.R
import com.compose.babyai.navigation.Routes
import com.compose.babyai.ui.component.SearchBar
import com.compose.babyai.ui.theme.PrimaryColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    var searchQuery by remember { mutableStateOf("") }

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

        Column(modifier = Modifier.fillMaxSize().statusBarsPadding()) {
            // Header
            HomeHeader(
                onFavIconClick = { /* Handle favorite icon click */ },
                onClickProfile = { /* Handle profile click */},
                onClickScan = { /* Handle scan click */ }
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
             /*   contentPadding = PaddingValues( vertical = 10.dp)*/
            ) {
                // Search Bar
                item {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50.dp))
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                navController.navigate(Routes.Search.route)
                            }
                            .padding(horizontal = 20.dp)
                    ){
                        SearchBar(searchQuery = searchQuery,
                            onSearchQueryChange = { searchQuery = it },
                            placeholderText = "Search outfits, brands or categories",
                            icon = painterResource(R.drawable.search_ic),
                            readOnly = true,
                            enabled = false
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }

                // Categories
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Try-ons",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.quicksand_bold)),
                            color = Color(0XFF272727)
                        )
                        Text(
                            text = "See All",
                            fontSize = 14.sp,
                            color = Color(0XFF272727),
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily(Font(R.font.quicksand_regular)),
                            modifier = Modifier.clickable { navController.navigate(Routes.AllCategory.route) }
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    CategoryList(modifier = Modifier.padding(horizontal = 5.dp))
                    Spacer(modifier = Modifier.height(24.dp))
                }

                // Banner
                item {
                    HomeBanner()
                    Spacer(modifier = Modifier.height(24.dp))
                }

                // Shop By Categories Header
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Shop By Categories",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.quicksand_bold)),
                            color = Color(0XFF272727)
                        )
                        Text(
                            text = "See All",
                            fontSize = 14.sp,
                            color = Color(0XFF272727),
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily(Font(R.font.quicksand_regular)),
                            modifier = Modifier.clickable { }
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

                // Grid Items
                val products = listOf(
                    ProductData("Baby Muslins", R.drawable.dummy_img),
                    ProductData("Top Shorts", R.drawable.dummy_img),
                    ProductData("Top Skirt", R.drawable.dummy_img),
                    ProductData("Pajama Set", R.drawable.dummy_img),
                    ProductData("Top Skirt", R.drawable.dummy_img),
                    ProductData("Top Shorts", R.drawable.dummy_img)
                )

                items(products.chunked(2)) { pair ->
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        ProductCard(modifier = Modifier.weight(1f), product = pair[0], onClick = { navController.navigate(Routes.ProductDetail.route) })
                        if (pair.size > 1) {
                            ProductCard(modifier = Modifier.weight(1f), product = pair[1], onClick = { navController.navigate(Routes.ProductDetail.route)  })
                        } else {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

                // AI Premium Card
                item {
                    AIPromoCard(modifier = Modifier.padding(horizontal = 20.dp))
                    Spacer(modifier = Modifier.height(150.dp))
                }
            }
        }
    }
}

data class ProductData(val title: String, val imageRes: Int)

@Composable
fun HomeHeader(
    onFavIconClick: () -> Unit,
    onClickProfile: () -> Unit,
    onClickScan: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        // App Logo
        Image(
            painter = painterResource(id = R.drawable.baby_ai),
            contentDescription = "Baby AI",
            modifier = Modifier.height(30.dp)
        )

        Row(verticalAlignment = Alignment.CenterVertically) {

            // Favourite Icon
            IconButton(onClick = { onFavIconClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.fav_ic),
                    contentDescription = "Favorites",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(52.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Profile + Arrow + Scan Container
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(22.dp))
                    .clickable { onClickProfile() }
                    .background(Color.White)
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                // Back Arrow
                Icon(
                    painter = painterResource(id = R.drawable.left_arrow),
                    contentDescription = "Back",
                    modifier = Modifier.size(16.dp),
                    tint = Color.Unspecified
                )

                Spacer(modifier = Modifier.width(6.dp))

                // Profile Image
                Image(
                    painter = painterResource(id = R.drawable.onb1),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(6.dp))

                // Scan Icon
                IconButton(
                    onClick = { onClickScan() },
                    modifier = Modifier.size(46.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.scan_ic),
                        contentDescription = "Scan",
                        tint = Color.Unspecified
                    )
                }
            }
        }
    }
}


@Composable
fun CategoryList(modifier: Modifier) {
    val categories = listOf(
        Pair("Muslins", R.drawable.dummy_img),
        Pair("Onesies", R.drawable.dummy_img),
        Pair("Booties", R.drawable.dummy_img),
        Pair("Booties", R.drawable.dummy_img),
        Pair("Booties", R.drawable.dummy_img),
        Pair("Toys", R.drawable.dummy_img)
    )
    LazyRow(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        items(categories) { category ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(26.dp)
                        )
                        .padding(10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = category.second),
                        contentDescription = null,
                        modifier = Modifier
                            .width(152.dp)
                            .height(192.dp)
                            .clip(RoundedCornerShape(22.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }

        }
    }
}

@Composable
fun HomeBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(24.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Banner",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Winter\nCollection",
                color = Color.White,
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.baloo2_bold)),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End,
                lineHeight = 28.sp
            )
            Text(
                text = "Special Christmas Deals",
                color = Color.White.copy(0.9f),
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.nunito_regular))
            )
        }
    }
}

@Composable
fun ProductCard(modifier: Modifier, product: ProductData,onClick: () -> Unit) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(30.dp))
            .background(Color.White)
            .padding(10.dp)
            .clickable{ onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = product.imageRes),
            contentDescription = product.title,
            modifier = Modifier.fillMaxWidth().height(215.dp).clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = product.title,
            fontSize = 13.sp,
            fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
            fontWeight = FontWeight.SemiBold,
            color = Color(0XFF1C1C1C)
        )
    }
}

@Composable
fun AIPromoCard(
    modifier: Modifier = Modifier
) {
    val balooSemiBold = remember {
        FontFamily(Font(R.font.baloo2_semibold))
    }
    val balooMedium = remember {
        FontFamily(Font(R.font.baloo2_medium))
    }
    val nunitoRegular = remember {
        FontFamily(Font(R.font.nunito_regular))
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(Color(0xFFFEF2B2))
            .padding(15.dp)
    ) {
        Column {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(Color.Black),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ai_star),
                        contentDescription = "AI",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(49.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = "Unlock Smart AI Closet Matching",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = balooSemiBold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Upload your baby's wardrobe and get perfect matching outfit suggestions powered by AI.",
                        fontSize = 14.sp,
                        fontFamily = nunitoRegular,
                        color = Color.Black,
                        lineHeight = 18.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                )
            ) {
                Text(
                    text = "GO Premium",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = balooMedium,
                    color = Color.Black
                )
            }
        }
    }
}