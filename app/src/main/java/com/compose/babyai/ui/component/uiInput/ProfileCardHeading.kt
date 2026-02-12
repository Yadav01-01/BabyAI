package com.compose.babyai.ui.component.uiInput

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.compose.babyai.R

@Composable
fun ProfileCardHeading(text : String){
    Text(
        text = text,
        fontSize = 18.sp,
        fontFamily = FontFamily(Font(R.font.nunito_medium)),
        fontWeight = FontWeight.Medium,
        color = Color.Black
    )
}
