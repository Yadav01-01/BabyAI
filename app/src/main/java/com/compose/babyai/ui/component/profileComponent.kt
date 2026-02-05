package com.compose.babyai.ui.component

import android.net.Uri
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.compose.babyai.R
import com.compose.babyai.ui.theme.gradientBrush
import com.compose.babyai.ui.theme.gradientGreyBrush
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.zIndex
import com.compose.babyai.data.model.BabyUiModel
import com.compose.babyai.data.model.ProfileUiState

fun getInitials(name: String?): String {
    if (name.isNullOrBlank()) return "?"

    return name
        .trim()
        .split("\\s+".toRegex())       // multiple spaces handle
        .take(2)                      // only first 2 words
        .mapNotNull { it.firstOrNull()?.uppercase() }
        .joinToString(" ")
}


val tealColor = Color(0xFF00BFA5)
@Composable
fun ProfileHeaderCard(state : ProfileUiState, babies: List<BabyUiModel>, onBabyClick:(BabyUiModel) -> Unit, onEditClick:()-> Unit, onAddBabyClick: ()-> Unit  ) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(26.dp))
            .background(Color(0xFF1ECBCC))
            .padding(15.dp)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    getInitials(state.userName)/*"S J"*/,
                    fontSize = 16.sp,
                    color = Color(0xFF0B4747),
                    fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                    fontWeight = FontWeight.SemiBold
                )

            }

            Spacer(modifier = Modifier.width(10.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = state.userName/*"Sarah Johnson"*/,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = state.phone/*"(406) 555-0120"*/,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_medium)),
                    color = Color(0xFFE6E6E6),
                    fontWeight = FontWeight.Medium
                )
            }

            Image(
                painter = painterResource(id = R.drawable.ic_edit_icon),
                contentDescription = null,
                modifier = Modifier.wrapContentSize() .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    onEditClick()
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {

            // 👶 Dynamic Babies
            items(
                items = babies,
                key = { it.id }   // ⭐ important for recomposition
            ) { baby ->
                BabyItemCard(
                    type = BabyCardType.BABY,
                    name = baby.name,
                    age = baby.age,
                    modifier = Modifier,
                    onClick = { onBabyClick(baby) }
                )
            }

            // ➕ Always last
            item {
                BabyItemCard(
                    type = BabyCardType.ADD,
                    modifier = Modifier,
                    onClick = onAddBabyClick
                )
            }
        }

        /*    Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                BabyCard("Emma", "8 months", onBabyClick = {
                    onBabyClick()
                }, modifier = Modifier.weight(1f).height(130.dp))
                BabyCard("Oliver", "2 years",onBabyClick = {
                    onBabyClick()
                },modifier = Modifier.weight(1f).height(130.dp))
                AddBabyCard( modifier = Modifier.weight(1f).height(130.dp),onAddBabyClick={onAddBabyClick()})
            }*/
    }
}



enum class BabyCardType {
    BABY,
    ADD
}

@Composable
fun BabyItemCard(
    type: BabyCardType,
    name: String = "",
    age: String = "",
    image : Int = R.drawable.dummy_babay_image,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.width(85.dp).height(130.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick() }
            .padding(horizontal = 5.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (type == BabyCardType.BABY) {
            // 👶 Baby Image
            Box(
                modifier = Modifier
                    .size(55.dp)
                    .clip(CircleShape)
            ) {
                AsyncImage(
                    model = image,
                    contentDescription = null,
                    modifier = Modifier.matchParentSize()
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = name,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.nunito_bold)),
                color = Color(0xFF0B4747),
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = age,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.nunito_regular)),
                color = Color.Black
            )
        } else {
            // ➕ Add Baby
            Image(
                painter = painterResource(id = R.drawable.ic_add_icon),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Add Baby",
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.nunito_bold)),
                color = Color(0xFF0B4747),
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontFamily = FontFamily(Font(R.font.nunito_semibold)),
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        modifier = Modifier.padding(bottom = 20.dp)
    )
}

@Composable
fun QuickActionItem(icon: Int, title: String, subtitle: String, onNextScreenClick: () -> Unit) {
    SettingBaseItem(icon, title, subtitle,onNextScreenClick={
        onNextScreenClick()
    })
}

@Composable
fun SettingItem(icon: Int, title: String,onNextScreenClick: () -> Unit) {
    SettingBaseItem(icon, title, null,onNextScreenClick={
        onNextScreenClick()
    })
}

