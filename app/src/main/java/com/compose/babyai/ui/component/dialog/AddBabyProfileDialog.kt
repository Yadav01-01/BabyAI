package com.compose.babyai.ui.component.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.compose.babyai.R
import com.compose.babyai.ui.component.uiInput.AppButton
import com.compose.babyai.ui.theme.PrimaryColor

@Composable
fun AddBabysProfileDialog(
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
                        painter = painterResource(id = R.drawable.profile_ic),
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
