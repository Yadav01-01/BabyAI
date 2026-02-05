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

//LogOutDialog

@Composable
fun LogOutDialog(
    onDismiss: () -> Unit,
    onLogout: () -> Unit
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
                .clip(RoundedCornerShape(45.dp))
                .background(Color.White)
                .padding(horizontal = 18.dp, vertical = 22.dp)
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // ❌ Close icon (top right)
                Box{


                    // 🗑 Delete Icon
                    Icon(
                        painter = painterResource(id = R.drawable.logout_dialog_icon),
                        contentDescription = null,
                        tint = Color(0xFF1EC9C3),
                        modifier = Modifier
                            .size(98.dp)
                            .align(Alignment.Center)
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth().padding(top = 5.dp),
                        contentAlignment = Alignment.TopEnd
                    ) {
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
                    }

                }



                Spacer(modifier = Modifier.height(16.dp))

                // 📝 Title
                Text(
                    text = "Confirm Logout",
                    fontSize = 26.sp,
                    fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                // 📄 Message
                Text(
                    text = "Are you sure you want to log out of your account?",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_medium)),
                    color = Color(0xB33C3C3C),
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                // 🔘 Delete Button
                Box(
                    modifier = Modifier
                        .fillMaxWidth().padding(horizontal = 4.dp)
                        .height(52.dp)
                        .clip(RoundedCornerShape(26.dp))
                        .background(Color(0xFF1EC9C3))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {  onLogout() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Yes, Logout",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.quicksand_semibold))
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // ❎ Cancel
                Text(
                    text = "Cancel",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                    color = Color.Black,
                    modifier = Modifier .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { onDismiss() }
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF2F2F2)
@Composable
fun LogOutDialogPreview() {
    LogOutDialog(
        onDismiss = {},
        onLogout = {}
    )
}