@Composable
fun SettingBaseItem(
    icon: Int,
    title: String,
    subtitle: String?,
    onNextScreenClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth() .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onNextScreenClick()}
            .clip(RoundedCornerShape(55.dp))
            .background(Color(0xFFEFF9F8))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(CircleShape).
                background(Color(0xFFFFFBE6)),
            contentAlignment = Alignment.Center
        ){
            Image(
                painterResource(icon),
                contentDescription = null,
                // tint = Color(0xFF179899),
                modifier = Modifier.size(21.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(title,
                fontSize = 16.sp,
                color = Color(0xFF1C1C1C),
                fontFamily = FontFamily(Font(R.font.varela_round)),
                fontWeight = FontWeight.Normal)
            subtitle?.let {
                Text(it,  fontSize = 14.sp,
                    color = Color(0xFF828282),
                    fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                    fontWeight = FontWeight.SemiBold)
            }
        }

        Image(
            painter = painterResource(id = R.drawable.ic_right_arrow),
            contentDescription = null)
        Spacer(modifier = Modifier.width(20.dp))
    }

    Spacer(modifier = Modifier.height(12.dp))
}



@Composable
fun NotificationItem() {
    var enabled by remember { mutableStateOf(true) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFEFF9F8))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // Icon
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(CircleShape)
                .background(Color(0xFFFFFBE6)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_blue_notification_icon),
                contentDescription = null,
                modifier = Modifier.size(21.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // ⭐ Text section (weight = 1f)
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Notifications",
                fontSize = 16.sp,
                color = Color(0xFF1C1C1C),
                fontFamily = FontFamily(Font(R.font.varela_round)),
                fontWeight = FontWeight.Normal,
                maxLines = 1,
            )

            Text(
                text = "Manage your notifications",
                fontSize = 14.sp,
                color = Color(0xFF828282),
                fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
            )
        }

        // ⭐ Switch (no weight)
        CustomSwitch(
            checked = enabled,
            onCheckedChange = { enabled = it },
            width = 36.dp,
            height = 20.dp,
            thumbSize = 12.dp
        )
    }
}


@Composable
fun LogoutButton(onLogOutClick:()->Unit) {
    Column(modifier = Modifier.fillMaxWidth().height(120.dp).clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        onLogOutClick()
    }, horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .background(Color(0xFFF31D1D)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id= R.drawable.ic_logout_icon2),
                contentDescription = null,
            )
        }
        /*Image(painter = painterResource(id = R.drawable.ic_logout_icon), contentDescription = null,
            modifier = Modifier.size(110.dp))*/
        Spacer(Modifier.height(18.dp))
        Text("Logout",
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.nunito_semibold)),
            color = Color(0xFFF31D1D),
            fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun CustomSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    width: Dp = 28.dp,
    height: Dp = 16.dp,
    thumbSize: Dp = 12.dp,
    trackColor: Brush = if (checked) gradientBrush else gradientGreyBrush,
    thumbColor: Color = if (checked) Color.White else Color.Gray
) {
    val thumbOffset by animateDpAsState(
        targetValue = if (checked) width - thumbSize - 3.dp else 1.dp,
        label = ""
    )

    Box(
        modifier = modifier
            .width(width)
            .height(height)
            .clip(CircleShape)
            .background(trackColor)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {  onCheckedChange(!checked) }
            .padding(2.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .size(thumbSize)
                .offset(x = thumbOffset)
                .background(thumbColor, CircleShape)
        )
    }
}



@Composable
fun NotificationItemFromVM(
    enabled: Boolean,
    onToggle: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(55.dp))
            .background(Color(0xFFEFF9F8))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
// Icon
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(CircleShape)
                .background(Color(0xFFFFFBE6)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_blue_notification_icon),
                contentDescription = null,
                modifier = Modifier.size(21.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))
        // ⭐ Text section (weight = 1f)
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Notifications",
                fontSize = 16.sp,
                color = Color(0xFF1C1C1C),
                fontFamily = FontFamily(Font(R.font.varela_round)),
                fontWeight = FontWeight.Normal,
                maxLines = 1,
            )

            Text(
                text = "Manage your notifications",
                fontSize = 14.sp,
                color = Color(0xFF828282),
                fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
            )
        }

        CustomSwitch(
            checked = enabled,
            onCheckedChange = onToggle
        )
    }
}





