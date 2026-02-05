package com.compose.babyai.data.model

import com.compose.babyai.R

//data class ProfileUIModel()
data class BabyUiModel(
    val id: Int,
    val name: String,
    val age: String,
    val image: Int = R.drawable.dummy_babay_image
)

data class ProfileUiState(
    val userName: String = "",
    val phone: String = "",
    val babies: List<BabyUiModel> = emptyList(),
    val wishlistCount: Int = 0,
    val notificationEnabled: Boolean = true
)

