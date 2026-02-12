package com.compose.babyai.data.uistate

data class LoginUiState(
    val name: String = "",

    val email: String = "",
    val phoneNumber: String = "",

    val selectedContactType: ContactType = ContactType.PHONE,

    val nameError: String? = null,
    val contactError: String? = null,

    val isLoading: Boolean = false
)

enum class ContactType {
    EMAIL,
    PHONE
}