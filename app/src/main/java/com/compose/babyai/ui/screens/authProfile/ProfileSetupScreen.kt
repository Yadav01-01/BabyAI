package com.compose.babyai.ui.screens.authProfile

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.compose.babyai.R
import com.compose.babyai.navigation.Routes
import com.compose.babyai.ui.component.uiInput.AppButton
import com.compose.babyai.ui.component.uiInput.CardTextField
import com.compose.babyai.ui.component.uiInput.ProfileCardHeading
import com.compose.babyai.ui.component.uiInput.SearchBar
import com.compose.babyai.ui.theme.PrimaryColor
import com.compose.babyai.util.dashedBorder


@Composable
fun ProfileSetupScreen(navController: NavHostController) {
    var currentStep by rememberSaveable  { mutableStateOf(1) }

    // State for Step 1
    var nickname by remember { mutableStateOf("") }
    var selectedAgeRange by remember { mutableStateOf("") }

    // State for Step 2
    var selectedGender by remember { mutableStateOf("") }
    var profileImageUri by remember { mutableStateOf<String?>(null) }

    // Capture image from ScanScreen
    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    val result = savedStateHandle?.getLiveData<String>("profile_image")
    
    LaunchedEffect(result?.value) {
        result?.value?.let { uri ->
            profileImageUri = uri
            // Clear the result after processing
            savedStateHandle.remove<String>("profile_image")
        }
    }

    // State for Step 3
    val selectedFabrics = remember { mutableStateListOf<String>() }

    // State for Step 4
    val selectedColors = remember { mutableStateListOf<String>() }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // 🔹 Background Image
        Image(
            painter = painterResource(id = R.drawable.main_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // 🔹 Foreground Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {

            // Top Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        if (currentStep > 1) currentStep-- else navController.popBackStack()
                    },
                    modifier = Modifier.wrapContentSize()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.draw_back_ic),
                        contentDescription = "Back",
                        modifier = Modifier.wrapContentSize(),
                        tint = Color.Unspecified
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.baby_ai),
                    contentDescription = "Logo",
                    modifier = Modifier.wrapContentSize()
                )

                Text(
                    text = "Skip",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                    color = Color.Black,
                    modifier = Modifier.clickable { navController.navigate(Routes.Home.route) }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Progress Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                for (i in 1..4) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(if (i <= currentStep) PrimaryColor else Color.White)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Step Info
            Text(
                text = "Step $currentStep",
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

            val stepTitles = listOf(
                "Baby Basic Details",
                "Baby Profile & Gender",
                "Baby Preferences",
                "Baby Preferences"
            )
            val stepSubtitles = listOf(
                "Help us personalize the perfect outfits.",
                "Help us personalize the perfect outfits.",
                "Choose your favorite styles and materials.",
                "Choose your favorite styles and materials."
            )

            Text(
                text = stepTitles[currentStep - 1],
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

            Text(
                text = stepSubtitles[currentStep - 1],
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.nunito_regular)),
                fontWeight = FontWeight.Normal,
                color = Color(0XFFB0B0B0)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Main Card
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                shape = RoundedCornerShape(30.dp),
                color = Color.White,
                shadowElevation = 2.dp
            ) {
                when (currentStep) {
                    1 -> StepOneContent(
                        nickname,
                        { nickname = it },
                        selectedAgeRange,
                        { selectedAgeRange = it },
                        { if (currentStep < 4) currentStep++ }
                    )

                    2 -> StepTwoContent(
                        selectedGender,
                        profileImageUri = profileImageUri,
                        onNextClick = { if (currentStep < 4) currentStep++ },
                        onGenderSelect = { selectedGender = it },
                        onClickCamera = { navController.navigate(Routes.AiScan.createRoute("profileSetup")) }
                        )

                    3 -> StepThreeContent(
                        selectedFabrics,
                        onNextClick = { if (currentStep < 4) currentStep++ })

                    4 -> StepFourContent(
                        selectedColors,
                        onNextClick = { navController.navigate(Routes.ProfileReady.route) })
                }
            }
            Spacer(Modifier.height(25.dp))
        }
    }
}

