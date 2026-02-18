package com.compose.babyai.viewModel.auth

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.compose.babyai.data.uistate.ContactType
import com.compose.babyai.data.uistate.LoginUiState
import com.compose.babyai.navigation.Routes
import com.compose.babyai.util.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onNameChange(name: String) {
        _uiState.update {
            it.copy(name = name, nameError = null)
        }
    }

    fun onEmailChange(email: String) {
        _uiState.update {
            it.copy(email = email, contactError = null)
        }
    }

    fun onPhoneChange(phone: String) {
        _uiState.update {
            it.copy(phoneNumber = phone.take(10), contactError = null)
        }
    }

    fun onContactTypeChange(type: ContactType) {
        _uiState.update {
            it.copy(
                selectedContactType = type,
                contactError = null
            )
        }
    }


    fun sendCode(navController: NavHostController) {
        val state = _uiState.value

        val nameError = validateName(state.name)
        val contactError = when (state.selectedContactType) {
            ContactType.EMAIL -> validateEmail(state.email)
            ContactType.PHONE -> validatePhone(state.phoneNumber)
        }

        if (nameError != null || contactError != null) {
            _uiState.update {
                it.copy(
                    nameError = nameError,
                    contactError = contactError
                )
            }
            return
        }

        // Validation success → API call
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            // TODO: repository.sendOtp(...)
            // Simulating success for now:
            navController.navigate(Routes.OtpVerify.route)

            _uiState.update { it.copy(isLoading = false) }
        }
    }

    // ---------------- VALIDATIONS ----------------

    private fun validateName(name: String): String? =
        when {
            name.isBlank() -> "Name is required"
            name.length < 3 -> "Name must be at least 3 characters"
            else -> null
        }

    private fun validateEmail(email: String): String? =
        when {
            email.isBlank() -> "Email is required"
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() ->
                "Enter a valid email"
            else -> null
        }

    private fun validatePhone(phone: String): String? =
        when {
            phone.isBlank() -> "Phone number is required"
            phone.length != 10 ->
                "Enter a valid 10-digit phone number"
            else -> null
        }
}
