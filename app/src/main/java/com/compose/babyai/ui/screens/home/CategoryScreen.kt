package com.compose.babyai.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.compose.babyai.R
import com.compose.babyai.ui.component.SearchBar
import com.compose.babyai.ui.theme.BabyAITheme

@Composable
fun CategoryScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.main_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            CategoryHeader()
            
            Spacer(modifier = Modifier.height(10.dp))
            
            SearchBar(
                searchQuery = searchQuery,
                onSearchQueryChange = { searchQuery = it },
                placeholderText = "Search categories",
                icon = painterResource(id = R.drawable.search_ic)
            )
            
            Spacer(modifier = Modifier.height(24.dp))

            val categories = remember {
                listOf(
                    CategoryItemData("Daily use", R.drawable.dummy_img),
                    CategoryItemData("Outfits", R.drawable.dummy_img),
                    CategoryItemData("Accessories", R.drawable.dummy_img),
                    CategoryItemData("Skincare", R.drawable.dummy_img),
                    CategoryItemData("Playtime", R.drawable.dummy_img),
                    CategoryItemData("Footwear", R.drawable.dummy_img),
                    CategoryItemData("Feeding", R.drawable.dummy_img),
                    CategoryItemData("Diapering", R.drawable.dummy_img),
                    CategoryItemData("Daily use", R.drawable.dummy_img),
                    CategoryItemData("Outfits", R.drawable.dummy_img),
                    CategoryItemData("Accessories", R.drawable.dummy_img),
                    CategoryItemData("Skincare", R.drawable.dummy_img),
                    CategoryItemData("Playtime", R.drawable.dummy_img),
                    CategoryItemData("Footwear", R.drawable.dummy_img),
                    CategoryItemData("Feeding", R.drawable.dummy_img),
                    CategoryItemData("Diapering", R.drawable.dummy_img)
                )
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                contentPadding = PaddingValues(bottom = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(categories) { category ->
                    CategoryGridItem(category = category, onClick = {})
                }
            }
        }
    }
}

@Composable
fun CategoryHeader(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Category",
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
            color = Color.Black
        )

        IconButton(onClick = { }) {
            Icon(
                painter = painterResource(id = R.drawable.fav_ic),
                contentDescription = "Favorites",
                tint = Color.Unspecified,
                modifier = Modifier.size(52.dp)
            )
        }
    }
}

data class CategoryItemData(val name: String, val imageRes: Int)

@Composable
fun CategoryGridItem(
    category: CategoryItemData,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Image(
                    painter = painterResource(id = category.imageRes),
                    contentDescription = category.name,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = category.name,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF272727),
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = 5.dp)
                )
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun CategoryScreenPreview() {
    BabyAITheme {
        CategoryScreen(navController = rememberNavController())
    }
}