@Composable
fun StepOneContent(
    nickname: String,
    onNicknameChange: (String) -> Unit,
    selectedAge: String,
    onAgeSelect: (String) -> Unit,
    onNextClick: () -> Unit
) {

    val ageRanges = listOf(
        "New \nBorn", "0-3 \nMonths", "3-6 \nMonths", "6-9 \nMonths",
        "9-12 \nMonths", "12-18 \nMonths", "18-24 \nMonths", "2-3 \nYears"
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 20.dp)
    ) {

        item {
            ProfileCardHeading("Enter Baby's Nickname")
            Spacer(modifier = Modifier.height(12.dp))
            DashedDivider(color = PrimaryColor, modifier = Modifier.padding(horizontal = 20.dp))
            Spacer(modifier = Modifier.height(16.dp))

            CardTextField(
                value = nickname,
                onValueChange = onNicknameChange,
                placeholderText = "Enter name",
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
            ProfileCardHeading("Baby Age")
            Spacer(modifier = Modifier.height(15.dp))
            DashedDivider(color = PrimaryColor, modifier = Modifier.padding(horizontal = 20.dp))
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                userScrollEnabled = false, // 🔥 CRITICAL
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .heightIn(max = 1000.dp) // bounded height
            ) {
                items(ageRanges) { age ->

                    val isSelected = selectedAge == age
                    val parts = age.split("\n")
                    val title = parts[0].trim()
                    val subtitle = parts.getOrNull(1)?.trim().orEmpty()

                    Box(
                        modifier = Modifier
                            .height(110.dp)
                            .clip(RoundedCornerShape(40.dp))
                            .background(if (isSelected) Color(0xFFE9FAFA) else Color.Transparent)
                            .border(
                                1.dp,
                                if (isSelected) PrimaryColor else Color(0xFFD9D9D9),
                                RoundedCornerShape(40.dp)
                            )
                            .clickable { onAgeSelect(age) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    SpanStyle(
                                        fontFamily = FontFamily(Font(R.font.baloo2_medium)),
                                        fontSize = 22.sp
                                    )
                                ) { append(title) }

                                if (subtitle.isNotEmpty()) {
                                    append("\n")
                                    withStyle(
                                        SpanStyle(
                                            fontFamily = FontFamily(Font(R.font.varela_round)),
                                            fontSize = 18.sp
                                        )
                                    ) { append(subtitle) }
                                }
                            },
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
            AppButton(
                text = "Next",
                onClick = { onNextClick() },
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Composable
fun StepTwoContent(
    selectedGender: String,
    profileImageUri: String? = null,
    onGenderSelect: (String) -> Unit,
    onNextClick: () -> Unit,
    onClickCamera: () -> Unit
) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 20.dp)
    ) {

        // ---------- Profile Picture ----------
        item {
            ProfileCardHeading("Choose Profile Picture")
            Spacer(modifier = Modifier.height(16.dp))
            DashedDivider(color = PrimaryColor, modifier = Modifier.padding(horizontal = 20.dp))
            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .size(92.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .dashedBorder(
                        color = PrimaryColor,
                        shape = RoundedCornerShape(30.dp)
                    )
                    .clickable {
                        onClickCamera()
                    },
                contentAlignment = Alignment.Center
            ) {
                if (profileImageUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(profileImageUri),
                        contentDescription = "Profile Picture",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.cam_ic),
                        contentDescription = "Pick Image",
                        tint = PrimaryColor,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }

        // ---------- Gender ----------
        item {
            Spacer(modifier = Modifier.height(32.dp))
            ProfileCardHeading("Baby Gender")
            Spacer(modifier = Modifier.height(15.dp))
            DashedDivider(color = PrimaryColor, modifier = Modifier.padding(horizontal = 20.dp))
            Spacer(modifier = Modifier.height(20.dp))
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                GenderCard(
                    title = "Baby Boy",
                    icon = R.drawable.ic_boy,
                    isSelected = selectedGender == "Boy",
                    modifier = Modifier.weight(1f),
                    onClick = { onGenderSelect("Boy") }
                )

                GenderCard(
                    title = "Baby Girl",
                    icon = R.drawable.ic_girl,
                    isSelected = selectedGender == "Girl",
                    modifier = Modifier.weight(1f),
                    onClick = { onGenderSelect("Girl") }
                )
            }

        }

        // ---------- Button ----------
        item {
            Spacer(modifier = Modifier.height(32.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                AppButton(
                    text = "Save & Next",
                    onClick = {
                        if (selectedGender.isNotEmpty()) {
                            onNextClick()
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun GenderCard(
    title: String,
    icon: Int,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .height(110.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(if (isSelected) Color(0xFFE9FAFA) else Color.Transparent)
            .border(
                1.dp,
                if (isSelected) PrimaryColor else Color.Transparent,
                RoundedCornerShape(30.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = title,
                modifier = Modifier.size(28.dp),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                fontFamily = FontFamily(Font(R.font.varela_round)),
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = Color.Black
            )
        }
    }
}

@Composable
fun StepThreeContent(selectedFabrics: MutableList<String>,onNextClick: () -> Unit) {
    val fabrics = listOf("Organic Cotton", "Bamboo", "Muslin", "Fleece", "Lyocell", "Modal", "Linen-Cotton Blend", "Soft Bleeds", "Jersey Knit", "Cotton-Spandex Blend")
    var searchQuery by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ) {

        // Scrollable Content
        LazyColumn(
            modifier = Modifier.fillMaxSize().background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(
                top = 20.dp,
                bottom = 100.dp // important so content doesn't hide behind button
            )
        ) {

            item {
                ProfileCardHeading("Fabric Preferences")
                Spacer(modifier = Modifier.height(16.dp))
                DashedDivider(
                    color = PrimaryColor,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
            }

            item {
                SearchBar(
                    searchQuery = searchQuery,
                    onSearchQueryChange = { searchQuery = it },
                    placeholderText = "Search fabric preferences",
                    icon = painterResource(R.drawable.search_ic),
                    readOnly = false,
                    enabled = true,
                    modifier = Modifier.padding(horizontal = 20.dp),
                    containerColor = Color(0XFFE9FAFA)
                )
                Spacer(Modifier.height(12.dp))
            }

            item {
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    fabrics.forEach { fabric ->
                        val isSelected = selectedFabrics.contains(fabric)

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(50.dp))
                                .background(
                                    if (isSelected) Color(0xFFE9FAFA) else Color.White
                                )
                                .border(
                                    1.dp,
                                    if (isSelected) PrimaryColor else Color(0xFFE0E0E0),
                                    RoundedCornerShape(50.dp)
                                )
                                .clickable {
                                    if (isSelected) {
                                        selectedFabrics.remove(fabric)
                                    } else {
                                        selectedFabrics.add(fabric)
                                    }
                                }
                                .padding(horizontal = 18.dp, vertical = 10.dp)
                        ) {
                            Text(
                                text = fabric,
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.nunito_regular)),
                                color = Color(0xFF333333)
                            )
                        }
                    }
                }
            }
        }

        // Fixed Bottom Button
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color.White)
                .padding(20.dp)
        ) {
            AppButton(
                text = "Save & Next",
                onClick = { onNextClick() }
            )
        }
    }


}

@Composable
fun StepFourContent(selectedColors: MutableList<String>,onNextClick: () -> Unit) {
    val colors = listOf("Pastel", "Bright", "Neutral", "Natural Tones", "Cool Colors", "Warm Colors")
    var searchQuery by remember { mutableStateOf("") }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // Scrollable Content
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(
                top = 20.dp,
                bottom = 100.dp // IMPORTANT: space for button
            )
        ) {

            item {
                ProfileCardHeading("Preferred Colors")
                Spacer(modifier = Modifier.height(16.dp))
                DashedDivider(
                    color = PrimaryColor,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
            }

            item {
                SearchBar(
                    searchQuery = searchQuery,
                    onSearchQueryChange = { searchQuery = it },
                    placeholderText = "Search fabric preferences",
                    icon = painterResource(R.drawable.search_ic),
                    readOnly = false,
                    enabled = true,
                    modifier = Modifier.padding(horizontal = 20.dp),
                    containerColor = Color(0XFFE9FAFA)
                )
                Spacer(Modifier.height(12.dp))
            }

            item {
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    colors.forEach { color ->
                        val isSelected = selectedColors.contains(color)

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(50.dp))
                                .background(
                                    if (isSelected) Color(0xFFE9FAFA) else Color.White
                                )
                                .border(
                                    1.dp,
                                    if (isSelected) PrimaryColor else Color(0xFFE0E0E0),
                                    RoundedCornerShape(50.dp)
                                )
                                .clickable {
                                    if (isSelected) {
                                        selectedColors.remove(color)
                                    } else {
                                        selectedColors.add(color)
                                    }
                                }
                                .padding(horizontal = 18.dp, vertical = 10.dp)
                        ) {
                            Text(
                                text = color,
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.nunito_regular)),
                                color = Color(0xFF333333)
                            )
                        }
                    }
                }
            }
        }

        // Fixed Bottom Button
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color.White)
                .padding(20.dp)
        ) {
            AppButton(
                text = "Save & Next",
                onClick = { onNextClick() }
            )
        }
    }

}

