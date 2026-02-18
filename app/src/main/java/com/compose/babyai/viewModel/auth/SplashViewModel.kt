package com.compose.babyai.viewModel.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.babyai.util.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _event = MutableSharedFlow<SplashEvent>()
    val event = _event.asSharedFlow()

    init {
        startSplashTimer()
    }

    private fun startSplashTimer() {
        viewModelScope.launch {
            delay(2000L)
            if ( sessionManager.isLoggedIn()){
                _event.emit(SplashEvent.NavigateToMain)
            } else{
                _event.emit(SplashEvent.NavigateToOnboarding)
            }
        }
    }
}
sealed class SplashEvent {
    object NavigateToOnboarding : SplashEvent()
    object NavigateToMain : SplashEvent()
}
