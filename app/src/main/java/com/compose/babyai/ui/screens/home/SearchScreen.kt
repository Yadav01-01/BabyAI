package com.compose.babyai.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.compose.babyai.R
import com.compose.babyai.data.model.FilterChipItem
import com.compose.babyai.data.model.SearchFilterType
import com.compose.babyai.navigation.Routes
import com.compose.babyai.ui.component.dialog.FilterDialog
import com.compose.babyai.ui.component.uiInput.OutfitTryCard
import com.compose.babyai.ui.component.uiInput.SearchBar
import com.compose.babyai.ui.screens.aiTry.OutfitData
import com.compose.babyai.ui.theme.PrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavHostController) {
    var searchQuery by remember { mutableStateOf("") }
    var filterSheet by remember { mutableStateOf(false) }
    var selectedFilter by remember { mutableStateOf<SearchFilterType?>(null) }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    val outfits = remember {
        listOf(
            OutfitData("BabySky Blue Stripes", "$249.99", R.drawable.dummy_img, true),
            OutfitData("BabySky Blue Stripes", "$249.99", R.drawable.dummy_img, false),
            OutfitData("BabySky Blue Stripes", "$249.99", R.drawable.dummy_img, false),
            OutfitData("BabySky Blue Stripes", "$249.99", R.drawable.dummy_img, true),
            OutfitData("BabySky Blue Stripes", "$249.99", R.drawable.dummy_img, false),
            OutfitData("BabySky Blue Stripes", "$249.99", R.drawable.dummy_img, false)
        )
    }
    val categories = listOf(
        Pair("Top Shorts", R.drawable.dummy_img),
        Pair("Muslins", R.drawable.dummy_img),
        Pair("Onesies", R.drawable.dummy_img),
        Pair("Booties", R.drawable.dummy_img),
        Pair("Booties", R.drawable.dummy_img),
        Pair("Toys", R.drawable.dummy_img)
    )

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }


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
        ) {
            // Search Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.size(56.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.draw_back_ic),
                        contentDescription = "Back",
                        tint = Color.Unspecified
                    )
                }


                SearchBar(
                    searchQuery = searchQuery,
                    onSearchQueryChange = { searchQuery = it },
                    placeholderText = "Search outfits, brands or categories",
                    icon = painterResource(R.drawable.search_ic),
                    readOnly = false,
                    enabled = true,
                    modifier = Modifier
                        .weight(1f)
                        .focusRequester(focusRequester)
                )

                IconButton(
                    onClick = { navController.navigate(Routes.Filter.route) },
                    modifier = Modifier.size(50.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.filter_ic),
                        contentDescription = "Filter",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(50.dp)
                    )
                }
            }


            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 20.dp)
            ) {
                // Recent Searches
                item {
                    if (searchQuery.isBlank()){
                        Text(
                            text = "Recent Searches",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)
                        )

                        RecentSearchList(
                            modifier = Modifier.padding(start = 20.dp),
                            categories = categories,
                            onClickTryOnsItem = { navController.navigate(Routes.AiTry.route) }
                        )
                    }else{
                        val filters = remember {
                            mutableStateListOf(
                                FilterChipItem("Latest Trends", ),
                                FilterChipItem("Brand", SearchFilterType.BRAND),
                                FilterChipItem("Size", SearchFilterType.SIZE),
                                FilterChipItem("Gender", SearchFilterType.GENDER),
                                FilterChipItem("Color", SearchFilterType.COLOR),
                                FilterChipItem("Price", SearchFilterType.PRICE)
                            )
                        }

                        SearchFilterRow(
                            filters = filters,
                            onFilterClick = { selected ->
                                selectedFilter = selected.type

                                filters.replaceAll {
                                    it.copy(isSelected = it.type == selected.type)
                                }

                                filterSheet = true
                            }
                        )

                    }
                }

                // Trending Outfits
                item {
                    Text(
                        text = "Trending Outfits",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)
                    )
                }

                // Trending Grid
                items(outfits.chunked(2)) { pair ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        OutfitTryCard(
                            modifier = Modifier.weight(1f),
                            onItemClick = { navController.navigate(Routes.ProductDetail.route) },
                            outfit = pair[0]
                        )

                        if (pair.size > 1) {
                            OutfitTryCard(
                                modifier = Modifier.weight(1f),
                                onItemClick = { navController.navigate(Routes.ProductDetail.route) },
                                outfit = pair[1]
                            )
                        } else {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }

            }
        }
    }
    if (filterSheet) {
        FilterDialog(
            modifier = Modifier
                .padding(15.dp)
                .padding(bottom = 10.dp),
            onDismiss = { filterSheet = false },
            onApply = {},
            options = when (selectedFilter) {
             /*   SearchFilterType.LATEST -> listOf("Latest Trends")*/
                SearchFilterType.BRAND -> listOf("Wrogn", "Roadster", "Hokum","JSmAppreals","Glady","Levi’s")
                SearchFilterType.SIZE -> listOf("New Born", "3-6 month", "6-9 months", "9-12 months","1-2 year","2-4 year","XS","M","L","XL","XXL")
                SearchFilterType.GENDER -> listOf("Boy", "Girl", "Baby Boys","Baby Girl")
                SearchFilterType.COLOR -> listOf("Black", "Blue", "Green","White","Grey","Brown","Purple","Red","Beige","Navy Blue","Pink","Light Green","Maroon","Light Blue","Silver","Gold")
                SearchFilterType.PRICE -> listOf("$ 199 and aboue", "$ 249 and below", "$ 149 -  $ 199","$ 299 -  $ 499","$ 549 -  $ 699","$ 749 -  $ 999")
                else -> emptyList()
            }
        )
    }

}

