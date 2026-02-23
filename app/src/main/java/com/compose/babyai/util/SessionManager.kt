package com.compose.babyai.util

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import androidx.core.content.edit

@Singleton
class SessionManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREF_NAME = "baby_ai_prefs"
        private const val IS_LOGIN = "is_login"
    }

    /**
     * Save login status
     */
    fun setLogin(isLoggedIn: Boolean) {
        prefs.edit { putBoolean(IS_LOGIN, isLoggedIn) }
    }

    /**
     * Check if user is logged in
     */
    fun isLoggedIn(): Boolean {
        return prefs.getBoolean(IS_LOGIN, false)
    }

    /**
     * Clear all session data
     */
    fun logout() {
        prefs.edit { clear() }
    }
}
