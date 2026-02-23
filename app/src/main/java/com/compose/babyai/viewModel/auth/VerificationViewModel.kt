package com.compose.babyai.viewModel.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.babyai.util.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerificationViewModel @Inject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(VerificationUiState())
    val uiState: StateFlow<VerificationUiState> = _uiState.asStateFlow()

    private var timerJob: Job? = null

    init {
        startTimer()
    }

    fun onOtpChange(index: Int, value: String) {
        if (value.length > 1) return
        if (value.isNotEmpty() && !value.all { it.isDigit() }) return

        val updatedList = _uiState.value.otpValues.toMutableList()
        updatedList[index] = value

        _uiState.update {
            it.copy(
                otpValues = updatedList,
                isOtpFilled = updatedList.all { digit -> digit.isNotEmpty() },
                otpError = null //  Clear error on typing
            )
        }
    }


    fun verifyOtp() {
        val state = _uiState.value

        if (!state.isOtpFilled) {
            _uiState.update {
                it.copy(otpError = "Please enter complete OTP")
            }
            return
        }

        // Clear previous error
        _uiState.update { it.copy(otpError = null) }

        // TODO: call API here

        sessionManager.setLogin(true)

        _uiState.update {
            it.copy(showSuccessDialog = true)
        }
    }


    fun dismissDialog() {
        _uiState.update {
            it.copy(showSuccessDialog = false)
        }
    }

    fun resendOtp() {
        startTimer()
    }

    private fun startTimer() {
        timerJob?.cancel()

        _uiState.update {
            it.copy(timeLeft = 30)
        }

        timerJob = viewModelScope.launch {
            while (_uiState.value.timeLeft > 0) {
                delay(1000L)
                _uiState.update {
                    it.copy(timeLeft = it.timeLeft - 1)
                }
            }
        }
    }
}

data class VerificationUiState(
    val otpValues: List<String> = List(5) { "" },
    val timeLeft: Int = 30,
    val isOtpFilled: Boolean = false,
    val showSuccessDialog: Boolean = false,
    val otpError: String? = null
)
