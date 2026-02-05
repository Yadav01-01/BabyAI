package com.compose.babyai.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.babyai.R
import com.compose.babyai.ui.theme.PrimaryColor

@Composable
fun InputTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    modifier: Modifier = Modifier,
    leadingIcon: Painter? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        placeholder = {
            Text(
                text = placeholderText,
                color = Color(0XFF6A7193),
                fontFamily = FontFamily(Font(R.font.nunito_regular)),
            )
        },
        leadingIcon = {
            leadingIcon?.let {
                Icon(
                    painter = it,
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(28.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedBorderColor = PrimaryColor,
            unfocusedBorderColor = Color(0xFFE0E0E0),
            cursorColor = PrimaryColor
        ),
        singleLine = true
    )
}


@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonColors: Color = PrimaryColor
){
    Button(
        onClick = { onClick() },
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(40.dp),
        colors = ButtonDefaults.buttonColors(containerColor = buttonColors)
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily(Font(R.font.baloo2_medium)),
            fontSize = 18.sp,
            color = Color.White
        )
    }
}

@Composable
fun BabyAiTopBar(
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {

        // 🔹 Back icon (LEFT)
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back_ic),
                contentDescription = "Back",
                modifier = Modifier.size(24.dp),
                tint = Color.Unspecified
            )
        }

        // 🔹 Baby AI logo (CENTER)
        Image(
            painter = painterResource(id = R.drawable.baby_ai),
            contentDescription = "Baby AI Logo",
            modifier = Modifier
                .align(Alignment.Center)
                .height(24.dp)
        )
    }
}

@Composable
fun CardTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        placeholder = {
            Text(
                text = placeholderText,
                color = Color(0XFF6A7193),
                fontFamily = FontFamily(Font(R.font.nunito_regular)),
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(28.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedBorderColor = PrimaryColor,
            unfocusedBorderColor = Color(0xFFE0E0E0),
            cursorColor = PrimaryColor
        ),
        singleLine = true
    )
}

@Composable
fun SearchBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    placeholderText: String,
    icon: Painter? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange, // cleaner
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        placeholder = {
            Text(
                text = placeholderText,
                color = Color(0xFFB0B0B0),
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.quicksand_regular))
            )
        },
        leadingIcon = icon?.let {
            {
                Icon(
                    painter = it,
                    contentDescription = null,
                    tint = Color(0xFFB0B0B0)
                )
            }
        },
        shape = RoundedCornerShape(28.dp),
        singleLine = true,
        enabled = enabled,
        readOnly = readOnly,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            cursorColor = Color.Black
        )
    )
}

@Composable
fun DetailHeading(heading : String){
    Text(heading,fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.baloo2_medium)),
        fontWeight = FontWeight.Medium,
        color = Color.Black)
}

@Composable
fun InputTextFieldWithoutIcon(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        placeholder = {
            Text(
                text = placeholderText,
                color = Color(0X806A7193),
                fontFamily = FontFamily(Font(R.font.nunito_regular)),
            )
        },
        textStyle = TextStyle(
            color = Color(0xFF0B4747),
            fontSize = 15.sp,
            fontFamily = FontFamily(Font(R.font.nunito_regular)),
            fontWeight = FontWeight.Normal
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(28.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedBorderColor = Color(0xFFE0E0E0),
            unfocusedBorderColor = Color(0xFFE0E0E0),
            cursorColor = Color(0xFFE0E0E0),
            focusedTextColor = Color(0xFF0B4747),     // 👈 typed text (focused)
            unfocusedTextColor = Color(0x806A7193)
        ),
        singleLine = true
    )
}

/*
InputTextField(
value = name,
onValueChange = { name = it },
placeholderText = "Parent/Guardian Full Name",
leadingIcon = painterResource(id = R.drawable.person)
)*/
