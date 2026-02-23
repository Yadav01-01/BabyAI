package com.compose.babyai.viewModel.profileSetup

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Collections.emptyList
import javax.inject.Inject

@HiltViewModel
class ProfileSetupViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileSetupUiState())
    val uiState: StateFlow<ProfileSetupUiState> = _uiState.asStateFlow()

    fun updateNickname(value: String) {
        _uiState.update { it.copy(nickname = value) }
    }

    fun updateAgeRange(value: String) {
        _uiState.update { it.copy(selectedAgeRange = value) }
    }

    fun updateGender(value: String) {
        _uiState.update { it.copy(selectedGender = value) }
    }

    fun updateProfileImage(uri: String) {
        _uiState.update { it.copy(profileImageUri = uri) }
    }

    fun toggleFabric(fabric: String) {
        _uiState.update { state ->
            val updated = state.selectedFabrics.toMutableList()
            if (updated.contains(fabric)) updated.remove(fabric)
            else updated.add(fabric)

            state.copy(selectedFabrics = updated)
        }
    }

    fun toggleColor(color: String) {
        _uiState.update { state ->
            val updated = state.selectedColors.toMutableList()
            if (updated.contains(color)) updated.remove(color)
            else updated.add(color)

            state.copy(selectedColors = updated)
        }
    }

    fun nextStep() {
        _uiState.update {
            if (it.currentStep < 4) it.copy(currentStep = it.currentStep + 1)
            else it
        }
    }

    fun previousStep() {
        _uiState.update {
            if (it.currentStep > 1) it.copy(currentStep = it.currentStep - 1)
            else it
        }
    }

    //  Important logic
    fun isNextEnabled(): Boolean {
        val state = _uiState.value
        return when (state.currentStep) {
            1 -> state.nickname.isNotBlank() && state.selectedAgeRange.isNotBlank()
            2 -> state.selectedGender.isNotBlank() && state.profileImageUri != null
            3 -> state.selectedFabrics.isNotEmpty()
            4 -> state.selectedColors.isNotEmpty()
            else -> false
        }
    }
}


data class ProfileSetupUiState(
    val currentStep: Int = 1,

    // Step 1
    val nickname: String = "",
    val selectedAgeRange: String = "",

    // Step 2
    val selectedGender: String = "",
    val profileImageUri: String? = null,

    // Step 3
    val selectedFabrics:  MutableList<String> = emptyList(),

    // Step 4
    val selectedColors:  MutableList<String> = emptyList()
)