@Composable
fun BabyPhotoPickerRow(
    imageRes : Any? =R.drawable.dummy_img,
    onSelect: (Int) -> Unit = {},
    onSelect1: (Int) -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        //.padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Box(
            modifier = Modifier.size(75.dp)
                .background(Color.Unspecified)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {  }
        ) {


            Box(
                modifier = Modifier.fillMaxSize()
                    .clip(RoundedCornerShape(16.dp))
                    .zIndex(0f)
            ) {
                AsyncImage(
                    model = imageRes ?: R.drawable.dummy_img,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                    placeholder = painterResource(R.drawable.dummy_img),
                    error = painterResource(R.drawable.dummy_img)
                )
            }

//            // ✅ Selected check badge
//            if (isSelected) {

            Image(
                painter = painterResource(R.drawable.ic_image_edit_icon),
                contentDescription = null,
                modifier = Modifier.size(20.dp).zIndex(1f).align(Alignment.TopEnd)
                    .offset(x = 5.dp, y = (-5).dp).clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {onSelect1(3)}
            )
            //}
        }

        Box(
            modifier = Modifier.size(75.dp)
                .clip(RoundedCornerShape(16.dp)).zIndex(0f)
            //  .background(backgroundColor)
        ) {
            Image(
                painter = painterResource(id = R.drawable.emoji_image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()

            )
        }

        // 3️⃣ Camera (Dashed Border)
        CameraItem(
            onClick = { onSelect(2) }
        )
    }
}


@Composable
fun PhotoItem(
    isSelected: Boolean,
    showCheck: Boolean = false,
    onClick: () -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF2F2F2))
            .then(if (!isSelected) Modifier.blur(6.dp).alpha(0.5f) else Modifier)
            .border(
                2.dp,
                if (isSelected) tealColor else Color.Transparent,
                RoundedCornerShape(16.dp)
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {  onClick() },
        contentAlignment = Alignment.Center
    ) {
        content()

        if (showCheck && isSelected) {
            /*            Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(6.dp)
                                .size(18.dp)
                                .background(tealColor, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(12.dp)
                            )
                        }*/
            Image(
                painter = painterResource(R.drawable.ic_image_edit_icon),
                contentDescription = null,
                modifier = Modifier.size(20.dp) .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {

                }
            )
        }
    }
}
@Composable
fun CameraItem(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .height(75.dp).fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFEFFFFF), shape = RoundedCornerShape(16.dp))
            .drawBehind {
                drawRoundRect(
                    color = tealColor,
                    style = Stroke(
                        width = 2.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(
                            floatArrayOf(12f, 8f)
                        )
                    ),
                    cornerRadius = CornerRadius(50f, 50f)
                )
            }
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_solar_camera),
            contentDescription = "Camera",
            tint = tealColor,
            modifier = Modifier.size(32.dp)
        )
    }
}



@Composable
fun CommonPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    backgroundColor: Color = Color(0xFF1ECBCC),
    textColor: Color = Color.White
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(25.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            disabledContainerColor = backgroundColor.copy(alpha = 0.5f)
        )
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
            fontWeight = FontWeight.SemiBold,
            color = textColor
        )
    }
}


@Composable
fun CommonOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    containerColor: Color = Color(0xFFEFE4D8),
    contentColor: Color = Color(0xFFFA3230),
    borderColor: Color = Color(0xFFFA3230)
) {
    OutlinedButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(25.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = containerColor.copy(alpha = 0.5f),
            disabledContentColor = contentColor.copy(alpha = 0.5f)
        ),
        border = BorderStroke(
            width = 1.dp,
            color = borderColor
        )
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
            fontWeight = FontWeight.SemiBold
        )
    }
}


@Composable
fun TopBar(onBackClick:()->Unit,onSearchClick:()->Unit,onWishListClick:()->Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Back Button
            Image(
                painter = painterResource(id = R.drawable.draw_back_ic),
                contentDescription = "Back",
                modifier = Modifier
                    .size(55.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {onBackClick() }
            )

            Text(
                text = "My Orders",
                fontSize = 20.sp,
                color = Color.Black,
                fontFamily = FontFamily(Font(R.font.quicksand_semibold))
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Search Icon

            Image(
                painter = painterResource(id = R.drawable.ic_top_search_icon),
                contentDescription = "Back",
                modifier = Modifier
                    .size(50.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {onSearchClick()
                    }
            )


            Image(
                painter = painterResource(id = R.drawable.ic_top_wishlist_icon),
                contentDescription = "Back",
                modifier = Modifier
                    .size(50.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {onWishListClick()
                    }
            )
        }
    }
}

@Composable
fun CommonTopBar(
    title: String,
    modifier: Modifier = Modifier,
    showBack: Boolean = true,
    onBackClick: () -> Unit = {},
    titleColor: Color = Color.Black
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (showBack) {
            Image(
                painter = painterResource(id = R.drawable.draw_back_ic),
                contentDescription = "Back",
                modifier = Modifier
                    .size(50.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        onBackClick()
                    }
            )

            Spacer(modifier = Modifier.width(12.dp))
        }

        Text(
            text = title,
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
            fontWeight = FontWeight.SemiBold,
            color = titleColor
        )
    }
}
