package com.compose.babyai.ui.component.dialog

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.compose.babyai.R
import com.compose.babyai.data.model.ShareOption
import com.compose.babyai.ui.component.AppButton
import com.compose.babyai.ui.component.ProfileCardHeading
import com.compose.babyai.ui.theme.PrimaryColor

@Composable
fun SuccessDialog(
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(50.dp),
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 2.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                // Close Button
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .clickable { onDismiss() }
                        .size(24.dp),
                    tint = Color.Black
                )

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Celebration Icon
                    Image(
                        painter = painterResource(id = R.drawable.party_popper), // Placeholder
                        contentDescription = null,
                        modifier = Modifier.wrapContentSize()
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Verified successfully!",
                        fontSize = 28.sp,
                        fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Let's set up your baby's profile",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.nunito_medium)),
                        color = Color(0XFF3C3C3C),
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun AddressNOrderSuccessDialog(
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            // Main Dialog Content
            Surface(
                shape = RoundedCornerShape(40.dp),
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp) // Space for the close button overlap
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 32.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Success Icon in light teal circle
                    Icon(
                        painter = painterResource(id = R.drawable.check_ic),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(100.dp)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Address updated\nSuccessfully!",
                        fontSize = 22.sp,
                        fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        lineHeight = 28.sp
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    AppButton(
                        text = "Okay!",
                        onClick = onDismiss,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            // Close Button
            Surface(
                shape = CircleShape,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(43.dp)
                    .offset(y = (-30).dp)
                    .clickable { onDismiss() },
                shadowElevation = 4.dp
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        modifier = Modifier.size(20.dp),
                        tint = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun AddBabyProfileDialog(
    onDismiss: () -> Unit,
    onAddBabyClick: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(40.dp),
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                // Close Button
                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = Color.Gray
                    )
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(10.dp))

                    // Baby Icon
                    Icon(
                        painter = painterResource(id = R.drawable.profile_ic), // Placeholder for the baby swaddle icon
                        contentDescription = null,
                        tint = PrimaryColor,
                        modifier = Modifier.size(100.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Add Baby Profile",
                        fontSize = 22.sp,
                        fontFamily = FontFamily(Font(R.font.baloo2_bold)),
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Before scanning outfits, please add your baby's profile so we can give accurate size, fabric & season recommendations.",
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.nunito_regular)),
                        color = Color(0XFF828282),
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        lineHeight = 20.sp
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    AppButton(
                        text = "Add Baby",
                        onClick = onAddBabyClick,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Maybe Later",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.baloo2_medium)),
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        modifier = Modifier.clickable { onDismiss() }
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShareBottomSheet(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(40.dp),
        containerColor = Color.White,
        dragHandle = null,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding( vertical = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Share with friends!",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Default.Close, contentDescription = "Close")
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            
            // Dashed Divider
            Canvas(modifier = Modifier.fillMaxWidth().height(1.dp)) {
                drawLine(
                    color = PrimaryColor,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            val shareOptions = listOf(
                ShareOption("Copy url", R.drawable.url_ic), // Placeholder icons
                ShareOption("WhatsApp", R.drawable.whatsapp_ic),
                ShareOption("Instagram", R.drawable.insta_ic),
                ShareOption("Telegram", R.drawable.telegram_ic),
                ShareOption("Facebook", R.drawable.fb_ic),
                ShareOption("X", R.drawable.x_ic),
                ShareOption("Messages", R.drawable.msg_ic),
                ShareOption("More", R.drawable.more_ic)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
            ) {
                items(shareOptions) { option ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable { /* Handle share */ }
                    ) {
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = option.icon),
                                contentDescription = option.name,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(55.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = option.name,
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.nunito_regular)),
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgeBottomSheet(
    onDismiss: () -> Unit,
    selectedAge: String,
    onAgeSelect: (String) -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val ageRanges = listOf(
        "New \nBorn", "0-3 \nMonths", "3-6 \nMonths", "6-9 \nMonths",
        "9-12 \nMonths", "12-18 \nMonths", "18-24 \nMonths", "2-3 \nYears"
    )
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(40.dp),
        containerColor = Color.White,
        dragHandle = null,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding( vertical = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileCardHeading("Baby Age")

            Spacer(modifier = Modifier.height(15.dp))

            // Dashed Divider
            Canvas(modifier = Modifier.fillMaxWidth().height(1.dp)) {
                drawLine(
                    color = PrimaryColor,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                userScrollEnabled = false, //  CRITICAL
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

            Spacer(modifier = Modifier.height(20.dp))

            AppButton(
                text = "Next",
                onClick = { onNextClick() },
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }
    }
}