data class TrendingItem(val title: String, val price: String, val imageRes: Int)


@Composable
fun TrendingCard(modifier: Modifier, item: TrendingItem) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(bottom = 20.dp)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            ) {
                Image(
                    painter = painterResource(id = item.imageRes),
                    contentDescription = null,
                    modifier = Modifier.height(215.dp),
                    contentScale = ContentScale.Crop
                )
                
                // Favorite Icon overlay
                IconButton(onClick = { }, modifier = Modifier.align(Alignment.TopEnd)) {
                    Icon(
                        painter = painterResource(id = R.drawable.fav_item),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(38.dp)
                    )
                }

                // AI Sparkle overlay
                Card(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .offset(y = 25.dp) // now safe
                        .size(50.dp),
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 14.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            painter = painterResource(id = R.drawable.try_angle),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(50.dp)
                        )
                    }
                }

            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = item.title,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                color = Color(0XFF1C1C1C),
                textAlign = TextAlign.Center
            )
            
            Text(
                text = item.price,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                color = PrimaryColor,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun FilterChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = if (isSelected) Color.Black else Color(0XFFE6E6E6),
                shape = RoundedCornerShape(30.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            fontFamily = FontFamily(Font(R.font.quicksand_regular)),
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )

        Spacer(modifier = Modifier.width(6.dp))

        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.size(18.dp)
        )
    }
}

@Composable
fun SearchFilterRow(
    filters: List<FilterChipItem>,
    onFilterClick: (FilterChipItem) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(filters) { filter ->
            FilterChip(
                text = filter.title,
                isSelected = filter.isSelected,
                onClick = { onFilterClick(filter) }
            )
        }
    }
}

@Composable
fun RecentSearchList(modifier: Modifier, categories: List<Pair<String, Int>>, onClickTryOnsItem: () -> Unit) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(end = 20.dp)
    ) {
        items(categories) { category ->
            RecentSearchItem(
                title = category.first,
                imageRes = category.second,
                onClick = onClickTryOnsItem
            )
        }
    }
}

@Composable
fun RecentSearchItem(
    title: String,
    imageRes: Int,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.wrapContentSize(),
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                modifier = Modifier
                    .width(75.dp)
                    .height(79.dp)
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = title,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF272727),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}
