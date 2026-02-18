package com.compose.babyai.ui.screens.aiTry

import android.net.Uri
import android.os.Environment
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.compose.babyai.R
import com.compose.babyai.navigation.Routes
import com.compose.babyai.ui.component.uiInput.AppButton
import com.compose.babyai.ui.theme.BabyAITheme
import com.compose.babyai.ui.theme.PrimaryColor
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ScanScreen(navController: NavHostController, navFrom: String) {
    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var capturedImageUri by remember { mutableStateOf<Uri?>(null) }
    var useAsAvatar by remember { mutableStateOf(false) }

    // Helper function to create a temporary image file
    fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)
    }

    // Launcher for capturing photo
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            imageUri?.let {
                capturedImageUri = it
            }
        }
    }

    // Launcher for picking image from gallery
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            capturedImageUri = it
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.main_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .size(52.dp)
                        .clip(CircleShape)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.draw_back_ic),
                        contentDescription = "Back",
                        tint = Color.Black,
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Scan",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.quicksand_semibold)),
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.scan_bg),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxWidth()
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    // Welcome Text
                    Text(
                        text = "Welcome to\nAI OutFit Scanner!",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp,
                        color = PrimaryColor,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.baloo2_semibold))
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Scan or upload your baby's picture to get\nperfect matching suggestion for clothes\nand accessories.",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        color = Color(0xFFB0B0B0),
                        fontFamily = FontFamily(Font(R.font.nunito_regular))
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    if (capturedImageUri != null) {
                        // Image Preview
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(225.dp)
                                .padding(horizontal = 10.dp)
                                .clickable {
                                    val encodedUri = Uri.encode(capturedImageUri.toString())
                                    navController.navigate(Routes.CamPreview.createRoute(encodedUri))
                                },
                            shape = RoundedCornerShape(30.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(capturedImageUri),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Action Buttons (Smaller)
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            // Take a Photo Button
                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(54.dp)
                                    .clip(RoundedCornerShape(30.dp))
                                    .background(Color(0xFFFBD606))
                                    .clickable {
                                        val file = createImageFile()
                                        val uri = FileProvider.getUriForFile(
                                            context,
                                            "${context.packageName}.provider",
                                            file
                                        )
                                        imageUri = uri
                                        cameraLauncher.launch(uri)
                                    },
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.cam_ic),
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Take a Photo",
                                    color = Color.White,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = FontFamily(Font(R.font.varela_round))
                                )
                            }

                            // Upload Image Button
                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(54.dp)
                                    .clip(RoundedCornerShape(30.dp))
                                    .clickable { galleryLauncher.launch("image/*") }
                                    .drawBehind {
                                        val strokeWidth = 1.5.dp.toPx()
                                        val dashWidth = 8.dp.toPx()
                                        val dashGap = 6.dp.toPx()

                                        drawRoundRect(
                                            color = PrimaryColor.copy(alpha = 0.6f),
                                            size = size,
                                            cornerRadius = CornerRadius(30.dp.toPx()),
                                            style = Stroke(
                                                width = strokeWidth,
                                                pathEffect = PathEffect.dashPathEffect(
                                                    floatArrayOf(dashWidth, dashGap),
                                                    0f
                                                )
                                            )
                                        )
                                    },
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.uploadic),
                                    contentDescription = null,
                                    tint = PrimaryColor,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Upload Image",
                                    color = PrimaryColor,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = FontFamily(Font(R.font.varela_round))
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Use as Avatar Checkbox
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Use this image as Avatar Profile Picture",
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.nunito_regular)),
                                color = Color(0xFF333333)
                            )
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(Color.Transparent)
                            ) {
                                Checkbox(
                                    checked = useAsAvatar,
                                    onCheckedChange = { useAsAvatar = it },
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = PrimaryColor,
                                        uncheckedColor = Color.LightGray,
                                        checkmarkColor = Color.White
                                    )
                                )
                            }

                        }

                    } else {
                        // Initial Action Cards
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            // Smart Scan Card (Camera)
                            Card(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(145.dp)
                                    .clickable {
                                        val file = createImageFile()
                                        val uri = FileProvider.getUriForFile(
                                            context,
                                            "${context.packageName}.provider",
                                            file
                                        )
                                        imageUri = uri
                                        cameraLauncher.launch(uri)
                                    },
                                shape = RoundedCornerShape(30.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFFBD606)),
                                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.cam_ic),
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(48.dp)
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = "Take a Photo",
                                        color = Color.White,
                                        fontSize = 16.sp,
                                        fontFamily = FontFamily(Font(R.font.varela_round))
                                    )
                                }
                            }

                            // Upload Image Card
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(145.dp)
                                    .clip(RoundedCornerShape(30.dp))
                                    .clickable { galleryLauncher.launch("image/*") }
                                    .drawBehind {
                                        val strokeWidth = 2.dp.toPx()
                                        val dashWidth = 12.dp.toPx()
                                        val dashGap = 8.dp.toPx()

                                        drawRoundRect(
                                            color = PrimaryColor.copy(alpha = 0.5f),
                                            size = size,
                                            cornerRadius = CornerRadius(30.dp.toPx()),
                                            style = Stroke(
                                                width = strokeWidth,
                                                pathEffect = PathEffect.dashPathEffect(
                                                    floatArrayOf(dashWidth, dashGap),
                                                    0f
                                                )
                                            )
                                        )
                                    }
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.uploadic),
                                        contentDescription = null,
                                        tint = Color.Unspecified,
                                        modifier = Modifier.size(48.dp)
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = "Upload Image",
                                        color = PrimaryColor,
                                        fontSize = 16.sp,
                                        fontFamily = FontFamily(Font(R.font.varela_round))
                                    )
                                }
                            }
                        }
                    }
                }
            }


            Spacer(modifier = Modifier.height(40.dp))

            // How it works Section
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(30.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE9FAFA)),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.que_ic),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(32.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "How it works",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
                            color = Color.Black
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    val steps = listOf(
                        "Capture or upload your baby's photo.",
                        "Our AI extracts color, pattern, fabric and style.",
                        "Get curated outfit & accessory suggestions that match."
                    )

                    steps.forEachIndexed { index, step ->
                        Text(
                            text = "${index + 1}. $step",
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.nunito_regular)),
                            color = Color.Black,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Action Buttons (Save and Add Details)
            if (capturedImageUri != null){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Save Button
                    AppButton(
                        text = "Save",
                        onClick = {
                            if (navFrom == "profileSetup") {
                                navController.previousBackStackEntry?.savedStateHandle?.set("profile_image", capturedImageUri.toString())
                            }
                            navController.popBackStack()
                        },
                        modifier = Modifier.weight(1f).height(56.dp).border(1.dp, Color.Black, RoundedCornerShape(40.dp)),
                        buttonColors = Color.White,
                        textColor = Color.Black
                    )

                    // Add Details Button
                    if (navFrom != "profileSetup"){
                        AppButton(
                            text = "Add Details",
                            onClick = { navController.navigate(Routes.AddBabyProfile.route) },
                            modifier = Modifier.weight(1f).height(56.dp),
                            buttonColors = PrimaryColor
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Tip Section
            Text(
                text = "Tip: For best results, lay the outfit flat on a plain background and ensure good lighting.",
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                color = Color.Gray,
                fontFamily = FontFamily(Font(R.font.nunito_regular))
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScanScreenPreview() {
    BabyAITheme {
        ScanScreen(navController = rememberNavController(), navFrom = "navFrom")
    }
}
