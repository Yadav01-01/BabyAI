package com.compose.babyai.ui.screens.profile.babyProfile

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.compose.babyai.R
import com.compose.babyai.navigation.Routes
import com.compose.babyai.ui.component.uiInput.BabyPhotoPickerRow
import com.compose.babyai.ui.component.uiInput.CommonOutlinedButton
import com.compose.babyai.ui.component.uiInput.CommonPrimaryButton
import com.compose.babyai.ui.component.uiInput.CommonTopBar
import com.compose.babyai.ui.component.uiInput.InputTextFieldWithoutIcon
import com.compose.babyai.ui.component.uiInput.SectionTitle
import com.compose.babyai.ui.dialog.DeleteBabysDetailsDialog
import com.compose.babyai.ui.dialog.DeleteProfileDialog
import com.compose.babyai.ui.spinner.CustomSpinner
import com.compose.babyai.ui.spinner.PreferredColorSpinner


@Composable
fun AddBabyProfile(navController: NavHostController) {
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
            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) -> {
                launcherCamera.launch(null)
            }

            else -> {
                permissionLauncher.launch(Manifest.permission.CAMERA)
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

            ) {

                CommonTopBar(
                    title = "Add Baby Profile",
                    onBackClick = {
                        navController.navigateUp()
                    }
                )


                Column(
                    modifier = Modifier
                        .fillMaxSize().padding(15.dp)
                        .verticalScroll(rememberScrollState()),
                ) {

                    SectionTitle("Baby's Profile Picture")

                    Spacer(modifier = Modifier.height(12.dp))

                    BabyPhotoPickerRow(
                        imageRes = profileImageUri,
                        onCamClick = {
                            navController.navigate(Routes.AiScan.createRoute("profileSetup"))
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

                    CommonPrimaryButton(
                        text = "Save & Next",
                        onClick = { navController.popBackStack() },

                        )

                    Spacer(modifier = Modifier.height(24.dp))

                }
            }
        }
    }
}



