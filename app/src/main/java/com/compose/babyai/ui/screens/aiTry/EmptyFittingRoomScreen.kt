package com.compose.babyai.ui.screens.aiTry

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.babyai.R
import com.compose.babyai.ui.component.uiInput.CommonPrimaryButton
import com.compose.babyai.ui.theme.PrimaryColor

@Composable
fun EmptyFittingRoom(){
    Box(modifier = Modifier.fillMaxSize()) {

        // Background
        Image(
            painter = painterResource(id = R.drawable.main_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(bottom = 30.dp)
        ) {

            item { Spacer(modifier = Modifier.height(5.dp)) }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    Text(
                        text = "Fitting Room",
                        fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
                        fontSize = 22.sp,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.CenterStart)
                    )
                }
            }


            item { Spacer(modifier = Modifier.height(10.dp)) }

            item {
                Image(
                    painter = painterResource(R.drawable.empty_ic),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(434.dp),
                    contentScale = ContentScale.FillWidth
                )
            }

            item { Spacer(modifier = Modifier.height(10.dp)) }

            item {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                color = PrimaryColor,
                                fontFamily = FontFamily(Font(R.font.baloo2_semibold)),
                                fontSize = 24.sp
                            )
                        ) {
                            append("Try Cute Outfits Instantly\n")
                        }
                    },
                    textAlign = TextAlign.Center
                )
            }

            item {
                Text(
                    text = "Add outfits in fitting room to try them on your baby virtually",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_regular)),
                    color = Color(0XFFB0B0B0),
                    textAlign = TextAlign.Center,
                    lineHeight = 22.sp,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }

            item { Spacer(modifier = Modifier.height(20.dp)) }

            item {
                CommonPrimaryButton(
                    text = "Add Outfit",
                    onClick = { /* Handle save */ },
                    modifier = Modifier.padding(horizontal = 20.dp))
            }

            item { Spacer(modifier = Modifier.height(20.dp)) }
        }
    }
}