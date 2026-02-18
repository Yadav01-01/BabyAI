package com.compose.babyai.viewModel

import androidx.lifecycle.ViewModel
import com.compose.babyai.data.model.BabyUiModel
import com.compose.babyai.data.model.ProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class BabyProfileViewModel  : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadProfile()
    }

    private fun loadProfile() {
        _uiState.value = ProfileUiState(
            userName = "Sarah Johnson",
            phone = "(406) 555-0120",
            wishlistCount = 12,
            notificationEnabled = true,
            babies = listOf(
                BabyUiModel(1, "Emma", "8 months"),
                BabyUiModel(2, "Oliver", "2 years")
            )
        )
    }

    fun toggleNotification(value: Boolean) {
        _uiState.value = _uiState.value.copy(notificationEnabled = value)
    }
}
