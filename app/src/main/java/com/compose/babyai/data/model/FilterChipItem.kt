package com.compose.babyai.data.model

data class FilterChipItem(
    val title: String,
    val type: SearchFilterType,
    val isSelected: Boolean = false
)


enum class SearchFilterType {
    LATEST,
    BRAND,
    SIZE,
    GENDER,
    COLOR,
    PRICE
}

