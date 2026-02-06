package com.compose.babyai.ui.screens.profile

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.compose.babyai.R
import com.compose.babyai.ui.component.BabyPhotoPickerRow
import com.compose.babyai.ui.component.CommonOutlinedButton
import com.compose.babyai.ui.component.CommonPrimaryButton
import com.compose.babyai.ui.component.CommonTopBar
import com.compose.babyai.ui.component.CustomSwitch
import com.compose.babyai.ui.component.InputTextFieldWithoutIcon
import com.compose.babyai.ui.component.SectionTitle
import com.compose.babyai.ui.component.tealColor
import com.compose.babyai.ui.dialog.DeleteBabysDetailsDialog
import com.compose.babyai.ui.dialog.DeleteProfileDialog
import com.compose.babyai.ui.dialog.LogOutDialog
import com.compose.babyai.ui.spinner.CustomSpinner
import com.compose.babyai.ui.spinner.PreferredColorSpinner
import java.io.File

data class BabyProfileData(
    val id: Int,
    val imageRes: Int,
    val backgroundColor: Color
)

data class BabyAgeData(
    val id: Int,
    val number: String,
    val month: String
)

data class FabricItem(
    val id: Int,
    val name: String,
    var isSelected: Boolean = false
)

data class PreferredColorItem(
    val id: Int,
    val name: String,
    var isSelected: Boolean = false
)

