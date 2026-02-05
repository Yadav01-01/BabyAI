package com.compose.babyai.ui.screens.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.compose.babyai.R
import com.compose.babyai.ui.component.CommonTopBar

data class ExpandableItem(
    val id: Int,
    val title: String,
    val content: String,
    val isExpanded: Boolean = false
)
@Composable
fun FrequentlyAskQuestionsScreen(navController: NavHostController) {
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

                .statusBarsPadding().navigationBarsPadding()
        ) {

            CommonTopBar(
                title = "Frequently Ask Questions",
                onBackClick = {
                    navController.navigateUp()
                }
            )

            val sampleFaqData = listOf(
                ExpandableItem(
                    id = 1,
                    title = "Lorem Ipsum is simply dummy text.",
                    content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim.",
                    isExpanded = true
                ),
                ExpandableItem(2, "Lorem Ipsum is simply dummy text.", "Lorem ipsum dolor sit amet."),
                ExpandableItem(3, "Lorem Ipsum is simply dummy text.", "Lorem ipsum dolor sit amet."),
                ExpandableItem(4, "Lorem Ipsum is simply dummy text.", "Lorem ipsum dolor sit amet."),
                ExpandableItem(5, "Lorem Ipsum is simply dummy text.", "Lorem ipsum dolor sit amet.")
            )

            var items by remember { mutableStateOf(sampleFaqData) }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(items) { item ->
                    FaqExpandableCard(
                        item = item,
                        onToggle = { clicked ->
                            items = items.map {
                                if (it.id == clicked.id)
                                    it.copy(isExpanded = !it.isExpanded)
                                else it.copy(isExpanded = false)
                            }
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun FaqExpandableCard(
    item: ExpandableItem,
    onToggle: (ExpandableItem) -> Unit
) {
    Column {

        // HEADER
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(50.dp))
                .background(Color(0xFF1ECBCC))
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { onToggle(item) }
                .padding(horizontal = 10.dp, vertical = 16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = item.title,
                    color = Color.White,
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    painter = painterResource(
                        if (item.isExpanded)
                            R.drawable.drop_down_up
                        else
                            R.drawable.drop_down_down
                    ),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        // EXPANDED CONTENT
        AnimatedVisibility(
            visible = item.isExpanded,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFFE9FAFA)).border(1.dp , color = Color(0xFFB9EFEF), shape = RoundedCornerShape(20.dp))
                    .padding(16.dp)
            ) {
                Text(
                    text = item.content,
                    color = Color.Black,
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_regular)),
                    lineHeight = 20.sp
                )
            }
        }
    }
}

