package com.compose.babyai.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.compose.babyai.R

//OrderCancelledDialog


@Composable
fun OrderCancelledDialog(
    title : String,
    description: String,
    onDismiss: () -> Unit,
    onBackToHome: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(50.dp))
                .background(Color.White)
                .padding(horizontal = 40.dp, vertical = 22.dp)
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // ❌ Close icon (top right)
                Box{


                    // 🗑 Delete Icon
                    Icon(
                        painter = painterResource(id = R.drawable.ic_order_cancle_icon),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(78.dp)
                            .align(Alignment.Center)
                    )


                }



                Spacer(modifier = Modifier.height(9.dp))

                // 📝 Title
                Text(
                    text = title/*"Order Cancelled"*/,
                    fontSize = 28.sp,
                    fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                // 📄 Message
                Text(
                    text = description/*"Your order cancellation has been \n confirmed."*/,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_medium)),
                    color = Color(0xB33C3C3C),
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp,
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.height(10.dp))

                // 🔘 Delete Button
                Box(
                    modifier = Modifier
                        .fillMaxWidth().padding(horizontal = 4.dp)
                        .height(55.dp)
                        .clip(RoundedCornerShape(26.dp))
                        .background(Color(0xFF1EC9C3))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {  onBackToHome() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Back to Home",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.quicksand_semibold))
                    )
                }

            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF2F2F2)
@Composable
fun OrderCancelledDialogPreview() {
    OrderCancelledDialog(
        title = "",
        description="",
        onDismiss = {},
        onBackToHome = {}
    )
}
