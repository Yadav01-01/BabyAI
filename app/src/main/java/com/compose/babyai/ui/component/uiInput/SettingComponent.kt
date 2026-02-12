package com.compose.babyai.ui.component.uiInput

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.babyai.R

@Composable
fun CommonOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hintText: String,
    modifier: Modifier = Modifier,
    leadingIconResId: Int? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    singleLine: Boolean = true
) {

    OutlinedTextField(
        value = value,
        onValueChange = { input ->
            if (keyboardType == KeyboardType.Phone) {
                val digits = input.filter { it.isDigit() }.take(10)
                onValueChange(digits)
            } else {
                onValueChange(input)
            }
        },
        modifier = modifier.border(1.dp, Color(0xFF808080), RoundedCornerShape(12.dp)),
        leadingIcon = {
            leadingIconResId?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    modifier = Modifier.size(22.dp)
                )
            }
        },
        placeholder = {
            Text(
                text = hintText,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.varela_round)),
                color = Color(0x99363636)
            )
        },
        textStyle = TextStyle(
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.varela_round)),
            color = Color.Black
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            cursorColor = Color(0xFF1EC9C3),
            focusedPlaceholderColor = Color(0x99363636),
            unfocusedPlaceholderColor = Color(0x99363636)
        ),
        shape = RoundedCornerShape(12.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = singleLine
    )
}
@Composable
fun InputField1(
    input: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    placeholderSize :TextUnit = 15.sp,
    textSize :TextUnit = 15.sp,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = input,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                fontFamily = FontFamily(Font(R.font.varela_round)),
                fontSize = placeholderSize,
                color = Color(0x99363636)
            )
        },
        textStyle = TextStyle(
            fontFamily = FontFamily(Font(R.font.varela_round)),
            fontSize = textSize,
            color = Color.Black
        ),
        modifier = modifier.fillMaxWidth() .border(1.dp, Color(0xFF808080), RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            cursorColor = Color(0xFF1EC9C3),
        ),
        singleLine = false,
        maxLines = 5
    )
}

@Composable
fun CommonButton2(
    onClick: () -> Unit,
    title: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 18.sp,
    radius: Dp = 14.dp,
    enabled: Boolean = true
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .clip(RoundedCornerShape(radius))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF25CFC8),
                        Color(0xFF1EC9C3)
                    )
                )
            )
            .clickable(
                enabled = enabled,
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            fontSize = fontSize,
            fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
            color = Color.White
        )
    }
}

@Composable
fun CommonButton3(
    onClick: () -> Unit,
    title: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 18.sp,
    radius: Dp = 14.dp,
    enabled: Boolean = true
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .clip(RoundedCornerShape(radius))
            .background(
                Color(0xFF1ECBCC)
            )
            .clickable(
                enabled = enabled,
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick() },
        contentAlignment = Alignment.Center
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(R.drawable.ic_plus_icon), contentDescription = "", modifier = Modifier.size(22.dp))
        Spacer(Modifier.width(10.dp))
        Text(
            text = title,
            fontSize = fontSize,
            fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
            color = Color.White
        )
        }
    }
}


@Composable
fun CommonOutlinedTextFieldCard(
    value: String,
    onValueChange: (String) -> Unit,
    hintText: String,
    modifier: Modifier = Modifier,
    leadingIconResId: Int? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    singleLine: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = { input ->
            if (keyboardType == KeyboardType.Phone || keyboardType == KeyboardType.Number) {
                val digits = input.filter { it.isDigit() || it == '/' }
                onValueChange(digits)
            } else {
                onValueChange(input)
            }
        },
        modifier = modifier.border(1.dp, Color(0xFF808080), RoundedCornerShape(28.dp)),
        leadingIcon = {
            leadingIconResId?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    modifier = Modifier.size(22.dp)
                )
            }
        },
        placeholder = {
            Text(
                text = hintText,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.varela_round)),
                color = Color(0x99363636)
            )
        },
        textStyle = TextStyle(
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.varela_round)),
            color = Color.Black
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            cursorColor = Color(0xFF1EC9C3),
            focusedPlaceholderColor = Color(0x99363636),
            unfocusedPlaceholderColor = Color(0x99363636)
        ),
        shape = RoundedCornerShape(28.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = singleLine
    )
}

@Composable
fun CommonOutlinedTextFieldCardNumber(
    value: String,
    onValueChange: (String) -> Unit,
    hintText: String,
    modifier: Modifier = Modifier,
    leadingIconResId: Int? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    singleLine: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = { input ->
            if (keyboardType == KeyboardType.Number) {
                val formatted = formatCardNumber(input)
                onValueChange(formatted)
            } else {
                onValueChange(input)
            }
        },
        modifier = modifier.border(1.dp, Color(0xFF808080), RoundedCornerShape(28.dp)),
        leadingIcon = {
            leadingIconResId?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    modifier = Modifier.size(22.dp)
                )
            }
        },
        placeholder = {
            Text(
                text = hintText,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.varela_round)),
                color = Color(0x99363636)
            )
        },
        textStyle = TextStyle(
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.varela_round)),
            color = Color.Black
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            cursorColor = Color(0xFF1EC9C3),
            focusedPlaceholderColor = Color(0x99363636),
            unfocusedPlaceholderColor = Color(0x99363636)
        ),
        shape = RoundedCornerShape(28.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = singleLine
    )
}


@Composable
fun CommonButtonCard(
    onClick: () -> Unit,
    title: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 18.sp,
    radius: Dp = 14.dp,
    enabled: Boolean = true
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .clip(RoundedCornerShape(radius))
            .background(Color(0xFF1ECBCC))
            .clickable(
                enabled = enabled,
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            fontSize = fontSize,
            fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
            color = Color.White
        )
    }
}

fun formatCardNumber(input: String): String {
    val digitsOnly = input.filter { it.isDigit() }.take(16)

    return digitsOnly.chunked(4).joinToString(" ")
}