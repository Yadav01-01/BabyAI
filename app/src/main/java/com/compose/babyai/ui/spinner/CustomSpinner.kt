package com.compose.babyai.ui.spinner

//CustomSpinner
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.babyai.ui.screens.profile.FabricItem
import com.compose.babyai.R

@Composable
fun CustomSpinner(

    modifier: Modifier = Modifier,
    fabricOptions : MutableList<FabricItem>,
    selectedFabricNames: List<String>,
    onSelectionChanged: (List<String>) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var rotation by remember { mutableStateOf(0f) }


    // Initialize selections
    LaunchedEffect(selectedFabricNames) {
        fabricOptions.forEachIndexed { index, item ->
            fabricOptions[index] = item.copy(
                isSelected = selectedFabricNames.contains(item.name)
            )
        }
    }



    Column(modifier = modifier) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))

                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .clickable {
                    expanded = !expanded
                    rotation = if (expanded) 180f else 0f
                }
                .padding(horizontal = 16.dp, vertical = 18.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Select Fabric Preferences",
                    fontSize = 15.sp,
                    color = Color.Black ,
                    fontFamily = FontFamily(Font(R.font.varela_round)),
                    fontWeight = FontWeight.Medium
                )

                Image(
                    painter = painterResource(R.drawable.dropdown_arrow),
                    contentDescription = "Expand",
                    modifier = Modifier
                        .size(17.dp)
                        .rotate(rotation),
                )
            }
        }

        // Dropdown options
        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn() + expandVertically(animationSpec = tween(300)),
            exit = fadeOut() + shrinkVertically(animationSpec = tween(300))
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .heightIn(max = 300.dp)
                        .padding(vertical = 8.dp)
                ) {
                    items(fabricOptions.size) { index ->
                        val fabric = fabricOptions[index]
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    // Toggle selection
                                    fabricOptions[index] = fabric.copy(
                                        isSelected = !fabric.isSelected
                                    )
                                    // Update parent
                                    val selectedNames = fabricOptions
                                        .filter { it.isSelected }
                                        .map { it.name }
                                    onSelectionChanged(selectedNames)
                                }
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {

                            Box(
                                modifier = Modifier
                                    .size(22.dp)
                                    .clip(RoundedCornerShape(6.dp))
                                    .border(
                                        width = 1.dp,
                                        color = if (fabric.isSelected) Color(0xFFFFD400) else Color(
                                            0xFF444444
                                        ),
                                        shape = RoundedCornerShape(6.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Checkbox(
                                    checked = fabric.isSelected,
                                    onCheckedChange = {
                                        fabricOptions[index] = fabric.copy(isSelected = it)

                                        val selectedNames = fabricOptions
                                            .filter { it.isSelected }
                                            .map { it.name }

                                        onSelectionChanged(selectedNames)
                                    },
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = Color(0xFFFFD400),
                                        uncheckedColor = Color.Transparent,
                                        checkmarkColor = Color.White
                                    ),
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = fabric.name,
                                fontSize = 14.sp,
                                color = Color(0xFF363636),
                                fontFamily = FontFamily(Font(R.font.nunito_medium)),
                                fontWeight =  FontWeight.Medium
                            )



                        }


                    }
                }
            }
        }
    }
}