@Composable
fun EditBabyProfile(navController: NavHostController) {
    val context = LocalContext.current
    var selectedProfileIndex by remember { mutableStateOf(0) }
    var selectedGender by remember { mutableStateOf("Baby Girl") }
    var babyNickname by remember { mutableStateOf("") }
    var selectedAge by remember { mutableStateOf("Newborn") }
    var expandedFabric by remember { mutableStateOf(false) }
    var expandedColors by remember { mutableStateOf(false) }
    var fullName by remember { mutableStateOf("Sarah Johnson") }
    var email by remember { mutableStateOf("sarah.johnson@email.com") }
    var phone by remember { mutableStateOf("+1 (555) 123-4567") }
    var selectedFabrics by remember { mutableStateOf(listOf<String>()) }
    var profileImageUri by remember { mutableStateOf<Uri?>(null) }
    val babyProfiles = listOf(
        BabyProfileData(
            id = 0,
            imageRes = R.drawable.dummy_img,
            backgroundColor = Color(0xFFFFC1E3)
        ),
        BabyProfileData(
            id = 1,
            imageRes = R.drawable.dummy_img,
            backgroundColor = Color(0xFFBFC6C9)
        ),
        BabyProfileData(
            id = 2,
            imageRes = R.drawable.dummy_img,
            backgroundColor = Color(0xFFF1D2BE)
        )
        // Aur items aasani se add kar sakte hain
    )

    val babyAge = listOf(
        BabyAgeData(
            id = 0,
            number = "New",
            month = "Months",
        ),
        BabyAgeData(
            id = 1,
            number = "0-3",
            month = "Months"
        ),
        BabyAgeData(
            id = 2,
            number = "3-6",
            month = "Months"
        )
        // Aur items aasani se add kar sakte hain
    )

    // Fabric options list
    val fabricOptions = remember {
        mutableStateListOf(
            FabricItem(1, "Organic Cotton", false),
            FabricItem(2, "Bamboo", false),
            FabricItem(3, "Muslin", false),
            FabricItem(4, "Fleece", false),
            FabricItem(5, "Lyocell", false),
            FabricItem(6, "Linen-Cotton Blend", false),
            FabricItem(7, "Modal", false),
            FabricItem(8, "Soft Blends", false),
            FabricItem(9, "Jersey Knit", false),
            FabricItem(10, "Cotton-Spandex Blend", false)
        )
    }

    val preferredColorOptions = remember {
        mutableStateListOf(
            PreferredColorItem(1, "Pastel", false),
            PreferredColorItem(2, "Bright", false),
            PreferredColorItem(3, "Neutral", false),
            PreferredColorItem(4, "Natural Tones", false),
            PreferredColorItem(5, "Cool Colors", false),
            PreferredColorItem(6, "Warm Colors", false),
            PreferredColorItem(7, "Modal", false),
            PreferredColorItem(8, "Soft Blends", false),
            PreferredColorItem(9, "Jersey Knit", false),
            PreferredColorItem(10, "Cotton-Spandex Blend", false)
        )
    }
    val tipsList = listOf(
        "You can add up to 3 child profiles.",
        "Keep sizes updated as your baby grows.",
        "We'll use this info to suggest the perfect outfits."
    )
    val launcherGallery = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        profileImageUri = uri
    }

    val launcherCamera = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap: Bitmap? ->
        bitmap?.let {
            val uri = saveBitmapToCache(context, it)
            profileImageUri = uri
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            launcherCamera.launch(null)
        } else {
            Toast.makeText(context, "Camera Permission is required", Toast.LENGTH_SHORT)
                .show()
        }
    }

    fun launchCameraWithPermissionCheck() {
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA) -> {
                launcherCamera.launch(null)
            }

            else -> {
                permissionLauncher.launch(android.Manifest.permission.CAMERA)
            }
        }
    }
    var showDialog1 by remember { mutableStateOf(false) }
    var showDialog2 by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.main_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillWidth
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp, end = 8.dp)
            ) {

                CommonTopBar(
                    title = "Edit Baby & Your Profile",
                    onBackClick = {
                        navController.navigateUp()
                    }
                )


                Column(
                    modifier = Modifier
                        .fillMaxSize().padding(start = 20.dp, end = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(modifier = Modifier.height(14.dp))
                    SectionTitle("Choose Baby Profile")

                    Spacer(modifier = Modifier.height(4.dp))


                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth(),

                        horizontalArrangement = Arrangement.spacedBy(12.dp),

                        ) {
                        itemsIndexed(babyProfiles) { index, profile ->
                            BabyProfileAvatar(
                                imageRes = profile.imageRes,
                                backgroundColor = profile.backgroundColor,
                                isSelected = selectedProfileIndex == profile.id,
                                onClick = { selectedProfileIndex = profile.id },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(92.dp) // Fixed width
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    SectionTitle("Baby's Profile Picture")

                    Spacer(modifier = Modifier.height(12.dp))

                    BabyPhotoPickerRow(
                        imageRes = profileImageUri,
                        onSelect = {
                            launchCameraWithPermissionCheck()
                        },
                        onSelect1 = {
                            launchCameraWithPermissionCheck()
                        }
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    SectionTitle("Baby Gender")

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Baby Boy
                        GenderButton(
                            text = "Baby Boy",
                            icon = R.drawable.ic_boy,
                            isSelected = selectedGender == "Baby Boy",
                            onClick = { selectedGender = "Baby Boy" },
                            modifier = Modifier.weight(1f)
                        )

                        GenderButton(
                            text = "Baby Girl",
                            icon = R.drawable.ic_girl,
                            isSelected = selectedGender == "Baby Girl",
                            onClick = { selectedGender = "Baby Girl" },
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    SectionTitle("Enter Baby's Nickname")

                    Spacer(modifier = Modifier.height(8.dp))

                    InputTextFieldWithoutIcon(
                        value = babyNickname,
                        onValueChange = { babyNickname = it },
                        placeholderText = "Enter name"
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    SectionTitle("Baby Age")

                    Spacer(modifier = Modifier.height(12.dp))

                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        itemsIndexed(babyAge) { index, profile ->

                            AgeButton(
                                number = profile.number,
                                month = profile.month,
                                isSelected = selectedAge == profile.number,
                                onClick = { selectedAge = profile.number },
                                modifier = Modifier
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    SectionTitle("Fabric Preferences")

                    Spacer(modifier = Modifier.height(8.dp))

                    CustomSpinner(
                        fabricOptions = fabricOptions,
                        modifier = Modifier.fillMaxWidth(),
                        selectedFabricNames = selectedFabrics,
                        onSelectionChanged = { updatedSelection ->
                            selectedFabrics = updatedSelection
                            println("Selected fabrics: $updatedSelection")
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    SectionTitle("Preferred Colors")

                    Spacer(modifier = Modifier.height(8.dp))

                    PreferredColorSpinner(
                        preferredColorOptions = preferredColorOptions,
                        modifier = Modifier.fillMaxWidth(),
                        selectedFabricNames = selectedFabrics,
                        onSelectionChanged = { updatedSelection ->
                            selectedFabrics = updatedSelection
                            println("Selected fabrics: $updatedSelection")
                        }
                    )

                    Spacer(modifier = Modifier.height(24.dp))


                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFE9FAFA), RoundedCornerShape(30.dp))
                            .padding(16.dp)
                    ) {
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(R.drawable.ic_tips_icon),
                                    contentDescription = "Tips",
                                    modifier = Modifier.size(32.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "Tips",
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.Black
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                tipsList.forEachIndexed { index, tip ->
                                    TipItem(text = "${index + 1}. $tip")
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    CommonOutlinedButton(
                        text = "Delete Baby Details",
                        onClick = { showDialog2 = true },

                        )

                    Spacer(modifier = Modifier.height(24.dp))

                    SectionTitle("Personal Information")

                    Spacer(modifier = Modifier.height(12.dp))

                    // Full Name
                    Text(
                        text = "Full Name",
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.nunito_regular)),
                        color = Color(0xFF737373),
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    InputTextFieldWithoutIcon(
                        value = fullName,
                        onValueChange = { fullName = it },
                        placeholderText = "Full name"
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Email Address
                    Text(
                        text = "Email Address",
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.nunito_regular)),
                        color = Color(0xFF737373),
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    InputTextFieldWithoutIcon(
                        value = email,
                        onValueChange = { email = it },
                        placeholderText = "Email"
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Phone
                    Text(
                        text = "Phone",
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.nunito_regular)),
                        color = Color(0xFF737373),
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    InputTextFieldWithoutIcon(
                        value = phone,
                        onValueChange = { phone = it },
                        placeholderText = "Phone"
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    CommonPrimaryButton(
                        text = "Save",
                        onClick = { /* Handle save */ },

                        )

                    Spacer(modifier = Modifier.height(16.dp))


                    CommonOutlinedButton(
                        text = "Delete Profile",
                        enabled = true,
                        onClick = { showDialog1 = true}
                    )

                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
    if (showDialog1) {
        DeleteProfileDialog(
            onDismiss = {showDialog1 = false},
            onDelete = {showDialog1 = false}
        )
    }
    if (showDialog2) {
        DeleteBabysDetailsDialog(
            onDismiss = {showDialog2 = false},
            onDelete = {showDialog2 = false}
        )
    }
}

@Composable
fun BabyProfileAvatar(
    imageRes: Int,
    backgroundColor: Color,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val tealColor = Color(0xFF1ECBCC)
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick() }
    ) {

        // Baby image
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(24.dp))
                .zIndex(0f)
                .then(
                    if (!isSelected)
                        Modifier.blur(2.dp)
                    else
                        Modifier
                )
                //  .background(backgroundColor)
                .border(
                    1.dp,
                    if (isSelected) tealColor else Color.Unspecified,
                    RoundedCornerShape(24.dp)
                )
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()

            )
        }

        if (isSelected) {

            Image(
                painter = painterResource(R.drawable.ic_check_icons),
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
                    .zIndex(1f)
                    .align(Alignment.TopEnd)
                    .offset(x = 5.dp, y = (-5).dp)
            )
        }
    }
}


@Composable
fun GenderButton(
    text: String,
    icon: Int,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(100.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(if (isSelected) Color(0xFFE8F8FF) else Color.White)
            .border(
                width = if (isSelected) 1.5.dp else 0.dp,
                color = if (isSelected) Color(0xFF2EC7F2) else Color.Transparent,
                shape = RoundedCornerShape(18.dp)
            )
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Image(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(45.dp)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = text,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.varela_round)),
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
        }
    }
}


@Composable
fun AgeButton(
    number: String,
    month: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(120.dp)
            .width(120.dp)
            .background(
                color = if (isSelected) Color(0xFFE9FAFA) else Color.Unspecified,
                shape = RoundedCornerShape(40.dp)
            )
            .border(
                width = if (isSelected) 1.5.dp else 0.dp,
                color = if (isSelected) tealColor else Color(0xFFD9D9D9),
                shape = RoundedCornerShape(40.dp)
            )
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {onClick() },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = number,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.baloo2_medium)),
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                lineHeight = 16.sp
            )
            Text(
                text = month,
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.nunito_regular)),
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                textAlign = TextAlign.Center,
                lineHeight = 16.sp
            )
        }
    }
}

@Composable
fun TipItem(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontFamily = FontFamily(Font(R.font.nunito_regular)),
        color = Color.Black,
        lineHeight = 18.sp
    )
}



fun saveBitmapToCache(context: Context, bitmap: Bitmap): Uri {
    val file = File(context.cacheDir, "profile_pic_${System.currentTimeMillis()}.png")
    file.outputStream().use {
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
        it.flush()
    }
    return FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider",
        file
    )
}