// Minimal FlowRow implementation since I don't know if they have the dependency
@Composable
fun FlowRow(
    modifier: Modifier = Modifier,
    mainAxisSpacing: androidx.compose.ui.unit.Dp = 0.dp,
    crossAxisSpacing: androidx.compose.ui.unit.Dp = 0.dp,
    content: @Composable () -> Unit
) {
    androidx.compose.ui.layout.Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        val placeables = measurables.map { it.measure(constraints) }
        var yPosition = 0
        var xPosition = 0
        var maxHeight = 0
        val layoutHeight: Int
        val layoutWidth = constraints.maxWidth

        val positions = mutableListOf<Pair<Int, Int>>()

        placeables.forEach { placeable ->
            if (xPosition + placeable.width > layoutWidth) {
                xPosition = 0
                yPosition += maxHeight + crossAxisSpacing.roundToPx()
                maxHeight = 0
            }
            positions.add(xPosition to yPosition)
            xPosition += placeable.width + mainAxisSpacing.roundToPx()
            maxHeight = maxOf(maxHeight, placeable.height)
        }
        layoutHeight = yPosition + maxHeight

        layout(layoutWidth, layoutHeight) {
            placeables.forEachIndexed { index, placeable ->
                placeable.placeRelative(positions[index].first, positions[index].second)
            }
        }
    }
}

@Composable
fun DashedDivider(
    color: Color = Color(0xFFE0F7F7),
    thickness: Dp = 1.dp,
    dashWidth: Dp = 6.dp,
    dashGap: Dp = 4.dp,
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(thickness)
    ) {
        drawLine(
            color = color,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            strokeWidth = thickness.toPx(),
            pathEffect = PathEffect.dashPathEffect(
                floatArrayOf(
                    dashWidth.toPx(),
                    dashGap.toPx()
                )
            )
        )
    }
}
