package com.compose.babyai.ui.component.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.painterResource
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
import com.compose.babyai.data.model.BottomSheetOption
import com.compose.babyai.data.model.ReturnReason
import com.compose.babyai.data.model.ShareOption
import com.compose.babyai.ui.component.AppButton
import com.compose.babyai.ui.component.InputField1
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


/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReturnReasonBottomSheet(
    onDismiss: () -> Unit,
    selectedReason: ReturnReason?,
    onReasonSelect: (ReturnReason) -> Unit,
    comment: String,
    onCommentChange: (String) -> Unit,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier
) {

    val reasons = listOf(
        ReturnReason(1, "Item doesn't fit"),
        ReturnReason(2, "Unhappy with the quality"),
        ReturnReason(3, "Wrong item received"),
        ReturnReason(4, "Item damaged or defective"),
        ReturnReason(5, "Ordered by mistake"),
        ReturnReason(6, "Other")
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
        containerColor = Color.White,
        dragHandle = null,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {

            // Header
            Text(
                text = "Reason for Return",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Dashed Divider
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
            ) {
                drawLine(
                    color = Color(0xFF23C6BE),
                    start = Offset.Zero,
                    end = Offset(size.width, 0f),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Reason list
            reasons.forEach { reason ->
                ReasonItem(
                    reason = reason,
                    isSelected = selectedReason?.id == reason.id,
                    onClick = { onReasonSelect(reason) }
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Additional comments
            Text(
                text = "Additional Comments (Optional)",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = comment,
                onValueChange = onCommentChange,
                placeholder = {
                    Text("Tell us more about why you're returning this order...")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                shape = RoundedCornerShape(14.dp),
                maxLines = 5
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = onDismiss,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(30.dp)
                ) {
                    Text("Back")
                }

                Button(
                    onClick = onConfirm,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF23C6BE)
                    )
                ) {
                    Text("Confirm")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Composable
private fun ReasonItem(
    reason: ReturnReason,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) Color(0xFF23C6BE) else Color(0xFFE0E0E0)
    val backgroundColor = if (isSelected) Color(0xFFE9FAFA) else Color.Transparent

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(backgroundColor)
            .border(1.dp, borderColor, RoundedCornerShape(14.dp))
            .clickable { onClick() }
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = reason.title,
            modifier = Modifier.weight(1f),
            fontSize = 15.sp
        )

        if (isSelected) {
            Image(
                painterResource(id = R.drawable.ic_circle_check_icon),
                contentDescription = null,
                modifier = Modifier.wrapContentSize()
                //tint = Color(0xFF23C6BE)
            )
        }
    }
}
*/

/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReturnReasonBottomSheet(
    onDismiss: () -> Unit,
    selectedReason: ReturnReason?,
    onReasonSelect: (ReturnReason) -> Unit,
    comment: String,
    onCommentChange: (String) -> Unit,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier
) {

    val reasons = listOf(
        ReturnReason(1, "Item doesn't fit"),
        ReturnReason(2, "Unhappy with the quality"),
        ReturnReason(3, "Wrong item received"),
        ReturnReason(4, "Item damaged or defective"),
        ReturnReason(5, "Ordered by mistake"),
        ReturnReason(6, "Other")
    )
    var  query  by remember { mutableStateOf("") }
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape( 28.dp),
        containerColor = Color.White,
        dragHandle = null,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding( vertical = 16.dp)
        ) {

            // Title
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Reason for Return",
                    fontSize = 17.sp,
                    fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
                    color = Color(0xFF000000),
                    modifier = Modifier.weight(1f)
                )
                */
/*  Box(
                      modifier = Modifier
                          .fillMaxWidth().padding(top = 5.dp),
                      contentAlignment = Alignment.TopEnd
                  ) {*//*

                Icon(
                    painter = painterResource(id = R.drawable.ic_cross_icon),
                    contentDescription = "Close",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { onDismiss() }
                )
                //}


            }

            Spacer(modifier = Modifier.height(12.dp))

            // Dashed divider
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
            ) {
                drawLine(
                    color = Color(0xFF23C6BE),
                    start = Offset.Zero,
                    end = Offset(size.width, 0f),
                    pathEffect = PathEffect.dashPathEffect(
                        floatArrayOf(15f, 15f),
                        0f
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Reasons
            reasons.forEach { reason ->
                ReasonItem(
                    reason = reason,
                    isSelected = selectedReason?.id == reason.id,
                    onClick = { onReasonSelect(reason) }
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Additional comments
            Text(
                text = "Additional Comments (Optional)",
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.nunito_regular)),
                color = Color(0xFF737373),
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            */
/*OutlinedTextField(
                value = comment,
                onValueChange = onCommentChange,
                placeholder = {
                    Text("Tell us more about why you're returning this order...")
                },
                modifier = Modifier
                    .fillMaxWidth().padding(horizontal = 20.dp)
                    .height(120.dp),
                shape = RoundedCornerShape(14.dp),
                maxLines = 5
            )*//*

            InputField1(
                input = query,
                onValueChange = { query =it },
                placeholder = "Tell us more about why you're returning this order...",
                modifier = Modifier
                    .fillMaxWidth().padding(horizontal = 20.dp)
                    .height(120.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Buttons
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {


                Button(
                    onClick = onConfirm,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1ECBCC)
                    )
                ) {
                    Text("Confirm", color = Color.White,
                        fontSize = 17.sp,
                        fontFamily = FontFamily(Font(R.font.baloo2_semibold)),)
                }

                OutlinedButton(
                    onClick = onDismiss,
                    modifier = Modifier.weight(1f),
                    border = BorderStroke(1.dp, Color(0xFF000000)),
                    shape = RoundedCornerShape(40.dp)
                ) {
                    Text("Back",
                        fontSize = 17.sp,
                        fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
                        color = Color(0xFF000000))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Composable
private fun ReasonItem(
    reason: ReturnReason,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) Color(0xFFB9EFEF) else Color(0xFFB9EFEF)
    val backgroundColor = if (isSelected) Color(0xFF1ECBCC) else Color(0xFFE9FAFA)

    Row(
        modifier = Modifier
            .fillMaxWidth().padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor)
            .border(1.dp, borderColor, RoundedCornerShape(10.dp))
            .clickable { onClick() }
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = reason.title,
            fontSize = 13.sp,
            fontFamily = FontFamily(Font(R.font.nunito_medium)),
            color = if (isSelected) Color(0xFFE9FAFA) else Color(0xFF363636),
            modifier = Modifier.weight(1f)
        )

        if (isSelected) {
            Icon(
                painter = painterResource(id = R.drawable.ic_circle_check_icon),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(15.dp)
            )
        }
    }
}
*/


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectionBottomSheet(
    title: String,
    options: List<BottomSheetOption>,
    selectedOption: BottomSheetOption?,
    onOptionSelect: (BottomSheetOption) -> Unit,
    comment: String,
    onCommentChange: (String) -> Unit,
    commentPlaceholder: String = "",
    showCommentBox: Boolean = true,
    confirmButtonText: String = "Confirm",
    backButtonText: String = "Back",
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(28.dp),
        containerColor = Color.White,
        dragHandle = null,
        modifier = modifier.padding(15.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {

            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Text(
                    text = title,
                    fontSize = 17.sp,
                    fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_cross_icon),
                    contentDescription = "Close",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { onDismiss() }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Divider
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
            ) {
                drawLine(
                    color = Color(0xFF23C6BE),
                    start = Offset.Zero,
                    end = Offset(size.width, 0f),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(15f, 15f))
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Options
            options.forEach { option ->
                SelectionItem(
                    title = option.title,
                    isSelected = selectedOption?.id == option.id,
                    onClick = { onOptionSelect(option) }
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

            // Comment Box
            if (showCommentBox) {

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Additional Comments (Optional)",
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_regular)),
                    color = Color(0xFF737373),
                    modifier = Modifier.padding(horizontal = 20.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                InputField1(
                    input = comment,
                    onValueChange = onCommentChange,
                    placeholder = commentPlaceholder,
                    placeholderSize = 13.sp,
                    textSize = 13.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .height(120.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Your feedback helps us improve our service",
                fontSize = 10.sp,
                fontFamily = FontFamily(Font(R.font.nunito_regular)),
                color = Color(0xFF737373),
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))
            // Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Button(
                    onClick = onConfirm,
                    modifier = Modifier.height(55.dp).weight(1f),
                    shape = RoundedCornerShape(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1ECBCC)
                    )
                ) {
                    Text(
                        confirmButtonText,
                        color = Color.White,
                        fontSize = 17.sp,
                        fontFamily = FontFamily(Font(R.font.baloo2_semibold))
                    )
                }

                OutlinedButton(
                    onClick = onDismiss,
                    modifier = Modifier.height(55.dp).weight(1f),
                    border = BorderStroke(1.dp, Color.Black),
                    shape = RoundedCornerShape(40.dp)
                ) {
                    Text(
                        backButtonText,
                        fontSize = 17.sp,
                        color = Color.Black,
                        fontFamily = FontFamily(Font(R.font.baloo2_semibold))
                    )
                }
            }

        }
    }
}

@Composable
fun SelectionItem(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor =
        if (isSelected) Color(0xFF1ECBCC) else Color(0xFFE9FAFA)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor)
            .border(1.dp, Color(0xFFB9EFEF), RoundedCornerShape(10.dp))
            .clickable { onClick() }
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = title,
            fontSize = 13.sp,
            fontFamily = FontFamily(Font(R.font.nunito_medium)),
            color = if (isSelected) Color.White else Color(0xFF363636),
            modifier = Modifier.weight(1f)
        )

        if (isSelected) {
            Icon(
                painter = painterResource(id = R.drawable.ic_circle_check_icon),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(15.dp)
            )
        }
    }
}

